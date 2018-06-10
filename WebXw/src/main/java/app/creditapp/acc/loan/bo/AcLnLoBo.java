package  app.creditapp.acc.loan.bo;

import java.util.List;

import app.base.ServiceException;
import app.creditapp.acc.loan.entity.AcLnLo;
import app.creditapp.inf.entity.ResponseParm;
import app.creditapp.inf.entity.WsIn3203;
import app.creditapp.inf.entity.WsOut3203_1;
import app.util.toolkit.Ipage;

/**
* Title: AcLnLoBo.java
* Description:
**/
public interface AcLnLoBo {

	public AcLnLo getById(AcLnLo acLnLo) throws ServiceException;
	
	public AcLnLo getLoAmt(AcLnLo acLnLo) throws ServiceException;

	public void del(AcLnLo acLnLo) throws ServiceException;

	public void insert(AcLnLo acLnLo) throws ServiceException;

	public void update(AcLnLo acLnLo) throws ServiceException;

	public Ipage findByPage(Ipage ipg, AcLnLo acLnLo) throws ServiceException;
	//接口ws3203校验
	public ResponseParm getresponseParm(WsIn3203 wsIn3203)throws ServiceException;
	//接口ws3203符合查找条件 返回分页信息
	public Ipage findByPageforws3203(Ipage ipg, AcLnLo acLnLo)throws ServiceException;
	//接口ws3203符合查找条件 返回总数数量
	public int  getCountforws3203(AcLnLo acLnLo) throws ServiceException;

	public AcLnLo getAcLnLoByCnt(AcLnLo acLnLo);

}