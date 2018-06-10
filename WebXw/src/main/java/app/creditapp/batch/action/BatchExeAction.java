package  app.creditapp.batch.action;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

import app.creditapp.batch.bo.BatchExeBo;
import app.creditapp.batch.console.BatchRunner;
import app.creditapp.batch.entity.BatchExe;
import app.util.User;
import app.util.toolkit.Ipage;

/**
 * Title: BatchExeAction.java
 * Description:
 **/
public class BatchExeAction extends BaseFormBean {

	//页面传值
	private BatchExe batchExe;
	private List<BatchExe> batchExeList;

	//注入BatchExeBo
	private BatchExeBo batchExeBo;

	private String query;
	private String batch_date;		
	private String node_no;		
	private FormData formbatch0009;
	private FormData formbatch0010;
	private FormService formService = new FormService();
	
	public BatchExeAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formbatch0009 = formService.getFormData("batch0009");
		batchExe = new BatchExe();
		getFormValue(formbatch0009);
		setObjValue(formbatch0009, batchExe);
		if(null==batchExe.getBatch_date() ||"".equals(batchExe.getBatch_date())){
			batchExe.setBatch_date(User.getSys_date(getHttpRequest()));
		}
		Ipage ipage = this.getIpage();
		batchExeList = (List) batchExeBo.findByPage(ipage, batchExe).getResult();
		getObjValue(formbatch0009, batchExe);
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formbatch0010 = formService.getFormData("batch0010");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formbatch0010 = formService.getFormData("batch0010");
		getFormValue(formbatch0010);
		batchExe = new BatchExe();
		setObjValue(formbatch0010, batchExe);
		batchExeBo.insert(batchExe);
		getObjValue(formbatch0010, batchExe);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formbatch0010 = formService.getFormData("batch0010");
		getFormValue(formbatch0010);
		batchExe = new BatchExe();
		setObjValue(formbatch0010, batchExe);
		batchExeBo.update(batchExe);
		getObjValue(formbatch0010, batchExe);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formbatch0009 = formService.getFormData("batch0009");
		batchExe = new BatchExe();
		batchExe.setBatch_date(batch_date);
		batchExe.setNode_no(node_no);
		batchExeBo.del(batchExe);
		this.addActionMessage("删除成功");
		batchExe = new BatchExe();
		Ipage ipage = this.getIpage();
		batchExeList = (List) batchExeBo.findByPage(ipage, batchExe).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formbatch0010 = formService.getFormData("batch0010");
		batchExe = new BatchExe();
		batchExe.setBatch_date(batch_date);
		batchExe.setNode_no(node_no);
		batchExe = batchExeBo.getById(batchExe);
		getObjValue(formbatch0010, batchExe);
		return "detail";
	}
	
	/**
	 * 单步执行批量
	 * @return
	 * @throws Exception
	 */
	public String runNode() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formbatch0009 = formService.getFormData("batch0009");
		batchExe = new BatchExe();
		batchExe.setBatch_date(batch_date);
		batchExe.setNode_no(node_no);
		batchExe = batchExeBo.getById(batchExe);
		if("1".equals(batchExe.getNode_status()) || "3".equals(batchExe.getNode_status())){
			BatchRunner runner = new BatchRunner(batch_date);
			runner.runnerSingle(batch_date, node_no);
			this.addActionMessage("节点已执行");
		}else{
			this.addActionMessage("节点非失败或未执行状态，不能执行");
		}
		Ipage ipage = this.getIpage();
		BatchExe batchExe2 = new BatchExe();
		batchExe2.setBatch_date(batch_date);
		batchExeList = (List) batchExeBo.findByPage(ipage, batchExe2).getResult();
		getObjValue(formbatch0009, batchExe2);
		return "list";
	}
	
	/**
	 * 执行批量
	 * @return
	 * @throws Exception
	 */
	public String runBatch() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formbatch0009 = formService.getFormData("batch0009");
		batchExe = new BatchExe();
		batchExe.setBatch_date(batch_date);
		 
		Date date = new Date();
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
		String sysDate=simpledateformat.format(date);
		
		int isys = Integer.parseInt(sysDate);
		int ibat = Integer.parseInt(batch_date);
		//zlc 20170911 注释掉
//		if(ibat-isys>=1){
//			this.addActionMessage("今天["+sysDate+"]无法执行["+batch_date+"]日批量");
//		}else{
			batchExeBo.checkBatch(batch_date);
			int is = batchExeBo.runBatch(batch_date);
			if(is>=1){
				this.addActionMessage("批量开始已执行");
			}else{
				this.addActionMessage("批量已全部执行成功");
			}
//		}
		Ipage ipage = this.getIpage();
		BatchExe batchExe2 = new BatchExe();
		batchExe2.setBatch_date(batch_date);
		batchExeList = (List) batchExeBo.findByPage(ipage, batchExe2).getResult();
		getObjValue(formbatch0009, batchExe2);
		return "list";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formbatch0010 = formService.getFormData("batch0010");
		 getFormValue(formbatch0010);
		 validateFormData(formbatch0010);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formbatch0010 = formService.getFormData("batch0010");
		 getFormValue(formbatch0010);
		 validateFormData(formbatch0010);
  	}
	public BatchExe getBatchExe() {
		return batchExe;
	}
	public void setBatchExe(BatchExe  batchExe) {
		this.batchExe = batchExe;
	}
	public List<BatchExe> getBatchExeList() {
		return batchExeList;
	}
	public void setBatchExeList(List<BatchExe> batchExeList) {
		this.batchExeList = batchExeList;
	}
	public BatchExeBo getBatchExeBo() {
		return batchExeBo;
	}
	public void setBatchExeBo(BatchExeBo batchExeBo) {
		this.batchExeBo = batchExeBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormbatch0010() {
		return formbatch0010;
	}
	public void setFormbatch0010(FormData formbatch0010) {
		this.formbatch0010 = formbatch0010;
	}
	public FormData getFormbatch0009() {
		return formbatch0009;
	}
	public void setFormbatch0009(FormData formbatch0009) {
		this.formbatch0009 = formbatch0009;
	}
	public void setBatch_date(String batch_date){
		this.batch_date = batch_date;
	}		
	public void setNode_no(String node_no){
		this.node_no = node_no;
	}		
	public String getBatch_date(){
		return batch_date;
	}
	public String getNode_no(){
		return node_no;
	}
}