package app.creditapp.corp.action;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import app.creditapp.corp.bo.RptCorpDailyBo;
import app.creditapp.corp.entity.RptCorpDaily;
import app.util.toolkit.Ipage;
import com.core.domain.screen.FormData;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;
public class RptCorpDailyAction extends BaseFormBean {
	
	private String query;
	private String view;
	
	private RptCorpDaily rptCorpDaily;
	
	private FormData formfin;
	
	private List<RptCorpDaily> list;
	
	private RptCorpDailyBo rptCorpDailyBo;
	
	public RptCorpDailyAction(){
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
		rptCorpDaily=new RptCorpDaily();
		getFormValue(formfin);
		setObjValue(formfin, rptCorpDaily);
		list=(List)rptCorpDailyBo.findByPage(ipage, rptCorpDaily).getResult();
		return "list";
	}
	
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		getFormValue(formfin);
		rptCorpDaily=new RptCorpDaily();
	    setObjValue(formfin,rptCorpDaily);
	    rptCorpDailyBo.insert(rptCorpDaily);
	    
		//以下操作是为了跳转到详情查看页面
		getObjValue(formfin,rptCorpDaily);
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
		rptCorpDaily=new RptCorpDaily();
	    setObjValue(formfin,rptCorpDaily);
	}
	
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		getFormValue(formfin);
		rptCorpDaily=new RptCorpDaily();
	    setObjValue(formfin,rptCorpDaily);
	    rptCorpDailyBo.update(rptCorpDaily);
		//以下操作是为了跳转到详情查看页面
		getObjValue(formfin,rptCorpDaily);
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
		rptCorpDaily=new RptCorpDaily();
	    setObjValue(formfin,rptCorpDaily);
	}
	
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		rptCorpDaily=new RptCorpDaily();

		rptCorpDailyBo.del(rptCorpDaily);
		this.setMessage("操作成功");
		return "return_list";
	}

	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		rptCorpDaily=new RptCorpDaily();
		
		rptCorpDaily = rptCorpDailyBo.getById(rptCorpDaily);
		getObjValue(formfin,rptCorpDaily);
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

	public RptCorpDaily getRptCorpDaily() {
		return rptCorpDaily;
	}

	public void setRptCorpDaily(RptCorpDaily rptCorpDaily) {
		this.rptCorpDaily = rptCorpDaily;
	}

	public List<RptCorpDaily> getList() {
		return list;
	}

	public void setList(List<RptCorpDaily> list) {
		this.list = list;
	}

	public RptCorpDailyBo getRptCorpDailyBo() {
		return rptCorpDailyBo;
	}

	public void setRptCorpDailyBo(RptCorpDailyBo rptCorpDailyBo) {
		this.rptCorpDailyBo = rptCorpDailyBo;
	}
}
