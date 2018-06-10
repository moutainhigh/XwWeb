package app.util.json;

import java.util.ArrayList;
import java.util.Iterator;

import app.creditapp.sys.entity.SysMenu;

public class SysJsonTree {
	/**
	 * 树的根节点，id=0
	 */
	private SysMenu root = null;
	private ArrayList nodelist = null;
	private StringBuffer returnStr = new StringBuffer();
	/**
	 * 是否需要复选框
	 */
	private boolean hasboxs = true;

	public boolean isHasboxs() {
		return hasboxs;
	}

	public void setHasboxs(boolean hasboxs) {
		this.hasboxs = hasboxs;
	}

	public SysJsonTree() {
		this.root = new SysMenu();
		this.root.setMenuNo("www");
		this.root.setMenuName("租赁管理系统");
	}

	/**
	 * 
	 * @param id
	 *            菜单id
	 * @param name
	 *            菜单名
	 * @param parentid
	 *            父菜单
	 * @param checked
	 *            复选框状态 0不显示 1显示
	 */
	public SysJsonTree(String id, String name) {
		this.root = new SysMenu();
		root.setMenuNo(id);
		root.setMenuName(name);
	}

	/**
	 * 以ArrayList 构造
	 * 
	 * @param list
	 */
	public SysJsonTree(ArrayList list) {
		this();
		this.nodelist = list;
	}

	/**
	 * 
	 * @param id 机构号
	 * @param name 机构名称
	 * @param list 数据
	 */
	public SysJsonTree(String id, String name,ArrayList list) {
		this(id,name);
		this.nodelist = list;
	}

	@SuppressWarnings("unchecked")
	public SysJsonTree(String id, String name,ArrayList list,boolean isTop) {
		this(id,name);
		if(isTop){
			
		
		SysMenu first = new SysMenu();
		first.setMenuNo("1");
		first.setMenuName(name);
		first.setMenuParent("0");	
		//list.add(first);
		ArrayList listNewArrayList  = new ArrayList<SysMenu>();

		for (SysMenu n : (ArrayList<SysMenu>)list) {
			if(n.getMenuParent().equals("0")){
				n.setMenuParent("1");
			}
			listNewArrayList.add(n);
		}
		
		listNewArrayList.add(first);
		this.nodelist = listNewArrayList;
		}
		else {
			this.nodelist = list;
		}
	}	
	public String getJson() {
		return getJson(this.nodelist, root);
	}
	
	@SuppressWarnings("unchecked")
	public void getJson1(ArrayList list, SysMenu node) {
		if (hasChild(list, node)) {
			returnStr.append("{id:'");
			returnStr.append(node.getMenuNo());
			returnStr.append("',text:'");
			returnStr.append(node.getMenuName());
			returnStr.append("',lvl:'");
			returnStr.append(node.getMenuUrl());
			
			returnStr.append("',userdata: [{name:'url',content:'");
			returnStr.append(node.getMenuUrl());
			returnStr.append("'}]");
			
			
			returnStr.append(",item:[");
			ArrayList childList = getChildrens(list, node);
			Iterator it = childList.iterator();
			while (it.hasNext()) {
				SysMenu n = (SysMenu) it.next();
				getJson1(list, n);
			}
			returnStr.append("]},");
		} else {
			returnStr.append("{id:'");
			returnStr.append(node.getMenuNo());
			returnStr.append("',text:'");
			returnStr.append(node.getMenuName());
			returnStr.append("',lvl:'");
			returnStr.append(node.getMenuUrl());
			
			//userdata：[{name:'type',content:'1'}]
			returnStr.append("',userdata: [{name:'url',content:'");
			returnStr.append(node.getMenuUrl());
			returnStr.append("'}]");
			
			returnStr.append(",item:[]},");
		}
	}
	@SuppressWarnings("unchecked")
	public String getJson(ArrayList list, SysMenu node) {
		//returnStr.append("{id:'0',text:'机构选择',item:[");
		getJson1(list, node);
		String str = returnStr.toString().replace(",]", "]");
		return str.substring(0, str.length() - 1);
	}

	/**
	 * 是否有孩子节点
	 * 
	 * @param list
	 * @param node
	 * @return
	 */
	private boolean hasChild(ArrayList list, SysMenu node) {
		return getChildrens(list, node).size() > 0 ? true : false;
	}

	/**
	 * 取所有孩子节点
	 * 
	 * @param list
	 * @param node
	 * @return
	 */
	private ArrayList getChildrens(ArrayList list, SysMenu node) {
		ArrayList childrens = new ArrayList();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			SysMenu n = (SysMenu) it.next();
			if (n.getMenuParent() != null) {
				if (n.getMenuParent().equals(node.getMenuNo())) {
					childrens.add(n);
				}
			}

		}
		return childrens;
	}

	private String checked(String ch) {
		if (!hasboxs) {
			return "null";
		}
		return ch;
	}

	public SysMenu getRoot() {
		return root;
	}

	public void setRoot(SysMenu root) {
		this.root = root;
	}

	public ArrayList getNodelist() {
		return nodelist;
	}

	public void setNodelist(ArrayList nodelist) {
		this.nodelist = nodelist;
	}

	public StringBuffer getReturnStr() {
		return returnStr;
	}

	public void setReturnStr(StringBuffer returnStr) {
		this.returnStr = returnStr;
	}

}
