package app.creditapp.dao;
import java.util.List;
import java.util.Map;
import app.base.DAOException;
import app.base.ServiceException;
import app.creditapp.entity.ParmDic;
public interface ParmDicDAO {
	/**
	 * 条件查询
	 * @param parmdic
	 * @return
	 * @throws DAOException
	 */
  public ParmDic getById(ParmDic parmdic) throws DAOException;
	/**
	 * 查询OPT_NAME
	 * @param parmdic
	 * @return
	 * @throws DAOException
	 */
  public String getOptName(ParmDic parmdic) throws DAOException;
  /**
	 * 获取数据字典中的OPT_CODE
	 * @param parmdic
	 * @return
	 * @throws ServiceException
	 */
  public String getOptCode(ParmDic parmdic) throws DAOException;
  /**
	 * 条件删除
	 * @param parmdic
	 * @return
	 * @throws DAOException
	 */
  public void del(ParmDic parmdic) throws DAOException;
  /**
	 * 条件删除
	 * @param keyname
	 * @return
	 * @throws DAOException
	 */
  public void delte(String keyname) throws DAOException;
  /**
	 * 新增
	 * @param parmdic
	 * @return
	 * @throws DAOException
	 */
  public void insert(ParmDic parmdic) throws DAOException;
  /**
	 * 修改
	 * @param parmdic
	 * @return
	 * @throws DAOException
	 */
  public void update(ParmDic parmdic) throws DAOException;
  /**
	 * 获取ParmDic记录数
	 * @param parmdic
	 * @return
	 * @throws DAOException
	 */
  public int getCount(ParmDic parmdic) throws DAOException;
  /**
	 *Map集合
	 * @param map
	 * @return
	 * @throws DAOException
	 */
  public List<ParmDic> findByPage(Map map) throws DAOException;
  
  public List<ParmDic> findlist(ParmDic parmDic)throws DAOException;
  /**
   * 
   * @param parmdic
   * @return  
   * @throws ServiceException
   * @desc
   * ParmDic 根据授信拆分额度类型查询授信额度拆分
   */
  public List<ParmDic> getBykeyname4AuthAmt(ParmDic parmdic) throws DAOException;
  /**
   * 根据业务期限类型获取名称
   * @param busi_type
   * @return 
   * @throws DAOException
   */
  public String getBusiName(String busi_type) throws DAOException;

}