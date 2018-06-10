package app.creditapp.sys.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import app.creditapp.sys.bo.SysMenuBo;
import app.creditapp.sys.entity.SysMenu;

import com.core.struts.BaseFormBean;

public class SysMenuAction extends BaseFormBean {

	//页面传值
	private SysMenu sm;	//菜单实体
	private String menuStr;	//字符组成式菜单
	private String menuNo;
	private String parentMenuNo;
	
	private String roleNo;
	private String tcode;
	private String tname;
	private String pcode;
	private String lvl;
	private String url;
	private String menuSts;
	private List<SysMenu> sysmenuList;
	
	//spring注入BO
	private SysMenuBo sysMenuBo;

	/***************** Action method start  *************************/
	/**
	 * 返回所有菜单
	 * @return
	 * @throws Exception
	 */
	/*
	public String getAllMenu() throws Exception{
		menuStr =  sysMenuBo.getAllJsonMenu();
		return "allMenu";
	}*/
	/**
	 * 角色设置对应菜单的设置
	 */
//	public String getAllMenuByRole() throws Exception{
//		menuStr =  sysMenuBo.getAllJsonMenu1();
//		String role_no=this.getHttpRequest().getParameter("role_no");
//		String menuno_str=sysRoleBo.checkJsp(role_no);
//		this.getHttpRequest().setAttribute("menuno_str", menuno_str);
//		return "allMenuByRole";
//	}
	
	/**
	 * 获取菜单
	 * @return
	 * @throws Exception
	 */
	/*
	public String getAllMenus() throws Exception{
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		menuStr =  sysMenuBo.getAllJsonMenu();
		//String menuno_str=tblorgjobusersbo.checkuserJsp(userid);
		//this.getHttpRequest().setAttribute("menuno_str", menuno_str);
		
		return "Usermenu";
	}
	*/
	/**
	 * 查询一个操作员号下面对应的所有菜单的内容
	 * @return
	 * @throws Exception
	 */
	
//	public String getAllButtonByRole()throws Exception{
//		String role_no=this.getHttpRequest().getParameter("role_no");
//		menuStr=sysMenuBo.getAllMenuByRoleNo(role_no);
//		return "allButtonByRole";	
//	}
	
	
	public String getRootMenu() throws Exception{
		menuStr = sysMenuBo.getAllJsonMenu2();
		return "rootMenu";
	}
	/*
	public String getChildMenu() throws Exception{
		sm = sysMenuBo.getById(menuNo);
		if(sm!=null){
			List<SysMenu> children = sysMenuBo.getAllMenuByParent(menuNo);
			this.getHttpResponse().setContentType("text/html;charset=utf-8"); 
			PrintWriter out = this.getHttpResponse().getWriter();
			StringBuffer result = new StringBuffer();
			if(sm.getMenuLvl().equals("1")){
				for(SysMenu menu : children){
					result.append("<li id='");
					result.append(menu.getMenuNo());
					result.append("'><span class='text'>");
					result.append(menu.getMenuName());
					result.append("</span><ul class='ajax'><li id='");
					result.append(menu.getMenuNo());
					result.append("-1'>{urlSysMenuuAction_getChildMenu.action?menuNo=");
					result.append(menu.getMenuNo());
					result.append("}</li></ul></li>");
				}
				out.print(result.toString());
			} else if(sm.getMenuLvl().equals("2")){
				for(SysMenu menu : children){
					result.append("<li id='");
					result.append(menu.getMenuNo());
					result.append("'><span class='text'>");
					result.append(menu.getMenuName());
					result.append("</span></li>");
				}
				out.print(result.toString());
			} else {
				out.print("");
			}
		}
		return null;
	}
	*/
	
	public String insertMenu() throws Exception{
		tname = java.net.URLDecoder.decode(new String(tname.getBytes("GBK"),"ISO-8859-1"), "UTF-8");
		this.getHttpResponse().setContentType("text/html;charset=utf-8"); 
		PrintWriter out = this.getHttpResponse().getWriter(); 
		String result = "success";
		try{
			sm = new SysMenu();
			sm.setMenuNo(tcode);
			sm.setMenuName(tname);
			sm.setMenuParent(pcode);
			sm.setMenuLvl(lvl);
			sm.setMenuUrl(url);
			sm.setMenuSts(menuSts);
			sm.setMenuShape("0");
			if(sysMenuBo.getById(tcode)==null){
				sysMenuBo.insert(sm);
			}else {
				result = "菜单号[ "+tcode+" ]已存在!";
			}
		}catch(Exception e){
			e.printStackTrace();
			result = "新增菜单失败!";
		}
		out.println(result);
		return null;
	}
	
	public String updateMenu() throws Exception{
		tname = java.net.URLDecoder.decode(new String(tname.getBytes("GBK"),"ISO-8859-1"), "UTF-8");
		this.getHttpResponse().setContentType("text/html;charset=utf-8"); 
		PrintWriter out = this.getHttpResponse().getWriter(); 
		String result = "success";
		try{
			sm = new SysMenu();
			sm.setMenuNo(tcode);
			sm.setMenuName(tname);
			sm.setMenuParent(pcode);
			sm.setMenuLvl(lvl);
			sm.setMenuUrl(url);
			sm.setMenuSts(menuSts);
			if(sysMenuBo.getById(tcode)!=null){
				sysMenuBo.update(sm);
			}else {
				result = "菜单号[ "+tcode+" ]不存在,修改失败!";
			}
		}catch(Exception e){
			result = "修改菜单失败!";
		}
		out.println(result);
		return null;
	}

	public String editWindow() throws Exception{
		return "editWindow";
	}
	
	public String moveMenu() throws Exception{
		sm = sysMenuBo.getById(menuNo);
		if(sm!=null){
			this.getHttpResponse().setContentType("text/html;charset=utf-8"); 
			PrintWriter out = this.getHttpResponse().getWriter();
			SysMenu parent = sysMenuBo.getById(parentMenuNo);
			if(parent!=null){
				sm.setMenuParent(parentMenuNo);
				sm.setMenuLvl((Integer.parseInt(parent.getMenuLvl())+1)+"");
				try {
					sysMenuBo.moveUpdate(sm);
					out.print("success");
					return null;
				} catch (Exception e) {
					out.print("false");
					return null;
				}
			}else {
				out.print("false");
			}
		}
		return null;
	}
	
	public String deleteMenu() throws Exception{
		PrintWriter out = this.getHttpResponse().getWriter();
		if(tcode!=null && !tcode.equals("")){
			try {
				sysMenuBo.delete(tcode);
				this.delByParentMenu(tcode);
				out.print("success");
			} catch (Exception e) {
				out.print("fail");
			}
		}else {
			out.print("fail");
		}
		return null;
	}
	
	//根据节点编号删除所有下级节点
	public void delByParentMenu(String tcode) throws Exception{
		for(SysMenu sysMenu : sysMenuBo.getAllMenuByParent(tcode)){
			sysMenuBo.delete(sysMenu.getMenuNo());
			this.delByParentMenu(sysMenu.getMenuNo());
		}
	}
	
	/*
	 * 同级菜单拖拽
	 * 1.相同父节点
	 * 	--只需修改序号
	 * 2.不同父节点
	 * 	--先移动到目标父节点下，然后修改序号
	 */
	public String changeMenuSeq() throws Exception{
		PrintWriter out = this.getHttpResponse().getWriter();
		sm = sysMenuBo.getById(menuNo);
		SysMenu parent = sysMenuBo.getById(parentMenuNo);
		if(sysMenuBo.changeMenuSeqForMove(sm, parent)){
			out.print("success");
		}else{
			out.print("false");
		}
		return null;
	}
	
	/**
	 * 制作导航地图
	 * @return
	 */
	public String getGuideMap(){
		String roleNo = (String) this.getHttpRequest().getSession().getAttribute("allRoleNo");
		sysmenuList = sysMenuBo.getAllMenuByRole_no(roleNo);
		sysmenuList = initMenuList(sysmenuList);
		return "showGuideMap";
	}
	
	private List<SysMenu> initMenuList(List<SysMenu> allMenuList){
		List<SysMenu> sysTopMenuList = new ArrayList<SysMenu>();
		List<SysMenu> sysSecondMenuList = new ArrayList<SysMenu>();
		List<SysMenu> sysThirdMenuList = new ArrayList<SysMenu>();
		Iterator<SysMenu> menuitor = allMenuList.iterator();
		SysMenu nextMenu = new SysMenu();
		
		while(menuitor.hasNext()){
			nextMenu =menuitor.next();
			if("0".equals(nextMenu.getMenuLvl())){
				menuitor.remove();
				continue;
			}
		}
		
		menuitor = allMenuList.iterator();
		while(menuitor.hasNext()){
			nextMenu =menuitor.next();
			if("1".equals(nextMenu.getMenuLvl())){
				sysTopMenuList.add(nextMenu);
				menuitor.remove();
			}else{
				break;
			}
		}
		menuitor = allMenuList.iterator();
		while(menuitor.hasNext()){
			nextMenu =menuitor.next();
			if("2".equals(nextMenu.getMenuLvl())){
				sysSecondMenuList.add(nextMenu);
				menuitor.remove();
			}else{
				break;
			}
		}
		menuitor = allMenuList.iterator();
		while(menuitor.hasNext()){
			nextMenu =menuitor.next();
			if("3".equals(nextMenu.getMenuLvl())){
				sysThirdMenuList.add(nextMenu);
				menuitor.remove();
			}else{
				break;
			}
		}
		for(SysMenu menu3:sysThirdMenuList){
			for(SysMenu menu2:sysSecondMenuList){
				if(menu3.getMenuParent().equals(menu2.getMenuNo())){
					if(menu2.getChildren()==null)menu2.setChildren(new ArrayList<SysMenu>());
					menu2.getChildren().add(menu3);
					break;
				}
			}
		}
		for(SysMenu menu2:sysSecondMenuList){
			for(SysMenu menu1:sysTopMenuList){
				if(menu2.getMenuParent().equals(menu1.getMenuNo())){
					if(menu1.getChildren()==null)menu1.setChildren(new ArrayList<SysMenu>());
					menu1.getChildren().add(menu2);
					break;
				}
			}
		}
		
		return sysTopMenuList;
	}
/**********************  Action method end ************************************/
	
	public SysMenu getSm() {
		return sm;
	}
	public void setSm(SysMenu sm) {
		this.sm = sm;
	}
	public String getMenuStr() {
		return menuStr;
	}
	public void setMenuStr(String menuStr) {
		this.menuStr = menuStr;
	}
	public String getRoleNo() {
		return roleNo;
	}
	public void setRoleNo(String roleNo) {
		this.roleNo = roleNo;
	}
	public SysMenuBo getSysMenuBo() {
		return sysMenuBo;
	}
	public void setSysMenuBo(SysMenuBo sysMenuBo) {
		this.sysMenuBo = sysMenuBo;
	}
	public String getMenuNo() {
		return menuNo;
	}
	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}
	public String getParentMenuNo() {
		return parentMenuNo;
	}
	public void setParentMenuNo(String parentMenuNo) {
		this.parentMenuNo = parentMenuNo;
	}
	public String getTcode() {
		return tcode;
	}
	public void setTcode(String tcode) {
		this.tcode = tcode;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public String getLvl() {
		return lvl;
	}
	public void setLvl(String lvl) {
		this.lvl = lvl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMenuSts() {
		return menuSts;
	}
	public void setMenuSts(String menuSts) {
		this.menuSts = menuSts;
	}

	public List<SysMenu> getSysmenuList() {
		return sysmenuList;
	}

	public void setSysmenuList(List<SysMenu> sysmenuList) {
		this.sysmenuList = sysmenuList;
	}
}