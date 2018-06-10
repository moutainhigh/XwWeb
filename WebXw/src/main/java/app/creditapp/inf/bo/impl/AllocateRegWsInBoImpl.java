package app.creditapp.inf.bo.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.corp.dao.CorpParmDao;
import app.creditapp.corp.entity.CorpParm;
import app.creditapp.inf.bo.AllocateRegWsInBo;
import app.creditapp.inf.client.AllocateRegService;
import app.creditapp.inf.dao.AllocateRegWsInDao;
import app.creditapp.inf.dao.WsBaseDao;
import app.creditapp.inf.entity.AllocateRegWsIn;
import app.creditapp.inf.entity.DetailTransactionCommonDTO;
import app.creditapp.inf.entity.MainTransactionCommonDTO;
import app.creditapp.inf.entity.ReqData;
import app.creditapp.inf.entity.ResData;
import app.creditapp.inf.entity.TaState;
import app.creditapp.inf.entity.WsBase;
import app.creditapp.proj.dao.ProjBaseDao;
import app.creditapp.proj.entity.ProjBase;
import app.util.User;
import app.util.toolkit.Ipage;

import com.alibaba.fastjson.JSON;

/**
 * Title: AllocateRegWsInBoImplImpl.java Description:
 * 
 **/
public class AllocateRegWsInBoImpl extends BaseService implements AllocateRegWsInBo {

	private AllocateRegWsInDao allocateRegWsInDao;
	private ProjBaseDao projBaseDao;
	private CorpParmDao corpParmDao;
	private AllocateRegService allocateRegService;
	private WsBaseDao wsBaseDao;

	public void del(AllocateRegWsIn allocateRegWsIn) throws ServiceException {
		try {
			allocateRegWsInDao.del(allocateRegWsIn);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, AllocateRegWsIn allocateRegWsIn) throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) allocateRegWsInDao.getCount(allocateRegWsIn) });// 初始化分页类
			allocateRegWsIn.setStartNumAndEndNum(ipg);
			ipg.setResult(allocateRegWsInDao.findByPage(allocateRegWsIn));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public AllocateRegWsIn getById(AllocateRegWsIn allocateRegWsIn) throws ServiceException {
		try {
			allocateRegWsIn = allocateRegWsInDao.getById(allocateRegWsIn);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return allocateRegWsIn;
	}

	public void insert(AllocateRegWsIn allocateRegWsIn) throws ServiceException {
		try {
			allocateRegWsInDao.insert(allocateRegWsIn);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public String getByid(AllocateRegWsIn allocateRegWsIn) throws ServiceException {
		String id = "";
		try {
			id = allocateRegWsInDao.getByid();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return id;
	}

	public void update(AllocateRegWsIn allocateRegWsIn) throws ServiceException {
		try {
			allocateRegWsInDao.update(allocateRegWsIn);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public ResData sendTA(String id) throws ServiceException {
		AllocateRegWsIn allocateRegWsIn = new AllocateRegWsIn();
		allocateRegWsIn.setId(id);
		ReqData reqData = new ReqData();
		MainTransactionCommonDTO mainTransactionCommonDTO = new MainTransactionCommonDTO();
		List<DetailTransactionCommonDTO> detailTransactionCommonDTOList = new ArrayList<DetailTransactionCommonDTO>();
		ResData resData = null;
		try {
			allocateRegWsIn = this.getById(allocateRegWsIn);
			reqData.setBusinessFlag(allocateRegWsIn.getBusinessflag());// 业务类型
			reqData.setAutoSyncFlag(allocateRegWsIn.getAutoSyncFlag());// 业务类型
			// 主信息
			mainTransactionCommonDTO.setId(allocateRegWsIn.getId());
			mainTransactionCommonDTO.setProjectID(allocateRegWsIn.getProjectid());
			mainTransactionCommonDTO.setDdType("");
			mainTransactionCommonDTO.setTransDate(allocateRegWsIn.getTransdate());
			mainTransactionCommonDTO.setTransReason(allocateRegWsIn.getTransreason());
			mainTransactionCommonDTO.setPurpose(allocateRegWsIn.getPurpose());
			mainTransactionCommonDTO.setMemo(allocateRegWsIn.getMemo());
//			mainTransactionCommonDTO.setUserCode("zhangluting");
			mainTransactionCommonDTO.setUserCode(allocateRegWsIn.getUserCode());
			mainTransactionCommonDTO.setStaffApp(allocateRegWsIn.getStaffapp());
			reqData.setMainTransactionCommonDTO(mainTransactionCommonDTO);
			// 辅助信息
			DetailTransactionCommonDTO detailTransactionCommonDTO = new DetailTransactionCommonDTO();
			detailTransactionCommonDTO.setId(allocateRegWsIn.getId());
			detailTransactionCommonDTO.setAmount(allocateRegWsIn.getAmount());
			detailTransactionCommonDTO.setBkTransTypeCode(allocateRegWsIn.getBktranstypecode());
			detailTransactionCommonDTO.setSkTransTypeCode(allocateRegWsIn.getSktranstypecode());
			detailTransactionCommonDTO.setFeeTypeCode(allocateRegWsIn.getFeetypecode());
			detailTransactionCommonDTO.setTaxTypeCode(allocateRegWsIn.getTaxtypecode());
			detailTransactionCommonDTO.setCustomID(allocateRegWsIn.getCustomid());
			detailTransactionCommonDTO.setAccountID(allocateRegWsIn.getAccountid());
			detailTransactionCommonDTO.setOpBankAcntNO(allocateRegWsIn.getOpbankacntno());
			detailTransactionCommonDTO.setOpBankName(allocateRegWsIn.getOpbankname());
			detailTransactionCommonDTO.setOpBankAcntName(allocateRegWsIn.getOpbankacntname());
			detailTransactionCommonDTO.setOpBankProvince(allocateRegWsIn.getOpbankprovince());
			detailTransactionCommonDTO.setOpBankCity(allocateRegWsIn.getOpbankcity());
			detailTransactionCommonDTO.setRepayTypeID(allocateRegWsIn.getRepaytypeid());
			detailTransactionCommonDTO.setAppUserCode(allocateRegWsIn.getAppusercode());
			// 核算信息
			detailTransactionCommonDTO.setPersonnel(allocateRegWsIn.getPersonnel());
			detailTransactionCommonDTO.setProject(allocateRegWsIn.getProject());// 项目名称
			ProjBase projBase = new ProjBase();
			projBase.setProjNo(allocateRegWsIn.getProjectid());
			projBase = projBaseDao.getById(projBase);
			CorpParm corpParm = new CorpParm();
			corpParm.setBrNo(projBase.getBrNo());
			corpParm = corpParmDao.getById(corpParm);
			detailTransactionCommonDTO.setCustomer(corpParm.getTaName());//
			detailTransactionCommonDTO.setCashFlow(allocateRegWsIn.getCashflow());
			detailTransactionCommonDTO.setVirtualAccount(allocateRegWsIn.getVirtualaccount());
			detailTransactionCommonDTO.setContractNumber(allocateRegWsIn.getContractnumber());
			detailTransactionCommonDTO.setStockCode(allocateRegWsIn.getStockcode());
			detailTransactionCommonDTO.setFundAccount(allocateRegWsIn.getFundaccount());
			detailTransactionCommonDTO.setSalesDepartment(allocateRegWsIn.getSalesdepartment());
			detailTransactionCommonDTO.setCheckNumber(allocateRegWsIn.getChecknumber());
			detailTransactionCommonDTO.setBusinessType(allocateRegWsIn.getBusinesstype());
			detailTransactionCommonDTO.setCashFlowStatement(allocateRegWsIn.getCashflowstatement());
			detailTransactionCommonDTO.setTaxCategory(allocateRegWsIn.getTaxcategory());
			detailTransactionCommonDTO.setInterestRate(allocateRegWsIn.getInterestrate());
			detailTransactionCommonDTO.setIndustry(allocateRegWsIn.getIndustry());// 行业
			detailTransactionCommonDTO.setStock(allocateRegWsIn.getStock());
			detailTransactionCommonDTO.setAssetName(allocateRegWsIn.getAssetname());
			detailTransactionCommonDTO.setProductName(allocateRegWsIn.getProductname());
			detailTransactionCommonDTO.setClientOrBeneficiary(allocateRegWsIn.getClientorbeneficiary());
			detailTransactionCommonDTO.setShareholder(allocateRegWsIn.getShareholder());
			detailTransactionCommonDTO.setEntrustContractNumber(allocateRegWsIn.getEntrustcontractnumber());
			detailTransactionCommonDTO.setTradingPurpose(allocateRegWsIn.getTradingpurpose());
			detailTransactionCommonDTO.setContractVariety(allocateRegWsIn.getContractvariety());
			detailTransactionCommonDTO.setDateOfReceipt(allocateRegWsIn.getDateofreceipt());
			detailTransactionCommonDTO.setInterestDate(allocateRegWsIn.getInterestdate());
			detailTransactionCommonDTO.setDueDate(allocateRegWsIn.getDuedate());
			detailTransactionCommonDTO.setInOrOut(allocateRegWsIn.getInorout());
			detailTransactionCommonDTO.setBillNumber(allocateRegWsIn.getBillnumber());
			detailTransactionCommonDTOList.add(detailTransactionCommonDTO);

			reqData.setDetailTransactionCommonDTOList(detailTransactionCommonDTOList);

			String reqdDataStr = JSON.toJSONString(reqData);
			reqdDataStr = reqdDataStr.replace("detailTransactionCommonDTOList", "DetailTransactionCommonDTOList");
			reqdDataStr = reqdDataStr.replace("mainTransactionCommonDTO", "MainTransactionCommonDTO");
			System.out.println("TASEND=" + reqdDataStr);
			String resDataStr = allocateRegService.saveActTransactionInfos(reqdDataStr);
			System.out.println("TARECV=" + resDataStr);
			resData = JSON.parseObject(resDataStr, ResData.class);
			//往通讯信息表中插入 发送到软通接口和返回的json 
			WsBase wsBase = new WsBase();
			Date date = new Date(System.currentTimeMillis());
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd HH:dd:ss"); 
			wsBase.setWsDate(df.format(date).substring(0,6));
			wsBase.setWsTime(df.format(date));
			wsBase.setTxCode("");
			wsBase.setRespDesc("收款发送");
			wsBase.setWsSts("01");
			wsBase.setReqContent(reqdDataStr);
			wsBase.setRespContent(resDataStr);
			wsBase.setBrNo(projBase.getBrNo());
			wsBaseDao.insert(wsBase);
			
			// Status 1:成功 -1:失败
			if ("1".equals(resData.getStatus())) {
				allocateRegWsIn.setTransid(resData.getTransID());// 拨款主ID
				allocateRegWsIn.setWssts("1");
				allocateRegWsIn.setCustomer(corpParm.getTaName());
				allocateRegWsIn.setSdtime(User.gethnTime());
				allocateRegWsIn.setRemarks("发送成功");
			} else {
				allocateRegWsIn.setWssts("2");
				allocateRegWsIn.setSdtime(User.gethnTime());
				String msg = resData.getErrorCode() + "--CopeType=" + resData.getErrorCopeType();
				if (msg.length() >= 100) {
					msg = msg.substring(0, 90);
				}
				allocateRegWsIn.setRemarks(msg);
			}
			this.update(allocateRegWsIn);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return resData;
	}

	@Override
	public TaState searchTA(String id) throws ServiceException {
		AllocateRegWsIn allocateRegWsIn = new AllocateRegWsIn();
		allocateRegWsIn.setId(id);
		TaState taState = new TaState();
		try {
			allocateRegWsIn = this.getById(allocateRegWsIn);
			if (!"".equals(allocateRegWsIn.getTransid())) {
				String reqdDataStr = "{\"transActionIds\": \"" + allocateRegWsIn.getTransid() + "\"}";
				String resDataStr = allocateRegService.getActTransactionState(reqdDataStr);
				List<TaState> taStateList = JSON.parseArray(resDataStr, TaState.class);// arse.parseObject(resDataStr,
																						// TaState.class);
				if (taStateList != null && taStateList.size() >= 1) {
					taState = taStateList.get(0);
				}
				if ("0".equals(taState.getState())) {
					allocateRegWsIn.setRemarks(taState.getRemarks());
					allocateRegWsIn.setWorkflowstate(taState.getWorkflowState());
					this.update(allocateRegWsIn);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return taState;
	}

	public AllocateRegWsInDao getAllocateRegWsInDao() {
		return allocateRegWsInDao;
	}

	public void setAllocateRegWsInDao(AllocateRegWsInDao allocateRegWsInDao) {
		this.allocateRegWsInDao = allocateRegWsInDao;
	}

	public AllocateRegService getAllocateRegService() {
		return allocateRegService;
	}

	public void setAllocateRegService(AllocateRegService allocateRegService) {
		this.allocateRegService = allocateRegService;
	}

	public ProjBaseDao getProjBaseDao() {
		return projBaseDao;
	}

	public void setProjBaseDao(ProjBaseDao projBaseDao) {
		this.projBaseDao = projBaseDao;
	}

	public CorpParmDao getCorpParmDao() {
		return corpParmDao;
	}

	public void setCorpParmDao(CorpParmDao corpParmDao) {
		this.corpParmDao = corpParmDao;
	}

	public WsBaseDao getWsBaseDao() {
		return wsBaseDao;
	}

	public void setWsBaseDao(WsBaseDao wsBaseDao) {
		this.wsBaseDao = wsBaseDao;
	}

}