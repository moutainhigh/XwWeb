package  app.creditapp.proj.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.proj.entity.ProjBase;
/**
* Title: ProjBaseDao.java
* Description:
**/
public interface ProjBaseDao {

	public ProjBase getById(ProjBase projBase) throws DAOException;
	
	public ProjBase getByIdForProjNo(ProjBase projBase) throws DAOException;
	
	public ProjBase getByIdForBrNo(ProjBase projBase) throws DAOException;
	
	public ProjBase getByIdForFlag(ProjBase projBase) throws DAOException;

	public void del(ProjBase projBase) throws DAOException;

	public void insert(ProjBase projBase) throws DAOException;

	public void merge(ProjBase projBase) throws DAOException;
	
	public void update(ProjBase projBase) throws DAOException;
	
	public void update_Read(ProjBase projBase) throws DAOException;
	
	public int getCount(ProjBase projBase) throws DAOException;
	public int getCountForUser(ProjBase projBase) throws DAOException;

	public List<ProjBase > findByPage(ProjBase projBase) throws DAOException;
	public List<ProjBase > findByPageForUser(ProjBase projBase) throws DAOException;
	public List<ProjBase > findByMyPage(ProjBase projBase) throws DAOException;
	//echart图显示添加
	public List<ProjBase > myProj_echarts(ProjBase projBase) throws DAOException;
	public List<ProjBase > myProj_day_echarts(ProjBase projBase) throws DAOException;	
	public List<ProjBase > myProj_fdtype_day_echarts(ProjBase projBase) throws DAOException;
	
	//更改项目状态
	public void updateSts(ProjBase projBase) throws DAOException;
	//同步时 更新 专户信息表 获取 该机构下的所有项目
	public List<ProjBase> findpageFormer(ProjBase projBase) throws DAOException;
	
/*	//合作机构管理360视图项目信息展示
	public List<ProjBase> getByIdforCorp(ProjBase projBase) throws DAOException;
	//获取符合条件brno的总数量进行分页处理   合作机构管理360视图项目信息展示
	public int getCountforCorp(ProjBase projBase) throws DAOException;
*/
}