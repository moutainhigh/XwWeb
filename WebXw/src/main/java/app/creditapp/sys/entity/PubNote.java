package app.creditapp.sys.entity;
import app.base.BaseDomain;
/**
* Title: PubNote.java
* Description:
* @version：1.0
**/
public class PubNote extends BaseDomain {
	private String noteNo;//公告编号
	private String noteTitle;//公告标题
	private String noteContent;//公告内容
	private String noteType;//公告类型
	private String noteFile;//附件地址
	private String noteVip;//是否置顶
	private String endDate;//到期时间
	private String noteSts;//公告状态
	private String opNo;//发布人
	private String txDate;//发布时间
	private String upDate;//更新日期
	private String contType;//编辑类型
	private String docName;//文档名称
	
	
	public String getNoteNo() {
		return noteNo;
	}
	public void setNoteNo(String noteNo) {
		this.noteNo = noteNo;
	}
	public String getNoteTitle() {
		return noteTitle;
	}
	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}
	public String getNoteContent() {
		return noteContent;
	}
	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}
	public String getNoteType() {
		return noteType;
	}
	public void setNoteType(String noteType) {
		this.noteType = noteType;
	}
	public String getNoteFile() {
		return noteFile;
	}
	public void setNoteFile(String noteFile) {
		this.noteFile = noteFile;
	}
	public String getNoteVip() {
		return noteVip;
	}
	public void setNoteVip(String noteVip) {
		this.noteVip = noteVip;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getNoteSts() {
		return noteSts;
	}
	public void setNoteSts(String noteSts) {
		this.noteSts = noteSts;
	}
	public String getOpNo() {
		return opNo;
	}
	public void setOpNo(String opNo) {
		this.opNo = opNo;
	}
	public String getTxDate() {
		return txDate;
	}
	public void setTxDate(String txDate) {
		this.txDate = txDate;
	}
	public String getUpDate() {
		return upDate;
	}
	public void setUpDate(String upDate) {
		this.upDate = upDate;
	}
	public String getContType() {
		return contType;
	}
	public void setContType(String contType) {
		this.contType = contType;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}

}