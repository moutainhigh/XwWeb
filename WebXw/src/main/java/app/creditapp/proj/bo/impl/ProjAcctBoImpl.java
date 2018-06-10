package app.creditapp.proj.bo.impl;

import java.text.DecimalFormat;
import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.inf.bo.AllocateRegServiceBo;
import app.creditapp.inf.client.AllocateRegService;
import app.creditapp.inf.dao.AllocateRegWsInDao;
import app.creditapp.inf.entity.AllocateRegWsIn;
import app.creditapp.inf.entity.DetailTransactionCommonDTO;
import app.creditapp.inf.entity.MainTransactionCommonDTO;
import app.creditapp.inf.entity.ReqData;
import app.creditapp.inf.entity.ResData;
import app.creditapp.proj.bo.ProjAcctBo;
import app.creditapp.proj.dao.AllocateRegDao;
import app.creditapp.proj.dao.ProjAcctDao;
import app.creditapp.proj.dao.ProjBaseDao;
import app.creditapp.proj.entity.ProjAcct;
import app.creditapp.proj.entity.ProjBase;
import app.creditapp.sys.dao.InterfaceBackMegDao;
import app.creditapp.sys.entity.InterfaceBackMeg;
import app.util.User;
import app.util.toolkit.Ipage;

import com.alibaba.fastjson.JSON;

import flex.messaging.io.ArrayList;

/**
 * Title: ProjAcctBoImplImpl.java Description:
 * 
 **/
public class ProjAcctBoImpl extends BaseService implements ProjAcctBo {
	private ProjBaseDao projBaseDao;
	private ProjAcctDao projAcctDao;
	private AllocateRegDao allocateRegDao;
	private AllocateRegServiceBo allocateRegServiceBo;
	private InterfaceBackMegDao interfaceBackMegDao;
	private AllocateRegService allocateRegService;
	private AllocateRegWsIn allocateRegWsIn;
	private AllocateRegWsInDao allocateRegWsInDao;

	public void del(ProjAcct projAcct) throws ServiceException {
		try {
			projAcctDao.del(projAcct);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public int getCount(ProjAcct projAcct) throws ServiceException{
		int count = 0;
		try {
			count = projAcctDao.getCount(projAcct);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return count;
	}
	public int getCountForX(ProjAcct projAcct) throws ServiceException{
		int count = 0;
		try {
			count = projAcctDao.getCountForX(projAcct);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return count;
		
	}
	public String getProjId(ProjAcct projAcct) throws ServiceException{
		String projId = "";
		try {
			projId = projAcctDao.getProjId(projAcct);
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return projId;
	}
	public void getInfForAcct(ProjAcct projAcct) throws ServiceException{
		
		try {
			projAcctDao.getInfForAcct(projAcct);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public List<ProjAcct> findListByProjNo(ProjAcct projAcct) throws ServiceException{
		List<ProjAcct> projAcctList = null;
		try {
			projAcctList = projAcctDao.findListByProjNo(projAcct);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return projAcctList;
	}
	
	public Ipage findByPage(Ipage ipg, ProjAcct projAcct) throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) projAcctDao.getCount(projAcct) });// 初始化分页类
			projAcct.setStartNumAndEndNum(ipg);
			ipg.setResult(projAcctDao.findByPage(projAcct));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public Ipage findByPageForPop(Ipage ipg, ProjAcct projAcct) throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) projAcctDao.getCountForPop(projAcct) });// 初始化分页类
			projAcct.setStartNumAndEndNum(ipg);
			ipg.setResult(projAcctDao.findByPageForPop(projAcct));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	//获取 该项目下的 专户和 虚拟账号信息
	public int getCountForProj(ProjAcct projAcct) throws ServiceException {
		int count = 0;
		try {
			count =  projAcctDao.getCount(projAcct);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return count;
	}

	public ProjAcct getById(ProjAcct projAcct) throws ServiceException {
		try {
			projAcct = projAcctDao.getById(projAcct);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return projAcct;
	}

	public ProjAcct getByProjNoAndAcctType(ProjAcct projAcct) throws ServiceException {
		try {
			projAcct = projAcctDao.getByProjNoAndAcctType(projAcct);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return projAcct;
	}

	/**
	 * @作者 DHCC-ZLC
	 * @日期 2016-8-25
	 * @方法说明：虚拟账户预拨款
	 * @返回参数 ProjAcct
	 */
	public ProjAcct virtual_AllocateReg(AllocateRegWsIn allocateRegWsIn) throws ServiceException {
		ProjAcct projAcct = new ProjAcct();
		try {
			double acctBal = 0.00;
			projAcct.setProjNo(allocateRegWsIn.getProjectid());
			projAcct.setAcctType("01");
			projAcct = projAcctDao.getByProjNoAndAcctType(projAcct);

			acctBal = projAcct.getAcctBal() - Double.valueOf(allocateRegWsIn.getAmount());
			// 虚拟户信息
			ProjAcct projAcct1 = new ProjAcct();
			projAcct1.setAcctId(allocateRegWsIn.getAcctId());
			projAcct1 = projAcctDao.getInterfaceById(projAcct1);
			if (acctBal > 0) {
				// 插入收拨款接口表
				allocateRegWsInDao.insert(allocateRegWsIn);
				allocateRegWsIn.setSdtime(User.gethnTime());
				// 出参
				ResData resData = new ResData();
				//ErrorMsgDTO errorMsgDTO = new ErrorMsgDTO();
				// 调用接口
				resData = allocateRegService(allocateRegWsIn);
				String status = resData.getStatus();
				if ("1".equals(status)) {
					// 修改专户余额
//					projAcct.setAcctBal(acctBal);
//					projAcctDao.update(projAcct);
//					// 修改虚拟户余额
//					ProjAcct projAcct2 = new ProjAcct();
//					projAcct2.setAcctId(allocateRegWsIn.getAcctId());
//					projAcct2 = projAcctDao.getById(projAcct1);
//					projAcct2.setAcctBal(projAcct2.getAcctBal() + Double.valueOf(allocateRegWsIn.getAmount()));
//					projAcctDao.update(projAcct2);
//					// 插入虚拟户明细表
//					AllocateReg allocateReg = new AllocateReg();
//					allocateReg.setAcctId(allocateRegWsIn.getAcctId());
//					allocateReg.setAcctNo("");
//					allocateReg.setProjNo(allocateRegWsIn.getProjectid());
//					allocateReg.setTxAmt(Double.valueOf(allocateRegWsIn.getAmount()));
//					allocateReg.setFAcctNo(projAcct.getAcctNo());
//					allocateReg.setOpNo(allocateRegWsIn.getUserCode());
//					allocateRegDao.insert(allocateReg);
					
					allocateRegWsIn.setWssts("1");
					allocateRegWsIn.setTransid(resData.getTransID());
					allocateRegWsInDao.update(allocateRegWsIn);
					projAcct.setFiller("软通系统返回信息:预拨款成功");
				} else {
					allocateRegWsIn.setWssts("2");
					allocateRegWsIn.setRemarks(resData.getErrorCopeType()+" code:"+resData.getErrorCode());
					allocateRegWsInDao.update(allocateRegWsIn);
					//errorMsgDTO = resData.getErrorMsgDTO();
					InterfaceBackMeg interfaceBackMeg = new InterfaceBackMeg();
					interfaceBackMeg.setInterfaceName("收拨款接口");
					interfaceBackMeg.setBackMegKey(resData.getErrorCode());
					interfaceBackMeg = interfaceBackMegDao.getById(interfaceBackMeg);
					projAcct.setFiller("软通系统返回信息:"+resData.getErrorCode()+"-"+interfaceBackMeg.getBackMegDes());
				}
			} else {
				projAcct.setFiller("专户余额不足");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return projAcct;
	}

	/**
	 * @作者 DHCC-ZLC
	 * @日期 2016-8-25
	 * @方法说明：预拨款service调用接口方法
	 * @返回参数 String
	 */
	public ResData allocateRegService(AllocateRegWsIn allocateRegWsIn) throws ServiceException {
		// 入参
		ReqData reqData = new ReqData();
		// 出参
		ResData resData = new ResData();
		// 主体信息
		MainTransactionCommonDTO mainTransactionCommonDTO = new MainTransactionCommonDTO();
		// 明细list
		List<DetailTransactionCommonDTO> detailTransactionCommonDTOList = new ArrayList();
		try {
			allocateRegWsIn = allocateRegWsInDao.getById(allocateRegWsIn);
			// 主体信息
			mainTransactionCommonDTO.setId(allocateRegWsIn.getId());
			mainTransactionCommonDTO.setProjectID(allocateRegWsIn.getProjectid());
			if(allocateRegWsIn.getDdtype()==null){
				mainTransactionCommonDTO.setDdType("");
			}else{
				mainTransactionCommonDTO.setDdType(allocateRegWsIn.getDdtype());
			}
			mainTransactionCommonDTO.setTransDate(allocateRegWsIn.getTransdate());
			mainTransactionCommonDTO.setTransReason(allocateRegWsIn.getTransreason());
			mainTransactionCommonDTO.setPurpose(allocateRegWsIn.getPurpose());
			mainTransactionCommonDTO.setMemo(allocateRegWsIn.getMemo());
//			mainTransactionCommonDTO.setUserCode("zhangluting");
			mainTransactionCommonDTO.setUserCode(allocateRegWsIn.getUserCode());
			mainTransactionCommonDTO.setStaffApp(allocateRegWsIn.getStaffapp());
			// 明细list
			DetailTransactionCommonDTO detailTransactionCommonDTO = new DetailTransactionCommonDTO();
			detailTransactionCommonDTO.setId(allocateRegWsIn.getId());
			detailTransactionCommonDTO.setAmount(allocateRegWsIn.getAmount());
			detailTransactionCommonDTO.setBkTransTypeCode(allocateRegWsIn.getBktranstypecode());
			detailTransactionCommonDTO.setSkTransTypeCode(allocateRegWsIn.getSktranstypecode());
			detailTransactionCommonDTO.setFeeTypeCode(allocateRegWsIn.getFeetypecode());
			detailTransactionCommonDTO.setTaxTypeCode(allocateRegWsIn.getTaxtypecode());
			detailTransactionCommonDTO.setCustomID(allocateRegWsIn.getCustomid());
			detailTransactionCommonDTO.setAccountID(allocateRegWsIn.getAccountid());
			// detailTransactionCommonDTO.setOpBankAcntID(allocateRegWsIn.getOpbankacntid());
			detailTransactionCommonDTO.setOpBankAcntNO(allocateRegWsIn.getOpbankacntno());
			detailTransactionCommonDTO.setOpBankName(allocateRegWsIn.getOpbankname());
			detailTransactionCommonDTO.setOpBankAcntName(allocateRegWsIn.getOpbankacntname());
			detailTransactionCommonDTO.setOpBankProvince(allocateRegWsIn.getOpbankprovince());
			detailTransactionCommonDTO.setOpBankCity(allocateRegWsIn.getOpbankcity());
			detailTransactionCommonDTO.setRepayTypeID(allocateRegWsIn.getRepaytypeid());
			detailTransactionCommonDTO.setAppUserCode(allocateRegWsIn.getAppusercode());
			// 核算信息
			detailTransactionCommonDTO.setPersonnel("");
			detailTransactionCommonDTO.setProject(allocateRegWsIn.getProject());
			detailTransactionCommonDTO.setCustomer(allocateRegWsIn.getCustomer());
			detailTransactionCommonDTO.setCashFlow("");
			detailTransactionCommonDTO.setVirtualAccount("");
			detailTransactionCommonDTO.setContractNumber("");
			detailTransactionCommonDTO.setStockCode("");
			detailTransactionCommonDTO.setFundAccount("");
			detailTransactionCommonDTO.setSalesDepartment("");
			detailTransactionCommonDTO.setCheckNumber("");
			detailTransactionCommonDTO.setBusinessType("");
			detailTransactionCommonDTO.setCashFlowStatement("");
			detailTransactionCommonDTO.setTaxCategory("");
			detailTransactionCommonDTO.setInterestRate("");
			detailTransactionCommonDTO.setIndustry(allocateRegWsIn.getIndustry());
			detailTransactionCommonDTO.setStock("");
			detailTransactionCommonDTO.setAssetName("");
			detailTransactionCommonDTO.setProductName("");
			detailTransactionCommonDTO.setClientOrBeneficiary("");
			detailTransactionCommonDTO.setShareholder("");
			detailTransactionCommonDTO.setEntrustContractNumber("");
			detailTransactionCommonDTO.setTradingPurpose("");
			detailTransactionCommonDTO.setContractVariety("");
			detailTransactionCommonDTO.setDateOfReceipt("");
			detailTransactionCommonDTO.setInterestDate("");
			detailTransactionCommonDTO.setDueDate("");
			detailTransactionCommonDTO.setInOrOut("");
			detailTransactionCommonDTO.setBillNumber("");

			// 插入数据
			detailTransactionCommonDTOList.add(detailTransactionCommonDTO);

			// 入参

			reqData.setBusinessFlag("1");// 1:账户拨款，2：信托付自营，3：账户收款，4:自营代垫，5:信托付保障基金，6：多账户拨款
			reqData.setAutoSyncFlag("2");//自动同步辅助核算标示   
			reqData.setMainTransactionCommonDTO(mainTransactionCommonDTO);
			reqData.setDetailTransactionCommonDTOList(detailTransactionCommonDTOList);

			// 调用webservice接口
			// AllocateRegServiceBo allocateRegServiceBo =
			// (AllocateRegServiceBo)SourceTemplate.getSpringContextInstance().getBean("allocateRegServiceBo");
			String backData = allocateRegServiceBo.saveActTransactionInfos(reqData);
			// String Data = JSON.toJSONString(reqData);
			// String backData =
			// allocateRegService.saveActTransactionInfos(Data);
			resData = JSON.parseObject(backData, ResData.class);

		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}

		return resData;
	}

	/**
	 * @作者 DHCC-ZLC
	 * @日期 2016-8-25
	 * @方法说明：收拨款接口实体类中 -----此方法仅处理 充值-100交易 by wsf
	 * @返回参数 String
	 */
	public AllocateRegWsIn insert_allocateRegWsIn(String projNo, double txAmt, String acctId, String opNo,
			String tradeCode) throws ServiceException {
		String transtypecode = "";// 交易类型编码(充值-100-4224;放款成功-200-4258;利息-201-4271;罚息-202-2731;本金-203-290)
		String bktranstypecode = "";// 拨款的交易类型编码(拨款时必填)
		String sktranstypecode = "";// 收款的交易类型编码 (条件必填，信托付自营或者收款交易时必填)
		String businessflag = "";// 收拨款类型1:账户拨款，2：信托付自营，3：账户收款，4:自营代垫，5:信托付保障基金，6：多账户拨款
		String autoSyncFlag = "";//自动同步辅助核算标示   
		// 拨款用
		//String accountid = "";// 资金运用方账号ID/借据I
		//String project = "";// 项目名称
		//String customer = "";// 客户名称（合作机构名称）
		String industry = "";// 行业
		// 金额位数转换
		String txamt = new DecimalFormat("#.00").format(txAmt);
		allocateRegWsIn = new AllocateRegWsIn();
		//虚拟户
		ProjAcct projAcct = new ProjAcct();
		projAcct.setAcctId(acctId);
		//项目
		ProjBase projBase = new ProjBase();
		projBase.setProjNo(projNo);
		//专户
		//ProjAcct projAcctZH = new ProjAcct();

		try {
			projAcct = projAcctDao.getInterfaceById(projAcct);
			projBase = projBaseDao.getById(projBase);
			/*projAcctZH.setProjNo(projNo);
			projAcctZH.setAcctType("01");
			projAcctZH = projAcctDao.getByProjNoAndAcctType(projAcctZH);*/
			transtypecode = "4224";
			businessflag = "1";
			autoSyncFlag = "2";
			bktranstypecode = transtypecode;
			//获取软通资金运用方账号ID/借据ID
			ProjAcct projAcct1 = new ProjAcct();
			projAcct1.setProjNo(projNo);
			
			projAcct1 = projAcctDao.VW_ACT_ACCOUNTINFO(projAcct1);
			
			// 主体信息
			String id = allocateRegWsInDao.getByid();
			allocateRegWsIn.setId(id);
			allocateRegWsIn.setBusinessflag(businessflag);
			allocateRegWsIn.setAutoSyncFlag(autoSyncFlag);
			allocateRegWsIn.setProjectid(projBase.getProjId());
			allocateRegWsIn.setDdtype("");
			allocateRegWsIn.setTransdate(projAcct.getTxDate());
			allocateRegWsIn.setTransreason("小微系统充值");
			allocateRegWsIn.setPurpose(projAcct.getAcctUse());
			allocateRegWsIn.setMemo("小微系统预拨款");
			allocateRegWsIn.setUserCode(opNo);
			allocateRegWsIn.setStaffapp("");
			// 明细list
			allocateRegWsIn.setAmount(txamt);
			allocateRegWsIn.setBktranstypecode(bktranstypecode);
			allocateRegWsIn.setSktranstypecode(sktranstypecode);
			allocateRegWsIn.setFeetypecode("");
			allocateRegWsIn.setTaxtypecode("");
			allocateRegWsIn.setCustomid("");
			if(projAcct1 != null){
				allocateRegWsIn.setAccountid(projAcct1.getAcctId());
			}
			// allocateRegWsIn.setOpbankacntid("");
			allocateRegWsIn.setOpbankacntno(projAcct.getCardNo());
			allocateRegWsIn.setOpbankname(projAcct.getOpnBank());
			allocateRegWsIn.setOpbankacntname(projAcct.getCardName());
			allocateRegWsIn.setOpbankprovince(projAcct.getBankProv());
			allocateRegWsIn.setOpbankcity(projAcct.getBankCity());
			allocateRegWsIn.setRepaytypeid("");
			allocateRegWsIn.setAppusercode("");
			// 以下内容为辅助核算内容
			allocateRegWsIn.setPersonnel("");
			allocateRegWsIn.setProject(projBase.getProjName());
			allocateRegWsIn.setCustomer("");
			allocateRegWsIn.setCashflow("");
			allocateRegWsIn.setVirtualaccount("");
			allocateRegWsIn.setContractnumber("");
			allocateRegWsIn.setStockcode("");
			allocateRegWsIn.setFundaccount("");
			allocateRegWsIn.setSalesdepartment("");
			allocateRegWsIn.setChecknumber("");
			allocateRegWsIn.setBusinesstype("");
			allocateRegWsIn.setCashflowstatement("");
			allocateRegWsIn.setTaxcategory("");
			allocateRegWsIn.setInterestrate("");
			allocateRegWsIn.setIndustry(industry);
			allocateRegWsIn.setStock("");
			allocateRegWsIn.setAssetname("");
			allocateRegWsIn.setProductname("");
			allocateRegWsIn.setClientorbeneficiary("");
			allocateRegWsIn.setShareholder("");
			allocateRegWsIn.setEntrustcontractnumber("");
			allocateRegWsIn.setTradingpurpose("");
			allocateRegWsIn.setContractvariety("");
			allocateRegWsIn.setDateofreceipt("");
			allocateRegWsIn.setInterestdate("");
			allocateRegWsIn.setDuedate("");
			allocateRegWsIn.setInorout("");
			allocateRegWsIn.setBillnumber("");
			// 插入收拨款接口表
			// allocateRegWsInDao.insert(allocateRegWsIn);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return allocateRegWsIn;
	}

	/**
	 * @作者 DHCC-ZLC
	 * @日期 2016-8-25
	 * @方法说明：自动调用收拨款接口方法
	 * @返回参数 String
	 */
	public void auto_allocateRegWsIn(String projNo, double txAmt, String acctId, String opNo, String tradeCode)
			throws ServiceException {
		try {
			// 调动方法生成收拨款实体类数据
			allocateRegWsIn = new AllocateRegWsIn();
			allocateRegWsIn = insert_allocateRegWsIn(projNo, txAmt, acctId, opNo, tradeCode);
			// 调用allocateRegService
			virtual_AllocateReg(allocateRegWsIn);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void insert(ProjAcct projAcct) throws ServiceException {
		try {
			/**
			 * 新增项目信息的账户号是序列
			 */
			projAcct.setAcctId(projAcctDao.getKey());
			projAcctDao.insert(projAcct);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(ProjAcct projAcct) throws ServiceException {
		try {
			projAcctDao.update(projAcct);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void updateSts(ProjAcct projAcct) throws ServiceException {
		try {
			projAcctDao.updateSts(projAcct);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void updateAcctBal(ProjAcct projAcct) throws ServiceException {
		try {
			projAcctDao.updateAcctBal(projAcct);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Ipage findByPageQuotaForCorp(Ipage ipage, ProjAcct projAcct) throws ServiceException {
		try {
			ipage.initPageCounts(new Integer[] { (Integer) projAcctDao.getCountQuotaForCorp(projAcct) });// 初始化分页类
			projAcct.setStartNumAndEndNum(ipage);
			ipage.setResult(projAcctDao.findByPageQuotaForCorp(projAcct));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipage;
	}

	public Ipage findByProjNoAndAcctType(Ipage ipg, ProjAcct projAcct) throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) projAcctDao.getCount(projAcct) });// 初始化分页类
			projAcct.setStartNumAndEndNum(ipg);
			ipg.setResult(projAcctDao.findByProjNoAndAcctType(projAcct));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	
	public int getByProjNo(ProjAcct acct) throws ServiceException {
		int size = 0;
		try{
			size = projAcctDao.getByProjNo(acct);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return size;
	}
	public ProjAcctDao getProjAcctDao() {
		return projAcctDao;
	}

	public void setProjAcctDao(ProjAcctDao projAcctDao) {
		this.projAcctDao = projAcctDao;
	}

	/**
	 * @return the allocateRegDao
	 */
	public AllocateRegDao getAllocateRegDao() {
		return allocateRegDao;
	}

	/**
	 * @param allocateRegDao
	 *            the allocateRegDao to set
	 */
	public void setAllocateRegDao(AllocateRegDao allocateRegDao) {
		this.allocateRegDao = allocateRegDao;
	}

	/**
	 * @return the allocateRegServiceBo
	 */
	public AllocateRegServiceBo getAllocateRegServiceBo() {
		return allocateRegServiceBo;
	}

	/**
	 * @param allocateRegServiceBo
	 *            the allocateRegServiceBo to set
	 */
	public void setAllocateRegServiceBo(AllocateRegServiceBo allocateRegServiceBo) {
		this.allocateRegServiceBo = allocateRegServiceBo;
	}

	/**
	 * @return the interfaceBackMegDao
	 */
	public InterfaceBackMegDao getInterfaceBackMegDao() {
		return interfaceBackMegDao;
	}

	/**
	 * @param interfaceBackMegDao
	 *            the interfaceBackMegDao to set
	 */
	public void setInterfaceBackMegDao(InterfaceBackMegDao interfaceBackMegDao) {
		this.interfaceBackMegDao = interfaceBackMegDao;
	}

	/**
	 * @return the allocateRegService
	 */
	public AllocateRegService getAllocateRegService() {
		return allocateRegService;
	}

	/**
	 * @param allocateRegService
	 *            the allocateRegService to set
	 */
	public void setAllocateRegService(AllocateRegService allocateRegService) {
		this.allocateRegService = allocateRegService;
	}

	/**
	 * @return the allocateRegWsIn
	 */
	public AllocateRegWsIn getAllocateRegWsIn() {
		return allocateRegWsIn;
	}

	/**
	 * @param allocateRegWsIn
	 *            the allocateRegWsIn to set
	 */
	public void setAllocateRegWsIn(AllocateRegWsIn allocateRegWsIn) {
		this.allocateRegWsIn = allocateRegWsIn;
	}

	/**
	 * @return the allocateRegWsInDao
	 */
	public AllocateRegWsInDao getAllocateRegWsInDao() {
		return allocateRegWsInDao;
	}

	/**
	 * @param allocateRegWsInDao
	 *            the allocateRegWsInDao to set
	 */
	public void setAllocateRegWsInDao(AllocateRegWsInDao allocateRegWsInDao) {
		this.allocateRegWsInDao = allocateRegWsInDao;
	}

	public ProjBaseDao getProjBaseDao() {
		return projBaseDao;
	}

	public void setProjBaseDao(ProjBaseDao projBaseDao) {
		this.projBaseDao = projBaseDao;
	}
	

}