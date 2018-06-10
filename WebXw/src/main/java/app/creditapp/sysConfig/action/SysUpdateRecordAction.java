package  app.creditapp.sysConfig.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import app.creditapp.sysConfig.bo.SysUpdateRecordBo;
import app.creditapp.sysConfig.entity.SysUpdateRecord;
import app.creditapp.sysConfig.entity.TableUpdateDetail;
import app.util.toolkit.Ipage;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;


/**
* Title: TableUpdateRecordAction.java
* Description:
**/

public class SysUpdateRecordAction extends BaseFormBean {

	//页面传值
	private SysUpdateRecord sysUpdateRecord;
	private List<SysUpdateRecord> tableUpdateRecordList;
	private List<TableUpdateDetail> tableUpdateDetailList;


	//注入TableUpdateRecordBo
	private SysUpdateRecordBo sysUpdateRecordBo;

	private String id;
	private String query;
	private String view;
	private String cifNo;
	private FormData formtbl0002;
	private FormData formtbl0001;
	private  FormService formService = new FormService();
	
	public SysUpdateRecordAction() {
		formtbl0002 = formService.getFormData("tbl0002");
		formtbl0001 = formService.getFormData("tbl0001");
		query = "";
		view = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	 public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		sysUpdateRecord = new SysUpdateRecord();
		getFormValue(formtbl0001);
		setObjValue(formtbl0001, sysUpdateRecord);
		Ipage ipage = this.getIpage();
		tableUpdateRecordList = (List) sysUpdateRecordBo.findByPage(ipage, sysUpdateRecord).getResult();
		return "list";
	}
	
	/**
	 * 新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		return "input";
	}
	

	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		sysUpdateRecordBo.del(id);
		this.addActionMessage("删除成功");
		sysUpdateRecord = new SysUpdateRecord();
		Ipage ipage = this.getIpage();
		tableUpdateRecordList = (List) sysUpdateRecordBo.findByPage(ipage, sysUpdateRecord).getResult();
		return "list";
	}

	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		sysUpdateRecord = sysUpdateRecordBo.getById(id);
		String rec = sysUpdateRecord.getModifyRecord();
		tableUpdateDetailList = new ArrayList<TableUpdateDetail>();
		TableUpdateDetail bean = null;
		//FILLER@阿斯达#122@,
		if( rec.indexOf(",") >-1 ){
			String [] arr = rec.split(",");//BR_NO@阿#1000001121@10000011
			for( int i=0;i<arr.length;i++ ){
				String []arr_ = arr[i].split("#");
				bean = new TableUpdateDetail();
				bean.setTableName(sysUpdateRecord.getTableName());
				bean.setCommentName(sysUpdateRecord.getTableComment());
				bean.setColumnName(arr_[0].split("@")[0]);
				bean.setPreModifyValue(arr_[1].split("@")[1]);
				bean.setAfterModifyValue(arr_[1].split("@")[0]);
				bean.setCol_name(arr_[0].split("@")[1]);
				tableUpdateDetailList.add(bean);
			}
		}else{
			String []arr_ = rec.split("#");
			bean = new TableUpdateDetail();
			bean.setTableName(sysUpdateRecord.getTableName());
			bean.setCommentName(sysUpdateRecord.getTableComment());
			bean.setColumnName(arr_[0].split("@")[0]);
			bean.setPreModifyValue(arr_[1].split("@")[1]);
			bean.setAfterModifyValue(arr_[1].split("@")[0]);
			bean.setCol_name(arr_[0].split("@")[1]);
			tableUpdateDetailList.add(bean);
		}
		getObjValue(formtbl0002, sysUpdateRecord);
		if(view.equals("view")){
			 query = "query";
		}
		return "detail";
	}
	
	private List<TableUpdateDetail> modifyStrToList(String modifyRecord,String tableName){
		List<TableUpdateDetail> resultList = new ArrayList<TableUpdateDetail>();
		if(!(modifyRecord==null||modifyRecord.equals(""))){
			String[] detailInfoArray = modifyRecord.split(";");
			TableUpdateDetail tableUpdateDetail ;
			int i = 1;
			for(String detailInfo:detailInfoArray){
				tableUpdateDetail = new TableUpdateDetail();
				String[] cellArray = detailInfo.split("-");
				tableUpdateDetail.setTableName(tableName);
				tableUpdateDetail.setColumnName(cellArray[0]);
				tableUpdateDetail.setCommentName(cellArray[1]);
				tableUpdateDetail.setPreModifyValue(cellArray[2]);
				tableUpdateDetail.setAfterModifyValue(cellArray[3]);
				resultList.add(tableUpdateDetail);
			}
		}
		return resultList;
	}


	public String getId() {
		return id;
	}
	public SysUpdateRecord getSysUpdateRecord() {
		return sysUpdateRecord;
	}
	public void setSysUpdateRecord(SysUpdateRecord sysUpdateRecord) {
		this.sysUpdateRecord = sysUpdateRecord;
	}
	public List<SysUpdateRecord> getTableUpdateRecordList() {
		return tableUpdateRecordList;
	}
	public void setTableUpdateRecordList(List<SysUpdateRecord> tableUpdateRecordList) {
		this.tableUpdateRecordList = tableUpdateRecordList;
	}
	public SysUpdateRecordBo getSysUpdateRecordBo() {
		return sysUpdateRecordBo;
	}
	public void setSysUpdateRecordBo(SysUpdateRecordBo sysUpdateRecordBo) {
		this.sysUpdateRecordBo = sysUpdateRecordBo;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	public FormData getFormtbl0002() {
		return formtbl0002;
	}
	public void setFormtbl0002(FormData formtbl0002) {
		this.formtbl0002 = formtbl0002;
	}
	public FormData getFormtbl0001() {
		return formtbl0001;
	}
	public void setFormtbl0001(FormData formtbl0001) {
		this.formtbl0001 = formtbl0001;
	}
	
	public FormService getFormService() {
		return formService;
	}
	public void setFormService(FormService formService) {
		this.formService = formService;
	}
	public List<TableUpdateDetail> getTableUpdateDetailList() {
		return tableUpdateDetailList;
	}
	public void setTableUpdateDetailList(
			List<TableUpdateDetail> tableUpdateDetailList) {
		this.tableUpdateDetailList = tableUpdateDetailList;
	}
	public String getCifNo() {
		return cifNo;
	}
	public void setCifNo(String cifNo) {
		this.cifNo = cifNo;
	}

}
