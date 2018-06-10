package  app.creditapp.cif.bo.impl;

import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.DateUtil;
import app.util.toolkit.Ipage;
import app.creditapp.cif.bo.CifPortraitBo;
import app.creditapp.cif.dao.CifPortraitDao;
import app.creditapp.cif.entity.CifPortrait;
/**
* Title: CifPortraitBoImplImpl.java
* Description:
**/
public class CifPortraitBoImpl extends BaseService implements CifPortraitBo {

	private CifPortraitDao cifPortraitDao;
	private List<String> cifPortraitList;
	public void del(CifPortrait cifPortrait) throws ServiceException {
		try {
			cifPortraitDao.del(cifPortrait);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, CifPortrait cifPortrait)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) cifPortraitDao
					.getCount(cifPortrait) });// 初始化分页类
			cifPortrait.setStartNumAndEndNum (ipg);
			ipg.setResult(cifPortraitDao.findByPage(cifPortrait));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public CifPortrait getById(CifPortrait cifPortrait) throws ServiceException {
		
		cifPortrait.setCifBlacknum(cifPortraitDao.getCifBlackNum(cifPortrait));
		int repd = 0;
		try {
			cifPortrait.setBirthDay(String.valueOf(DateUtil.getYear() - Integer.parseInt(cifPortrait.getBirthDay().substring(0, 4))));
			cifPortrait.setGradePersent(cifPortraitDao.getPercent(cifPortrait));
			repd = cifPortraitDao.getRepdNum(cifPortrait);
			cifPortrait.setRepdNum(repd);
			if(cifPortraitDao.getIfHG(cifPortrait)>=0){//0为回购客户
				cifPortrait.setIfHg("0");
			}else{
				cifPortrait.setIfHg("1");
			}
			if(cifPortraitDao.getIfDC(cifPortrait)>=0){//0为代偿客户
				cifPortrait.setIfDc("0");
			}else{
				cifPortrait.setIfDc("1");
			}
			cifPortrait.setPrtDesc("信用良好，有超过该类"+cifPortrait.getGradePersent()+"%的客户");
//			if(cifPortraitDao.getMaxVersion(cifPortrait)==null){
//				cifPortrait.setVersion(1);
//				cifPortraitDao.insert(cifPortrait);
//			}else{
//				cifPortrait.setVersion((Integer.parseInt(cifPortraitDao.getMaxVersion(cifPortrait))+1));
//				
////				cifPortraitDao.insert(cifPortrait);
//				}
			cifPortraitDao.del(cifPortrait);//删除该客户画像信息旧版本重新插入最近版本的该客户画像信息
			
			cifPortraitDao.insert(cifPortrait);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return cifPortrait;
	}

	public void insert(CifPortrait cifPortrait) throws ServiceException {
		try {
			cifPortraitDao.insert(cifPortrait);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(CifPortrait cifPortrait) throws ServiceException {
		try {
			cifPortraitDao.update(cifPortrait);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public String getWorkType(CifPortrait cifPortrait) throws ServiceException {
		String WorkType;
		try {
			WorkType = cifPortraitDao.getWorkType(cifPortrait);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return WorkType;
	}
	@Override
	public String getIfCar(CifPortrait cifPortrait) throws ServiceException {
		String ifCar;
		try {
			ifCar = cifPortraitDao.getIfCar(cifPortrait);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ifCar;
	}

	@Override
	public String getIfRoom(CifPortrait cifPortrait) throws ServiceException {
		String ifRoom;
		try {
			ifRoom = cifPortraitDao.getIfRoom(cifPortrait);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ifRoom;
	}
	@Override
	public List<CifPortrait> getCifGroup(CifPortrait cifPortrait) throws ServiceException {
		List CifGroupList = null;
		try {
			//sss String prdtNo = cifPortraitDao.getPrdtNo(cifPortrait);
			CifGroupList = cifPortraitDao.getCifGroup(cifPortrait);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return CifGroupList;
	}
	public CifPortraitDao getCifPortraitDao() {
		return cifPortraitDao;
	}

	public void setCifPortraitDao(CifPortraitDao cifPortraitDao) {
		this.cifPortraitDao = cifPortraitDao;
	}

	public List<String> getCifPortraitList() {
		return cifPortraitList;
	}

	public void setCifPortraitList(List<String> cifPortraitList) {
		this.cifPortraitList = cifPortraitList;
	}









	
}