package  app.creditapp.cif.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.cif.entity.CifPortrait;
/**
* Title: CifPortraitDao.java
* Description:
**/
public interface CifPortraitDao {

	public CifPortrait getById(CifPortrait cifPortrait) throws DAOException;

	public void del(CifPortrait cifPortrait) throws DAOException;

	public void insert(CifPortrait cifPortrait) throws DAOException;

	public void update(CifPortrait cifPortrait) throws DAOException;
	
	public int getCount(CifPortrait cifPortrait) throws DAOException;

	public List<CifPortrait > findByPage(CifPortrait cifPortrait) throws DAOException;
	//查询客户类型
//	public String getCifType(CifPortrait cifPortrait) throws DAOException;
	//查询逾期次数
	public int getRepdNum(CifPortrait cifPortrait) throws DAOException;
	//查询同类客户百分比
	public double getPercent(CifPortrait cifPortrait) throws DAOException;
	//查询工作类型
	public String getWorkType(CifPortrait cifPortrait) throws DAOException ;
	//查询是否有车
	public String getIfCar(CifPortrait cifPortrait) throws DAOException ;
	//查询是否有房
	public String getIfRoom(CifPortrait cifPortrait) throws DAOException ;
	
	public int getCifBlackNum(CifPortrait cifPortrait) throws DAOException;
	
	public int getIfDC(CifPortrait cifPortrait) throws DAOException;
	
	public int getIfHG(CifPortrait cifPortrait) throws DAOException;
	
	public List<CifPortrait > getCifGroup(CifPortrait cifPortrait) throws DAOException;
	//按版本号和客户号查询最新版本的客户画像信息
	public CifPortrait getCifPortraitByVersion(String cifno,String version) throws DAOException;
	
	public String getMaxVersion(CifPortrait cifPortrait) throws DAOException;
}