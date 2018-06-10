package app.creditapp.bo;
import java.util.List;

import app.base.ServiceException;
import app.creditapp.entity.ParmDic;
import app.util.toolkit.Ipage;
public interface ParmDicBO {
	/**
	 * 获取对象
	 * @param parmdic
	 * @return
	 * @throws ServiceException
	 */
  public ParmDic getById(ParmDic parmdic) throws ServiceException;
  /**
	 * 获取数据字典中的OPT_NAME
	 * @param parmdic
	 * @return
	 * @throws ServiceException
	 */
  public String getOptName(ParmDic parmdic) throws ServiceException;
  /**
	 * 获取数据字典中的OPT_CODE
	 * @param parmdic
	 * @return
	 * @throws ServiceException
	 */
  public String getOptCode(ParmDic parmdic) throws ServiceException;
  /**
	 * 删除
	 * @param parmdic
	 * @return
	 * @throws ServiceException
	 */
  public void del(ParmDic parmdic) throws ServiceException;
  /**
	 * 删除
	 * @param keyname
	 * @return
	 * @throws ServiceException
	 */
  public void delte(String keyname) throws ServiceException;
  /**
	 * 新增
	 * @param parmdic
	 * @return
	 * @throws ServiceException
	 */
  public void Insert(ParmDic parmdic) throws ServiceException;
  /**
	 * 分页查询ParmDic
	 * @param ipg
	 * @param parmdic
	 * @return
	 * @throws ServiceException
	 */
  public Ipage findByPage(Ipage ipg,ParmDic parmdic) throws ServiceException;
  /**
	 * 修改
	 * @param parmdic
	 * @return
	 * @throws ServiceException
	 */
  public void Update(ParmDic parmdic) throws ServiceException;
  public   List<ParmDic> findlist(ParmDic parmDic)throws ServiceException; 
  /**
   * 
   * @param parmdic
   * @return  
   * @throws ServiceException
   * @desc
   * ParmDic 根据授信拆分额度类型查询授信额度拆分
   */
  public List<ParmDic> getBykeyname4AuthAmt(ParmDic parmdic) throws ServiceException;

}