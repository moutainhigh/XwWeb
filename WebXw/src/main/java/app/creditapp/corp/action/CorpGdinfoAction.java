package  app.creditapp.corp.action;
import java.util.List;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.corp.bo.CorpBaseBo;
import app.creditapp.corp.bo.CorpGdinfoBo;
import app.creditapp.corp.entity.CorpBase;
import app.creditapp.corp.entity.CorpCert;
import app.creditapp.corp.entity.CorpCont;
import app.creditapp.corp.entity.CorpGdinfo;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: CorpGdinfoAction.java
 * Description:
 **/
public class CorpGdinfoAction extends BaseFormBean {

	//页面传值
	private CorpGdinfo corpGdinfo;
	private CorpBase corpBase;
	private List<CorpGdinfo> corpGdinfoList;

	//注入CorpGdinfoBo
	private CorpGdinfoBo corpGdinfoBo;
	private CorpBaseBo corpBaseBo;

	
	private String query;
	private String gdId;
	private String brNo;
	
	private FormData formcoop0009;
	private FormData formcoop0010;
	private FormService formService = new FormService();
	
	public CorpGdinfoAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formcoop0009 = formService.getFormData("coop0009");
		corpGdinfo = new CorpGdinfo();
		getFormValue(formcoop0009);
		setObjValue(formcoop0009, corpGdinfo);
		corpGdinfo.setBrNo(User.getBrno(ServletActionContext.getRequest()));
		Ipage ipage = this.getIpage();
		corpGdinfoList = (List) corpGdinfoBo.findByPage(ipage, corpGdinfo).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formcoop0010 = formService.getFormData("coop0010");
		corpGdinfo = new CorpGdinfo();
		corpBase = new CorpBase();
		 
		corpBase.setBrNo(brNo);
		corpBase = corpBaseBo.getById(corpBase);
		corpGdinfo.setBrNo(brNo);
		corpGdinfo.setBrName(corpBase.getBrName());
		corpGdinfo.setCurNo("CNY");
		corpGdinfo.setDeptNo(User.getBrno(ServletActionContext.getRequest()));//登记部门
		corpGdinfo.setOpNo(User.getLoginid(ServletActionContext.getRequest()));//操作员
		corpGdinfo.setTxDate(User.getSys_date(ServletActionContext.getRequest()));//登记日期
//		corpGdinfo.setUpDate(User.getSys_date(ServletActionContext.getRequest()));//修改日期
//		corpGdinfo.setUpOpno(User.getLoginid(ServletActionContext.getRequest()));//修改人员
		getObjValue(formcoop0010, corpGdinfo);
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formcoop0010 = formService.getFormData("coop0010");
		getFormValue(formcoop0010);
		corpGdinfo = new CorpGdinfo();
		setObjValue(formcoop0010, corpGdinfo);
		corpGdinfo.setDeptNo(User.getBrno(ServletActionContext.getRequest()));//登记部门
		corpGdinfo.setOpNo(User.getLoginid(ServletActionContext.getRequest()));//操作员
		corpGdinfo.setTxDate(User.getSys_date(ServletActionContext.getRequest()));//登记日期
		//corpGdinfo.setUpDate(User.getSys_date(ServletActionContext.getRequest()));//修改日期
		//corpGdinfo.setUpOpno(User.getLoginid(ServletActionContext.getRequest()));//修改人员
		corpGdinfoBo.insert(corpGdinfo);
		getObjValue(formcoop0010, corpGdinfo);
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
		formcoop0010 = formService.getFormData("coop0010");
		getFormValue(formcoop0010);
		corpGdinfo = new CorpGdinfo();
		setObjValue(formcoop0010, corpGdinfo);
		//corpGdinfo.setDeptNo(User.getBrno(ServletActionContext.getRequest()));//登记部门
		//corpGdinfo.setOpNo(User.getLoginid(ServletActionContext.getRequest()));//操作员
		//corpGdinfo.setTxDate(User.getSys_date(ServletActionContext.getRequest()));//登记日期
		corpGdinfo.setUpDate(User.getSys_date(ServletActionContext.getRequest()));//修改日期
		corpGdinfo.setUpOpno(User.getLoginid(ServletActionContext.getRequest()));//修改人员
		corpGdinfoBo.update(corpGdinfo);
		getObjValue(formcoop0010, corpGdinfo);
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
		formcoop0009 = formService.getFormData("coop0009");
		corpGdinfo = new CorpGdinfo();
		corpGdinfo.setGdId(gdId);
		corpGdinfoBo.del(corpGdinfo);
		this.addActionMessage("删除成功");
		corpGdinfo = new CorpGdinfo();
		corpGdinfo.setBrNo(brNo);
		Ipage ipage = this.getIpage();
		corpGdinfoList = (List) corpGdinfoBo.findByPage(ipage, corpGdinfo).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formcoop0010 = formService.getFormData("coop0010");
		corpGdinfo = new CorpGdinfo();
		corpGdinfo.setGdId(gdId);
		corpGdinfo = corpGdinfoBo.getById(corpGdinfo);
		getObjValue(formcoop0010, corpGdinfo);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formcoop0010 = formService.getFormData("coop0010");
		 getFormValue(formcoop0010);
		 validateFormData(formcoop0010);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formcoop0010 = formService.getFormData("coop0010");
		 getFormValue(formcoop0010);
		 validateFormData(formcoop0010);
  	}
	
	/**
	    * 根据合作机构号显示所有的的股东 信息
	    * @return
	    * @throws Exception
	    */
	   public String listQuotaForCorp() throws Exception {
	   	ActionContext.initialize(ServletActionContext.getRequest(),
	       		ServletActionContext.getResponse());
	   	corpGdinfo = new CorpGdinfo();
	   	corpGdinfo.setBrNo(brNo);
	   	Ipage ipage = this.getIpage();
	   	corpGdinfoList = (List) corpGdinfoBo.findByPageQuotaForCorp(ipage, corpGdinfo).getResult();
	   	return "list";
	   }
    public String getBrNo() {
		return brNo;
	}
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}
	public CorpGdinfo getCorpGdinfo() {
		return corpGdinfo;
	}
	public void setCorpGdinfo(CorpGdinfo  corpGdinfo) {
		this.corpGdinfo = corpGdinfo;
	}
	public List<CorpGdinfo> getCorpGdinfoList() {
		return corpGdinfoList;
	}
	public void setCorpGdinfoList(List<CorpGdinfo> corpGdinfoList) {
		this.corpGdinfoList = corpGdinfoList;
	}
	public CorpGdinfoBo getCorpGdinfoBo() {
		return corpGdinfoBo;
	}
	public void setCorpGdinfoBo(CorpGdinfoBo corpGdinfoBo) {
		this.corpGdinfoBo = corpGdinfoBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormcoop0010() {
		return formcoop0010;
	}
	public void setFormcoop0010(FormData formcoop0010) {
		this.formcoop0010 = formcoop0010;
	}
	public FormData getFormcoop0009() {
		return formcoop0009;
	}
	public void setFormcoop0009(FormData formcoop0009) {
		this.formcoop0009 = formcoop0009;
	}
	public void setGdId(String gdId){
		this.gdId = gdId;
	}		
	public String getGdId(){
		return gdId;
	}
	public CorpBase getCorpBase() {
		return corpBase;
	}
	public void setCorpBase(CorpBase corpBase) {
		this.corpBase = corpBase;
	}
	public CorpBaseBo getCorpBaseBo() {
		return corpBaseBo;
	}
	public void setCorpBaseBo(CorpBaseBo corpBaseBo) {
		this.corpBaseBo = corpBaseBo;
	}
}