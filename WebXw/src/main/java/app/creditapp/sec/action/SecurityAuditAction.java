package  app.creditapp.sec.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.struts2.ServletActionContext;

import app.creditapp.sec.bo.SecurityAuditBo;
import app.creditapp.sec.entity.PasswordRegexp;
import app.creditapp.sec.entity.SecurityAudit;
import app.oscache.MBaseCache;
import app.util.User;
import app.util.toolkit.Ipage;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;


/**
* Title: SecurityAuditAction.java
* Description:
**/

public class SecurityAuditAction extends BaseFormBean {

	//页面传值
	private SecurityAudit secAudit;
	private List<SecurityAudit> secAuditList;

	//注入SecurityAuditBo
	private SecurityAuditBo secAuditBo;

	private String itemNo;
	private String itemNoStr;
	private String query;
	private String view;
	private String audit;
	private String isUse;
	private String itemValues;
	private String itemValueStr;
	private String codeType;
	private FormData formsec0021;
	private FormData formsec0020;
	//接收修改密码信息 原始密码@新改密码
	private String changePWInfo; 
	private String checkRegIndex;
	private String pwd2 ;
	
	public String getPwd2() {
		return pwd2;
	}

	public void setPwd2(String pwd2) {
		this.pwd2 = pwd2;
	}
	public String getChangePWInfo() {
		return changePWInfo;
	}

	public void setChangePWInfo(String changePWInfo) {
		this.changePWInfo = changePWInfo;
	}
	private  FormService formService = new FormService();
	
	public SecurityAuditAction() {
		formsec0021 = formService.getFormData("sec0021");
//		formsec0020 = formService.getFormData("sec0020");
		query = "";
		view = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	 public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		secAudit = new SecurityAudit();
//		getFormValue(formsec0020);
//		setObjValue(formsec0020, secAudit);
		Ipage ipage = this.getIpage();
		if(audit!=null){
			if(audit.equals("SL")){//密码最长使用期限
				secAudit.setCodeType(audit);
			}else if(audit.equals("SF")){//密码输入错误后的容忍次数
				secAudit.setCodeType(audit);
			}else if(audit.equals("SR")){//是否记录用户及角色变更日志
				secAudit.setCodeType(audit);
			}else{//密码校验
				secAudit.setCodeType(audit);
			}
			secAuditList = (List) secAuditBo.findByPage(ipage, secAudit).getResult();
		}else{
			System.out.println("@@@@#"+itemNo);
			secAudit = secAuditBo.getById(itemNo);
			SecurityAudit sa = new SecurityAudit();
			sa.setCodeType(secAudit.getCodeType());
			secAuditList = (List) secAuditBo.findByPage(ipage, sa).getResult();
		}
		return "list";
	}
	
	/**
	 * 新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		getFormValue(formsec0021);
		secAudit = new SecurityAudit();
		setObjValue(formsec0021, secAudit);
		secAuditBo.insert(secAudit);
		getObjValue(formsec0021, secAudit);
		itemNo= secAudit.getItemNo();
		query = "query";
		return "detail";
	}
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		secAudit = new SecurityAudit();
		getFormValue(formsec0021);
		setObjValue(formsec0021, secAudit);
		secAuditBo.update(secAudit);
		getObjValue(formsec0021, secAudit);
		itemNo= secAudit.getItemNo();
		secAudit = secAuditBo.getById(itemNo);
		if (secAudit.getIsEdit()==0) {
			this.changeFormProperty(formsec0021, "itemValues", "readonly", "1");
		}
		getObjValue(formsec0021, secAudit);
		itemNo= secAudit.getItemNo();
		return "detail";
	}
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		secAuditBo.del(itemNo);
		this.addActionMessage("删除成功");
		secAudit = new SecurityAudit();
		Ipage ipage = this.getIpage();
		secAuditList = (List) secAuditBo.findByPage(ipage, secAudit).getResult();
		return "list";
	}

	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		secAudit = secAuditBo.getById(itemNo);
		if (secAudit.getIsEdit()==0) {
			this.changeFormProperty(formsec0021, "itemValues", "readonly", "1");
		}
		getObjValue(formsec0021, secAudit);
		itemNo= secAudit.getItemNo();
		return "detail";
	}
	/**
	 * 修改是否启用
	 * @return
	 * @throws Exception
	 */
	public String updateIsUse() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		secAudit = new SecurityAudit();
		secAudit = secAuditBo.getById(itemNo);
		isUse = "1".equals(isUse)?"0":"1";
		secAudit.setIsUse(isUse);
		secAuditBo.updateIsUse(secAudit);
		SecurityAudit sa = new SecurityAudit();
		sa.setCodeType(secAudit.getCodeType());
		Ipage ipage = this.getIpage();
		if(itemNo.equals("10")){
			MBaseCache.getCache().reloadSecurity();
		}
		secAuditList = (List) secAuditBo.findByPage(ipage, sa).getResult();
		return "list";
	}
	public String getTableList() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		String[] itemValuesArray = itemValueStr.split("@");
		String[] itemNoArray = itemNoStr.split("@");
		secAudit = new SecurityAudit();
		for(int i =0;i<itemNoArray.length;i++){
			secAudit = secAuditBo.getById(itemNoArray[i].trim());
			if(secAudit.getIsEdit()==0){
				secAudit.setItemValues("");
			}else{
				secAudit.setItemValues(itemValuesArray[i].trim());
			}
			secAuditBo.update(secAudit);
		}
		SecurityAudit sa = new SecurityAudit();
		sa.setCodeType(secAudit.getCodeType());
		Ipage ipage = this.getIpage();
		secAuditList = (List) secAuditBo.findByPage(ipage, sa).getResult();
		addActionMessage("已保存成功");
		return "list";
	}
	/**
	 * 跳转新增或修改页面
	 * @return
	 * @throws Exception
	 */
	public String tomodifyPwd() throws Exception {
		return "success";
	}
	public String modifyPwd() throws Exception{
		this.getHttpResponse().setContentType("text/html;charset=utf-8");
		PrintWriter out = this.getHttpResponse().getWriter();
		try{
			changePWInfo = "[{pwdmes:'" +"seak" + "'}]";
			String userId = User.getUserid(this.getHttpRequest());
			List<SecurityAudit> list= new ArrayList<SecurityAudit>();
			SecurityAudit secAudit =new SecurityAudit();
			secAudit.setIsUse("1");
			list =  secAuditBo.getListForSec(secAudit);
			for(int i=0;i<list.size();i++){
				String itemNo = list.get(i).getItemNo();
			//密码长度最小值，值选择：0-20位
				if(itemNo.equals("1")){
					if(pwd2.length()<Integer.valueOf((list.get(i).getItemValues()))){
						changePWInfo = "[{pwdmes:'" + "密码长度必须大于等于" +list.get(i).getItemValues()+"位：" + "'}]";
						out.println(changePWInfo);
						return null;	
					}
				}
			//	必须包含英文大写字母(A 到 Z)
				if(itemNo.equals("2")){
					Pattern pattern = Pattern.compile(".*[A-Z]{1,}.*");
					Matcher m = pattern.matcher(pwd2);
					if(!m.matches()){
						changePWInfo = "[{pwdmes:'" + "密码必须包含英文“大写字母”：" + "'}]";
						out.println(changePWInfo);
						return null;
					}
				}
			//	必须包含英文小写字母(a 到 z)
				if(itemNo.equals("3")){
					Pattern pattern = Pattern.compile(".*[a-z]{1,}.*");
					Matcher m = pattern.matcher(pwd2);
					if(!m.matches()){
						changePWInfo = "[{pwdmes:'" + "密码必须包含英文“小写字母”：" + "'}]";
						out.println(changePWInfo);
						return null;
					}
				}
			//	必须包含10 个基本数字(0 到 9)
				if(itemNo.equals("4")){
					Pattern pattern = Pattern.compile(".*[0-9]{1,}.*");
					Matcher m = pattern.matcher(pwd2);
					if(!m.matches()){
						changePWInfo = "[{pwdmes:'" +"密码必须包含“数字”：" + "'}]";
						out.println(changePWInfo);
						return null;
					}
				}
			//	必须包含特殊字符(!、@、$、*、.)
				if(itemNo.equals("5")){
					Pattern pattern = Pattern.compile(".*[!@$*.]{1,}.*");
					Matcher m = pattern.matcher(pwd2);
					if(!m.matches()){
						changePWInfo = "[{pwdmes:'" + "密码必须包含“特殊字符”：" + "'}]";
						out.println(changePWInfo);
						return null;
					}
				}
			//	不能包含用户的帐户名
				if(itemNo.equals("6")){
					if(!(pwd2.indexOf(userId) == -1)){
						changePWInfo = "[{pwdmes:'" +"密码不能包含用户的帐户名：" + "'}]";
						out.println(changePWInfo);
						return null;
					}
				}
			}
			out.println(changePWInfo);
			return null;
		}catch(Exception e){
			changePWInfo = "[{pwdmes:'" +"请与管理员联系！" + "'}]";
			out.println(changePWInfo);
			return null;
		}
	}
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 getFormValue(formsec0021);
		 validateFormData(formsec0021);
   }
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 getFormValue(formsec0021);
		 validateFormData(formsec0021);
  }
	
	/********************************************南充商行重写安全审计模块******************************/
	
	public String findSecurityConfig(){
		secAuditList = secAuditBo.findAuditListByCode(codeType);
//		Map<Integer,String> passrdRegxpMap = new LinkedHashMap<Integer, String>();
//		for(PasswordRegexp reg:PasswordRegexp.values()){
//		}
		for(SecurityAudit audit:secAuditList){
			if(audit.getItemNo().equals("1")){
				checkRegIndex = audit.getItemValues();
				break;
			}
		}
		ServletActionContext.getRequest().setAttribute("auditlist", secAuditList);
		return "newList";
	}
	
	public String updateAuditInfo()throws Exception{
		this.getHttpResponse().setContentType("text/html;charset=utf-8");
		PrintWriter out = this.getHttpResponse().getWriter();
		secAudit = new SecurityAudit();
		secAudit.setItemNo(itemNo);
		//TODO 添加修改的值
		secAudit.setItemValues(itemValueStr);
		try {
			secAuditBo.update(secAudit);
			MBaseCache.getCache().reloadSecurity();
			out.println("SUCC");
		} catch (Exception e) {
			e.printStackTrace();
			out.println("修改过程中出现错误，请跟管理员联系");
		}
		return null;
	}
	
	public String updateIsUseNew() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		secAudit = new SecurityAudit();
		secAudit.setItemNo(itemNo);
		isUse = "1".equals(isUse)?"0":"1";
		secAudit.setIsUse(isUse);
		secAuditBo.updateIsUse(secAudit);
		MBaseCache.getCache().reloadSecurity();
		return findSecurityConfig();
//		return "list";
	}
	
	public String updateAllAuditInfo()throws Exception{
		//TODO 批量修改方法
		return findSecurityConfig();
	}
	
	public SecurityAudit getSecurityAudit() {
		return secAudit;
	}
	public void setSecurityAudit(SecurityAudit  secAudit) {
		this.secAudit = secAudit;
	}
	public List<SecurityAudit> getSecurityAuditList() {
		return secAuditList;
	}
	public void setSecurityAuditList(List<SecurityAudit> secAuditList) {
		this.secAuditList = secAuditList;
	}
	public SecurityAuditBo getSecurityAuditBo() {
		return secAuditBo;
	}
	public void setSecurityAuditBo(SecurityAuditBo secAuditBo) {
		this.secAuditBo = secAuditBo;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	public FormData getFormsec0021() {
		return formsec0021;
	}
	public void setFormsec0021(FormData formsec0021) {
		this.formsec0021 = formsec0021;
	}
	public FormData getFormsec0020() {
		return formsec0020;
	}
	public void setFormsec0020(FormData formsec0020) {
		this.formsec0020 = formsec0020;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public String getAudit() {
		return audit;
	}
	public void setAudit(String audit) {
		this.audit = audit;
	}
	public String getItemValues() {
		return itemValues;
	}
	public void setItemValues(String itemValues) {
		this.itemValues = itemValues;
	}
	public String getCodeType() {
		return codeType;
	}
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getItemValueStr() {
		return itemValueStr;
	}

	public void setItemValueStr(String itemValueStr) {
		this.itemValueStr = itemValueStr;
	}

	public String getItemNoStr() {
		return itemNoStr;
	}

	public void setItemNoStr(String itemNoStr) {
		this.itemNoStr = itemNoStr;
	}

	public SecurityAudit getSecAudit() {
		return secAudit;
	}

	public void setSecAudit(SecurityAudit secAudit) {
		this.secAudit = secAudit;
	}

	public List<SecurityAudit> getSecAuditList() {
		return secAuditList;
	}

	public void setSecAuditList(List<SecurityAudit> secAuditList) {
		this.secAuditList = secAuditList;
	}

	public SecurityAuditBo getSecAuditBo() {
		return secAuditBo;
	}

	public void setSecAuditBo(SecurityAuditBo secAuditBo) {
		this.secAuditBo = secAuditBo;
	}

	public String getCheckRegIndex() {
		return checkRegIndex;
	}

	public void setCheckRegIndex(String checkRegIndex) {
		this.checkRegIndex = checkRegIndex;
	}
}
