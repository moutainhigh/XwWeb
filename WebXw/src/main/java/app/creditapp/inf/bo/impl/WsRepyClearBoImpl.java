package  app.creditapp.inf.bo.impl;

import java.util.HashMap;
import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.inf.bo.WsRepyClearBo;
import app.creditapp.inf.dao.WsRepyClearDao;
import app.creditapp.inf.entity.ResponseParm;
import app.creditapp.inf.entity.WsIn2303;
import app.creditapp.inf.entity.WsIn2303_1;
import app.creditapp.inf.entity.WsIn2304;
import app.creditapp.inf.entity.WsOut2304;
import app.creditapp.inf.entity.WsRepyClear;
import app.util.IbatisUtils;
import app.util.ruleFiter.entity.ValidateLog;
import app.util.ruleFiter.fiter.FiterEngineInterface;
import app.util.toolkit.Ipage;
/**
* Title: WsRepyClearBoImplImpl.java
* Description:
**/
public class WsRepyClearBoImpl extends BaseService implements WsRepyClearBo {

	private WsRepyClearDao wsRepyClearDao;
	private FiterEngineInterface filterEngineInterface;

	public void del(WsRepyClear wsRepyClear) throws ServiceException {
		try {
			wsRepyClearDao.del(wsRepyClear);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, WsRepyClear wsRepyClear)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) wsRepyClearDao
					.getCount(wsRepyClear) });// 初始化分页类
			//			wsRepyClear.setStartNumAndEndNum (ipg);
			HashMap<String, Object> map = (HashMap<String, Object>) IbatisUtils
			.getEntityPropertyMap(ipg, wsRepyClear);
			map.put("startNum", ipg.getStartRow());
			map.put("endNum", ipg.getEndNum());
			ipg.setResult(wsRepyClearDao.findByPage(map));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public WsRepyClear getById(WsRepyClear wsRepyClear) throws ServiceException {
		try {
			wsRepyClear = wsRepyClearDao.getById(wsRepyClear);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return wsRepyClear;
	}

	public void insert(WsRepyClear wsRepyClear) throws ServiceException {
		try {
			wsRepyClearDao.insert(wsRepyClear);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(WsRepyClear wsRepyClear) throws ServiceException {
		try {
			wsRepyClearDao.update(wsRepyClear);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	//ws2303接口校验并进行存储
	public ResponseParm getresponseParm(WsIn2303 wsIn2303) throws ServiceException{
		ResponseParm responseParm = new ResponseParm();
		List<WsIn2303_1> wsIn2303_1list = null;
		WsIn2303_1 wsIn2303_1 = null;
		ValidateLog _vlws2303;
		responseParm.setRespCode("0000");
		try {
			_vlws2303 = filterEngineInterface.createValidateLog("wsIn2303Id", wsIn2303, true);
			if(_vlws2303.isSuccess()){
				wsIn2303_1list = wsIn2303.getListPayOver();
				if(wsIn2303_1list==null || "".equals(wsIn2303_1list)){
					//提前清贷中  不含有 罚息
				}else{
					//提前清贷中  含有 罚息
					int wsIn2303_1list_len = wsIn2303_1list.size();
					for(int j=0;j<wsIn2303_1list_len;j++){
						wsIn2303_1 = wsIn2303_1list.get(j);
						ValidateLog _vl_wsIn2303_1 = filterEngineInterface.createValidateLog("wsIn2303_1Id", wsIn2303_1, true);
						if(!_vl_wsIn2303_1.isSuccess()){
							//校验没有通过，设置校验码为0001
							responseParm.setRespCode("9004");
							//添加校验描述
							responseParm.setRespDesc("校验完成，输入数据格式存在错误！"+_vl_wsIn2303_1.getErrorMessage());
							break;
						}
					}
				}
			}else{
				//校验没有通过，设置校验码为0001
				responseParm.setRespCode("9004");
				//添加校验描述
				responseParm.setRespDesc("校验完成，输入数据格式存在错误！"+_vlws2303.getErrorMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParm;
	}
	//接口ws2303数据库存储
	public void insertforws2303(WsIn2303 wsIn2303) throws ServiceException{
		try {
			wsRepyClearDao.insertforws2303(wsIn2303);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	//接口ws2303重复合同号删除操作
	public void delforws2303(WsRepyClear wsRepyClear) throws ServiceException{
		try {
			wsRepyClearDao.delforws2303(wsRepyClear);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	//接口ws2304 存在的记录数
	public int  getCountFor2304(WsRepyClear wsRepyClear) throws ServiceException{
		int count = 0;
		try {
			count = wsRepyClearDao.getCountforws3204(wsRepyClear);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return count;
	}
	//ws2304接口校验
	public ResponseParm getresponseParmfor2304(WsIn2304 wsIn2304) throws ServiceException{
		ResponseParm responseParm = new ResponseParm();
		ValidateLog _vlws2304;
		//校验没有通过，设置校验码为0001
		responseParm.setRespCode("0000");
		responseParm.setRespDesc("信息查询成功！");
		try {
			_vlws2304 = filterEngineInterface.createValidateLog("wsIn2304Id", wsIn2304, true);
			if(!_vlws2304.isSuccess()){
				responseParm.setRespCode("9004");
				responseParm.setRespDesc(_vlws2304.getErrorMessage());
			}else{
				WsRepyClear wsIn2304forsearch = new WsRepyClear();
				String brNo = wsIn2304.getBrNo();
				String pactNo = wsIn2304.getPactNo();
				wsIn2304forsearch.setBrNo(brNo);
				int count = wsRepyClearDao.getCountforws3204(wsIn2304forsearch);
				//判断是否存在该批次号
				if(count == 0){
					responseParm.setRespCode("1004");
					responseParm.setRespDesc("合作机构号为： "+brNo+" 的记录不存在！");
				}else{
					wsIn2304forsearch.setPactNo(pactNo);
					int countforPactNo = wsRepyClearDao.getCountforws3204(wsIn2304forsearch);
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
	//接口ws2304根据合作结构号，合同号返回结果值
	public WsOut2304 getInfo(WsIn2304 wsIn2304) throws ServiceException{
		WsOut2304 wsOut2304 = new WsOut2304();
		try {
			wsOut2304 = wsRepyClearDao.getInfo(wsIn2304);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return wsOut2304;
	}
	//接口ws2304根据合作结构号，合同号返回结果值
	public WsOut2304 getInfoForNew(WsIn2304 wsIn2304) throws ServiceException{
		WsOut2304 wsOut2304 = new WsOut2304();
		try {
			wsOut2304 = wsRepyClearDao.getInfoForNew(wsIn2304);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return wsOut2304;
	}


	public WsRepyClearDao getWsRepyClearDao() {
		return wsRepyClearDao;
	}

	public void setWsRepyClearDao(WsRepyClearDao wsRepyClearDao) {
		this.wsRepyClearDao = wsRepyClearDao;
	}

	public FiterEngineInterface getFilterEngineInterface() {
		return filterEngineInterface;
	}

	public void setFilterEngineInterface(FiterEngineInterface filterEngineInterface) {
		this.filterEngineInterface = filterEngineInterface;
	}

	
	
}