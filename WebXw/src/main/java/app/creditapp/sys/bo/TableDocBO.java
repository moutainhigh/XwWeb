package app.creditapp.sys.bo;
import java.util.List;

import app.base.ServiceException;
import app.creditapp.sys.entity.TableDoc;
import app.creditapp.sysConfig.entity.TreeNode;
import app.util.toolkit.Ipage;
public interface TableDocBO {
  public TableDoc getById(String id) throws ServiceException;
  public void del(String id) throws ServiceException;
  public void insertOrUpdate(TableDoc tabledoc) throws ServiceException;
  public Ipage findByPage(Ipage ipg,TableDoc tabledoc) throws ServiceException;
  public List<TableDoc> getMkTblList() throws ServiceException;
  public void insertParmBatch(List<TreeNode> treeNodes) throws ServiceException;
/**
 * 返回所有菜单组成的字符串
 * @return
 */
public String getAllJsonMenu();
public void createdoc();
}