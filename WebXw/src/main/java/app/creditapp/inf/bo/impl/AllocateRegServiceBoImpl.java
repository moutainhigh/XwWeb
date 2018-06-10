package app.creditapp.inf.bo.impl;

import accounting.biz.pub.ParmBiz;
import accounting.plat.core.AccountingException;
import app.base.BaseService;
import app.creditapp.inf.bo.AllocateRegServiceBo;
import app.creditapp.inf.client.AllocateRegService;
import app.creditapp.inf.dao.WsBaseDao;
import app.creditapp.inf.entity.ReqData;
import app.creditapp.inf.entity.WsBase;
import app.creditapp.proj.bo.ProjBaseBo;
import app.creditapp.proj.entity.ProjBase;
import app.util.DBUtils;
import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;

import com.alibaba.fastjson.JSON;


/**
 * @作者 DHCC-ZLC
 * @日期 2016-8-25
 * @描述
 */
public class AllocateRegServiceBoImpl extends BaseService implements AllocateRegServiceBo {
	public AllocateRegServiceBoImpl() {

	}
	
	private AllocateRegService allocateRegService;
	private WsBaseDao wsBaseDao;
	private ProjBaseBo projBaseBo;
	private ProjBase projBase;
	/**
	 * 单笔预拨款
	 */
	public String saveActTransactionInfos(ReqData reqData) {
		String str = "";
		
		String data = JSON.toJSONString(reqData);
		data = data.replace("detailTransactionCommonDTOList", "DetailTransactionCommonDTOList");
		data = data.replace("mainTransactionCommonDTO", "MainTransactionCommonDTO");
		str = allocateRegService.saveActTransactionInfos(data);
		
		//查询合作机构编号
		projBase = new ProjBase();
		//获取项目编号
		projBase.setProjNo(reqData.getMainTransactionCommonDTO().getProjectID());
		projBase = projBaseBo.getById(projBase);
		
		//往通讯信息表中插入 发送到软通接口和返回的json 
		WsBase wsBase = new WsBase();
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd HH:dd:ss"); 
		wsBase.setWsDate(df.format(date).substring(0,6));
		wsBase.setWsTime(df.format(date));
		wsBase.setTxCode("");
		wsBase.setRespDesc("预拨款发送");
		wsBase.setWsSts("01");
		wsBase.setReqContent(data);
		wsBase.setRespContent(str);
		wsBase.setBrNo(projBase.getBrNo());
		wsBaseDao.insert(wsBase);
		return str;
	}

	/**
	 * @return the allocateRegService
	 */
	public AllocateRegService getAllocateRegService() {
		return allocateRegService;
	}

	/**
	 * @param allocateRegService the allocateRegService to set
	 */
	public void setAllocateRegService(AllocateRegService allocateRegService) {
		this.allocateRegService = allocateRegService;
	}

	public WsBaseDao getWsBaseDao() {
		return wsBaseDao;
	}

	public void setWsBaseDao(WsBaseDao wsBaseDao) {
		this.wsBaseDao = wsBaseDao;
	}

	public ProjBase getProjBase() {
		return projBase;
	}

	public void setProjBase(ProjBase projBase) {
		this.projBase = projBase;
	}

	public ProjBaseBo getProjBaseBo() {
		return projBaseBo;
	}

	public void setProjBaseBo(ProjBaseBo projBaseBo) {
		this.projBaseBo = projBaseBo;
	}

}