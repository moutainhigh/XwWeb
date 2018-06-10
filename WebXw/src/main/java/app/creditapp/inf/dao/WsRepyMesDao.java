package app.creditapp.inf.dao;

import java.util.List;
import java.util.Map;

import app.base.DAOException;
import app.creditapp.inf.entity.WsOut2302_1;
import app.creditapp.inf.entity.WsRepyMes;
import app.creditapp.inf.entity.WsRepyMes_Count;

public interface WsRepyMesDao {

	public WsRepyMes getById(WsRepyMes wsRepyMes) throws DAOException;

	public void delete(WsRepyMes wsRepyMes) throws DAOException;

	public String insert(WsRepyMes wsRepyMes) throws DAOException;
	
	public void insertBatch(final List<WsRepyMes> wsRepyMesList) throws DAOException;

	public void update(WsRepyMes wsRepyMes) throws DAOException;
	
	public void updateSts(WsRepyMes wsRepyMes) throws DAOException;

	public int getCount(WsRepyMes wsRepyMes) throws DAOException;

	public List<WsRepyMes> findByPage(Map map) throws DAOException;
	
	public List<WsRepyMes> findByAll(Map map) throws DAOException;
	//接口ws2302符合条件返回列表
	public List<WsOut2302_1> findByPageforws2302(WsRepyMes wsRepyMes) throws DAOException;
	//接口ws2302符合条件返回个数
	public int getCountforws2302(WsRepyMes wsRepyMes) throws DAOException;
	//接口ws2301详细表删除相同合同号的数据
	public void deleteForPactNo() throws DAOException;
	//根据批次返回列表
	public List<WsRepyMes> getByBatch(String wsRepyMesBatch) throws DAOException;
	
	public WsRepyMes getByBatchAndPactNo(WsRepyMes wsRepyMes) throws DAOException;
	//单笔查询 ，返回实体类
	public WsOut2302_1 getByIdForNew(WsRepyMes wsRepyMes) throws DAOException;

	//扣款信息统计
	public int getMesCount(WsRepyMes_Count wc) throws DAOException;
	public List<WsRepyMes_Count> getCountMes(Map map) throws DAOException;
}
