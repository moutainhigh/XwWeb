package app.creditapp.proj.action;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import app.creditapp.proj.bo.RptProjBo;
import app.creditapp.proj.entity.RptProj;
import app.util.toolkit.Ipage;
import com.core.domain.screen.FormData;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;
public class RptProjAction extends BaseFormBean {
	
	private String query;
	private String view;
	
	private RptProj rptProj;
	
	private FormData formfin;
	
	private List<RptProj> list;
	
	private RptProjBo rptProjBo;
	
	public RptProjAction(){
		query = "";
		view = "";
	}
	
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		return "input";
	}
	
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		Ipage ipage = this.getIpage();
		rptProj=new RptProj();
		getFormValue(formfin);
		setObjValue(formfin, rptProj);
		list=(List)rptProjBo.findByPage(ipage, rptProj).getResult();
		return "list";
	}
	
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		getFormValue(formfin);
		rptProj=new RptProj();
	    setObjValue(formfin,rptProj);
	    rptProjBo.insert(rptProj);
	    
		//以下操作是为了跳转到详情查看页面
		getObjValue(formfin,rptProj);
		query = "query";
		return "detail";
	}
	
	/**
	 * 新增验证
	 * @throws Exception
	 */
	public void validateInsert() throws Exception{
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		getFormValue(formfin);
		validateFormData(formfin) ;
		//将值赋给对象用来进行进一步的验证
		rptProj=new RptProj();
	    setObjValue(formfin,rptProj);
	}
	
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		getFormValue(formfin);
		rptProj=new RptProj();
	    setObjValue(formfin,rptProj);
	    rptProjBo.update(rptProj);
		//以下操作是为了跳转到详情查看页面
		getObjValue(formfin,rptProj);
		query = "query";
		return "detail";
	}

	/**
	 * 修改验证
	 * @throws Exception
	 */
	public void validateUpdate() throws Exception{
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		getFormValue(formfin);
		validateFormData(formfin) ;
		//将值赋给对象用来进行进一步的验证
		rptProj=new RptProj();
	    setObjValue(formfin,rptProj);
	}
	
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		rptProj=new RptProj();

		rptProjBo.del(rptProj);
		this.setMessage("操作成功");
		return "return_list";
	}

	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		rptProj=new RptProj();
		
		rptProj = rptProjBo.getById(rptProj);
		getObjValue(formfin,rptProj);
		 if(view.equals("view")){
			 query = "query";
		 }
		return "detail";
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

	public RptProj getRptProj() {
		return rptProj;
	}

	public void setRptProj(RptProj rptProj) {
		this.rptProj = rptProj;
	}

	public List<RptProj> getList() {
		return list;
	}

	public void setList(List<RptProj> list) {
		this.list = list;
	}

	public RptProjBo getRptProjBo() {
		return rptProjBo;
	}

	public void setRptProjBo(RptProjBo rptProjBo) {
		this.rptProjBo = rptProjBo;
	}
}
