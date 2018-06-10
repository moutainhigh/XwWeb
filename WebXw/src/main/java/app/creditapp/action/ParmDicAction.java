package app.creditapp.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import app.creditapp.bo.ParmDicBO;
import app.creditapp.entity.ParmDic;
import app.oscache.CachecodeUtil;
import app.oscache.MBaseCache;
import app.util.toolkit.Ipage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

public class ParmDicAction extends BaseFormBean {

	// 页面传值
	private ParmDic parmdic;
	private String if_edit; // 是否允许编辑
	private List<ParmDic> parmdicList;

	// 注入ParmKeyBO
	private ParmDicBO parmdicbo;

	private String key_name;
	private String opt_code;// 下拉项值
	private String query;
	private String view;
	private FormData formsys4031; // 新增表单
	private FormData formsys4032; // 修改表单
	private FormService formService = new FormService();

	public ParmDicAction() {
		formsys4031 = formService.getFormData("sys4031");
		query = "";
		view = "";
	}

	/**
	 * ParmDic查询
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		formsys4031 = formService.getFormData("sys4031");
		getFormValue(formsys4031);
		Ipage ipage = this.getIpage();
		parmdic=new ParmDic();
		setObjValue(formsys4031, parmdic);
		parmdicList = (List) parmdicbo.findByPage(ipage, parmdic).getResult();
		return "list";
	}

	/**
	 * 进入修改页面
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		formsys4032 = formService.getFormData("sys4032");
		parmdic = new ParmDic();
		parmdic.setKey_name(key_name);
		getObjValue(formsys4032, parmdic);
		return "input";
	}

	// /**
	// * 修改操作
	// * @return
	// * @throws Exception
	// */
	// public String InsertOrUpdate() throws Exception {
	// this.parmdicbo.Update(parmdic);
	// // System.out.println("11111111111");
	// this.setMessage("操作成功");
	// return "input_result";
	// }

	/**
	 * 新增操作
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		formsys4032 = formService.getFormData("sys4032");
		getFormValue(formsys4032);
		parmdic = new ParmDic();
		setObjValue(formsys4032, parmdic);
		parmdicbo.Insert(parmdic);
		this.addActionMessage("保存成功！");
		getObjValue(formsys4032, parmdic);
		this.changeFormProperty(formsys4032, "key_name", "readonly", "1");
		this.changeFormProperty(formsys4032, "opt_code", "readonly", "1");
		return "detail";
	}

	/**
	 * 新增验证
	 * 
	 * @throws Exception
	 */
	public void validateInsert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		formsys4032 = formService.getFormData("sys4032");
		getFormValue(formsys4032);
		validateFormData(formsys4032);
		// 将值赋给对象用来进行进一步的验证
		// 将值赋给对象用来进行进一步的验证
		parmdic = new ParmDic();
		setObjValue(formsys4032, parmdic);
		parmdic=parmdicbo.getById(parmdic);
		if(null!=parmdic){
			this.addFieldError("数据字典项", "数据字典项已存在，请更换选项值！");
		}
	}

	/**
	 * 修改操作
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		formsys4032 = formService.getFormData("sys4032");
		getFormValue(formsys4032);
		this.changeFormProperty(formsys4032, "key_name", "readonly", "1");
		this.changeFormProperty(formsys4032, "opt_code", "readonly", "1");
		parmdic = new ParmDic();
		setObjValue(formsys4032, parmdic);
		parmdicbo.Update(parmdic);
		this.addActionMessage("保存成功！");
		getObjValue(formsys4032, parmdic);
		return "detail";
	}

	/**
	 * 修改验证
	 * 
	 * @throws Exception
	 */
	public void validateUpdate() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		formsys4032 = formService.getFormData("sys4032");
		getFormValue(formsys4032);
		validateFormData(formsys4032);
		// 将值赋给对象用来进行进一步的验证
		parmdic = new ParmDic();
		setObjValue(formsys4032, parmdic);
	}

	public String getById() {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		formsys4032 = formService.getFormData("sys4032");
		parmdic = new ParmDic();
		parmdic.setKey_name(key_name);
		parmdic.setOpt_code(opt_code);
		parmdic = parmdicbo.getById(parmdic);
		getObjValue(formsys4032, parmdic);
		this.changeFormProperty(formsys4032, "key_name", "readonly", "1");
		this.changeFormProperty(formsys4032, "opt_code", "readonly", "1");
//		if (view.equals("view")) {
//			query = "query";
//		}
		return "detail";
	}
	
	public String del() {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		parmdic = new ParmDic();
		parmdic.setKey_name(key_name);
		parmdic.setOpt_code(opt_code);
		parmdicbo.del(parmdic);
		return "return_list";
	}

	public String example() {
		System.out.println("------------------"
				+ ServletActionContext.getRequest().getParameter("path"));
		String path = ServletActionContext.getRequest().getParameter("path");
		String[] paths = path.split(",");
		for (int i = 0; i < paths.length; i++) {
			String k = paths[i].replace("|", "");
			System.out.println("p=====" + k);

		}
		return null;
	}
	public String flushCache() throws Exception{
		HttpServletResponse response = getHttpResponse();
		response.setContentType("text/html;charset=GBK");
		PrintWriter pw = response.getWriter();
		try {
			MBaseCache.getCache().removeByGroup(String.valueOf(CachecodeUtil.CACHE_DATADICT));
			MBaseCache.getCache().removeByGroup(String.valueOf(CachecodeUtil.SECURITY));// 安全审计缓存
			MBaseCache.getCache().removeAll();
			MBaseCache.getCache().reloadButton(); // 重新加载按钮权限
			pw.write("success");
		} catch (Exception e) {
			e.printStackTrace();
			pw.write("false");
		}
		return null;
	}
	//业务期限品种
	public String getFindlist() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		parmdic = new ParmDic();
		parmdic.setKey_name(key_name);
		//通过key查询到数据字典list列表
		parmdicList = parmdicbo.findlist(parmdic);//将list保存到一个数组中
		this.getHttpResponse().setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = this.getHttpResponse().getWriter();
		JSONArray json_list = (JSONArray)JSON.toJSON(parmdicList);
        System.out.println(">>>>>>>>>>>>data:" + json_list) ;
        out.print(json_list);
		return null;
	}

	public String getIf_edit() {
		return if_edit;
	}

	public void setIf_edit(String if_edit) {
		this.if_edit = if_edit;
	}

	public ParmDic getParmdic() {
		return parmdic;
	}

	public void setParmdic(ParmDic parmdic) {
		this.parmdic = parmdic;
	}

	public List<ParmDic> getParmdicList() {
		return parmdicList;
	}

	public void setParmdicList(List<ParmDic> parmdicList) {
		this.parmdicList = parmdicList;
	}

	public ParmDicBO getParmdicbo() {
		return parmdicbo;
	}

	public void setParmdicbo(ParmDicBO parmdicbo) {
		this.parmdicbo = parmdicbo;
	}

	public String getKey_name() {
		return key_name;
	}

	public void setKey_name(String key_name) {
		this.key_name = key_name;
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

	public FormData getFormsys4031() {
		return formsys4031;
	}

	public void setFormsys4031(FormData formsys4031) {
		this.formsys4031 = formsys4031;
	}

	public FormData getFormsys4032() {
		return formsys4032;
	}

	public void setFormsys4032(FormData formsys4032) {
		this.formsys4032 = formsys4032;
	}

	public FormService getFormService() {
		return formService;
	}

	public void setFormService(FormService formService) {
		this.formService = formService;
	}

	public String getOpt_code() {
		return opt_code;
	}

	public void setOpt_code(String opt_code) {
		this.opt_code = opt_code;
	}
}