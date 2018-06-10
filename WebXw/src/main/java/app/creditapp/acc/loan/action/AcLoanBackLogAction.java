package app.creditapp.acc.loan.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

import accounting.biz.loan.RepayBiz;
import accounting.biz.pub.ParmBiz;
import accounting.domain.sys.OperaInfo;
import accounting.plat.PUBConstant;
import accounting.plat.dao.JdbcDao;
import app.base.ServiceException;
import app.creditapp.acc.loan.bo.AcLoanBackLogBo;
import app.creditapp.acc.loan.bo.LoanBo;
import app.creditapp.acc.loan.bo.impl.LoanBoImpl;
import app.creditapp.acc.loan.dao.AcLoanBackLogDao;
import app.creditapp.acc.loan.dao.impl.AcLoanBackLogDaoImpl;
import app.creditapp.acc.loan.entity.AcLoanBackLog;
import app.creditapp.inf.bo.WsBaseBo;
import app.creditapp.inf.bo.impl.WsBaseBoImpl;
import app.creditapp.inf.dao.WsBaseDao;
import app.creditapp.inf.dao.impl.WsBaseDaoImpl;
import app.creditapp.inf.entity.WsBase;
import app.util.DBUtils;
import app.util.toolkit.Ipage;

/**
 * Title: AcLoanBackLogAction.java Description:
 * 
 **/
public class AcLoanBackLogAction extends BaseFormBean {

	// 页面传值
	private AcLoanBackLog acLoanBackLog;
	private List<AcLoanBackLog> acLoanBackLogList;

	// 注入AcLoanBackLogBo
	private AcLoanBackLogBo acLoanBackLogBo;
	private WsBaseBo wsBaseBo = new WsBaseBoImpl();
	private LoanBo loanBo = new LoanBoImpl();
	private String query;
	private String backLogNo;
	private FormData formloan100;
	private FormData formloan101;
	private FormService formService = new FormService();

	private String loanLogNo;
	private String backCnt;
	private String backType;
	private String backRes;
	private String failCaus;
	private String uuid;
	private String cardChn;

	private HttpServletRequest request;

	public AcLoanBackLogAction() {
		query = "";
	}

	/**
	 * 分页查询
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formloan100 = formService.getFormData("loan100");
		acLoanBackLog = new AcLoanBackLog();
		getFormValue(formloan100);
		setObjValue(formloan100, acLoanBackLog);
		Ipage ipage = this.getIpage();
		acLoanBackLogList = (List) acLoanBackLogBo.findByPage(ipage, acLoanBackLog).getResult();
		return "list";
	}

	/**
	 * 获取新增页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formloan101 = formService.getFormData("loan101");
		return "input";
	}

	/**
	 * 新增保存操作
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formloan101 = formService.getFormData("loan101");
		getFormValue(formloan101);
		acLoanBackLog = new AcLoanBackLog();
		setObjValue(formloan101, acLoanBackLog);
		acLoanBackLogBo.insert(acLoanBackLog);
		getObjValue(formloan101, acLoanBackLog);
		return "detail";
	}


	/**
	 * 修改保存操作
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formloan101 = formService.getFormData("loan101");
		getFormValue(formloan101);
		acLoanBackLog = new AcLoanBackLog();
		setObjValue(formloan101, acLoanBackLog);
		acLoanBackLogBo.update(acLoanBackLog);
		getObjValue(formloan101, acLoanBackLog);
		return "detail";
	}

	/**
	 * 删除操作
	 * 
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formloan100 = formService.getFormData("loan100");
		acLoanBackLog = new AcLoanBackLog();
		acLoanBackLog.setBackLogNo(backLogNo);
		acLoanBackLogBo.del(acLoanBackLog);
		this.addActionMessage("删除成功");
		acLoanBackLog = new AcLoanBackLog();
		Ipage ipage = this.getIpage();
		acLoanBackLogList = (List) acLoanBackLogBo.findByPage(ipage, acLoanBackLog).getResult();
		return "list";
	}

	/**
	 * 查询操作
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formloan101 = formService.getFormData("loan101");
		acLoanBackLog = new AcLoanBackLog();
		acLoanBackLog.setBackLogNo(backLogNo);
		acLoanBackLog = acLoanBackLogBo.getById(acLoanBackLog);
		getObjValue(formloan101, acLoanBackLog);
		return "detail";
	}

	/**
	 * 新增保存操作校验
	 * 
	 * @return
	 * @throws Exception
	 */
	public void validateInsert() {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formloan101 = formService.getFormData("loan101");
		getFormValue(formloan101);
		validateFormData(formloan101);
	}

	/**
	 * 修改保存操作校验
	 * 
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate() {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formloan101 = formService.getFormData("loan101");
		getFormValue(formloan101);
		validateFormData(formloan101);
	}

	/**
	 * 
	 * @作者 DHCC-LIUJ
	 * @日期 2016-8-26
	 * @描述 处理第三方回盘  
	 */
	public void dealBack(String uUID, List list) {
		// ActionContext.initialize(ServletActionContext.getRequest(),
		// ServletActionContext.getResponse());
		// ServletOutputStream out = null;
		Connection conn = null;
		try {
			WsBaseDao wsBaseDao = new WsBaseDaoImpl();
			AcLoanBackLogDao acLoanBackLogDao = new AcLoanBackLogDaoImpl();
			String msg = "回调放款";
			List<AcLoanBackLog> acLoanBackLogListT = list;
			// List<AcLoanBackLog> acLoanBackLogListT = new
			// ArrayList<AcLoanBackLog>();
			// for(int i = 0;i< list.size();i++){
			// AcLoanBackLog acLoanBackLog = new AcLoanBackLog();
			// acLoanBackLog.setBackCnt((String)list.get(2));
			// acLoanBackLog.setUuid((String)list.get(11));
			// acLoanBackLogListT.add(acLoanBackLog);
			// }

			WsBase wsBase = new WsBase();
			conn = DBUtils.getConn();
			wsBase.setWsDate(ParmBiz.getBizDt(conn));
			wsBase.setWsTime(ParmBiz.getOracleUpDate(conn));
			wsBase.setTxCode("P100001");
			wsBase.setRespDesc("支付交易代理回调接受成功");
			wsBase.setWsSts("01");
			wsBase.setRespContent(msg);
			wsBaseDao.insertDelBack(wsBase);

			// TradeInfo tradeInfo = readStringXmlOut(msg);//获取支付返回的信息
//			wsBase.setWsSerial(uUID);// 获取UUID
			wsBaseDao.updateDelBack(wsBase);

			for (AcLoanBackLog ac : acLoanBackLogListT) {

				backCnt = ac.getBackCnt();// 条目号
				uuid = ac.getUuid();
				AcLoanBackLog albkl = new AcLoanBackLog();
				AcLoanBackLog acLoanBackLog = new AcLoanBackLog();
				albkl.setBackCnt(backCnt);
				albkl.setUuid(uuid);
				acLoanBackLog = acLoanBackLogDao.getById(albkl);
				/****** 增加机制，回调等待数据提交每次2秒钟，最多等待60次，解决回调快的问题 start ******/
				int i = 0;
				while (i < 60 && acLoanBackLog == null) {
					Thread.sleep(2000);
					acLoanBackLog = acLoanBackLogDao.getById(albkl);
					i++;
				}
				/****** 增加机制，回调等待数据提交每次2秒钟，最多等待30次，解决回调快的问题 end ********/
				if (acLoanBackLog != null) {
					int result = 0;
					String PayStatus = "31000";
					String sql = "UPDATE AC_LOAN_BACK_LOG SET BACK_STS = '" + PUBConstant.BACK_STS_06 + "'";
					backRes = "01";// 成功
					result = JdbcDao.updateSql(sql + " WHERE back_log_no='" + acLoanBackLog.getBackLogNo()
							+ "' and back_cnt='" + backCnt + "' and back_sts <> '" + PUBConstant.BACK_STS_06 + "'",
							conn);
					if (result > 0) {
						conn.commit();
					} else {
						break;
					}
					failCaus = "....";
					cardChn = "CL0002";// 获取支付渠道 等待支付返回报文加上支付渠道
					acLoanBackLog.setBackRes(backRes);
					acLoanBackLog.setFailCaus(failCaus);
					acLoanBackLog.setBackDate(ParmBiz.getOracleTxTime(conn));
					acLoanBackLog.setStatus(PayStatus);
					if (PUBConstant.BACK_TYPE_01.equals(acLoanBackLog.getBackType())) {// 放款
						loanBo.grantLoanBack(acLoanBackLog.getBackLogNo(), acLoanBackLog.getBackCnt(), backRes,
								failCaus, PayStatus, cardChn);
					} else if (PUBConstant.BACK_TYPE_02.equals(acLoanBackLog.getBackType())) {// 扣款
						OperaInfo operaInfo = new OperaInfo(conn);
						operaInfo.setTraceCnt(1);
						operaInfo.setBizDt(ParmBiz.getBizDt(conn));
						operaInfo.setTxDt(ParmBiz.getOracleTxDate(conn));
						RepayBiz.acDebitBack(acLoanBackLog.getBackLogNo(), acLoanBackLog.getBackCnt(), backRes,
								failCaus, operaInfo, PayStatus, cardChn);
					}
				}
			}

			// }
			try {
				conn.commit();
			} catch (SQLException e1) {
				conn.rollback();
				e1.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public AcLoanBackLog getAcLoanBackLog() {
		return acLoanBackLog;
	}

	public void setAcLoanBackLog(AcLoanBackLog acLoanBackLog) {
		this.acLoanBackLog = acLoanBackLog;
	}

	public List<AcLoanBackLog> getAcLoanBackLogList() {
		return acLoanBackLogList;
	}

	public void setAcLoanBackLogList(List<AcLoanBackLog> acLoanBackLogList) {
		this.acLoanBackLogList = acLoanBackLogList;
	}

	public AcLoanBackLogBo getAcLoanBackLogBo() {
		return acLoanBackLogBo;
	}

	public void setAcLoanBackLogBo(AcLoanBackLogBo acLoanBackLogBo) {
		this.acLoanBackLogBo = acLoanBackLogBo;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public FormData getFormloan101() {
		return formloan101;
	}

	public void setFormloan101(FormData formloan101) {
		this.formloan101 = formloan101;
	}

	public FormData getFormloan100() {
		return formloan100;
	}

	public void setFormloan100(FormData formloan100) {
		this.formloan100 = formloan100;
	}

	public void setBackLogNo(String backLogNo) {
		this.backLogNo = backLogNo;
	}

	public String getBackLogNo() {
		return backLogNo;
	}

	public LoanBo getLoanBo() {
		return loanBo;
	}

	public void setLoanBo(LoanBo loanBo) {
		this.loanBo = loanBo;
	}

	public FormService getFormService() {
		return formService;
	}

	public String getLoanLogNo() {
		return loanLogNo;
	}

	public String getBackType() {
		return backType;
	}

	public String getBackRes() {
		return backRes;
	}

	public String getFailCaus() {
		return failCaus;
	}

	public void setFormService(FormService formService) {
		this.formService = formService;
	}

	public void setLoanLogNo(String loanLogNo) {
		this.loanLogNo = loanLogNo;
	}

	public void setBackType(String backType) {
		this.backType = backType;
	}

	public void setBackRes(String backRes) {
		this.backRes = backRes;
	}

	public void setFailCaus(String failCaus) {
		this.failCaus = failCaus;
	}

	public String getBackCnt() {
		return backCnt;
	}

	public void setBackCnt(String backCnt) {
		this.backCnt = backCnt;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public WsBaseBo getWsBaseBo() {
		return wsBaseBo;
	}

	public void setWsBaseBo(WsBaseBo wsBaseBo) {
		this.wsBaseBo = wsBaseBo;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCardChn() {
		return cardChn;
	}

	public void setCardChn(String cardChn) {
		this.cardChn = cardChn;
	}
}