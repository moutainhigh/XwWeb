package  app.creditapp.ln.action;
import java.util.ArrayList;
import java.util.List;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.aft.bo.AftReverseBo;
import app.creditapp.aft.entity.AftReverse;
import app.creditapp.ln.bo.LnApprIdeaBo;
import app.creditapp.ln.entity.LnApprIdea;
import app.creditapp.pact.entity.LnPact;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;
import com.dhcc.workflow.WFUtil;
import com.dhcc.workflow.api.TaskQuery;
import com.dhcc.workflow.api.task.Task;

/**
 * Title: LnApprIdeaAction.java
 **/
public class LnApprIdeaAction extends BaseFormBean {

	//页面传值
	private LnApprIdea lnApprIdea;
	private List<LnApprIdea> lnApprIdeaList;
	private List<LnApprIdea> lnList;

	//注入LnApprIdeaBo
	private LnApprIdeaBo lnApprIdeaBo;
	private AftReverseBo aftReverseBo;

	private String query;
	private String ideaId;		
	private String appId;		
	private String apprKind;		
	
	private List tabList;
	
	private FormData formlnappridea0001;
	private FormData formlnappridea0002;
	private FormData formlnappridea0003;
	private FormData formlnappridea0004;
	private FormService formService = new FormService();
	
	public LnApprIdeaAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formlnappridea0001 = formService.getFormData("lnappridea0001");
		lnApprIdea = new LnApprIdea();
		getFormValue(formlnappridea0001);
		setObjValue(formlnappridea0001, lnApprIdea);
		lnApprIdea.setApprOp(User.getLoginid(getHttpRequest()));
//		lnApprIdea.setLoginid(User.getLoginIdByAuth(this.getHttpRequest()));
		Ipage ipage = this.getIpage();
		lnApprIdeaList = (List) lnApprIdeaBo.findByPage(ipage, lnApprIdea).getResult();
		return "list";
	}
	public String findByPageForRead() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formlnappridea0001 = formService.getFormData("lnappridea0001");
		lnApprIdea = new LnApprIdea();
		getFormValue(formlnappridea0001);
		setObjValue(formlnappridea0001, lnApprIdea);
//		lnApprIdea.setApprOp(User.getLoginid(getHttpRequest()));
		Ipage ipage = this.getIpage();
		lnApprIdeaList = (List) lnApprIdeaBo.findByPageForRead(ipage, lnApprIdea).getResult();
		return "list";
	}
	
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByAppId() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
//		formlnappridea0003 = formService.getFormData("lnappridea0003");
		lnApprIdea = new LnApprIdea();
//		getFormValue(formlnappridea0003);
//		setObjValue(formlnappridea0003, lnApprIdea);
		lnApprIdea.setAppId(appId);
		Ipage ipage = this.getIpage();
		lnApprIdeaList = (List) lnApprIdeaBo.findByPage(ipage, lnApprIdea).getResult();
		return "list";
	}
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formlnappridea0002 = formService.getFormData("lnappridea0002");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formlnappridea0002 = formService.getFormData("lnappridea0002");
		getFormValue(formlnappridea0002);
		lnApprIdea = new LnApprIdea();
		setObjValue(formlnappridea0002, lnApprIdea);
		lnApprIdeaBo.insert(lnApprIdea);
		getObjValue(formlnappridea0002, lnApprIdea);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formlnappridea0002 = formService.getFormData("lnappridea0002");
		getFormValue(formlnappridea0002);
		lnApprIdea = new LnApprIdea();
		setObjValue(formlnappridea0002, lnApprIdea);
		lnApprIdeaBo.update(lnApprIdea);
		getObjValue(formlnappridea0002, lnApprIdea);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formlnappridea0001 = formService.getFormData("lnappridea0001");
		lnApprIdea = new LnApprIdea();
		lnApprIdea.setIdeaId(ideaId);
		lnApprIdeaBo.del(lnApprIdea);
		this.addActionMessage("删除成功");
		lnApprIdea = new LnApprIdea();
		Ipage ipage = this.getIpage();
		lnApprIdeaList = (List) lnApprIdeaBo.findByPage(ipage, lnApprIdea).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formlnappridea0002 = formService.getFormData("lnappridea0002");
		lnApprIdea = new LnApprIdea();
		lnApprIdea.setIdeaId(ideaId);
		lnApprIdea = lnApprIdeaBo.getById(lnApprIdea);
		getObjValue(formlnappridea0002, lnApprIdea);
		return "detail";
	}
	public String getByIdLn() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formlnappridea0004 = formService.getFormData("lnappridea0004");
		lnApprIdea = new LnApprIdea();
		lnApprIdea.setIdeaId(ideaId);
		lnApprIdea = lnApprIdeaBo.getById(lnApprIdea);
		getObjValue(formlnappridea0004, lnApprIdea);
		return "detail";
	}
	public String getTab() throws Exception{
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		tabList = new ArrayList();
		String[] tab = null;
		
		if(apprKind.equals("01")){
			tab = new String[2];
			tab[0] = "贷款审批";
			tab[1] = "LnApplyMidAction_getByIdToLn.action?appId=" + appId+ "&apprKind="+ apprKind+"&query="+ query;
			tabList.add(tab);
		}
		if(apprKind.equals("02")){
			AftReverse aftReverse = new AftReverse();
			aftReverse.setTraceNo(appId);
			aftReverse = aftReverseBo.getByIdForRead(aftReverse);
			String traceNo = aftReverse.getTraceNo();
			
			if(aftReverse.getTxCde().equals("LNC4")){
				tab = new String[2];
				tab[0] = "详情";
				tab[1] = "AcLnPmLogAction_findByPage.action?traceNo=" + traceNo;
				tabList.add(tab);
			}else if(aftReverse.getTxCde().equals("LNAD")){
				tab = new String[2];
				tab[0] = "详情";
				tab[1] = "AftAdvpayAction_getByIdForTrace.action?traceNo=" + traceNo+ "&query="+ query;
				tabList.add(tab);
			}else if(aftReverse.getTxCde().equals("CGPD")){
				tab = new String[2];
				tab[0] = "详情";
				tab[1] = "AftPaydayAction_getByIdForTrace.action?traceNo=" + traceNo+ "&query="+ query;
				tabList.add(tab);
			}else if(aftReverse.getTxCde().equals("LNUP")){
				tab = new String[2];
				tab[0] = "详情";
				tab[1] = "AftAcnoAction_getByIdForTrace.action?traceNo=" + traceNo+ "&query="+ query;
				tabList.add(tab);
			}else if(aftReverse.getTxCde().equals("LNWV")){
				tab = new String[2];
				tab[0] = "详情";
				tab[1] = "AftReliefAction_getByIdForTrace.action?traceNo=" + traceNo+ "&query="+ query;
				tabList.add(tab);
			}
		}
		if(apprKind.equals("03")){
			tab = new String[2];
			tab[0] = "客户信息";
			tab[1] = "CifBlackAction_getById.action?blkId=" + appId+ "&apprKind="+ apprKind+"&query="+ query;
			tabList.add(tab);
		}
		if(apprKind.equals("04")){
			tab = new String[2];
			tab[0] = "息费减免";
			tab[1] = "AftReliefAction_getByIdForRead.action?refId=" + appId+ "&apprKind="+ apprKind+"&query="+ query;
			tabList.add(tab);
		}
		
		
		
		return "tab";
	}
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formlnappridea0002 = formService.getFormData("lnappridea0002");
		 getFormValue(formlnappridea0002);
		 validateFormData(formlnappridea0002);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formlnappridea0002 = formService.getFormData("lnappridea0002");
		 getFormValue(formlnappridea0002);
		 validateFormData(formlnappridea0002);
  	}
	public LnApprIdea getLnApprIdea() {
		return lnApprIdea;
	}
	public void setLnApprIdea(LnApprIdea  lnApprIdea) {
		this.lnApprIdea = lnApprIdea;
	}
	public List<LnApprIdea> getLnApprIdeaList() {
		return lnApprIdeaList;
	}
	public void setLnApprIdeaList(List<LnApprIdea> lnApprIdeaList) {
		this.lnApprIdeaList = lnApprIdeaList;
	}
	public LnApprIdeaBo getLnApprIdeaBo() {
		return lnApprIdeaBo;
	}
	public void setLnApprIdeaBo(LnApprIdeaBo lnApprIdeaBo) {
		this.lnApprIdeaBo = lnApprIdeaBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormlnappridea0002() {
		return formlnappridea0002;
	}
	public void setFormlnappridea0002(FormData formlnappridea0002) {
		this.formlnappridea0002 = formlnappridea0002;
	}
	public FormData getFormlnappridea0001() {
		return formlnappridea0001;
	}
	public void setFormlnappridea0001(FormData formlnappridea0001) {
		this.formlnappridea0001 = formlnappridea0001;
	}
	public void setIdeaId(String ideaId){
		this.ideaId = ideaId;
	}		
	public String getIdeaId(){
		return ideaId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public List getTabList() {
		return tabList;
	}
	public void setTabList(List tabList) {
		this.tabList = tabList;
	}
	public String getApprKind() {
		return apprKind;
	}
	public void setApprKind(String apprKind) {
		this.apprKind = apprKind;
	}
	public List<LnApprIdea> getLnList() {
		return lnList;
	}
	public void setLnList(List<LnApprIdea> lnList) {
		this.lnList = lnList;
	}
	public FormData getFormlnappridea0003() {
		return formlnappridea0003;
	}
	public void setFormlnappridea0003(FormData formlnappridea0003) {
		this.formlnappridea0003 = formlnappridea0003;
	}
	public FormData getFormlnappridea0004() {
		return formlnappridea0004;
	}
	public void setFormlnappridea0004(FormData formlnappridea0004) {
		this.formlnappridea0004 = formlnappridea0004;
	}
	public AftReverseBo getAftReverseBo() {
		return aftReverseBo;
	}
	public void setAftReverseBo(AftReverseBo aftReverseBo) {
		this.aftReverseBo = aftReverseBo;
	}
	
}