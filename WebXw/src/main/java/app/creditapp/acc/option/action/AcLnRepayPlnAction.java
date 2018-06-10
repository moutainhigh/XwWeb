package  app.creditapp.acc.option.action;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import app.creditapp.acc.loan.entity.AcLnMst;
import app.creditapp.acc.option.bo.AcLnRepayPlnBo;
import app.creditapp.acc.option.entity.AcLnRepayPln;
import app.util.DateUtil;
import app.util.toolkit.Ipage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: AcLnRepayPlnAction.java
 * Description:
 **/
public class AcLnRepayPlnAction extends BaseFormBean {

	//页面传值
	private AcLnRepayPln acLnRepayPln;
	private List<AcLnRepayPln> acLnRepayPlnList;

	//注入AcLnRepayPlnBo
	private AcLnRepayPlnBo acLnRepayPlnBo;

	private String query;
	private Integer perdNo;		
	private String loanNo;
	private String pactNo;
	private String brNo;

	private String beg_date;
	private String term_mon;
	private String term_day;
	private String date_temp;
	private String pactId;
	private FormData formlnpln001;
	private FormData formlnpln002;
	private FormData formcalPln001;
	private FormService formService = new FormService();
	
	public AcLnRepayPlnAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formlnpln001 = formService.getFormData("lnpln001");
		getFormValue(formlnpln001);
		acLnRepayPln = new AcLnRepayPln();
		setObjValue(formlnpln001, acLnRepayPln);
		acLnRepayPln.setPactNo(pactNo);
		Ipage ipage = this.getIpage();
		acLnRepayPlnList = (List) acLnRepayPlnBo.findByPage(ipage, acLnRepayPln).getResult();
		return "list";
	}
	public String findByPageRead() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formlnpln001 = formService.getFormData("lnpln001");
		acLnRepayPln = new AcLnRepayPln();
		getFormValue(formlnpln001);
		setObjValue(formlnpln001, acLnRepayPln);
		acLnRepayPln.setPactNo(pactNo);
		Ipage ipage = this.getIpage();
		acLnRepayPlnList = (List) acLnRepayPlnBo.findByPage(ipage, acLnRepayPln).getResult();
		return "listRead";
	}
	
	

	/**
	 * 还款计划试算
	 * @return
	 * @throws Exception
	 */
	public String calPlnsByParms() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formcalPln001 = formService.getFormData("calPln001");
		AcLnMst acLnMst = new AcLnMst();
		getFormValue(formcalPln001);
		setObjValue(formcalPln001, acLnMst);
		if(acLnMst.getLoanAmt()!=0.00){
			acLnRepayPlnList = (List) acLnRepayPlnBo.calPlnsByParms(acLnMst);
		}
		return "listForCalPlans";
	}
	public String calPlnsByPro() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		AcLnMst acLnMst = new AcLnMst();
		acLnMst.setPrdtNo(ServletActionContext.getRequest().getParameter("prdtNo"));
		acLnMst.setDevaInd(ServletActionContext.getRequest().getParameter("prdtId"));//产品id
		acLnMst.setLoanAmt(Double.parseDouble(ServletActionContext.getRequest().getParameter("loanAmt")));
		acLnMst.setTermDay(Integer.parseInt(ServletActionContext.getRequest().getParameter("termDay")));
		acLnMst.setTermMon(Integer.parseInt(ServletActionContext.getRequest().getParameter("termMon")));
		if(acLnMst.getTermDay() > 0){
			acLnMst.setLoanTerm(acLnMst.getTermMon()+1);
		}else{
			acLnMst.setLoanTerm(acLnMst.getTermMon());
		}
		acLnMst.setLnRate(Double.parseDouble(ServletActionContext.getRequest().getParameter("lnRate"))/1000);
		acLnMst.setRepayDay(ServletActionContext.getRequest().getParameter("repayDay"));
		acLnMst.setOpnDt(DateUtil.getYYYYMMDD(ServletActionContext.getRequest().getParameter("icDt")));
		acLnMst.setMtrDt(DateUtil.getYYYYMMDD(ServletActionContext.getRequest().getParameter("mtrDt")));	
		if(acLnMst.getLoanAmt()!=0.00){
			acLnRepayPlnList = (List) acLnRepayPlnBo.calPlnsByParms(acLnMst);
			PrintWriter out = this.getHttpResponse().getWriter();
			JSONArray json_list = (JSONArray)JSON.toJSON(acLnRepayPlnList);
			System.out.println(">>>>>>>>>>>>data:" + json_list) ;
			out.print(json_list);
		}
		return null;
	}
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formlnpln002 = formService.getFormData("lnpln002");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formlnpln002 = formService.getFormData("lnpln002");
		getFormValue(formlnpln002);
		acLnRepayPln = new AcLnRepayPln();
		setObjValue(formlnpln002, acLnRepayPln);
		acLnRepayPlnBo.insert(acLnRepayPln);
		getObjValue(formlnpln002, acLnRepayPln);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formlnpln002 = formService.getFormData("lnpln002");
		getFormValue(formlnpln002);
		acLnRepayPln = new AcLnRepayPln();
		setObjValue(formlnpln002, acLnRepayPln);
		acLnRepayPlnBo.update(acLnRepayPln);
		getObjValue(formlnpln002, acLnRepayPln);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formlnpln001 = formService.getFormData("lnpln001");
		acLnRepayPln = new AcLnRepayPln();
		acLnRepayPln.setPerdNo(perdNo);
		acLnRepayPln.setLoanNo(loanNo);
		acLnRepayPlnBo.del(acLnRepayPln);
		this.addActionMessage("删除成功");
		acLnRepayPln = new AcLnRepayPln();
		Ipage ipage = this.getIpage();
		acLnRepayPlnList = (List) acLnRepayPlnBo.findByPage(ipage, acLnRepayPln).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formlnpln002 = formService.getFormData("lnpln002");
		acLnRepayPln = new AcLnRepayPln();
		acLnRepayPln.setPerdNo(perdNo);
		acLnRepayPln.setLoanNo(loanNo);
		acLnRepayPln = acLnRepayPlnBo.getById(acLnRepayPln);
		getObjValue(formlnpln002, acLnRepayPln);
		return "detail";
	}
	
	/**
	 * 查询操作根据pactNo进行查询
	 * @return
	 * @throws Exception
	 */
	public String getByIdforpact() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formlnpln002 = formService.getFormData("lnpln002");
		acLnRepayPln = new AcLnRepayPln();
		acLnRepayPln.setPactNo(pactNo);
		acLnRepayPln = acLnRepayPlnBo.getByIdforpact(acLnRepayPln);
		getObjValue(formlnpln002, acLnRepayPln);
		return "detail";
	}
	
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formlnpln002 = formService.getFormData("lnpln002");
		 getFormValue(formlnpln002);
		 validateFormData(formlnpln002);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formlnpln002 = formService.getFormData("lnpln002");
		 getFormValue(formlnpln002);
		 validateFormData(formlnpln002);
  	}
	
	/**
	    * 自动根据期限月和起始日期计算结束日期
	    * @return
	    * @throws Exception
	    */
		public String checkDate() throws Exception{
			ActionContext.initialize(ServletActionContext.getRequest(),
					ServletActionContext.getResponse());
			HttpServletResponse response = getHttpResponse();
			response.setContentType("text/html;charset=GBK");
			PrintWriter pw = response.getWriter();
			String check_result = "";
			try {
					if(null != beg_date&& null!=term_mon&& null!=term_day){
						//返回日期经过若干月后的日期(传入几位日期便返回多少位的格式)
						date_temp =(String)DateUtil.getDateStr(beg_date, Integer.parseInt(term_mon));
						//功能描述：计算在给定的日期加上或减去 n 天后的日期
						check_result=date_temp.replaceAll("-", "");
						date_temp = (String)DateUtil.addByDay(check_result, Integer.parseInt(term_day));
						date_temp=date_temp.substring(0,4)+"-"+date_temp.substring(4,6)+"-"+date_temp.substring(6,8);
					}else{
						date_temp="";
					}				
			} catch (Exception e) {
				e.printStackTrace();
			}
			pw.print(date_temp);
			return null;
		}
	public AcLnRepayPln getAcLnRepayPln() {
		return acLnRepayPln;
	}
	public void setAcLnRepayPln(AcLnRepayPln  acLnRepayPln) {
		this.acLnRepayPln = acLnRepayPln;
	}
	public List<AcLnRepayPln> getAcLnRepayPlnList() {
		return acLnRepayPlnList;
	}
	public void setAcLnRepayPlnList(List<AcLnRepayPln> acLnRepayPlnList) {
		this.acLnRepayPlnList = acLnRepayPlnList;
	}
	public AcLnRepayPlnBo getAcLnRepayPlnBo() {
		return acLnRepayPlnBo;
	}
	public void setAcLnRepayPlnBo(AcLnRepayPlnBo acLnRepayPlnBo) {
		this.acLnRepayPlnBo = acLnRepayPlnBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormlnpln002() {
		return formlnpln002;
	}
	public void setFormlnpln002(FormData formlnpln002) {
		this.formlnpln002 = formlnpln002;
	}
	public FormData getFormlnpln001() {
		return formlnpln001;
	}
	public void setFormlnpln001(FormData formlnpln001) {
		this.formlnpln001 = formlnpln001;
	}
	public void setPerdNo(Integer perdNo){
		this.perdNo = perdNo;
	}		
	public void setLoanNo(String loanNo){
		this.loanNo = loanNo;
	}		
	public Integer getPerdNo(){
		return perdNo;
	}
	public String getLoanNo(){
		return loanNo;
	}
	public FormData getFormcalPln001() {
		return formcalPln001;
	}
	public void setFormcalPln001(FormData formcalPln001) {
		this.formcalPln001 = formcalPln001;
	}
	public String getPactNo() {
		return pactNo;
	}
	public void setPactNo(String pactNo) {
		this.pactNo = pactNo;
	}
	public FormService getFormService() {
		return formService;
	}
	public void setFormService(FormService formService) {
		this.formService = formService;
	}
	public String getBrNo() {
		return brNo;
	}
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}
	public String getBeg_date() {
		return beg_date;
	}
	public void setBeg_date(String begDate) {
		beg_date = begDate;
	}
	public String getTerm_mon() {
		return term_mon;
	}
	public void setTerm_mon(String termMon) {
		term_mon = termMon;
	}
	public String getTerm_day() {
		return term_day;
	}
	public void setTerm_day(String termDay) {
		term_day = termDay;
	}
	public String getDate_temp() {
		return date_temp;
	}
	public String getPactId() {
		return pactId;
	}
	public void setPactId(String pactId) {
		this.pactId = pactId;
	}
	public void setDate_temp(String dateTemp) {
		date_temp = dateTemp;
	}
	
}