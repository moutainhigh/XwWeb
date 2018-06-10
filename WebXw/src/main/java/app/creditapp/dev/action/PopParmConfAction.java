package  app.creditapp.dev.action;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import app.creditapp.dev.bo.PopParmConfBo;
import app.creditapp.dev.entity.PopParmConf;
import app.util.User;
import app.util.toolkit.Ipage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: PopParmConfAction.java
 * Description:
 **/
public class PopParmConfAction extends BaseFormBean {

	//页面传值
	private PopParmConf popParmConf;
	private List<PopParmConf> popParmConfList;

	//注入PopParmConfBo
	private PopParmConfBo popParmConfBo;

	private String query;
	private String scene_id;
	private String parms;	
	private String query_sql;
	private String if_checkbox;
	private FormData formdev0001;
	private FormData formdev0002;
	private FormData formdev0102;
	
	private FormService formService = new FormService();
	
	public PopParmConfAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formdev0001 = formService.getFormData("dev0001");
		popParmConf = new PopParmConf();
		getFormValue(formdev0001);
		setObjValue(formdev0001, popParmConf);
		popParmConf.setScene_id(scene_id);
		if_checkbox = if_checkbox==null?"0":if_checkbox;
		popParmConf.setIf_checkbox(if_checkbox);
		Ipage ipage = this.getIpage();
		popParmConfList = (List) popParmConfBo.findByPage(ipage, popParmConf).getResult();
		return "list";
	}
	
	public String findForCheckBox() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formdev0001 = formService.getFormData("dev0001");
		popParmConf = new PopParmConf();
		getFormValue(formdev0001);
		setObjValue(formdev0001, popParmConf);
		Ipage ipage = this.getIpage();
		popParmConf.setIf_checkbox("1");
		popParmConfList = (List) popParmConfBo.findByPage(ipage, popParmConf).getResult();
		return "list";
	}
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formdev0002 = formService.getFormData("dev0002");
		return "input";
	}
	public String inputChB() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formdev0102 = formService.getFormData("dev0102");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formdev0002 = formService.getFormData("dev0002");
		getFormValue(formdev0002);
		popParmConf = new PopParmConf();
		setObjValue(formdev0002, popParmConf);
		popParmConfBo.insert(popParmConf);
		this.addActionMessage("保存成功!");
//		getObjValue(formdev0002, popParmConf);
//		return "detail";formdev0001 = formService.getFormData("dev0001");
		formdev0001 = formService.getFormData("dev0001");
		PopParmConf popParmConf2 = new PopParmConf();
		if_checkbox = if_checkbox==null?"0":if_checkbox;
		popParmConf2.setIf_checkbox(if_checkbox);
		Ipage ipage = this.getIpage();
		popParmConfList = (List) popParmConfBo.findByPage(ipage, popParmConf2).getResult();
		return "list";
	}
	
	public String insertChB() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formdev0102 = formService.getFormData("dev0102");
		getFormValue(formdev0102);
		popParmConf = new PopParmConf();
		setObjValue(formdev0102, popParmConf);
		popParmConfBo.insert(popParmConf);
		this.addActionMessage("保存成功!");
		formdev0001 = formService.getFormData("dev0001");
		PopParmConf	popParmConf1= new PopParmConf();
		Ipage ipage = this.getIpage();
		popParmConf1.setIf_checkbox("1");
		popParmConfList = (List) popParmConfBo.findByPage(ipage, popParmConf1).getResult();
		return "list";
	}
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formdev0002 = formService.getFormData("dev0002");
		getFormValue(formdev0002);
		popParmConf = new PopParmConf();
		setObjValue(formdev0002, popParmConf);
		popParmConfBo.update(popParmConf);
		this.addActionMessage("保存成功!");
//		getObjValue(formdev0002, popParmConf);
		formdev0001 = formService.getFormData("dev0001");
		PopParmConf popParmConf2 = new PopParmConf();
		if_checkbox = if_checkbox==null?"0":if_checkbox;
		popParmConf2.setIf_checkbox(if_checkbox);
		Ipage ipage = this.getIpage();
		popParmConfList = (List) popParmConfBo.findByPage(ipage, popParmConf2).getResult();
		return "list1";
	}
	public String updateChB() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formdev0002 = formService.getFormData("dev0002");
		getFormValue(formdev0002);
		popParmConf = new PopParmConf();
		setObjValue(formdev0002, popParmConf);
		popParmConfBo.update(popParmConf);
		this.addActionMessage("保存成功!");
//		getObjValue(formdev0002, popParmConf);
		formdev0001 = formService.getFormData("dev0001");
		PopParmConf	popParmConf1= new PopParmConf();
		Ipage ipage = this.getIpage();
		popParmConf1.setIf_checkbox("1");
		popParmConfList = (List) popParmConfBo.findByPage(ipage, popParmConf1).getResult();
		return "list";
	}
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formdev0001 = formService.getFormData("dev0001");
		popParmConf = new PopParmConf();
		popParmConf.setScene_id(scene_id);
		popParmConf.setIf_checkbox(if_checkbox);
		popParmConfBo.del(popParmConf);
		this.addActionMessage("删除成功");
		formdev0001 = formService.getFormData("dev0001");
		PopParmConf popParmConf2 = new PopParmConf();
		if_checkbox = if_checkbox==null?"0":if_checkbox;
		popParmConf2.setIf_checkbox(if_checkbox);
		Ipage ipage = this.getIpage();
		popParmConfList = (List) popParmConfBo.findByPage(ipage, popParmConf2).getResult();
		return "list";
	}
	
	public String delbox() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formdev0001 = formService.getFormData("dev0001");
		popParmConf = new PopParmConf();
		popParmConf.setScene_id(scene_id);
		popParmConf.setIf_checkbox(if_checkbox);
		popParmConfBo.del(popParmConf);
		this.addActionMessage("删除成功");
		formdev0001 = formService.getFormData("dev0001");
		PopParmConf	popParmConf1= new PopParmConf();
		Ipage ipage = this.getIpage();
		popParmConf1.setIf_checkbox("1");
		popParmConfList = (List) popParmConfBo.findByPage(ipage, popParmConf1).getResult();
		return "list";
	}
	
	/**
	 * 测试操作
	 * @return
	 * @throws Exception
	 */
	public String test() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formdev0001 = formService.getFormData("dev0001");
		popParmConf = new PopParmConf();
		popParmConf.setScene_id(scene_id);
		popParmConf.setIf_checkbox(if_checkbox);
		// this.addActionMessage("测试成功");
		Ipage ipage = this.getIpage();
		popParmConfList = (List) popParmConfBo.findByPage(ipage, popParmConf).getResult();
		return "list";
	}
	
	/**
	 * @description 通过生成的Sql语句查询是否有多条记录
	 * @version 1.0
	 */
	public void queryAjax() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		this.getHttpResponse().setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = this.getHttpResponse().getWriter();
		Map map = new HashMap();
		parms = URLDecoder.decode(parms, "UTF-8"); 
		map = popParmConfBo.queryAjax(scene_id, parms);
		JSONObject json_map = (JSONObject)JSON.toJSON(map);
		out.print(json_map);
	}
	/**
	 * @description pop公用复选框获得sql语句
	 * @version 1.0
	 */
	public void createSql() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		this.getHttpResponse().setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = this.getHttpResponse().getWriter();
		Map map = new HashMap();
		parms = URLDecoder.decode(parms, "UTF-8"); 
		map = popParmConfBo.createSql(scene_id, parms);
		
//		JSONObject json_map=JSONObject.fromObject(map);//
		
		JSONObject json_map = (JSONObject)JSON.toJSON(map);
		
		out.print(json_map);
	}
	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getByIdChB() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
			formdev0102 = formService.getFormData("dev0102");
		popParmConf = new PopParmConf();
		popParmConf.setScene_id(scene_id);
		popParmConf = popParmConfBo.getById(popParmConf);
			getObjValue(formdev0102, popParmConf);
		return "detail";
	}
	
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
//		if("1".equals(if_checkbox)){
//			formdev0102 = formService.getFormData("dev0102");
//		}else{
			formdev0002 = formService.getFormData("dev0002");
//		}
		popParmConf = new PopParmConf();
		popParmConf.setScene_id(scene_id);
		popParmConf = popParmConfBo.getById(popParmConf);
//		if("1".equals(if_checkbox)){
//			getObjValue(formdev0102, popParmConf);
//		}else{
			getObjValue(formdev0002, popParmConf);
//		}
		
		return "detail";
	}
	
	/**
	 * @方法描述 POP分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPop() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		this.getHttpResponse().setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = this.getHttpResponse().getWriter();
		Map map = new HashMap();
		parms = URLDecoder.decode(parms, "UTF-8"); 
		if( query_sql!=null && !"".equals(query_sql) ){
			query_sql = URLDecoder.decode(query_sql, "UTF-8"); 
		}
//		if(("POP019".equals(scene_id)||"POP501".equals(scene_id))&&(!parms.contains("op_no"))){
//			parms="op_no="+User.getLoginid(getHttpRequest())+","+parms;
//		}
		map = popParmConfBo.findByPop(scene_id, parms,query_sql);
//		JSONObject json_map=JSONObject.fromObject(map);//
		JSONObject json_map = (JSONObject)JSON.toJSON(map);
		out.print(json_map);
		return null;
	}
	
	public String findByPopChB() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		this.getHttpResponse().setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = this.getHttpResponse().getWriter();
		Map map = new HashMap();
		parms = URLDecoder.decode(parms, "UTF-8"); 
		System.out.println("POP-parms:"+parms);
		map = popParmConfBo.findByPopChB(scene_id, parms);
//		JSONObject json_map=JSONObject.fromObject(map);//
		JSONObject json_map = (JSONObject)JSON.toJSON(map);
		out.print(json_map);
		return null;
	}
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formdev0002 = formService.getFormData("dev0002");
		 getFormValue(formdev0002);
		 validateFormData(formdev0002);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 if("1".equals(if_checkbox)){
				formdev0102 = formService.getFormData("dev0102");
				getFormValue(formdev0102);
				 validateFormData(formdev0102);
			}else{
				formdev0002 = formService.getFormData("dev0002");
				getFormValue(formdev0002);
				validateFormData(formdev0002);
			}
  	}
	public PopParmConf getPopParmConf() {
		return popParmConf;
	}
	public void setPopParmConf(PopParmConf  popParmConf) {
		this.popParmConf = popParmConf;
	}
	public List<PopParmConf> getPopParmConfList() {
		return popParmConfList;
	}
	public void setPopParmConfList(List<PopParmConf> popParmConfList) {
		this.popParmConfList = popParmConfList;
	}
	public PopParmConfBo getPopParmConfBo() {
		return popParmConfBo;
	}
	public void setPopParmConfBo(PopParmConfBo popParmConfBo) {
		this.popParmConfBo = popParmConfBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormdev0002() {
		return formdev0002;
	}
	public void setFormdev0002(FormData formdev0002) {
		this.formdev0002 = formdev0002;
	}
	public FormData getFormdev0001() {
		return formdev0001;
	}
	public void setFormdev0001(FormData formdev0001) {
		this.formdev0001 = formdev0001;
	}
	public void setScene_id(String scene_id){
		this.scene_id = scene_id;
	}		
	public String getScene_id(){
		return scene_id;
	}
	public String getParms() {
		return parms;
	}
	public void setParms(String parms) {
		this.parms = parms;
	}
	public String getQuery_sql() {
		return query_sql;
	}
	public void setQuery_sql(String querySql) {
		query_sql = querySql;
	}
	public FormData getFormdev0102() {
		return formdev0102;
	}
	public String getIf_checkbox() {
		return if_checkbox;
	}
	public void setIf_checkbox(String ifCheckbox) {
		if_checkbox = ifCheckbox;
	}
	public void setFormdev0102(FormData formdev0102) {
		this.formdev0102 = formdev0102;
	}
}