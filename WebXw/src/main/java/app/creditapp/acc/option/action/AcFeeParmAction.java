package  app.creditapp.acc.option.action;
import java.util.List;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.acc.option.bo.AcFeeParmBo;
import app.creditapp.acc.option.entity.AcFeeParm;
import app.creditapp.acc.option.entity.AcReplanParm;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: AcFeeParmAction.java
 * Description:
 **/
public class AcFeeParmAction extends BaseFormBean {

	//页面传值
	private AcFeeParm acFeeParm;
	private List<AcFeeParm> acFeeParmList;

	//注入AcFeeParmBo
	private AcFeeParmBo acFeeParmBo;

	private String query;
	private String feeParmId;		
	private FormData formfee101;
	private FormData formfee102;
	private FormService formService = new FormService();
	
	public AcFeeParmAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formfee101 = formService.getFormData("fee101");
		acFeeParm = new AcFeeParm();
		getFormValue(formfee101);
		setObjValue(formfee101, acFeeParm);
		Ipage ipage = this.getIpage();
		acFeeParmList = (List) acFeeParmBo.findByPage(ipage, acFeeParm).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfee102 = formService.getFormData("fee102");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfee102 = formService.getFormData("fee102");
		getFormValue(formfee102);
		acFeeParm = new AcFeeParm();
		setObjValue(formfee102, acFeeParm);
		acFeeParm.setOpNo(User.getLoginid(this.getHttpRequest()));
		acFeeParm.setTxDate(User.getSys_date(this.getHttpRequest()));
		acFeeParm.setUpDate(User.getSys_date(this.getHttpRequest()));
		acFeeParm.setFeeScenes("0");//默认失效
		acFeeParmBo.insert(acFeeParm);
		getObjValue(formfee102, acFeeParm);
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
		formfee102 = formService.getFormData("fee102");
		getFormValue(formfee102);
		acFeeParm = new AcFeeParm();
		setObjValue(formfee102, acFeeParm);
		acFeeParm.setUpDate(User.getSys_date(this.getHttpRequest()));
		acFeeParmBo.update(acFeeParm);
		getObjValue(formfee102, acFeeParm);
		this.addActionMessage("操作成功！");
		return "detail";
	}
	
	/**
	 * 启用操作
	 * @return
	 * @throws Exception
	 */
	public String openParm() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		acFeeParm = new AcFeeParm();
		acFeeParm.setFeeParmId(feeParmId);
		acFeeParm.setFeeSts("1");
		acFeeParmBo.updateSts(acFeeParm);
		this.addActionMessage("操作成功！");
		return findByPage();
	}
	
	/**
	 * 关闭操作
	 * @return
	 * @throws Exception
	 */
	public String closeParm() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		acFeeParm = new AcFeeParm();
		acFeeParm.setFeeParmId(feeParmId);
		acFeeParm.setFeeSts("0");
		acFeeParmBo.updateSts(acFeeParm);
		this.addActionMessage("操作成功！");
		return findByPage();
	}
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfee101 = formService.getFormData("fee101");
		acFeeParm = new AcFeeParm();
		acFeeParm.setFeeParmId(feeParmId);
		acFeeParmBo.del(acFeeParm);
		this.addActionMessage("删除成功");
		acFeeParm = new AcFeeParm();
		Ipage ipage = this.getIpage();
		acFeeParmList = (List) acFeeParmBo.findByPage(ipage, acFeeParm).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formfee102 = formService.getFormData("fee102");
		acFeeParm = new AcFeeParm();
		acFeeParm.setFeeParmId(feeParmId);
		acFeeParm = acFeeParmBo.getById(acFeeParm);
		getObjValue(formfee102, acFeeParm);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formfee102 = formService.getFormData("fee102");
		 getFormValue(formfee102);
		 validateFormData(formfee102);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formfee102 = formService.getFormData("fee102");
		 getFormValue(formfee102);
		 validateFormData(formfee102);
  	}
	public AcFeeParm getAcFeeParm() {
		return acFeeParm;
	}
	public void setAcFeeParm(AcFeeParm  acFeeParm) {
		this.acFeeParm = acFeeParm;
	}
	public List<AcFeeParm> getAcFeeParmList() {
		return acFeeParmList;
	}
	public void setAcFeeParmList(List<AcFeeParm> acFeeParmList) {
		this.acFeeParmList = acFeeParmList;
	}
	public AcFeeParmBo getAcFeeParmBo() {
		return acFeeParmBo;
	}
	public void setAcFeeParmBo(AcFeeParmBo acFeeParmBo) {
		this.acFeeParmBo = acFeeParmBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormfee102() {
		return formfee102;
	}
	public void setFormfee102(FormData formfee102) {
		this.formfee102 = formfee102;
	}
	public FormData getFormfee101() {
		return formfee101;
	}
	public void setFormfee101(FormData formfee101) {
		this.formfee101 = formfee101;
	}
	public void setFeeParmId(String feeParmId){
		this.feeParmId = feeParmId;
	}		
	public String getFeeParmId(){
		return feeParmId;
	}
}