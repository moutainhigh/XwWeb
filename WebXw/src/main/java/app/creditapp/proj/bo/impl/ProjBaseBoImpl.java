package  app.creditapp.proj.bo.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.corp.dao.CorpTaRelDao;
import app.creditapp.corp.entity.CorpTaRel;
import app.creditapp.corp.entity.CorpTaRelforcif;
//import app.creditapp.ln.worker.WorkUtils;
import app.creditapp.proj.bo.ProjBaseBo;
import app.creditapp.proj.dao.ProjBaseDao;
import app.creditapp.proj.dao.ProjMangDao;
import app.creditapp.proj.entity.ProjBase;
import app.util.toolkit.Ipage;
/**
* Title: ProjBaseBoImplImpl.java
* Description:
**/
public class ProjBaseBoImpl extends BaseService implements ProjBaseBo {

	private ProjBaseDao projBaseDao;
	private ProjMangDao projMangDao;
	private CorpTaRelDao corpTaRelDao;

	public void del(ProjBase projBase) throws ServiceException {
		try {
			projBaseDao.del(projBase);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public List<ProjBase> getList(ProjBase projBase) throws ServiceException {
		List  list = null;
		try {
			list = projBaseDao.findpageFormer(projBase);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return list;
	}

	public Ipage findByPage(Ipage ipg, ProjBase projBase)
	
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) projBaseDao
					.getCount(projBase) });// 初始化分页类
			projBase.setStartNumAndEndNum (ipg);
			ipg.setResult(projBaseDao.findByPage(projBase));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	public Ipage findByPageForUser(Ipage ipg, ProjBase projBase)
	
	throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) projBaseDao
					.getCountForUser(projBase) });// 初始化分页类
			projBase.setStartNumAndEndNum (ipg);
			ipg.setResult(projBaseDao.findByPageForUser(projBase));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	public Ipage findByMyPage(Ipage ipg, ProjBase projBase)
	
	        throws ServiceException {
        try { 	 
        	     
	          ipg.initPageCounts(new Integer[] { (Integer) projBaseDao
			      .getCount(projBase) });// 初始化分页类

	          projBase.setStartNumAndEndNum (ipg);
	          ipg.setResult(projBaseDao.findByMyPage(projBase));
        } catch (Exception e) {
	       throw new ServiceException(e.getMessage());
        }
        return ipg;
    }      
	public ProjBase getById(ProjBase projBase) throws ServiceException {
		try {
			projBase = projBaseDao.getById(projBase);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return projBase;
	}
	public ProjBase getByIdForProjNo(ProjBase projBase) throws ServiceException {
		try {
			projBase = projBaseDao.getByIdForProjNo(projBase);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return projBase;
	}
	//用于 5105 接口 合作机构 和 项目号 的校验
	public ProjBase getByIdForBrNo(ProjBase projBase) throws ServiceException {
		try {
			projBase = projBaseDao.getByIdForBrNo(projBase);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return projBase;
	}
	public ProjBase getByIdForFlag(ProjBase projBase) throws ServiceException {
		try {
			projBase = projBaseDao.getByIdForFlag(projBase);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return projBase;
	}
	public void insert(ProjBase projBase) throws ServiceException {
		try {
			projBaseDao.insert(projBase);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public void merge(ProjBase projBase) throws ServiceException {
		try {
			projBaseDao.merge(projBase);
//			WorkUtils.getInstance().proc_proj_acct();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public void update_Read(ProjBase projBase) throws ServiceException {
		try {
			projBaseDao.update_Read(projBase);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public String update(ProjBase projBase) throws ServiceException {
		CorpTaRel corpTaRel = new CorpTaRel();
		corpTaRel.setBrNo(projBase.getBrNo());
		corpTaRel.setProjNo(projBase.getProjNo());
		ProjBase projBaseForSearch = new ProjBase();
		String result = "0";
		try {
			//先进行判断，此项目编号下brNo是否存在，不存在表示这是一个新增的项目号，需要进行添加合作机构
			projBaseForSearch = projBaseDao.getById(projBase);
			String brNo = projBaseForSearch.getBrNo();
			if(brNo!=null && (!brNo.equals(projBase.getBrNo()))){
				corpTaRel.setBrNo(brNo);
				//根据brNo在corpTaRel查找所有的客户号
				List<CorpTaRel> list = corpTaRelDao.getCifNo(corpTaRel);
				//根据projNo在dblink中查找所有的客户号
				List<CorpTaRelforcif> listm = corpTaRelDao.getByBrNoForDb(corpTaRel);
				int listsize = list.size();
				int listsizem =  listm.size();
				String[] CorpTaRelc = new String[listsize];
				for(int i=0;i<listsize;i++){
					CorpTaRelc[i]= list.get(i).getTaCifNo();
				}
				//根据brNo在Corp_ta_rel表中将得到的客户号进行排序
				Arrays.sort(CorpTaRelc);
				String[] Listm = new String[listsizem];
				for(int i = 0;i<listsizem;i++){
					Listm[i] = listm.get(i).getTaCifNo();
				}
				//根据projNo在dblink表中将得到的客户号进行排序
				Arrays.sort(Listm);
				if(Arrays.equals(Listm, CorpTaRelc) && listsizem!=0){
					projBaseDao.update(projBase);
					//向corp_Ta_Rel插入数据
					corpTaRelDao.insertforCorp(corpTaRel);
					corpTaRelDao.deleteforDblink();
					//当数据全部能对应起来，那么进行存储和更新，返回结果为1
					result = "1";
				}
			}else{
				//当机构号为空的时候，那么就不进行校验，直接进行更新操作
				projBaseDao.update(projBase);
				//向corp_Ta_Rel插入数据
				//xbb 注释以下两行：因为无dblink连接导致报错，现系统无需跟业务系统进行交互
//				corpTaRelDao.insertforCorp(corpTaRel);
//				corpTaRelDao.deleteforDblink();
				//当数据全部能对应起来，那么进行存储和更新，返回结果为1
				result = "1";	
			}
			return result;
			
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		
	}
	//更改项目状态
	public void updateSts(ProjBase projBase) throws ServiceException {
		try {
			projBaseDao.updateSts(projBase);				
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public  List<ProjBase> myProj_echarts(ProjBase projBase) throws ServiceException {
		List projBaseList = null;
		try {
			projBaseList = projBaseDao.myProj_echarts(projBase);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return projBaseList;
	}
	public  List<ProjBase> myProj_day_echarts(ProjBase projBase) throws ServiceException {
		List projBaseList = null;
		try {
			projBaseList = projBaseDao.myProj_day_echarts(projBase);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return projBaseList;
	}
	public  List<ProjBase> myProj_fdtype_day_echarts(ProjBase projBase) throws ServiceException {
		List projBaseList = null;
		try {
			projBaseList = projBaseDao.myProj_fdtype_day_echarts(projBase);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return projBaseList;
	}
	/*//合作机构管理360视图项目列表展示
	public Ipage findByPageforCorp(Ipage ipg, ProjBase projBase)throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) projBaseDao.getCountforCorp(projBase)});// 初始化分页类
			projBase.setStartNumAndEndNum (ipg);
			ipg.setResult(projBaseDao.getByIdforCorp(projBase));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}*/
	public ProjBaseDao getProjBaseDao() {
		return projBaseDao;
	}

	public void setProjBaseDao(ProjBaseDao projBaseDao) {
		this.projBaseDao = projBaseDao;
	}

	public CorpTaRelDao getCorpTaRelDao() {
		return corpTaRelDao;
	}

	public void setCorpTaRelDao(CorpTaRelDao corpTaRelDao) {
		this.corpTaRelDao = corpTaRelDao;
	}

	public ProjMangDao getProjMangDao() {
		return projMangDao;
	}

	public void setProjMangDao(ProjMangDao projMangDao) {
		this.projMangDao = projMangDao;
	}
}