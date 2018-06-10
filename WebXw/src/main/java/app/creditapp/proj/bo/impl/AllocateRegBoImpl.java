package  app.creditapp.proj.bo.impl;

import com.alibaba.fastjson.JSON;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.inf.client.AllocateRegService;
import app.creditapp.inf.entity.ReqData;
import app.creditapp.proj.bo.AllocateRegBo;
import app.creditapp.proj.dao.AllocateRegDao;
import app.creditapp.proj.entity.AllocateReg;
/**
* Title: AllocateRegBoImplImpl.java
* Description:
**/
public class AllocateRegBoImpl extends BaseService implements AllocateRegBo {

	private AllocateRegDao allocateRegDao;
	private AllocateRegService allocateRegService;

	public void del(AllocateReg allocateReg) throws ServiceException {
		try {
			allocateRegDao.del(allocateReg);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, AllocateReg allocateReg)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) allocateRegDao
					.getCount(allocateReg) });// 初始化分页类
			allocateReg.setStartNumAndEndNum (ipg);
			ipg.setResult(allocateRegDao.findByPage(allocateReg));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public AllocateReg getById(AllocateReg allocateReg) throws ServiceException {
		try {
			allocateReg = allocateRegDao.getById(allocateReg);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return allocateReg;
	}

	public void insert(AllocateReg allocateReg) throws ServiceException {
		try {
			allocateRegDao.insert(allocateReg);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(AllocateReg allocateReg) throws ServiceException {
		try {
			allocateRegDao.update(allocateReg);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public AllocateRegDao getAllocateRegDao() {
		return allocateRegDao;
	}

	public void setAllocateRegDao(AllocateRegDao allocateRegDao) {
		this.allocateRegDao = allocateRegDao;
	}
	
	
	//@Override
	public String allocateRegService() {
		String str = "";
		ReqData rd = new ReqData();
		
		//往rd set值
		
		String reqData = JSON.toJSONString(rd); //json转换
		str = allocateRegService.saveActTransactionInfos(reqData);
		
		return str;
	}

	/**
	 * @return the allocateRegService
	 */
	public AllocateRegService getAllocateRegService() {
		return allocateRegService;
	}

	/**
	 * @param allocateRegService the allocateRegService to set
	 */
	public void setAllocateRegService(AllocateRegService allocateRegService) {
		this.allocateRegService = allocateRegService;
	}
	
}