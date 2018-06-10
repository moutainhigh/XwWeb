package app.creditapp.sys.dao;

import java.sql.Connection;
import java.util.List;

import app.base.DAOException;
import app.creditapp.sys.entity.TableDoc;
import app.creditapp.sysConfig.entity.TreeNode;

public interface TableDocDAO {
	public TableDoc getById(String id) throws DAOException;

	public void del(String id) throws DAOException;

	public void insert(TableDoc tabledoc) throws DAOException;

	public void update(TableDoc tabledoc) throws DAOException;

	public int getCount(TableDoc tabledoc) throws DAOException;

	public List<TableDoc> findByPage(TableDoc tabledoc) throws DAOException;

	public List<TableDoc> getMkTblList() throws DAOException;

	public void insertParmBatch(List<TreeNode> treeNodes)
			throws DAOException;

	/**
	 * 获取所有的菜单
	 * 
	 * @param string
	 * @return
	 */
	public List<TableDoc> findAll(String string) throws DAOException;

	public void createdoc(Connection conn) throws Exception;
}