package  app.creditapp.inf.bo.impl;

import java.util.List;

import app.base.BaseService;
import app.base.DAOException;
import app.base.ServiceException;
import app.creditapp.inf.bo.WsRepyPlanBo;
import app.creditapp.inf.dao.WsRepyPlanDao;
import app.creditapp.inf.entity.ResponseParm;
import app.creditapp.inf.entity.WsIn2202;
import app.creditapp.inf.entity.WsOut2202;
import app.creditapp.inf.entity.WsRepyPlan;
import app.util.ruleFiter.entity.ValidateLog;
import app.util.ruleFiter.fiter.FiterEngineInterface;
import app.util.toolkit.Ipage;
/**
* Title: WsRepyPlanBoImplImpl.java
* Description:
**/
public class WsRepyPlanBoImpl extends BaseService implements WsRepyPlanBo {

	private WsRepyPlanDao wsRepyPlanDao;
	private FiterEngineInterface filterEngineInterface;

	public void del(WsRepyPlan wsRepyPlan) throws ServiceException {
		try {
			wsRepyPlanDao.del(wsRepyPlan);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, WsRepyPlan wsRepyPlan)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) wsRepyPlanDao
					.getCount(wsRepyPlan) });// 初始化分页类
			wsRepyPlan.setStartNumAndEndNum (ipg);
			ipg.setResult(wsRepyPlanDao.findByPage(wsRepyPlan));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	//同一个合同中的每期应还本金之和 PRCP_AMT
	
	public double getPrcpAmtCount(WsRepyPlan wsRepyPlan)throws  ServiceException {
		double count = 0;
		try {
			count =  wsRepyPlanDao.getPrcpAmtCount(wsRepyPlan);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return count;
	}
	
	public WsRepyPlan getById(WsRepyPlan wsRepyPlan) throws ServiceException {
		try {
			wsRepyPlan = wsRepyPlanDao.getById(wsRepyPlan);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return wsRepyPlan;
	}
	

	public void insert(WsRepyPlan wsRepyPlan) throws ServiceException {
		try {
			wsRepyPlanDao.insert(wsRepyPlan);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(WsRepyPlan wsRepyPlan) throws ServiceException {
		try {
			wsRepyPlanDao.update(wsRepyPlan);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	/**
	 * @作者 DHCC-ZKX
	 * @日期 July 20, 2016
	 * @方法说明：接口ws2202调用rule-WsIn2202.xml校验规则
	 * @返回参数 响应参数实体ResponseParm
	 */
	public ResponseParm validateForWs(WsIn2202 wsIn2202) throws ServiceException{
		ResponseParm responseParm = new ResponseParm();
		responseParm.setRespCode("0000");
		ValidateLog _vl;
		try {
			_vl = filterEngineInterface.createValidateLog("wsIn2202Id", wsIn2202, true);	
			if(!_vl.isSuccess()){
				responseParm.setRespCode("9004");
				responseParm.setRespDesc(_vl.getErrorMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParm; 
	}
	
	/**
	 * @作者 DHCC-ZKX
	 * @日期 July 20, 2016
	 * @方法说明：还款计划查询，从数据库表中获取数据
	 * @返回参数 响应参数实体WsOut2202
	 */
	@Override
	public List<WsOut2202> findByWsIn(WsIn2202 wsIn2202) throws ServiceException {
		List<WsOut2202> wsOut2202 = wsRepyPlanDao.findByWsIn(wsIn2202);
		return wsOut2202;
	}

	
	
	public WsRepyPlanDao getWsRepyPlanDao() {
		return wsRepyPlanDao;
	}

	public void setWsRepyPlanDao(WsRepyPlanDao wsRepyPlanDao) {
		this.wsRepyPlanDao = wsRepyPlanDao;
	}

	public FiterEngineInterface getFilterEngineInterface() {
		return filterEngineInterface;
	}

	public void setFilterEngineInterface(FiterEngineInterface filterEngineInterface) {
		this.filterEngineInterface = filterEngineInterface;
	}
}