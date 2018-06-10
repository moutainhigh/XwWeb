package app.creditapp.sys.action;

import java.io.PrintWriter;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import app.base.BaseAction;
import app.base.SourceTemplate;
import app.creditapp.sys.bo.TableDocBO;
import app.creditapp.sys.entity.TableDoc;
import app.creditapp.sysConfig.entity.TreeNode;

import com.alibaba.fastjson.JSON;
import com.core.struts.ActionContext;

public class TableDocAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	// 页面传值
	private TableDoc tableDoc;// 菜单实体
	private String menuStr;// 字符串组成式菜单
	private List<TableDoc> tableDocList;
	private String nodes;

	private String dealtype;

	private String doc_no;
	private String doc_name;
	private String lev;
	private String uplev;

	// 注入TableDocBO
	private TableDocBO tableDocBo;

	/**
	 * 返回所有菜单组成的字符串
	 * 
	 * @return
	 */
	public String getAllMenu() throws Exception {
		menuStr = tableDocBo.getAllJsonMenu();
		return "allMenu";

	}

	public TableDoc getTableDoc() {
		return tableDoc;
	}

	public void setTableDoc(TableDoc tableDoc) {
		this.tableDoc = tableDoc;
	}

	public String getMenuStr() {
		return menuStr;
	}

	public void setMenuStr(String menuStr) {
		this.menuStr = menuStr;
	}

	public List<TableDoc> getTableDocList() {
		return tableDocList;
	}

	public void setTableDocList(List<TableDoc> tableDocList) {
		this.tableDocList = tableDocList;
	}

	public TableDocBO getTableDocBo() {
		return tableDocBo;
	}

	public void setTableDocBo(TableDocBO tableDocBo) {
		this.tableDocBo = tableDocBo;
	}

	public String menuTreeUpdate() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		this.getHttpResponse().setContentType("text/html;charset=utf-8");
		PrintWriter out = this.getHttpResponse().getWriter();
		int rv = 0;
		try {
			TableDocBO tableDocBo = (TableDocBO) SourceTemplate
					.getSpringContextInstance().getBean("tableDocBo");
			TableDoc sa = new TableDoc();
			sa.setDoc_no(doc_no);
			sa.setDoc_name(doc_name);
			sa.setLev(lev);
			sa.setUplev(uplev);
			if (!"3".equals(dealtype)) {// 新增 or 修改
				tableDocBo.insertOrUpdate(sa);
			} else {// 删除
				tableDocBo.del(doc_no);
			}
		} catch (Exception e) {
			System.out.println(rv);
			out.println(rv);
		}
		rv = 1;

		System.out.println(rv);
		out.println(rv);
		return null;
	}

	public String createdoc() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		this.getHttpResponse().setContentType("text/html;charset=utf-8");
		PrintWriter out = this.getHttpResponse().getWriter();
		int rv = 0;
		try {
			TableDocBO tableDocBo = (TableDocBO) SourceTemplate
					.getSpringContextInstance().getBean("tableDocBo");

			tableDocBo.createdoc();
		} catch (Exception e) {
			System.out.println(rv);
			out.println(rv);
		}
		rv = 1;

		System.out.println(rv);
		out.println(rv);
		return null;
	}

	public String saveParm() throws Exception {
		nodes = java.net.URLDecoder.decode(new String(nodes.getBytes("GBK"),
				"ISO-8859-1"), "UTF-8");
		this.getHttpResponse().setContentType("text/html;charset=utf-8");
		PrintWriter out = this.getHttpResponse().getWriter();

		String flag = "success";
		try {
			List<TreeNode> treeNodes = JSON.parseArray(nodes,
					TreeNode.class);
			tableDocBo.insertParmBatch(treeNodes);
		} catch (Exception e) {
			flag = "fail";
			e.printStackTrace();
		}
		out.println(flag);
		return null;
	}

	public String getDealtype() {
		return dealtype;
	}

	public void setDealtype(String dealtype) {
		this.dealtype = dealtype;
	}

	public String getLev() {
		return lev;
	}

	public void setLev(String lev) {
		this.lev = lev;
	}

	public String getUplev() {
		return uplev;
	}

	public void setUplev(String uplev) {
		this.uplev = uplev;
	}

	public String getDoc_no() {
		return doc_no;
	}

	public void setDoc_no(String docNo) {
		doc_no = docNo;
	}

	public String getDoc_name() {
		return doc_name;
	}

	public void setDoc_name(String docName) {
		doc_name = docName;
	}

	public String getNodes() {
		return nodes;
	}

	public void setNodes(String nodes) {
		this.nodes = nodes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}