package  app.creditapp.inf.bo.impl;

import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.inf.bo.WsRedctnBo;
import app.creditapp.inf.dao.WsRedctnDao;
import app.creditapp.inf.entity.ResponseParm;
import app.creditapp.inf.entity.WsIn2902;
import app.creditapp.inf.entity.WsOut2902_1;
import app.creditapp.inf.entity.WsRedctn;
import app.creditapp.ln.entity.PreApply;
import app.util.ruleFiter.entity.ValidateLog;
import app.util.ruleFiter.fiter.FiterEngineInterface;
import app.util.toolkit.Ipage;
/**
* Title: WsRedctnBoImplImpl.java
* Description:
**/
public class WsRedctnBoImpl extends BaseService implements WsRedctnBo {

	private WsRedctnDao wsRedctnDao;
	private FiterEngineInterface filterEngineInterface;
	
	public void del(WsRedctn wsRedctn) throws ServiceException {
		try {
			wsRedctnDao.del(wsRedctn);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, WsRedctn wsRedctn)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) wsRedctnDao
					.getCount(wsRedctn) });// 初始化分页类
			wsRedctn.setStartNumAndEndNum (ipg);
			ipg.setResult(wsRedctnDao.findByPage(wsRedctn));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public WsRedctn getById(WsRedctn wsRedctn) throws ServiceException {
		try {
			wsRedctn = wsRedctnDao.getById(wsRedctn);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return wsRedctn;
	}

	public void insert(WsRedctn wsRedctn) throws ServiceException {
		try {
			wsRedctnDao.insert(wsRedctn);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(WsRedctn wsRedctn) throws ServiceException {
		try {
			wsRedctnDao.update(wsRedctn);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	//接口ws2902校验
	public ResponseParm getresponse(WsIn2902 wsIn2902)throws ServiceException{
		ResponseParm responseParm = new ResponseParm();
		ValidateLog _vlws2902;
		responseParm.setRespCode("0000");
		responseParm.setRespDesc("校验完成，输入正确");
		try {
			_vlws2902 = filterEngineInterface.createValidateLog("wsIn2902Id", wsIn2902,true);
			
			if(!_vlws2902.isSuccess()){
				responseParm.setRespCode("9004");
				responseParm.setRespDesc(_vlws2902.getErrorMessage());
			}else{
				WsRedctn wsIn2902forsearch = new WsRedctn();
				String batchNo = wsIn2902.getBatNo();
				String pactNo = wsIn2902.getPactNo();
				wsIn2902forsearch.setBatchNo(batchNo);
				int count = wsRedctnDao.getCountforws2902(wsIn2902forsearch);
				//判断是否存在该批次号
				if(count == 0){
					responseParm.setRespCode("2990");
					responseParm.setRespDesc("批次号为： "+batchNo+"  的记录不存在！");
				}else{
					wsIn2902forsearch.setPactNo(pactNo);
					int countforPactNo = wsRedctnDao.getCountforws2902(wsIn2902forsearch);
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
	//接口ws2902分页查询实现方法
	public Ipage findByPageforws2902(Ipage ipg,WsRedctn wsRedctn)throws ServiceException{
		try {
			ipg.initPageCounts(new Integer[] { (Integer) wsRedctnDao.getCountforws2902(wsRedctn) });// 初始化分页类
			wsRedctn.setStartNumAndEndNum(ipg);
			List<WsOut2902_1> wsOut2902_1list = wsRedctnDao.findByPageforws2902(wsRedctn);
			//获取分页后的列表，完成分页操作
			ipg.setResult(wsOut2902_1list);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	return ipg;
	}
	//接口ws2902获取数据
	public int getCountforws2902(WsRedctn wsRedctn) throws ServiceException{
		int count = 0;
		try {
			count = wsRedctnDao.getCountforws2902(wsRedctn);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return count;
	}

	public WsRedctnDao getWsRedctnDao() {
		return wsRedctnDao;
	}

	public void setWsRedctnDao(WsRedctnDao wsRedctnDao) {
		this.wsRedctnDao = wsRedctnDao;
	}

	public FiterEngineInterface getFilterEngineInterface() {
		return filterEngineInterface;
	}

	public void setFilterEngineInterface(FiterEngineInterface filterEngineInterface) {
		this.filterEngineInterface = filterEngineInterface;
	}
	
	
}