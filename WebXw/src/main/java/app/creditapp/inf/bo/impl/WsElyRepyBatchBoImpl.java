package  app.creditapp.inf.bo.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.inf.bo.WsElyRepyBatchBo;
import app.creditapp.inf.dao.WsElyRepyBatchDao;
import app.creditapp.inf.dao.WsElyRepyDao;
import app.creditapp.inf.dao.WsRepyPlanDao;
import app.creditapp.inf.entity.WsElyRepy;
import app.creditapp.inf.entity.WsElyRepyBatch;
import app.creditapp.inf.entity.WsIn2803;
import app.creditapp.inf.entity.WsIn2803_1;
import app.creditapp.inf.entity.WsIn2804;
import app.creditapp.inf.entity.WsIn2804_1;
import app.creditapp.inf.entity.WsIn2804_2;
import app.creditapp.inf.entity.WsRepyPlan;
import app.util.ruleFiter.entity.ValidateLog;
import app.util.ruleFiter.fiter.FiterEngineInterface;
import app.util.toolkit.Ipage;
import app.util.IbatisUtils;
/**
* Title: WsElyRepyBatchBoImplImpl.java
* Description:
**/
public class WsElyRepyBatchBoImpl extends BaseService implements WsElyRepyBatchBo {

	private WsElyRepyBatchDao wsElyRepyBatchDao;
	private WsElyRepyDao wsElyRepyDao;
	private FiterEngineInterface fiterEngineInterface;
	private WsRepyPlanDao wsRepyPlanDao;

	//A类提前还款批量申请[2803]
	public Map<String,String> validateAndSave(WsIn2803 wsIn2803) throws ServiceException {
		
		Map<String,String> errorMap = new HashMap<String,String>();// 错误信息集合
		boolean _success_flag1 = true; //校验成功标志
		List<WsIn2803_1> wsIn2803_1list =  wsIn2803.getList(); //  提前还款申请明细信息
		List<WsElyRepy> wsElyRepylist = new ArrayList<WsElyRepy>();
		try {
			//首先进行对wsIn2803,进行校验并输出校验结果
			ValidateLog _vl_wsIn2803 = fiterEngineInterface.createValidateLog("wsIn2803Id", wsIn2803, true);	
			if(!_vl_wsIn2803.isSuccess()){
				errorMap.put("subject", _vl_wsIn2803.getErrorMessage());
				_success_flag1 = false;
			}else{
				for(int i = 0; i<wsIn2803_1list.size(); i++){
					//对WsIn2803_1进行校验
					ValidateLog _vl_wsIn2803_1 = fiterEngineInterface.createValidateLog("wsIn2803_1Id", wsIn2803_1list.get(i), true);
					if(!_vl_wsIn2803_1.isSuccess()){
						errorMap.put(wsIn2803_1list.get(i).getPactNo(), _vl_wsIn2803_1.getErrorMessage());
						wsIn2803_1list.remove(wsIn2803_1list.get(i)); // 验证不通过的 移除
						i--;
					} else {
						WsElyRepy wsElyRepy = new WsElyRepy();
						wsElyRepy.setPactNo(wsIn2803_1list.get(i).getPactNo());
						//删除当日添加的相同合同号的提前还款申请
						wsElyRepyDao.delByPactNo(wsElyRepy);	
						wsElyRepy.setBatchNo(wsIn2803.getBatNo());
						wsElyRepy.setBrNo(wsIn2803.getBrNo());
						wsElyRepy.setPayTotal(wsIn2803_1list.get(i).getPayTotal());
						wsElyRepylist.add(wsElyRepy);
					}				
				}
			}	
		}catch(Exception e){
			e.printStackTrace();
		}
		//只有通过校验的列表才进行数据库的插入操作
		if(wsIn2803_1list.size()!=0 && _success_flag1==true){
			wsElyRepyDao.insertForList(wsElyRepylist);//批量插入详细信息
			// 插入主表信息
			WsElyRepyBatch wsElyRepyBatch = new WsElyRepyBatch();
			wsElyRepyBatch.setBatchNo(wsIn2803.getBatNo());
			wsElyRepyBatch.setDataCnt(wsIn2803_1list.size());
			wsElyRepyBatch.setBrNo(wsIn2803.getBrNo());
			wsElyRepyBatchDao.insert(wsElyRepyBatch);
			
			wsIn2803.setDataCnt(wsIn2803_1list.size());
		} else {
			wsIn2803.setDataCnt(0);	
		}
		return errorMap;  // 返回错误信息
	}
	//B类提前还款批量申请[2804]
	public Map<String,String> validateAndSave(WsIn2804 wsIn2804) throws ServiceException {
		
		Map<String,String> errorMap = new HashMap<String,String>();// 错误信息集合
		boolean _success_flag1 = true; //校验成功标志
		List<WsIn2804_1> wsIn2804_1list =  wsIn2804.getList(); //  提前还款申请明细信息
		List<WsIn2804_2> wsIn2804_2list =  wsIn2804.getListPlan(); //  还款计划明细信息
		List<WsElyRepy> wsElyRepylist = new ArrayList<WsElyRepy>();
		WsElyRepyBatch wsElyRepyBatch = new WsElyRepyBatch();
		try {
			//首先进行对wsIn2804,进行校验并输出校验结果
			ValidateLog _vl_wsIn2804 = fiterEngineInterface.createValidateLog("wsIn2804Id", wsIn2804, true);	
			if(!_vl_wsIn2804.isSuccess()){
				errorMap.put("subject", _vl_wsIn2804.getErrorMessage());
				_success_flag1 = false;
			}else{
				for(int i = 0; i<wsIn2804_1list.size(); i++){
					//对WsIn2804_1进行校验
					ValidateLog _vl_wsIn2804_1 = fiterEngineInterface.createValidateLog("wsIn2804_1Id", wsIn2804_1list.get(i), true);
					if(!_vl_wsIn2804_1.isSuccess()){
						errorMap.put(wsIn2804_1list.get(i).getPactNo(), _vl_wsIn2804_1.getErrorMessage());
						wsIn2804_1list.remove(wsIn2804_1list.get(i)); // 验证不通过的 移除
						i--;
					} else {
						WsElyRepy wsElyRepy = new WsElyRepy();
						wsElyRepy.setPactNo(wsIn2804_1list.get(i).getPactNo());
						//删除当日添加的相同合同号的提前还款申请
						wsElyRepyDao.delByPactNo(wsElyRepy);	
						wsElyRepy.setBrNo(wsIn2804.getBrNo());
						wsElyRepy.setBatchNo(wsIn2804.getBatNo());
						wsElyRepy.setPayTotal(wsIn2804_1list.get(i).getPayTotal());	
						wsElyRepy.setPayAmt(wsIn2804_1list.get(i).getPayAmt());
						wsElyRepy.setPayInte(wsIn2804_1list.get(i).getPayInte());
						wsElyRepy.setPayOver(wsIn2804_1list.get(i).getPayOver());
						wsElyRepy.setFeeTotal(wsIn2804_1list.get(i).getFeeTotal());
						wsElyRepy.setFeeDam(wsIn2804_1list.get(i).getFeeDam());
						
						wsElyRepylist.add(wsElyRepy);
					}
				}
				for(int i = 0; i<wsIn2804_2list.size(); i++){
					//对WsIn2804_2进行校验
					ValidateLog _vl_wsIn2804_2 = fiterEngineInterface.createValidateLog("wsIn2804_2Id", wsIn2804_2list.get(i), true);
					if(!_vl_wsIn2804_2.isSuccess()){
						errorMap.put(wsIn2804_2list.get(i).getPactNo(), _vl_wsIn2804_2.getErrorMessage());
						wsIn2804_2list.remove(wsIn2804_2list.get(i)); // 验证不通过的 移除
						i--;
					} else {
						WsRepyPlan wsRepyPlan = new WsRepyPlan();
						wsRepyPlan.setPactNo(wsIn2804_2list.get(i).getPactNo());
						//删除当日添加的相同合同号的还款计划变更申请
						wsRepyPlanDao.delByPactNo(wsRepyPlan);		
						wsIn2804_2list.get(i).setBatchNo(wsIn2804.getBatNo());
						wsIn2804_2list.get(i).setBrNo(wsIn2804.getBrNo());
					}
				}
				wsIn2804.setDataCnt(wsElyRepylist.size());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		//只有通过校验的列表才进行数据库的插入操作
		if(wsIn2804_1list.size()!=0 && _success_flag1==true){	
			wsRepyPlanDao.insertlist(wsIn2804_2list);//批量插入还款计划详细信息
			wsElyRepyDao.insertForList(wsElyRepylist);//批量插入详细信息
			wsElyRepyBatch.setDataCnt(wsIn2804_1list.size());
			wsElyRepyBatch.setBatchNo(wsIn2804.getBatNo());
			wsElyRepyBatch.setBrNo(wsIn2804.getBrNo());
			wsElyRepyBatchDao.insert(wsElyRepyBatch);      // 插入主表信息
		} else {
			wsIn2804.setDataCnt(0);	
		}
		return errorMap;  // 返回错误信息
	}
	public int getCount(WsElyRepyBatch wsElyRepyBatch) throws ServiceException{
		int count = wsElyRepyBatchDao.getCnt(wsElyRepyBatch);
		return count;		
	}
	public void del(WsElyRepyBatch wsElyRepyBatch) throws ServiceException {
		try {
			wsElyRepyBatchDao.del(wsElyRepyBatch);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, WsElyRepyBatch wsElyRepyBatch)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) wsElyRepyBatchDao
					.getCount(wsElyRepyBatch) });// 初始化分页类
						HashMap<String, Object> map = (HashMap<String, Object>) IbatisUtils
			.getEntityPropertyMap(ipg, wsElyRepyBatch);
			map.put("startNum", ipg.getStartRow());
			map.put("endNum", ipg.getEndNum());
			ipg.setResult(wsElyRepyBatchDao.findByPage(map));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public WsElyRepyBatch getById(WsElyRepyBatch wsElyRepyBatch) throws ServiceException {
		try {
			wsElyRepyBatch = wsElyRepyBatchDao.getById(wsElyRepyBatch);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return wsElyRepyBatch;
	}

	public void insert(WsElyRepyBatch wsElyRepyBatch) throws ServiceException {
		try {
			wsElyRepyBatchDao.insert(wsElyRepyBatch);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(WsElyRepyBatch wsElyRepyBatch) throws ServiceException {
		try {
			wsElyRepyBatchDao.update(wsElyRepyBatch);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public WsElyRepyBatchDao getWsElyRepyBatchDao() {
		return wsElyRepyBatchDao;
	}

	public void setWsElyRepyBatchDao(WsElyRepyBatchDao wsElyRepyBatchDao) {
		this.wsElyRepyBatchDao = wsElyRepyBatchDao;
	}

	public WsElyRepyDao getWsElyRepyDao() {
		return wsElyRepyDao;
	}

	public void setWsElyRepyDao(WsElyRepyDao wsElyRepyDao) {
		this.wsElyRepyDao = wsElyRepyDao;
	}

	public FiterEngineInterface getFiterEngineInterface() {
		return fiterEngineInterface;
	}

	public void setFiterEngineInterface(FiterEngineInterface fiterEngineInterface) {
		this.fiterEngineInterface = fiterEngineInterface;
	}
	public WsRepyPlanDao getWsRepyPlanDao() {
		return wsRepyPlanDao;
	}
	public void setWsRepyPlanDao(WsRepyPlanDao wsRepyPlanDao) {
		this.wsRepyPlanDao = wsRepyPlanDao;
	}
}