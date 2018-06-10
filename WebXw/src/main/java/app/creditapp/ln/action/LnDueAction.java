package  app.creditapp.ln.action;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import redis.clients.jedis.Jedis;
import app.creditapp.acc.loan.bo.AcLnMstBo;
import app.creditapp.acc.loan.entity.AcLnMst;
import app.creditapp.aft.bo.AftPoolRelBo;
import app.creditapp.aft.entity.AftPoolRel;
import app.creditapp.ln.bo.LnDueBo;
import app.creditapp.ln.bo.LnStageBo;
import app.creditapp.ln.entity.LnDue;
import app.creditapp.ln.entity.LnStage;
import app.creditapp.ln.worker.WorkEforDue;
import app.creditapp.redis.util.RedisUtil;
import app.util.User;
import app.util.toolkit.Ipage;

import com.alibaba.fastjson.JSON;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: LnDueAction.java
 * Description:
 **/
public class LnDueAction extends BaseFormBean {

	//页面传值
	private LnDue lnDue;
	private List<LnDue> lnDueList;
	private List<LnDue> lnDueListFail;
	//注入LnDueBo
	private LnDueBo lnDueBo;
	private AftPoolRelBo aftPoolRelBo;
	private LnStage lnStage;
	private LnStageBo lnStageBo;
	private AcLnMst acLnMst;
	private AcLnMstBo acLnMstBo;
	
	private String query;
	private String pactNo;
	private String brName;
	private String dueNo;		
	private String poolId;
	private String pactId;
	private String brNo;
	private String cifNo;
	private Double dueAmt;
	private Double bal;
	private Double intst;
	private String begDate;
	private String endDate;
	private String fiveSts;
	private String poolDate;
	private String poolSts;
	private FormData formlndue0001;
	private FormData formlndue0003;
	private FormData formlndue0004;
	private FormData formlndue0005;
	private FormData formlndue0002;
	private FormData formaft0123;
	private FormService formService = new FormService();
	
	private List tabList;
	
	//
	private String formSts;
	public LnDueAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formlndue0001 = formService.getFormData("lndue0001");
		this.changeFormProperty(formlndue0001, "dueSts", "initValue","02");
		lnDue = new LnDue();
		getFormValue(formlndue0001);	
		setObjValue(formlndue0001, lnDue);
		lnDue.setLoginid(User.getLoginIdByAuth(this.getHttpRequest())); // 从session中获取权限
		Ipage ipage = this.getIpage();
		lnDueList = (List) lnDueBo.findByPage(ipage, lnDue).getResult();
		return "list";
	}
	/**
	 * 分账失败查询
	 * @return
	 * @throws Exception
	 */
	public String findByPagePartFail() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formlndue0005 = formService.getFormData("lndue0005");
		lnDue = new LnDue();
		getFormValue(formlndue0005);	
		setObjValue(formlndue0005, lnDue);
		lnDue.setLoginid(User.getLoginIdByAuth(this.getHttpRequest())); // 从session中获取权限
		Ipage ipage = this.getIpage();
//		lnDue.setPoolId(poolId);
//		lnDueList = (List) lnDueBo.findByPageForPop(ipage, lnDue).getResult();
		lnDue.setApprSts("02");
		lnDueListFail = (List) lnDueBo.findAllFail(ipage, lnDue).getResult();
		
		return "list";
	}
	public String doFailWork(){
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		String[] pactNos = pactNo.split(",");
		for(int i=1;i<pactNos.length;i++){
			// pactNo=Q8lJTb&brNo=1000000001
			if(!"".equals(pactNos[i]) && pactNos[i] != null){
				pactNo = pactNos[i].split("&")[0].split("=")[1];
				brNo = pactNos[i].split("&")[1].split("=")[1];
				lnDue = new LnDue();
				lnDue.setPactNo(pactNo);
				lnDue.setBrNo(brNo);
				lnDue = lnDueBo.getByPactNoAndBrNo(lnDue);
				Jedis jedis = RedisUtil.getJedis();
		  		jedis.lpush(RedisUtil.QUEUE6, JSON.toJSONString(lnDue));
		  		jedis.close();
			}
		}
		this.addActionMessage("操作成功，请稍后查询放款状态");
		return "list";
	}
	
	public String doAFailWork() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
		ServletActionContext.getResponse());

		lnDue = new LnDue();
		lnDue.setDueNo(dueNo);
		lnDue = lnDueBo.getById(lnDue);
		
		lnStage = new LnStage();
		lnStage.setAppId(lnDue.getAppId());
		lnStage=lnStageBo.getById(lnStage);
		if (lnStage==null||"".equals(lnStage)){
			this.addActionMessage("该笔业务没有阶段信息，无法重新放款！");
		}else{
			String partSts = lnStage.getPartSts();//分账 01未分账02成功03失败
			String mstSts = lnStage.getMstSts();//主文件01未生成02已生成
			String sendSts = lnStage.getSendSts();//发送01未发送02已发送
			String paySts = lnStage.getPaySts();//放款01未放款02放款成功03放款失败
			//未分账，无主文件
			if(("01".equals(partSts)||"03".equals(partSts))&&"01".equals(mstSts)){
				Jedis jedis = RedisUtil.getJedis();
				jedis.lpush(RedisUtil.QUEUE6, JSON.toJSONString(lnDue));
	  			jedis.close();
	  			this.addActionMessage("操作成功，请稍后查询放款状态");
			}else if("02".equals(mstSts)&&("01".equals(sendSts)||"03".equals(paySts))){
				acLnMst = new AcLnMst();
				acLnMst.setLoanNo(lnDue.getDueNo());
				acLnMstBo.del(acLnMst);
				Jedis jedis = RedisUtil.getJedis();
				jedis.lpush(RedisUtil.QUEUE6, JSON.toJSONString(lnDue));
	  			jedis.close();
	  			this.addActionMessage("操作成功，请稍后查询放款状态");
			}else{
				this.addActionMessage("该笔业务不符合要求，无法重新放款！");
			}
		}
		return findByPage();
	}
	public String findByPageForPop() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formlndue0004 = formService.getFormData("lndue0004");
		lnDue = new LnDue();
		getFormValue(formlndue0004);	
		setObjValue(formlndue0004, lnDue);
		lnDue.setLoginid(User.getLoginIdByAuth(this.getHttpRequest())); // 从session中获取权限
		Ipage ipage = this.getIpage();
		lnDue.setPoolId(poolId);
		lnDueList = (List) lnDueBo.findByPageForPop(ipage, lnDue).getResult();
		return "list";
	}
	/**
	 * 台账报表
	 * @return
	 * @throws Exception
	 */
	public String findByPageForRead() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formlndue0003 = formService.getFormData("lndue0003");
		lnDue = new LnDue();
		getFormValue(formlndue0003);	
		setObjValue(formlndue0003, lnDue);
		lnDue.setLoginid(User.getLoginIdByAuth(this.getHttpRequest())); // 从session中获取权限
		Ipage ipage = this.getIpage();
		lnDueList = (List) lnDueBo.findByPageForRead(ipage, lnDue).getResult();
		return "list";
	}
	/**
	 * 抽查回访列表
	 * @return
	 * @throws Exception
	 */
	public String findByPageForCheck() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formaft0123 = formService.getFormData("aft0123");
		Ipage ipage = this.getIpage();
		lnDue = new LnDue();
		getFormValue(formaft0123);
		setObjValue(formaft0123, lnDue);
		lnDue.setLoginid(User.getLoginIdByAuth(this.getHttpRequest())); // 从session中获取权限
		lnDueList = (List) lnDueBo.getCheckList(ipage, lnDue).getResult();
		//JSON.toJSONString(lnPactList);
		return "list";
	}
	public String doReplace(){
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		String[] pactNos = pactNo.split("@");
		for(int i=0;i<pactNos.length;i++){
			if(!"".equals(pactNos[i]) && pactNos[i] != null){
				pactNo = pactNos[i].split("=")[1];
				lnDue = new LnDue();
				lnDue.setPactNo(pactNo);
				lnDue = lnDueBo.getByIdForPactNo(lnDue);
				
				AftPoolRel aftPoolRel = new AftPoolRel();
				aftPoolRel.setPoolId(poolId);
				aftPoolRel.setPactId(lnDue.getPactId());
				aftPoolRel.setPactNo(lnDue.getPactNo());
				aftPoolRel.setBrNo(lnDue.getBrNo());
				aftPoolRel.setCifNo(lnDue.getCifNo());
				aftPoolRel.setCifName(lnDue.getCifName());
				aftPoolRel.setDueAmt(lnDue.getDueAmt());
				aftPoolRel.setBal(lnDue.getBal());
				aftPoolRel.setBegDate(lnDue.getBegDate());
				aftPoolRel.setEndDate(lnDue.getEndDate());
				aftPoolRel.setFiveSts(lnDue.getFiveSts());
				aftPoolRelBo.insert(aftPoolRel);
			}	
			
		}
		this.addActionMessage("保存成功");
		return "list";
	}
	
	public String doReplaceAjax() throws IOException{
		this.getHttpResponse().setContentType("text/html;charset=UTF-8");
		String[] pactNos = pactNo.split("@");
		for(int i=0;i<pactNos.length;i++){
			if(!"".equals(pactNos[i]) && pactNos[i] != null){
				lnDue = new LnDue();
				lnDue.setPactNo(pactNos[i]);
				lnDue = lnDueBo.getByIdForPactNo(lnDue);
				
				AftPoolRel aftPoolRel = new AftPoolRel();
				aftPoolRel.setPoolId(poolId);
				aftPoolRel.setPactId(lnDue.getPactId());
				aftPoolRel.setPactNo(lnDue.getPactNo());
				aftPoolRel.setBrNo(lnDue.getBrNo());
				aftPoolRel.setCifNo(lnDue.getCifNo());
				aftPoolRel.setCifName(lnDue.getCifName());
				aftPoolRel.setDueAmt(lnDue.getDueAmt());
				aftPoolRel.setBal(lnDue.getBal());
				aftPoolRel.setBegDate(lnDue.getBegDate());
				aftPoolRel.setEndDate(lnDue.getEndDate());
				aftPoolRel.setFiveSts(lnDue.getFiveSts());
				aftPoolRelBo.insert(aftPoolRel);
			}
			
		}
		PrintWriter pw = this.getHttpResponse().getWriter();
		pw.write("success");
		return null;
	}
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formlndue0002 = formService.getFormData("lndue0002");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formlndue0002 = formService.getFormData("lndue0002");
		getFormValue(formlndue0002);
		lnDue = new LnDue();
		setObjValue(formlndue0002, lnDue);
		lnDue.setTxDate(User.getSys_date(ServletActionContext.getRequest()));
		lnDue.setOpNo(User.getLoginid(ServletActionContext.getRequest()));
		lnDueBo.insert(lnDue);
		getObjValue(formlndue0002, lnDue);
		this.addActionMessage("操作成功");
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formlndue0002 = formService.getFormData("lndue0002");
		getFormValue(formlndue0002);
		lnDue = new LnDue();
		setObjValue(formlndue0002, lnDue);
		//lnDue.setTxDate(User.getSys_date(ServletActionContext.getRequest()));
		//lnDue.setOpNo(User.getLoginid(ServletActionContext.getRequest()));
		lnDueBo.update(lnDue);
		getObjValue(formlndue0002, lnDue);
		this.addActionMessage("操作成功");
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formlndue0001 = formService.getFormData("lndue0001");
		lnDue = new LnDue();
		lnDue.setDueNo(dueNo);
		lnDueBo.del(lnDue);
		this.addActionMessage("删除成功");
		lnDue = new LnDue();
		lnDue.setBrNo(User.getBrno(this.getHttpRequest()));
		Ipage ipage = this.getIpage();
		lnDueList = (List) lnDueBo.findByPage(ipage, lnDue).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formlndue0002 = formService.getFormData("lndue0002");
		lnDue = new LnDue();
		lnDue.setDueNo(dueNo);
		lnDue = lnDueBo.getById(lnDue);
		getObjValue(formlndue0002, lnDue);
		return "detail";
	}
	/**
	 * 分账失败放款
	 * @return
	 * @throws Exception
	 */
	public String findAllFail(){
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formlndue0001 = formService.getFormData("lndue0001");
		lnDue = new LnDue();
		getFormValue(formlndue0001);
		setObjValue(formlndue0001, lnDue);
		lnDue.setBrNo(User.getBrno(this.getHttpRequest()));
		Logger logger = Logger.getLogger(WorkEforDue.class);
		Ipage ipage = this.getIpage();
		lnDueList = (List) lnDueBo.findByPage(ipage, lnDue).getResult();
    	lnDue.setApprSts("02");
		lnDueListFail = (List) lnDueBo.findAllFail(ipage, lnDue).getResult();
		// push入6号消息队列
    	Jedis jedis = RedisUtil.getJedis();
		for(int i=0; i<lnDueListFail.size();i++){
			lnDue = lnDueListFail.get(i);
			jedis.lpush(RedisUtil.QUEUE6, String.valueOf(lnDue));
			logger.info("分账失败业务重新分账-[合同编号为PactId=" + lnDueListFail.get(i).getPactId()+",合同号为"+lnDueListFail.get(i).getPactNo()+"]！");
		}
		this.addActionMessage("操作成功，请稍后查询放款状态");
		return "list";
	}
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formlndue0002 = formService.getFormData("lndue0002");
		 getFormValue(formlndue0002);
		 validateFormData(formlndue0002);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formlndue0002 = formService.getFormData("lndue0002");
		 getFormValue(formlndue0002);
		 validateFormData(formlndue0002);
		 
  	}
	public String getTab() throws Exception{
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		lnDue = new LnDue();
		lnDue.setDueNo(dueNo);
		lnDue  = lnDueBo.getById(lnDue);
		String cifNo = lnDue.getCifNo();
		String dueNo = lnDue.getDueNo();
		String pactNo = lnDue.getPactId();
		tabList = new ArrayList();
		String[] tab = null;
		tab = new String[2];
		tab[0] = "借据详情";
		tab[1] = "LnDueAction_getById.action?dueNo=" + dueNo+"&query="+query+"&formSts=1";
		tabList.add(tab);
		tab = new String[2];
		tab[0] = "账户信息";
		tab[1] = "LnAcctAction_findByPageRead.action?cifNo=" + cifNo+"&formSts=1";
		tabList.add(tab);
		return "tab";
	}
	
	public List getTabList() {
		return tabList;
	}
	public void setTabList(List tabList) {
		this.tabList = tabList;
	}
	public LnDue getLnDue() {
		return lnDue;
	}
	public void setLnDue(LnDue  lnDue) {
		this.lnDue = lnDue;
	}
	public List<LnDue> getLnDueList() {
		return lnDueList;
	}
	public void setLnDueList(List<LnDue> lnDueList) {
		this.lnDueList = lnDueList;
	}
	public LnDueBo getLnDueBo() {
		return lnDueBo;
	}
	public void setLnDueBo(LnDueBo lnDueBo) {
		this.lnDueBo = lnDueBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormlndue0002() {
		return formlndue0002;
	}
	public void setFormlndue0002(FormData formlndue0002) {
		this.formlndue0002 = formlndue0002;
	}
	public FormData getFormlndue0001() {
		return formlndue0001;
	}
	public void setFormlndue0001(FormData formlndue0001) {
		this.formlndue0001 = formlndue0001;
	}
	public void setDueNo(String dueNo){
		this.dueNo = dueNo;
	}		
	public String getDueNo(){
		return dueNo;
	}
	public List<LnDue> getLnDueListFail() {
		return lnDueListFail;
	}
	public void setLnDueListFail(List<LnDue> lnDueListFail) {
		this.lnDueListFail = lnDueListFail;
	}
	public FormData getFormaft0123() {
		return formaft0123;
	}
	public void setFormaft0123(FormData formaft0123) {
		this.formaft0123 = formaft0123;
	}
	public FormData getFormlndue0003() {
		return formlndue0003;
	}
	public void setFormlndue0003(FormData formlndue0003) {
		this.formlndue0003 = formlndue0003;
	}
	public String getFormSts() {
		return formSts;
	}
	public void setFormSts(String formSts) {
		this.formSts = formSts;
	}
	public FormData getFormlndue0004() {
		return formlndue0004;
	}
	public void setFormlndue0004(FormData formlndue0004) {
		this.formlndue0004 = formlndue0004;
	}
	public String getPactNo() {
		return pactNo;
	}
	public void setPactNo(String pactNo) {
		this.pactNo = pactNo;
	}
	public String getBrName() {
		return brName;
	}
	public void setBrName(String brName) {
		this.brName = brName;
	}
	public FormService getFormService() {
		return formService;
	}
	public void setFormService(FormService formService) {
		this.formService = formService;
	}
	public String getPoolId() {
		return poolId;
	}
	public void setPoolId(String poolId) {
		this.poolId = poolId;
	}
	public AftPoolRelBo getAftPoolRelBo() {
		return aftPoolRelBo;
	}
	public void setAftPoolRelBo(AftPoolRelBo aftPoolRelBo) {
		this.aftPoolRelBo = aftPoolRelBo;
	}
	public String getBrNo() {
		return brNo;
	}
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}
	public String getCifNo() {
		return cifNo;
	}
	public void setCifNo(String cifNo) {
		this.cifNo = cifNo;
	}
	public Double getDueAmt() {
		return dueAmt;
	}
	public void setDueAmt(Double dueAmt) {
		this.dueAmt = dueAmt;
	}
	public Double getBal() {
		return bal;
	}
	public void setBal(Double bal) {
		this.bal = bal;
	}
	public Double getIntst() {
		return intst;
	}
	public void setIntst(Double intst) {
		this.intst = intst;
	}
	public String getBegDate() {
		return begDate;
	}
	public void setBegDate(String begDate) {
		this.begDate = begDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getFiveSts() {
		return fiveSts;
	}
	public void setFiveSts(String fiveSts) {
		this.fiveSts = fiveSts;
	}
	public String getPoolDate() {
		return poolDate;
	}
	public void setPoolDate(String poolDate) {
		this.poolDate = poolDate;
	}
	public String getPoolSts() {
		return poolSts;
	}
	public void setPoolSts(String poolSts) {
		this.poolSts = poolSts;
	}
	public String getPactId() {
		return pactId;
	}
	public void setPactId(String pactId) {
		this.pactId = pactId;
	}
	/**
	 * @return the formlndue0005
	 */
	public FormData getFormlndue0005() {
		return formlndue0005;
	}
	/**
	 * @param formlndue0005 the formlndue0005 to set
	 */
	public void setFormlndue0005(FormData formlndue0005) {
		this.formlndue0005 = formlndue0005;
	}
	/**
	 * @return the lnStage
	 */
	public LnStage getLnStage() {
		return lnStage;
	}
	/**
	 * @param lnStage the lnStage to set
	 */
	public void setLnStage(LnStage lnStage) {
		this.lnStage = lnStage;
	}
	/**
	 * @return the lnStageBo
	 */
	public LnStageBo getLnStageBo() {
		return lnStageBo;
	}
	/**
	 * @param lnStageBo the lnStageBo to set
	 */
	public void setLnStageBo(LnStageBo lnStageBo) {
		this.lnStageBo = lnStageBo;
	}
	/**
	 * @return the acLnMst
	 */
	public AcLnMst getAcLnMst() {
		return acLnMst;
	}
	/**
	 * @param acLnMst the acLnMst to set
	 */
	public void setAcLnMst(AcLnMst acLnMst) {
		this.acLnMst = acLnMst;
	}
	/**
	 * @return the acLnMstBo
	 */
	public AcLnMstBo getAcLnMstBo() {
		return acLnMstBo;
	}
	/**
	 * @param acLnMstBo the acLnMstBo to set
	 */
	public void setAcLnMstBo(AcLnMstBo acLnMstBo) {
		this.acLnMstBo = acLnMstBo;
	}

}