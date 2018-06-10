package  app.creditapp.cif.action;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

import app.creditapp.cif.bo.CifPersHisBo;
import app.creditapp.cif.entity.CifPersHis;
import app.util.User;
import app.util.toolkit.Ipage;

/**
 * Title: CifPersHisAction.java
 * Description:
 **/
public class CifPersHisAction extends BaseFormBean {

	//页面传值
	private CifPersHis cifPersHis;
	private List<CifPersHis> cifPersHisList;
	private String backSts;
	
	//注入CifPersHisBo
	private CifPersHisBo cifPersHisBo;

	private String query;
	private String recId;	
	private String cifNo;
	
	private FormData formcif0008;
	private FormData formcif0009;
	private FormService formService = new FormService();
	
	public CifPersHisAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formcif0008 = formService.getFormData("cif0008");
		cifPersHis = new CifPersHis();
		getFormValue(formcif0008);
		setObjValue(formcif0008, cifPersHis);
		cifPersHis.setBrNo(User.getBrno(ServletActionContext.getRequest()));
		Ipage ipage = this.getIpage();
		cifPersHisList = (List) cifPersHisBo.findByPage(ipage, cifPersHis).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formcif0009 = formService.getFormData("cif0009");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formcif0009 = formService.getFormData("cif0009");
		getFormValue(formcif0009);
		cifPersHis = new CifPersHis();
		setObjValue(formcif0009, cifPersHis);
		cifPersHis.setBrNo(User.getBrno(ServletActionContext.getRequest()));
		cifPersHis.setTxDate(User.getSys_date(ServletActionContext.getRequest()));
		cifPersHis.setOpNo(User.getLoginid(ServletActionContext.getRequest()));
		cifPersHisBo.insert(cifPersHis);
		getObjValue(formcif0009, cifPersHis);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formcif0009 = formService.getFormData("cif0009");
		getFormValue(formcif0009);
		cifPersHis = new CifPersHis();
		setObjValue(formcif0009, cifPersHis);
		cifPersHis.setUpDate(User.getSys_date(ServletActionContext.getRequest()));
		cifPersHisBo.update(cifPersHis);
		getObjValue(formcif0009, cifPersHis);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formcif0008 = formService.getFormData("cif0008");
		cifPersHis = new CifPersHis();
		cifPersHis.setRecId(recId);
		cifPersHisBo.del(cifPersHis);
		this.addActionMessage("删除成功");
		cifPersHis = new CifPersHis();
		cifPersHis.setBrNo(User.getBrno(ServletActionContext.getRequest()));
		Ipage ipage = this.getIpage();
		cifPersHisList = (List) cifPersHisBo.findByPage(ipage, cifPersHis).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formcif0009 = formService.getFormData("cif0009");
		cifPersHis = new CifPersHis();
		cifPersHis.setRecId(recId);
		cifPersHis = cifPersHisBo.getById(cifPersHis);
		ServletActionContext.getRequest().setAttribute("cfn", cifPersHis.getCifNo());
		getObjValue(formcif0009, cifPersHis);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formcif0009 = formService.getFormData("cif0009");
		 getFormValue(formcif0009);
		 validateFormData(formcif0009);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formcif0009 = formService.getFormData("cif0009");
		 getFormValue(formcif0009);
		 validateFormData(formcif0009);
  	}
	
	 /**
     * 根据客户号显示所有的的历史记录
     * @return
     * @throws Exception
     */
    public String listQuotaForCif() throws Exception {
    	ActionContext.initialize(ServletActionContext.getRequest(),
        		ServletActionContext.getResponse());
    	cifPersHis = new CifPersHis();
    	cifPersHis.setCifNo(cifNo);
    	Ipage ipage = this.getIpage();
    	cifPersHisList = (List) cifPersHisBo.findByPageQuotaForCif(ipage, cifPersHis).getResult();
    	return "list";
    }
    /**
     * 根据客户号显示所有的的历史记录
     * @return
     * @throws Exception
     */
    public String listQuotaForCif_Read() throws Exception {
    	ActionContext.initialize(ServletActionContext.getRequest(),
        		ServletActionContext.getResponse());
    	cifPersHis = new CifPersHis();
    	cifPersHis.setCifNo(cifNo);
    	Ipage ipage = this.getIpage();
    	cifPersHisList = (List) cifPersHisBo.findByPageQuotaForCif(ipage, cifPersHis).getResult();
    	return "list_Read";
    }
	
	public CifPersHis getCifPersHis() {
		return cifPersHis;
	}
	public void setCifPersHis(CifPersHis  cifPersHis) {
		this.cifPersHis = cifPersHis;
	}
	public List<CifPersHis> getCifPersHisList() {
		return cifPersHisList;
	}
	public void setCifPersHisList(List<CifPersHis> cifPersHisList) {
		this.cifPersHisList = cifPersHisList;
	}
	public CifPersHisBo getCifPersHisBo() {
		return cifPersHisBo;
	}
	public void setCifPersHisBo(CifPersHisBo cifPersHisBo) {
		this.cifPersHisBo = cifPersHisBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormcif0009() {
		return formcif0009;
	}
	public void setFormcif0009(FormData formcif0009) {
		this.formcif0009 = formcif0009;
	}
	public FormData getFormcif0008() {
		return formcif0008;
	}
	public void setFormcif0008(FormData formcif0008) {
		this.formcif0008 = formcif0008;
	}
	public void setRecId(String recId){
		this.recId = recId;
	}		
	public String getRecId(){
		return recId;
	}
	public String getCifNo() {
		return cifNo;
	}
	public void setCifNo(String cifNo) {
		this.cifNo = cifNo;
	}
	public String getBackSts() {
		return backSts;
	}
	public void setBackSts(String backSts) {
		this.backSts = backSts;
	}
}