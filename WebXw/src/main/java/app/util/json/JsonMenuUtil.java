package app.util.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.creditapp.sys.entity.SysMenu;

public class JsonMenuUtil {

	/**
	 * 把全部无序的树形菜单列表，排成有序，并以json字符串的形式输出
	 * @param sysMenuList
	 * @return
	 */
	public static String ulist2json(List<SysMenu> sysMenuList){
		String jsonStr = null;
		List<SysMenu> list = JsonMenuUtil.getTreeMenuList(sysMenuList);
		jsonStr = JsonMenuUtil.llist2json(list);
		return jsonStr;
	}
	
	/**
	 * 将有序的菜单树转换成json格式
	 * @param sysMenuList
	 * @return
	 */
	public static String llist2json(List<SysMenu> sysMenuList){
		StringBuffer jsonbf = new StringBuffer();

		try{
		
		/*[{ "id":"1", "name":"日常提醒", "url":"#", "target":"", "imgurl":"pageframe/images1/menu/nav_ico1.png", nodes:[
           { "id":"11", "name":"日常提醒", "url":"#", "target":"", nodes:[
       		{ "id":"117", "name":"查看页面20", "url":"查看页面20.html", "target":"rightFrame"},*/
		String mF= "";
		
		jsonbf.append("[");
		for(int i=0; i<sysMenuList.size(); i++){
			SysMenu sys_Menu = sysMenuList.get(i);
			jsonbf.append("{").append("\"id\":\"").append(sys_Menu.getMenuNo()).append("\", \"name\":\"").append(sys_Menu.getMenuName())
			.append("\" ,\"url\":\"#\", \"tartget\":\"\", \"imgurl\":\"pageframe/images1/menu/nav_ico").append(i+1).append(".png\", nodes:[");
			for(int j=0; j<sys_Menu.getChildren().size(); j++){
				SysMenu sys_Menu2 = sys_Menu.getChildren().get(j);
				jsonbf.append("{\"id\":\"").append(sys_Menu2.getMenuNo()).append("\", \"name\":\"").append(sys_Menu2.getMenuName())
				.append("\", \"url\":\"#\", \"target\":\"\", nodes:[");
				for(int k=0; k<sys_Menu2.getChildren().size(); k++){
					SysMenu sys_Menu3 = sys_Menu2.getChildren().get(k);
					if((null !=sys_Menu3.getMenuUrl()) && ( sys_Menu3.getMenuUrl().contains("?"))){
						mF = "&menuno="+sys_Menu3.getMenuNo();
					}else{
						mF = "?menuno="+sys_Menu3.getMenuNo();
					}
					jsonbf.append("{\"id\":\"").append(sys_Menu3.getMenuNo()).append("\", \"name\":\"").append(sys_Menu3.getMenuName().trim())
					.append("\", \"url\":\"").append(sys_Menu3.getMenuUrl()+mF).append("\", \"target\":\"rightFrame\"}");
					if(k < sys_Menu2.getChildren().size()-1){
						jsonbf.append(",");
					}
				}
				jsonbf.append("]}");
				if(j < sys_Menu.getChildren().size()-1){
					jsonbf.append(",");
				}
			}
			jsonbf.append("]}");
			if(i < sysMenuList.size()-1){
				jsonbf.append(",");
			}
		}
		jsonbf.append("]");
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return jsonbf.toString();
	}
	
	public static Map<String,String> ulist2tree(List<SysMenu> sysMenuList){
		List<SysMenu> list = JsonMenuUtil.getTreeMenuList(sysMenuList);
		return JsonMenuUtil.llist2tree(list);
	}

	
	public static Map<String,String> llist2tree(List<SysMenu> sysMenuList){
		Map<String,String> map = new HashMap<String,String>();
		try{
			SysMenu sysMenu2,sysMenu3;
			for(SysMenu sysMenu1 : sysMenuList){
				StringBuffer content = new StringBuffer("<div id='accordion' class='entry_basic'>");
				if(sysMenu1.getChildren()!=null && sysMenu1.getChildren().size()>0){
					for(int i=0;i<sysMenu1.getChildren().size();i++){
						sysMenu2 = sysMenu1.getChildren().get(i);
						if(i==0){
							content.append("<a>");
						}else {
							content.append("<a class='entry_basic_a'>");
						}
						content.append(sysMenu2.getMenuName()).append("</a><div class='zTreeDemoBackground left'>");
						if(sysMenu2.getChildren()!=null && sysMenu2.getChildren().size()>0){
							content.append("<ul style='padding:5px 10px;'>");
							for(int j=0;j<sysMenu2.getChildren().size();j++){
								sysMenu3 = sysMenu2.getChildren().get(j);
								String url = sysMenu3.getMenuUrl();
								if(url==null || "".equals(url)){
									url = "";
								}else {
									if(url.contains("?")){
										url = url + "&menuno=" + sysMenu3.getMenuNo();
									}else {
										url = url + "?menuno=" + sysMenu3.getMenuNo();
									}
								}
								content.append("<li onclick='goMenuUrl(this)' url='").append(url).append("'>")
								.append(sysMenu3.getMenuName()).append("</li>");
							}
							content.append("</ul>");
						}
						content.append("</div>\n");
					}
				}
				content.append("</div>");
				map.put(sysMenu1.getMenuNo(), content.toString());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 根据菜单列表，按标准树形菜单进行整理
	 * @param sysMenuList
	 * @return 返回整个树形菜单列表
	 */
	public static List<SysMenu> getTreeMenuList(List<SysMenu> sysMenuList){
		List<SysMenu> list1 = new ArrayList<SysMenu>();
		List<SysMenu> list2 = new ArrayList<SysMenu>();
		List<SysMenu> list3 = new ArrayList<SysMenu>();
		for(SysMenu sys_Menu : sysMenuList){
			if("1".equals(sys_Menu.getMenuLvl())){
				list1.add(sys_Menu);
			}
			if("2".equals(sys_Menu.getMenuLvl())){
				list2.add(sys_Menu);
			}
			if("3".equals(sys_Menu.getMenuLvl())){
				list3.add(sys_Menu);
			}
		}
		for(SysMenu sys_Menu : list3){
			for(SysMenu vsys_Menu : list2){
				if(vsys_Menu.getMenuNo().equals(sys_Menu.getMenuParent())){
					vsys_Menu.getChildren().add(sys_Menu);
					break;
				}
			}
		}
		for(SysMenu sys_Menu : list2){
			for(SysMenu vsys_Menu : list1){
				if(vsys_Menu.getMenuNo().equals(sys_Menu.getMenuParent())){
					vsys_Menu.getChildren().add(sys_Menu);
					break;
				}
			}
		}
		return list1;
	}
}
