package  app.creditapp.aft.action;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import accounting.plat.util.NumberUtil;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.aft.bo.AftAssetRelBo;
import app.creditapp.aft.entity.AftAssetRel;
import app.creditapp.pact.bo.LnPactBo;
import app.creditapp.pact.entity.LnPact;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: AftAssetRelAction.java
 * Description:
 **/
public class AftAssetRelAction extends BaseFormBean {

	//页面传值
	private AftAssetRel aftAssetRel;
	private List<AftAssetRel> aftAssetRelList;

	//注入AftAssetRelBo
	private AftAssetRelBo aftAssetRelBo;
	private LnPactBo lnPactBo;
	
	private String query;
	private String recId;	
	private String pactId;
	private String assId;
	private String brNo;
	private String pactNo;
	
	private FormData formaft0012;
	private FormData formaft0013;
	private FormService formService = new FormService();
	
	public AftAssetRelAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formaft0012 = formService.getFormData("aft0012");
		aftAssetRel = new AftAssetRel();
		getFormValue(formaft0012);
		setObjValue(formaft0012, aftAssetRel);
		Ipage ipage = this.getIpage();
		aftAssetRelList = (List) aftAssetRelBo.findByPage(ipage, aftAssetRel).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formaft0013 = formService.getFormData("aft0013");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formaft0013 = formService.getFormData("aft0013");
		getFormValue(formaft0013);
		aftAssetRel = new AftAssetRel();
		setObjValue(formaft0013, aftAssetRel);
		aftAssetRel.setTxDate(User.getSys_date(this.getHttpRequest()));
		aftAssetRelBo.insert(aftAssetRel);
		getObjValue(formaft0013, aftAssetRel);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formaft0013 = formService.getFormData("aft0013");
		getFormValue(formaft0013);
		aftAssetRel = new AftAssetRel();
		setObjValue(formaft0013, aftAssetRel);
		aftAssetRel.setUpDate(User.getSys_date(this.getHttpRequest()));
		aftAssetRelBo.update(aftAssetRel);
		getObjValue(formaft0013, aftAssetRel);
		return "detail";
	}
	public String updateForAmt() throws Exception {
		this.getHttpResponse().setContentType("text/html;charset=utf-8");
		HttpServletRequest requset=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		String result="";
		double feeAmt=0.00;
		String recId=requset.getParameter("recId");
		String values=requset.getParameter("value");
		if(values!=""||values == null ){
			double value =   Double.parseDouble(values); 
			Map<String,String> map = new ConcurrentHashMap<String,String>();
			map.put("assAmt", values);
			map.put("recId", recId);
			aftAssetRel = new AftAssetRel();
			aftAssetRel.setRecId(recId);
			String pactNo=requset.getParameter("pactNo");
			feeAmt = aftAssetRelBo.getTotalAmt(pactNo,recId);   //已经存在的质押金额
			double two =feeAmt+value;                    //已经存在的质押金额+新增的质押金额
			LnPact lnPact = new LnPact();
			lnPact.setPactNo(pactNo);
			lnPact = lnPactBo.getById(lnPact);            //合同金额
			if(NumberUtil.isAmtGreatEqual(lnPact.getPactAmt(),two)){    //两个金额比较
				try {
					aftAssetRelBo.updateValue(map);
					result="1";
				} catch (Exception e) {
					e.printStackTrace();
					result="0";
				}
				
			}
		}
		
		response.getWriter().write(result);
		response.getWriter().close();
		return null;
		
	}	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formaft0012 = formService.getFormData("aft0012");
		aftAssetRel = new AftAssetRel();
		aftAssetRel.setRecId(recId);
		aftAssetRelBo.del(aftAssetRel);
		aftAssetRel = new AftAssetRel();
		aftAssetRel.setAssId(assId);
		getFormValue(formaft0012);
		setObjValue(formaft0012, aftAssetRel);
		Ipage ipage = this.getIpage();
		aftAssetRelList = (List) aftAssetRelBo.findByPage(ipage, aftAssetRel).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formaft0013 = formService.getFormData("aft0013");
		aftAssetRel = new AftAssetRel();
		aftAssetRel.setRecId(recId);
		aftAssetRel = aftAssetRelBo.getById(aftAssetRel);
		getObjValue(formaft0013, aftAssetRel);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formaft0013 = formService.getFormData("aft0013");
		 getFormValue(formaft0013);
		 validateFormData(formaft0013);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formaft0013 = formService.getFormData("aft0013");
		 getFormValue(formaft0013);
		 validateFormData(formaft0013);
  	}
	public AftAssetRel getAftAssetRel() {
		return aftAssetRel;
	}
	public void setAftAssetRel(AftAssetRel  aftAssetRel) {
		this.aftAssetRel = aftAssetRel;
	}
	public List<AftAssetRel> getAftAssetRelList() {
		return aftAssetRelList;
	}
	public void setAftAssetRelList(List<AftAssetRel> aftAssetRelList) {
		this.aftAssetRelList = aftAssetRelList;
	}
	public AftAssetRelBo getAftAssetRelBo() {
		return aftAssetRelBo;
	}
	public void setAftAssetRelBo(AftAssetRelBo aftAssetRelBo) {
		this.aftAssetRelBo = aftAssetRelBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormaft0013() {
		return formaft0013;
	}
	public void setFormaft0013(FormData formaft0013) {
		this.formaft0013 = formaft0013;
	}
	public FormData getFormaft0012() {
		return formaft0012;
	}
	public void setFormaft0012(FormData formaft0012) {
		this.formaft0012 = formaft0012;
	}
	public void setRecId(String recId){
		this.recId = recId;
	}		
	public String getRecId(){
		return recId;
	}
	public String getAssId() {
		return assId;
	}
	public void setAssId(String assId) {
		this.assId = assId;
	}
	public String getBrNo() {
		return brNo;
	}
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}
	public String getPactNo() {
		return pactNo;
	}
	public void setPactNo(String pactNo) {
		this.pactNo = pactNo;
	}
	public String getPactId() {
		return pactId;
	}
	public void setPactId(String pactId) {
		this.pactId = pactId;
	}
	public LnPactBo getLnPactBo() {
		return lnPactBo;
	}
	public void setLnPactBo(LnPactBo lnPactBo) {
		this.lnPactBo = lnPactBo;
	}
}