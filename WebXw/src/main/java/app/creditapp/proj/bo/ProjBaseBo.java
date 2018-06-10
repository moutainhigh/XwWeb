package  app.creditapp.proj.bo;


import java.util.List;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.proj.entity.ProjBase;

/**
* Title: ProjBaseBo.java
* Description:
**/
public interface ProjBaseBo {

	public ProjBase getById(ProjBase projBase) throws ServiceException;
	
	public ProjBase getByIdForProjNo(ProjBase projBase) throws ServiceException;
	
	public ProjBase getByIdForFlag(ProjBase projBase) throws ServiceException;

	public void del(ProjBase projBase) throws ServiceException;

	public void insert(ProjBase projBase) throws ServiceException;

	public void merge(ProjBase projBase) throws ServiceException;
	
	public String update(ProjBase projBase) throws ServiceException;
	
	public void update_Read(ProjBase projBase) throws ServiceException;

	public Ipage findByPage(Ipage ipg, ProjBase projBase) throws ServiceException;
	public Ipage findByPageForUser(Ipage ipg, ProjBase projBase) throws ServiceException;
	
	public Ipage findByMyPage(Ipage ipg, ProjBase projBase) throws ServiceException;
	//项目/规模
	public List<ProjBase> myProj_echarts(ProjBase projBase) throws ServiceException;
	//项目/每日业务量
	public List<ProjBase> myProj_day_echarts(ProjBase projBase) throws ServiceException;
	//项目中每日业务量-集合类/单一类
	public List<ProjBase> myProj_fdtype_day_echarts(ProjBase projBase) throws ServiceException;
	/*//合作机构管理360视图项目列表展示
	public Ipage findByPageforCorp(Ipage ipg, ProjBase projBase)throws ServiceException;*/
	
	//更改项目状态
	public void updateSts(ProjBase projBase) throws ServiceException;
	
	public List<ProjBase> getList(ProjBase projBase) throws ServiceException;
	//用于 5105 接口 合作机构 和 项目号 的校验
	public ProjBase getByIdForBrNo(ProjBase projBase) throws ServiceException;

}