package  app.creditapp.ln.bo.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.base.BaseService;
import app.base.CreateKey;
import app.base.ServiceException;
import app.creditapp.inf.entity.ResponseParm;
import app.creditapp.inf.entity.WsIn2001;
import app.creditapp.inf.entity.WsIn2001_1;
import app.creditapp.inf.entity.WsIn2001_1_1;
import app.creditapp.inf.entity.WsIn2004;
import app.creditapp.inf.entity.WsIn2004_1;
import app.creditapp.inf.entity.WsIn2005;
import app.creditapp.inf.entity.WsOut2004;
import app.creditapp.ln.bo.PreBatchBo;
import app.creditapp.ln.dao.PreApplyDao;
import app.creditapp.ln.dao.PreBatchDao;
import app.creditapp.ln.entity.PreBatch;
import app.util.DateUtil;
import app.util.IbatisUtils;
import app.util.ruleFiter.entity.ValidateLog;
import app.util.ruleFiter.fiter.FiterEngineInterface;
import app.util.toolkit.Ipage;
/**
* Title: PreBatchBoImplImpl.java
* Description:
**/
public class PreBatchBoImpl extends BaseService implements PreBatchBo {

	private PreBatchDao preBatchDao;
	private PreApplyDao preApplyDao;  // 明细信息处理持久层对象
	private FiterEngineInterface filterEngineInterface;
	
	public void del(PreBatch preBatch) throws ServiceException {
		try {
			preBatchDao.del(preBatch);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, PreBatch preBatch)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) preBatchDao
					.getCount(preBatch) });// 初始化分页类
			HashMap<String, Object> map = (HashMap<String, Object>) IbatisUtils
					.getEntityPropertyMap(ipg, preBatch);
			map.put("startNum", ipg.getStartRow());
			map.put("endNum", ipg.getEndNum());
			ipg.setResult(preBatchDao.findByPage(map));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public PreBatch getById(PreBatch preBatch) throws ServiceException {
		try {
			preBatch = preBatchDao.getById(preBatch);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return preBatch;
	}

	public void insert(PreBatch preBatch) throws ServiceException {
		try {
			preBatchDao.insert(preBatch);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(PreBatch preBatch) throws ServiceException {
		try {
			preBatchDao.update(preBatch);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void updateNum(PreBatch preBatch) throws ServiceException {
		try {
			preBatchDao.updateNum(preBatch);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Override
	public PreBatch getByPreApply(PreBatch preBatch) throws ServiceException {
		try {
			preBatch = preBatchDao.getByPreApply(preBatch);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return preBatch;
	}

	

	//接口ws2004数据校验，反馈
	public ResponseParm validateforPreApplyInsertWs(WsIn2004 wsIn2004) throws ServiceException {
		ResponseParm responseParm = new ResponseParm();
		// 没有问题，返回0000，同时，校验通过
		wsIn2004.setAppId(CreateKey.getPreAppId());
		wsIn2004.setBatchNo(CreateKey.getPreBatchNo());
		responseParm.setRespCode("0000");
		ValidateLog _vlwsin2004, _vlwsin2004_1;
		try {
			_vlwsin2004 = filterEngineInterface.createValidateLog("wsIn2004Id", wsIn2004, true);
			if (!_vlwsin2004.isSuccess()) { // 有问题
				responseParm.setRespCode("9004");
				responseParm.setRespDesc("接口 2004  " + _vlwsin2004.getErrorMessage());
			} else {
				if (wsIn2004.getListGage() != null && wsIn2004.getListGage().size()>0) {
					for (WsIn2004_1 wsIn2004_1 : wsIn2004.getListGage()) {
						wsIn2004_1.setAppId(wsIn2004.getAppId());
						_vlwsin2004_1 = filterEngineInterface.createValidateLog("wsIn2004_1Id", wsIn2004_1, true);
						if (!_vlwsin2004_1.isSuccess()) {
							responseParm.setRespCode("9004");
							responseParm.setRespDesc("接口 2004  " + _vlwsin2004_1.getErrorMessage());
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParm; // 返回错误信息
	}
	
	/**
	 * 校验并存储
	 */
	public Map<String,String> validateAndSave(WsIn2001 wsIn2001) throws ServiceException {
		Map<String,String> errorMap = new HashMap<String,String>();// 错误信息集合s
		Map<String,String> prePactNoRepeat = new HashMap<String,String>();// 错误信息集合s
		// 创建批次主信息实体对象
		PreBatch preBatch = new PreBatch();
		// 默认批次状态为未处理
		// 对明细信息进行校验
		List<WsIn2001_1> wsIn2001_1list =  wsIn2001.getList(); //  预进件申请明细信息
		WsIn2001_1 wsIn2001_1 = new WsIn2001_1();
		List<WsIn2001_1_1> wsIn2001_1_1list = new ArrayList<WsIn2001_1_1>();
		WsIn2001_1_1 wsIn2001_1_1 = new WsIn2001_1_1();
		try {
			ValidateLog _vl_wsIn2001 = filterEngineInterface.createValidateLog("wsIn2001Id", wsIn2001, true);
			
			if(!_vl_wsIn2001.isSuccess()){ // 大实体有问题
				//首先进行对wsin2001 brNo,batNo,dataCnt,进行校验并输出校验结果
				errorMap.put("subject", _vl_wsIn2001.getErrorMessage());
			}else if(_vl_wsIn2001.isSuccess()){
				//该批次号是否已经插入过
				PreBatch preBatchforhave = new PreBatch();
				preBatchforhave.setBatchNo(wsIn2001.getBatNo());
				preBatchforhave = preBatchDao.getById(preBatchforhave);
				String batchNo = wsIn2001.getBatNo();
				if(preBatchforhave != null){
				errorMap.put("repeat","批次号为： "+batchNo+"  插入重复");
				}else{
			 // 大实体没问题
				int i=0;
				for( ;i<wsIn2001_1list.size(); i++){
					wsIn2001_1 = wsIn2001_1list.get(i);
					boolean _success_flag = true ; // 校验成功标志
					String appId = CreateKey.getPreAppId(); //生成申请业务编号
					wsIn2001_1.setAppId(appId);
					wsIn2001_1.setBatchNo(wsIn2001.getBatNo()); // 设置批次编号
					wsIn2001_1.setBrNo(wsIn2001.getBrNo()); // 设置合作机构编号
					//对WsIn2001_1输入的实体类进行校验
					ValidateLog _vl_wsIn2001_1 = filterEngineInterface.createValidateLog("wsIn2001_1Id", wsIn2001_1, true);
					if(!_vl_wsIn2001_1.isSuccess()){
						if(prePactNoRepeat.get(wsIn2001_1.getPrePactNo())==null){
							prePactNoRepeat.put(wsIn2001_1.getPrePactNo(), "1");
							errorMap.put(wsIn2001_1.getPrePactNo(), _vl_wsIn2001_1.getErrorMessage());
						}else{
							errorMap.put("error", "合同号为"+wsIn2001_1.getPrePactNo()+"的申请为重复合同号");
						}
						_success_flag = false;
					}else{
						if(prePactNoRepeat.get(wsIn2001_1.getPrePactNo())==null){
							prePactNoRepeat.put(wsIn2001_1.getPrePactNo(), "1");
						}else{
							errorMap.put("error", "合同号为"+wsIn2001_1.getPrePactNo()+"的申请为重复合同号");
							_success_flag = false;
						}
					}
					
					//如果wsIn2001_1校验通过,对WsIn2001_1_1字段进行校验
					if(_success_flag){
						if(wsIn2001_1.getListGage()!=null && wsIn2001_1.getListGage().size()!=0){
							wsIn2001_1_1list = wsIn2001_1.getListGage();
							int wsIn2001_1_1list_len = wsIn2001_1_1list.size();
							for(int j=0;j<wsIn2001_1_1list_len;j++){
								wsIn2001_1_1 = wsIn2001_1_1list.get(j);
								wsIn2001_1_1.setAppId(appId);
								ValidateLog _vl_wsIn2001_1_1 = filterEngineInterface.createValidateLog("wsIn2001_1_1Id", wsIn2001_1_1, true);
								if(!_vl_wsIn2001_1_1.isSuccess()){
									errorMap.put(wsIn2001_1.getPrePactNo(), _vl_wsIn2001_1_1.getErrorMessage());
									_success_flag = false;
								}
							}
						}
						
					}
					//for循环，检查不符合规则的数据，直接从列表中移除，并且返回错误信息
					if(!_success_flag){
						wsIn2001_1list.remove(wsIn2001_1); // 验证不通过的 移除
						i--;
					}
				}
				prePactNoRepeat.clear();
				prePactNoRepeat=null;
				// 存储
				//只有通过校验的列表才进行数据库的插入操作
				if(wsIn2001_1list.size()!=0 ){
					//向数据库写入成功接收了多少条数据
					preBatch.setDbNum(wsIn2001_1list.size()); // 校验通过的笔数
						preBatch.setBatchNo(wsIn2001.getBatNo());  // 批次编号
						preBatch.setBatchDate(DateUtil.getSysDate());    // 当前系统日期
						preBatch.setBatchTime(DateUtil.getTime());    // 当前系统时间
						preBatch.setBatchNum(wsIn2001.getDataCnt()); // 批次中记录数
						preBatch.setBatchType("01");  				// 默认为自动进件
						preBatch.setBrNo(wsIn2001.getBrNo()); 	    // 合作机构编号
						preBatch.setBatchSts("01");
						preBatchDao.insert(preBatch);      // 插入批次信息
						preApplyDao.insertBatchfor2001(wsIn2001_1list);  // 批量插入预进件明细信息
				}
			}
		}
	}catch(Exception e){
			e.printStackTrace();
		}
		return errorMap;  // 返回错误信息
	}
	public  void validateforlist(List<WsIn2001_1> wsIn2001_1list){
		
		
	}
	
	//接口ws2005调用rule.xml校验规则
	public ResponseParm validateforListWs(WsIn2005 wsIn2005) throws ServiceException{
		ResponseParm responseParm = new ResponseParm();
		responseParm.setRespCode("0000");
		ValidateLog _vl;
		
		try {
			_vl = filterEngineInterface.createValidateLog("wsIn2005Id", wsIn2005, true);
			if(!_vl.isSuccess()){ // 有问题
				responseParm.setRespCode("9004");
				responseParm.setRespDesc(_vl.getErrorMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParm;  // 返回错误信息
	}
	
	public int coutforListWs(PreBatch preBatch) throws ServiceException{
		int count = 0;
		try {
			 count = preBatchDao.getCountforlistWs(preBatch);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
		} 

	/**
	 * @作者 DHCC-孙明义
	 * @日期 Jun 23, 2016
	 * @方法说明：预进件申请处理结果查询接口
	 * @返回参数 返回校验错误信息集合 返回征信查询结果
	 */
	@Override
	public List<WsOut2004> findByBatchNo(WsOut2004 wsOut2004) throws ServiceException {
		ResponseParm responseParm = new ResponseParm();   //  响应请求对象定义
//		List<WsPubBean> wsPubBeanList = wsPreApplyDao.findByBatchNo(wsPubBean);// 还未开发
//		return wsPubBeanList;
		return null;
	}

	//接口ws2005输出符合条件的数量,开始
	public Ipage findByPageforListWs(Ipage ipg, PreBatch preBatch) throws ServiceException{
		try {
			
			ipg.initPageCounts(new Integer[] { (Integer) preBatchDao.getCountforlistWs(preBatch) });// 初始化分页类
			
			HashMap<String, Object> map = (HashMap<String, Object>) IbatisUtils.getEntityPropertyMap(ipg, preBatch);
			map.put("startNum", ipg.getStartRow());
			map.put("endNum", ipg.getEndNum());
			ipg.setResult(preBatchDao.findByPageforlistWs(map));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	
	
	
	
	public PreBatchDao getPreBatchDao() {
		return preBatchDao;
	}

	public void setPreBatchDao(PreBatchDao preBatchDao) {
		this.preBatchDao = preBatchDao;
	}

	public PreApplyDao getPreApplyDao() {
		return preApplyDao;
	}

	public void setPreApplyDao(PreApplyDao preApplyDao) {
		this.preApplyDao = preApplyDao;
	}

	public FiterEngineInterface getFilterEngineInterface() {
		return filterEngineInterface;
	}

	public void setFilterEngineInterface(FiterEngineInterface filterEngineInterface) {
		this.filterEngineInterface = filterEngineInterface;
	}

	
}