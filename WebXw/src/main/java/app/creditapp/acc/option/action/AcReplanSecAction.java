package  app.creditapp.acc.option.action;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import app.creditapp.acc.option.bo.AcReplanParmBo;
import app.creditapp.acc.option.bo.AcReplanSecBo;
import app.creditapp.acc.option.entity.AcReplanParm;
import app.creditapp.acc.option.entity.AcReplanSec;
import app.util.User;
import app.util.toolkit.Ipage;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: AcReplanSecAction.java
 **/
public class AcReplanSecAction extends BaseFormBean {

	//页面传值
	private AcReplanSec acReplanSec;
	private List<AcReplanSec> acReplanSecList;

	//注入AcReplanSecBo
	private AcReplanSecBo acReplanSecBo;
	private AcReplanParmBo acReplanParmBo;


	private String query;
	private String secId;	
	private String replanId;
	private FormData formreplan003;
	private FormData formreplan004;
	private FormService formService = new FormService();
	
	public AcReplanSecAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formreplan003 = formService.getFormData("replan003");
		acReplanSec = new AcReplanSec();
		getFormValue(formreplan003);
		setObjValue(formreplan003, acReplanSec);
		Ipage ipage = this.getIpage();
		acReplanSecList = (List) acReplanSecBo.findByPage(ipage, acReplanSec).getResult();
		return "list";
	}
	
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByReplanId() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		acReplanSec = new AcReplanSec();
		Ipage ipage = this.getIpage();
		acReplanSec.setReplanId(replanId);
		acReplanSecList = (List) acReplanSecBo.findByPage(ipage, acReplanSec).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formreplan004 = formService.getFormData("replan004");
		getFormValue(formreplan004);
		acReplanSec = new AcReplanSec();
		acReplanSec.setReplanId(replanId);
		getObjValue(formreplan004, acReplanSec);
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formreplan004 = formService.getFormData("replan004");
		getFormValue(formreplan004);
		acReplanSec = new AcReplanSec();
		setObjValue(formreplan004, acReplanSec);
		acReplanSecBo.insert(acReplanSec);
		getObjValue(formreplan004, acReplanSec);
		this.addActionMessage("操作成功！");
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formreplan004 = formService.getFormData("replan004");
		getFormValue(formreplan004);
		acReplanSec = new AcReplanSec();
		setObjValue(formreplan004, acReplanSec);
		acReplanSec.setUpDate(User.getSys_date(this.getHttpRequest()));
		acReplanSecBo.update(acReplanSec);
		getObjValue(formreplan004, acReplanSec);
		this.addActionMessage("操作成功！");
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formreplan003 = formService.getFormData("replan003");
		acReplanSec = new AcReplanSec();
		acReplanSec.setSecId(secId);
		acReplanSecBo.del(acReplanSec);
		this.addActionMessage("删除成功");
		acReplanSec = new AcReplanSec();
		acReplanSec.setReplanId(replanId);
		Ipage ipage = this.getIpage();
		acReplanSecList = (List) acReplanSecBo.findByPage(ipage, acReplanSec).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formreplan004 = formService.getFormData("replan004");
		acReplanSec = new AcReplanSec();
		acReplanSec.setSecId(secId);
		acReplanSec = acReplanSecBo.getById(acReplanSec);
		getObjValue(formreplan004, acReplanSec);
		return "detail";
	}
	
	/**
	 * 新增操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInput(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 AcReplanParm acReplanParm = new AcReplanParm();
		 acReplanParm.setReplanId(replanId);
		 acReplanParm = acReplanParmBo.getById(acReplanParm);
		 int secNo = acReplanParm.getSecNo();
		 List<AcReplanSec> acReplanSecList = new ArrayList<AcReplanSec>();
		 AcReplanSec acReplanSec = new AcReplanSec();
		 acReplanSec.setReplanId(replanId);
		 acReplanSecList = acReplanSecBo.getListByReplanId(acReplanSec);
		 if(acReplanSecList.size()>=secNo){
			 this.addActionError("还款分段信息总段数不能超过还款方案信息中的分段数！");
		 }
		 
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formreplan004 = formService.getFormData("replan004");
		 getFormValue(formreplan004);
		 validateFormData(formreplan004);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formreplan004 = formService.getFormData("replan004");
		 getFormValue(formreplan004);
		 validateFormData(formreplan004);
  	}
	public AcReplanSec getAcReplanSec() {
		return acReplanSec;
	}
	public void setAcReplanSec(AcReplanSec  acReplanSec) {
		this.acReplanSec = acReplanSec;
	}
	public List<AcReplanSec> getAcReplanSecList() {
		return acReplanSecList;
	}
	public void setAcReplanSecList(List<AcReplanSec> acReplanSecList) {
		this.acReplanSecList = acReplanSecList;
	}
	public AcReplanSecBo getAcReplanSecBo() {
		return acReplanSecBo;
	}
	public void setAcReplanSecBo(AcReplanSecBo acReplanSecBo) {
		this.acReplanSecBo = acReplanSecBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormreplan004() {
		return formreplan004;
	}
	public void setFormreplan004(FormData formreplan004) {
		this.formreplan004 = formreplan004;
	}
	public FormData getFormreplan003() {
		return formreplan003;
	}
	public void setFormreplan003(FormData formreplan003) {
		this.formreplan003 = formreplan003;
	}
	public void setSecId(String secId){
		this.secId = secId;
	}		
	public String getSecId(){
		return secId;
	}
	public String getReplanId() {
		return replanId;
	}
	public void setReplanId(String replanId) {
		this.replanId = replanId;
	}
	public AcReplanParmBo getAcReplanParmBo() {
		return acReplanParmBo;
	}
	public void setAcReplanParmBo(AcReplanParmBo acReplanParmBo) {
		this.acReplanParmBo = acReplanParmBo;
	}
}