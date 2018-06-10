package  app.creditapp.inf.bo.impl;

import java.util.HashMap;
import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.inf.bo.WsRepyMesBo;
import app.creditapp.inf.dao.WsRepyMesDao;
import app.creditapp.inf.entity.ResponseParm;
import app.creditapp.inf.entity.WsIn2302;
import app.creditapp.inf.entity.WsOut2302_1;
import app.creditapp.inf.entity.WsRepyMes;
import app.creditapp.inf.entity.WsRepyMes_Count;
import app.creditapp.ln.entity.PreApply;
import app.util.IbatisUtils;
import app.util.ruleFiter.entity.ValidateLog;
import app.util.ruleFiter.fiter.FiterEngineInterface;
import app.util.toolkit.Ipage;
/**
* Title: WsRepyMesBoImplImpl.java
* Description:
**/
public class WsRepyMesBoImpl extends BaseService implements WsRepyMesBo {

	private WsRepyMesDao wsRepyMesDao;
	private FiterEngineInterface filterEngineInterface;

	public void del(WsRepyMes wsRepyMes) throws ServiceException {
		try {
			wsRepyMesDao.delete(wsRepyMes);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, WsRepyMes wsRepyMes)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) wsRepyMesDao.getCount(wsRepyMes) });// 初始化分页类
			 HashMap<String,Object> map = (HashMap<String,Object>)IbatisUtils.getEntityPropertyMap(ipg, wsRepyMes);
			 map.put("startNum", ipg.getStartRow());
		      map.put("endNum", ipg.getEndNum());
		      ipg.setResult(wsRepyMesDao.findByPage(map)); 
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public WsOut2302_1 getByIdForNew(WsRepyMes wsRepyMes) throws ServiceException {
		WsOut2302_1 wsOut2302_1 = new WsOut2302_1();
		try {
			wsOut2302_1 = wsRepyMesDao.getByIdForNew(wsRepyMes);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return wsOut2302_1;
	}
	
	public WsRepyMes getById(WsRepyMes wsRepyMes) throws ServiceException {
		try {
			wsRepyMes = wsRepyMesDao.getById(wsRepyMes);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return wsRepyMes;
	}

	public void insert(WsRepyMes wsRepyMes) throws ServiceException {
		try {
			wsRepyMesDao.insert(wsRepyMes);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(WsRepyMes wsRepyMes) throws ServiceException {
		try {
			wsRepyMesDao.update(wsRepyMes);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	//接口ws2302校验
	public ResponseParm getresponse(WsIn2302 wsIn2302)throws ServiceException{
		ResponseParm responseParm = new ResponseParm();
		responseParm.setRespCode("0000");
		responseParm.setRespDesc("输入数据校验通过！");
		ValidateLog _vlws2302;
		try {
			_vlws2302 = filterEngineInterface.createValidateLog("wsIn2302Id", wsIn2302,true);
			if(!_vlws2302.isSuccess()){
				responseParm.setRespCode("9004");
				responseParm.setRespDesc("校验完成，输入数据格式存在错误"+_vlws2302.getErrorMessage());
			}else{
				WsRepyMes wsIn2302forsearch = new WsRepyMes();
				String batchNo = wsIn2302.getBatNo();
				String pactNo = wsIn2302.getPactNo();
				wsIn2302forsearch.setBatchNo(batchNo);
				int count = wsRepyMesDao.getCountforws2302(wsIn2302forsearch);
				//判断是否存在该批次号
				if(count == 0){
					responseParm.setRespCode("2990");
					responseParm.setRespDesc("批次号为： "+batchNo+"  的记录不存在！");
				}else{
					wsIn2302forsearch.setPactNo(pactNo);
					int countforPactNo = wsRepyMesDao.getCount(wsIn2302forsearch);
					if(countforPactNo == 0){
						responseParm.setRespCode("1001");
						responseParm.setRespDesc("合同号为：  "+ pactNo +"  的记录不存在！");
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParm;
	}
	//接口ws2302分页查询实现方法
	public Ipage findByPageforws2302(Ipage ipg, WsRepyMes wsRepyMes)throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) wsRepyMesDao.getCountforws2302(wsRepyMes) });// 初始化分页类
			wsRepyMes.setStartNumAndEndNum(ipg);
			List<WsOut2302_1> wsOut2301_1list = wsRepyMesDao.findByPageforws2302(wsRepyMes);
			WsOut2302_1 wsOut2302_1 = new WsOut2302_1();
			
			for(int i=0;i<wsOut2301_1list.size();i++){
				wsOut2302_1 = wsOut2301_1list.get(i);
				String dealSts = wsOut2302_1.getDealSts();
				//非 处理成功 将实扣信息 金额 进行置0
				if(!"03".equals(dealSts)){
					wsOut2301_1list.get(i).setFeeTotal(0);
					wsOut2301_1list.get(i).setRepayAmt(0);
					wsOut2301_1list.get(i).setRepayInte(0);
					wsOut2301_1list.get(i).setRepayOver(0);
					wsOut2301_1list.get(i).setRepayTotal(0);
					if(wsOut2301_1list.get(i).getDealDesc()==null){
						wsOut2301_1list.get(i).setDealDesc("");
					}else{
						wsOut2301_1list.get(i).setDealDesc(wsOut2301_1list.get(i).getDealDesc());
					}
					if(dealSts==null){
						wsOut2301_1list.get(i).setDealSts("");	
					}else{
						wsOut2301_1list.get(i).setDealSts(dealSts);	
					}
				}
			}
			//获取分页后的列表，完成分页操作
			ipg.setResult(wsOut2301_1list);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	return ipg;
	}
	//接口ws2302获取数据
	public int getCountforws2302(WsRepyMes wsRepyMes) throws ServiceException {
		int count = 0;
		try {
			count = wsRepyMesDao.getCountforws2302(wsRepyMes);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return count;
	}
	//扣款信息统计
	public Ipage getCountMes(Ipage ipg, WsRepyMes_Count wc) throws ServiceException{
		try {
			ipg.initPageCounts(new Integer[] { (Integer) wsRepyMesDao.getMesCount(wc) });// 初始化分页类
			 HashMap<String,Object> map = (HashMap<String,Object>)IbatisUtils.getEntityPropertyMap(ipg, wc);
			 map.put("startNum", ipg.getStartRow());
		      map.put("endNum", ipg.getEndNum());
		      ipg.setResult(wsRepyMesDao.getCountMes(map)); 
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	
	public FiterEngineInterface getFilterEngineInterface() {
		return filterEngineInterface;
	}

	public void setFilterEngineInterface(FiterEngineInterface filterEngineInterface) {
		this.filterEngineInterface = filterEngineInterface;
	}

	public WsRepyMesDao getWsRepyMesDao() {
		return wsRepyMesDao;
	}

	public void setWsRepyMesDao(WsRepyMesDao wsRepyMesDao) {
		this.wsRepyMesDao = wsRepyMesDao;
	}
	
}