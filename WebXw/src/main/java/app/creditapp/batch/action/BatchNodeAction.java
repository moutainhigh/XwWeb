package  app.creditapp.batch.action;

import java.util.ArrayList;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.batch.bo.BatchNodeBo;
import app.creditapp.batch.entity.BatchNode;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: BatchNodeAction.java
 * Description:
 **/
public class BatchNodeAction extends BaseFormBean {

	//页面传值
	private BatchNode batchNode;
	private List<BatchNode> batchNodeList;
	private List tabList;

	//注入BatchNodeBo
	private BatchNodeBo batchNodeBo;

	private String query;
	private String node_no;		
	private FormData formbatch0001;
	private FormData formbatch0002;
	private FormService formService = new FormService();
	
	public BatchNodeAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formbatch0001 = formService.getFormData("batch0001");
		batchNode = new BatchNode();
		getFormValue(formbatch0001);
		setObjValue(formbatch0001, batchNode);
		Ipage ipage = this.getIpage();
		batchNodeList = (List) batchNodeBo.findByPage(ipage, batchNode).getResult();
		return "list";
	}
	
    public String getTab(){
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		tabList = new ArrayList();
		String[] tab = null;
		
		tab = new String[2];
		tab[0] = "导入数据格式化SQL信息";
		tab[1] = "BatchSqlAction_findByPage.action?node_no="+ node_no ;
		tabList.add(tab);
   
		return "tab";
    }
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formbatch0002 = formService.getFormData("batch0002");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formbatch0002 = formService.getFormData("batch0002");
		getFormValue(formbatch0002);
		batchNode = new BatchNode();
		setObjValue(formbatch0002, batchNode);
		batchNode.setScheme_no("1");		
		batchNodeBo.insert(batchNode);
		this.addActionMessage("操作成功");
		getObjValue(formbatch0002, batchNode);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formbatch0002 = formService.getFormData("batch0002");
		this.changeFormProperty(formbatch0002, "node_no", "readonly", "1");
		getFormValue(formbatch0002);
		batchNode = new BatchNode();
		setObjValue(formbatch0002, batchNode);
		batchNode.setScheme_no("1");
		batchNodeBo.update(batchNode);
		this.addActionMessage("操作成功");
		getObjValue(formbatch0002, batchNode);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formbatch0001 = formService.getFormData("batch0001");
		batchNode = new BatchNode();
		batchNode.setNode_no(node_no);
		batchNodeBo.del(batchNode);
		this.addActionMessage("删除成功");
		batchNode = new BatchNode();
		Ipage ipage = this.getIpage();
		batchNodeList = (List) batchNodeBo.findByPage(ipage, batchNode).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formbatch0002 = formService.getFormData("batch0002");
		this.changeFormProperty(formbatch0002, "node_no", "readonly", "1");
		batchNode = new BatchNode();
		batchNode.setNode_no(node_no);
		batchNode = batchNodeBo.getById(batchNode);
		getObjValue(formbatch0002, batchNode);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formbatch0002 = formService.getFormData("batch0002");
		 getFormValue(formbatch0002);
		 batchNode = new BatchNode();
		 setObjValue(formbatch0002, batchNode);
		 batchNode = batchNodeBo.getById(batchNode);
		 if(batchNode != null){
			 this.addActionError("节点号已存在!");
		 }
		 validateFormData(formbatch0002);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formbatch0002 = formService.getFormData("batch0002");
		 getFormValue(formbatch0002);
		 validateFormData(formbatch0002);
  	}
	
	public BatchNode getBatchNode() {
		return batchNode;
	}
	public void setBatchNode(BatchNode  batchNode) {
		this.batchNode = batchNode;
	}
	public List<BatchNode> getBatchNodeList() {
		return batchNodeList;
	}
	public void setBatchNodeList(List<BatchNode> batchNodeList) {
		this.batchNodeList = batchNodeList;
	}
	public BatchNodeBo getBatchNodeBo() {
		return batchNodeBo;
	}
	public void setBatchNodeBo(BatchNodeBo batchNodeBo) {
		this.batchNodeBo = batchNodeBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormbatch0002() {
		return formbatch0002;
	}
	public void setFormbatch0002(FormData formbatch0002) {
		this.formbatch0002 = formbatch0002;
	}
	public FormData getFormbatch0001() {
		return formbatch0001;
	}
	public void setFormbatch0001(FormData formbatch0001) {
		this.formbatch0001 = formbatch0001;
	}
	public void setNode_no(String node_no){
		this.node_no = node_no;
	}		
	public String getNode_no(){
		return node_no;
	}
	public List getTabList() {
		return tabList;
	}
	public void setTabList(List tabList) {
		this.tabList = tabList;
	}
}