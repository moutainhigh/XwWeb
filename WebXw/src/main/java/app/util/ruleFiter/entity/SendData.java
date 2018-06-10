package app.util.ruleFiter.entity;

import java.io.File;

import app.util.ruleFiter.type.FileType;

public class SendData {
	/**
	 * 准备调用的校验规则的ID
	 */
	private String sendRuleId; 
	/**
	 * 发送校验数据的格式
	 */
	private FileType fileType; 
	/**
	 * 报文文件
	 */
	private File sendFileData;
	/**
	 * 报文字符串
	 */
	private String sendStringData;
	/**
	 * 其他备用信息
	 */
	private String remarks;
	
	
	public String getSendRuleId() {
		return sendRuleId;
	}
	public void setSendRuleId(String sendRuleId) {
		this.sendRuleId = sendRuleId;
	}
	public FileType getFileType() {
		return fileType;
	}
	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}
	public File getSendFileData() {
		return sendFileData;
	}
	public void setSendFileData(File sendFileData) {
		this.sendFileData = sendFileData;
	}
	public String getSendStringData() {
		return sendStringData;
	}
	public void setSendStringData(String sendStringData) {
		this.sendStringData = sendStringData;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
