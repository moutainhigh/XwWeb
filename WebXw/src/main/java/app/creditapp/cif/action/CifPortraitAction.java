package  app.creditapp.cif.action;
import java.util.List;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.cif.bo.CifPersInfBo;
import app.creditapp.cif.bo.CifPortraitBo;
import app.creditapp.cif.entity.CifPersInf;
import app.creditapp.cif.entity.CifPortrait;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: CifPortraitAction.java
 * Description:
 **/
public class CifPortraitAction extends BaseFormBean {

	//页面传值
	private CifPortrait cifPortrait;
	private List<CifPortrait> cifPortraitList;

	//注入CifPortraitBo
	private CifPortraitBo cifPortraitBo;
	private CifPersInf cifPersInf;
	private String query;
	private String cifNo;	
	private FormData formcif0002;
	private FormData formcif0003;
	private FormData formcif0004;
	private FormService formService = new FormService();
	//注入CifPersBo
	private CifPersInfBo cifPersInfBo;
	public CifPortraitAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formcif0003 = formService.getFormData("cif0003");
		cifPortrait = new CifPortrait();
		getFormValue(formcif0003);
		setObjValue(formcif0003, cifPortrait);
		Ipage ipage = this.getIpage();
		cifPortraitList = (List) cifPortraitBo.findByPage(ipage, cifPortrait).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formcif0004 = formService.getFormData("cif0004");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formcif0004 = formService.getFormData("cif0004");
		getFormValue(formcif0004);
		cifPortrait = new CifPortrait();
		setObjValue(formcif0004, cifPortrait);
		cifPortrait.setTxDate(User.getSys_date(this.getHttpRequest()));
		cifPortraitBo.insert(cifPortrait);
		getObjValue(formcif0004, cifPortrait);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formcif0004 = formService.getFormData("cif0004");
		getFormValue(formcif0004);
		cifPortrait = new CifPortrait();
		setObjValue(formcif0004, cifPortrait);
		cifPortrait.setUpDate(User.getSys_date(this.getHttpRequest()));
		cifPortraitBo.update(cifPortrait);
		getObjValue(formcif0004, cifPortrait);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formcif0003 = formService.getFormData("cif0003");
		cifPortrait = new CifPortrait();
		cifPortrait.setCifNo(cifNo);
		cifPortraitBo.del(cifPortrait);
		this.addActionMessage("删除成功");
		cifPortrait = new CifPortrait();
		Ipage ipage = this.getIpage();
		cifPortraitList = (List) cifPortraitBo.findByPage(ipage, cifPortrait).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
//		formcif0004 = formService.getFormData("cif0004");
		cifPortrait = new CifPortrait();
//		cifPortrait.setCifNo(cifNo);
//		formcif0002 = formService.getFormData("cif0002");
		cifPersInf = new CifPersInf();
		cifPersInf.setCifNo(cifNo);
		cifPersInf = cifPersInfBo.getById(cifPersInf);
		cifPortrait.setCifNo(cifPersInf.getCifNo());
		cifPortrait.setCifName(cifPersInf.getCifName());
		cifPortrait.setSex(cifPersInf.getSex());
		cifPortrait.setBirthDay(cifPersInf.getBirthDay());
		cifPortrait.setMarriage(cifPersInf.getMarriage());
		cifPortrait.setEdu(cifPersInf.getEdu());
		cifPortrait.setIncome(cifPersInf.getIncome());
		cifPortrait.setIdType(cifPersInf.getIdType());
		cifPortrait.setIdNo(cifPersInf.getIdNo());
		cifPortrait.setGrade(cifPersInf.getGrade());
		cifPortrait.setResTel(cifPersInf.getCommTel());
		cifPortrait.setResAddr(cifPersInf.getResAddr());
		cifPortrait.setCifType(cifPersInf.getCifType());
		cifPortrait.setWorkType(cifPortraitBo.getWorkType(cifPortrait));
		String ifCar = cifPortraitBo.getIfCar(cifPortrait);
		String ifRoom = cifPortraitBo.getIfRoom(cifPortrait);
		if(ifCar==null||"".equals(ifCar)){//判断是否有车
			cifPortrait.setIfCar("0");
		}else{
			cifPortrait.setIfCar(ifCar);
		}
		if(ifCar==null||"".equals(ifRoom)){//判断是否有房
			cifPortrait.setIfRoom("0");
		}else{
			cifPortrait.setIfRoom(ifRoom);
		}
		cifPortraitList = cifPortraitBo.getCifGroup(cifPortrait);//客户群体：融资客户，消费客户
		cifPortrait = cifPortraitBo.getById(cifPortrait);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formcif0004 = formService.getFormData("cif0004");
		 getFormValue(formcif0004);
		 validateFormData(formcif0004);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formcif0004 = formService.getFormData("cif0004");
		 getFormValue(formcif0004);
		 validateFormData(formcif0004);
  	}
	public CifPortrait getCifPortrait() {
		return cifPortrait;
	}
	public void setCifPortrait(CifPortrait  cifPortrait) {
		this.cifPortrait = cifPortrait;
	}
	public List<CifPortrait> getCifPortraitList() {
		return cifPortraitList;
	}
	public void setCifPortraitList(List<CifPortrait> cifPortraitList) {
		this.cifPortraitList = cifPortraitList;
	}
	public CifPortraitBo getCifPortraitBo() {
		return cifPortraitBo;
	}
	public void setCifPortraitBo(CifPortraitBo cifPortraitBo) {
		this.cifPortraitBo = cifPortraitBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormcif0004() {
		return formcif0004;
	}
	public void setFormcif0004(FormData formcif0004) {
		this.formcif0004 = formcif0004;
	}
	public FormData getFormcif0003() {
		return formcif0003;
	}
	public void setFormcif0003(FormData formcif0003) {
		this.formcif0003 = formcif0003;
	}
	public void setCifNo(String cifNo){
		this.cifNo = cifNo;
	}		
	public String getCifNo(){
		return cifNo;
	}
	public FormData getFormcif0002() {
		return formcif0002;
	}
	public void setFormcif0002(FormData formcif0002) {
		this.formcif0002 = formcif0002;
	}
	public CifPersInf getCifPersInf() {
		return cifPersInf;
	}
	public void setCifPersInf(CifPersInf cifPersInf) {
		this.cifPersInf = cifPersInf;
	}
	public CifPersInfBo getCifPersInfBo() {
		return cifPersInfBo;
	}
	public void setCifPersInfBo(CifPersInfBo cifPersInfBo) {
		this.cifPersInfBo = cifPersInfBo;
	}
}