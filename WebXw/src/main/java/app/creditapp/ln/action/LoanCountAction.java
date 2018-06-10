package app.creditapp.ln.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import app.creditapp.ln.bo.LoanCountBo;
import app.creditapp.ln.entity.LoanCount;
import app.util.User;
import app.util.toolkit.Ipage;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

public class LoanCountAction extends BaseFormBean{
	
	private LoanCount loanCount;
	private List<LoanCount> loanCountList;
	private LoanCountBo loanCountBo;
	
	private String query;
	private FormData formdebitCount0001;  //debitCount0001是扣款查询表单
	private FormService formService = new FormService();
	

	public LoanCountAction() {
		query = "";
	}
	
	
	
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formdebitCount0001 = formService.getFormData("debitCount0001");
		loanCount = new LoanCount();
		getFormValue(formdebitCount0001);
		setObjValue(formdebitCount0001, loanCount);
		loanCount.setLoginid(User.getLoginIdByAuth(this.getHttpRequest())); // 从session中获取权限
		Ipage ipage = this.getIpage();
		loanCountList = (List) loanCountBo.findByPage(ipage, loanCount).getResult();
		return "list";
	}



	public LoanCount getLoanCount() {
		return loanCount;
	}



	public void setLoanCount(LoanCount loanCount) {
		this.loanCount = loanCount;
	}



	public List<LoanCount> getLoanCountList() {
		return loanCountList;
	}



	public void setLoanCountList(List<LoanCount> loanCountList) {
		this.loanCountList = loanCountList;
	}



	public LoanCountBo getLoanCountBo() {
		return loanCountBo;
	}



	public void setLoanCountBo(LoanCountBo loanCountBo) {
		this.loanCountBo = loanCountBo;
	}



	public String getQuery() {
		return query;
	}



	public void setQuery(String query) {
		this.query = query;
	}



	public FormData getFormdebitCount0001() {
		return formdebitCount0001;
	}



	public void setFormdebitCount0001(FormData formdebitCount0001) {
		this.formdebitCount0001 = formdebitCount0001;
	}

}
