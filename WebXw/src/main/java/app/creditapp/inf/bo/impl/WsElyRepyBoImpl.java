package  app.creditapp.inf.bo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.base.BaseService;
import app.base.CreateKey;
import app.base.ServiceException;
import app.creditapp.acc.loan.dao.AcLnMstDao;
import app.creditapp.acc.loan.entity.AcLnMst;
import app.creditapp.aft.bo.AftAdvpayBo;
import app.creditapp.aft.entity.AftAdvpay;
import app.creditapp.inf.bo.WsElyRepyBo;
import app.creditapp.inf.dao.WsElyRepyBatchDao;
import app.creditapp.inf.dao.WsElyRepyDao;
import app.creditapp.inf.dao.WsRepyPlanDao;
import app.creditapp.inf.entity.ResponseParm;
import app.creditapp.inf.entity.WsElyRepy;
import app.creditapp.inf.entity.WsElyRepyBatch;
import app.creditapp.inf.entity.WsIn2801;
import app.creditapp.inf.entity.WsIn2802;
import app.creditapp.inf.entity.WsIn2802_1;
import app.creditapp.inf.entity.WsIn2805;
import app.creditapp.inf.entity.WsIn2806;
import app.creditapp.inf.entity.WsOut2806;
import app.creditapp.inf.entity.WsRepyPlan;
import app.creditapp.pact.dao.LnPactDao;
import app.util.ruleFiter.entity.ValidateLog;
import app.util.ruleFiter.fiter.FiterEngineInterface;
import app.util.toolkit.Ipage;
/**
* Title: WsElyRepyBoImplImpl.java
* Description:
* @author:su
* 
**/
public class WsElyRepyBoImpl extends BaseService implements WsElyRepyBo {

	private LnPactDao lnPactDao;
	private WsElyRepyDao wsElyRepyDao;
	private WsElyRepyBatchDao wsElyRepyBatchDao;
	private FiterEngineInterface fiterEngineInterface;
	private WsRepyPlanDao wsRepyPlanDao;
	private AftAdvpayBo aftAdvpayBo;
	private AcLnMstDao acLnMstDao;
	
	//检验并进行存储A类提前还款单笔申请
	public Map<String,String> validateAndSave(WsIn2801 wsIn2801) throws ServiceException {

		Map<String,String> errorMap = new HashMap<String,String>();// 错误信息集合
		String batNo = CreateKey.getBatchNo();//随机生成批次号
		try {		
			//首先进行对wsIn2801,进行校验并输出校验结果
			ValidateLog _vl_wsIn2801 = fiterEngineInterface.createValidateLog("wsIn2801Id", wsIn2801, true);	
			if(!_vl_wsIn2801.isSuccess()){
				errorMap.put("", _vl_wsIn2801.getErrorMessage());
			}else{
				//插入提前还款申请主表
				WsElyRepyBatch wsElyRepyBatch = new WsElyRepyBatch();
				wsElyRepyBatch.setBrNo(wsIn2801.getBrNo());
				wsElyRepyBatch.setBatchNo(batNo);
				wsElyRepyBatch.setDataCnt(1);
				wsElyRepyBatchDao.insert(wsElyRepyBatch);
				//插入提前还款申请明细表
				WsElyRepy wsElyRepy = new WsElyRepy();
				wsElyRepy.setPactNo(wsIn2801.getPactNo());
				wsElyRepyDao.delByPactNo(wsElyRepy);//删除当天同合同的扣款日变更申请
				wsElyRepy.setBrNo(wsIn2801.getBrNo());
				wsElyRepy.setBatchNo(batNo);
				wsElyRepy.setPayTotal(wsIn2801.getPayTotal());
				wsElyRepy.setOnlinOff(wsIn2801.getOnlinOff());
				wsElyRepy.setDebitType(wsIn2801.getDebitType());
				wsElyRepyDao.insert(wsElyRepy);
			}	
		}catch(Exception e){
			e.printStackTrace();
		}
		return errorMap;  // 返回错误信息
		}
	//检验并进行存储B类提前还款单笔申请
	public Map<String,String> validateAndSave(WsIn2802 wsIn2802) throws ServiceException {

		Map<String,String> errorMap = new HashMap<String,String>();// 错误信息集合
		String batNo = CreateKey.getBatchNo();//随机生成批次号
		WsElyRepyBatch wsElyRepyBatch = new WsElyRepyBatch();
		List<WsIn2802_1> wsIn2802_1list =  wsIn2802.getList(); //  提前还款申请明细信息
		boolean _success_flag1 = true; //校验成功标志
		try {		
			//首先进行对wsIn2802,进行校验并输出校验结果
			ValidateLog _vl_wsIn2802 = fiterEngineInterface.createValidateLog("wsIn2802Id", wsIn2802, true);	
			if(!_vl_wsIn2802.isSuccess()){
				errorMap.put("subject", _vl_wsIn2802.getErrorMessage());
			}else{
				for(int i = 0; i<wsIn2802_1list.size(); i++){
					//对WsIn2802_1进行校验
					ValidateLog _vl_wsIn2802_1 = fiterEngineInterface.createValidateLog("wsIn2802_1Id", wsIn2802_1list.get(i), true);
					if(!_vl_wsIn2802_1.isSuccess()){
						errorMap.put(wsIn2802_1list.get(i).getPactNo(), _vl_wsIn2802_1.getErrorMessage());
						wsIn2802_1list.remove(wsIn2802_1list.get(i)); // 验证不通过的 移除
						i--;
					} else {
						WsRepyPlan wsRepyPlan = new WsRepyPlan();
						wsRepyPlan.setPactNo(wsIn2802_1list.get(i).getPactNo());
						//删除当日添加的相同合同号的还款计划
						wsRepyPlanDao.delByPactNo(wsRepyPlan);	
						wsIn2802_1list.get(i).setBatchNo(batNo);
						wsIn2802_1list.get(i).setBrNo(wsIn2802.getBrNo());
					}				
				}
			}	
		}catch(Exception e){
			e.printStackTrace();
		}
		//只有通过校验的列表才进行数据库的插入操作
		if(wsIn2802_1list.size()!=0 && _success_flag1==true){	
			wsRepyPlanDao.insertList(wsIn2802_1list);//插入还款计划详细信息
			// 插入提前还款申请主表信息
			wsElyRepyBatch.setBatchNo(batNo);
			wsElyRepyBatch.setDataCnt(1);
			wsElyRepyBatch.setBrNo(wsIn2802.getBrNo());
			wsElyRepyBatchDao.insert(wsElyRepyBatch);   
			//插入提前还款申请明细表信息
			WsElyRepy wsElyRepy = new WsElyRepy();
			wsElyRepy.setPactNo(wsIn2802.getPactNo());
			wsElyRepyDao.delByPactNo(wsElyRepy);//删除该合同号当天插入的提前还款申请记录
			wsElyRepy.setBrNo(wsIn2802.getBrNo());
			wsElyRepy.setPayTotal(wsIn2802.getPayTotal());
			wsElyRepy.setPayAmt(wsIn2802.getPayAmt());
			wsElyRepy.setPayInte(wsIn2802.getPayInte());
			wsElyRepy.setPayOver(wsIn2802.getPayOver());
			wsElyRepy.setFeeTotal(wsIn2802.getFeeTotal());
			wsElyRepy.setFeeDam(wsIn2802.getFeeDam());
			wsElyRepy.setBatchNo(batNo);
			wsElyRepyDao.insert(wsElyRepy);
		}
		return errorMap;  // 返回错误信息
		}
	
	/**
	 * @作者 DHCC-ZKX
	 * @方法说明：接口ws2805调用rule-WsIn2805.xml校验规则
	 * @返回参数 响应参数实体ResponseParm
	 */
	public  ResponseParm validateWsIn(WsIn2805 wsIn2805) throws ServiceException{
		ResponseParm responseParm = new ResponseParm();
		responseParm.setRespCode("0000");
		ValidateLog _vl;		
		try {
			_vl = fiterEngineInterface.createValidateLog("wsIn2805Id", wsIn2805, true);
			if(!_vl.isSuccess()){ // 有问题
				responseParm.setRespCode("9004");
				responseParm.setRespDesc(_vl.getErrorMessage());
			}else{
				WsElyRepy wsIn2805forsearch = new WsElyRepy();
				String batchNo = wsIn2805.getBatNo();
				String pactNo = wsIn2805.getPactNo();
				String brNo = wsIn2805.getBrNo();
				wsIn2805forsearch.setBrNo(brNo);
				wsIn2805forsearch.setBatchNo(batchNo);
				int count = wsElyRepyDao.getCount(wsIn2805forsearch);
				//判断是否存在该批次号
				if(count == 0){
					responseParm.setRespCode("1004");
					responseParm.setRespDesc("查询记录不存在！");
				}else{
					wsIn2805forsearch.setBatchNo(batchNo);
					int countforbat = wsElyRepyDao.getCount(wsIn2805forsearch);
					if(countforbat == 0){
						responseParm.setRespCode("1004");
						responseParm.setRespDesc("批次号为： "+batchNo+"  的记录不存在！");
					}else{
						wsIn2805forsearch.setPactNo(pactNo);
						int countforPactNo = wsElyRepyDao.getCount(wsIn2805forsearch);
						if(countforPactNo == 0){
							responseParm.setRespCode("1001");
							responseParm.setRespDesc("合同号为：  "+ pactNo +"  的记录不存在！");
						}
						
					}
					
				}
			}
			if(wsIn2805.getPageNo() == 0 ){
				//当不设置显示第几页的时候，默认显示全部的内容
				wsIn2805.setPageNo(1);
				wsIn2805.setPageSize(9999);
			}else if(wsIn2805.getPageNo() != 0 && wsIn2805.getPageSize() == 0){
				//当设置显示第几页的时候，默认每页显示10条
				wsIn2805.setPageNo(1);
				wsIn2805.setPageSize(10);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParm; 
	}

	public int countforListWs(WsElyRepy wsElyRepy) throws ServiceException{
		int count = 0;
		try {
			 count = wsElyRepyDao.getCountforlist(wsElyRepy);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
		} 
	
	public void del(WsElyRepy wsElyRepy) throws ServiceException {
		try {
			wsElyRepyDao.del(wsElyRepy);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, WsElyRepy wsElyRepy)throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) wsElyRepyDao
					.getCount(wsElyRepy) });// 初始化分页类
			wsElyRepy.setStartNumAndEndNum (ipg);
			ipg.setResult(wsElyRepyDao.findByPage(wsElyRepy));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	
	public Ipage findByPageForList(Ipage ipg, WsElyRepy wsElyRepy)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) wsElyRepyDao
					.getCount(wsElyRepy) });// 初始化分页类
			wsElyRepy.setStartNumAndEndNum (ipg);
			ipg.setResult(wsElyRepyDao.findByPageForList(wsElyRepy));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public WsElyRepy getById(WsElyRepy wsElyRepy) throws ServiceException {
		try {
			wsElyRepy = wsElyRepyDao.getById(wsElyRepy);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return wsElyRepy;
	}

	/*
	 * 对新增数据进行校验
	 * (non-Javadoc)
	 * @see app.creditapp.inf.bo.WsElyRepyBo#insert(app.creditapp.inf.entity.WsElyRepy)
	 */
	public void insert(WsElyRepy wsElyRepy) throws ServiceException {
		wsElyRepy.setWsId(CreateKey.getWsId());   //接收数据id
		String batNo = CreateKey.getBatchNo();
		wsElyRepy.setBatchNo(batNo);//随机生成批次号
		
		Map<String,String> errorMap = valAndSave(wsElyRepy);
		if( errorMap.size() > 0 ) {
			throw new ServiceException("输入数据格式存在错误");
		} else {
			//提前还款业务逻辑入口
			aftAdvpayBo.insertAftAdvPay(batNo);
			
		}		
		
		
	}
	
	@Override
	public WsOut2806 calcu(WsIn2806 wsIn2806) throws ServiceException {
		ValidateLog _vl;
		AftAdvpay aftAdvaday = new AftAdvpay();
		WsOut2806 wsOut2806 = new WsOut2806();

		try {
			if(wsIn2806.getPayDate().equals("")||wsIn2806.getPayDate() == null){
				throw new ServiceException("还款日期为空，请重新填写！");
			}
			
			_vl = fiterEngineInterface.createValidateLog("wsIn2806Id", wsIn2806, true);	
			if(!_vl.isSuccess()){
				throw new ServiceException("数据校验失败，请重新填写");
			}
			aftAdvaday.setPayDate(wsIn2806.getPayDate());
			aftAdvaday.setBrNo(wsIn2806.getBrNo());
			aftAdvaday.setPactNo(wsIn2806.getPactNo());
			aftAdvaday.setRepType(wsIn2806.getPayType());
			aftAdvaday.setPayAmt(wsIn2806.getPayAmt());
			AcLnMst aclnmst1 = new AcLnMst();
			aclnmst1.setPactNo(wsIn2806.getPactNo());
			aclnmst1.setBrNo(wsIn2806.getBrNo());
			AcLnMst aclnmst = acLnMstDao.getByPactNo(aclnmst1);
			if(aclnmst == null){
				throw new ServiceException(wsIn2806.getPactNo()+"合同信息不存在！");
			}
			wsOut2806 = aftAdvpayBo.calcAdvpay(aftAdvaday);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		
		
		
		
		
		
		return wsOut2806;
	}
	public Map<String,String> valAndSave(WsElyRepy wsElyRepy)throws ServiceException{
		Map<String,String> errorMap = new HashMap<String,String>();// 错误信息集合
		try {
			//首先进行对wsElyRepy,进行校验并输出校验结果
			ValidateLog _vl_wsIn2801 = fiterEngineInterface.createValidateLog("wsIn2801Id", wsElyRepy, true);	
			if(!_vl_wsIn2801.isSuccess()){
				errorMap.put("", _vl_wsIn2801.getErrorMessage());
			}else{
				//插入提前还款申请主表
				WsElyRepyBatch wsElyRepyBatchTwo = new WsElyRepyBatch();
				wsElyRepyBatchTwo.setBrNo(wsElyRepy.getBrNo());
				wsElyRepyBatchTwo.setBatchNo(wsElyRepy.getBatchNo());
				wsElyRepyBatchTwo.setDataCnt(1);
				wsElyRepyBatchTwo.setTxTime(wsElyRepy.getTxDate());
				wsElyRepyBatchDao.insert(wsElyRepyBatchTwo);
				//插入提前还款申请明细表
				wsElyRepyDao.delByPactNo(wsElyRepy);//删除当天同合同的扣款日变更申请

				wsElyRepyDao.insert(wsElyRepy);
			}	
			
		}catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return errorMap;
	}

	
	public void update(WsElyRepy wsElyRepy) throws ServiceException {
		try {
			wsElyRepyDao.update(wsElyRepy);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public WsElyRepyDao getWsElyRepyDao() {
		return wsElyRepyDao;
	}

	public void setWsElyRepyDao(WsElyRepyDao wsElyRepyDao) {
		this.wsElyRepyDao = wsElyRepyDao;
	}

	public WsElyRepyBatchDao getWsElyRepyBatchDao() {
		return wsElyRepyBatchDao;
	}

	public void setWsElyRepyBatchDao(WsElyRepyBatchDao wsElyRepyBatchDao) {
		this.wsElyRepyBatchDao = wsElyRepyBatchDao;
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
	public LnPactDao getLnPactDao() {
		return lnPactDao;
	}
	public void setLnPactDao(LnPactDao lnPactDao) {
		this.lnPactDao = lnPactDao;
	}
	public AftAdvpayBo getAftAdvpayBo() {
		return aftAdvpayBo;
	}
	public void setAftAdvpayBo(AftAdvpayBo aftAdvpayBo) {
		this.aftAdvpayBo = aftAdvpayBo;
	}
	public AcLnMstDao getAcLnMstDao() {
		return acLnMstDao;
	}
	public void setAcLnMstDao(AcLnMstDao acLnMstDao) {
		this.acLnMstDao = acLnMstDao;
	}
}