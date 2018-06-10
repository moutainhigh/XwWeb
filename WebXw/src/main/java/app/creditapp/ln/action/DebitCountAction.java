package app.creditapp.ln.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import app.creditapp.ln.bo.DebitCountBo;
import app.creditapp.ln.entity.DebitCount;
import app.util.User;
import app.util.toolkit.Ipage;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * @title 扣款统计action
 * @author supenghui
 */
public class DebitCountAction extends BaseFormBean {
	
	
	private DebitCount debitCount;
	private List<DebitCount> debitCountList;
	private DebitCountBo debitCountBo;
	
	private String query;
	private FormData formdebitCount0001;  //debitCount0001是扣款查询表单
	private FormService formService = new FormService();
	
	public DebitCountAction() {
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
		debitCount = new DebitCount();
		getFormValue(formdebitCount0001);
		setObjValue(formdebitCount0001, debitCount);
		debitCount.setLoginid(User.getLoginIdByAuth(this.getHttpRequest())); // 从session中获取权限
		Ipage ipage = this.getIpage();
		debitCountList = (List) debitCountBo.findByPage(ipage, debitCount).getResult();
		return "list";
	}



	public DebitCount getDebitCount() {
		return debitCount;
	}



	public void setDebitCount(DebitCount debitCount) {
		this.debitCount = debitCount;
	}



	public List<DebitCount> getDebitCountList() {
		return debitCountList;
	}



	public void setDebitCountList(List<DebitCount> debitCountList) {
		this.debitCountList = debitCountList;
	}



	public String getQuery() {
		return query;
	}



	public void setQuery(String query) {
		this.query = query;
	}

	public DebitCountBo getDebitCountBo() {
		return debitCountBo;
	}



	public void setDebitCountBo(DebitCountBo debitCountBo) {
		this.debitCountBo = debitCountBo;
	}



	public FormData getFormdebitCount0001() {
		return formdebitCount0001;
	}



	public void setFormdebitCount0001(FormData formdebitCount0001) {
		this.formdebitCount0001 = formdebitCount0001;
	}
	

}
