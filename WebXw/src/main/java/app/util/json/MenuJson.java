package app.util.json;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import app.creditapp.sys.entity.SysMenu;

public class MenuJson {
    private SysMenu root = null;

    public MenuJson() {
	this.root = new SysMenu();
	this.root.setMenuNo("0");
	this.root.setMenuName("小微金融运管平台");
    }

    public MenuJson(List<SysMenu> lst) {
	this();
	for (Iterator<SysMenu> iterator = lst.iterator(); iterator.hasNext();) {
	    SysMenu sysMenu = (SysMenu) iterator.next();
	    appendChild(sysMenu);
	}
    }

    public void init(Map<String, List<SysMenu>> menus) {
	for (int index = 1; index <= menus.size(); index++) {
	    String key = "menu_" + String.valueOf(index);
	    ArrayList<SysMenu> list = (ArrayList<SysMenu>) menus.get(key);
	    for (int i = 0; i < list.size(); i++) {
		appendChild(list.get(i));
	    }
	}
    }

    public void appendChild(SysMenu node) {
	SysMenu _node = this.indexNode(root, node.getMenuParent());
	if (_node == null)
	    return;
	_node.addChildren(node);
    }

    public SysMenu indexNode(SysMenu node, String id) {
	if (node == null || id == null) {
	    return null;
	}
	SysMenu _node = null;
	if (id.equals(this.root.getMenuNo())) {
	    return this.root;
	}
	ArrayList<SysMenu> list = node.getChildren();
	if (list == null || list.size() == 0) {
	    return null;
	}
	for (int index = 0; index < list.size(); index++) {
	    _node = list.get(index);
	    if (!id.equals(_node.getMenuNo())) {
		_node = indexNode(_node, id);
		if (_node != null) {
		    return _node;
		}
	    } else {
		break;
	    }
	}
	return _node;
    }

    public void setRootName(String name) {
	this.root.setMenuName(name);
    }

    public String toJson() {
	return "(" + this.root.toJson() + ")";
    }
}
