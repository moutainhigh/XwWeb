package  app.creditapp.sys.action;
import java.io.PrintWriter;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.sys.bo.SysButtonBo;
import app.creditapp.sys.entity.SysButton;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
* Title: SysButtonAction.java
* Description:
**/
public class SysButtonAction extends BaseFormBean {

	//页面传值
	private SysButton sysButton;
	private List<SysButton> sysButtonList;

	//注入SysButtonBo
	private SysButtonBo sysButtonBo;

	private String query;
	private String menuNo;
	private String menu_no;
	private String button_no;
	private String button_desc;		
	private FormData formsys0031;
	private FormData formsys0032;
	private FormService formService = new FormService();
	
	public SysButtonAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	 public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formsys0031 = formService.getFormData("sys0031");
		sysButton = new SysButton();
		getFormValue(formsys0031);
		setObjValue(formsys0031, sysButton);
		Ipage ipage = this.getIpage();
		sysButtonList = (List) sysButtonBo.findByPage(ipage, sysButton).getResult();
		return "list";
	}
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys0032 = formService.getFormData("sys0032");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys0032 = formService.getFormData("sys0032");
		getFormValue(formsys0032);
		sysButton = new SysButton();
		setObjValue(formsys0032, sysButton);
		sysButtonBo.insert(sysButton);
		getObjValue(formsys0032, sysButton);
		return "detail";
	}
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys0032 = formService.getFormData("sys0032");
		getFormValue(formsys0032);
		sysButton = new SysButton();
		setObjValue(formsys0032, sysButton);
		sysButtonBo.update(sysButton);
		getObjValue(formsys0032, sysButton);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys0031 = formService.getFormData("sys0031");
		sysButton = new SysButton();
		sysButton.setMenu_no(menu_no);
		sysButton.setButton_no(button_no);
		sysButtonBo.del(sysButton);
		this.addActionMessage("删除成功");
		sysButton = new SysButton();
		Ipage ipage = this.getIpage();
		sysButtonList = (List) sysButtonBo.findByPage(ipage, sysButton).getResult();
		return "list";
	}

	
		/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formsys0032 = formService.getFormData("sys0032");
		sysButton = new SysButton();
		sysButton.setMenu_no(menu_no);
		sysButton.setButton_no(button_no);
		sysButton = sysButtonBo.getById(sysButton);
		getObjValue(formsys0032, sysButton);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formsys0032 = formService.getFormData("sys0032");
		 getFormValue(formsys0032);
		 validateFormData(formsys0032);
   }
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formsys0032 = formService.getFormData("sys0032");
		 getFormValue(formsys0032);
		 validateFormData(formsys0032);
  	}

	 public String findAllByMenu() throws Exception {
		 ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		 sysButton = new SysButton();
		 System.out.println("从页面获取的menuNo:"+menuNo);
		 menu_no = menuNo;
		 sysButton.setMenu_no(menu_no);
		 sysButtonList = sysButtonBo.findAllByMenu(sysButton);
		 return "list";
	 }

	public String insertBtn() throws Exception{
		System.out.println("从页面获取的menu_no:"+menu_no);
		System.out.println("从页面获取的button_no:"+button_no);
		button_no = java.net.URLDecoder.decode(new String(button_no.getBytes("GBK"),"ISO-8859-1"), "UTF-8");
		System.out.println("转换编码后的button_no:"+button_no);
		button_desc = java.net.URLDecoder.decode(new String(button_desc.getBytes("GBK"),"ISO-8859-1"), "UTF-8");
		
		this.getHttpResponse().setContentType("text/html;charset=utf-8"); 
		PrintWriter out = this.getHttpResponse().getWriter(); 
		String result = "success";
		try{
			sysButton = new SysButton();
			sysButton.setMenu_no(menu_no);
			sysButton.setButton_no(button_no);
			sysButton.setButton_desc(button_desc);
			sysButtonBo.insert(sysButton);
		}catch(Exception e){
			e.printStackTrace();
			result = "fail";
		}
		out.print(result);
		return null;
	}
	
	public String updateBtn() throws Exception{
		button_no = java.net.URLDecoder.decode(new String(button_no.getBytes("GBK"),"ISO-8859-1"), "UTF-8");
		button_desc = java.net.URLDecoder.decode(new String(button_desc.getBytes("GBK"),"ISO-8859-1"), "UTF-8");
		
		this.getHttpResponse().setContentType("text/html;charset=utf-8"); 
		PrintWriter out = this.getHttpResponse().getWriter(); 
		String result = "success";
		try{
			sysButton = new SysButton();
			sysButton.setMenu_no(menu_no);
			sysButton.setButton_no(button_no);
			sysButton.setButton_desc(button_desc);
			sysButtonBo.update(sysButton);
		}catch(Exception e){
			e.printStackTrace();
			result = "fail";
		}
		out.print(result);
		return null;
	}
	
	public String deleteBtn() throws Exception{
		button_no = java.net.URLDecoder.decode(new String(button_no.getBytes("GBK"),"ISO-8859-1"), "UTF-8");
		
		this.getHttpResponse().setContentType("text/html;charset=utf-8"); 
		PrintWriter out = this.getHttpResponse().getWriter(); 
		String result = "success";
		try{
			sysButton = new SysButton();
			sysButton.setMenu_no(menu_no);
			sysButton.setButton_no(button_no);
			sysButtonBo.del(sysButton);
		}catch(Exception e){
			e.printStackTrace();
			result = "fail";
		}
		out.print(result);
		return null;
	}
	
	
	public SysButton getSysButton() {
		return sysButton;
	}
	public void setSysButton(SysButton sysButton) {
		this.sysButton = sysButton;
	}
	public List<SysButton> getSysButtonList() {
		return sysButtonList;
	}
	public void setSysButtonList(List<SysButton> sysButtonList) {
		this.sysButtonList = sysButtonList;
	}
	public SysButtonBo getSysButtonBo() {
		return sysButtonBo;
	}
	public void setSysButtonBo(SysButtonBo sysButtonBo) {
		this.sysButtonBo = sysButtonBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getMenu_no() {
		return menu_no;
	}
	public void setMenu_no(String menuNo) {
		menu_no = menuNo;
	}
	public String getMenuNo() {
		return menuNo;
	}
	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}
	public String getButton_no() {
		return button_no;
	}
	public void setButton_no(String buttonNo) {
		button_no = buttonNo;
	}
	public String getButton_desc() {
		return button_desc;
	}
	public void setButton_desc(String buttonDesc) {
		button_desc = buttonDesc;
	}
	public FormData getFormsys0031() {
		return formsys0031;
	}
	public void setFormsys0031(FormData formsys0031) {
		this.formsys0031 = formsys0031;
	}
	public FormData getFormsys0032() {
		return formsys0032;
	}
	public void setFormsys0032(FormData formsys0032) {
		this.formsys0032 = formsys0032;
	}
	public FormService getFormService() {
		return formService;
	}
	public void setFormService(FormService formService) {
		this.formService = formService;
	}
	
//	public SysButton getSysButton() {
//		return sysButton;
//	}
//	public void setSysButton(SysButton  sysButton) {
//		this.sysButton = sysButton;
//	}
//	public List<SysButton> getSysButtonList() {
//		return sysButtonList;
//	}
//	public void setSysButtonList(List<SysButton> sysButtonList) {
//		this.sysButtonList = sysButtonList;
//	}
//	public SysButtonBo getSysButtonBo() {
//		return sysButtonBo;
//	}
//	public void setSysButtonBo(SysButtonBo sysButtonBo) {
//		this.sysButtonBo = sysButtonBo;
//	}
//	public String getQuery() {
//		return query;
//	}
//	public void setQuery(String query) {
//		this.query = query;
//	}
//	public FormData getFormsys0032() {
//		return formsys0032;
//	}
//	public void setFormsys0032(FormData formsys0032) {
//		this.formsys0032 = formsys0032;
//	}
//	public FormData getFormsys0031() {
//		return formsys0031;
//	}
//	public void setFormsys0031(FormData formsys0031) {
//		this.formsys0031 = formsys0031;
//	}
//	public void setMenuNo(String menuNo){
//		this.menuNo = menuNo;
//	}		
//	public void setButtonNo(String buttonNo){
//		this.buttonNo = buttonNo;
//	}		
//	public String getMenuNo(){
//		return menuNo;
//	}
//	public String getButtonNo(){
//		return buttonNo;
//	}
//	public String getButtonDesc() {
//		return buttonDesc;
//	}
//	public void setButtonDesc(String buttonDesc) {
//		this.buttonDesc = buttonDesc;
//	}
}