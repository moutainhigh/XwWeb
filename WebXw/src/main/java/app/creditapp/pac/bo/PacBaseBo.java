package app.creditapp.pac.bo;

import java.util.List;

import app.base.ServiceException;
import app.creditapp.pac.entity.PacBase;
import app.util.toolkit.Ipage;

/**
 * Title: PacBaseBo.java Description:
 * 
 **/
public interface PacBaseBo {

//    public PacBase getById(PacBase pacBase) throws ServiceException;
//
//    public void del(PacBase pacBase) throws ServiceException;

    public void insert(PacBase pacBase) throws ServiceException;

//    public void update(PacBase pacBase) throws ServiceException;
//
//    public Ipage findByPage(Ipage ipg, PacBase pacBase) throws ServiceException;
//
//    /**
//     * @方法说明: 按条件查询所有影像存储信息
//     * @param pacBase
//     * @return
//     * @return List<PacBase>
//     * @修改说明:
//     */
//    public List<PacBase> listPacBase(PacBase pacBase) throws ServiceException;
//
//    /**
//     * @方法说明: 更新下载次数
//     * @param pacId
//     * @return  void
//     * @修改说明:
//     */
//    public void updateDownCnt(String pacId) throws ServiceException;
//    
//    /**
//     * 获取最新的一条记录
//     * @param pacBase
//     * @return
//     * @throws ServiceException
//     */
//    public PacBase getLastPacBase(PacBase pacBase) throws ServiceException;
//    
//    //获取业务编号
//    public PacBase getBusiNo(String pac_type_no,String busi_no,String pacScene, PacBase pac) throws ServiceException;
//    
//    //获取角色所在场景对应影像的是否只读权限
//    public String getReadOnly(String role_no, String pacScene) throws ServiceException;
//    
//    public List<PacBase> getyn(PacBase pacBase) throws ServiceException;
//    
//    public int getCount(PacBase pacBase) throws ServiceException;

}