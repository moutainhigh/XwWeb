package  app.creditapp.acc.loan.action;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import app.creditapp.acc.loan.bo.AcDebitSuspBo;
import app.creditapp.acc.loan.entity.AcDebitSusp;
import app.util.User;
import app.util.toolkit.Ipage;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: AcDebitSuspAction.java
 * Description:
 **/
public class AcDebitSuspAction extends BaseFormBean {

	//页面传值
	private AcDebitSusp acDebitSusp;
	private List<AcDebitSusp> acDebitSuspList;

	//注入AcDebitSuspBo
	private AcDebitSuspBo acDebitSuspBo;

	private String query;
	private String wsId;		
	private FormData formacc0021;
	private FormData formacc0032;
	private FormService formService = new FormService();
	
	public AcDebitSuspAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formacc0021 = formService.getFormData("acc0021");
		acDebitSusp = new AcDebitSusp();
		getFormValue(formacc0021);
		setObjValue(formacc0021, acDebitSusp);
		acDebitSusp.setBrNo(User.getBrno(this.getHttpRequest()));
		Ipage ipage = this.getIpage();
		acDebitSuspList = (List) acDebitSuspBo.findByPage(ipage, acDebitSusp).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formacc0032 = formService.getFormData("acc0032");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formacc0032 = formService.getFormData("acc0032");
		getFormValue(formacc0032);
		acDebitSusp = new AcDebitSusp();
		setObjValue(formacc0032, acDebitSusp);
		acDebitSusp.setBrNo(User.getBrno(this.getHttpRequest()));
		acDebitSusp.setTxDate(User.getSys_date(this.getHttpRequest()));
		acDebitSuspBo.insert(acDebitSusp);
		getObjValue(formacc0032, acDebitSusp);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formacc0032 = formService.getFormData("acc0032");
		getFormValue(formacc0032);
		acDebitSusp = new AcDebitSusp();
		setObjValue(formacc0032, acDebitSusp);
		acDebitSuspBo.update(acDebitSusp);
		getObjValue(formacc0032, acDebitSusp);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formacc0021 = formService.getFormData("acc0021");
		acDebitSusp = new AcDebitSusp();
		acDebitSusp.setWsId(wsId);
		acDebitSuspBo.del(acDebitSusp);
		this.addActionMessage("删除成功");
		acDebitSusp = new AcDebitSusp();
		acDebitSusp.setBrNo(User.getBrno(this.getHttpRequest()));
		Ipage ipage = this.getIpage();
		acDebitSuspList = (List) acDebitSuspBo.findByPage(ipage, acDebitSusp).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formacc0032 = formService.getFormData("acc0032");
		acDebitSusp = new AcDebitSusp();
		acDebitSusp.setWsId(wsId);
		acDebitSusp = acDebitSuspBo.getById(acDebitSusp);
		getObjValue(formacc0032, acDebitSusp);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formacc0032 = formService.getFormData("acc0032");
		 getFormValue(formacc0032);
		 validateFormData(formacc0032);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formacc0032 = formService.getFormData("acc0032");
		 getFormValue(formacc0032);
		 validateFormData(formacc0032);
  	}
	
	
	public AcDebitSusp getAcDebitSusp() {
		return acDebitSusp;
	}
	public void setAcDebitSusp(AcDebitSusp  acDebitSusp) {
		this.acDebitSusp = acDebitSusp;
	}
	public List<AcDebitSusp> getAcDebitSuspList() {
		return acDebitSuspList;
	}
	public void setAcDebitSuspList(List<AcDebitSusp> acDebitSuspList) {
		this.acDebitSuspList = acDebitSuspList;
	}
	public AcDebitSuspBo getAcDebitSuspBo() {
		return acDebitSuspBo;
	}
	public void setAcDebitSuspBo(AcDebitSuspBo acDebitSuspBo) {
		this.acDebitSuspBo = acDebitSuspBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormacc0032() {
		return formacc0032;
	}
	public void setFormacc0032(FormData formacc0032) {
		this.formacc0032 = formacc0032;
	}
	public FormData getFormacc0021() {
		return formacc0021;
	}
	public void setFormacc0021(FormData formacc0021) {
		this.formacc0021 = formacc0021;
	}
	public void setWsId(String wsId){
		this.wsId = wsId;
	}		
	public String getWsId(){
		return wsId;
	}
}