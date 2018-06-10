package  app.creditapp.batch.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.batch.dao.BatchNodeDao;
import app.creditapp.batch.entity.BatchNode;
/**
* Title: BatchNodeDaoImpl.java
* Description:
**/
public class BatchNodeDaoImpl extends BaseIbatisDao implements BatchNodeDao {

	public void del(BatchNode batchNode) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("BatchNode.del", batchNode);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<BatchNode> findByPage(BatchNode batchNode) throws DAOException {
		List batchNodeList = null;
		try {
			batchNodeList = getSqlMapClientTemplate().queryForList(
					"BatchNode.findByPage", batchNode);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return batchNodeList;
	}

	public BatchNode getById(BatchNode batchNode) throws DAOException {
		try {
			batchNode = (BatchNode) getSqlMapClientTemplate()
					.queryForObject("BatchNode.getById", batchNode);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return batchNode;
	}

	public int getCount(BatchNode batchNode) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"BatchNode.findPage.count", batchNode);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(BatchNode batchNode) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("BatchNode.insert",
					batchNode);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(BatchNode batchNode) throws DAOException {
		try {
			getSqlMapClientTemplate().update("BatchNode.update",
					batchNode);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	
	public boolean isGroupRunning() throws DAOException {
		boolean isRunning = false; 
		int count = 0;
		BatchNode batchNode = new BatchNode();
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"BatchNode.isGroupRunning", batchNode);
			if (count>0) {
				isRunning = true;
			}
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return isRunning;
	}
	
	public List<BatchNode> getNoDepenTaskInfoList() throws DAOException {
		List batchNodeList = null;
		BatchNode batchNode = new BatchNode();
		try {
			batchNodeList = getSqlMapClientTemplate().queryForList(
					"BatchNode.getNoDepenTaskInfoList", batchNode);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return batchNodeList;
	}
	
	public List<BatchNode> listRules(String node_no) throws DAOException {
		List batchNodeList = null;
		BatchNode batchNode = new BatchNode();
		try {
			batchNode.setNode_no(node_no);
			batchNodeList = getSqlMapClientTemplate().queryForList("BatchNode.listRules", batchNode);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return batchNodeList;
	}
	
	public String getPreTaskState(String node_no) throws DAOException {
		String State = "";
		BatchNode batchNode = new BatchNode();
		try {
			batchNode.setNode_no(node_no);
			State = (String) getSqlMapClientTemplate().queryForObject(
					"BatchNode.getPreTaskState", batchNode);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return State;
	}
	
	public boolean getFatherState(String node_no) throws DAOException {
		boolean state = false;
		List batchNodeList = null;
		try {
			batchNodeList = getSqlMapClientTemplate().queryForList("BatchNode.getFatherState", node_no);
			if(batchNodeList.size()==0){
				state = true;
			}
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return state;
	}
}