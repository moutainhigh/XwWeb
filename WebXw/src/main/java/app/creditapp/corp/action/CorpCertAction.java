package  app.creditapp.corp.action;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import app.creditapp.corp.bo.CorpBaseBo;
import app.creditapp.corp.bo.CorpCertBo;
import app.creditapp.corp.entity.CorpBase;
import app.creditapp.corp.entity.CorpCert;
import app.util.User;
import app.util.toolkit.Ipage;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: CorpCertAction.java
 * Description:
 **/
public class CorpCertAction extends BaseFormBean {

	//页面传值
	private CorpCert corpCert;
	private CorpBase corpBase;
	
	private List<CorpCert> corpCertList;

	//注入CorpCertBo
	private CorpCertBo corpCertBo;
	private CorpBaseBo corpBaseBo;

	
	
	private String query;
	private String certId;	
	private String brNo;
	
	private FormData formcoop0005;
	private FormData formcoop0006;
	private FormService formService = new FormService();
	
	public CorpCertAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formcoop0005 = formService.getFormData("coop0005");
		corpCert = new CorpCert();
		getFormValue(formcoop0005);
		setObjValue(formcoop0005, corpCert);
		corpCert.setBrNo(User.getBrno(ServletActionContext.getRequest()));
		Ipage ipage = this.getIpage();
		corpCertList = (List) corpCertBo.findByPage(ipage, corpCert).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formcoop0006 = formService.getFormData("coop0006");
		corpCert = new CorpCert();
		corpBase = new CorpBase();
		 
		corpBase.setBrNo(brNo);
		corpBase = corpBaseBo.getById(corpBase);
		corpCert.setBrNo(brNo);
		corpCert.setBrName(corpBase.getBrName());
		
		corpCert.setDeptNo(User.getBrno(ServletActionContext.getRequest()));//登记部门
		corpCert.setOpNo(User.getLoginid(ServletActionContext.getRequest()));//操作员
		corpCert.setTxDate(User.getSys_date(ServletActionContext.getRequest()));//登记日期
//		corpCert.setUpDate(User.getSys_date(ServletActionContext.getRequest()));//修改日期
//		corpCert.setUpOpNo(User.getLoginid(ServletActionContext.getRequest()));//修改人员
		getObjValue(formcoop0006, corpCert);
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formcoop0006 = formService.getFormData("coop0006");
		getFormValue(formcoop0006);
		corpCert = new CorpCert();
		setObjValue(formcoop0006, corpCert);
		corpCert.setDeptNo(User.getBrno(ServletActionContext.getRequest()));//登记部门
		corpCert.setOpNo(User.getLoginid(ServletActionContext.getRequest()));//操作员
		corpCert.setTxDate(User.getSys_date(ServletActionContext.getRequest()));//登记日期
		//corpCert.setUpDate(User.getSys_date(ServletActionContext.getRequest()));//修改日期
		//corpCert.setUpOpno(User.getLoginid(ServletActionContext.getRequest()));//修改人员
		corpCertBo.insert(corpCert);
		getObjValue(formcoop0006, corpCert);
		this.addActionMessage("操作成功");
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formcoop0006 = formService.getFormData("coop0006");
		getFormValue(formcoop0006);
		corpCert = new CorpCert();
		setObjValue(formcoop0006, corpCert);
//		corpCert.setDeptNo(User.getBrno(ServletActionContext.getRequest()));//登记部门
//		corpCert.setOpNo(User.getLoginid(ServletActionContext.getRequest()));//操作员
//		corpCert.setTxDate(User.getSys_date(ServletActionContext.getRequest()));//登记日期
		corpCert.setUpDate(User.getSys_date(ServletActionContext.getRequest()));//修改日期
		corpCert.setUpOpNo(User.getLoginid(ServletActionContext.getRequest()));//修改人员
		corpCertBo.update(corpCert);
		getObjValue(formcoop0006, corpCert);
		this.addActionMessage("操作成功");
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formcoop0005 = formService.getFormData("coop0005");
		corpCert = new CorpCert();
		corpCert.setCertId(certId);
		corpCertBo.del(corpCert);
		this.addActionMessage("删除成功");
		corpCert = new CorpCert();
		corpCert.setBrNo(brNo);
		Ipage ipage = this.getIpage();
		corpCertList = (List) corpCertBo.findByPage(ipage, corpCert).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formcoop0006 = formService.getFormData("coop0006");
		corpCert = new CorpCert();
		corpCert.setCertId(certId);
		corpCert = corpCertBo.getById(corpCert);
		getObjValue(formcoop0006, corpCert);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formcoop0006 = formService.getFormData("coop0006");
		 getFormValue(formcoop0006);
		 validateFormData(formcoop0006);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formcoop0006 = formService.getFormData("coop0006");
		 getFormValue(formcoop0006);
		 validateFormData(formcoop0006);
  	}
	
	/**
	    * 根据合作机构号显示所有的的资质信息
	    * @return
	    * @throws Exception
	    */
	   public String listQuotaForCorp() throws Exception {
	   	ActionContext.initialize(ServletActionContext.getRequest(),
	       		ServletActionContext.getResponse());
	   	corpCert = new CorpCert();
	   	corpCert.setBrNo(brNo);
	   	Ipage ipage = this.getIpage();
	   	corpCertList = (List) corpCertBo.findByPageQuotaForCorp(ipage, corpCert).getResult();
	   	return "list";
	   }
	   public String listQuotaForCorp_Read() throws Exception {
		   	ActionContext.initialize(ServletActionContext.getRequest(),
		       		ServletActionContext.getResponse());
		   	corpCert = new CorpCert();
		   	corpCert.setBrNo(brNo);
		   	Ipage ipage = this.getIpage();
		   	corpCertList = (List) corpCertBo.findByPageQuotaForCorp(ipage, corpCert).getResult();
		   	return "list_Read";
		   }

   public CorpBaseBo getCorpBaseBo() {
		return corpBaseBo;
	}
	public void setCorpBaseBo(CorpBaseBo corpBaseBo) {
		this.corpBaseBo = corpBaseBo;
	}
    public CorpBase getCorpBase() {
	   return corpBase;
	}
	public void setCorpBase(CorpBase corpBase) {
		this.corpBase = corpBase;
	}   
   
	public String getBrNo() {
		return brNo;
	}
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}	
	public CorpCert getCorpCert() {
		return corpCert;
	}
	public void setCorpCert(CorpCert  corpCert) {
		this.corpCert = corpCert;
	}
	public List<CorpCert> getCorpCertList() {
		return corpCertList;
	}
	public void setCorpCertList(List<CorpCert> corpCertList) {
		this.corpCertList = corpCertList;
	}
	public CorpCertBo getCorpCertBo() {
		return corpCertBo;
	}
	public void setCorpCertBo(CorpCertBo corpCertBo) {
		this.corpCertBo = corpCertBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormcoop0006() {
		return formcoop0006;
	}
	public void setFormcoop0006(FormData formcoop0006) {
		this.formcoop0006 = formcoop0006;
	}
	public FormData getFormcoop0005() {
		return formcoop0005;
	}
	public void setFormcoop0005(FormData formcoop0005) {
		this.formcoop0005 = formcoop0005;
	}
	public void setCertId(String certId){
		this.certId = certId;
	}		
	public String getCertId(){
		return certId;
	}
}