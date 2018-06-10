package  app.creditapp.proj.action;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

import app.creditapp.corp.bo.CorpBaseBo;
import app.creditapp.corp.entity.CorpBase;
import app.creditapp.corp.entity.RptParams;
import app.creditapp.entity.CacheBase;
import app.creditapp.fund.bo.FundProvProjBo;
import app.creditapp.fund.bo.RptFundDailyBo;
import app.creditapp.fund.entity.FundProvProj;
import app.creditapp.fund.entity.RptFundDaily;
import app.creditapp.pact.bo.LnPactBo;
import app.creditapp.pact.entity.LnPact;
import app.creditapp.proj.bo.ProjAcctBo;
import app.creditapp.proj.bo.ProjBaseBo;
import app.creditapp.proj.bo.ProjMangBo;
import app.creditapp.proj.bo.ProjParmBo;
import app.creditapp.proj.bo.RptProjBo;
import app.creditapp.proj.entity.ProjAcct;
import app.creditapp.proj.entity.ProjBase;
import app.creditapp.proj.entity.ProjMang;
import app.creditapp.proj.entity.ProjParm;
import app.creditapp.proj.entity.ProjRel;
import app.creditapp.proj.entity.RptProj;
import app.oscache.CachecodeUtil;
import app.oscache.Datadict;
import app.util.User;
import app.util.toolkit.Ipage;

/**
 * Title: ProjBaseAction.java
 * Description:
 **/
public class ProjBaseAction extends BaseFormBean {

	//页面传值
	private ProjBase projBase;
	private LnPact lnPact;
	private ProjMang projMang;
	private List<ProjBase> projBaseList = new ArrayList<ProjBase>();
	private List<ProjBase> projBaseList1;
	private List<ProjMang> projMangList;
	private RptParams rptParams;
    
	//注入ProjBaseBo
	private ProjBaseBo projBaseBo;
	private LnPactBo lnPactBo;
	private ProjAcctBo projAcctBo;
	private ProjMangBo projMangBo;
	private ProjParmBo projParmBo;
	private RptProjBo rptProjBo;
	private FundProvProjBo fundProvProjBo;
	private RptFundDailyBo rptFundDailyBo;
	private CorpBaseBo corpBaseBo;
	

	private String query;
	private String flag;
	private String projNo;	
	private String acctType;
	private String isFlag;
	private String names="";
	private String brNo;
	private String brName;
	private String projNatu;
	private String projId;
	private String appId;
	private String backSts;
	private String projSts;
	private List tabList;
	
	private FormData formproj0001;
	private FormData formproj0002;
	private FormData formproj0013;
	private ProjRel projRel;
	private ProjRel projRel1;
	private ProjRel projRel2;
	private FormService formService = new FormService();
	
	public ProjBaseAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formproj0001 = formService.getFormData("proj0001");
		this.changeFormProperty(formproj0001, "projSts", "initValue","01");

		projBase = new ProjBase();
		getFormValue(formproj0001);
		setObjValue(formproj0001, projBase);
		projBase.setLoginid(User.getLoginIdByAuth(this.getHttpRequest())); // 从session中获取权限
		Ipage ipage = this.getIpage();
		if(projBase.getLoginid()==null||projBase.getLoginid()==""){
			projBaseList1 = (List) projBaseBo.findByPage(ipage, projBase).getResult();
		}else{
			projBaseList1 = (List) projBaseBo.findByPageForUser(ipage, projBase).getResult();
		}
		for(int i=0;i<projBaseList1.size();i++){
			if(projBaseList1.get(i).getBrNo()==null){
				projBaseList1.get(i).setBrNoSts("0");
				projBaseList.add(projBaseList1.get(i));
			}else{
				projBaseList.add(projBaseList1.get(i));
			}
		}
		return "list";
	}

	//同步
	public String merge() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formproj0001 = formService.getFormData("proj0001");
		projBase = new ProjBase();
		projBase.setOpNo(User.getLoginid(ServletActionContext.getRequest()));//操作员
		projBase.setTxDate(User.getSys_date(ServletActionContext.getRequest()));//登记日期
		projBaseBo.merge(projBase);
		projMang = new ProjMang();
		projMangList = (List)projMangBo.findByPageForManager(projMang);
		for(int i=0;i<projMangList.size();i++){
			String loginId =projMangList.get(i).getManagers();
			String id =projMangList.get(i).getId();
			String[] array = loginId.split(",");
			for(int j=0;j<array.length;j++){
				projMang.setLoginId(array[j]);
				projMang.setProjNo(id);
				projMangBo.insert(projMang);
			}
		}
		//获取现在数据库中所有的项目
		projBase = new ProjBase();
		projBase.setLoginid(User.getLoginIdByAuth(this.getHttpRequest())); // 从session中获取权限
		
		List<ProjBase> projBaseList2 = (List) projBaseBo.getList(projBase);
		if(projBaseList2 != null){
			ProjBase projBaseForA = new ProjBase();
			String projNoForParm = null;
			//为每个项目 增加一个 默认的 配置信息
			for (int j = 0;j<projBaseList2.size();j++){
				ProjParm projParm  = new ProjParm();
				projBaseForA = projBaseList2.get(j);
				projNoForParm = projBaseForA.getProjNo();
				projParm.setProjNo(projNoForParm);
				projParm = projParmBo.getById(projParm);
				if(projParm==null || "".equals(projParm)){
					ProjParm projParmP  = new ProjParm();
					projParmP.setProjNo(projNoForParm);
					projParmP.setProjName(projBaseForA.getProjName());
					projParmP.setOverDays(5);
					projParmP.setTruPayType("1");
					projParmP.setMangFeerate(0.005f);
					projParmP.setRgAppRate(10f);
					projParmP.setBackDays(90);
					projParmP.setLeverRate(0.25f);
					projParmBo.insert(projParmP);
				}
			}
		}
		this.addActionMessage("操作成功");
		this.changeFormProperty(formproj0001, "projSts", "initValue","01");
		projBase = new ProjBase();
		projBase.setLoginid(User.getLoginIdByAuth(this.getHttpRequest())); // 从session中获取权限
		projBase.setProjSts("01");
		Ipage ipage = this.getIpage();
		projBaseList1 = (List) projBaseBo.findByPage(ipage, projBase).getResult();
		for(int i=0;i<projBaseList1.size();i++){
			projBase = new ProjBase();
			projBase = projBaseList1.get(i);
			String brNo = projBase.getBrNo();
			if(brNo==null || "".equals(brNo)){
				projBase.setBrNoSts("0");
				projBaseList.add(projBase);
			}else{
				projBaseList.add(projBase);
			}
		}
		return "detail";
	}
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formproj0002 = formService.getFormData("proj0002");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formproj0002 = formService.getFormData("proj0002");
		getFormValue(formproj0002);
		projBase = new ProjBase();
		setObjValue(formproj0002, projBase);
		projMang = new ProjMang();
		ProjMang projMang1 = new ProjMang();
		projBase.setDeptNo(User.getBrno(ServletActionContext.getRequest()));//登记部门
		projBase.setOpNo(User.getLoginid(ServletActionContext.getRequest()));//操作员
		projBase.setTxDate(User.getSys_date(ServletActionContext.getRequest()));//登记日期
		//projBase.setUpDate(User.getSys_date(ServletActionContext.getRequest()));//修改日期
		//projBase.setUpOpno(User.getLoginid(ServletActionContext.getRequest()));//修改人员
		projMang.setLoginId(User.getLoginid(ServletActionContext.getRequest()));
		projMang.setProjNo(projBase.getProjNo());
		projMang1 = projMangBo.getById(projMang);
		if(projMang1 == null){
			projMang.setPerSOn(User.getLoginid(getHttpRequest()));
			projMang.setTiMe(projBase.getTxDate());
			projMangBo.insert(projMang);
		}else{
			this.addActionMessage("该用户已经关联此项目并具有此项目的管理权限");
		}
		
		//新增操作增加放款扣款账号
		
		ProjAcct projAcct = new ProjAcct();
		projAcct.setProjNo(projId);
////		//专户
		projAcct.setCardSts("01");
		projAcct.setAcctType("01");
		
		//获取项目编号与租户号
		String projNo =   projBase.getProjNo();
		String tenantNo = projBase.getTenantNo();
//		int countForZ = projAcctBo.getCountForProj(projAcct);
//		if(countForZ == 0){
//			this.addActionMessage("此项目 专户账户信息 不存在 或 失效,请检查 账户信息 ");
//		}else{
		if(!projNo.equals(tenantNo)){
			this.addActionMessage("项目编号与租户号不一致，请检查！");	
		}
		else {
			projAcct.setAcctType("03");
			createAcctAndSend(projBase);//转向开户方法。。
			projBaseBo.insert(projBase);
			this.addActionMessage("操作成功");
			getObjValue(formproj0002, projBase);
				}
//		this.addActionMessage("操作成功");
			return "detail";
	

}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	//保存更新的条件是确保TA客户都已经与我们的合作机构ID映射后，再自动关联
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formproj0002 = formService.getFormData("proj0002");
		getFormValue(formproj0002);
		projBase = new ProjBase();
		setObjValue(formproj0002, projBase);
		projBase.setOthRate(Float.parseFloat(ServletActionContext.getRequest().getParameter("othRate"))/100);
		projBase.setPayRate(Float.parseFloat(ServletActionContext.getRequest().getParameter("payRate"))/100);
		//软通项目编号为固定值，小微项目编号为可变值 projid为主键
		projBase.setProjId(projId);
		//projBase.setDeptNo(User.getBrno(ServletActionContext.getRequest()));//登记部门
		//projBase.setOpNo(User.getLoginid(ServletActionContext.getRequest()));//操作员
		//projBase.setTxDate(User.getSys_date(ServletActionContext.getRequest()));//登记日期
		projBase.setUpDate(User.getSys_date(ServletActionContext.getRequest()));//修改日期
		projBase.setUpOpno(User.getLoginid(ServletActionContext.getRequest()));//修改人员
	
		//判断此项目下 是否存在 虚拟账户 和 专户账号  ，不存在的话 不会进行保存更改

		//暂时跳过判断是否有专户虚拟户——————————注释掉
		ProjAcct projAcct = new ProjAcct();
		projAcct.setProjNo(projId);
////		//专户
		projAcct.setCardSts("01");
		projAcct.setAcctType("01");
//  获得项目编号与租户号
		String projNo =   projBase.getProjNo();
		String tenantNo = projBase.getTenantNo();
		ProjAcct acct = new ProjAcct();
		acct.setProjNo(projNo);
		int size = projAcctBo.getByProjNo(acct);
		
//		int countForZ = projAcctBo.getCountForProj(projAcct);
//		if(countForZ == 0){
//			this.addActionMessage("此项目 专户账户信息 不存在 或 失效,请检查 账户信息 ");
//		}else{
		if(!projNo.equals(tenantNo))
			this.addActionMessage("项目编号与租户号不一致，请检查！");
		else if(size>=2){
			return "detail";
		}else{
			projAcct.setAcctType("03");
			createAcctAndSend(projBase);//转向开户方法。。。
//				};
//			int countForX = projAcctBo.getCountForProj(projAcct);
//			if(countForX ==0){
//				this.addActionMessage("此项目 虚拟账户信息 不存在 或 失效,请检查 账户信息 ");
//			}else{
//				//所有信息都存在
			String result =projBaseBo.update(projBase);
	
//	
//				if(!projNo.equals(tenantNo))
//					this.addActionMessage("项目编号与租户号不一致，请检查！");
//				else 
				if("1".equals(result)){
					this.addActionMessage("操作成功");
					getObjValue(formproj0002, projBase);
				}else{
					this.addActionMessage("此次更新失败！");
				}
		};
//			}
//		}
		return "detail";
	}
	
	
	
//   新增的项目信息保存时判断是否有账号及自动开户
	
public String createAcctAndSend(ProjBase projBase){
		
		String CustNo = projBase.getProjNo();//客户号
		String CustName = projBase.getProjName();//客户名
		String TenantNo = projBase.getTenantNo();//租户号=
		String BrNo = projBase.getBrNo();//机构号
		String res = "";
		CacheBase cacheBase = null; 
		List<CacheBase> li = new Datadict("PROJ_ACCT_TYPE").getDatadictByLevel();
		for (int i=0;i<li.size();i++){
			cacheBase = li.get(i);
//			ProjAcct projAcct = new ProjAcct();
			if("01".equals(cacheBase.getOpt_code())){//专户
				
//				if(projAcctBo.getCount(projAcct)!=0){//该类型的账户不已存在则存入数据库
				continue;
				}
				
			ProjAcct projAcct = new ProjAcct();
			projAcct.setProjNo(projBase.getProjNo());
			projAcct.setAcctType(cacheBase.getOpt_code());
			
//			
//    2017/9/5   不要备付金账户
			if("02".equals(cacheBase.getOpt_code())){//三方备付金账户
//				if(projAcctBo.getCount(projAcct)!=0){//该类型的账户不已存在则存入数据库
//					continue;
//				}
//				projAcct.setProjName(projBase.getProjName());
//				projAcct.setFiller("三方备付金账户");
//				projAcct.setCardSts("01");
//				projAcct.setSendSts("05");
//
//				//获取账号acct_no，xinjia				
//				projAcct.setAcctNo(projBase.getBankAcntNo());
//
//				
//				projAcct.setDeptNo(User.getBrno(ServletActionContext.getRequest()));// 登记部门
//				projAcct.setOpNo(User.getLoginid(ServletActionContext.getRequest()));// 操作员
//				projAcct.setTxDate(User.getSys_date(ServletActionContext.getRequest()));// 登记日期
//				projAcctBo.insert(projAcct);
				continue;
			}
			//账户号
			String AccountNo = CachecodeUtil.MFSPREFIX+"_"+cacheBase.getOpt_code()+"_"+projBase.getProjNo();
			//账户名
			String AccountName = CachecodeUtil.MFSPREFIX+"_"+cacheBase.getOpt_name()+"_"+projBase.getProjName();
			//发送支付
			/*
			AcctSendDetail acctSendDetail = new AcctSendDetail();
			acctSendDetail.setAccountName(AccountName);
			acctSendDetail.setAccountNo(AccountNo);
			acctSendDetail.setTenantNo(TenantNo);//租户号
			acctSendDetail.setCustNo(CustNo);
			acctSendDetail.setCustName(CustName);
			String acctType = cacheBase.getOpt_code();
			if("03".equals(acctType)){
				//放款账户
				acctSendDetail.setAcctType("1");
			}else if ("04".equals(acctType)){
				//扣款账户
				acctSendDetail.setAcctType("2");
			}
//			ResultMes resultMes = SendZFService.createAcctXMLAndSend(acctSendDetail, BrNo);
			*/
			projAcct.setAcctNo(AccountNo);
			projAcct.setAcctName(AccountName);
			projAcct.setProjName(projBase.getProjName());
			projAcct.setAcctBal(0.00);
			if("03".equals(cacheBase.getOpt_code())){
				projAcct.setAcctUse("01");
			}else if("04".equals(cacheBase.getOpt_code())){
				projAcct.setAcctUse("02");
			}
			projAcct.setCardSts("01");
			projAcct.setFiller("自动生成");
			projAcct.setDeptNo(User.getBrno(ServletActionContext.getRequest()));// 登记部门
			projAcct.setOpNo(User.getLoginid(ServletActionContext.getRequest()));// 操作员
			projAcct.setTxDate(User.getSys_date(ServletActionContext.getRequest()));// 登记日期
			
			/*
			if("1".equals(resultMes.getResultCode())){
				projAcct.setSendSts("02");//02代表已发送，开户成功
				System.out.println("账户 开户成功");
			}else{
				projAcct.setSendSts("01");
				System.out.println("账户 开户失败，"+resultMes.getResultMsg());
			}*/
			if(projAcctBo.getCount(projAcct)==0){//该类型的账户不已存在则存入数据库
				projAcctBo.insert(projAcct);
			}else{
				projAcctBo.updateSts(projAcct);
			}
		}
		return res;
	}


///////新增分界线————————————————————————————————————————————————————————————————————————————————————


	//保存更新的条件是确保TA客户都已经与我们的合作机构ID映射后，再自动关联
	public String updateForBrNo() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formproj0001 = formService.getFormData("proj0001");
		projBase = new ProjBase();
		projBase.setProjId(projId);
		projBase.setProjNo(projNo);
		projBase = projBaseBo.getById(projBase);
		projBase.setBrNo(brNo);
		//软通项目编号为固定值，小微项目编号为可变值  projid为主键
		projBase.setProjId(projId);
		projBase.setBrName(brName);
		String result = projBaseBo.update(projBase);
		if("1".equals(result)){
			this.addActionMessage("关联合作机构号更新成功");
		}else{
			this.addActionMessage("关联合作机构号更新失败！");
		}
		this.changeFormProperty(formproj0001, "projSts", "initValue","01");
		projBase = new ProjBase();
		projBase.setLoginid(User.getLoginIdByAuth(this.getHttpRequest())); // 从session中获取权限
		projBase.setProjSts("01");
		Ipage ipage = this.getIpage();
		projBaseList = (List) projBaseBo.findByPage(ipage, projBase).getResult();
		return "list";
	}
	
	public String relateBr() throws Exception {
		this.getHttpResponse().setContentType("text/html;charset=UTF-8");
		projBase = new ProjBase();
		projBase.setProjId(projId);
		projBase.setProjNo(projNo);
		projBase = projBaseBo.getById(projBase);
		projBase.setBrNo(brNo);
		PrintWriter pw = this.getHttpResponse().getWriter();
		if(brNo == null || "".equals(brNo)){
			pw.write("error|"+"未选择合作机构，此次更新失败");
		}else{
			//软通项目编号为固定值，小微项目编号为可变值  projid为主键
			projBase.setProjId(projId);
			CorpBase  corpBase = new CorpBase();
			corpBase.setBrNo(brNo);
			corpBase = corpBaseBo.getById(corpBase);
			projBase.setBrName(corpBase.getBrName());
			String result = projBaseBo.update(projBase);
			if("1".equals(result)){
				pw.write("success|"+"编号为： "+brNo+"  的合作机构号更新成功");
			}else{
				pw.write("error|"+"编号为： "+brNo+"  的合作机构号更新成功");
			}
		}
		return null;
	}
	
	//标记
	public String updateForFlag() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		projBase = new ProjBase();
		projMang = new ProjMang();
		
		if(projNo==null || "".equals(projNo)){
			this.addActionMessage("请先在 详情->项目信息 中补录小微项目编号！");
		}else{
			
			projBase.setProjNo(projNo);//项目编号
			projBase.setLoginid(User.getLoginid(this.getHttpRequest()));//操作员编号
			projMang.setProjNo(projNo);//项目编号
			projMang.setLoginId(User.getLoginid(this.getHttpRequest()));//操作员编号
			projBase = projBaseBo.getByIdForFlag(projBase);
			projMang = projMangBo.getById(projMang);
			if(projMang==null){
				this.addActionMessage("已标记");
			}else{
			if("1".equals(projBase.getVipFlag())){
				projMang.setVipFlag("0");
				projMangBo.update(projMang);
				this.addActionMessage("取消标记");
			}else{
				projMang.setVipFlag("1");
				projMangBo.update(projMang);
				this.addActionMessage("已标记");
				}
			}
		}
		formproj0001 = formService.getFormData("proj0001");
		projBase = new ProjBase();
		this.changeFormProperty(formproj0001, "projSts", "initValue","01");
		projBase.setProjSts("01");
		projBase.setLoginid(User.getLoginIdByAuth(this.getHttpRequest())); // 从session中获取权限
		Ipage ipage = this.getIpage();
		projBaseList1 = (List) projBaseBo.findByPageForUser(ipage, projBase).getResult();
		for(int i=0;i<projBaseList1.size();i++){
			if(projBaseList1.get(i).getBrNo()==null){
				projBaseList1.get(i).setBrNoSts("0");
				projBaseList.add(projBaseList1.get(i));
			}else{
				projBaseList.add(projBaseList1.get(i));
			}
		}
		return "list";
	}
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formproj0001 = formService.getFormData("proj0001");
		projBase = new ProjBase();
		projBase.setProjNo(projNo);
		projBaseBo.del(projBase);
		this.addActionMessage("删除成功");
		projBase = new ProjBase();
		projBase.setBrNo(User.getBrno(this.getHttpRequest()));
		Ipage ipage = this.getIpage();
		projBaseList = (List) projBaseBo.findByPage(ipage, projBase).getResult();
		return "list";
	}
	
	/**
	 * 解冻操作
	 * 
	 */
	 public String unfreeze()throws Exception{
		 ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		 projBase = new ProjBase();
		 projBase.setProjNo(projNo);
		 projBase.setProjSts("01");
		 projBaseBo.updateSts(projBase);	
		 formproj0001 = formService.getFormData("proj0001");
		 projBase = new ProjBase();
		 Ipage ipage = this.getIpage();
		 if(projBase.getProjSts()==null){
				projBase.setProjSts("01");
			}
			this.changeFormProperty(formproj0001, "projSts", "initValue","01");
		 projBase.setLoginid(User.getLoginIdByAuth(this.getHttpRequest())); // 从session中获取权限
		 projBaseList = (List) projBaseBo.findByPage(ipage, projBase).getResult();
		
		 return "list";
	 }
	 /**
		 * 
		 * 结束操作
		 */
	 public String Theend()throws Exception{
		 ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		 projBase = new ProjBase();
		 projBase.setProjNo(projNo);
		 projBase.setProjSts("02");
		 projBaseBo.updateSts(projBase);
		 projBaseBo.update_Read(projBase);
		 formproj0001 = formService.getFormData("proj0001");
		 projBase = new ProjBase();
		 getFormValue(formproj0001);
		 Ipage ipage = this.getIpage();
		 projBaseList1 = (List) projBaseBo.findByPage(ipage, projBase).getResult();
		 for(int i=0;i<projBaseList1.size();i++){
				if(projBaseList1.get(i).getBrNo()==null){
					projBaseList1.get(i).setBrNoSts("0");
					projBaseList.add(projBaseList1.get(i));
				}else{
					projBaseList.add(projBaseList1.get(i));
				}
			}
		 return "list";
	 }
	 /**
	  * 冻结操作
	  */
	 public String Freeze()throws Exception{
		 ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		 projBase = new ProjBase();
		 projBase.setProjNo(projNo);
		 projBase.setProjSts("03");
		 projBaseBo.updateSts(projBase);
		 
		 formproj0001 = formService.getFormData("proj0001");
		 projBase = new ProjBase();
		 if(projBase.getProjSts()==null){
				projBase.setProjSts("01");
			}
			this.changeFormProperty(formproj0001, "projSts", "initValue","01");
		 projBase.setLoginid(User.getLoginIdByAuth(this.getHttpRequest())); // 从session中获取权限
		 Ipage ipage = this.getIpage();
		 projBaseList1 = (List) projBaseBo.findByPage(ipage, projBase).getResult();
		 for(int i=0;i<projBaseList1.size();i++){
				if(projBaseList1.get(i).getBrNo()==null){
					projBaseList1.get(i).setBrNoSts("0");
					projBaseList.add(projBaseList1.get(i));
				}else{
					projBaseList.add(projBaseList1.get(i));
				}
			}
		 return "list";
	 }

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formproj0002 = formService.getFormData("proj0002");
		projBase = new ProjBase();
		projBase.setProjId(projId);
		projBase.setProjNo(projNo);
		projBase = projBaseBo.getById(projBase);
		getObjValue(formproj0002, projBase);
		return "detail";
	}
	public String getByIdForProjNo() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formproj0002 = formService.getFormData("proj0002");
		projBase = new ProjBase();
		lnPact = new LnPact();
		lnPact.setAppId(appId);
		lnPact = lnPactBo.getByIdForAppId(lnPact);
		projBase.setProjNo(lnPact.getProjNo());
		projBase = projBaseBo.getByIdForProjNo(projBase);
		getObjValue(formproj0002, projBase);
		return "detail";
	}
	//合作机构360视图观看关联项目项
	public String getByIdforCorp() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		projBase = new ProjBase();
		projBase.setBrNo(brNo);
		Ipage ipage = this.getIpage();
		projBaseList = (List) projBaseBo.findByPage(ipage, projBase).getResult();
		return "view";
	}
	public String getByIdforCorp360() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		projBase = new ProjBase();
		projBase.setBrNo(brNo);
		Ipage ipage = this.getIpage();
		projBaseList = (List) projBaseBo.findByPage(ipage, projBase).getResult();
		return "view";
	}
	/**
	 * @作者 DHCC-ZLC
	 * @日期 2016-8-10
	 * @方法说明：
	 * @返回参数 String
	 */
	public String myProj_echarts() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		projBase = new ProjBase();
		projBase.setLoginid(User.getLoginIdByAuth(this.getHttpRequest()));
		//--项目/规模
		projBaseList = (List) projBaseBo.myProj_echarts(projBase);
		String nodes = "";
		String name = "";
		for(ProjBase proj: projBaseList){
			nodes += ",{name:'"+proj.getProjName()+"',value:"+proj.getProjAmt()/100000000+"}";
			//nodes += ",{value:"+proj.getProjAmt()+"}";
			name += ",'"+proj.getProjName()+"'";
			
		}
		if(nodes.length()>0){
			nodes = nodes.substring(1);
		}
		if(name.length()>0){
			name = name.substring(1);
		}		
		projRel = new ProjRel();
		projRel.setTitle("");
		projRel.setName(name);
		projRel.setData("");
		projRel.setNodes(nodes);
		//--项目/累计业务量
		projBase.setTxDate((User.getTime().replace("-", "")).substring(0, 8));
		List<ProjBase> projBaseList1 = (List) projBaseBo.myProj_day_echarts(projBase);	
		//--项目类型（集合/单一）比例
		List<ProjBase> projBaseList2 = (List) projBaseBo.myProj_fdtype_day_echarts(projBase);	
		 nodes = "";	
		 name = "";
		for(ProjBase proj: projBaseList1){
			nodes += ",{name:'"+proj.getProjName()+"',value:"+proj.getProjAmt()+"}";
			
			name += ",'"+proj.getProjName()+"'";
			
		}
		if(nodes.length()>0){
			nodes = nodes.substring(1);
		}
		if(name.length()>0){
			name = name.substring(1);
		}		
		projRel1 = new ProjRel();
		projRel1.setTitle("");
		projRel1.setName(name);
		projRel1.setData("");
		projRel1.setNodes(nodes);	
		 nodes = "";
		 name = "";
		 String projtype = "";
		for(ProjBase proj: projBaseList2){
			if("02".equals(proj.getProjNatu())){
				projtype = "集合类";
			}else{
				projtype = "单一类";
			}
			nodes += ",{name:'"+projtype+"',value:"+proj.getProjAmt()+"}";
			//nodes += ",{value:"+fund.getFdAmt()+"}";
			name += ",'"+projtype+"'";
			
		}
		if(nodes.length()>0){
			nodes = nodes.substring(1);
		}
		if(name.length()>0){
			name = name.substring(1);
		}		
		projRel2 = new ProjRel();
		projRel2.setTitle("");
		projRel2.setName(name);
		projRel2.setData("");
		projRel2.setNodes(nodes);	
		
		return "list";
	}
	public String myProj_getDetailTab() throws Exception{
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		tabList = new ArrayList();
		String[] tab = null;
		
//		tab = new String[2];
//		tab[0] = "集合类项目";
//		tab[1] = "ProjBaseAction_myProj_findByPage.action";
//		tabList.add(tab);
//		
//     	tab = new String[2];
//		tab[0] = "单一类项目";
//		tab[1] = "ProjBaseAction_myProj_findByPage.action?projNatu=01";
//		tabList.add(tab);
	    
		return "tab";
	}
	public String myProj_findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		//list列表
		projBase = new ProjBase();
		if(projNatu == null || "".equals(projNatu)){
			projBase.setProjNatu("02");
		}else{
			projBase.setProjNatu(projNatu);
		}
		projBase.setLoginid(User.getLoginIdByAuth(this.getHttpRequest())); // 从session中获取权限
		Ipage ipage = this.getIpage();
		projBaseList = (List) projBaseBo.findByMyPage(ipage, projBase).getResult();
		return "list";
	}
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formproj0002 = formService.getFormData("proj0002");
		 getFormValue(formproj0002);
		 validateFormData(formproj0002);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formproj0002 = formService.getFormData("proj0002");
		 getFormValue(formproj0002);
		 validateFormData(formproj0002);
  	}
	
	
	/**
	 * 查看360视角
	 * @return
	 */
	public String getAllDetail() {
		ActionContext.initialize(ServletActionContext.getRequest(), 
				ServletActionContext.getResponse());
		projBase = new ProjBase();
		projBase.setProjId(projId);
		projBase.setProjNo(projNo);
		//cifPers.setEcif_no(ecif_no);
		projBase = projBaseBo.getById(projBase);
		//cifType = cifPersInf.getCifType();
		//if_agri = cifPers.getIf_agri();
		return "all";
	}
	/**
	 * @作者 DHCC-ZLC
	 * @日期 2016-8-12
	 * @方法说明：查看我的项目360视角
	 * @返回参数 String
	 */
	public String myProj_getAllDetail() {
		ActionContext.initialize(ServletActionContext.getRequest(), 
				ServletActionContext.getResponse());
		//projBase = new ProjBase();
		//projBase.setProjNo(projNo);
		//cifPers.setEcif_no(ecif_no);
		//projBase = projBaseBo.getById(projBase);
		//cifType = cifPersInf.getCifType();
		//if_agri = cifPers.getIf_agri();
		return "all";
	}	
	public String getAllDetailForRead() {
		ActionContext.initialize(ServletActionContext.getRequest(), 
				ServletActionContext.getResponse());
		projBase = new ProjBase();
		projBase.setProjId(projId);
		projBase.setProjNo(projNo);
		//cifPers.setEcif_no(ecif_no);
		projBase = projBaseBo.getById(projBase);
		//cifType = cifPersInf.getCifType();
		//if_agri = cifPers.getIf_agri();
		return "all";
	}
	/**
	 * 360试图中table头
	 * @return
	 */
	public String getDetailTop() {
		ActionContext.initialize(ServletActionContext.getRequest(), 
				ServletActionContext.getResponse());
		formproj0013 = formService.getFormData("proj0013");
		projBase = new ProjBase();
		projBase.setProjId(projId);
		projBase.setProjNo(projNo);
		projBase = projBaseBo.getById(projBase);
		projMang = new ProjMang();
		projMang.setProjNo(projNo);
		projMangList = (List<ProjMang>)projMangBo.getByIdLoginId(projMang);
		for(int i = 0; i<projMangList.size();i++){
			String name=projMangList.get(i).getUserName();
			if(name!=null){
			 names = name+"|"+names;
			}
		}
		if(names==""){
			names="1";
		}
		names = names.substring(0,names.length()-1);
		projBase.setOpNo(names);
		getObjValue(formproj0013, projBase);
		query = "query";
		return "top";
	}
	
	public String getTab() throws Exception{
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		tabList = new ArrayList();
		String[] tab = null;
		
		tab = new String[2];
		tab[0] = "项目信息";
		tab[1] = "ProjBaseAction_getById.action?projNo=" + projNo+ "&query="+ query + "&projId="+projId+"";
		tabList.add(tab);
		

		tab = new String[2];
		tab[0] = "账户信息";
	    tab[1] = "ProjAcctAction_findByPageToProj_Read.action?projNo=" + projNo + "&query="+ query + "&projId="+projId+"&flag="+"1";
	    tabList.add(tab);
			
			
		tab = new String[2];
		tab[0] = "配置信息";
		tab[1] = "ProjParmAction_getById.action?projNo="+projNo+ "&query="+ query + "&projId="+projId+"";
		tabList.add(tab);
	    tab = new String[2];
		tab[0] = "债权赎买历史";
		tab[1] = "FundRightDetailAction_findByPage.action?projNo="+projNo+"&query="+query;
		tabList.add(tab);
		
	    
		return "tab";
	}
	

	public String getDetailTab() throws Exception{
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		tabList = new ArrayList();
		if(projNo==null || "".equals(projNo)){
			this.addActionMessage("请先在  项目信息 中补录小微项目编号！");
			return "   ";
		}else{
			String[] tab = null;
			
			tab = new String[2];
			tab[0] = "资产笔数";
			tab[1] = "ProjBaseAction_getProNum.action?projNo=" + projNo+ "&query="+ query + "";
		    tabList.add(tab);
		    
//		    tab = new String[2];
//			tab[0] = "发放金额";
//			tab[1] = "ProjBaseAction_getLoanAmt.action?projNo=" + projNo+ "&query="+ query + "";
//		    tabList.add(tab);


		    tab = new String[2];
			tab[0] = "资金规模";
			tab[1] = "ProjBaseAction_getSize.action?projNo=" + projNo+ "&projNatu=" +projNatu+ "&query="+ query + "";
		    tabList.add(tab);
		    
		    tab = new String[2];
			tab[0] = "资金杠杆比例";
			tab[1] = "ProjBaseAction_getLever.action?projNo=" + projNo+ "&query="+ query + "";
		    tabList.add(tab);
		    
		    tab = new String[2];
			tab[0] = "放款回收金额";
			tab[1] = "ProjBaseAction_getReback.action?projNo=" + projNo+ "&query="+ query + "";
		    tabList.add(tab);
		    
		    tab = new String[2];
			tab[0] = "放款回收笔数";
			tab[1] = "ProjBaseAction_getReNumback.action?projNo=" + projNo+ "&query="+ query + "";
		    tabList.add(tab);
		    
		    tab = new String[2];
			tab[0] = "账户余额";
			tab[1] = "ProjBaseAction_getVirtualBal.action?projNo=" + projNo+ "&query="+ query + "";
		    tabList.add(tab);
		    
		    tab = new String[2];
			tab[0] = "收益情况";
			tab[1] = "ProjBaseAction_getIncome.action?projId=" + projId+"&projNo=" + projNo+ "&query="+ query + "";
		    tabList.add(tab);
		    
		    tab = new String[2];
			tab[0] = "经营指标";
			tab[1] = "ProjBaseAction_getBusniess_Indicators.action?projId=" + projId+ "&projNo=" + projNo+ "&query="+ query + "";
		    tabList.add(tab);
		    
			return "tab";
		}
		
	}
	
	public String getProNum() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		RptProj rptProj = new RptProj();
		rptProj.setProjNo(projNo);
		List<RptProj> list = rptProjBo.findByAll(rptProj);
		
		String xStr = "";
		String data = "";
		for(RptProj rcd : list){
			xStr += ",'"+rcd.getRptDate()+"'";
			data += ","+rcd.getLnCnt();  //未结清笔数
		}
		if(xStr.length()>0){
			xStr = xStr.substring(1);
			data = data.substring(1);
		}
		rptParams = new RptParams();
		rptParams.setData1(xStr);
		rptParams.setData2(data);
		return "detail";
	}
	
	public String getLoanAmt() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		RptProj rptProj = new RptProj();
		rptProj.setProjNo(projNo);
		List<RptProj> list = rptProjBo.findByAll(rptProj);
		
		String xStr = "";
		String data = "";
		for(RptProj rcd : list){
			xStr += ",'"+rcd.getRptDate()+"'";
			data += ","+new DecimalFormat("#.00").format(rcd.getAmtTot()/10000);
		}
		if(xStr.length()>0){
			xStr = xStr.substring(1);
			data = data.substring(1);
		}
		rptParams = new RptParams();
		rptParams.setData1(xStr);
		rptParams.setData2(data);
		return "detail";
	}
	public String getAmtDay() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		RptProj rptProj = new RptProj();
		rptProj.setProjNo(projNo);
		List<RptProj> list = rptProjBo.findByAll(rptProj);
		
		String xStr = "";
		String data = "";
		for(RptProj rcd : list){
			xStr += ",'"+rcd.getRptDate()+"'";
			data += ","+new DecimalFormat("#.00").format(rcd.getAmtDay()/10000);
		}
		if(xStr.length()>0){
			xStr = xStr.substring(1);
			data = data.substring(1);
		}
		rptParams = new RptParams();
		rptParams.setData1(xStr);
		rptParams.setData2(data);
		return "detail";
	}
	
	public String getIncome() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		FundProvProj fundProvProj = new FundProvProj();
		fundProvProj.setProjNo(projNo);
		List<FundProvProj> list = fundProvProjBo.findAll(fundProvProj);
		
		String xStr = "";
		String data1 = "";
		String data2 = "";
		String data3 = "";
		String data4 = "";
		for(FundProvProj rcd : list){
			xStr += ",'"+rcd.getBegDate()+"-"+rcd.getEndDate()+"'";
			data1 += ","+new DecimalFormat("#.00").format(rcd.getPayAmt()/10000);
			data2 += ","+new DecimalFormat("#.00").format(rcd.getFinlIncome()/10000);
			data3 += ","+new DecimalFormat("#.00").format(rcd.getServiceFee()/10000);
			data4 += ","+new DecimalFormat("#.00").format(rcd.getManagerFee()/10000);
		}
		if(xStr.length()>0){
			xStr = xStr.substring(1);
			data1 = data1.substring(1);
			data2 = data2.substring(1);
			data3 = data3.substring(1);
			data4 = data4.substring(1);
		}
		rptParams = new RptParams();
		rptParams.setData1(xStr);
		rptParams.setData2(data1);
		rptParams.setData3(data2);
		rptParams.setData4(data3);
		rptParams.setData5(data4);
		return "detail";
	}
	
	/**
	 * @作者 DHCC-ZLC
	 * @日期 2016-8-18
	 * @方法说明：未完成，待续
	 * @返回参数 String
	 */
	public String getSize() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		RptFundDaily rptFundDaily = new RptFundDaily();
		rptFundDaily.setProjNo(projNo);
		String xStr = "";
		String data = ""; //总额
		String data1 = "";//优先级
		String data2 = "";//次级
		if("01".equals(projNatu)){
		  //单一类
			List<RptFundDaily> list = rptFundDailyBo.findByFdType(rptFundDaily);

			for(RptFundDaily rcd : list){
				xStr += ",'"+rcd.getRptDate()+"'";
				data += ","+new DecimalFormat("#.00").format(rcd.getFdAmt()/10000);
			}
			if(xStr.length()>0){
				xStr = xStr.substring(1);
				data = data.substring(1);
			}			
		}else {
		  //集合类
		   rptFundDaily.setFdType("01");
		   List<RptFundDaily> list = rptFundDailyBo.findByFdType(rptFundDaily);
			for(RptFundDaily rcd : list){
				xStr += ",'"+rcd.getRptDate()+"'";
				data1 += ","+new DecimalFormat("#.00").format(rcd.getFdAmt()/10000);
			}

			rptFundDaily.setFdType("02");
			List<RptFundDaily> list1 = rptFundDailyBo.findByFdType(rptFundDaily);
			 for(RptFundDaily rcd : list1){
				 data2 += ","+new DecimalFormat("#.00").format(rcd.getFdAmt()/10000);
			 }
			 if(xStr.length()>0){
				 xStr = xStr.substring(1);
			 }
			 if(data1.length()>0){
				 data1 = data1.substring(1);
			 }
			 if(data2.length()>0){
				 data2 = data2.substring(1);
			 }
		}
    	rptParams = new RptParams();
		rptParams.setData1(xStr);
		rptParams.setData2(data);
 		rptParams.setData3(data1);
		rptParams.setData4(data2);
		return "detail";
	}
	
	public String getLever() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		RptProj rptProj = new RptProj();
		rptProj.setProjNo(projNo);
		List<RptProj> list = rptProjBo.findByAll(rptProj);
		
		String xStr = "";
		String data = "";
		for(RptProj rcd : list){
			//判断分母是否为空或0
			if(!"".equals(rcd.getSubAmt())&&rcd.getSubAmt()!=0){
				xStr += ",'"+rcd.getRptDate()+"'";
				data += ","+new DecimalFormat("#.00").format(rcd.getPriAmt()/rcd.getSubAmt()*100);
			}
		}
		if(xStr.length()>0){
			xStr = xStr.substring(1);
			data = data.substring(1);
		}
		rptParams = new RptParams();
		rptParams.setData1(xStr);
		rptParams.setData2(data);
		return "detail";
	}
	
	public String getReback() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		RptProj rptProj = new RptProj();
		rptProj.setProjNo(projNo);
		List<RptProj> list = rptProjBo.findByAll(rptProj);
		
		String xStr = "";
		String data = "";
		String data1 = "";
		for(RptProj rcd : list){
			xStr += ",'"+rcd.getRptDate()+"'";
			data += ","+new DecimalFormat("#.00").format(rcd.getRepayDay()/10000);
			data1 += ","+new DecimalFormat("#.00").format(rcd.getAmtDay()/10000);
		}
		if(xStr.length()>0){
			xStr = xStr.substring(1);
			data = data.substring(1);
			data1 = data1.substring(1);
		}
		rptParams = new RptParams();
		rptParams.setData1(xStr);
		rptParams.setData2(data);
		rptParams.setData3(data1);
		return "detail";
	}
	
	public String getReNumback() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		RptProj rptProj = new RptProj();
		rptProj.setProjNo(projNo);
		List<RptProj> list = rptProjBo.findByAllNum(rptProj);
		
		String xStr = "";
		String data = "";
		String data1 = "";
		for(RptProj rcd : list){
			xStr += ",'"+rcd.getRptDate()+"'";
			data += ","+rcd.getRepayDaycnt();
			data1 += ","+rcd.getCntDay();
		}
		if(xStr.length()>0){
			xStr = xStr.substring(1);
			data = data.substring(1);
			data1 = data1.substring(1);
		}
		rptParams = new RptParams();
		rptParams.setData1(xStr);
		rptParams.setData2(data);
		rptParams.setData3(data1);
 		return "detail";
	}
	
	public String getVirtualBal() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		RptProj rptProj = new RptProj();
		rptProj.setProjNo(projNo);
		List<RptProj> list = rptProjBo.findByAll(rptProj);
		
		String xStr = "";
		String data1 = "";
		String data2 = "";
		for(RptProj rcd : list){
			xStr += ",'"+rcd.getRptDate()+"'";
			data1 += ","+new DecimalFormat("#.00").format(rcd.getAccBal()/10000);
			data2 += ","+new DecimalFormat("#.00").format(rcd.getVirBal()/10000);
		}
		if(xStr.length()>0){
			xStr = xStr.substring(1);
			data1 = data1.substring(1);
			data2 = data2.substring(1);
		}
		rptParams = new RptParams();
		rptParams.setData1(xStr);
		rptParams.setData2(data1);
		rptParams.setData3(data2);
		return "detail";
	}

	/**
	 * @作者 DHCC-ZLC
	 * @日期 2016-8-13
	 * @方法说明：项目中账户360视图
	 * @返回参数 String
	 */
	public String account_getAllDetail() {
		ActionContext.initialize(ServletActionContext.getRequest(), 
				ServletActionContext.getResponse());
		//projBase = new ProjBase();
		//projBase.setProjNo(projNo);
		//cifPers.setEcif_no(ecif_no);
		//projBase = projBaseBo.getById(projBase);
		//cifType = cifPersInf.getCifType();
		//if_agri = cifPers.getIf_agri();
		return "all";
	}
	public String getAccountBal() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		RptProj rptProj = new RptProj();
		rptProj.setProjNo(projNo);
		List<RptProj> rptProjList = rptProjBo.getAccountBal(rptProj);
		
		String xStr = "";
		String data1 = "";
		String data2 = "";
		String data3 = "";
		String data4 = "";
		for(RptProj rcd : rptProjList){
			xStr += ",'"+rcd.getRptDate()+"'";
			data1 += ","+new DecimalFormat("#0.00").format(rcd.getAccBal()/10000);
			data2 += ","+new DecimalFormat("#0.00").format(rcd.getAmtDay()/10000);
			data3 += ","+new DecimalFormat("#0.00").format((rcd.getRepayDay()+rcd.getIntstDay()+rcd.getFineDay())/10000);
			data4 += ","+new DecimalFormat("#0.00").format(rcd.getVirBal()/10000);
		}
		if(xStr.length()>0){
			xStr = xStr.substring(1);
			data1 = data1.substring(1);
			data2 = data2.substring(1);
			data3 = data3.substring(1);
			data4 = data4.substring(1);
		}
		rptParams = new RptParams();
		rptParams.setData1(xStr);
		rptParams.setData2(data1);
		rptParams.setData3(data2);
		rptParams.setData4(data3);
		rptParams.setData5(data4);
		return "detail";
	}
	public String getAccountVirBal() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		RptProj rptProj = new RptProj();
		rptProj.setProjNo(projNo);
		List<RptProj> rptProjList = rptProjBo.getAccountBal(rptProj);
		
		String xStr = "";
		String data1 = "";
		String data2 = "";
		String data3 = "";
		String data4 = "";
		for(RptProj rcd : rptProjList){
			xStr += ",'"+rcd.getRptDate()+"'";
			data1 += ","+new DecimalFormat("#0.00").format(rcd.getAccBal()/10000);
			data2 += ","+new DecimalFormat("#0.00").format(rcd.getAmtTot()/10000);
			data3 += ","+new DecimalFormat("#0.00").format((rcd.getRepayTot()+rcd.getIntstTot()+rcd.getFineTot())/10000);
			data4 += ","+new DecimalFormat("#0.00").format(rcd.getVirBal()/10000);
		}
		if(xStr.length()>0){
			xStr = xStr.substring(1);
			data1 = data1.substring(1);
			data2 = data2.substring(1);
			data3 = data3.substring(1);
			data4 = data4.substring(1);
		}
		rptParams = new RptParams();
		rptParams.setData1(xStr);
		rptParams.setData2(data1);
		rptParams.setData3(data2);
		rptParams.setData4(data3);
		rptParams.setData5(data4);
		return "detail";
	}
	/**
	 * @作者 DHCC-ZLC
	 * @日期 2016-8-15
	 * @方法说明：
	 * @返回参数 String
	 */
	public String getManagerDetailTab() throws Exception{
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		tabList = new ArrayList();
		String[] tab = null;
	    
		tab = new String[2];
		tab[0] = "收益情况";
		tab[1] = "ProjBaseAction_getIncome.action?projNo=" + projNo+ "&query="+ query + "";
	    tabList.add(tab);
   
	    tab = new String[2];
		tab[0] = "累计发放金额";
		tab[1] = "ProjBaseAction_getLoanAmt.action?projId=" + projId+"&projNo=" + projNo+ "&query="+ query + "";
	    tabList.add(tab);
	    
	    tab = new String[2];
	    tab[0] = "每日发放金额";
	    tab[1] = "ProjBaseAction_getAmtDay.action?projId=" + projId+"&projNo=" + projNo+ "&query="+ query + "";
	    tabList.add(tab);

	    tab = new String[2];
		tab[0] = "账户余额";
		tab[1] = "ProjBaseAction_getVirtualBal.action?projId=" + projId+"&projNo=" + projNo+ "&query="+ query + "";
	    tabList.add(tab);	    
	    
//	    tab = new String[2];
//		tab[0] = "经营指标";
//		tab[1] = "ProjBaseAction_getBusniess_Indicators.action?projId=" + projId+ "&projNo=" + projNo+ "&query="+ query + "";
//	    tabList.add(tab);	    

		return "tab";
	}
	public String getBusniess_Indicators() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		RptProj rptProj = new RptProj();
		rptProj.setProjNo(projNo);
		List<RptProj> list = rptProjBo.findByAll(rptProj);
		
		String xStr = "";
		String data2 = "";//逾期率
		String data3 = "";//代偿率
		String data4 = "";//回购率
		String data5 = "";//通过率
		String data6 = "";//扣失率
		String data7 = "";//退货率
		for(RptProj rcd : list){
			//判断分母是否为空或0
			if(!"".equals(rcd.getAmtTot())&&rcd.getAmtTot()!=0){
				xStr += ",'"+rcd.getRptDate()+"'";
				data2 += ","+new DecimalFormat("#0.000#").format(rcd.getOverBal()/rcd.getAmtTot());
				data3 += ","+new DecimalFormat("#0.000#").format(rcd.getCompAmt()/rcd.getAmtTot());
				data4 += ","+new DecimalFormat("#0.000#").format(rcd.getRebuyAmt()/rcd.getAmtTot());
				data5 += ","+new DecimalFormat("#0.000#").format(rcd.getPassMonrate());
				data6 += ","+new DecimalFormat("#0.000#").format(rcd.getFailMonrate());
				data7 += ","+new DecimalFormat("#0.000#").format(rcd.getCancleRate());
			}
		}
		if(xStr.length()>0){
			xStr = xStr.substring(1);
			data2 = data2.substring(1);
			data3 = data3.substring(1);
			data4 = data4.substring(1);
		}
		rptParams = new RptParams();
		rptParams.setData1(xStr);
		rptParams.setData2(data2);
		rptParams.setData3(data3);
		rptParams.setData4(data4);
		return "detail";
	}
	
	public ProjBase getProjBase() {
		return projBase;
	}
	public void setProjBase(ProjBase  projBase) {
		this.projBase = projBase;
	}
	public List<ProjBase> getProjBaseList() {
		return projBaseList;
	}
	public void setProjBaseList(List<ProjBase> projBaseList) {
		this.projBaseList = projBaseList;
	}
	public ProjBaseBo getProjBaseBo() {
		return projBaseBo;
	}
	public void setProjBaseBo(ProjBaseBo projBaseBo) {
		this.projBaseBo = projBaseBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormproj0002() {
		return formproj0002;
	}
	public void setFormproj0002(FormData formproj0002) {
		this.formproj0002 = formproj0002;
	}
	public FormData getFormproj0001() {
		return formproj0001;
	}
	public void setFormproj0001(FormData formproj0001) {
		this.formproj0001 = formproj0001;
	}
	public void setProjNo(String projNo){
		this.projNo = projNo;
	}		
	public String getProjNo(){
		return projNo;
	}

	public List getTabList() {
		return tabList;
	}
	public void setTabList(List tabList) {
		this.tabList = tabList;
	}
	public FormData getFormproj0013() {
		return formproj0013;
	}
	public void setFormproj0013(FormData formproj0013) {
		this.formproj0013 = formproj0013;
	}
	public String getAcctType() {
		return acctType;
	}
	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}
	public String getIsFlag() {
		return isFlag;
	}
	public void setIsFlag(String isFlag) {
		this.isFlag = isFlag;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public ProjMang getProjMang() {
		return projMang;
	}
	public void setProjMang(ProjMang projMang) {
		this.projMang = projMang;
	}
	public List<ProjMang> getProjMangList() {
		return projMangList;
	}
	public void setProjMangList(List<ProjMang> projMangList) {
		this.projMangList = projMangList;
	}
	public ProjMangBo getProjMangBo() {
		return projMangBo;
	}
	public void setProjMangBo(ProjMangBo projMangBo) {
		this.projMangBo = projMangBo;
	}
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
	public String getBrNo() {
		return brNo;
	}
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}
	public FormService getFormService() {
		return formService;
	}
	public void setFormService(FormService formService) {
		this.formService = formService;
	}
	public String getBrName() {
		return brName;
	}
	public void setBrName(String brName) {
		this.brName = brName;
	}
	public RptParams getRptParams() {
		return rptParams;
	}
	public void setRptParams(RptParams rptParams) {
		this.rptParams = rptParams;
	}
	public RptProjBo getRptProjBo() {
		return rptProjBo;
	}
	public void setRptProjBo(RptProjBo rptProjBo) {
		this.rptProjBo = rptProjBo;
	}
	public FundProvProjBo getFundProvProjBo() {
		return fundProvProjBo;
	}
	public void setFundProvProjBo(FundProvProjBo fundProvProjBo) {
		this.fundProvProjBo = fundProvProjBo;
	}
	public ProjRel getProjRel() {
		return projRel;
	}
	public void setProjRel(ProjRel projRel) {
		this.projRel = projRel;
	}
	public ProjRel getProjRel1() {
		return projRel1;
	}
	public void setProjRel1(ProjRel projRel1) {
		this.projRel1 = projRel1;
	}
	public ProjRel getProjRel2() {
		return projRel2;
	}
	public void setProjRel2(ProjRel projRel2) {
		this.projRel2 = projRel2;
	}
	public String getProjNatu() {
		return projNatu;
	}
	public void setProjNatu(String projNatu) {
		this.projNatu = projNatu;
	}
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	public LnPact getLnPact() {
		return lnPact;
	}
	public void setLnPact(LnPact lnPact) {
		this.lnPact = lnPact;
	}
	public LnPactBo getLnPactBo() {
		return lnPactBo;
	}
	public void setLnPactBo(LnPactBo lnPactBo) {
		this.lnPactBo = lnPactBo;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	
	public RptFundDailyBo getRptFundDailyBo() {
		return rptFundDailyBo;
	}
	public void setRptFundDailyBo(RptFundDailyBo rptFundDailyBo) {
		this.rptFundDailyBo = rptFundDailyBo;
}
	public String getBackSts() {
		return backSts;
	}
	public void setBackSts(String backSts) {
		this.backSts = backSts;
	}

	public String getProjSts() {
		return projSts;
	}
	public void setProjSts(String projSts) {
		this.projSts = projSts;
	}

	public List<ProjBase> getProjBaseList1() {
		return projBaseList1;
	}
	public void setProjBaseList1(List<ProjBase> projBaseList1) {
		this.projBaseList1 = projBaseList1;
	}
	public ProjAcctBo getProjAcctBo() {
		return projAcctBo;
	}
	public void setProjAcctBo(ProjAcctBo projAcctBo) {
		this.projAcctBo = projAcctBo;
	}
	public ProjParmBo getProjParmBo() {
		return projParmBo;
	}
	public void setProjParmBo(ProjParmBo projParmBo) {
		this.projParmBo = projParmBo;
	}
	public CorpBaseBo getCorpBaseBo() {
		return corpBaseBo;
	}
	public void setCorpBaseBo(CorpBaseBo corpBaseBo) {
		this.corpBaseBo = corpBaseBo;
	}
}