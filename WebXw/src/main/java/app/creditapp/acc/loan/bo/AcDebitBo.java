package  app.creditapp.acc.loan.bo;

import java.util.List;

import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.acc.loan.entity.AcDebit;
import app.creditapp.inf.entity.ResponseParm;
import app.creditapp.inf.entity.WsIn2305;

/**
* Title: AcDebitBo.java
* Description:
**/
public interface AcDebitBo {

	public AcDebit getById(AcDebit acDebit) throws ServiceException;

	public AcDebit getByDebitNo(AcDebit acDebit) throws ServiceException;

	public void del(AcDebit acDebit) throws ServiceException;

	public void insert(AcDebit acDebit) throws ServiceException;

	public void update(AcDebit acDebit) throws ServiceException;

	public Ipage findByPage(Ipage ipg, AcDebit acDebit) throws ServiceException;
	
	public List<AcDebit> timDebit() throws ServiceException;
	//接口ws2305校验
	public ResponseParm getresponse(WsIn2305 wsIn2305)throws ServiceException;
	//接口ws2302分页查询实现方法
	public Ipage findByPageforws2305(Ipage ipg, AcDebit acDebit)throws ServiceException;
	//接口ws2305获取符合条件的数量
	public int getCountforws2305(AcDebit acDebit) throws ServiceException;

}