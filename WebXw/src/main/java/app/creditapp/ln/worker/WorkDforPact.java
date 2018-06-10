package app.creditapp.ln.worker;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import app.base.SourceTemplate;
import app.creditapp.ln.entity.LnApplyMid;
import app.creditapp.pact.bo.LnPactBo;
import app.creditapp.pact.entity.LnPact;
import app.creditapp.redis.util.RedisUtil;
import app.creditapp.wkf.entity.WkfParm;
import app.util.syslog.bo.SysExceptionBo;

import com.alibaba.fastjson.JSON;

/**
 * @作者 DHCC-SONG
 * @日期 Jun 6, 2016
 * @描述 业务处理D节点 生成合同
 */
public class WorkDforPact implements Runnable {
	Logger logger = Logger.getLogger(WorkDforPact.class);
	private LnApplyMid lnApplyMid;
	
	public WorkDforPact(LnApplyMid lnApplyMid) {
		this.lnApplyMid = lnApplyMid;
	}

	public void run() {
		try {
		    if( lnApplyMid==null ){
		    	logger.error("D任务处理失败,接收到数据为空！");
		    }else{
		    	// 直接调用生成合同的存储过程
		    	logger.info("APPID:"+lnApplyMid.getAppId()+" WORK D 处理开始");
				String _topact_sts = WorkUtils.getInstance().proc_apply_topact(lnApplyMid.getAppId(), lnApplyMid.getApprType());// 生成合同成功标志 1成功 0失败
				String _appr_type = lnApplyMid.getApprType(); // 审批类型[01自动02人工]
				if( "1".equals(_topact_sts) ){ // 生成合同成功
					LnPactBo lnPactBo = (LnPactBo) SourceTemplate.getSpringContextInstance().getBean("lnPactBo");
					LnPact lnPact = new LnPact();
					lnPact.setAppId(lnApplyMid.getAppId());
					lnPact = lnPactBo.getByAppId(lnPact);
					if( "01".equals(_appr_type) ){ // 自动审批
						// push入5号消息队列
				    	Jedis jedis = RedisUtil.getJedis();
				  		jedis.lpush(RedisUtil.QUEUE5, JSON.toJSONString(lnPact));
				  		jedis.close();
				  		logger.info("D任务处理成功-自动审批-[申请号为appId=" + lnApplyMid.getAppId()+",合同号为"+lnApplyMid.getPactNo()+"]！");
					} else {  // 人工审批
						// 自动提交合同进入工作流引擎  
						//组合流程变量，然后启动流程
						WkfParm parm = new WkfParm();
						parm.setApp_no(lnPact.getPactId());
						parm.setApp_type("01");//贷款审批
						parm.setBr_no(lnPact.getBrNo());
//						parm.setBr_lev(User.getFicode(this.getHttpRequest()));
						parm.setBr_lev("1");
						StringBuilder wfAppValue = new StringBuilder();
						wfAppValue.append("客户号:"+lnPact.getCifNo());
						wfAppValue.append(",客户名称:"+lnPact.getCifName());
						parm.setWf_app_value(wfAppValue.toString());//业务串
//						long b = System.currentTimeMillis();
						String nextDesc =lnPactBo.doSubmitUpdate(parm,lnPact,"SYSTEM", lnPact.getBrNo());
//						long e = System.currentTimeMillis();
						WorkUtils.getInstance().proc_up_stage(lnApplyMid.getAppId(), "RGAPP_STS", "01", "人工审批待审批");
						logger.info("D任务处理成功-人工审批-[APPId=" + lnPact.getAppId()+",batchNo=" + lnPact.getBatchNo()+",合同编号为PactId=" + lnPact.getPactId()+",合同号为PactNo="+lnPact.getPactNo()+"]！");
					}
				} else { // 生成合同失败
					logger.info("D任务处理失败-生成合同失败-PKG_LN_PACT.PROC_APPLY_TOPACT-[申请ID为AppId=" + lnApplyMid.getAppId()+"]");
			    }
		    }
		} catch (Exception e) {
			SysExceptionBo sysExceptionBo = (SysExceptionBo) SourceTemplate.getSpringContextInstance().getBean("sysExceptionBo");
			sysExceptionBo.insertNewWorkExceptionLog(e, lnApplyMid.getAppId());
			e.printStackTrace();
		}
	}
	
	// 复写父类的方法
	public String toString(){
		String ret = "";
		if( lnApplyMid != null ){
			ret = "业务ID："+ lnApplyMid.getAppId()+",合同编号："+lnApplyMid.getPactNo();
		}
		return ret;
	}
}
