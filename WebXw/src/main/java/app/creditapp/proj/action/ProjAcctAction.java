package app.creditapp.proj.action;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

import app.creditapp.corp.bo.CorpAcctBo;
import app.creditapp.corp.entity.CorpAcct;
import app.creditapp.fund.bo.FundDetailBo;
import app.creditapp.fund.bo.FundPayPlanBo;
import app.creditapp.fund.bo.FundRepayPlanBo;
import app.creditapp.fund.entity.FundDetail;
import app.creditapp.fund.entity.FundPayPlan;
import app.creditapp.fund.entity.FundRepayPlan;
import app.creditapp.inf.bo.WsBaseBo;
import app.creditapp.inf.entity.AllocateRegWsIn;
import app.creditapp.proj.bo.ProjAcctBo;
import app.creditapp.proj.bo.ProjBaseBo;
import app.creditapp.proj.bo.RptProjBo;
import app.creditapp.proj.entity.FutureCashFlow;
import app.creditapp.proj.entity.ProjAcct;
import app.creditapp.proj.entity.ProjBase;
import app.creditapp.proj.entity.RptProj;
import app.util.DateUtil;
import app.util.User;
import app.util.toolkit.Ipage;

/**
 * Title: ProjAcctAction.java Description:
 * 
 **/
public class ProjAcctAction extends BaseFormBean {

	// 页面传值
	private ProjAcct projAcct;
	private ProjBase projBase;
	private FutureCashFlow futureCashFlow;

	private List tabList;
	
	private List<CorpAcct> corpAcctList;
	private List<ProjAcct> projAcctList;

	// 注入ProjAcctBo
	private ProjAcctBo projAcctBo;
	private ProjBaseBo projBaseBo;
	private CorpAcctBo corpAcctBo;
	private FundDetailBo fundDetailBo;
	private WsBaseBo wsBaseBo;
	private FundPayPlanBo fundPayPlanBo;
	private FundRepayPlanBo fundRepayPlanBo;

	private RptProjBo rptProjBo;
	private String cardSts;
	private HttpServletRequest request;

	private String query;
	private String txDate;
	private String upOpno;
	private String deptNo;
	private String acctDesc;
	private String acctId;
	private String acctName;
	private String upDate;
	private String cardNo;
	private Double acctBal;
	private String acctUse;
	private String acctType;
	private String projName;
	private String opNo;
	private String filler;
	private String projNo;//项目编号
	private String opnBank;
	private String acctNo;//账户号
	private String projId;
	private FormData formproj0007;
	private FormData formproj0015;
	private FormData formproj0008;
	private FormData formproj0011;
	private FormData formproj0016;
	private FormData formproj0017;
	private FormData formproj0020;
	private FormData formproj0030;
	private FormData formproj0028;
	private double txAmt;
	private String isFlag;
	private String endDate;
	private String CalCulType;
	// 页面返回按钮判断参数
	private String backSts;
	// 收拨款接口
	// 主信息对象
	private String id;// 唯一标识本条记录(唯一标识，外围系统通过该ID可获得生成的拨款台账主信息)
	private String businessflag;// 业务标示1:账户拨款，2:信托付自营，3:账户收款，4:自营代垫，5:信托付保障基金，6:多账户拨款
	private String autoSyncFlag;// 自动同步辅助核算标示
	private String projectid;// 项目ID
	private String ddtype;// 代垫类型(条件必填，如果交易类型为自营代垫则必填 1:垫营业税及附加2:垫费)
	private String transdate;// 业务日期(拨款日期) yyyy-MM-dd
	private String transreason;// 拨款依据
	private String purpose;// 用途
	private String memo;// 摘要
	private String userCode;// 创建人CODE
	private String staffapp;// 拨款对象 (自营代垫必填：0：外部账户 1：个人员工户2：信托户)
	// 明细信息对象
	private String amount;// 交易金额(2位小数)
	private String bktranstypecode;// 拨款的交易类型编码(拨款时必填)
	private String sktranstypecode;// 收款的交易类型编码 (条件必填，信托付自营或者收款交易时必填)
	private String feetypecode;// 费用类型编码(条件必填,费用类交易类型时候必填)
	private String taxtypecode;// 税目类型编码(条件必填,税目类交易时，必填 )
	private String customid;// 收款人ID
	private String accountid;// 资金运用方账号ID/借据ID(条件必填，当明细交易类型存在账户类交易类型时候必填)
	// private String opbankacntid ;//对方银行ID
	private String opbankacntno;// 对方银行账号(条件必填，拨款、多帐户拨款、信托付自营、自营代垫(拨款对象为外部账户或信托专户)必填、最多100个字符)
	private String opbankname;// 对方银行账号开户行(条件必填,拨款、多帐户拨款、信托付自营、自营代垫(拨款对象为外部账户或信托专户)必填、最多100个字符)
	private String opbankacntname;// 对方银行账号户名(条件必填,拨款、多帐户拨款、信托付自营、自营代垫(拨款对象为外部账户或信托专户)必填、最多100个字符)
	private String opbankprovince;// 开户行所属省(条件必填,拨款、多帐户拨款、信托付自营必填、最多100个字符)
	private String opbankcity;// 开户行所属市(条件必填,拨款、多帐户拨款、信托付自营必填、最多100个字符)
	private String repaytypeid;// 还款方式
								// 条件必填，在内部账户中的业务分类为贷款的，且明细交易类型中为本金的，01正常收回，02资产重组，03资产剥离，04以资抵债，05担保代偿，06核损核销，07政策性还款，08债转股、09转出。
	private String appusercode;// 内部人员(条件必填:自营代垫拨款对象为个人时候必填)
	private String tradeCode;
	private String project;
	private String customer;
	private String industry;

	private FormService formService = new FormService();

	public ProjAcctAction() {
		query = "";
	}

	/**
	 * 分页查询
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		formproj0007 = formService.getFormData("proj0007");
		projAcct = new ProjAcct();
		getFormValue(formproj0007);
		setObjValue(formproj0007, projAcct);
		projAcct.setAcctType(acctType);
		projAcct.setProjNo(projNo);
		Ipage ipage = this.getIpage();
		projAcctList = (List) projAcctBo.findByPage(ipage, projAcct)
				.getResult();
		return "list";
	}

	public String findByPageForPop() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		formproj0015 = formService.getFormData("proj0015");
		projAcct = new ProjAcct();
		getFormValue(formproj0015);
		setObjValue(formproj0015, projAcct);
		projAcct.setProjNo(projNo);
		Ipage ipage = this.getIpage();
		projAcctList = (List) projAcctBo.findByPageForPop(ipage, projAcct)
				.getResult();
		return "list";
	}

	/**
	 * 分页查询
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findByPageToProj() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());

		if (projNo == null || "".equals(projNo)) {
			this.addActionMessage("请先在  项目信息 中补录小微项目编号！");
			return "   ";
		} else {
			formproj0007 = formService.getFormData("proj0007");
			projAcct = new ProjAcct();
			projAcct.setProjNo(projNo);
			getFormValue(formproj0007);
			setObjValue(formproj0007, projAcct);
			projAcct.setAcctType(acctType);
			projAcct.setProjNo(projNo);
			projAcct.setQuery(query);
			Ipage ipage = this.getIpage();
			projAcctList = (List) projAcctBo.findByPage(ipage, projAcct)
					.getResult();
			return "list";
		}

	}

	/**
	 * 分页查询
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findByPageToProj_Read() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());

		if (projNo == null || "".equals(projNo)) {
			this.addActionMessage("请先在  项目信息 中补录小微项目编号！");
			return "   ";
		} else {
			// formproj0007 = formService.getFormData("proj0007");
			projAcct = new ProjAcct();
			// projAcct.setProjNo(projNo);
			// getFormValue(formproj0007);
			// setObjValue(formproj0007, projAcct);
			// projAcct.setAcctType(acctType);
			projAcct.setProjNo(projNo);
			// projAcct.setQuery(query);
			Ipage ipage = this.getIpage();
			projAcctList = (List) projAcctBo.findByPage(ipage, projAcct)
					.getResult();
			return "list_Read";
		}
	}

	/**
	 * 分页查询虚拟账户信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findByPageForVa() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		formproj0011 = formService.getFormData("proj0011");
		projAcct = new ProjAcct();
		getFormValue(formproj0011);
		setObjValue(formproj0011, projAcct);
		// projAcct.setAcctType(acctType);
		// projAcct.setProjNo(projNo);
		projAcct.setLoginid(User.getLoginIdByAuth(this.getHttpRequest())); // 从session中获取权限
		Ipage ipage = this.getIpage();
		projAcctList = (List) projAcctBo.findByPage(ipage, projAcct)
				.getResult();
		return "list";
	}

	/**
	 * 获取新增页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());

		formproj0008 = formService.getFormData("proj0008");

		projAcct = new ProjAcct();
		projBase = new ProjBase();

		if (projNo != null && !projNo.equals("")) {
			projBase.setProjNo(projNo);
			projBase = projBaseBo.getById(projBase);
			projAcct.setProjNo(projNo);
			projAcct.setProjName(projBase.getProjName());
		}

		projAcct.setDeptNo(User.getBrno(ServletActionContext.getRequest()));// 登记部门
		projAcct.setOpNo(User.getLoginid(ServletActionContext.getRequest()));// 操作员
		projAcct.setTxDate(User.getSys_date(ServletActionContext.getRequest()));// 登记日期
		// projAcct.setUpDate(User.getSys_date(ServletActionContext.getRequest()));//修改日期
		// projAcct.setUpOpno(User.getLoginid(ServletActionContext.getRequest()));//修改人员
		getObjValue(formproj0008, projAcct);
		return "input";
	}
	/**
	 * 获取新增页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String inputAcct() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());

		formproj0028 = formService.getFormData("proj0028");

		projAcct = new ProjAcct();
		projBase = new ProjBase();

		if (projNo != null && !projNo.equals("")) {
			projBase.setProjNo(projNo);
			projBase = projBaseBo.getById(projBase);
			projAcct.setProjNo(projNo);
			projAcct.setProjName(projBase.getProjName());
		}

		projAcct.setDeptNo(User.getBrno(ServletActionContext.getRequest()));// 登记部门
		projAcct.setOpNo(User.getLoginid(ServletActionContext.getRequest()));// 操作员
		projAcct.setTxDate(User.getSys_date(ServletActionContext.getRequest()));// 登记日期
		// projAcct.setUpDate(User.getSys_date(ServletActionContext.getRequest()));//修改日期
		// projAcct.setUpOpno(User.getLoginid(ServletActionContext.getRequest()));//修改人员
		getObjValue(formproj0028, projAcct);
		return "input";
	}
	/**
	 * 新增保存操作
	 *改成新增专户的方法
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		//formproj0008 = formService.getFormData("proj0008");    此方法中原来是0008
		
		formproj0028 = formService.getFormData("proj0028");

		
		getFormValue(formproj0028);
		projAcct = new ProjAcct();
		setObjValue(formproj0028, projAcct);
		projAcct.setDeptNo(User.getBrno(ServletActionContext.getRequest()));// 登记部门
		projAcct.setOpNo(User.getLoginid(ServletActionContext.getRequest()));// 操作员
		projAcct.setTxDate(User.getSys_date(ServletActionContext.getRequest()));// 登记日期
		// projAcct.setUpDate(User.getSys_date(ServletActionContext.getRequest()));//修改日期
		// projAcct.setUpOpno(User.getLoginid(ServletActionContext.getRequest()));//修改人员
	//	int count = projAcctBo.getCountForX(projAcct);
		
		
		int count = projAcctBo.getCountForProj(projAcct);

		//根据 渠道 和 项目编号进行判断 是否已经存在 此传户，存在的话就不能进行新增操作
		if(count!=0){
			this.addActionError("此渠道的专户已经存在！");
			getObjValue(formproj0028, projAcct);
			return inputAcct();
		}else{
			String s = projAcctBo.getProjId(projAcct);
			projAcct.setProjId(s);
			projAcct.setCardSts("01");
//			String res = acctSynMes(projAcct,"1");
//			if("1".equals(res)){
				projAcctBo.insert(projAcct);
				getObjValue(formproj0028, projAcct);
				this.addActionMessage("新增成功,专户添加成功!");
//			}else{
//				this.addActionMessage("新增失败，"+res);
//			}
		}
		return findByPageToProj_Read();
	}
	/**
	 * 生效
	 * 
	 * @return
	 * @throws Exception
	 */
	public String active() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		formproj0007 = formService.getFormData("proj0007");
		projAcct = new ProjAcct();
		projAcct.setAcctId(acctId);
		projAcct = projAcctBo.getById(projAcct);
		if ("01".equals(cardSts)) {
			String res = acctSynMes(projAcct,"0");
			if("1".equals(res)){
				projAcct.setCardSts("02");
				projAcct.setSendSts("0");
				projAcctBo.updateSts(projAcct);
				this.addActionMessage("已失效");
			}else{
				this.addActionMessage("操作失败，账户未失效，"+res);
			}
		} else {
			int count = projAcctBo.getCountForX(projAcct);
			//根据 渠道 和 项目编号进行判断 是否已经存在 此虚拟户，存在的话就不能进行新增操作
			if(count!=0){
				this.addActionError("该项目已有一条生效的虚拟帐户！");
				return findByPageToProj_Read();
			}else{
				String res = acctSynMes(projAcct,"3");
				if("1".equals(res)){
					projAcct.setCardSts("01");
					projAcct.setSendSts("1");
					projAcctBo.updateSts(projAcct);
					this.addActionMessage("生效成功");
				}else{
					this.addActionMessage("操作失败，账户未生效，"+res);
				}
			}
		}
		// projAcctBo.update(projAcct);
		// this.addActionMessage("生效成功");
		projAcct = new ProjAcct();
		projAcct.setAcctType(acctType);
		projAcct.setProjNo(projNo);
		Ipage ipage = this.getIpage();
		projAcctList = (List) projAcctBo.findByPage(ipage, projAcct)
				.getResult();
		return "list";
	}

	/**
	 * 失效
	 * 
	 * @return
	 * @throws Exception
	 */
	public String inactive() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		formproj0007 = formService.getFormData("proj0007");
		projAcct = new ProjAcct();
		projAcct.setAcctId(acctId);
		projAcct = projAcctBo.getById(projAcct);
		projAcct.setSendSts("0");
		projAcct.setCardSts("02");
		projAcctBo.update(projAcct);
		this.addActionMessage("已失效");
		projAcct = new ProjAcct();
		Ipage ipage = this.getIpage();
		projAcctList = (List) projAcctBo.findByPage(ipage, projAcct)
				.getResult();
		return "list";
	}

	/**
	 * 修改保存操作
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		formproj0008 = formService.getFormData("proj0008");
		getFormValue(formproj0008);
		projAcct = new ProjAcct();
			setObjValue(formproj0008, projAcct);
			projAcct.setUpDate(User.getSys_date(ServletActionContext.getRequest()));// 修改日期
			projAcct.setUpOpno(User.getLoginid(ServletActionContext.getRequest()));// 修改人员
			String s = projAcctBo.getProjId(projAcct);
			projAcct.setProjId(s);
//			String res = acctSynMes(projAcct,"2");
//			if("1".equals(res)){
				projAcctBo.update(projAcct);
				getObjValue(formproj0008, projAcct);
				this.addActionMessage("账户修改成功");
//			}else{
//				this.addActionMessage("账户修改失败，"+res);
//			}
			return "detail";
	}
	
	public String updateForZ() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		formproj0020 = formService.getFormData("proj0020");
		getFormValue(formproj0020);
		projAcct = new ProjAcct();
			setObjValue(formproj0020, projAcct);
			projAcct.setUpDate(User.getSys_date(ServletActionContext.getRequest()));// 修改日期
			projAcct.setUpOpno(User.getLoginid(ServletActionContext.getRequest()));// 修改人员
			projAcctBo.update(projAcct);
			getObjValue(formproj0020, projAcct);
			this.addActionMessage("操作成功");
			return "detailForZ";
	}
	
	public String updateForFK() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		formproj0030 = formService.getFormData("proj0030");
		getFormValue(formproj0030);
		projAcct = new ProjAcct();
			setObjValue(formproj0030, projAcct);
			projAcct.setUpDate(User.getSys_date(ServletActionContext.getRequest()));// 修改日期
			projAcct.setUpOpno(User.getLoginid(ServletActionContext.getRequest()));// 修改人员
			projAcctBo.update(projAcct);
			getObjValue(formproj0030, projAcct);
			this.addActionMessage("操作成功");
			return "detailForFK";
	}

	public String acctSynMes() throws Exception {
		
		ProjAcct projAcct = new ProjAcct();
		projAcct.setAcctId(acctId);
		projAcct = projAcctBo.getById(projAcct);
		
		projBase = new ProjBase();
		projBase.setProjNo(projNo);
		projBase = projBaseBo.getByIdForProjNo(projBase);
		/*
		AcctSendDetail acctSendDetail = new AcctSendDetail();
		
		acctSendDetail.setAccountName(projAcct.getAcctName());
		acctSendDetail.setAccountNo(projAcct.getAcctNo());
		acctSendDetail.setCustName(projBase.getProjName());
		acctSendDetail.setCustNo(projBase.getProjNo());
		acctSendDetail.setTenantNo(projBase.getTenantNo());
		
		ResultMes resultMes = SendZFService.createAcctXMLAndSend(acctSendDetail, projBase.getBrNo());
		
		if("1".equals(resultMes.getResultCode())){
			this.addActionMessage("账户发送成功！");
			projAcct.setCardSts("01");
			projAcct.setSendSts("02");
			projAcctBo.update(projAcct);
		}else{
			this.addActionMessage("账户发送失败！"+resultMes.getResultMsg());
		}
		*/
		return findByPageToProj_Read();
	}
	
	public String acctSynMes(ProjAcct projAcct,String sendSts) throws Exception {
		
		return findByPageToProj_Read();
	}
	
//	// 同步发送
//	public String acctSynMes() throws Exception {
//		ProjAcct projAcct = new ProjAcct();
//		projAcct.setAcctId(acctId);
//		projAcct = projAcctBo.getById(projAcct);
//		String projNo = projAcct.getProjNo();//获取项目编号
//		if (projAcct == null || "".equals(projAcct)) {
//			this.addActionError("项目编号‘" + projNo + "’对应的账号不存在！");
//			return findByPageToProj_Read();
//		}
//		String sendSts = projAcct.getSendSts();
//		if ("02".equals(projAcct.getCardSts())) {
//			if ("1".equals(sendSts) || "2".equals(sendSts)) {
//				this.addActionError("项目账号‘" + projAcct.getAcctNo()
//						+ "’已失效！不允许开户或修改");
//				return findByPageToProj_Read();
//			}
//		}
//		// 报文头
//		SimpleDateFormat timeFormater = new SimpleDateFormat(
//				"yyyy-MM-dd HH:mm:ss");
//		AcctSynHead acctSynHead = new AcctSynHead();
//		acctSynHead.setRequestType("A100002");
//		acctSynHead.setUUID("00014");// 交易流水号
//		acctSynHead.setComId("0001");
//		acctSynHead.setComIP("211.168.22.111");
//		acctSynHead.setFrom("1881632261");
//		acctSynHead.setSystemName("16133001");
//		acctSynHead.setSystemPassword("111111");
//		acctSynHead.setSendTime(timeFormater.format(new Date()));
//		acctSynHead.setAsyn("0");
//		acctSynHead.setReturnUrl("http://ufc.esb.cost.com/cooperation/");
//		acctSynHead.setSigned("");
//		acctSynHead.setItSystem("0");
//		String headXml = XMLUtil.createHead(acctSynHead); // 头文件
//		// 报文实体
//		AcctSynDetail acctSynDetail = new AcctSynDetail();
//		acctSynDetail.setAccountNo(projAcct.getAcctNo());// 账户号
//		acctSynDetail.setOperaType(sendSts);
//		if(projAcct.getAcctName()==null||"".equals(projAcct.getAcctName())){
//			this.addActionError("账户名称不可为空！");
//			return findByPageToProj_Read();
//		}
//		acctSynDetail.setAccountName(projAcct.getAcctName()); // 账户名称
//		if(projAcct.getProjId()==null||"".equals(projAcct.getProjId())){
//			this.addActionError("父账户不可为空！");
//			return findByPageToProj_Read();
//		}
//		acctSynDetail.setBelongTo(projAcct.getProjId());// 父账户
//		if(projAcct.getOrgNo()==null||"".equals(projAcct.getOrgNo())){
//			this.addActionError("机构号不可为空！");
//			return findByPageToProj_Read();
//		}
//		acctSynDetail.setOrgNo(projAcct.getOrgNo());// 机构号
//		//账户类型
//		if("02".equals(projAcct.getAcctType())){
//			acctSynDetail.setAccountType("01");
//		}else{
//			this.addActionError("只允许虚拟户同步发送账户数据！");
//			return findByPageToProj_Read();
//		}
//		acctSynDetail.setMaxAmount(""); // 单笔限额
//		if(projAcct.getCardChn()==null||"".equals(projAcct.getCardChn())){
//			this.addActionError("虚拟账户渠道不可为空！");
//			return findByPageToProj_Read();
//		}
//		acctSynDetail.setPayChannel(projAcct.getCardChn()); // 交易渠道代码
//		acctSynDetail.setProvince(projAcct.getBankProv());// 开户行所在省
//		acctSynDetail.setCity(projAcct.getBankCity());// 开户行所在市
//		acctSynDetail.setBankName(projAcct.getOpnBank());// 开户行名称
//		acctSynDetail.setSettlementFlag(projAcct.getSellFlag());// 联行号详细
//		String bodyXml = XMLUtil.createBody(acctSynDetail);// 转换为XML
//		String xml = headXml + bodyXml + "</Package>";
//		System.out.println(xml);
//		// 调用接口
//		Date d1 = new Date();
//		ZfTradeService ZfTradeService = (ZfTradeService) SourceTemplate
//				.getSpringContextInstance().getBean("zfTradeService");
//		String result = ZfTradeService.doAction("A100002", xml);
//		Date d2 = new Date();
//		System.out.println(result);
//
//		Document document = null;
//		try {
//			document = DocumentHelper.parseText(result);
//		} catch (DocumentException e) {
//			e.printStackTrace();
//		}
//		Element root = document.getRootElement();
//		Element eHeader = root.element("Header");
//
//		Element eResultCode = eHeader.element("ResultCode");
//		String resultCode = eResultCode.getText();
//
//		Element eResultMsg = eHeader.element("ResultMsg");
//		String resultMsg = eResultMsg.getText();
//
//		String wsSts = "01";
//		if ("1".equals(resultCode)) {
//			wsSts = "02";
//			if ("1".equals(sendSts)) {
//				projAcct.setSendSts("2");
//				projAcctBo.updateSts(projAcct);
//				this.addActionError("项目账户开户成功！");
//			} else if ("2".equals(sendSts)) {
//				this.addActionError("项目账户修改成功！");
//			} else if ("0".equals(sendSts)) {
//				projAcct.setSendSts("1");
//				projAcctBo.updateSts(projAcct);
//				this.addActionError("项目账户注销成功！");
//			}
//		} else {
//			this.addActionError("数据同步失败，支付平台返回：" + resultMsg + "!");
//		}
//		//查询合作机构编号
//		projBase = new ProjBase();
//		projBase.setProjNo(projNo);
//		projBase = projBaseBo.getById(projBase);
//		
//		WsBase wsBase = new WsBase();
//		SimpleDateFormat wsTimeFormater = new SimpleDateFormat(
//				"yyyyMMdd HH:mm:ss");
//		wsBase.setWsTime(wsTimeFormater.format(d1));
//		wsBase.setRespTime(wsTimeFormater.format(d2));
//		wsBase.setTxCode("A100002");
//		wsBase.setWsSerial("00014");
//		wsBase.setRespCode(resultCode);
//		wsBase.setRespDesc("支付平台返回：" + resultMsg + "");
//		wsBase.setWsSts(wsSts);
//		wsBase.setReqContent(xml);
//		wsBase.setRespContent(result);
//		wsBase.setBrNo(projBase.getBrNo());
//		wsBaseBo.insert(wsBase);
//		return findByPageToProj_Read();
//	}
//	// 同步发送
//	public String acctSynMes(ProjAcct projAcct,String sendSts) throws Exception {
//		String projNo = projAcct.getProjNo();//获取项目编号
//		// 报文头
//		SimpleDateFormat timeFormater = new SimpleDateFormat(
//				"yyyy-MM-dd HH:mm:ss");
//		AcctSynHead acctSynHead = new AcctSynHead();
//		acctSynHead.setRequestType("A100002");
//		acctSynHead.setUUID("00014");// 交易流水号
//		acctSynHead.setComId("0001");
//		acctSynHead.setComIP("211.168.22.111");
//		acctSynHead.setFrom("1881632261");
//		acctSynHead.setSystemName("16133001");
//		acctSynHead.setSystemPassword("111111");
//		acctSynHead.setSendTime(timeFormater.format(new Date()));
//		acctSynHead.setAsyn("0");
//		acctSynHead.setReturnUrl("http://ufc.esb.cost.com/cooperation/");
//		acctSynHead.setSigned("");
//		acctSynHead.setItSystem("0");
//		String headXml = XMLUtil.createHead(acctSynHead); // 头文件
//		// 报文实体
//		AcctSynDetail acctSynDetail = new AcctSynDetail();
//		acctSynDetail.setAccountNo(projAcct.getAcctNo());// 账户号
//		acctSynDetail.setOperaType(sendSts);
//		if(projAcct.getAcctName()==null||"".equals(projAcct.getAcctName())){
//			return "账户名称不可为空！";
//		}
//		acctSynDetail.setAccountName(projAcct.getAcctName()); // 账户名称
//		if(projAcct.getProjId()==null||"".equals(projAcct.getProjId())){
//			return "父账户不可为空！";
//		}
//		acctSynDetail.setBelongTo(projAcct.getProjId());// 父账户
//		if(projAcct.getOrgNo()==null||"".equals(projAcct.getOrgNo())){
//			return "机构号不可为空！";
//		}
//		acctSynDetail.setOrgNo(projAcct.getOrgNo());// 机构号
//		//账户类型
//		if("02".equals(projAcct.getAcctType())){
//			acctSynDetail.setAccountType("01");
//		}else{
//			return "只允许虚拟户同步发送账户数据！";
//		}
//		acctSynDetail.setMaxAmount(""); // 单笔限额
//		if(projAcct.getCardChn()==null||"".equals(projAcct.getCardChn())){
//			return "虚拟账户渠道不可为空！";
//		}
//		acctSynDetail.setPayChannel(projAcct.getCardChn()); // 交易渠道代码
//		acctSynDetail.setProvince(projAcct.getBankProv());// 开户行所在省
//		acctSynDetail.setCity(projAcct.getBankCity());// 开户行所在市
//		acctSynDetail.setBankName(projAcct.getOpnBank());// 开户行名称
//		acctSynDetail.setSettlementFlag(projAcct.getSellFlag());// 联行号详细
//		String bodyXml = XMLUtil.createBody(acctSynDetail);// 转换为XML
//		String xml = headXml + bodyXml + "</Package>";
//		System.out.println(xml);
//		// 调用接口
//		Date d1 = new Date();
//		ZfTradeService ZfTradeService = (ZfTradeService) SourceTemplate
//				.getSpringContextInstance().getBean("zfTradeService");
//		String result = ZfTradeService.doAction("A100002", xml);
//		Date d2 = new Date();
//		System.out.println(result);
//
//		Document document = null;
//		try {
//			document = DocumentHelper.parseText(result);
//		} catch (DocumentException e) {
//			e.printStackTrace();
//		}
//		Element root = document.getRootElement();
//		Element eHeader = root.element("Header");
//
//		Element eResultCode = eHeader.element("ResultCode");
//		String resultCode = eResultCode.getText();
//
//		Element eResultMsg = eHeader.element("ResultMsg");
//		String resultMsg = eResultMsg.getText();
//
//		String wsSts = "01";
//		if ("1".equals(resultCode)) {
//			wsSts = "02";
//		}
//		//查询合作机构编号
//		projBase = new ProjBase();
//		projBase.setProjNo(projNo);
//		projBase = projBaseBo.getById(projBase);
//		
//		WsBase wsBase = new WsBase();
//		SimpleDateFormat wsTimeFormater = new SimpleDateFormat(
//				"yyyyMMdd HH:mm:ss");
//		wsBase.setWsTime(wsTimeFormater.format(d1));
//		wsBase.setRespTime(wsTimeFormater.format(d2));
//		wsBase.setTxCode("A100002");
//		wsBase.setWsSerial("00014");
//		wsBase.setRespCode(resultCode);
//		wsBase.setRespDesc("支付平台返回：" + resultMsg + "");
//		wsBase.setWsSts(wsSts);
//		wsBase.setReqContent(xml);
//		wsBase.setRespContent(result);
//		wsBase.setBrNo(projBase.getBrNo());
//		wsBaseBo.insert(wsBase);
//		if("1".equals(resultCode)||"0045".equals(resultCode)){
//			return "1";
//		}else{
//			return "支付平台返回：" + resultMsg + "!";
//		}
//	}
	
	
	
//   余额查询
//	public String acctBalMes() throws Exception {
//
//		ProjAcct projAcct = new ProjAcct();
//		projAcct.setAcctId(acctId);
//		projAcct = projAcctBo.getById(projAcct);
//		String projNo = projAcct.getProjNo();
//		if (projAcct == null || "".equals(projAcct)) {
//			this.addActionError("项目编号‘" + projNo + "’对应的账号不存在！");
//			return findByPageToProj_Read();
//		}
//		SimpleDateFormat timeFormater = new SimpleDateFormat(
//				"yyyy-MM-dd HH:mm:ss");
//		// 报文头
//		AcctSynHead acctSynHead = new AcctSynHead();
//		acctSynHead.setRequestType("B100002");
//		acctSynHead.setUUID("00014");// 交易流水号
//		acctSynHead.setComId("0001");
//		acctSynHead.setComIP("211.168.22.111");
//		acctSynHead.setFrom("1881632261");
//		acctSynHead.setSystemName("16133001");
//		acctSynHead.setSystemPassword("111111");
//
//		acctSynHead.setSendTime(timeFormater.format(new Date()));
//		acctSynHead.setAsyn("0");
//		acctSynHead.setReturnUrl("http://ufc.esb.cost.com/cooperation/");
//		acctSynHead.setSigned("");
//		acctSynHead.setItSystem("0");
//		String headXml = XMLUtil.createHead(acctSynHead); // 头文件
//		// 报文实体
//		AcctBalDetail acctBalDetail = new AcctBalDetail();
//		acctBalDetail.setAccountNo(projAcct.getAcctNo());// 账户号
//		if("01".equals(acctType)){
//			acctBalDetail.setPayType("02");// 交易类型专户
//			acctBalDetail.setPayChannel(""); // 交易渠道代码
//		}else{
//			if(projAcct.getCardChn()==null||"".equals(projAcct.getCardChn())){
//				this.addActionError("虚拟账户渠道不可为空！");
//				return findByPageToProj_Read();
//			}
//			acctBalDetail.setPayChannel(projAcct.getCardChn()); // 交易渠道代码
//			acctBalDetail.setPayType("01");// 交易类型第三方
//		}
//		String bodyXml = XMLUtil.createBody(acctBalDetail);// 转换为XML
//		String xml = headXml + bodyXml + "</Package>";
//		System.out.println(xml);
//		// 调用接口
//		Date d1 = new Date();
//		QueryService QueryService = (QueryService) SourceTemplate
//				.getSpringContextInstance().getBean("queryService");
//		String result = QueryService.doAction("B100002", xml);
//		Date d2 = new Date();
//		System.out.println(result);
//		Document document = null;
//		try {
//			document = DocumentHelper.parseText(result);
//		} catch (DocumentException e) {
//			e.printStackTrace();
//		}
//		Element root = document.getRootElement();
//		Element eHeader = root.element("Header");
//		Element eResultCode = eHeader.element("ResultCode");
//		String resultCode = eResultCode.getText();
//
//		Element eResultMsg = eHeader.element("ResultMsg");
//		String resultMsg = eResultMsg.getText();
//
//		String wsSts = "01";
//		if ("1".equals(resultCode)) {
//			wsSts = "02";
//			Element eBody = root.element("Body");
//			Element eBalanceInfoList = eBody.element("BalanceInfoList");
//			Element eBalanceInfo = eBalanceInfoList.element("BalanceInfo");
//			Element eBalance = eBalanceInfo.element("Balance");
//			Double money = Double.valueOf(eBalance.getText()) / 100;
//			// resultMsg = resultMsg+"账户余额= "+s+"";
//			projAcct.setAcctBal(money);
//			projAcctBo.update(projAcct);
//			this.addActionError("数据同步成功！账户余额=" + new DecimalFormat("######################.00").format(money) + "元");
//		} else {
//			this.addActionError("数据同步失败，支付平台返回：" + resultMsg + "!");
//		}
//		//查询合作机构编号
//		projBase = new ProjBase();
//		projBase.setProjNo(projNo);
//		projBase = projBaseBo.getById(projBase);
//		
//		WsBase wsBase = new WsBase();
//		SimpleDateFormat wsTimeFormater = new SimpleDateFormat(
//				"yyyyMMdd HH:mm:ss");
//		wsBase.setWsTime(wsTimeFormater.format(d1));
//		wsBase.setRespTime(wsTimeFormater.format(d2));
//		wsBase.setTxCode("B100002");
//		wsBase.setWsSerial("00014");
//		wsBase.setRespCode(resultCode);
//		wsBase.setRespDesc("支付平台返回：" + resultMsg + "");
//		wsBase.setWsSts(wsSts);
//		wsBase.setReqContent(xml);
//		wsBase.setRespContent(result);
//		wsBase.setBrNo(projBase.getBrNo());
//		wsBaseBo.insert(wsBase);
//
//		return findByPageToProj_Read();
//	}
		//查询余额
	public String serchAcctBal() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		
		ProjAcct projAcct = new ProjAcct();
		projAcct.setAcctId(acctId);
		projAcct = projAcctBo.getById(projAcct);
		
		String acctNo = projAcct.getAcctNo();
////		projAcct.setAcctId(acctId);
//		projAcct = projAcctBo.getById(projAcct);    		

		/*
		AcctSerchDetail acctSerchDetail = new AcctSerchDetail();
//		acctSerchDetail.setNo(projNo);//疑似错误代码，No为账户号，此处set了项目编号
		
		acctSerchDetail.setNo(acctNo);
//		acctSerchDetail.setQueryType("03");//根据0907下发文档，无此属性
		acctSerchDetail.setChannelNo("");
		ResultMes resultMes = SendZFService.serchAcctXML(acctSerchDetail, "");
		if ("1".equals(resultMes.getResultCode())){
			List<BalanceInfo> balanceInfoList = resultMes.getBalanceInfoList();
			for (int i=0;i<balanceInfoList.size();i++){
				BalanceInfo balanceInfo = balanceInfoList.get(i);
//				ProjAcct projAcct= new ProjAcct();
				projAcct.setAcctNo(balanceInfo.getAccountNo());
				projAcct.setAcctBal(Double.valueOf(balanceInfo.getBalance())/100);
				projAcctBo.updateAcctBal(projAcct);
			}
			this.addActionMessage("同步余额成功！");
		}else{
			this.addActionMessage("查询余额失败！"+resultMes.getResultMsg());
		}
		*/
		return findByPageToProj_Read();
	}
	/**
	 * 删除操作
	 * 
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		formproj0007 = formService.getFormData("proj0007");
		projAcct = new ProjAcct();
		projAcct.setTxDate(txDate);
		projAcct.setUpOpno(upOpno);
		projAcct.setDeptNo(deptNo);
		projAcct.setAcctDesc(acctDesc);
		projAcct.setAcctId(acctId);
		projAcct.setAcctName(acctName);
		projAcct.setUpDate(upDate);
		projAcct.setCardNo(cardNo);
		projAcct.setAcctBal(acctBal);
		projAcct.setAcctUse(acctUse);
		projAcct.setAcctType(acctType);
		projAcct.setProjName(projName);
		projAcct.setOpNo(opNo);
		projAcct.setFiller(filler);
		projAcct.setProjNo(projNo);
		projAcct.setOpnBank(opnBank);
		projAcct.setAcctNo(acctNo);
		projAcctBo.del(projAcct);
		this.addActionMessage("删除成功");
		projAcct = new ProjAcct();
		Ipage ipage = this.getIpage();
		projAcct.setAcctType(acctType);
		projAcctList = (List) projAcctBo.findByPage(ipage, projAcct)
				.getResult();
		return "list";
	}

	/**
	 * 查询操作
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		formproj0008 = formService.getFormData("proj0008");
		formproj0016 = formService.getFormData("proj0016");
		formproj0017 = formService.getFormData("proj0017");
		formproj0020 = formService.getFormData("proj0020");
		projAcct = new ProjAcct();

		projAcct.setAcctId(acctId);
		projAcct.setProjNo(projNo);
		projAcct.setAcctType(acctType);
		projAcct.setProjName(projName);
		projAcct.setUpDate(User.getSys_date(ServletActionContext.getRequest()));// 修改日期
		projAcct.setUpOpno(User.getLoginid(ServletActionContext.getRequest()));// 修改人员
		projAcct.setAcctNo(acctNo);
		projAcct = projAcctBo.getById(projAcct);
		if ("query".equals(query)) {// 用于详情中显示开户行坐在省市
			if("01".equals(projAcct.getAcctType())){
				getObjValue(formproj0017, projAcct);
			}else{
				getObjValue(formproj0016, projAcct);
			}
			
		} else {
			if("01".equals(projAcct.getAcctType())){
				getObjValue(formproj0020, projAcct);
			}else{
				getObjValue(formproj0008, projAcct);
			}
			
		}
		//判断为专户的时候，不显示  虚拟账户渠道
		if("01".equals(projAcct.getAcctType())){
			return "detailForZ";
		}else{
			return "detail";
		}
		
	}
	/**
	 * 查询操作
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getByIdXQ() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		if("01".equals(acctType)){//专户
			formproj0020 = formService.getFormData("proj0020");
			projAcct = new ProjAcct();
			projAcct.setAcctId(acctId);
			projAcct = projAcctBo.getById(projAcct);
			getObjValue(formproj0020, projAcct);
			return "detailForZ";
		}else if("02".equals(acctType)){//三方备付金户
			formproj0008 = formService.getFormData("proj0008");
			projAcct = new ProjAcct();
			projAcct.setAcctId(acctId);
			projAcct = projAcctBo.getById(projAcct);
			getObjValue(formproj0008, projAcct);
			return "detail";
		}else{//放款扣款账户
			formproj0030 = formService.getFormData("proj0030");
			projAcct = new ProjAcct();
			projAcct.setAcctId(acctId);
			projAcct = projAcctBo.getById(projAcct);
			getObjValue(formproj0030, projAcct);
			return "detailForFK";
		}
		
	}
	/**
	 * 查询操作
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getById_Read() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		formproj0008 = formService.getFormData("proj0008");
		formproj0016 = formService.getFormData("proj0016");
		projAcct = new ProjAcct();

		projAcct.setAcctId(acctId);
		projAcct.setProjNo(projNo);
		projAcct.setAcctType(acctType);
		projAcct.setProjName(projName);
		projAcct.setUpDate(User.getSys_date(ServletActionContext.getRequest()));// 修改日期
		projAcct.setUpOpno(User.getLoginid(ServletActionContext.getRequest()));// 修改人员
		projAcct.setAcctNo(acctNo);
		projAcct = projAcctBo.getById(projAcct);
		if ("query".equals(query)) {
			getObjValue(formproj0016, projAcct);
		} else {
			getObjValue(formproj0008, projAcct);
		}
		return "detail_Read";
	}

	/**
	 * 新增保存操作校验
	 * 
	 * @return
	 * @throws Exception
	 */
	public void validateInsert() {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		formproj0008 = formService.getFormData("proj0008");
		getFormValue(formproj0008);
		validateFormData(formproj0008);
	}

	/**
	 * 修改保存操作校验
	 * 
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate() {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		formproj0008 = formService.getFormData("proj0008");
			getFormValue(formproj0008);
			validateFormData(formproj0008);
	}

	/**
	 * 根据项目显示所有的的账户 信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String listQuotaForCorp() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		if (projNo == null || "".equals(projNo)) {
			this.addActionMessage("请先在  项目信息 中补录小微项目编号！");
			return "   ";
		} else {
			projAcct = new ProjAcct();
			projAcct.setProjNo(projNo);
			Ipage ipage = this.getIpage();
			projAcctList = (List) projAcctBo.findByPageQuotaForCorp(ipage,
					projAcct).getResult();
			return "list";
		}

	}

	/**
	 * @作者 DHCC-ZLC
	 * @日期 2016-8-13
	 * @方法说明：
	 * @返回参数 String
	 */
	public String findByProjNoAndAcctType() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		projAcct = new ProjAcct();
		projAcct.setProjNo(projNo);
		projAcct.setAcctType(acctType);
		Ipage ipage = this.getIpage();
		projAcctList = (List) projAcctBo.findByProjNoAndAcctType(ipage,
				projAcct).getResult();
		if ("01".equals(acctType)) {
			return "list1";
		} else {
			return "list2";
		}
	}
	public String findByProjNoToFK() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		projAcct = new ProjAcct();
		projAcct.setProjNo(projNo);
		Ipage ipage = this.getIpage();
		projAcctList = projAcctBo.findListByProjNo(projAcct);
		return "list";
	}
	/**
	 * @作者 DHCC-ZLC
	 * @日期 2016-8-22
	 * @方法说明：未来现金流预测
	 * @返回参数 String
	 */
	public String future_cash_flow() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());

		String nowDate = DateUtil.addByDay((User.getTime().replace("-", ""))
				.substring(0, 8), -1);
		String beginDate = DateUtil.addByDay(nowDate, -30);
		endDate = endDate.replace("-", "");
		// 预测区间
		int between_Date = DateUtil.getBetweenDays(DateUtil
				.changeToShow(nowDate), DateUtil.changeToShow(endDate)) + 1;
		// 1.从资金信息获取30天追加资金平均值
		FundDetail fundDetail = new FundDetail();
		fundDetail.setTxDate(nowDate);
		fundDetail.setTradeType("01");
		fundDetail.setFundNo(projNo);
		Double add_bal_avg = fundDetailBo.getBalBytradeType(fundDetail);

		// 2.清算进入金额
		FundRepayPlan fundRepayPlan = new FundRepayPlan();
		fundRepayPlan.setRepayDate(nowDate);
		fundRepayPlan.setFiller(endDate);
		fundRepayPlan.setFundNo(projNo);
		double repay_bal_back = fundRepayPlanBo.getBalByDate(fundRepayPlan);

		// 3.还款、代偿、回购进入专户金额
		RptProj rptProj = new RptProj();
		rptProj.setProjNo(projNo);
		rptProj.setRptDate(beginDate);
		// 开始日期获取
		RptProj rptProj1 = rptProjBo.getDailyById(rptProj);
		// 结束日期获取
		rptProj.setRptDate(nowDate);
		RptProj rptProj2 = rptProjBo.getDailyById(rptProj);
		if (rptProj1 == null) {
			rptProj1 = new RptProj();
			rptProj1.setRepayTot(0.0);
			rptProj1.setIntstTot(0.0);
			rptProj1.setFineTot(0.0);
			rptProj1.setCompAmt(0.0);
			rptProj1.setRebuyAmt(0.0);
		}
		if (rptProj2 == null) {
			rptProj2 = new RptProj();
			rptProj2.setRepayTot(0.0);
			rptProj2.setIntstTot(0.0);
			rptProj2.setFineTot(0.0);
			rptProj2.setCompAmt(0.0);
			rptProj2.setRebuyAmt(0.0);
		}
		// 3.1获取平均还款额
		double repay_avg = DateUtil
				.DoDcm(
						rptProj2.getRepayTot()
								+ rptProj2.getIntstTot()
								+ rptProj2.getFineTot()
								- (rptProj1.getRepayTot()
										+ rptProj1.getIntstTot() + rptProj1
										.getFineTot())).divide(
						DateUtil.IoDcm(30), 3, BigDecimal.ROUND_HALF_UP)
				.doubleValue();
		// 3.2获取平均代偿金额
		double comp_amt_avg = DateUtil.DoDcm(
				rptProj2.getCompAmt() - rptProj1.getCompAmt()).divide(
				DateUtil.IoDcm(30), 3, BigDecimal.ROUND_HALF_UP).doubleValue();
		// 3.3获取平津回购金额
		double rebuy_amt_avg = DateUtil.DoDcm(
				rptProj2.getRebuyAmt() - rptProj1.getRebuyAmt()).divide(
				DateUtil.IoDcm(30), 3, BigDecimal.ROUND_HALF_UP).doubleValue();

		// 4.获取平均兑付赎回值
		FundPayPlan fundPayPlan = new FundPayPlan();
		fundPayPlan.setPayDate(nowDate);
		fundPayPlan.setWarnDate(endDate);
		fundPayPlan.setFundNo(projNo);
		double pay_bal_out = fundPayPlanBo.getBalByDate(fundPayPlan);

		// 5.获取平均放款额 AMT_AVG
		double amt_avg = rptProj2.getAmtAvg();
		// 入款
		double inAmt = add_bal_avg * between_Date + repay_bal_back
				+ (repay_avg + comp_amt_avg + rebuy_amt_avg) * between_Date;
		// 出款
		double outAmt = pay_bal_out + amt_avg * between_Date;
		// 剩款
		double restAmt = inAmt - outAmt + rptProj2.getAccBal();
		futureCashFlow = new FutureCashFlow();
		futureCashFlow.setInAmt(inAmt);
		futureCashFlow.setOutAmt(outAmt);
		futureCashFlow.setRestAmt(restAmt);
		PrintWriter out = this.getHttpResponse().getWriter();
		JSON json = (JSON) JSON.toJSON(futureCashFlow);
		out.print(json);

		return null;

	}

	/**
	 * @作者 DHCC-ZLC
	 * @日期 2016-8-22
	 * @方法说明：虚拟账户测算
	 * @返回参数 String
	 */
	public String virtual_account_calculaor() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());

		String nowDate = DateUtil.addByDay((User.getTime().replace("-", ""))
				.substring(0, 8), -1);
		endDate = endDate.replace("-", "");
		int days = 0;
		double bal = 0.00;
		// 预测区间
		// 虚拟账户余额
		projAcct = new ProjAcct();
		projAcct.setAcctId(acctId);
		projAcct = projAcctBo.getById(projAcct);

		RptProj rptProj = new RptProj();
		rptProj.setProjNo(projNo);
		rptProj.setRptDate(nowDate);
		rptProj = rptProjBo.getDailyById(rptProj);
		if(rptProj!=null){
			if ("01".equals(CalCulType)) {
				// 时间测算
				// 1.能用天数
				days = (int) Math
						.floor(projAcct.getAcctBal() / rptProj.getAmtTot());
				// 2.剩余金额
				bal = projAcct.getAcctBal() % rptProj.getAmtTot();
				futureCashFlow = new FutureCashFlow();
				futureCashFlow.setUserDays(days);
				futureCashFlow.setBal(bal);
				futureCashFlow.setCalCulType(CalCulType);
			} else {
				// 金额测算
				// 1.能用天数
				days = DateUtil.getBetweenDays(DateUtil.changeToShow(nowDate),
						DateUtil.changeToShow(endDate)) + 1;
				;
				// 2.所用金额
				double userAmt = rptProj.getAmtTot() * days;
				// 3.剩余金额
				bal = projAcct.getAcctBal() - userAmt;
				futureCashFlow = new FutureCashFlow();
				futureCashFlow.setUserAmt(userAmt);
				futureCashFlow.setBal(bal);
				futureCashFlow.setCalCulType(CalCulType);
				PrintWriter out = this.getHttpResponse().getWriter();
				JSON json = (JSON) JSON.toJSON(futureCashFlow);
				out.print(json);
			}
		}
		return null;
	}

	/**
	 * @作者 DHCC-ZLC
	 * @日期 2016-8-22
	 * @方法说明：虚拟账户预拨款
	 * @返回参数 String
	 */
	public String virtual_AllocateReg() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		memo = java.net.URLDecoder.decode(memo, "UTF-8");
		transreason = java.net.URLDecoder.decode(transreason, "UTF-8");
		purpose = java.net.URLDecoder.decode(purpose, "UTF-8");
		opbankprovince = java.net.URLDecoder.decode(opbankprovince, "UTF-8");
		opbankcity = java.net.URLDecoder.decode(opbankcity, "UTF-8");
		project = java.net.URLDecoder.decode(project, "UTF-8");
		customer = java.net.URLDecoder.decode(customer, "UTF-8");
		industry = java.net.URLDecoder.decode(industry, "UTF-8");
		opbankacntname = java.net.URLDecoder.decode(opbankacntname, "UTF-8");
		opbankname = java.net.URLDecoder.decode(opbankname, "UTF-8");
		projAcct = new ProjAcct();
		AllocateRegWsIn allocateRegWsIn = new AllocateRegWsIn();
		// 主体信息
		allocateRegWsIn.setId(id);
		allocateRegWsIn.setBusinessflag(businessflag);
		allocateRegWsIn.setAutoSyncFlag("2");
		allocateRegWsIn.setProjectid(projNo);
		allocateRegWsIn.setDdtype(ddtype);
		allocateRegWsIn.setTransdate(transdate);
		allocateRegWsIn.setTransreason(transreason);
		allocateRegWsIn.setPurpose(purpose);
		allocateRegWsIn.setMemo(memo);
		allocateRegWsIn.setUserCode(userCode);
		allocateRegWsIn.setStaffapp(staffapp);
		// 明细list
		allocateRegWsIn.setAmount(amount);
		allocateRegWsIn.setBktranstypecode(bktranstypecode);
		allocateRegWsIn.setSktranstypecode(sktranstypecode);
		allocateRegWsIn.setFeetypecode(feetypecode);
		allocateRegWsIn.setTaxtypecode(taxtypecode);
		allocateRegWsIn.setCustomid(customid);
		allocateRegWsIn.setAccountid(accountid);//
		// allocateRegWsIn.setOpbankacntid(opbankacntid);
		allocateRegWsIn.setOpbankacntno(opbankacntno);
		allocateRegWsIn.setOpbankname(opbankname);
		allocateRegWsIn.setOpbankacntname(opbankacntname);
		allocateRegWsIn.setOpbankprovince(opbankprovince);
		allocateRegWsIn.setOpbankcity(opbankcity);
		allocateRegWsIn.setRepaytypeid(repaytypeid);
		allocateRegWsIn.setAppusercode(appusercode);
		// 以下内容为辅助核算内容
		allocateRegWsIn.setPersonnel("");
		allocateRegWsIn.setProject(project);
		allocateRegWsIn.setCustomer(customer);
		allocateRegWsIn.setCashflow("");
		allocateRegWsIn.setVirtualaccount("");
		allocateRegWsIn.setContractnumber("");
		allocateRegWsIn.setStockcode("");
		allocateRegWsIn.setFundaccount("");
		allocateRegWsIn.setSalesdepartment("");
		allocateRegWsIn.setChecknumber("");
		allocateRegWsIn.setBusinesstype("");
		allocateRegWsIn.setCashflowstatement("");
		allocateRegWsIn.setTaxcategory("");
		allocateRegWsIn.setInterestrate("");
		allocateRegWsIn.setIndustry(industry);
		allocateRegWsIn.setStock("");
		allocateRegWsIn.setAssetname("");
		allocateRegWsIn.setProductname("");
		allocateRegWsIn.setClientorbeneficiary("");
		allocateRegWsIn.setShareholder("");
		allocateRegWsIn.setEntrustcontractnumber("");
		allocateRegWsIn.setTradingpurpose("");
		allocateRegWsIn.setContractvariety("");
		allocateRegWsIn.setDateofreceipt("");
		allocateRegWsIn.setInterestdate("");
		allocateRegWsIn.setDuedate("");
		allocateRegWsIn.setInorout("");
		allocateRegWsIn.setBillnumber("");
		allocateRegWsIn.setAcctId(acctId);
		projAcct = projAcctBo.virtual_AllocateReg(allocateRegWsIn);
		PrintWriter out = this.getHttpResponse().getWriter();
		JSON json = (JSON) JSON.toJSON(projAcct);
		out.print(json);
		return null;

	}

	/**
	 * @作者 DHCC-ZLC
	 * @日期 2016-8-22
	 * @方法说明：数据插入收拨款表中数据进行反显
	 * @返回参数 String
	 */
	public String insert_allocateRegWsIn() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		AllocateRegWsIn allocateRegWsIn = new AllocateRegWsIn();
		opNo = User.getLoginid(this.getHttpRequest());
		allocateRegWsIn = projAcctBo.insert_allocateRegWsIn(projNo, txAmt,
				acctId, opNo, tradeCode);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSON json = (JSON) JSON.toJSON(allocateRegWsIn);
		if(allocateRegWsIn.getAccountid()==null){
			json = null;
		}
		out.print(json);
		return null;

	}

	public String getTab() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		tabList = new ArrayList();
		String[] tab = null;

//		System.out.println("=========isFlag=======" + isFlag
//				+ "======acctType========" + acctType + "====projNo======="
//				+ projNo);

		if (acctType != null && acctType.equals("01")) {
			tab = new String[2];
			tab[0] = "专户信息";
			tab[1] = "ProjAcctAction_getById_Read.action?acctId=" + acctId
					+ "&acctType=" + acctType + "&query=query&projNo=" + projNo;
			tabList.add(tab);

			tab = new String[2];
			tab[0] = "专户流水";
			tab[1] = "ProjAcctLstAction_listQuotaForCorp_Read.action?acctId="
					+ acctId + "&acctType=" + acctType + "&query=query&projNo="
					+ projNo;
			tabList.add(tab);

		} else {

			tab = new String[2];
			tab[0] = "虚拟账户信息";
			tab[1] = "ProjAcctAction_getById.action?acctId=" + acctId
					+ "&acctType=" + acctType + "&query=query&projNo=" + projNo;
			tabList.add(tab);

			tab = new String[2];
			tab[0] = "虚拟账户信息流水";
			tab[1] = "ProjAcctLstAction_listQuotaForCorp.action?acctId="
					+ acctId + "&acctType=" + acctType + "&query=query&projNo="
					+ projNo;
			tabList.add(tab);
		}

		return "tab";
	}

	// 资金同步
	public String fundsync() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		
		projAcct = new ProjAcct();
		projAcct.setAcctId(acctId);
		projAcct = projAcctBo.getById(projAcct);
		String projNo = projAcct.getProjNo();//获取项目编号
		if (projAcct == null || "".equals(projAcct)) {
			this.addActionError("项目编号‘" + projNo + "’对应的账号不存在！");
			if ("01".equals(acctType)) {
				return "list1";
			} else {
				return "list2";
			}
		}
		SimpleDateFormat timeFormater = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		/*
		// 报文头
		AcctSynHead acctSynHead = new AcctSynHead();
		acctSynHead.setRequestType("B100002");
		acctSynHead.setUUID("00014");// 交易流水号
		acctSynHead.setComId("0001");
		acctSynHead.setComIP("211.168.22.111");
		acctSynHead.setFrom("1881632261");
		acctSynHead.setSystemName("16133001");
		acctSynHead.setSystemPassword("111111");

		acctSynHead.setSendTime(timeFormater.format(new Date()));
		acctSynHead.setAsyn("0");
		acctSynHead.setReturnUrl("http://ufc.esb.cost.com/cooperation/");
		acctSynHead.setSigned("");
		acctSynHead.setItSystem("0");
		String headXml = XMLUtil.createHead(acctSynHead); // 头文件
		// 报文实体
		AcctBalDetail acctBalDetail = new AcctBalDetail();
		acctBalDetail.setAccountNo(projAcct.getAcctNo());// 账户号
		acctBalDetail.setPayChannel(projAcct.getCardChn()); // 交易渠道代码
		acctBalDetail.setPayType("01");// 交易类型

		String bodyXml = XMLUtil.createBody(acctBalDetail);// 转换为XML
		String xml = headXml + bodyXml + "</Package>";
		System.out.println(xml);
		// 调用接口
		 
		 
		Date d1 = new Date();
		QueryService QueryService = (QueryService) SourceTemplate
				.getSpringContextInstance().getBean("queryService");
		String result = QueryService.doAction("B100002", xml);
		Date d2 = new Date();
		System.out.println(result);
		Document document = null;
		try {
			document = DocumentHelper.parseText(result);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Element root = document.getRootElement();
		Element eHeader = root.element("Header");
		Element eResultCode = eHeader.element("ResultCode");
		String resultCode = eResultCode.getText();

		Element eResultMsg = eHeader.element("ResultMsg");
		String resultMsg = eResultMsg.getText();

		String wsSts = "01";
		if ("1".equals(resultCode)) {
			wsSts = "02";
			Element eBody = root.element("Body");
			Element eBalanceInfoList = eBody.element("BalanceInfoList");
			Element eBalanceInfo = eBalanceInfoList.element("BalanceInfo");
			Element eBalance = eBalanceInfo.element("Balance");
			Double money = Double.valueOf(eBalance.getText()) / 100;
			// resultMsg = resultMsg+"账户余额= "+s+"";
			projAcct.setAcctBal(money);
			projAcctBo.update(projAcct);
			this.addActionError("数据同步成功！账户余额=" + money + "元");
		} else {
			this.addActionError("数据同步失败，支付平台返回：" + resultMsg + "!");
		}
		*/

		//查询合作机构编号
		projBase = new ProjBase();
		projBase.setProjNo(projNo);
		projBase = projBaseBo.getById(projBase);
		
		/*
		WsBase wsBase = new WsBase();
		SimpleDateFormat wsTimeFormater = new SimpleDateFormat(
				"yyyyMMdd HH:mm:ss");
		wsBase.setWsTime(wsTimeFormater.format(d1));
		wsBase.setRespTime(wsTimeFormater.format(d2));
		wsBase.setTxCode("A100002");
		wsBase.setWsSerial("00014");
		wsBase.setRespCode(resultCode);
		wsBase.setRespDesc("支付平台返回：" + resultMsg + "");
		wsBase.setWsSts(wsSts);
		wsBase.setReqContent(xml);
		wsBase.setRespContent(result);
		wsBase.setBrNo(projBase.getBrNo());
		wsBaseBo.insert(wsBase);
		*/
	
		
		
		projAcct = new ProjAcct();
		projAcct.setProjNo(projNo);
		projAcct.setAcctId(acctId);
		projAcct.setAcctType(acctType);
		Ipage ipage = this.getIpage();
		projAcctList = (List) projAcctBo.findByProjNoAndAcctType(ipage,
				projAcct).getResult();
		if ("01".equals(acctType)) {
			return "list1";
		} else {
			return "list2";
		}

	}

	public ProjAcct getProjAcct() {
		return projAcct;
	}

	public void setProjAcct(ProjAcct projAcct) {
		this.projAcct = projAcct;
	}

	public List<ProjAcct> getProjAcctList() {
		return projAcctList;
	}

	public void setProjAcctList(List<ProjAcct> projAcctList) {
		this.projAcctList = projAcctList;
	}

	public ProjAcctBo getProjAcctBo() {
		return projAcctBo;
	}

	public void setProjAcctBo(ProjAcctBo projAcctBo) {
		this.projAcctBo = projAcctBo;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public FormData getFormproj0008() {
		return formproj0008;
	}

	public void setFormproj0008(FormData formproj0008) {
		this.formproj0008 = formproj0008;
	}

	public FormData getFormproj0007() {
		return formproj0007;
	}

	public void setFormproj0007(FormData formproj0007) {
		this.formproj0007 = formproj0007;
	}

	public void setTxDate(String txDate) {
		this.txDate = txDate;
	}

	public void setUpOpno(String upOpno) {
		this.upOpno = upOpno;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public void setAcctDesc(String acctDesc) {
		this.acctDesc = acctDesc;
	}

	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	public void setUpDate(String upDate) {
		this.upDate = upDate;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public void setAcctBal(Double acctBal) {
		this.acctBal = acctBal;
	}

	public void setAcctUse(String acctUse) {
		this.acctUse = acctUse;
	}

	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public void setOpNo(String opNo) {
		this.opNo = opNo;
	}

	public void setFiller(String filler) {
		this.filler = filler;
	}

	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}

	public void setOpnBank(String opnBank) {
		this.opnBank = opnBank;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public String getTxDate() {
		return txDate;
	}

	public String getUpOpno() {
		return upOpno;
	}

	public String getDeptNo() {
		return deptNo;
	}

	public String getAcctDesc() {
		return acctDesc;
	}

	public String getAcctId() {
		return acctId;
	}

	public String getAcctName() {
		return acctName;
	}

	public String getUpDate() {
		return upDate;
	}

	public String getCardNo() {
		return cardNo;
	}

	public Double getAcctBal() {
		return acctBal;
	}

	public String getAcctUse() {
		return acctUse;
	}

	public String getAcctType() {
		return acctType;
	}

	public String getProjName() {
		return projName;
	}

	public String getOpNo() {
		return opNo;
	}

	public String getFiller() {
		return filler;
	}

	public String getProjNo() {
		return projNo;
	}

	public String getOpnBank() {
		return opnBank;
	}

	public String getAcctNo() {
		return acctNo;
	}

	public ProjBase getProjBase() {
		return projBase;
	}

	public void setProjBase(ProjBase projBase) {
		this.projBase = projBase;
	}

	public ProjBaseBo getProjBaseBo() {
		return projBaseBo;
	}

	public void setProjBaseBo(ProjBaseBo projBaseBo) {
		this.projBaseBo = projBaseBo;
	}

	public List getTabList() {
		return tabList;
	}

	public void setTabList(List tabList) {
		this.tabList = tabList;
	}

	public String getIsFlag() {
		return isFlag;
	}

	public void setIsFlag(String isFlag) {
		this.isFlag = isFlag;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public FundDetailBo getFundDetailBo() {
		return fundDetailBo;
	}

	public void setFundDetailBo(FundDetailBo fundDetailBo) {
		this.fundDetailBo = fundDetailBo;
	}

	public FundPayPlanBo getFundPayPlanBo() {
		return fundPayPlanBo;
	}

	public void setFundPayPlanBo(FundPayPlanBo fundPayPlanBo) {
		this.fundPayPlanBo = fundPayPlanBo;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the rptProjBo
	 */
	public RptProjBo getRptProjBo() {
		return rptProjBo;
	}

	/**
	 * @param rptProjBo
	 *            the rptProjBo to set
	 */
	public void setRptProjBo(RptProjBo rptProjBo) {
		this.rptProjBo = rptProjBo;
	}

	public FundRepayPlanBo getFundRepayPlanBo() {
		return fundRepayPlanBo;
	}

	public void setFundRepayPlanBo(FundRepayPlanBo fundRepayPlanBo) {
		this.fundRepayPlanBo = fundRepayPlanBo;
	}

	public FutureCashFlow getFutureCashFlow() {
		return futureCashFlow;
	}

	public void setFutureCashFlow(FutureCashFlow futureCashFlow) {
		this.futureCashFlow = futureCashFlow;
	}

	public String getCalCulType() {
		return CalCulType;
	}

	public void setCalCulType(String calCulType) {
		CalCulType = calCulType;
	}

	/**
	 * @return the txAmt
	 */
	public double getTxAmt() {
		return txAmt;
	}

	/**
	 * @param txAmt
	 *            the txAmt to set
	 */
	public void setTxAmt(double txAmt) {
		this.txAmt = txAmt;
	}

	public FormData getFormproj0011() {
		return formproj0011;
	}

	public void setFormproj0011(FormData formproj0011) {
		this.formproj0011 = formproj0011;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the businessflag
	 */
	public String getBusinessflag() {
		return businessflag;
	}

	/**
	 * @param businessflag
	 *            the businessflag to set
	 */
	public void setBusinessflag(String businessflag) {
		this.businessflag = businessflag;
	}

	/**
	 * @return the projectid
	 */
	public String getProjectid() {
		return projectid;
	}

	/**
	 * @param projectid
	 *            the projectid to set
	 */
	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}

	/**
	 * @return the ddtype
	 */
	public String getDdtype() {
		return ddtype;
	}

	/**
	 * @param ddtype
	 *            the ddtype to set
	 */
	public void setDdtype(String ddtype) {
		this.ddtype = ddtype;
	}

	/**
	 * @return the transdate
	 */
	public String getTransdate() {
		return transdate;
	}

	/**
	 * @param transdate
	 *            the transdate to set
	 */
	public void setTransdate(String transdate) {
		this.transdate = transdate;
	}

	/**
	 * @return the transreason
	 */
	public String getTransreason() {
		return transreason;
	}

	/**
	 * @param transreason
	 *            the transreason to set
	 */
	public void setTransreason(String transreason) {
		this.transreason = transreason;
	}

	/**
	 * @return the purpose
	 */
	public String getPurpose() {
		return purpose;
	}

	/**
	 * @param purpose
	 *            the purpose to set
	 */
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	/**
	 * @return the memo
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * @param memo
	 *            the memo to set
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * @return the bktranstypecode
	 */
	public String getBktranstypecode() {
		return bktranstypecode;
	}

	/**
	 * @param bktranstypecode
	 *            the bktranstypecode to set
	 */
	public void setBktranstypecode(String bktranstypecode) {
		this.bktranstypecode = bktranstypecode;
	}

	/**
	 * @return the sktranstypecode
	 */
	public String getSktranstypecode() {
		return sktranstypecode;
	}

	/**
	 * @param sktranstypecode
	 *            the sktranstypecode to set
	 */
	public void setSktranstypecode(String sktranstypecode) {
		this.sktranstypecode = sktranstypecode;
	}

	/**
	 * @return the feetypecode
	 */
	public String getFeetypecode() {
		return feetypecode;
	}

	/**
	 * @param feetypecode
	 *            the feetypecode to set
	 */
	public void setFeetypecode(String feetypecode) {
		this.feetypecode = feetypecode;
	}

	/**
	 * @return the taxtypecode
	 */
	public String getTaxtypecode() {
		return taxtypecode;
	}

	/**
	 * @param taxtypecode
	 *            the taxtypecode to set
	 */
	public void setTaxtypecode(String taxtypecode) {
		this.taxtypecode = taxtypecode;
	}

	/**
	 * @return the customid
	 */
	public String getCustomid() {
		return customid;
	}

	/**
	 * @param customid
	 *            the customid to set
	 */
	public void setCustomid(String customid) {
		this.customid = customid;
	}

	/**
	 * @return the accountid
	 */
	public String getAccountid() {
		return accountid;
	}

	/**
	 * @param accountid
	 *            the accountid to set
	 */
	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	/**
	 * @return the opbankacntno
	 */
	public String getOpbankacntno() {
		return opbankacntno;
	}

	/**
	 * @param opbankacntno
	 *            the opbankacntno to set
	 */
	public void setOpbankacntno(String opbankacntno) {
		this.opbankacntno = opbankacntno;
	}

	/**
	 * @return the opbankname
	 */
	public String getOpbankname() {
		return opbankname;
	}

	/**
	 * @param opbankname
	 *            the opbankname to set
	 */
	public void setOpbankname(String opbankname) {
		this.opbankname = opbankname;
	}

	/**
	 * @return the opbankacntname
	 */
	public String getOpbankacntname() {
		return opbankacntname;
	}

	/**
	 * @param opbankacntname
	 *            the opbankacntname to set
	 */
	public void setOpbankacntname(String opbankacntname) {
		this.opbankacntname = opbankacntname;
	}

	/**
	 * @return the opbankprovince
	 */
	public String getOpbankprovince() {
		return opbankprovince;
	}

	/**
	 * @param opbankprovince
	 *            the opbankprovince to set
	 */
	public void setOpbankprovince(String opbankprovince) {
		this.opbankprovince = opbankprovince;
	}

	/**
	 * @return the opbankcity
	 */
	public String getOpbankcity() {
		return opbankcity;
	}

	/**
	 * @param opbankcity
	 *            the opbankcity to set
	 */
	public void setOpbankcity(String opbankcity) {
		this.opbankcity = opbankcity;
	}

	/**
	 * @return the tradeCode
	 */
	public String getTradeCode() {
		return tradeCode;
	}

	/**
	 * @param tradeCode
	 *            the tradeCode to set
	 */
	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}

	/**
	 * @return the project
	 */
	public String getProject() {
		return project;
	}

	/**
	 * @param project
	 *            the project to set
	 */
	public void setProject(String project) {
		this.project = project;
	}

	/**
	 * @return the customer
	 */
	public String getCustomer() {
		return customer;
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(String customer) {
		this.customer = customer;
	}

	/**
	 * @return the industry
	 */
	public String getIndustry() {
		return industry;
	}

	/**
	 * @param industry
	 *            the industry to set
	 */
	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public FormData getFormproj0015() {
		return formproj0015;
	}

	public void setFormproj0015(FormData formproj0015) {
		this.formproj0015 = formproj0015;
	}

	/**
	 * @return the staffapp
	 */
	public String getStaffapp() {
		return staffapp;
	}

	/**
	 * @param staffapp
	 *            the staffapp to set
	 */
	public void setStaffapp(String staffapp) {
		this.staffapp = staffapp;
	}

	/**
	 * @return the repaytypeid
	 */
	public String getRepaytypeid() {
		return repaytypeid;
	}

	/**
	 * @param repaytypeid
	 *            the repaytypeid to set
	 */
	public void setRepaytypeid(String repaytypeid) {
		this.repaytypeid = repaytypeid;
	}

	/**
	 * @return the appUserCode
	 */
	public String getAppusercode() {
		return appusercode;
	}

	/**
	 * @param appUserCode
	 *            the appUserCode to set
	 */
	public void setAppusercode(String appusercode) {
		this.appusercode = appusercode;
	}

	public String getCardSts() {
		return cardSts;
	}

	public void setCardSts(String cardSts) {
		this.cardSts = cardSts;
	}

	/**
	 * @return the dataSts
	 */
	public String getBackSts() {
		return backSts;
	}

	/**
	 * @param dataSts
	 *            the dataSts to set
	 */
	public void setBackSts(String backSts) {
		this.backSts = backSts;
	}

	public WsBaseBo getWsBaseBo() {
		return wsBaseBo;
	}

	public void setWsBaseBo(WsBaseBo wsBaseBo) {
		this.wsBaseBo = wsBaseBo;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getAutoSyncFlag() {
		return autoSyncFlag;
	}

	public void setAutoSyncFlag(String autoSyncFlag) {
		this.autoSyncFlag = autoSyncFlag;
	}

	public FormData getFormproj0016() {
		return formproj0016;
	}

	public void setFormproj0016(FormData formproj0016) {
		this.formproj0016 = formproj0016;
	}

	public FormData getFormproj0017() {
		return formproj0017;
	}

	public void setFormproj0017(FormData formproj0017) {
		this.formproj0017 = formproj0017;
	}

	public FormData getFormproj0020() {
		return formproj0020;
	}

	public void setFormproj0020(FormData formproj0020) {
		this.formproj0020 = formproj0020;
	}

	/**
	 * @return the formproj0028
	 */
	public FormData getFormproj0028() {
		return formproj0028;
	}

	/**
	 * @param formproj0028 the formproj0028 to set
	 */
	public void setFormproj0028(FormData formproj0028) {
		this.formproj0028 = formproj0028;
	}

	/**
	 * @return the formproj0030
	 */
	public FormData getFormproj0030() {
		return formproj0030;
	}

	/**
	 * @param formproj0030 the formproj0030 to set
	 */
	public void setFormproj0030(FormData formproj0030) {
		this.formproj0030 = formproj0030;
	}

	/**
	 * @return the corpAcctBo
	 */
	public CorpAcctBo getCorpAcctBo() {
		return corpAcctBo;
	}

	/**
	 * @param corpAcctBo the corpAcctBo to set
	 */
	public void setCorpAcctBo(CorpAcctBo corpAcctBo) {
		this.corpAcctBo = corpAcctBo;
	}

	/**
	 * @return the corpAcctList
	 */
	public List<CorpAcct> getCorpAcctList() {
		return corpAcctList;
	}

	/**
	 * @param corpAcctList the corpAcctList to set
	 */
	public void setCorpAcctList(List<CorpAcct> corpAcctList) {
		this.corpAcctList = corpAcctList;
	}
	

}