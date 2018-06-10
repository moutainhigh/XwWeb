package app.creditapp.bo.impl;

import java.util.HashMap;
import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.bo.ParmDicBO;
import app.creditapp.dao.ParmDicDAO;
import app.creditapp.entity.ParmDic;
import app.util.IbatisUtils;
import app.util.toolkit.Ipage;

public class ParmDicBOImpl extends BaseService implements ParmDicBO {
	// 注入ParmKeyDAO类
	private ParmDicDAO parmdicdao;

	public ParmDicDAO getParmdicdao() {
		return parmdicdao;
	}

	public void setParmdicdao(ParmDicDAO parmdicdao) {
		this.parmdicdao = parmdicdao;
	}

	public void del(ParmDic parmdic) throws ServiceException {
		try {
			parmdicdao.del(parmdic);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, ParmDic parmdic) throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) parmdicdao
					.getCount(parmdic) });// 初始化分页类
			HashMap<String, Object> map = (HashMap<String, Object>) IbatisUtils
					.getEntityPropertyMap(ipg, parmdic);
			map.put("startNum", ipg.getStartRow());
			map.put("endNum", ipg.getEndNum());
			ipg.setResult(parmdicdao.findByPage(map));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public ParmDic getById(ParmDic parmdic) throws ServiceException {
		ParmDic parm = null;
		try {
			parm = parmdicdao.getById(parmdic);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return parm;
	}

	public String getOptName(ParmDic parmdic) throws ServiceException {
		String opt_name = null;
		try {
			opt_name = parmdicdao.getOptName(parmdic);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return opt_name;
	}
	
	public String getOptCode(ParmDic parmdic) throws ServiceException {
		String opt_code = null;
		try {
			opt_code = parmdicdao.getOptCode(parmdic);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return opt_code;
	}

	public void Insert(ParmDic parmdic) throws ServiceException {
		try {

			parmdicdao.insert(parmdic);
			System.out.println("<---------");

		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void Update(ParmDic parmdic) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			parmdicdao.update(parmdic);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}

	}

	public void delte(String keyname) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			parmdicdao.delte(keyname);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public List<ParmDic> findlist(ParmDic parmDic) throws ServiceException {
		// TODO Auto-generated method stub

		return this.parmdicdao.findlist(parmDic);
	}

	public List<ParmDic> getBykeyname4AuthAmt(ParmDic parmdic)
			throws ServiceException {
		List<ParmDic> parmdicList = null;
		try {
			parmdicList = parmdicdao.getBykeyname4AuthAmt(parmdic);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return parmdicList;
	}
}