package app.base.httpclient.entity;

import app.creditapp.aft.entity.aftMessage.PasSubTypeEntity;

public class SendMessageEntity {
	private SendMessageType sendType;
	private String title;
	private String contet;
	private String groupNo;
	private String[] reciveUserNos;
	private PasSubTypeEntity pasSubTypeEntity;
	
	public SendMessageEntity() {
		super();
	}
	public SendMessageEntity(SendMessageType sendType, String title, String contet,
			PasSubTypeEntity pasSubTypeEntity) {
		super();
		this.sendType = sendType;
		this.title = title;
		this.contet = contet;
		this.pasSubTypeEntity = pasSubTypeEntity;
	}
	public SendMessageEntity(SendMessageType sendType, String title,
			String contet, String[] reciveUserNos,
			PasSubTypeEntity pasSubTypeEntity) {
		super();
		this.sendType = sendType;
		this.title = title;
		this.contet = contet;
		this.reciveUserNos = reciveUserNos;
		this.pasSubTypeEntity = pasSubTypeEntity;
	}
	
	public SendMessageEntity(SendMessageType sendType, String title,
			String contet, String groupNo, String[] reciveUserNos,
			PasSubTypeEntity pasSubTypeEntity) {
		super();
		this.sendType = sendType;
		this.title = title;
		this.contet = contet;
		this.groupNo = groupNo;
		this.reciveUserNos = reciveUserNos;
		this.pasSubTypeEntity = pasSubTypeEntity;
	}
	public SendMessageType getSendType() {
		return sendType;
	}
	public void setSendType(SendMessageType sendType) {
		this.sendType = sendType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContet() {
		return contet;
	}
	public void setContet(String contet) {
		this.contet = contet;
	}
	public PasSubTypeEntity getPasSubTypeEntity() {
		return pasSubTypeEntity;
	}
	public void setPasSubTypeEntity(PasSubTypeEntity pasSubTypeEntity) {
		this.pasSubTypeEntity = pasSubTypeEntity;
	}
	public String[] getReciveUserNos() {
		return reciveUserNos;
	}
	public void setReciveUserNos(String[] reciveUserNos) {
		this.reciveUserNos = reciveUserNos;
	}
	public String getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}
}
