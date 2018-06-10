package app.creditapp.ln.worker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import app.base.SourceTemplate;
import app.creditapp.inf.client.RuleTrans;
import app.creditapp.inf.client.RulesService;
import app.creditapp.inf.client.entity.Request;
import app.creditapp.inf.client.entity.RequestObj;
import app.creditapp.inf.client.entity.ReturnObj;
import app.creditapp.inf.client.entity.RuleFact;
import app.creditapp.inf.entity.WsIn2001;
import app.creditapp.ln.bo.PreApplyBo;
import app.creditapp.ln.entity.PreApply;
import app.util.syslog.bo.SysExceptionBo;

import com.alibaba.fastjson.JSON;

/**
 * @作者 DHCC-SONG
 * @日期 Jun 6, 2016
 * @描述 业务处理P节点 判断是否重复进件、筛查、自动审批
 */
public class WorkPforPreScree implements Runnable {
	Logger logger = Logger.getLogger(WorkPforPreScree.class);
	private String batchNo;
	
	public WorkPforPreScree(String batchNo) {
		this.batchNo = batchNo;
	}
	/***
	 * @作者 DHCC-SONG
	 * @日期 Jun 29, 2016
	 * @方法说明：判断是否重复进件、筛查、自动审批
	 * @返回参数 void
	 */
	public void run() {
		
		String _val_result = "0"; // 是否进行了筛查0出现异常1筛查完成
		try {
		    if(batchNo==null||"".equals(batchNo)){
		    	  logger.error("P任务处理失败,接收到数据为空！");
		    }else{
		    	_val_result = WorkUtils.getInstance().proc_pre_screen(batchNo);//获得输出参数
		    	if("1".equals(_val_result)){
		    		PreApply preApply = new PreApply();
					String reError = null;
					RuleTrans ru = new RuleTrans();
					preApply.setBatchNo(batchNo);
					PreApplyBo preApplyBo = (PreApplyBo)SourceTemplate.getSpringContextInstance().getBean("preApplyBo");
					List<PreApply> preApplyList = preApplyBo.findListByBatNo(preApply);
					for(PreApply preA:preApplyList){
						//判断筛查是否通过，通过则进行自动审批
						if("01".equals(preA.getChkRes())){
							ReturnObj re = appAuto(preA);
							reError = ru.resultError(re.getResponse().getRuleFact().get(0));
							if(reError != null){
								preApply.setAppId(preA.getAppId());
								preApply.setIfFlag(re.getResponse().getResultId());
								preApply.setChkRes("16");
								preApply.setChkDesc(reError);
								preApply.setAppSts("03");
								preApplyBo.updateZDSP(preApply);
								logger.info("P任务处理完成,预审批自动审批否决,否决原因:["+reError+"],批次号batch_no="+batchNo+",申请号app_id="+preA.getAppId());
							}else{
								preApply.setAppId(preA.getAppId());
								preApply.setIfFlag(re.getResponse().getResultId());
								preApply.setChkRes(preA.getChkRes());
								preApply.setChkDesc(preA.getChkDesc());
								preApply.setAppSts("02");
								preApplyBo.updateZDSP(preApply);
								logger.info("P任务处理完成,预审批自动审批通过,批次号batch_no="+batchNo+",申请号app_id="+preA.getAppId());
							}
						}else{
							preA.setAppSts("03");
							preApplyBo.updateZDSP(preA);
							logger.info("P任务处理失败,预审批筛查未通过,批次号batch_no="+batchNo);
						}
					}
		    	}else{
		    		logger.info("P任务处理失败,预审批筛查异常,批次号batch_no="+batchNo);
		    	}
		    }
//			logger.info(message);
		} catch (Exception e) {
			SysExceptionBo sysExceptionBo = (SysExceptionBo) SourceTemplate.getSpringContextInstance().getBean("sysExceptionBo");
			sysExceptionBo.insertNewWorkExceptionLog(e, batchNo);
			e.printStackTrace();
		}
	}
	
	/***
	 * @作者 wangtao
	 * @日期 Jul 12, 2016
	 * @方法说明：调用规则引擎接口进行自动审批[不同的产品规则编号不同]
	 * @返回参数 02通过03否决
	 */
	public ReturnObj appAuto(PreApply ln){
		//连接规则引擎
//		RulesServiceService factory = new RulesServiceService();
//		RulesService rs = factory.getRulesServicePort();
		RulesService rs = (RulesService) SourceTemplate.getSpringContextInstance().getBean("RulesService");
		//拼接传入数据
		RequestObj requestObj = new RequestObj();
		Request request = new Request();
		RuleFact ruleFact = new RuleFact();
		
		ruleFact.setPrdtNo(ln.getPrdtNo());
		ruleFact.setAge(birthToAge(ln.getBirthDay()));
		ruleFact.setEdu(ln.getEdu());
		ruleFact.setCifType(ln.getCifType());
		if(ln.getWorkSts()==null||"".equals(ln.getWorkSts())){
			ruleFact.setIfWork("99");
		}else{
			ruleFact.setIfWork(ln.getWorkSts());
		}
		ruleFact.setIfRoom(ln.getIfRoom());
		ruleFact.setIfBlack(ln.getChkRes());
		ruleFact.setIfPhone("");
		if(!(ln.getResTel()==null||"".equals(ln.getResTel()))){
			ruleFact.setIfPhone(ln.getResTel());
		}
		if(!(ln.getPhoneNo()==null||"".equals(ln.getPhoneNo()))){
			ruleFact.setIfPhone(ln.getPhoneNo());
		}
		ruleFact.setAgeRes("");
		ruleFact.setEduRes("");
		ruleFact.setCifTypeRes("");
		ruleFact.setIfWorkRes("");
		ruleFact.setIfBendiRes("");
		ruleFact.setIfRoomRes("");
		ruleFact.setIfBlackRes("");
		ruleFact.setIfPhoneRes("");
		ruleFact.setAppRes("");
		
		ruleFact.setSex(ln.getSex());
		ruleFact.setWorkYear(dateToYear(ln.getWorkYear())+"");
		ruleFact.setMarriage(ln.getMarriage());
		ruleFact.setChildren(ln.getChildren());
		ruleFact.setIncome(ln.getIncome());
		ruleFact.setIfBendi("");
		if((ln.getAppArea().equals(ln.getCifArea()))){
			ruleFact.setIfBendi("01");
		}
		ruleFact.setIfCar(ln.getIfCar());
		ruleFact.setIfCard(ln.getIfCard());
		ruleFact.setCardAmt(ln.getCardAmt());
		ruleFact.setIfCarcred(ln.getIfCarcred());
		ruleFact.setIfMort(ln.getIfMort());
		
		List<RuleFact> list = new ArrayList<RuleFact>();
		list.add(0, ruleFact);
//		request.setRuleName("appAutoWeiXin");
		request.setRuleName("appAuto"+ln.getPrdtNo());
		request.setRuleFact(list);
		requestObj.setUser("1001");
		requestObj.setPassword("1");
		requestObj.setRequest(request);
		//输入字符串
//		String str1= JSON.toJSONString(requestObj);
		//调用规则引擎得到返回的字符串
		String str= rs.executeRule(JSON.toJSONString(requestObj));
		//将返回的字符串转为Object并取出审批结果
		return (ReturnObj) JSON.parseObject(str, ReturnObj.class);
	}
	/***
	 * @作者 wangtao
	 * @日期 Jul 12, 2016
	 * @方法说明：将生日转换为年龄
	 * @返回参数 年龄
	 */
	public int birthToAge(String birth){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateString = formatter.format(new Date());
        Date birthDate = null;
        Date currentTime = null;
        try {
        	birthDate = formatter.parse(birth);
        	currentTime = formatter.parse(dateString);
        } catch (Exception e) {
        }
        long day = (currentTime.getTime() - birthDate.getTime()) / (24 * 60 * 60 * 1000);
        int year=(int)day/365;
        return year;
	}
	/***
	 * @作者 wangtao
	 * @日期 Jul 12, 2016
	 * @方法说明：将生日转换为年龄
	 * @返回参数 年龄
	 */
	public int dateToYear(String da){
		if("".equals(da)||da==null){
			return 0;
		}else{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
	        String dateString = formatter.format(new Date());
	        int year = Integer.parseInt(dateString)-Integer.parseInt(da);
	        return year;
		}
		
	}
	
	// 复写父类的方法
	public String toString(){
		String ret = "";
		if( !(batchNo==null||"".equals(batchNo)) ){
			ret = "批次号："+ batchNo+"";
		}
		return ret;
	}
}
