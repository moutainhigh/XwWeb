package  app.creditapp.batch.action;
import java.util.List;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.batch.bo.BatchSqlBo;
import app.creditapp.batch.entity.BatchSql;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: BatchSqlAction.java
 * Description:
 **/
public class BatchSqlAction extends BaseFormBean {

	//页面传值
	private BatchSql batchSql;
	private List<BatchSql> batchSqlList;

	//注入BatchSqlBo
	private BatchSqlBo batchSqlBo;

	private String query;
	private String node_no;		
	private String id;		
	private FormData formbatch0007;
	private FormData formbatch0008;
	private FormService formService = new FormService();
	
	public BatchSqlAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formbatch0007 = formService.getFormData("batch0007");
		batchSql = new BatchSql();
		getFormValue(formbatch0007);
		setObjValue(formbatch0007, batchSql);
		Ipage ipage = this.getIpage();
		batchSqlList = (List) batchSqlBo.findByPage(ipage, batchSql).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formbatch0008 = formService.getFormData("batch0008");
		batchSql = new BatchSql();
		batchSql.setNode_no(node_no);
		getObjValue(formbatch0008, batchSql);
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formbatch0008 = formService.getFormData("batch0008");
		getFormValue(formbatch0008);
		batchSql = new BatchSql();
		setObjValue(formbatch0008, batchSql);
		batchSqlBo.insert(batchSql);
		this.addActionMessage("操作成功");
		getObjValue(formbatch0008, batchSql);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formbatch0008 = formService.getFormData("batch0008");
		getFormValue(formbatch0008);
		batchSql = new BatchSql();
		setObjValue(formbatch0008, batchSql);
		batchSqlBo.update(batchSql);
		this.addActionMessage("操作成功");
		getObjValue(formbatch0008, batchSql);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formbatch0007 = formService.getFormData("batch0007");
		batchSql = new BatchSql();
		batchSql.setId(id);
		batchSqlBo.del(batchSql);
		this.addActionMessage("删除成功");
		batchSql = new BatchSql();
		Ipage ipage = this.getIpage();
		batchSql.setNode_no(node_no);
		batchSqlList = (List) batchSqlBo.findByPage(ipage, batchSql).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formbatch0008 = formService.getFormData("batch0008");
		batchSql = new BatchSql();
		batchSql.setId(id);
		batchSql = batchSqlBo.getById(batchSql);
		getObjValue(formbatch0008, batchSql);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formbatch0008 = formService.getFormData("batch0008");
		 getFormValue(formbatch0008);
		 validateFormData(formbatch0008);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formbatch0008 = formService.getFormData("batch0008");
		 getFormValue(formbatch0008);
		 validateFormData(formbatch0008);
  	}
	public BatchSql getBatchSql() {
		return batchSql;
	}
	public void setBatchSql(BatchSql  batchSql) {
		this.batchSql = batchSql;
	}
	public List<BatchSql> getBatchSqlList() {
		return batchSqlList;
	}
	public void setBatchSqlList(List<BatchSql> batchSqlList) {
		this.batchSqlList = batchSqlList;
	}
	public BatchSqlBo getBatchSqlBo() {
		return batchSqlBo;
	}
	public void setBatchSqlBo(BatchSqlBo batchSqlBo) {
		this.batchSqlBo = batchSqlBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormbatch0008() {
		return formbatch0008;
	}
	public void setFormbatch0008(FormData formbatch0008) {
		this.formbatch0008 = formbatch0008;
	}
	public FormData getFormbatch0007() {
		return formbatch0007;
	}
	public void setFormbatch0007(FormData formbatch0007) {
		this.formbatch0007 = formbatch0007;
	}
	public void setNode_no(String node_no){
		this.node_no = node_no;
	}		
	public String getNode_no(){
		return node_no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}