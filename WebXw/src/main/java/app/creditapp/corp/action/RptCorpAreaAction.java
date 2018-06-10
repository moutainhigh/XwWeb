package app.creditapp.corp.action;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import app.creditapp.corp.bo.RptCorpAreaBo;
import app.creditapp.corp.entity.RptCorpArea;
import app.util.toolkit.Ipage;
import com.core.domain.screen.FormData;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;
public class RptCorpAreaAction extends BaseFormBean {
	
	private String query;
	private String view;
	
	private RptCorpArea rptCorpArea;
	
	private FormData formfin;
	
	private List<RptCorpArea> list;
	
	private RptCorpAreaBo rptCorpAreaBo;
	
	public RptCorpAreaAction(){
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
		rptCorpArea=new RptCorpArea();
		getFormValue(formfin);
		setObjValue(formfin, rptCorpArea);
		list=(List)rptCorpAreaBo.findByPage(ipage, rptCorpArea).getResult();
		return "list";
	}
	
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		getFormValue(formfin);
		rptCorpArea=new RptCorpArea();
	    setObjValue(formfin,rptCorpArea);
	    rptCorpAreaBo.insert(rptCorpArea);
	    
		//以下操作是为了跳转到详情查看页面
		getObjValue(formfin,rptCorpArea);
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
		rptCorpArea=new RptCorpArea();
	    setObjValue(formfin,rptCorpArea);
	}
	
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		getFormValue(formfin);
		rptCorpArea=new RptCorpArea();
	    setObjValue(formfin,rptCorpArea);
	    rptCorpAreaBo.update(rptCorpArea);
		//以下操作是为了跳转到详情查看页面
		getObjValue(formfin,rptCorpArea);
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
		rptCorpArea=new RptCorpArea();
	    setObjValue(formfin,rptCorpArea);
	}
	
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		rptCorpArea=new RptCorpArea();

		rptCorpAreaBo.del(rptCorpArea);
		this.setMessage("操作成功");
		return "return_list";
	}

	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		rptCorpArea=new RptCorpArea();
		
		rptCorpArea = rptCorpAreaBo.getById(rptCorpArea);
		getObjValue(formfin,rptCorpArea);
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

	public RptCorpArea getRptCorpArea() {
		return rptCorpArea;
	}

	public void setRptCorpArea(RptCorpArea rptCorpArea) {
		this.rptCorpArea = rptCorpArea;
	}

	public List<RptCorpArea> getList() {
		return list;
	}

	public void setList(List<RptCorpArea> list) {
		this.list = list;
	}

	public RptCorpAreaBo getRptCorpAreaBo() {
		return rptCorpAreaBo;
	}

	public void setRptCorpAreaBo(RptCorpAreaBo rptCorpAreaBo) {
		this.rptCorpAreaBo = rptCorpAreaBo;
	}
}
