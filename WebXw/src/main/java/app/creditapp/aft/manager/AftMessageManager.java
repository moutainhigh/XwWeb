package app.creditapp.aft.manager;

import app.creditapp.gage.bo.LnGageBo;
import app.creditapp.gage.entity.LnGage;
import app.creditapp.proj.bo.ProjBaseBo;
import app.creditapp.proj.entity.ProjBase;
import app.util.DateUtil;

public class AftMessageManager implements AftManagerManagerInterface{
	private ProjBaseBo projBaseBo;
	private LnGageBo lnGageBo;
	
	public boolean disposeMessage(ManagerEntity entity){
		try {
			switch (entity.getParmRewType()) {
			case Cash_Not_Suff_Funds:
				//资金现金账户余额不足
				changeProjSts(entity.getProjNo());
				return true;
			case Vuser_Not_Suff_Funds:
				//TODO  项目虚拟户余额不足预警  与拨款提醒
				break;
			case Cash_Due_Audit:
				//只有忽略；不需要操作按钮。已在代码中完成
				break;
			case Real_time_transaction_Rate:
				changeProjSts(entity.getProjNo());
				break;
			case Entrust_Collection:
				//只有忽略；不需要操作按钮。已在代码中完成
				break;
			case Suser_Not_Suff_Funds:
				changeProjSts(entity.getProjNo());
				return true;
			case Proj_compensatory_Rate:
				changeProjSts(entity.getProjNo());
				return true;
			case Proj_buy_back_Rate:
				changeProjSts(entity.getProjNo());
				return true;
			case Proj_Overdue_Rate:
				changeProjSts(entity.getProjNo());
				return true;
			case Payback_Datum_Not_Upload:
				//只有忽略；不需要操作按钮。已在代码中完成
				break;
			case Image_Datum_Not_Upload:
				//只有忽略；不需要操作按钮。已完成
				break;
			case Funds_Due_Settlement:
				//只有忽略；不需要操作按钮。已完成
				break;
			case Funds_Due_Exchange:
				//只有忽略；不需要操作按钮。已完成
				break;
			case Proj_Overdue_Payback:
				//只有忽略；不需要操作按钮。已完成
				break;
			case Guar_Release:
				LnGage lnGage = new LnGage();
				lnGage.setGageId(entity.getRelId());
				lnGage.setGageSts("03");
				lnGage.setGageOutdate(DateUtil.getSysGlobal().getSys_date());
				lnGageBo.updateSts(lnGage);
				break;
			case Pending_Approval:
				//TODO 待审批任务提醒 
				break;
			case Split_Bill_Fail:
				changeProjSts(entity.getProjNo());
				return true;
			default:
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private void changeProjSts(String projNo) throws Exception{
		ProjBase projBase = new ProjBase();
		projBase.setProjNo(projNo);
		projBase.setProjSts("03");
		projBaseBo.updateSts(projBase);	
	}

	public void setProjBaseBo(ProjBaseBo projBaseBo) {
		this.projBaseBo = projBaseBo;
	}

	public void setLnGageBo(LnGageBo lnGageBo) {
		this.lnGageBo = lnGageBo;
	}
}	
