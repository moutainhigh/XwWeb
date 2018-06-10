package app.creditapp.wkf.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import app.creditapp.sys.bo.SysUserBo;
import app.creditapp.sys.entity.SysUser;
import app.creditapp.wkf.bo.WkfApprovalUserBo;
import app.creditapp.wkf.entity.WkfApprovalUser;
import app.util.User;
import app.util.toolkit.Ipage;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: WkfApprovalUserAction.java Description:
 * 
 */
public class WkfApprovalUserAction extends BaseFormBean {

	private static final long serialVersionUID = -5939313515181895639L;
	// 页面传值
	private WkfApprovalUser wkfApprovalUser;
	private List<WkfApprovalUser> wkfApprovalUserList;
	private List<Map<String, Object>> wkfApprovalUserMapList;

	// 注入WkfApprovalUserBo
	private WkfApprovalUserBo wkfApprovalUserBo;
	private SysUserBo sysUserBo;

	private String query;
	private String seq;
	private FormData formwkf0003;
	private FormData formwkf0004;
	private FormData formwkf0007;
	private FormData formwkf0008;
	private String roleNoStr;
	private String wkfRoleNoStr;
	private String brNoStr;
	private String wkfUserName;// 操作员
	private String wkfRoleNo;// 审批角色编号
	private String wkfBrNo;// 审批用户所在机构
	private FormService formService = new FormService();

	/** 是否审批人员* */
	private String if_approver;

	public WkfApprovalUserAction() {
		query = "";
	}

	/**
	 * 分页查询
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String findByPage() throws Exception {
		ActionContext.initialize(getHttpRequest(), getHttpResponse());
		formwkf0003 = formService.getFormData("wkf0003");
		wkfApprovalUser = new WkfApprovalUser();
		getFormValue(formwkf0003);
		setObjValue(formwkf0003, wkfApprovalUser);
		if ( StringUtils.isEmpty(wkfApprovalUser.getWkfBrNo()) ) {
			wkfApprovalUser.setWkfBrNo((User.getBrno(this.getHttpRequest())));
		}
		wkfApprovalUser.setOp_no(User.getLoginid(ServletActionContext.getRequest()));
		Ipage ipage = this.getIpage();
		wkfApprovalUserList = (List<WkfApprovalUser>) wkfApprovalUserBo
				.findByPage(ipage, wkfApprovalUser).getResult();
		return "list";
	}

	@SuppressWarnings("unchecked")
	public String findByPagePop() throws Exception {
		ActionContext.initialize(getHttpRequest(), getHttpResponse());
		formwkf0008 = formService.getFormData("wkf0008");
		wkfApprovalUser = new WkfApprovalUser();
		getFormValue(formwkf0008);
		setObjValue(formwkf0008, wkfApprovalUser);
		Ipage ipage = this.getIpage();
		wkfApprovalUserList = (List<WkfApprovalUser>) wkfApprovalUserBo
				.findByPage(ipage, wkfApprovalUser).getResult();
		return "popList";
	}

	@SuppressWarnings("unchecked")
	public String findByPageMapPop() throws Exception {
		ActionContext.initialize(getHttpRequest(), getHttpResponse());
		formwkf0008 = formService.getFormData("wkf0008");
		wkfApprovalUser = new WkfApprovalUser();
		getFormValue(formwkf0008);
		setObjValue(formwkf0008, wkfApprovalUser);
		Ipage ipage = this.getIpage();
		wkfApprovalUserMapList = (List<Map<String, Object>>) wkfApprovalUserBo
				.findByPageMapPop(ipage, wkfApprovalUser).getResult();
		return "userMapList";
	}

	public String findApprovalUserByPage() throws Exception {
		ActionContext.initialize(getHttpRequest(), getHttpResponse());
		// formwkf0003 = formService.getFormData("wkf0003");
		// wkfApprovalUser = new WkfApprovalUser();
		// getFormValue(formwkf0003);
		// setObjValue(formwkf0003, wkfApprovalUser);
		wkfApprovalUser = new WkfApprovalUser();
		// wkfBrNo="100001,100002,100003";
		// wkfRoleNo="sp001,sp002,sp003";
		// wkfUserName="0827,0828,0829";
		wkfApprovalUser.setWkfBrNo(wkfBrNo);
		wkfApprovalUser.setWkfRoleNo(wkfRoleNo);
		wkfApprovalUser.setWkfUserName(wkfUserName);
		Ipage ipage = this.getIpage();
		wkfApprovalUserMapList = (List<Map<String, Object>>) wkfApprovalUserBo
				.findApprovalUserByPage(ipage, wkfApprovalUser).getResult();
		return "approvalUserList";
	}

	/**
	 * 
	 * 功能描述：查询当前节点的会签成员
	 * 
	 * @return
	 * @throws Exception
	 * @修改日志：
	 */
	public String findApproveUserByPage() throws Exception {
		ActionContext.initialize(getHttpRequest(), getHttpResponse());
		wkfApprovalUser = new WkfApprovalUser();
		wkfApprovalUser.setWkfBrNo(User.getBrno(this.getHttpRequest()));
		wkfApprovalUser.setWkfRoleNo(wkfRoleNo);
		wkfApprovalUser.setWkfUserName(wkfUserName);
		Ipage ipage = this.getIpage();
		ipage.setPageSize(100);
		wkfApprovalUserMapList = (List<Map<String, Object>>) wkfApprovalUserBo
				.findApproveUserByPage(ipage, wkfApprovalUser).getResult();
		return "approvalUserList";
	}

	/**
	 * 获取新增页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(getHttpRequest(), getHttpResponse());
		formwkf0004 = formService.getFormData("wkf0004");
		wkfApprovalUser = new WkfApprovalUser();
		SysUser sysUser = new SysUser();
		sysUser.setUser_no(User.getLoginid(ServletActionContext.getRequest()));
		sysUser = sysUserBo.getById(sysUser);
		if(wkfUserName!=null){
			wkfApprovalUser.setWkfUserName(wkfUserName);
		}
		wkfApprovalUser.setBr_no(User.getBrno(this.getHttpRequest()));
		wkfApprovalUser.setOp_no(User.getLoginid(this.getHttpRequest()));
		wkfApprovalUser.setTx_date(User.getSys_date(this.getHttpRequest()));
		getObjValue(formwkf0004, wkfApprovalUser);
		return "input";
	}

	/**
	 * 获取批量新增页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String batchInput() throws Exception {
		ActionContext.initialize(getHttpRequest(), getHttpResponse());
		formwkf0007 = formService.getFormData("wkf0007");
		return "batchInput";
	}

	/**
	 * 新增保存操作
	 * 
	 * @return
	 * @throws Exception
	 * @modify zsp 2013-09-29
	 */
	public String insert() throws Exception {
		ActionContext.initialize(getHttpRequest(), getHttpResponse());
		formwkf0004 = formService.getFormData("wkf0004");
		getFormValue(formwkf0004);
		wkfApprovalUser = new WkfApprovalUser();
		setObjValue(formwkf0004, wkfApprovalUser);
		
		SysUser sysUser = new SysUser();
		sysUser.setUser_no(wkfApprovalUser.getWkfUserName());
		sysUser = sysUserBo.getById(sysUser);
		
		String roleNos = wkfApprovalUser.getWkfRoleNo();
//		String[] roleNo = roleNos.split("@");
//		String[] roleName = wkfApprovalUser.getWkfRoleName().split("@");
//		int total = 0;
//		if( roleNo != null ) {
//			total = roleNo.length;
//		}
//		for(int i =0;i<total;i++){
//			 wkfApprovalUser.setWkfRoleNo(roleNo[i]);
//			 wkfApprovalUser.setWkfRoleName(roleName[i]);
		wkfApprovalUser.setWkfRoleNo(roleNos);
		wkfApprovalUser.setWkfBrNo("00000");
		wkfApprovalUserBo.insert(wkfApprovalUser);
//		}
		this.addActionMessage("操作成功！");
//		wkfApprovalUser.setWkfRoleNo(roleNos);
		getObjValue(formwkf0004, wkfApprovalUser);
		return "detail";
	}

	/**
	 * 批量新增保存操作
	 * 
	 * @return
	 * @throws Exception
	 */
	public String batchInsert() throws Exception {
		ActionContext.initialize(getHttpRequest(), getHttpResponse());
		wkfApprovalUserBo.batchInsert(wkfRoleNoStr, roleNoStr, brNoStr);
		formwkf0007 = formService.getFormData("wkf0007");
		this.addActionMessage("操作成功");
		return "batchInput";
	}

	/**
	 * 修改保存操作
	 * 
	 * @return
	 * @throws Exception
	 * @modify zsp 2013-09-29
	 */
	public String update() throws Exception {
		ActionContext.initialize(getHttpRequest(), getHttpResponse());
		formwkf0004 = formService.getFormData("wkf0004");
		getFormValue(formwkf0004);
		wkfApprovalUser = new WkfApprovalUser();
		setObjValue(formwkf0004, wkfApprovalUser);
		
//		SysUser sysUser = new SysUser();
//		sysUser.setUser_no(wkfApprovalUser.getWkfUserName());
//		
//		sysUser = sysUserBo.getById(sysUser);
		
		wkfApprovalUser.setUp_date(User.getSys_date(this.getHttpRequest()));
		// 更新的角色号
//		String roleNos = wkfApprovalUser.getWkfRoleNo();
		// 库中已经存在的用户角色
//		String roleList = wkfApprovalUserBo.getAllRoles(wkfApprovalUser
//				.getWkfUserName());
//		String[] roleName = wkfApprovalUser.getWkfRoleName().split("@");

//		String[] roleNo = roleNos.split("@");
//		String[] roleNo2 = roleList.split("@");
//		for (int i = 0; i < roleNo2.length; i++) {
//			wkfApprovalUser.setWkfRoleNo(roleNo2[i]);
//			wkfApprovalUserBo.del(wkfApprovalUser);
//		}
//		for (int i = 0; i < roleNo.length; i++) {
//			wkfApprovalUser.setWkfRoleNo(roleNo[i]);
//			wkfApprovalUser.setWkfRoleName(roleName[i]);
//			wkfApprovalUserBo.insert(wkfApprovalUser);
//		}
		wkfApprovalUser.setWkfUserName(wkfUserName);
		wkfApprovalUser.setWkfRoleNo(wkfRoleNo);
//		if(wkfApprovalUser.getWkfBrNo()==null){
//			wkfApprovalUser.setWkfBrNo("111");
//		}
		wkfApprovalUserBo.update(wkfApprovalUser);
		this.addActionMessage("操作成功！");
		getObjValue(formwkf0004, wkfApprovalUser);
		return "detail";
	}

	public void validateUpdate() {
		ActionContext.initialize(getHttpRequest(), getHttpResponse());
		formwkf0004 = formService.getFormData("wkf0004");
		getFormValue(formwkf0004);
		validateFormData(formwkf0004);
		wkfApprovalUser = new WkfApprovalUser();
		setObjValue(formwkf0004, wkfApprovalUser);
		SysUser sysUser = new SysUser();
		sysUser.setUser_no(wkfApprovalUser.getWkfUserName());
		sysUser = sysUserBo.getById(sysUser);
		if(sysUser == null){
			this.addActionError("该用户不存在，请核实后新增！");
		}
	}

	
	/**
	 * 删除操作
	 * 
	 * @return
	 * @throws Exception
	 * @modify zsp 2013-09-29
	 */
	@SuppressWarnings("unchecked")
	public String del() throws Exception {
		ActionContext.initialize(getHttpRequest(), getHttpResponse());
		formwkf0003 = formService.getFormData("wkf0003");
		wkfApprovalUser = new WkfApprovalUser();
		System.out.println("wkfUserName"+wkfUserName);
		System.out.println("wkfRoleNo"+wkfRoleNo);
		System.out.println("seq"+wkfRoleNo);
		wkfApprovalUser.setWkfUserName(wkfUserName);

//		String roleNos = wkfApprovalUserBo.getAllRoles(wkfUserName);
//		String[] roleNo = roleNos.split("@");
//		for (int i = 0; i < roleNo.length; i++) {
//			wkfApprovalUser.setWkfRoleNo(roleNo[i]);
		wkfApprovalUserBo.del(wkfApprovalUser);
//		}
		this.addActionMessage("删除成功");
		wkfApprovalUser = new WkfApprovalUser();
		wkfApprovalUser.setOp_no(User.getLoginid(ServletActionContext.getRequest()));
		Ipage ipage = this.getIpage();
		wkfApprovalUserList = (List<WkfApprovalUser>) wkfApprovalUserBo
				.findByPage(ipage, wkfApprovalUser).getResult();
		return "list";
	}

	public String toApprover() throws Exception {
		ActionContext.initialize(getHttpRequest(), getHttpResponse());

		wkfApprovalUser = new WkfApprovalUser();
		String tlrno = getHttpRequest().getParameter("tlrno");
		if ( "0".equals(if_approver) ) {
			formwkf0004 = formService.getFormData("wkf0004");
			String brNo = getHttpRequest().getParameter("brNo");
			if ( !(StringUtils.isEmpty(tlrno)) ) {
				wkfApprovalUser.setWkfUserName(tlrno);
			}
			if ( !(StringUtils.isEmpty(brNo)) ) {
				wkfApprovalUser.setWkfBrNo(brNo);
			}
			wkfApprovalUser.setBr_no(User.getBrno(getHttpRequest()));
			wkfApprovalUser.setOp_no(User.getLoginid(getHttpRequest()));
			wkfApprovalUser.setTx_date(User.getSys_date(getHttpRequest()));
			wkfApprovalUser.setUp_date(User.getSys_date(getHttpRequest()));
			getObjValue(formwkf0004, wkfApprovalUser);
			return "input";
		}

		formwkf0003 = formService.getFormData("wkf0003");
		getFormValue(formwkf0003);
		setObjValue(formwkf0003, wkfApprovalUser);
		if ( !(StringUtils.isEmpty(tlrno)) ) {
			wkfApprovalUser.setWkfUserName(tlrno);
		} else {
			if ( StringUtils.isEmpty(wkfApprovalUser.getWkfBrNo()) ) {
				wkfApprovalUser.setWkfBrNo((User.getBrno(this.getHttpRequest())));
			}
		}
		getObjValue(formwkf0003, wkfApprovalUser);
		
		Ipage ipage = this.getIpage();
		wkfApprovalUserList = (List<WkfApprovalUser>) wkfApprovalUserBo.findByPage(ipage, wkfApprovalUser).getResult();
		return "list";
	}

	/**
	 * 查询操作
	 * 
	 * @return
	 * @throws Exception
	 * @modify zsp 2013-09-29
	 */
	public String getById() throws Exception {
		ActionContext.initialize(getHttpRequest(), getHttpResponse());
		formwkf0004 = formService.getFormData("wkf0004");
		wkfApprovalUser = new WkfApprovalUser();
		wkfApprovalUser.setSeq(seq);
		wkfApprovalUser = wkfApprovalUserBo.getById(wkfApprovalUser);
		//String roleNos =  wkfApprovalUserBo.getAllRoles(wkfApprovalUser.getWkfUserName());		//wkfApprovalUser.setWkfRoleNo(roleNos);

		getObjValue(formwkf0004, wkfApprovalUser);
		return "detail";
	}

	/**
	 * 新增保存操作校验
	 * 
	 * @return
	 * @throws Exception
	 * @modify zsp 2013-09-29
	 */
	public void validateInsert() {
		ActionContext.initialize(getHttpRequest(), getHttpResponse());
		formwkf0004 = formService.getFormData("wkf0004");
		getFormValue(formwkf0004);
		validateFormData(formwkf0004);
		wkfApprovalUser = new WkfApprovalUser();
		setObjValue(formwkf0004, wkfApprovalUser);
		// 判断审批用户是否存在
		int count = wkfApprovalUserBo.getUserCount(wkfApprovalUser
				.getWkfUserName());
		if ( count != 0 ) {
			this.addActionError("该审批用户已经存在!");
		}
		//
		SysUser sysUser = new SysUser();
		sysUser.setUser_no(wkfApprovalUser.getWkfUserName());
		sysUser = sysUserBo.getById(sysUser);
		if(sysUser == null){
			this.addActionError("该用户不存在，请核实后新增！");
		}
	}

	public WkfApprovalUser getWkfApprovalUser() {
		return wkfApprovalUser;
	}

	public void setWkfApprovalUser(WkfApprovalUser wkfApprovalUser) {
		this.wkfApprovalUser = wkfApprovalUser;
	}

	public List<WkfApprovalUser> getWkfApprovalUserList() {
		return wkfApprovalUserList;
	}

	public void setWkfApprovalUserList(List<WkfApprovalUser> wkfApprovalUserList) {
		this.wkfApprovalUserList = wkfApprovalUserList;
	}

	public WkfApprovalUserBo getWkfApprovalUserBo() {
		return wkfApprovalUserBo;
	}

	public void setWkfApprovalUserBo(WkfApprovalUserBo wkfApprovalUserBo) {
		this.wkfApprovalUserBo = wkfApprovalUserBo;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public FormData getFormwkf0004() {
		return formwkf0004;
	}

	public void setFormwkf0004(FormData formwkf0004) {
		this.formwkf0004 = formwkf0004;
	}

	public FormData getFormwkf0003() {
		return formwkf0003;
	}

	public void setFormwkf0003(FormData formwkf0003) {
		this.formwkf0003 = formwkf0003;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getSeq() {
		return seq;
	}

	public void setFormwkf0007(FormData formwkf0007) {
		this.formwkf0007 = formwkf0007;
	}

	public FormData getFormwkf0007() {
		return formwkf0007;
	}

	public void setRoleNoStr(String roleNoStr) {
		this.roleNoStr = roleNoStr;
	}

	public String getRoleNoStr() {
		return roleNoStr;
	}

	public void setWkfRoleNoStr(String wkfRoleNoStr) {
		this.wkfRoleNoStr = wkfRoleNoStr;
	}

	public String getWkfRoleNoStr() {
		return wkfRoleNoStr;
	}

	public void setBrNoStr(String brNoStr) {
		this.brNoStr = brNoStr;
	}

	public String getBrNoStr() {
		return brNoStr;
	}

	public FormData getFormwkf0008() {
		return formwkf0008;
	}

	public void setFormwkf0008(FormData formwkf0008) {
		this.formwkf0008 = formwkf0008;
	}

	public List<Map<String, Object>> getWkfApprovalUserMapList() {
		return wkfApprovalUserMapList;
	}

	public void setWkfApprovalUserMapList(
			List<Map<String, Object>> wkfApprovalUserMapList) {
		this.wkfApprovalUserMapList = wkfApprovalUserMapList;
	}

	public String getWkfUserName() {
		return wkfUserName;
	}

	public void setWkfUserName(String wkfUserName) {
		this.wkfUserName = wkfUserName;
	}

	public String getWkfRoleNo() {
		return wkfRoleNo;
	}

	public void setWkfRoleNo(String wkfRoleNo) {
		this.wkfRoleNo = wkfRoleNo;
	}

	public String getWkfBrNo() {
		return wkfBrNo;
	}

	public void setWkfBrNo(String wkfBrNo) {
		this.wkfBrNo = wkfBrNo;
	}

	public String getIf_approver() {
		return if_approver;
	}

	public void setIf_approver(String if_approver) {
		this.if_approver = if_approver;
	}

	public SysUserBo getSysUserBo() {
		return sysUserBo;
	}

	public void setSysUserBo(SysUserBo sysUserBo) {
		this.sysUserBo = sysUserBo;
	}

}