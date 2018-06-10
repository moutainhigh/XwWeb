package app.creditapp.ln.worker;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import app.base.SourceTemplate;
import app.base.httpclient.entity.SendMessageEntity;
import app.base.httpclient.entity.SendMessageType;
import app.base.httpclient.work.SendMessageMain;
import app.creditapp.acc.loan.action.AcLoanBackLogAction;
import app.creditapp.acc.loan.bo.LoanBo;
import app.creditapp.aft.bo.AftRewPactBo;
import app.creditapp.aft.entity.AftRewPact;
import app.creditapp.aft.entity.aftMessage.PasSubTypeEntity;
import app.creditapp.corp.entity.CorpParm;
import app.creditapp.ln.bo.LnDueBo;
import app.creditapp.ln.entity.LnDue;
import app.creditapp.proj.bo.ProjMangBo;
import app.creditapp.proj.entity.ProjMang;
import app.creditapp.sys.bo.ParmRewBo;
import app.creditapp.sys.entity.ParmRew;
import app.oscache.CachecodeUtil;
import app.oscache.MBaseCache;
import app.util.syslog.bo.SysExceptionBo;

/**
 * @作者 DHCC-SONG
 * @日期 Jun 6, 2016
 * @描述 业务处理F节点 分账处理业务
 */
public class WorkFforAcc implements Runnable {
	String _grant_result = "02"; // 默认放款失败
	Logger logger = Logger.getLogger(WorkFforAcc.class);
	
	private LnDue lnDue;
	public WorkFforAcc( LnDue lnDue) {
		this.lnDue = lnDue;
	}

	public void run() {
		try {
		    if(lnDue==null){
		    	logger.error("任务处理失败！");
		    }else{
		    	// 调用存储过程进行分账
		    	logger.info("APPID:"+lnDue.getAppId()+" WORK F 处理开始");
				String _split_sts = WorkUtils.getInstance().proc_acc_fund(lnDue.getDueNo(), lnDue.getProjNo(), lnDue.getDueAmt(), lnDue.getMtrDate());// 分账成功标志 [02成功 03失败]
				if( "02".equals( _split_sts ) ) {  // 生成贷款主文件
					logger.info("F任务处理-分账成功-PKG_LN_DUE.PROC_ACC_SPLIT-[合同编号为PactId=" + lnDue.getPactId()+",合同号为"+lnDue.getPactNo()+"]");
					WorkUtils.getInstance().proc_up_stage(lnDue.getAppId(), "PART_STS", "02","分账成功");
					// 调用判断该机构是否定时放款 从缓存中取，仿照C节点
					String put_type = "";
					put_type = getPutType(lnDue.getBrNo());
					logger.info("******************put_type="+put_type+"");
					if("02".equals(put_type)){
						logger.info("******************定时放款");
						// 如果是定时放款 则调用阿斌写的定时任务组件 生成定时放款任务，更新LN_DUE表中的放款状态为01待放款，到时直接调用放款函数
//						LnDueBo lnDueBo = (LnDueBo) SourceTemplate.getSpringContextInstance().getBean("lnDueBo");
//						List<LnDue> lnDueList = lnDueBo.findList();
					}else{
//						logger.info("******************实时放款");
						// 如果是实时放款，则直接调用刘杰写的放款函数进行生成贷款主文件并放款
						LoanBo loanBo = (LoanBo) SourceTemplate.getSpringContextInstance().getBean("loanBo");
//						long b = System.currentTimeMillis();
						_grant_result = loanBo.grantLoan(lnDue);
//						long e = System.currentTimeMillis();
						if("01".equals(_grant_result)){
							// 放款成功则更新LN_DUE表中的放款状态为02正常
							lnDueUpdate(lnDue.getAppId(),lnDue.getDueNo(),"02");
//							WorkUtils.getInstance().proc_up_stage(lnDue.getAppId(), "PAY_STS", "02", "放款成功");
							logger.info("贷款放款处理成功![申请号AppId=" + lnDue.getAppId()+",合同编号PactNo="+lnDue.getPactNo()+"]");
						}else{
							logger.info("贷款放款处理失败![申请号AppId=" + lnDue.getAppId()+",合同编号PactNo="+lnDue.getPactNo()+"]");
						}
					}
				}else{
					//分账余额不足
					if("04".equals(_split_sts)){
						WorkUtils.getInstance().proc_up_stage(lnDue.getAppId(), "PART_STS", "03","分账失败,分账余额不足");
					}else{
						WorkUtils.getInstance().proc_up_stage(lnDue.getAppId(), "PART_STS", "03","分账失败");
					}
//					sendMes(lnDue);
					//将分账失败信息插入AftRewPact表
					insertToAftRewPact(lnDue);
					logger.info("F任务处理-分账失败-PKG_LN_DUE.PROC_ACC_FUND-[申请号AppId=" + lnDue.getAppId()+",合同号为"+lnDue.getPactNo()+"]");
				}
		    }
//			logger.info(message);
		} catch (Exception e) {
			SysExceptionBo sysExceptionBo = (SysExceptionBo) SourceTemplate.getSpringContextInstance().getBean("sysExceptionBo");
			sysExceptionBo.insertNewWorkExceptionLog(e, lnDue.getAppId());
			e.printStackTrace();
		}
	}
	
	public String getPutType(String brNo)
    {   
		String putType = "";
		List<CorpParm> corpParmList = (List<CorpParm>) MBaseCache.getCache().getBeanCache(CachecodeUtil.CORP_PARM_STR, CachecodeUtil.CORP_PARM);
		for(CorpParm corpParm : corpParmList){
			if(brNo.equals(corpParm.getBrNo())){
				putType = corpParm.getPutType();
				break;
			}
		}
		return putType;
    }
	public void insertToAftRewPact(LnDue lnDue){   
		AftRewPact aftRewPact = new AftRewPact();
		//手动输入预警内容
		aftRewPact.setRewCont("分账失败");
		//从借据表里获取
		aftRewPact.setBal(lnDue.getBal());
		aftRewPact.setBegDate(lnDue.getBegDate());
		aftRewPact.setBrNo(lnDue.getBrNo());
		aftRewPact.setCifName(lnDue.getCifName());
		aftRewPact.setCifNo(lnDue.getCifNo());
		aftRewPact.setEndDate(lnDue.getEndDate());
		aftRewPact.setPactAmt(lnDue.getDueAmt());
		aftRewPact.setPactId(lnDue.getPactId());
		aftRewPact.setPactNo(lnDue.getPactNo());
		aftRewPact.setPrdtNo(lnDue.getPrdtNo());
		aftRewPact.setProjNo(lnDue.getProjNo());
		aftRewPact.setRelId(lnDue.getAppId());
		//查询预警配置信息
		ParmRew parmRew = new ParmRew();
		parmRew.setRewNo("100013");
		ParmRewBo parmRewBo = (ParmRewBo) SourceTemplate.getSpringContextInstance().getBean("parmRewBo");
		parmRew = parmRewBo.getById(parmRew);
		//从预警配置表里获取
		aftRewPact.setRewName(parmRew.getRewName());
		aftRewPact.setRewValue(parmRew.getRewValue());
		aftRewPact.setRewNo(parmRew.getRewNo());
		aftRewPact.setRewSts(parmRew.getRewSts());
		aftRewPact.setRewType(parmRew.getRewType());
		AftRewPactBo aftRewPactBo = (AftRewPactBo) SourceTemplate.getSpringContextInstance().getBean("aftRewPactBo");
		aftRewPactBo.insertForSplit(aftRewPact);
    }
	// 放款成功则更新LN_DUE表中的放款状态为02正常
	public int lnDueUpdate(String appId,String dueNo,String dueSts){
		LnDue lnDue = new LnDue();
		lnDue.setAppId(appId);
		lnDue.setDueNo(dueNo);
		lnDue.setDueSts(dueSts);
		//使用Bo对cifEval表进行更新
		LnDueBo lnDueBo = (LnDueBo) SourceTemplate.getSpringContextInstance().getBean("lnDueBo");
		int result = lnDueBo.dueStsUpdate(lnDue);
		return result;
	}
	
	public void sendMes(LnDue lnDue){
		String a=lnDue.getProjNo();
//		List<String> list = new ArrayList<String>();
//    	Connection conn=DBUtil.getConnection();
//    	Statement stmt=null;
//    	ResultSet rs = null;
//    	String sql ="select login_id from proj_mang where proj_no="+a;
//    	stmt = conn.createStatement();
//    	rs = stmt.executeQuery(sql);
//    	while(rs.next()){
//      list.add(rs.getString(1));					
//    	}
//    	conn.close();
//    	rs.close();
//    	stmt.close();
		ProjMang projMang=new ProjMang();
		projMang.setProjNo(a);
		
		ProjMangBo projMangBo = (ProjMangBo) SourceTemplate.getSpringContextInstance().getBean("projMangBo");
		List<ProjMang> projMangList = null;
		
		projMangList=projMangBo.getByIdLoginId(projMang);
		if(projMangList.equals(null)){
			
		}
		else{
		List<String> list = new ArrayList<String>();
		for(int i=0;i<projMangList.size();i++){
		list.add( projMangList.get(i).getLoginId());
		}
//    	String user[] = (String[])list.toArray(new String[list.size()]);
		String user[] = (String[])list.toArray(new String[list.size()]);
		String b=lnDue.getProjName();
    	String title=b;
        String contet="分账余额不足";
    	SendMessageEntity sendMessageEntity=new SendMessageEntity();
    	sendMessageEntity.setTitle(title);
    	sendMessageEntity.setContet(contet);
    	sendMessageEntity.setReciveUserNos(user);
    	sendMessageEntity.setSendType(SendMessageType.MESSAGE);
    	sendMessageEntity.setPasSubTypeEntity(PasSubTypeEntity.RewFundMessage);
    	try {
			SendMessageMain.sendMessage(sendMessageEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		}
	}
	
	// 复写父类的方法
	public String toString(){
		String ret = "";
		if( lnDue != null ){
			ret = "业务ID："+ lnDue.getAppId()+",合同编号："+lnDue.getPactNo();
		}
		return ret;
	}
}
