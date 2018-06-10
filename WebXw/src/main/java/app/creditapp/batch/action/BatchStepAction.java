package  app.creditapp.batch.action;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

import app.creditapp.batch.bo.BatchStepBo;
import app.creditapp.batch.entity.BatchStep;
import app.util.toolkit.Ipage;

/**
 * Title: BatchStepAction.java
 * Description:
 **/
public class BatchStepAction extends BaseFormBean {

	//页面传值
	private BatchStep batchStep;
	private List<BatchStep> batchStepList;

	//注入BatchStepBo
	private BatchStepBo batchStepBo;

	private String query;
	private String node_no;
	private String up_node_no;		
	private FormData formbatch0003;
	private FormData formbatch0004;
	private FormService formService = new FormService();
	
	public BatchStepAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formbatch0003 = formService.getFormData("batch0003");
		batchStep = new BatchStep();
		getFormValue(formbatch0003);
		setObjValue(formbatch0003, batchStep);
		Ipage ipage = this.getIpage();
		batchStepList = (List) batchStepBo.findByPage(ipage, batchStep).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formbatch0004 = formService.getFormData("batch0004");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formbatch0004 = formService.getFormData("batch0004");
		getFormValue(formbatch0004);
		batchStep = new BatchStep();
		setObjValue(formbatch0004, batchStep);
		batchStepBo.insert(batchStep);
		this.addActionMessage("操作成功");
		getObjValue(formbatch0004, batchStep);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formbatch0004 = formService.getFormData("batch0004");
		getFormValue(formbatch0004);
		batchStep = new BatchStep();
		setObjValue(formbatch0004, batchStep);
		batchStepBo.update(batchStep);
		this.addActionMessage("操作成功");
		getObjValue(formbatch0004, batchStep);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formbatch0003 = formService.getFormData("batch0003");
		batchStep = new BatchStep();
		batchStep.setNode_no(node_no);
		batchStep.setUp_node_no(up_node_no);
		batchStepBo.del(batchStep);
		this.addActionMessage("删除成功");
		batchStep = new BatchStep();
		Ipage ipage = this.getIpage();
		batchStepList = (List) batchStepBo.findByPage(ipage, batchStep).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formbatch0004 = formService.getFormData("batch0004");
		batchStep = new BatchStep();
		batchStep.setNode_no(node_no);
		batchStep.setUp_node_no(up_node_no);
		batchStep = batchStepBo.getById(batchStep);
		getObjValue(formbatch0004, batchStep);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formbatch0004 = formService.getFormData("batch0004");
		 getFormValue(formbatch0004);
		 validateFormData(formbatch0004);
		 batchStep = new BatchStep();
		 setObjValue(formbatch0004, batchStep);
		 batchStep = batchStepBo.getById(batchStep);
		 if(batchStep != null){
			 this.addActionError("该步骤已存在！");
		 }
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formbatch0004 = formService.getFormData("batch0004");
		 getFormValue(formbatch0004);
		 validateFormData(formbatch0004);
  	}
	public BatchStep getBatchStep() {
		return batchStep;
	}
	public void setBatchStep(BatchStep  batchStep) {
		this.batchStep = batchStep;
	}
	public List<BatchStep> getBatchStepList() {
		return batchStepList;
	}
	public void setBatchStepList(List<BatchStep> batchStepList) {
		this.batchStepList = batchStepList;
	}
	public BatchStepBo getBatchStepBo() {
		return batchStepBo;
	}
	public void setBatchStepBo(BatchStepBo batchStepBo) {
		this.batchStepBo = batchStepBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormbatch0004() {
		return formbatch0004;
	}
	public void setFormbatch0004(FormData formbatch0004) {
		this.formbatch0004 = formbatch0004;
	}
	public FormData getFormbatch0003() {
		return formbatch0003;
	}
	public void setFormbatch0003(FormData formbatch0003) {
		this.formbatch0003 = formbatch0003;
	}
	public void setNode_no(String node_no){
		this.node_no = node_no;
	}		
	public String getNode_no(){
		return node_no;
	}
	public String getUp_node_no() {
		return up_node_no;
	}
	public void setUp_node_no(String upNodeNo) {
		up_node_no = upNodeNo;
	}
}