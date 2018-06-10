package  app.creditapp.wkf.bo.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.sys.entity.ProdBrno;
import app.creditapp.wkf.bo.AppWkfcfgBo;
import app.creditapp.wkf.dao.AppWkfcfgDao;
import app.creditapp.wkf.dao.WorkflowConfigCache;
import app.creditapp.wkf.entity.AppWkfcfg;
import app.util.toolkit.Ipage;
/**
* Title: AppWkfcfgBoImplImpl.java
* Description:
**/
public class AppWkfcfgBoImpl extends BaseService implements AppWkfcfgBo {

	private AppWkfcfgDao appWkfcfgDao;


	public Ipage findByPage(Ipage ipg, AppWkfcfg appWkfcfg)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) appWkfcfgDao
					.getCount(appWkfcfg) });// 初始化分页类
			appWkfcfg.setStartNumAndEndNum (ipg);
			ipg.setResult(appWkfcfgDao.findByPage(appWkfcfg));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public String getById(AppWkfcfg appWkfcfg) throws ServiceException {
		String wkfNo = "";
		try {
			wkfNo = appWkfcfgDao.getById(appWkfcfg);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return wkfNo;
	}
	public AppWkfcfg getById2(AppWkfcfg appWkfcfg) throws ServiceException {
		try {
			appWkfcfg = appWkfcfgDao.getById2(appWkfcfg);
		} catch (Exception e) {
			throw new ServiceException("稍事休息!系统发生错误，请联系管理员处理");
		}
		return appWkfcfg;
	}
	
	/* 
	 * 描述: 优化读取业务流程标识，优先从内存中读取，内存中不存在时则查询数据库，返回结果后放入内存中
	 * 
	 * (non-Javadoc)
	 * @see app.creditapp.wkf.bo.AppWkfcfgBo#getByIdForLoan(app.creditapp.wkf.entity.AppWkfcfg)
	 */
	public String getByIdForLoan(AppWkfcfg appWkfcfg) throws ServiceException {
		String processKey = WorkflowConfigCache.get(appWkfcfg);
		if( processKey != null && !"".equals(processKey) ) {
			return processKey;
		}
		
		try {
			processKey = appWkfcfgDao.getByIdForLoan(appWkfcfg);
			if( processKey != null && !"".equals(processKey) ) {
				WorkflowConfigCache.put(appWkfcfg, processKey);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		
		return processKey;
	}

	public void update(AppWkfcfg appWkfcfg,List<String> prdt_no) throws ServiceException {
		try {
			String str = "";
			if(prdt_no !=null && prdt_no.size()>0){
				for(int i=0;i<prdt_no.size();i++){
					if("".equals(str)){
						str += "@"+prdt_no.get(i)+"@";
					}else{
						str += prdt_no.get(i)+"@";
					}
				}
			}
			appWkfcfg.setPrdtNo(str);
			appWkfcfgDao.update(appWkfcfg);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("系统错误，修改失败!请联系管理员处理");
		}
	}

	public List<ProdBrno> getProdList(String brNo) throws ServiceException {
		List<ProdBrno> prodList = null;
		try {
			prodList = appWkfcfgDao.getProdList(brNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return prodList;
	}
	
	public String chkPrdtNo(String id,String appType,String brNo, String prdtNo)
		throws ServiceException {
		List<AppWkfcfg> list = null;
		String result = "";
		try {
			AppWkfcfg appWkfcfg = new AppWkfcfg();
			appWkfcfg.setId(id);
			appWkfcfg.setAppType(appType);
			appWkfcfg.setBrNo(brNo);
			appWkfcfg.setPrdtNo(prdtNo);

			list = appWkfcfgDao.chkPrdtNo(appWkfcfg);
			if(list!=null&&list.size()>0){
				appWkfcfg = list.get(0);
				result += appWkfcfg.getId()+"#"+appWkfcfg.getProcessDesc()+"#"+brNo+"#"+appWkfcfg.getBrName()+"#"+prdtNo+"#"+appWkfcfg.getPrdtName();
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return result;
	}
	
	public void insert(AppWkfcfg appWkfcfg, String prdt_no)
			throws ServiceException {
		try {
//			String str = "";
			String brNoStr = "";
			String brNoArr[] = null;
//			if(prdt_no !=null && prdt_no.size()>0){
//				for(int i=0;i<prdt_no.size();i++){
//					if("".equals(str)){
//						str += "@"+prdt_no.get(i)+"@";
//					}else{
//						str += prdt_no.get(i)+"@";
//					}
//				}
//			}
//			if(StringUtils.isNotBlank(appWkfcfg.getBrNo())){
//				brNoArr = appWkfcfg.getBrNo().split("@");
//				brNoStr = appWkfcfg.getBrNo();
//			}
			appWkfcfg.setId(appWkfcfgDao.getNextVal());
			appWkfcfgDao.insert(appWkfcfg);
//			if(brNoArr!=null&&brNoArr.length>0){
//				for(int i=0;i<brNoArr.length;i++){
//					if(StringUtils.isNotBlank(brNoArr[i])){
//						appWkfcfg.setId(appWkfcfgDao.getNextVal());
//						appWkfcfg.setPrdtNo(prdt_no);
//						appWkfcfg.setBrNo(brNoArr[i]);
//						appWkfcfgDao.insert(appWkfcfg);
//					}
//				}
//			}
			appWkfcfg.setBrNo(brNoStr);
			brNoStr = null;
			prdt_no = null;
			brNoArr = null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
	}
	
	public List<AppWkfcfg> getAppWkfcfgList(AppWkfcfg appWkfcfg) throws ServiceException {
		List<AppWkfcfg> appWkfcfgList = null;
		try {
			appWkfcfgList = appWkfcfgDao.getAppWkfcfgList(appWkfcfg);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return appWkfcfgList;
	}

	public AppWkfcfgDao getAppWkfcfgDao() {
		return appWkfcfgDao;
	}

	public void setAppWkfcfgDao(AppWkfcfgDao appWkfcfgDao) {
		this.appWkfcfgDao = appWkfcfgDao;
	}

	public List<AppWkfcfg> getByProcessKey(String processKey) throws Exception {
		List<AppWkfcfg>  appWkfcfg =null;
		try{
			appWkfcfg = appWkfcfgDao.getByProcessKey(processKey);
		}catch(Exception e){
			e.printStackTrace();
		}
		return appWkfcfg;
	}

	public void updateProcessStsByKey(AppWkfcfg appWkfcfg) throws Exception {
		try{
			appWkfcfgDao.updateProcessStsByKey(appWkfcfg);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException("系统发生错误，请联系管理员处理!");
		}
	}

	public void deleteByKeyAndType(AppWkfcfg appWkfcfg) throws Exception {
		
		try{
			appWkfcfgDao.deleteByKeyAndType(appWkfcfg);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException("系统错误删除失败，请联系管理员处理!");
		}
	}

}