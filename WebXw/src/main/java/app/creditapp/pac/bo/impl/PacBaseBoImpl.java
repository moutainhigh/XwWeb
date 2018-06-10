package app.creditapp.pac.bo.impl;


import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
//import app.creditapp.appro.bo.ApproBaseBo;
//import app.creditapp.appro.entity.ApproBase;
//import app.creditapp.lnp.bo.LnNoteBo;
//import app.creditapp.lnp.bo.LnPactBo;
//import app.creditapp.lnp.entity.LnNote;
//import app.creditapp.lnp.entity.LnPact;
import app.creditapp.pac.bo.PacBaseBo;
//import app.creditapp.pac.bo.PacRoleSceneRelBo;
//import app.creditapp.pac.bo.PacTypeBo;
import app.creditapp.pac.dao.PacBaseDao;
import app.creditapp.pac.entity.PacBase;
//import app.creditapp.pac.entity.PacRoleSceneRel;
//import app.creditapp.pac.entity.PacType;

/**
 * Title: PacBaseBoImplImpl.java Description:
 * 
 **/
public class PacBaseBoImpl extends BaseService implements PacBaseBo {

    private PacBaseDao pacBaseDao;
//    private PacTypeBo pacTypeBo;
//    private ApproBaseBo approBaseBo;
//    private LnPactBo lnPactBo;
//    private LnNoteBo lnNoteBo;
//    private PacRoleSceneRelBo pacRoleSceneRelBo;
    
//    /**
//     * @方法说明: 按条件查询所有影像存储信息
//     * @param pacBase
//     * @return
//     * @return List<PacBase>
//     * @修改说明:
//     */
//    public List<PacBase> listPacBase(PacBase pacBase) throws ServiceException {
//	try {
//	    return pacBaseDao.listPacBase(pacBase);
//	} catch (Exception e) {
//	    throw new ServiceException(e.getMessage());
//	}
//    }
//    
//    public PacBase getLastPacBase(PacBase pacBase) throws ServiceException {
//	try {
//	    return pacBaseDao.getLastPacBase(pacBase);
//	} catch (Exception e) {
//	    throw new ServiceException(e.getMessage());
//	}
//    }
//    
//    
//    public void updateDownCnt(String pacId) throws ServiceException {
//	try {
//	    pacBaseDao.updateDownCnt(pacId);
//	} catch (Exception e) {
//	    throw new ServiceException(e.getMessage());
//	}
//    }
//
//    public void del(PacBase pacBase) throws ServiceException {
//	try {
//	    pacBaseDao.del(pacBase);
//	} catch (Exception e) {
//	    throw new ServiceException(e.getMessage());
//	}
//    }
//
//    public Ipage findByPage(Ipage ipg, PacBase pacBase) throws ServiceException {
//	try {
//	    ipg.initPageCounts(new Integer[] { (Integer) pacBaseDao
//		    .getCount(pacBase) });// 初始化分页类
//	    pacBase.setStartNumAndEndNum(ipg);
//	    ipg.setResult(pacBaseDao.findByPage(pacBase));
//	} catch (Exception e) {
//	    throw new ServiceException(e.getMessage());
//	}
//	return ipg;
//    }
//
//    public PacBase getById(PacBase pacBase) throws ServiceException {
//	try {
//	    pacBase = pacBaseDao.getById(pacBase);
//	} catch (Exception e) {
//	    throw new ServiceException(e.getMessage());
//	}
//	return pacBase;
//    }

    public void insert(PacBase pacBase) throws ServiceException {
	try {
	    pacBaseDao.insert(pacBase);
	} catch (Exception e) {
	    throw new ServiceException(e.getMessage());
	}
    }

//    public void update(PacBase pacBase) throws ServiceException {
//	try {
//	    pacBaseDao.update(pacBase);
//	} catch (Exception e) {
//	    throw new ServiceException(e.getMessage());
//	}
//    }
//    
//    @Override
//	public PacBase getBusiNo(String pacTypeNo, String busi_no,String pacScene, PacBase pac) throws ServiceException {
//    	String sceneType = null;
//    	try {
//    	    PacType pacType = new PacType();
//    	    pacType.setPac_type_no(pacTypeNo);
//    	    pacType = pacTypeBo.getById(pacType);
//    	    if(null != pacType){
//    	    	sceneType = pacType.getScene_type();
//    	    	//sceneType节点对应场景pacScene影像模块对应场景
//    			//10客户20申请30批复40合同50借据60担保70出账80贷后检查90其他
//    	    	if("20".equals(pacScene)){//授信
//    	    		if("10".equals(sceneType)){
//    	    			busi_no = "";
//    	    			pac.setPac_scene("10");
//    	    		}else if("20".equals(sceneType)){
////    	    			ApproBase approBase = new ApproBase();
////    					approBase.setAppro_no(busi_no);
////    					approBase = approBaseBo.getById(approBase);
////    					if(null != approBase){
////    						busi_no = approBase.getApp_no();
////    					}
//    					pac.setPac_scene("20");
//    	    		}
//    	    	}else if("30".equals(pacScene)){//批复
//    	    		if("10".equals(sceneType)){
//    	    			busi_no = "";
//    	    			pac.setPac_scene("10");
//    	    		}else if("20".equals(sceneType)){
////    	    			ApproBase approBase = new ApproBase();
////    					approBase.setAppro_no(busi_no);
////    					approBase = approBaseBo.getById(approBase);
////    					if(null != approBase){
////    						busi_no = approBase.getApp_no();
////    					}
//    					pac.setPac_scene("20");
//    	    		}
//    	    	}else if("40".equals(pacScene)){//合同
//    	    		if("10".equals(sceneType)){
//    	    			busi_no = "";
//    	    			pac.setPac_scene("10");
//    	    		}else if("20".equals(sceneType)){
//    	    			LnPact lnPact = new LnPact();
//    					lnPact.setPact_no(busi_no);
//    					lnPact = lnPactBo.getById(lnPact);
//    					if(null != lnPact){
//    						busi_no = lnPact.getApp_no();
//    					}
//    					pac.setPac_scene("20");
//    	    		}else if("30".equals(sceneType)){
//    	    			LnPact lnPact = new LnPact();
//    					lnPact.setPact_no(busi_no);
//    					lnPact = lnPactBo.getById(lnPact);
//    					if(null != lnPact){
//    						busi_no = lnPact.getAppro_no();
//    					}
//    					pac.setPac_scene("30");
//    	    		}
//    	    	}else if("70".equals(pacScene)){//出账
//    	    		if("10".equals(sceneType)){
//    	    			busi_no = "";
//    	    			pac.setPac_scene("10");
//    	    		}else if("20".equals(sceneType)){
//    	    			LnNote lnNote = new LnNote();
//    					lnNote.setNote_no(busi_no);
//    					lnNote = lnNoteBo.getById(lnNote);
//    					if(null != lnNote){
//    						busi_no = lnNote.getPact_no();
//        					LnPact lnPact = new LnPact();
//        					lnPact.setPact_no(busi_no);
//        					lnPact = lnPactBo.getById(lnPact);
//        					if(null != lnPact){
//        						busi_no = lnPact.getApp_no();
//        					}
//    					}
//    					pac.setPac_scene("20");
//    	    		}else if("30".equals(sceneType)){
//    	    			LnNote lnNote = new LnNote();
//    					lnNote.setNote_no(busi_no);
//    					lnNote = lnNoteBo.getById(lnNote);
//    					if(null != lnNote){
//    						busi_no = lnNote.getPact_no();
//        					LnPact lnPact = new LnPact();
//        					lnPact.setPact_no(busi_no);
//        					lnPact = lnPactBo.getById(lnPact);
//        					if(null != lnPact){
//        						busi_no = lnPact.getAppro_no();
//        					}
//    					}
//    					pac.setPac_scene("30");
//    	    		}else if("40".equals(sceneType)){
//    	    			LnNote lnNote = new LnNote();
//    					lnNote.setNote_no(busi_no);
//    					lnNote = lnNoteBo.getById(lnNote);
//    					if(null != lnNote){
//    						busi_no = lnNote.getPact_no();
//    					}
//    					pac.setPac_scene("40");
//    	    		}else if("70".equals(sceneType)){
//    	    			LnNote lnNote = new LnNote();
//    					lnNote.setNote_no(busi_no);
//    					lnNote = lnNoteBo.getById(lnNote);
//    					if(null != lnNote){
//    						busi_no = lnNote.getNote_no();
//    					}
//    					pac.setPac_scene("70");
//    	    		}
//    	    	}
//    	    }
//    	} catch (Exception e) {
//    	    throw new ServiceException(e.getMessage());
//    	}
//    	pac.setBusi_no(busi_no);
//		return pac;
//	}
//    
//    @Override
//	public String getReadOnly(String roleNo, String pacScene)
//			throws ServiceException {
//    	String read_only = "0";
//    	try {
//    		PacRoleSceneRel pacRoleSceneRel = new PacRoleSceneRel();
//    		pacRoleSceneRel.setRole_no(roleNo);
//    		pacRoleSceneRel.setScene_id(pacScene);
//    		pacRoleSceneRel = pacRoleSceneRelBo.getById(pacRoleSceneRel);
//    		if(null != pacRoleSceneRel){
//    			read_only = pacRoleSceneRel.getRead_only();
//    		}
//    	} catch (Exception e) {
//    	    throw new ServiceException(e.getMessage());
//    	}
//		return read_only;
//	}
//    
//    public List<PacBase> getyn(PacBase pacBase) throws ServiceException {
//    	List<PacBase> list = null;
//    	try {
//    	    list = pacBaseDao.getyn(pacBase);
//    	} catch (Exception e) {
//    	    throw new ServiceException(e.getMessage());
//    	}
//    	return list;
//        }

    public PacBaseDao getPacBaseDao() {
	return pacBaseDao;
    }

    public void setPacBaseDao(PacBaseDao pacBaseDao) {
	this.pacBaseDao = pacBaseDao;
    }

//	public PacTypeBo getPacTypeBo() {
//		return pacTypeBo;
//	}
//
//	public void setPacTypeBo(PacTypeBo pacTypeBo) {
//		this.pacTypeBo = pacTypeBo;
//	}
//
//	public ApproBaseBo getApproBaseBo() {
//		return approBaseBo;
//	}
//
//	public void setApproBaseBo(ApproBaseBo approBaseBo) {
//		this.approBaseBo = approBaseBo;
//	}
//
//	public LnPactBo getLnPactBo() {
//		return lnPactBo;
//	}
//
//	public void setLnPactBo(LnPactBo lnPactBo) {
//		this.lnPactBo = lnPactBo;
//	}
//
//	public LnNoteBo getLnNoteBo() {
//		return lnNoteBo;
//	}
//
//	public void setLnNoteBo(LnNoteBo lnNoteBo) {
//		this.lnNoteBo = lnNoteBo;
//	}
//
//	public PacRoleSceneRelBo getPacRoleSceneRelBo() {
//		return pacRoleSceneRelBo;
//	}
//
//	public void setPacRoleSceneRelBo(PacRoleSceneRelBo pacRoleSceneRelBo) {
//		this.pacRoleSceneRelBo = pacRoleSceneRelBo;
//	}
//
//	@Override
//	public int getCount(PacBase pacBase) throws ServiceException {
//		// TODO Auto-generated method stub
//		int count = 0;
//		try{
//			count = pacBaseDao.getPacCount(pacBase);
//		}catch(Exception e){
//			throw new ServiceException(e.getMessage());
//		}
//		return count;
//	}

}