package  app.creditapp.dev.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.dev.entity.TreeConf;
/**
* Title: TreeConfDao.java
* Description:
**/
public interface TreeConfDao {

	public TreeConf getById(TreeConf treeConf) throws DAOException;

	public void del(TreeConf treeConf) throws DAOException;

	public void insert(TreeConf treeConf) throws DAOException;

	public void update(TreeConf treeConf) throws DAOException;
	
	public int getCount(TreeConf treeConf) throws DAOException;

	public List<TreeConf > findByPage(TreeConf treeConf) throws DAOException;

}