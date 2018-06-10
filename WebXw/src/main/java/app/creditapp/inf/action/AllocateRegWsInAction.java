package app.creditapp.inf.action;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import app.creditapp.inf.bo.AllocateRegWsInBo;
import app.creditapp.inf.entity.AllocateRegWsIn;
import app.creditapp.inf.entity.ResData;
import app.creditapp.inf.entity.TaState;
import app.util.toolkit.Ipage;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: AllocateRegWsInAction.java Description:
 * 
 **/
public class AllocateRegWsInAction extends BaseFormBean {

	// 页面传值
	private AllocateRegWsIn allocateRegWsIn;
	private List<AllocateRegWsIn> allocateRegWsInList;

	// 注入AllocateRegWsInBo
	private AllocateRegWsInBo allocateRegWsInBo;

	private String query;
	private String id;
	private String projectid;
	private String backSts;
	private FormData formactrgwsin0001;
	private FormData formactrgwsin0002;
	private FormService formService = new FormService();
	public AllocateRegWsInAction() {
		query = "";
	}

	/**
	 * 分页查询
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formactrgwsin0001 = formService.getFormData("actrgwsin0001");
		allocateRegWsIn = new AllocateRegWsIn();
		getFormValue(formactrgwsin0001);
		setObjValue(formactrgwsin0001, allocateRegWsIn);
		if(projectid!=null){
			allocateRegWsIn.setProjectid(projectid);
		}
		Ipage ipage = this.getIpage();
		String date = allocateRegWsIn.getTransdate();
		if(date!=null&&!"".equals(date))
		date = date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6,8);
		allocateRegWsIn.setTransdate(date);
		allocateRegWsInList = (List) allocateRegWsInBo.findByPage(ipage, allocateRegWsIn).getResult();
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh");
		return "list";
	}

	/**
	 * 获取新增页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formactrgwsin0002 = formService.getFormData("actrgwsin0002");
		return "input";
	}

	/**
	 * 新增保存操作
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formactrgwsin0002 = formService.getFormData("actrgwsin0002");
		getFormValue(formactrgwsin0002);
		allocateRegWsIn = new AllocateRegWsIn();
		setObjValue(formactrgwsin0002, allocateRegWsIn);
		allocateRegWsInBo.insert(allocateRegWsIn);
		getObjValue(formactrgwsin0002, allocateRegWsIn);
		return "detail";
	}

	/**
	 * 修改保存操作
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formactrgwsin0002 = formService.getFormData("actrgwsin0002");
		getFormValue(formactrgwsin0002);
		allocateRegWsIn = new AllocateRegWsIn();
		setObjValue(formactrgwsin0002, allocateRegWsIn);
		allocateRegWsInBo.update(allocateRegWsIn);
		getObjValue(formactrgwsin0002, allocateRegWsIn);
		return "detail";
	}

	/**
	 * 删除操作
	 * 
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formactrgwsin0001 = formService.getFormData("actrgwsin0001");
		allocateRegWsIn = new AllocateRegWsIn();
		allocateRegWsIn.setId(id);
		allocateRegWsInBo.del(allocateRegWsIn);
		this.addActionMessage("删除成功");
		AllocateRegWsIn allocateRegWsIn2 = new AllocateRegWsIn();
		Ipage ipage = this.getIpage();
		allocateRegWsInList = (List) allocateRegWsInBo.findByPage(ipage, allocateRegWsIn2).getResult();
		return "list";
	}

	/**
	 * 收拨款发送
	 * 
	 * @return
	 * @throws Exception
	 */
	public String sendTA() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formactrgwsin0001 = formService.getFormData("actrgwsin0001");
		allocateRegWsIn = new AllocateRegWsIn();
		allocateRegWsIn.setId(id);
		allocateRegWsIn = allocateRegWsInBo.getById(allocateRegWsIn);
		if (allocateRegWsIn != null) {
			try {
				ResData res = allocateRegWsInBo.sendTA(id);
				if ("1".equals(res.getStatus())) {
					this.addActionMessage("TA发送成功");
				} else {
					this.addActionMessage("TA发送失败！！！" + res.getErrorCode() + "-" + res.getErrorCopeType());
				}
			} catch (Exception e) {
				this.addActionMessage("TA发送异常" + e.getMessage());
			}
		} else {
			this.addActionMessage("记录不存在");
		}

		// 查询列表
		AllocateRegWsIn allocateRegWsIn2 = new AllocateRegWsIn();
		allocateRegWsIn2.setProjectid(allocateRegWsIn.getProjectid());
		getObjValue(formactrgwsin0001, allocateRegWsIn2);
		Ipage ipage = this.getIpage();
		allocateRegWsInList = (List) allocateRegWsInBo.findByPage(ipage, allocateRegWsIn2).getResult();
		return "list";
	}

	/**
	 * 收拨款查询结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public String searchTA() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formactrgwsin0001 = formService.getFormData("actrgwsin0001");
		allocateRegWsIn = new AllocateRegWsIn();
		allocateRegWsIn.setId(id);
		try {
			TaState res = allocateRegWsInBo.searchTA(id);
			//0:正常1：审批中2：审批拒绝3：审批通过
			if ("0".equals(res.getState())) {
				this.addActionMessage("TA查询成功,结果["+res.getWorkflowState()+"](0:正常1：审批中2：审批拒绝3：审批通过)");
			} else {
				this.addActionMessage("TA查询失败！！！" + res.getErrorMsg());
			}
		} catch (Exception e) {
			this.addActionMessage("TA发送异常" + e.getMessage());
		}
		// 查询列表
		allocateRegWsIn = new AllocateRegWsIn();
		allocateRegWsIn.setProjectid(projectid);
		getObjValue(formactrgwsin0001, allocateRegWsIn);
		Ipage ipage = this.getIpage();
		allocateRegWsInList = (List) allocateRegWsInBo.findByPage(ipage, allocateRegWsIn).getResult();
		return "list";
	}

	/**
	 * 查询操作
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formactrgwsin0002 = formService.getFormData("actrgwsin0002");
		allocateRegWsIn = new AllocateRegWsIn();
		allocateRegWsIn.setId(id);
		allocateRegWsIn = allocateRegWsInBo.getById(allocateRegWsIn);
		getObjValue(formactrgwsin0002, allocateRegWsIn);
		return "detail";
	}

	/**
	 * 新增保存操作校验
	 * 
	 * @return
	 * @throws Exception
	 */
	public void validateInsert() {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formactrgwsin0002 = formService.getFormData("actrgwsin0002");
		getFormValue(formactrgwsin0002);
		validateFormData(formactrgwsin0002);
	}

	/**
	 * 修改保存操作校验
	 * 
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate() {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formactrgwsin0002 = formService.getFormData("actrgwsin0002");
		getFormValue(formactrgwsin0002);
		validateFormData(formactrgwsin0002);
	}

	public AllocateRegWsIn getAllocateRegWsIn() {
		return allocateRegWsIn;
	}

	public void setAllocateRegWsIn(AllocateRegWsIn allocateRegWsIn) {
		this.allocateRegWsIn = allocateRegWsIn;
	}

	public List<AllocateRegWsIn> getAllocateRegWsInList() {
		return allocateRegWsInList;
	}

	public void setAllocateRegWsInList(List<AllocateRegWsIn> allocateRegWsInList) {
		this.allocateRegWsInList = allocateRegWsInList;
	}

	public AllocateRegWsInBo getAllocateRegWsInBo() {
		return allocateRegWsInBo;
	}

	public void setAllocateRegWsInBo(AllocateRegWsInBo allocateRegWsInBo) {
		this.allocateRegWsInBo = allocateRegWsInBo;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public FormData getFormactrgwsin0002() {
		return formactrgwsin0002;
	}

	public void setFormactrgwsin0002(FormData formactrgwsin0002) {
		this.formactrgwsin0002 = formactrgwsin0002;
	}

	public FormData getFormactrgwsin0001() {
		return formactrgwsin0001;
	}

	public void setFormactrgwsin0001(FormData formactrgwsin0001) {
		this.formactrgwsin0001 = formactrgwsin0001;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getProjectid() {
		return projectid;
	}

	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}

	public String getBackSts() {
		return backSts;
	}

	public void setBackSts(String backSts) {
		this.backSts = backSts;
	}

}