package  app.creditapp.sys.bo.impl;

import java.util.List;

import app.base.BaseService;
import app.base.DAOException;
import app.base.ServiceException;
import app.creditapp.pac.bo.PacBaseBo;
import app.creditapp.pac.entity.PacBase;
import app.creditapp.sys.bo.PubNoteBo;
import app.creditapp.sys.dao.PubNoteDao;
import app.creditapp.sys.entity.PubNote;
import app.util.toolkit.Ipage;
/**
* Title: PubNoteBoImplImpl.java
* Description:
**/
public class PubNoteBoImpl extends BaseService implements PubNoteBo {

	private PubNoteDao pubNoteDao;
	private PacBaseBo pacBaseBo;

	public void del(PubNote pubNote) throws ServiceException {
		try {
			pubNoteDao.del(pubNote);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, PubNote pubNote)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) pubNoteDao
					.getCount(pubNote) });// 初始化分页类
			pubNote.setStartNumAndEndNum (ipg);
			ipg.setResult(pubNoteDao.findByPage(pubNote));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	
	public List<PubNote> findAllByNote_type(PubNote pubNote)
			throws ServiceException {
		List pubNoteList = null;
		try {
			pubNoteList = pubNoteDao.findAllByNote_type(pubNote);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return pubNoteList;
	}	
	public PubNote getById(PubNote pubNote) throws ServiceException {
		try {				
			pubNote = pubNoteDao.getById(pubNote);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return pubNote;
	}

	public void insert(PubNote pubNote) throws ServiceException {
		try {

//			if(pubNote.getContType().equals("2")){
//				PacBase pacBase=new PacBase();
//				pacBase.setBusi_no(pubNote.getNoteNo());
//				pacBase.setPac_type_no(pubNote.getNoteType());
//				pacBase.setPac_name(pubNote.getNoteTitle());
//				pacBase.setPac_addr(pubNote.getNoteFile());
//				pacBase.setOp_no(pubNote.getOpNo());
//				pacBase.setTx_time(pubNote.getTxDate());
//				pacBase.setUp_time(pubNote.getUpDate());
//				pacBaseBo.insert(pacBase);
//			}			

			PubNote pubNotes = pubNoteDao.getById(pubNote);
			if (pubNotes == null) {
				pubNoteDao.insert(pubNote);
			} else {
				pubNoteDao.update(pubNote);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(PubNote pubNote) throws ServiceException {
		try {
			pubNoteDao.update(pubNote);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public List<PubNote> getTop10(PubNote pubNote) throws ServiceException {
		List<PubNote> list = null;
		try {
			list = pubNoteDao.getTop10(pubNote);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return list;
	}

	public PubNoteDao getPubNoteDao() {
		return pubNoteDao;
	}

	public void setPubNoteDao(PubNoteDao pubNoteDao) {
		this.pubNoteDao = pubNoteDao;
	}

	public PacBaseBo getPacBaseBo() {
		return pacBaseBo;
	}

	public void setPacBaseBo(PacBaseBo pacBaseBo) {
		this.pacBaseBo = pacBaseBo;
	}

	
}