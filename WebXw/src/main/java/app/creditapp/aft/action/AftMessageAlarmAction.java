package app.creditapp.aft.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import app.base.BaseAction;
import app.base.ServiceException;
import app.creditapp.aft.bo.AftMessageAlarmBo;
import app.creditapp.aft.bo.AftRewFundBo;
import app.creditapp.aft.bo.AftRewPactBo;
import app.creditapp.aft.bo.AftRewProjBo;
import app.creditapp.aft.bo.AftRewRealBo;
import app.creditapp.aft.entity.AftRewFund;
import app.creditapp.aft.entity.AftRewPact;
import app.creditapp.aft.entity.AftRewProj;
import app.creditapp.aft.entity.AftRewReal;
import app.creditapp.aft.entity.aftMessage.AjaxData;
import app.creditapp.aft.entity.aftMessage.PasSubTypeEntity;
import app.creditapp.aft.manager.AftManagerManagerInterface;
import app.creditapp.aft.manager.ManagerEntity;
import app.creditapp.aft.manager.ParmRewType;
import app.util.User;

import com.alibaba.fastjson.JSON;

public class AftMessageAlarmAction extends BaseAction{
	private AftRewPactBo aftRewPactBo;
	private AftRewProjBo aftRewProjBo;
	private AftRewFundBo aftRewFundBo;
	private AftRewRealBo aftRewRealBo;
	private AftMessageAlarmBo aftMessageAlarmBo;
	private AftManagerManagerInterface aftManagerManagerInterface;
	
	private String messageJson;
	private String sendDate;
	
	private String bizPkno;
	private String pasSubType;
	private String pasMaxType;
	private String pasNo;
	private String relId;
	private String projNo;
	
	private String beginDate;
	private String endDate;
	
	
	public String showMessagePage(){
		String messageJson =(String)getHttpRequest().getSession().getAttribute("messageData");
		if(messageJson ==null){
			String result = aftMessageAlarmBo.initMessagePage(User.getLoginid(getHttpRequest()),null,true);
			getHttpRequest().getSession().setAttribute("messageData", "{\"ajaxData\":"+result+"}");
		}
		return "initMessage";
	}
	
	/**
	 * 初始化消息面json串数组
	 * @作者 DHCC-LIYABIN
	 * @日期 2016年8月3日
	 * @方法说明：
	 * @返回参数 String
	 */
	public String initMessagePage(){
		messageJson = aftMessageAlarmBo.initMessagePage(User.getLoginid(getHttpRequest()),User.getSys_date(getHttpRequest()),false);
		messageJson = "{\"ajaxData\":"+messageJson+"}";
		return "initMessage";
	}
	
	public String reloadMessagePage() throws IOException{
		String result = aftMessageAlarmBo.initMessagePage(User.getLoginid(getHttpRequest()),sendDate,false);
		messageJson = "{\"resultData\":"+result+"}";
		HttpServletResponse response = getHttpResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(messageJson);
		return null;
	}
	
	public String getMessageJsonForRecent() throws IOException{
		String result = aftMessageAlarmBo.initMessagePage(User.getLoginid(getHttpRequest()),User.getSys_date(getHttpRequest()),true);
		messageJson = "{\"resultData\":"+result+"}";
		HttpServletResponse response = getHttpResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(messageJson);
		return null;
	}
	
	public String getMessageJsonForBetween() throws IOException{
		AjaxData ajaxData = aftMessageAlarmBo.initAjaxDataForBetween(User.getLoginid(getHttpRequest()),beginDate, endDate);
		messageJson = "{\"resultData\":"+JSON.toJSONString(ajaxData)+"}";
		HttpServletResponse response = getHttpResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(messageJson);
		return null;
	}
	
	public String ignoreMessage()throws IOException{
		HttpServletResponse response = getHttpResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		PasSubTypeEntity typeEntity = PasSubTypeEntity.getTypeNameByNo(pasSubType);
		try {
			if(typeEntity == PasSubTypeEntity.RewPactMessage || typeEntity == PasSubTypeEntity.RewPactAlert){
				//合同部分为汇总请求，因此批量忽略
				AftRewPact rewPact = new AftRewPact();
				rewPact.setProjNo(bizPkno);
				rewPact.setRewSts("02");
				aftRewPactBo.updateSts(rewPact);
			}
			
			if(typeEntity == PasSubTypeEntity.RewProjMessage || typeEntity == PasSubTypeEntity.RewProjAlert){
				AftRewProj rewProj = new AftRewProj();
				rewProj.setRewId(bizPkno);
				rewProj.setRewSts("02");
				aftRewProjBo.updateSts(rewProj);
			}
			
			if(typeEntity == PasSubTypeEntity.RewFundMessage || typeEntity == PasSubTypeEntity.RewFundAlert){
				AftRewFund rewFund = new AftRewFund();
				rewFund.setRewId(bizPkno);
				rewFund.setRewSts("02");
				aftRewFundBo.updateSts(rewFund);
			}
			
			if(typeEntity == PasSubTypeEntity.RewRealMessage || typeEntity == PasSubTypeEntity.RewRealAlert){
				AftRewReal rewReal = new AftRewReal();
				rewReal.setRewId(bizPkno);
				rewReal.setRewSts("02");
				aftRewRealBo.updateSts(rewReal);
			}
			out.print("succ");
		} catch (ServiceException e) {
			out.print("error");
			e.printStackTrace();
		}
		return null;
	}
	
	public String updateMessage()throws IOException{
		HttpServletResponse response = getHttpResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		PasSubTypeEntity typeEntity = PasSubTypeEntity.getTypeNameByNo(pasSubType);
		try {
			//先进行业务处理
			ManagerEntity entity = new ManagerEntity();
			entity.setProjNo(projNo);
			entity.setRelId(relId);
			entity.setParmRewType(ParmRewType.getByRewNo(pasNo));
			aftManagerManagerInterface.disposeMessage(entity);
			
			if(aftManagerManagerInterface.disposeMessage(entity)){
				if(typeEntity == PasSubTypeEntity.RewPactMessage || typeEntity == PasSubTypeEntity.RewPactAlert){
					AftRewPact rewPact = new AftRewPact();
					rewPact.setRewId(bizPkno);
					rewPact.setDealRest("02");
					aftRewPactBo.updateDealRest(rewPact);
				}
				
				if(typeEntity == PasSubTypeEntity.RewProjMessage || typeEntity == PasSubTypeEntity.RewProjAlert){
					AftRewProj rewProj = new AftRewProj();
					rewProj.setRewId(bizPkno);
					rewProj.setDealRest("02");
					aftRewProjBo.updateDealRest(rewProj);
				}
				
				if(typeEntity == PasSubTypeEntity.RewFundMessage || typeEntity == PasSubTypeEntity.RewFundAlert){
					AftRewFund rewFund = new AftRewFund();
					rewFund.setRewId(bizPkno);
					rewFund.setDealRest("02");
					aftRewFundBo.updateDealRest(rewFund);
				}
				
				if(typeEntity == PasSubTypeEntity.RewRealMessage || typeEntity == PasSubTypeEntity.RewRealAlert){
					AftRewReal rewReal = new AftRewReal();
					rewReal.setRewId(bizPkno);
					rewReal.setDealRest("02");
					aftRewRealBo.updateDealRest(rewReal);
				}
				out.print("succ");
			}else{
				out.print("error");
			}
			
		} catch (ServiceException e) {
			out.print("error");
			e.printStackTrace();
		}
		return null;
	}
	
	public String getMessageJson() {
		return messageJson;
	}

	public void setMessageJson(String messageJson) {
		this.messageJson = messageJson;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getBizPkno() {
		return bizPkno;
	}

	public void setBizPkno(String bizPkno) {
		this.bizPkno = bizPkno;
	}

	public String getPasSubType() {
		return pasSubType;
	}

	public void setPasSubType(String pasSubType) {
		this.pasSubType = pasSubType;
	}

	public void setAftMessageAlarmBo(AftMessageAlarmBo aftMessageAlarmBo) {
		this.aftMessageAlarmBo = aftMessageAlarmBo;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getPasMaxType() {
		return pasMaxType;
	}

	public void setPasMaxType(String pasMaxType) {
		this.pasMaxType = pasMaxType;
	}

	public String getPasNo() {
		return pasNo;
	}

	public void setPasNo(String pasNo) {
		this.pasNo = pasNo;
	}

	public String getRelId() {
		return relId;
	}

	public void setRelId(String relId) {
		this.relId = relId;
	}

	public void setAftRewPactBo(AftRewPactBo aftRewPactBo) {
		this.aftRewPactBo = aftRewPactBo;
	}

	public void setAftRewProjBo(AftRewProjBo aftRewProjBo) {
		this.aftRewProjBo = aftRewProjBo;
	}

	public void setAftRewFundBo(AftRewFundBo aftRewFundBo) {
		this.aftRewFundBo = aftRewFundBo;
	}

	public void setAftRewRealBo(AftRewRealBo aftRewRealBo) {
		this.aftRewRealBo = aftRewRealBo;
	}

	public String getProjNo() {
		return projNo;
	}

	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}

	public void setAftManagerManagerInterface(
			AftManagerManagerInterface aftManagerManagerInterface) {
		this.aftManagerManagerInterface = aftManagerManagerInterface;
	}
}
