package  app.creditapp.acc.loan.dao;

import java.util.List;

import app.base.DAOException;
import app.creditapp.acc.loan.entity.AcLnLo;
import app.creditapp.inf.entity.WsOut3203_1;
/**
* Title: AcLnLoDao.java
* Description:
**/
public interface AcLnLoDao {

	public AcLnLo getById(AcLnLo acLnLo) throws DAOException;
	
	public AcLnLo getMinLnLo(AcLnLo acLnLo) throws DAOException;

	public void del(AcLnLo acLnLo) throws DAOException;

	public void insert(AcLnLo acLnLo) throws DAOException;

	public void update(AcLnLo acLnLo) throws DAOException;
	
	public int getCount(AcLnLo acLnLo) throws DAOException;

	public List<AcLnLo > findByPage(AcLnLo acLnLo) throws DAOException;
	
	public AcLnLo getLoAmt(AcLnLo acLnLo) throws DAOException;

	public List<AcLnLo > getListByLoanNo(AcLnLo acLnLo) throws DAOException;
	//接口ws3202查询返回列表
	public List<WsOut3203_1> findByPageforws3203(AcLnLo acLnLo) throws DAOException;
	//接口ws3202查询返回 符合条件的数量
	public int getCountfor3203(AcLnLo acLnLo) throws DAOException;
	//根据借据号 合同号 机构号取数据
	public List<AcLnLo> getByLoanPactBrNo(AcLnLo acLnLo) throws DAOException;

	public AcLnLo getAcLnLoByCnt(AcLnLo acLnLo);

}