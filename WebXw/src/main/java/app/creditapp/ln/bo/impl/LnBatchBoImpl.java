package  app.creditapp.ln.bo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.base.BaseService;
import app.base.CreateKey;
import app.base.ServiceException;
import app.creditapp.inf.entity.RequestParm;
import app.creditapp.inf.entity.ResponseParm;
import app.creditapp.inf.entity.WsIn2101;
import app.creditapp.inf.entity.WsIn2101_1;
import app.creditapp.inf.entity.WsIn2101_1_1;
import app.creditapp.inf.entity.WsIn2101_1_2;
import app.creditapp.inf.entity.WsIn2101_1_3;
import app.creditapp.inf.entity.WsIn2101_1_4;
import app.creditapp.inf.entity.WsIn2103;
import app.creditapp.inf.entity.WsOut2101;
import app.creditapp.inf.entity.WsOut2101_1;
import app.creditapp.ln.bo.LnBatchBo;
import app.creditapp.ln.dao.LnApplyMidDao;
import app.creditapp.ln.dao.LnBatchDao;
import app.creditapp.ln.entity.LnBatch;
import app.util.DateUtil;
import app.util.IbatisUtils;
import app.util.ruleFiter.entity.ValidateLog;
import app.util.ruleFiter.fiter.FiterEngineInterface;
import app.util.toolkit.Ipage;
/**
* Title: LnBatchBoImplImpl.java
* Description:
**/
public class LnBatchBoImpl extends BaseService implements LnBatchBo {


	private LnApplyMidDao lnApplyMidDao; // 正式进件明细信息持久化层
	private LnBatchDao lnBatchDao;       // 正式进件批次信息持久化层
	private FiterEngineInterface filterEngineInterface;

	/**
	 * @作者 DHCC-SONG
	 * @日期 Jun 27, 2016
	 * @方法说明：校验并插入批次表、进件申请中间表、业务阶段表
	 * @返回参数 响应参数实体ResponseParm
	 */
	
	public List<WsOut2101_1> validateApplyMid(WsIn2101 wsIn2101) throws ServiceException{
		// 获取正式进件明细信息
		WsOut2101 wsOut2101 = new WsOut2101();
		WsOut2101_1 wsOut2101_1 = new WsOut2101_1();
		List<WsIn2101_1> wsIn2101_1List =  wsIn2101.getList(); // 进件申请中间表List -- lnApplyMidList
		List<WsOut2101_1> wsOut2101_1List = wsOut2101.getList();
//		Map<String,String> errorMap = new HashMap<String,String>();// 错误信息集合
		try {
			String appId = null;
			for(WsIn2101_1 wsIn2101_1 : wsIn2101_1List){
				appId = CreateKey.getLnApplyId();    // 生成正式业务申请ID
				// 校验正式进件申请信息
				ValidateLog _vl_applyMid = filterEngineInterface.createValidateLog("wsIn2101_1Id", wsIn2101_1, true);
				boolean _success_flag = true ; // 校验成功标志
				
				if(!_vl_applyMid.isSuccess()){
//					sealDesc.put(wsLnApplyMid.getPactNo(), _vl_applyMid.getErrorMessage());
					wsOut2101_1.setDealDesc(_vl_applyMid.getErrorMessage());
					wsOut2101_1List.add(wsOut2101_1);
					_success_flag = false;
				}
				
				// 校验账户信息
				if(_success_flag){
					for(WsIn2101_1_1 wsIn2101_1_1 : wsIn2101_1.getListAc()){
//						wsLnAcctMid.setAppId(appId);
						ValidateLog _vl_acct = filterEngineInterface.createValidateLog("wsIn2101_1_1Id", wsIn2101_1_1, true);
						if(!_vl_acct.isSuccess()){
//							errorMap.put(wsLnApplyMid.getPactNo(), _vl_acct.getErrorMessage());
							wsOut2101_1.setDealDesc(_vl_acct.getErrorMessage());
							wsOut2101_1List.add(wsOut2101_1);
							_success_flag = false ;
						}
					}
				}
				
				// 校验押品信息
				if(_success_flag){
					for(WsIn2101_1_2 wsIn2101_1_2 : wsIn2101_1.getListGage()){
//						wsLnGageMid.setAppId(appId);
						ValidateLog _vl_gage = filterEngineInterface.createValidateLog("wsIn2101_1_2Id", wsIn2101_1_2, true);
						if(!_vl_gage.isSuccess()){
//							errorMap.put(wsLnApplyMid.getPactNo(), _vl_gage.getErrorMessage());
							wsOut2101_1.setDealDesc(_vl_gage.getErrorMessage());
							wsOut2101_1List.add(wsOut2101_1);
							_success_flag = false ;
						}
					}
				}
				
				if(!_success_flag){
					wsIn2101_1List.remove(wsIn2101_1); // 验证不通过的 移除
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return wsOut2101_1List;
	}
	
	/**
	 * @作者 DHCC-SONG
	 * @日期 Jun 27, 2016
	 * @方法说明：插入批次表、进件申请中间表、业务阶段表
	 * @返回参数 响应参数实体ResponseParm
	 */
	public void insertBatMidAndStage(WsIn2101 wsIn2101) throws ServiceException{
		// 创建批次主信息实体对象
		LnBatch lnBatch = new LnBatch(); 
		lnBatch.setBatchNo(wsIn2101.getBatNo());   // 批次编号
		lnBatch.setBatchDate(DateUtil.getDate());    // 当前系统日期
		lnBatch.setBatchTime(DateUtil.getTime());    // 当前系统时间
		lnBatch.setBatchNum(wsIn2101.getDataCnt());  // 批次中记录数
		lnBatch.setBatchType("01");  				   // 默认为自动进件
		lnBatch.setBrNo(wsIn2101.getBrNo()); 	       // 合作机构编号
		lnBatch.setBatchSts("01");                   // 默认批次状态为未处理
		// 获取正式进件明细信息
		
		List<WsIn2101_1> wsIn2101_1List =  wsIn2101.getList(); //  正式进件申请明细信息
		lnBatch.setDbNum(wsIn2101_1List.size()) ; // 插入数据库的明细数据
		// 插入批次主表
		lnBatchDao.insert(lnBatch);
		// 批量插入正式进件明细信息 系统自动调用触发器插入ln_stage表，触发器名称：TRI_LN_APPLYMID_INSERT
//		lnApplyMidDao.insertBatchWs2101_1(wsIn2101_1List);
		lnApplyMidDao.insertBatchWs2101_1(wsIn2101_1List);
	}
	
	@Override
	public ResponseParm search(RequestParm reqParm) throws ServiceException {
		ResponseParm responseParm = new ResponseParm();
		// -- 王涛 正式进件申请结果查询业务逻辑
//		Map<String, Object> map = new HashMap<String, Object>();
//			JSONObject jsonMap = JSONObject.fromObject(reqParm.getContent());
//			JSONObject jsonMap = (JSONObject)JSON.toJSON(reqParm.getContent());
//			Iterator<String> it = jsonMap.keys();
//			while(it.hasNext()) {
//				String key = (String) it.next();
//				map.put(key, jsonMap.get(key));
//			}
		//List<LnApplyMid> palist = lnApplyMidDao.findByBatchno(map);
		// preApplyDao.insertBatch(pbic.getPaList());
		
		//responseParm.setRespCode("");
		//responseParm.setContent(JSONArray.fromObject(palist).toString());
		
		return responseParm;
	}

	/**
	 * @作者 DHCC-仲凯旋
	 * @日期 July 12, 2016
	 * @方法说明：接口ws2103调用rule-WsIn2103.xml校验规则
	 * @返回参数 响应参数实体ResponseParm
	 */
	public  ResponseParm validateWsIn(WsIn2103 wsIn2103) throws ServiceException{
		ResponseParm responseParm = new ResponseParm();
		responseParm.setRespCode("0000");
		ValidateLog _vl;		
		try {
			_vl = filterEngineInterface.createValidateLog("wsIn2103Id", wsIn2103, true);
			if(!_vl.isSuccess()){ // 有问题
				responseParm.setRespCode("9004");
				responseParm.setRespDesc(_vl.getErrorMessage());
			}else{
				LnBatch lnBatch = new LnBatch();
				lnBatch.setBatchNo(wsIn2103.getBatNo());
				int count = lnBatchDao.getCount(lnBatch);
				if(count==0){
					responseParm.setRespCode("2990");
					responseParm.setRespDesc("批次号为： "+wsIn2103.getBatNo()+"  的记录不存在！");
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParm; 
	}
	public Ipage findByPageforList(Ipage ipg, LnBatch lnBatch) throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) lnBatchDao.getCountforlist(lnBatch) });// 初始化分页类
			HashMap<String, Object> map = (HashMap<String, Object>) IbatisUtils.getEntityPropertyMap(ipg, lnBatch);
			map.put("startNum", ipg.getStartRow());
			map.put("endNum", ipg.getEndNum());
			ipg.setResult(lnBatchDao.findByPageforlist(map));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	
	public int countforListWs(LnBatch lnBatch) throws ServiceException{
		int count = 0;
		try {
			 count = lnBatchDao.getCountforlist(lnBatch);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
		} 
	
	public void del(LnBatch lnBatch) throws ServiceException {
		try {
			lnBatchDao.del(lnBatch);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, LnBatch lnBatch)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) lnBatchDao
					.getCount(lnBatch) });// 初始化分页类
			lnBatch.setStartNumAndEndNum (ipg);
			ipg.setResult(lnBatchDao.findByPage(lnBatch));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public LnBatch getById(LnBatch lnBatch) throws ServiceException {
		try {
			lnBatch = lnBatchDao.getById(lnBatch);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return lnBatch;
	}

	public void insert(LnBatch lnBatch) throws ServiceException {
		try {
			lnBatchDao.insert(lnBatch);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(LnBatch lnBatch) throws ServiceException {
		try {
			lnBatchDao.update(lnBatch);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void updateNum(LnBatch lnBatch) throws ServiceException {
		try {
			lnBatchDao.updateNum(lnBatch);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public LnBatch getByLnApplyMid(LnBatch lnBatch) throws ServiceException {
		try {
			lnBatch = lnBatchDao.getByLnApplyMid(lnBatch);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return lnBatch;
	}

	public  Map<String,String> validateAndSave(WsIn2101 wsIn2101) throws ServiceException{
		
		Map<String,String> pactNoRepeat = new HashMap<String,String>();// 错误信息集合s
		Map<String,String> errorMap = new HashMap<String,String>();// 错误信息集合s
		// 创建批次主信息实体对象
		LnBatch lnBatch = new LnBatch();
//		boolean _success_flag1 = true; //校验成功标志
//		//该批次号是否已经插入过
//		LnBatch lnBatchforhave = new LnBatch();
//		lnBatchforhave.setBatchNo(wsIn2101.getBatNo());
		// 默认批次状态为未处理
		// 对明细信息进行校验
		List<WsIn2101_1> wsIn2101_1list =  wsIn2101.getList(); //  预进件申请明细信息
		WsIn2101_1 wsIn2101_1 = null;
		List<WsIn2101_1_1> wsIn2101_1_1list = null;
		List<WsIn2101_1_2> wsIn2101_1_2list = null;
		List<WsIn2101_1_3> wsIn2101_1_3list = null;
		List<WsIn2101_1_4> wsIn2101_1_4list = null;
		WsIn2101_1_1 wsIn2101_1_1 = null;
		WsIn2101_1_2 wsIn2101_1_2 = null;
		WsIn2101_1_3 wsIn2101_1_3 = null;
		WsIn2101_1_4 wsIn2101_1_4 = null;
		
		try {
			ValidateLog _vl_wsIn2101 = filterEngineInterface.createValidateLog("wsIn2101Id", wsIn2101, true);
			
			if(!_vl_wsIn2101.isSuccess()){ // 大实体有问题
				//首先进行对wsin2001 brNo,batNo,dataCnt,进行校验并输出校验结果
				errorMap.put("subject", _vl_wsIn2101.getErrorMessage());
			}else if(_vl_wsIn2101.isSuccess()){
				String batchNo = wsIn2101.getBatNo();
				lnBatch.setBatchNo(batchNo);
			    int count = lnBatchDao.getCount(lnBatch);
			    if(count != 0){
			    	errorMap.put("repeat","批次号为："+batchNo+"插入重复");
			    }else{
			    	// 大实体没问题
			    	 int i=0;
					for(;i<wsIn2101_1list.size(); i++){
						boolean _success_flag = true ; // 校验成功标志
						wsIn2101_1 = wsIn2101_1list.get(i);
						String appId = CreateKey.getLnApplyId(); //生成申请业务编号
						wsIn2101_1.setAppId(appId);
						wsIn2101_1.setBatchNo(wsIn2101.getBatNo()); // 设置批次编号
						wsIn2101_1.setBrNo(wsIn2101.getBrNo()); // 设置合作机构编号
						wsIn2101_1.setAppSts("01");//申请状态[01未处理02已处理]
						wsIn2101_1.setChkRes("00");//筛查结果[00未筛查01成功02文件错误03字段错误04黑名单客户05评级拒绝06查征拒绝07重复作废08配偶信息不完整09合作机构编号无效10信托项目编号无效，或者与合作机构编号不符11产品号不存在或者无效12不存在押品信息13押品抵押价值不足14账户信息不完整15账户放款金额总和不等于合同金额99其他错误]
						wsIn2101_1.setChkDesc("未筛查");
						wsIn2101_1.setAppDate(DateUtil.getSysDate());
						//对WsIn2001_1输入的实体类进行校验
						ValidateLog _vl_wsIn2101_1 = filterEngineInterface.createValidateLog("wsIn2101_1Id", wsIn2101_1, true);
						if(!_vl_wsIn2101_1.isSuccess()){
							if(pactNoRepeat.get(wsIn2101_1.getPactNo())==null){
								pactNoRepeat.put(wsIn2101_1.getPactNo(), "1");
								errorMap.put(wsIn2101_1.getPactNo(), _vl_wsIn2101_1.getErrorMessage());
							}else{
								errorMap.put(wsIn2101_1.getPactNo(), "合同号重复");
							}
							_success_flag = false;
						}else{
							if(pactNoRepeat.get(wsIn2101_1.getPactNo())==null){
								pactNoRepeat.put(wsIn2101_1.getPactNo(), "1");
							}else{
								errorMap.put("error", "合同号为"+wsIn2101_1.getPactNo()+"的申请为重复合同号");
								_success_flag = false;
							}
						}
						//如果wsIn2001_1校验通过,对WsIn2001_1_1字段进行校验
						if(_success_flag&&wsIn2101_1.getListAc()!=null){
							wsIn2101_1_1list = wsIn2101_1.getListAc();
							int wsIn2101_1_1list_len = wsIn2101_1_1list.size();
							for(int j=0;j<wsIn2101_1_1list_len;j++){
								wsIn2101_1_1 = wsIn2101_1_1list.get(j);
								wsIn2101_1_1.setAppId(appId);
								ValidateLog _vl_wsIn2101_1_1 = filterEngineInterface.createValidateLog("wsIn2101_1_1Id", wsIn2101_1_1, true);
								if(!_vl_wsIn2101_1_1.isSuccess()){
									errorMap.put(wsIn2101_1.getPactNo(), _vl_wsIn2101_1_1.getErrorMessage());
									_success_flag = false;
								}
							}
						}else{
							_success_flag = false;
						}
						//如果wsIn2001_1_1校验通过,对WsIn2001_1_2字段进行校验
						if(_success_flag){
							if(wsIn2101_1.getListGage()!=null){
								wsIn2101_1_2list = wsIn2101_1.getListGage();
								int wsIn2101_1_2list_len = wsIn2101_1_2list.size();
								for(int j=0;j<wsIn2101_1_2list_len;j++){
									wsIn2101_1_2 = wsIn2101_1_2list.get(j);
									wsIn2101_1_2.setAppId(appId);
									ValidateLog _vl_wsIn2101_1_2 = filterEngineInterface.createValidateLog("wsIn2101_1_2Id", wsIn2101_1_2, true);
									if(!_vl_wsIn2101_1_2.isSuccess()){
										errorMap.put(wsIn2101_1.getPactNo(), _vl_wsIn2101_1_2.getErrorMessage());
										_success_flag = false;
									}
								}
							}
						}
						//如果wsIn2001_1_2校验通过,对WsIn2001_1_3字段进行校验
						if(_success_flag&&wsIn2101_1.getListCom()!=null){
							wsIn2101_1_3list = wsIn2101_1.getListCom();
							int wsIn2101_1_3list_len = wsIn2101_1_3list.size();
							for(int j=0;j<wsIn2101_1_3list_len;j++){
								wsIn2101_1_3 = wsIn2101_1_3list.get(j);
								wsIn2101_1_3.setAppId(appId);
								ValidateLog _vl_wsIn2101_1_3 = filterEngineInterface.createValidateLog("wsIn2101_1_3Id", wsIn2101_1_3, true);
								if(!_vl_wsIn2101_1_3.isSuccess()){
									errorMap.put(wsIn2101_1.getPactNo(), _vl_wsIn2101_1_3.getErrorMessage());
									_success_flag = false;
								}
							}
						}else{
							_success_flag = false;
						}
						//如果wsIn2001_1_3校验通过,对WsIn2001_1_4字段进行校验
						if(_success_flag&&wsIn2101_1.getListRel()!=null){
							wsIn2101_1_4list = wsIn2101_1.getListRel();
							int wsIn2101_1_4list_len = wsIn2101_1_4list.size();
							for(int j=0;j<wsIn2101_1_4list_len;j++){
								wsIn2101_1_4 = wsIn2101_1_4list.get(j);
								wsIn2101_1_4.setAppId(appId);
								ValidateLog _vl_wsIn2101_1_4 = filterEngineInterface.createValidateLog("wsIn2101_1_4Id", wsIn2101_1_4, true);
								if(!_vl_wsIn2101_1_4.isSuccess()){
									errorMap.put(wsIn2101_1.getPactNo(), _vl_wsIn2101_1_4.getErrorMessage());
									_success_flag = false;
								}
							}
						}else{
							_success_flag = false;
						}
						//for循环，检查不符合规则的数据，直接从列表中移除，并且返回错误信息
						if(!_success_flag){
							pactNoRepeat.remove(wsIn2101_1.getPactNo());
							wsIn2101_1list.remove(wsIn2101_1); // 验证不通过的 移除
							i--;
						}
			       }
					pactNoRepeat.clear();
					pactNoRepeat=null;
				// 存储
				//只有通过校验的列表才进行数据库的插入操作
				if(wsIn2101_1list.size()!=0 ){
					//向数据库写入成功接收了多少条数据
					lnBatch.setDbNum(wsIn2101_1list.size()); // 校验通过的笔数
						//插入批次信息
						lnBatch.setBatchNo(wsIn2101.getBatNo());  // 批次编号
						lnBatch.setBatchDate(DateUtil.getSysDate());    // 当前系统日期
						lnBatch.setBatchTime(DateUtil.getTime());    // 当前系统时间
						lnBatch.setBatchNum(wsIn2101.getDataCnt()); // 批次中记录数
						lnBatch.setBatchType("01");  				// 默认为自动进件
						lnBatch.setBrNo(wsIn2101.getBrNo()); 	    // 合作机构编号
						lnBatch.setBatchSts("01");
						lnBatchDao.insert(lnBatch);
						// 批量插入正式进件明细信息 系统自动调用触发器插入ln_stage表，触发器名称：TRI_LN_APPLYMID_INSERT
						lnApplyMidDao.insertBatchWs2101_1(wsIn2101_1list);
				 }
			   }
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return errorMap;  // 返回错误信息
	}
	public LnBatchDao getLnBatchDao() {
		return lnBatchDao;
	}

	public void setLnBatchDao(LnBatchDao lnBatchDao) {
		this.lnBatchDao = lnBatchDao;
	}

	public LnApplyMidDao getLnApplyMidDao() {
		return lnApplyMidDao;
	}

	public void setLnApplyMidDao(LnApplyMidDao lnApplyMidDao) {
		this.lnApplyMidDao = lnApplyMidDao;
	}

	public FiterEngineInterface getFilterEngineInterface() {
		return filterEngineInterface;
	}

	public void setFilterEngineInterface(FiterEngineInterface filterEngineInterface) {
		this.filterEngineInterface = filterEngineInterface;
	}

}