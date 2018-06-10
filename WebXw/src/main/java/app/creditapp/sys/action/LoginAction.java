package app.creditapp.sys.action;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.dbunit.util.Base64;

import com.alibaba.fastjson.JSON;
import com.core.struts.BaseFormBean;

import accounting.plat.PUBConstant;
import app.base.ServiceException;
import app.creditapp.aft.bo.AftMessageAlarmBo;
import app.creditapp.aft.entity.aftMessage.AjaxData;
import app.creditapp.entity.SysGlobal;
import app.creditapp.sys.bo.StudentBo;
import app.creditapp.sys.bo.SysLogBo;
import app.creditapp.sys.bo.SysMenuBo;
import app.creditapp.sys.bo.SysUserBo;
import app.creditapp.sys.bo.SysUserLogBo;
import app.creditapp.sys.bo.SysUserRoleBo;
import app.creditapp.sys.entity.SysLog;
import app.creditapp.sys.entity.SysMenu;
import app.creditapp.sys.entity.SysUser;
import app.creditapp.sys.entity.SysUserLog;
import app.creditapp.wkf.bo.WkfApprovalRoleBo;
import app.creditapp.wkf.bo.WkfApprovalUserBo;
import app.creditapp.wkf.entity.WkfApprovalRole;
import app.oscache.CachecodeUtil;
import app.oscache.MBaseCache;
import app.util.DateUtil;
import app.util.LoginSessionListener;
import app.util.User;
import app.util.json.JsonMenuUtil;

public class LoginAction extends BaseFormBean {
	private static final long serialVersionUID = 123867490237412370L;
	private String browserVersion;
	private SysUserBo sysUserBo;
	private SysUserLogBo sysUserLogBo;
	private SysLogBo sysLogBo;
	private StudentBo studentBo;
	private SysUserRoleBo sysUserRoleBo;
	private WkfApprovalUserBo wkfApprovalUserBo;
	private WkfApprovalRoleBo wkfApprovalRoleBo;
	private AftMessageAlarmBo aftMessageAlarmBo;
	private SysMenuBo sysMenuBo;
	private SysUser op;
	private List<SysMenu> sysMenuLev1List;
	private Map<String, String> menuTreeMap;
	
	private String userId;
	private String password;
	private String changeUi;

	/**
	 * 操作员登录交易
	 * cms.action
	 * @return
	 * @throws Exception
	 */
	public String userLogin() throws Exception {
		getHttpRequest().getSession().setAttribute("browserVersion", browserVersion);
		// 取操作员
		SysUser sysUser = sysUserBo.getById(op);
		// 系统日志
		SysLog syslog = new SysLog();

		if (sysUser == null) {
			this.addActionError("没有此用户名!");
			return redirectPage();
		} else {
			if (!"01".equals(sysUser.getUser_sts())) {
				this.addActionError("该用户已失效,无法登陆!");
				return redirectPage();
			}
			// 系统日志
			syslog.setOpNo(sysUser.getUser_id());
			syslog.setOpClass(this.getClass().toString());
			syslog.setOpId(sysUser.getUser_id());
			syslog.setOpDesc("操作员登录");
			sysLogBo.insertOrUpdate(syslog);
			// 操作员登录日志
			SysUserLog sysUserLog = new SysUserLog();
			sysUserLog.setUserId(sysUser.getUser_id());
			sysUserLog = sysUserLogBo.getById(sysUserLog);

			// 密码密文
			String passWord = Base64.encodeString(op.getPass_word());
			if (sysUser.getPass_word().equals(passWord)) {
				// 取用户权限
				String allRoleNo = getAllRoleNo(sysUser.getRoleNos());
				if (StringUtils.isBlank(allRoleNo)) {
					this.addActionError("该用户未配置用户机构角色关系,无法登录,请联系管理员配置!");
					return redirectPage();
				}

				if (LoginSessionListener.isOnline(op.getUser_no())) {
					LoginSessionListener.kickFirstOper(this.getHttpRequest().getSession(), op.getUser_no());// 把第一个用户T下线
				}
				LoginSessionListener.putSessionMap(this.getHttpRequest().getSession(), op.getUser_no());

				addUserSession(sysUser, sysUserLog, this.getHttpRequest());
				sysUserLog.setPassError(0);
				sysUserLogBo.userLogin(sysUserLog);// 记登录日志
				// 菜单处理
				List<SysMenu> sysMenuList = sysMenuBo.getAllMenuByRole_no(allRoleNo);
				sysMenuLev1List = sysMenuBo.findMenuLev1ByRole(allRoleNo);
				getHttpRequest().getSession().setAttribute("sysMenuLev1List", sysMenuLev1List);
				getHttpRequest().getSession().setAttribute("allRoleNo", allRoleNo);
				menuTreeMap = JsonMenuUtil.ulist2tree(sysMenuList);
				getSession().put("menuTreeMap", menuTreeMap);
				getSession().remove("code");
				
			} else {
				sysUserLog.setPassError(sysUserLog.getPassError() + 1);
				sysUserLogBo.userLogin(sysUserLog);// 记登录日志
				this.addActionError("密码错误，请确认密码！");
				return redirectPage();
			}
		}
		return getUserLoginResult();
	}
	
	public String checkUserLogin() throws UnknownHostException {
		if(op == null){
			op = new SysUser();
			op.setUser_no(userId);
			op.setPass_word(password);
		}
		if(op !=null && op.getUser_no() == null) op.setUser_no(userId);
		if(op !=null && op.getPass_word() == null) op.setPass_word(password);
		// 取操作员
		SysUser sysUser = sysUserBo.getById(op);
		// 系统日志
		SysLog syslog = new SysLog();

		if (sysUser == null) {
			return "error|没有此用户名!";
		} else {
			if (!"01".equals(sysUser.getUser_sts())) {
				return "error|该用户已失效,无法登陆!";
			}
			// 系统日志
			syslog.setOpNo(sysUser.getUser_id());
			syslog.setOpClass(this.getClass().toString());
			syslog.setOpId(sysUser.getUser_id());
			syslog.setOpDesc("操作员登录");
			sysLogBo.insertOrUpdate(syslog);
			// 操作员登录日志
			SysUserLog sysUserLog = new SysUserLog();
			sysUserLog.setUserId(sysUser.getUser_id());
			sysUserLog = sysUserLogBo.getById(sysUserLog);

			// 密码密文
			String passWord = Base64.encodeString(op.getPass_word());
			if (sysUser.getPass_word().equals(passWord)) {
				// 取用户权限
				String allRoleNo = getAllRoleNo(sysUser.getRoleNos());
				if (StringUtils.isBlank(allRoleNo)) {
					this.addActionError("");
					return "error|该用户未配置用户机构角色关系,无法登录,请联系管理员配置!";
				}

				if (LoginSessionListener.isOnline(op.getUser_no())) {
					LoginSessionListener.kickFirstOper(this.getHttpRequest().getSession(), op.getUser_no());// 把第一个用户T下线
				}
				LoginSessionListener.putSessionMap(this.getHttpRequest().getSession(), op.getUser_no());

				addUserSession(sysUser, sysUserLog, this.getHttpRequest());
				sysUserLog.setPassError(0);
				sysUserLogBo.userLogin(sysUserLog);// 记登录日志
				// 菜单处理
				List<SysMenu> sysMenuList = sysMenuBo.getAllMenuByRole_no(allRoleNo);
				sysMenuLev1List = sysMenuBo.findMenuLev1ByRole(allRoleNo);
				getHttpRequest().getSession().setAttribute("sysMenuLev1List",sysMenuLev1List);
				getHttpRequest().getSession().setAttribute("allRoleNo",allRoleNo);
				menuTreeMap = JsonMenuUtil.ulist2tree(sysMenuList);
				getSession().put("menuTreeMap", menuTreeMap);
				getSession().remove("code");
				
				AjaxData messageAjaxData = aftMessageAlarmBo.initAjaxData(User.getLoginid(getHttpRequest()),User.getSys_date(getHttpRequest()), true);
				getHttpRequest().getSession().setAttribute("messageData", "{\"ajaxData\":"+JSON.toJSONString(messageAjaxData)+"}");
//				System.out.println(JSON.toJSONString(messageAjaxData));
				getHttpRequest().getSession().setAttribute("messageSumNumber", messageAjaxData.getSumCount());
				String pushMessageServerPath = (String) MBaseCache.getCache().getBeanCache("pushMessageServerPath", CachecodeUtil.SECURITY);
				String websocketEndpointPath = (String) MBaseCache.getCache().getBeanCache("websocketEndpointPath", CachecodeUtil.SECURITY);
				String wholeRequestPath = "ws://"+pushMessageServerPath + websocketEndpointPath + "/"+User.getLoginid(getHttpRequest())+"/"+sysUser.getRoleNos()+"/"+InetAddress.getLocalHost().getHostAddress();
				getHttpRequest().getSession().setAttribute("pushMessageServerPath", wholeRequestPath);
				
				getHttpRequest().getSession().setAttribute("indexUi", sysUser.getLastUi());
				
			} else {
				sysUserLog.setPassError(sysUserLog.getPassError() + 1);
				sysUserLogBo.userLogin(sysUserLog);// 记登录日志
				this.addActionError("");
				return "error|密码错误，请确认密码！";
			}
		}
		return "success";
	}
	
	public String changeSysUserUi() throws IOException{
		this.getHttpResponse().setContentType("text/html;charset=utf-8");
		HttpServletResponse response=ServletActionContext.getResponse();
		String user_no = User.getLoginid(getHttpRequest());
		SysUser sysUser = new SysUser();
		sysUser.setUser_no(user_no);
		sysUser = sysUserBo.getById(sysUser);
		sysUser.setLastUi(getChangeUi());
		sysUserBo.update(sysUser);
		getHttpRequest().getSession().setAttribute("indexUi", sysUser.getLastUi());
		response.getWriter().write("success");
		response.getWriter().close();
		return null;
	}
	
	/*
	 * cmsForAjax.action
	 */
	public String userLoginForAjax() throws Exception{
		this.getHttpResponse().setContentType("text/html;charset=utf-8");
		HttpServletResponse response=ServletActionContext.getResponse();
		String checkResult = checkUserLogin();
		response.getWriter().write(checkResult);
		response.getWriter().close();
		return null;
	}

	/*
	 * 操作员退出
	 */
	public String userLogout() throws Exception {
		HttpSession session = this.getHttpRequest().getSession();
		// 操作员登出写日志
		String user_no = (String) session.getAttribute("userId");
		if (user_no != null) {
			SysUserLog sysUserLog = new SysUserLog();
			sysUserLog.setUserId(user_no);
			sysUserLogBo.userLogout(sysUserLog);
		}
		String redirectStr = redirectPage();
		try {
			this.getHttpRequest().getSession().invalidate();// 销毁session
		} catch (Exception e) {
			return redirectStr;// 如果清session出现异常说明session已经销毁一次，则直接返回登录界面
		}
		return redirectStr;
	}

	private void addUserSession(SysUser sysUser, SysUserLog sysUserLog, HttpServletRequest request) {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm:ss");// 设置日期显示格式
		String time = df.format(date);
		String ip = getIpAddr(request);// 客户端IP地址
		String browser = request.getHeader("User-Agent");// 浏览器名及版
		String clientInfo = time + "|" + ip + "|" + browser;
		sysUserLog.setClientInfo(clientInfo);
		sysUserLog.setLoginIp(ip);
		sysUserLog.setSessionId(request.getSession().getId());

		/** 审批角色 **/
		String wkfRoleNos = null;
		try {
			wkfRoleNos = wkfApprovalUserBo.getAllRoles(sysUser.getUser_no());
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		String wkfRoleNames = "";
		if (wkfRoleNos != null && !"".equals(wkfRoleNos)) {
			String[] wrs = wkfRoleNos.split("@");
			for (int i = 0; i < wrs.length; i++) {
				WkfApprovalRole wkfApprovalRole = new WkfApprovalRole();
				wkfApprovalRole.setWkfRoleNo(wrs[i]);
				wkfApprovalRole = wkfApprovalRoleBo.getById(wkfApprovalRole);
				wkfRoleNames += wkfApprovalRole.getWkfRoleName() + "|";
			}
		}

		request.getSession().setAttribute("ip", ip);
		request.getSession().setAttribute("color",sysUser.getSkin());
		request.getSession().setAttribute("browser", browser);
		request.getSession().setAttribute("userinfo", clientInfo);
		request.getSession().setAttribute("sessionID", request.getSession().getId());
		request.getSession().setAttribute("pwd", sysUser.getPass_word()); // 密码
		request.getSession().setAttribute("loginid", sysUser.getUser_no()); // 操作员号码
		request.getSession().setAttribute("userId", sysUser.getUser_id()); // 操作员ID
		request.getSession().setAttribute("wfOrgNo", sysUser.getOrg_no()); // 所属部门

		request.getSession().setAttribute("wkfRoleNos", wkfRoleNos);// 审批角色描述
		request.getSession().setAttribute("wkfrolenames", wkfRoleNames);// 审批角色描述
		request.getSession().setAttribute("displayname", sysUser.getDisp_name());// 操作员名称
		request.getSession().setAttribute("roleNos", sysUser.getRoleNos());

		SysGlobal sg = DateUtil.getSysGlobal();
		request.getSession().setAttribute("sys_date", sg.getSys_date());
		request.getSession().setAttribute("bat_date", sg.getBat_date());
		request.getSession().setAttribute("lst_date", sg.getLst_date());
		PUBConstant.CUR_PRCS_DT = sg.getSys_date();
		request.getSession().setAttribute("frame", "");
		request.getSession().setAttribute("menuno", "");
		
		this.getSession().put("sysmenu", sysMenuBo.getmu());
		this.getSession().put("isLogin", true);
		
	}

	private String redirectPage() {
		browserVersion = (String) getHttpRequest().getSession().getAttribute("browserVersion");
		if ("old".equals(browserVersion)) {
			return "login";
		} else {
			return "newLogin";
		}
	}

	private String getUserLoginResult() {
		getSession().put("frame", "main");
		if (null == getSession().get("color")) {
			getSession().put("color", "yellow");
		}
		return (String) getSession().get("frame");
	}

	/**
	 * @作者 DHCC-SONG
	 * @日期 Aug 5, 2016
	 * @方法说明： 根据用户中角色串 “@” 分割
	 * @返回参数 String
	 */
	private String getAllRoleNo(String roleNos) {
//		List<SysUserRole> rolenolist = sysUserRoleBo.getAllRoleNo(user_no);
		String allRoleNo = "('"+ roleNos.replaceAll("@", "\',\'") +"')";
//		StringBuilder paramRoleNo = new StringBuilder();
//		if (rolenolist.size() <= 0) {
//			return paramRoleNo.toString();
//		}
//		paramRoleNo.append("(");
//		for (int i = 0; i < rolenolist.size(); i++) {
//			if (i != (rolenolist.size() - 1)) {
//				paramRoleNo.append("'").append(rolenolist.get(i).getRole_no()).append("',");
//			} else {
//				paramRoleNo.append("'").append(rolenolist.get(i).getRole_no()).append("')");
//			}
//		}
		return allRoleNo;
	}

	private String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public String getBrowserVersion() {
		return browserVersion;
	}

	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}

	public SysUserBo getSysUserBo() {
		return sysUserBo;
	}

	public void setSysUserBo(SysUserBo sysUserBo) {
		this.sysUserBo = sysUserBo;
	}

	public SysUserLogBo getSysUserLogBo() {
		return sysUserLogBo;
	}

	public void setSysUserLogBo(SysUserLogBo sysUserLogBo) {
		this.sysUserLogBo = sysUserLogBo;
	}

	public SysLogBo getSysLogBo() {
		return sysLogBo;
	}

	public void setSysLogBo(SysLogBo sysLogBo) {
		this.sysLogBo = sysLogBo;
	}

	public SysUserRoleBo getSysUserRoleBo() {
		return sysUserRoleBo;
	}

	public void setSysUserRoleBo(SysUserRoleBo sysUserRoleBo) {
		this.sysUserRoleBo = sysUserRoleBo;
	}

//	public WkfApprovalUserBo getWkfApprovalUserBo() {
//		return wkfApprovalUserBo;
//	}
//
//	public void setWkfApprovalUserBo(WkfApprovalUserBo wkfApprovalUserBo) {
//		this.wkfApprovalUserBo = wkfApprovalUserBo;
//	}
//
//	public WkfApprovalRoleBo getWkfApprovalRoleBo() {
//		return wkfApprovalRoleBo;
//	}
//
//	public void setWkfApprovalRoleBo(WkfApprovalRoleBo wkfApprovalRoleBo) {
//		this.wkfApprovalRoleBo = wkfApprovalRoleBo;
//	}

	public SysMenuBo getSysMenuBo() {
		return sysMenuBo;
	}

	public void setSysMenuBo(SysMenuBo sysMenuBo) {
		this.sysMenuBo = sysMenuBo;
	}

	public SysUser getOp() {
		return op;
	}

	public void setOp(SysUser op) {
		this.op = op;
	}

	public List<SysMenu> getSysMenuLev1List() {
		return sysMenuLev1List;
	}

	public void setSysMenuLev1List(List<SysMenu> sysMenuLev1List) {
		this.sysMenuLev1List = sysMenuLev1List;
	}

	public Map<String, String> getMenuTreeMap() {
		return menuTreeMap;
	}

	public void setMenuTreeMap(Map<String, String> menuTreeMap) {
		this.menuTreeMap = menuTreeMap;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAftMessageAlarmBo(AftMessageAlarmBo aftMessageAlarmBo) {
		this.aftMessageAlarmBo = aftMessageAlarmBo;
	}

	public String getChangeUi() {
		return changeUi;
	}

	public void setChangeUi(String changeUi) {
		this.changeUi = changeUi;
	}

	public StudentBo getStudentBo() {
		return studentBo;
	}

	public void setStudentBo(StudentBo studentBo) {
		this.studentBo = studentBo;
	}

	public WkfApprovalUserBo getWkfApprovalUserBo() {
		return wkfApprovalUserBo;
	}

	public void setWkfApprovalUserBo(WkfApprovalUserBo wkfApprovalUserBo) {
		this.wkfApprovalUserBo = wkfApprovalUserBo;
	}

	public WkfApprovalRoleBo getWkfApprovalRoleBo() {
		return wkfApprovalRoleBo;
	}

	public void setWkfApprovalRoleBo(WkfApprovalRoleBo wkfApprovalRoleBo) {
		this.wkfApprovalRoleBo = wkfApprovalRoleBo;
	}

	public AftMessageAlarmBo getAftMessageAlarmBo() {
		return aftMessageAlarmBo;
	}
}
