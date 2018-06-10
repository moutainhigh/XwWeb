package  app.creditapp.acc.loan.bo.impl;

import java.util.HashMap;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.acc.loan.bo.AcLnMstBo;
import app.creditapp.acc.loan.dao.AcLnMstDao;
import app.creditapp.acc.loan.entity.AcLnMst;
import app.creditapp.acc.loan.entity.AcLnMstFail;
import app.util.IbatisUtils;
import app.util.ruleFiter.fiter.FiterEngineInterface;
import app.util.toolkit.Ipage;
/**
* Title: AcLnMstBoImplImpl.java
* Description:
**/
public class AcLnMstBoImpl extends BaseService implements AcLnMstBo {

	private AcLnMstDao acLnMstDao;
	private FiterEngineInterface fiterEngineInterface;

	/**
	 * @作者 DHCC-仲凯旋
	 * @日期 July 27, 2016
	 * @方法说明：接口ws2105调用rule-WsIn2105.xml校验规则
	 * @返回参数 响应参数实体ResponseParm
	 */
//	public  ResponseParm validateWsIn(WsIn2105 wsIn2105) throws ServiceException{
//		ResponseParm responseParm = new ResponseParm();
//		responseParm.setRespCode("0000");
//		ValidateLog _vl;		
//		try {
//			_vl = fiterEngineInterface.createValidateLog("wsIn2105Id", wsIn2105, true);
//			if(!_vl.isSuccess()){ // 有问题
//				responseParm.setRespCode("0001");
//				responseParm.setRespDesc(_vl.getErrorMessage());
//			}else{
//				AcLnMst acLnMst = new AcLnMst();
//				acLnMst.setPactNo(wsIn2105.getPactNo());
//				int count = acLnMstDao.getCount(acLnMst);
//				if(count==0){
//					responseParm.setRespCode("1001");
//					responseParm.setRespDesc("合同号码为：  "+ wsIn2105.getPactNo() +"  的记录不存在！");
//				}					
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return responseParm; 
//	}
	//分页查询合同信息
	public Ipage findByPageforList(Ipage ipg, AcLnMst acLnMst) throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) acLnMstDao.getCountforlist(acLnMst) });// 初始化分页类
			HashMap<String, Object> map = (HashMap<String, Object>) IbatisUtils.getEntityPropertyMap(ipg, acLnMst);
			map.put("startNum", ipg.getStartRow());
			map.put("endNum", ipg.getEndNum());
			ipg.setResult(acLnMstDao.findByPageforlist(map));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	
	public int countforListWs(AcLnMst acLnMst) throws ServiceException{
		int count = 0;
		try {
			count = acLnMstDao.getCountforlist(acLnMst);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
		} 
	
	public void del(AcLnMst acLnMst) throws ServiceException {
		try {
			acLnMstDao.del(acLnMst);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, AcLnMst acLnMst)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) acLnMstDao
					.getCount(acLnMst) });// 初始化分页类
			acLnMst.setStartNumAndEndNum (ipg);
			ipg.setResult(acLnMstDao.findByPage(acLnMst));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	
		public Ipage findByPagedk(Ipage ipg, AcLnMst acLnMst)
				throws ServiceException {
			try {
					ipg.initPageCounts(new Integer[] { (Integer) acLnMstDao
							.getCountdk(acLnMst) });// 初始化分页类
					acLnMst.setStartNumAndEndNum (ipg);
					ipg.setResult(acLnMstDao.findByPagedk(acLnMst));
			} catch (Exception e) {
				throw new ServiceException(e.getMessage());
			}
			return ipg;
		}

	public AcLnMst getById(AcLnMst acLnMst) throws ServiceException {
		try {
			acLnMst = acLnMstDao.getById(acLnMst);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return acLnMst;
	}
	
	public AcLnMst getByPactNo(AcLnMst acLnMst) throws ServiceException {
		try {
			acLnMst = acLnMstDao.getByPactNo(acLnMst);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return acLnMst;
	}

	public void insert(AcLnMst acLnMst) throws ServiceException {
		try {
			acLnMstDao.insert(acLnMst);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(AcLnMst acLnMst) throws ServiceException {
		try {
			acLnMstDao.update(acLnMst);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	/**
	 * 放款失败分页查询
	 */
	public Ipage findByPageToLoanFail(Ipage ipg, AcLnMstFail acLnMstFail)
		throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) acLnMstDao
					.getCountToLoanFail(acLnMstFail) });// 初始化分页类
			acLnMstFail.setStartNumAndEndNum (ipg);
			ipg.setResult(acLnMstDao.findByPageToLoanFail(acLnMstFail));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	
	public AcLnMstDao getAcLnMstDao() {
		return acLnMstDao;
	}

	public void setAcLnMstDao(AcLnMstDao acLnMstDao) {
		this.acLnMstDao = acLnMstDao;
	}

	public FiterEngineInterface getFiterEngineInterface() {
		return fiterEngineInterface;
	}

	public void setFiterEngineInterface(FiterEngineInterface fiterEngineInterface) {
		this.fiterEngineInterface = fiterEngineInterface;
	}
}