package app.creditapp.ln.worker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import app.base.SourceTemplate;
import app.creditapp.cred.bo.CifEvalBo;
import app.creditapp.cred.entity.CifEval;
import app.creditapp.inf.client.RulesService;
import app.creditapp.inf.client.entity.Request;
import app.creditapp.inf.client.entity.RequestObj;
import app.creditapp.inf.client.entity.ReturnObj;
import app.creditapp.inf.client.entity.RuleFact;
import app.creditapp.ln.entity.LnApplyMid;
import app.creditapp.redis.util.RedisUtil;
import app.util.syslog.bo.SysExceptionBo;

import com.alibaba.fastjson.JSON;

/**
 * @作者 DHCC-SONG
 * @日期 Jun 6, 2016
 * @描述 业务处理A节点 是否重复 筛查 拆分客户 评级 准入
 */
public class WorkAforValidate implements Runnable {
	Logger logger = Logger.getLogger(WorkAforValidate.class);
	private LnApplyMid lnApplyMid;
	
	public WorkAforValidate(LnApplyMid lnApplyMid) {
		this.lnApplyMid = lnApplyMid;
	}
	/***
	 * @作者 DHCC-SONG
	 * @日期 Jun 29, 2016
	 * @方法说明：判断是否重复进件、筛查、拆分客户表、评级，准入
	 * @返回参数 void
	 */
	public void run() {
		String _val_result = "0"; // 默认筛查不通过
		String _split_result = "0"; // 拆分成功标志，默认失败
		String _repeat_result = "0";
		try {
		    if(lnApplyMid==null){
		    	  logger.error("A任务处理失败,接收到数据为空！");
		    }else{
		    	  logger.info("APPID:"+lnApplyMid.getAppId()+" WORK A 处理开始");
				  // 调用存储过程进行内部规则筛查
		    	  _repeat_result = WorkUtils.getInstance().proc_pact_repeat(lnApplyMid.getAppId());
		    	  if("0".equals(_repeat_result)){
		    		  //进件不重复，进行内部规则筛查
		    		  _val_result = WorkUtils.getInstance().proc_ln_screen(lnApplyMid.getAppId());//获得输出参数
				      if("1".equals( _val_result )){  // 筛查没有问题，则进行拆分
				    	  // 调用存储过程进行客户信息拆分
					      _split_result = WorkUtils.getInstance().proc_cif_split(lnApplyMid.getAppId()); // 获得输出参数
					      if("1".equals( _split_result )){  // 客户信息拆分没有问题，则进行评级
					    	  //  调用规则引擎进行客户评级返回json串
//					    	  ReturnObj ro = cifScore(lnApplyMid);
					    	  String sRo = "0000";
					    	  //取出得分与评级Id
					    	  if(sRo!=null&&"0000".equals(sRo)){
						    	  int score = 100;
						    	  String resultId = "1000001";
						    	  //将评级结果更新至CIF_EVAL表中 根据申请ID和身份证号更新.0
						    	  int result = cifEvalUpdate(lnApplyMid.getAppId(),lnApplyMid.getIdNo(),score,resultId);
						    	  //更新业务阶段表中的EVAL_STS为02已评级
						    	  if( result!=0 ){
						    		  WorkUtils.getInstance().proc_up_stage(lnApplyMid.getAppId(), "EVAL_STS", "02", "客户已评级");
						    	  }
					    	  }else{
					    		  WorkUtils.getInstance().proc_up_stage(lnApplyMid.getAppId(), "EVAL_STS", "01", "客户未评级");
					    		  logger.info("调用规则引擎进行客户评分出错，客户未评级；-[申请编号AppId=" + lnApplyMid.getAppId()+",合同号PactNo="+lnApplyMid.getPactNo()+"]");
					    	  }
					    	  //  如果评级大于C 则push入2号消息队列
					    	  Jedis jedis = RedisUtil.getJedis();
					  		  jedis.lpush(RedisUtil.QUEUE2, JSON.toJSONString(lnApplyMid));// 只写入业务申请编号
					  		  jedis.close();
					  		  logger.info("A任务处理成功-[申请编号AppId=" + lnApplyMid.getAppId()+",合同号PactNo="+lnApplyMid.getPactNo()+"]");
					      }else{
					    	  logger.info("A任务处理失败-客户拆分失败-PKG_LN_APPLY.PROC_CIF_SPLIT-[申请编号AppId=" + lnApplyMid.getAppId()+",合同号PactNo="+lnApplyMid.getPactNo()+"]");
					      }
				      }else{
				    	  logger.info("A任务处理失败-筛查失败-PKG_LN_APPLY.PROC_LN_SCREEN-[申请编号AppId=" + lnApplyMid.getAppId()+",合同号PactNo="+lnApplyMid.getPactNo()+"]");
				      }
		    	  }else{
		    		  logger.info("A任务处理失败-进件重复-PKG_LN_APPLY.PACT_REPEAT-[申请编号AppId=" + lnApplyMid.getAppId()+",合同号PactNo="+lnApplyMid.getPactNo()+"]");
		    	  } 
		    }
//			logger.info(message);
		} catch (Exception e) {
			SysExceptionBo sysExceptionBo = (SysExceptionBo) SourceTemplate.getSpringContextInstance().getBean("sysExceptionBo");
			sysExceptionBo.insertNewWorkExceptionLog(e, lnApplyMid.getAppId());
			e.printStackTrace();
		}
	}
	
	/***
	 * @作者 wangtao
	 * @日期 Jul 11, 2016
	 * @方法说明：调用规则引擎 根据客户名称 进行客户评级
	 * @返回参数 评级得分
	 */
	public ReturnObj cifScore(LnApplyMid ln){
		//拼接传入数据
		RequestObj requestObj = new RequestObj();
		Request request = new Request();
		RuleFact ruleFact = new RuleFact();
		ruleFact.setCifName(ln.getCifName());
		ruleFact.setAge(birthToAge(ln.getBirthDay()));
		ruleFact.setCifNameScore(0);
		ruleFact.setAgeScore(0);
		List<RuleFact> list = new ArrayList<RuleFact>();
		list.add(0, ruleFact);
		request.setRuleName("cifEval");
		request.setRuleFact(list);
		requestObj.setUser("1001");
		requestObj.setPassword("1");
		requestObj.setRequest(request);
		ReturnObj ro = null;
		try {
			//连接规则引擎
			RulesService rs = (RulesService) SourceTemplate.getSpringContextInstance().getBean("RulesService");
			//调用规则引擎得到返回的字符串
			String strout = rs.executeRule(JSON.toJSONString(requestObj));
			ro = (ReturnObj) JSON.parseObject(strout, ReturnObj.class);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("客户评级调用规则引擎失败");
		}
	    return ro;
	}
	
	/***
	 * @作者 wangtao
	 * @日期 Jul 11, 2016
	 * @方法说明：将评级结果更新至CIF_EVAL表中 根据申请ID和身份证号更新
	 * @返回参数 无
	 */
	public int cifEvalUpdate(String appId,String idNo,int score,String resultId){
		//获取当前日期并统一格式格式
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String evalDate = df.format(new Date()).toString();
		//其他字段的值
		String evalSts = "01";
		String grade = "A";
		String evalDesc = "信用极好";
		if(score<60){
			grade = "C";
			evalDesc = "信用极差";
		}
		//设置需要更新的字段值
		CifEval cifEval = new CifEval();
		cifEval.setEvalDate(evalDate);
		cifEval.setEvalSts(evalSts);
		cifEval.setGrade(grade);
		cifEval.setScore(score);
		cifEval.setIdNo(idNo);
		cifEval.setAppId(appId);
		cifEval.setEvalDesc(evalDesc);
		cifEval.setResultId(resultId);
		//使用Bo对cifEval表进行更新
		CifEvalBo cifEvalBo = (CifEvalBo) SourceTemplate.getSpringContextInstance().getBean("cifEvalBo");
		int result = cifEvalBo.cifScoreUpdate(cifEval);
		return result;
	}
	/***
	 * @作者 wangtao
	 * @日期 Jul 12, 2016
	 * @方法说明：将生日转换为年龄
	 * @返回参数 年龄
	 */
	public int birthToAge(String birth) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String dateString = formatter.format(new Date());
		Date birthDate = null;
		Date currentTime = null;
		int year=0;
		try {
			birthDate = formatter.parse(birth);
			currentTime = formatter.parse(dateString);
			long day = (currentTime.getTime() - birthDate.getTime()) / (24 * 60 * 60 * 1000);
			year = (int) day / 365;
		} catch (Exception e) {
		}		
		return year;
	}
	// 复写父类的方法
	public String toString(){
		String ret = "";
		if( lnApplyMid!=null ){
			ret = "业务ID："+ lnApplyMid.getAppId()+",合同编号："+lnApplyMid.getPactNo();
		}
		return ret;
	}
}
