package  app.creditapp.acc.option.action;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.acc.option.bo.AcLnRepayPlnSuspBo;
import app.creditapp.acc.option.bo.impl.AcLnRepayPlnBoImpl;
import app.creditapp.acc.option.bo.impl.AcLnRepayPlnSuspBoImpl;
import app.creditapp.acc.option.entity.AcLnRepayPlnSusp;
import app.creditapp.batch.util.DBUtil;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: AcLnRepayPlnSuspAction.java
 **/
public class AcLnRepayPlnSuspAction extends BaseFormBean {

	//页面传值
	private AcLnRepayPlnSusp acLnRepayPlnSusp;
	private List<AcLnRepayPlnSusp> acLnRepayPlnSuspList;

	//注入AcLnRepayPlnSuspBo
	private AcLnRepayPlnSuspBo acLnRepayPlnSuspBo;

	private String query;
	private String wsId;		
	private FormData formacc321;
	private FormData formacc0321;
	private FormService formService = new FormService();
	
	public AcLnRepayPlnSuspAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formacc321 = formService.getFormData("acc321");
		acLnRepayPlnSusp = new AcLnRepayPlnSusp();
		getFormValue(formacc321);
		setObjValue(formacc321, acLnRepayPlnSusp);
		acLnRepayPlnSusp.setBrNo(User.getBrno(this.getHttpRequest()));
		Ipage ipage = this.getIpage();
		acLnRepayPlnSuspList = (List) acLnRepayPlnSuspBo.findByPage(ipage, acLnRepayPlnSusp).getResult();
		return "list";
	}
	
	/**
	 * 测试 
	 * @return
	 * @throws Exception
	 */
	public String testValidAcLnRepayPlnSusp() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
//		Connection con = null;
//		Statement stmtBatch=null;
//		ResultSet rs = null;
//		
//		try {
//			con = DBUtil.getConnection();// 获得数据库连接 
//			stmtBatch = con.createStatement();
//			
//			String sql = "select * from WS_REPY_PLAN where 1=1";
//			System.out.println(sql);
//			rs = stmtBatch.executeQuery(sql);
//			List<AcLnRepayPlnSusp> list = new ArrayList<AcLnRepayPlnSusp>();
//			 while(rs.next()){    
//			     // 可以根据列名称也可以根据列索引    
//				 AcLnRepayPlnSusp acLnRepayPlnSusp = new AcLnRepayPlnSusp();    
//				 acLnRepayPlnSusp.setWsId(rs.getString("WS_ID"));  
//				 acLnRepayPlnSusp.setBatchNo(rs.getString("BATCH_NO"));
//				 acLnRepayPlnSusp.setBrNo(rs.getString("BR_NO"));
//				 acLnRepayPlnSusp.setPactNo(rs.getString("PACT_NO"));
//				 acLnRepayPlnSusp.setCnt(rs.getInt("CNT"));
//				 acLnRepayPlnSusp.setBegDate(rs.getString("BEG_DATE"));
//				 acLnRepayPlnSusp.setEndDate(rs.getString("END_DATE"));
//				 acLnRepayPlnSusp.setTotalAmt(rs.getDouble("TOTAL_AMT"));  
//				 acLnRepayPlnSusp.setPrcpAmt(rs.getDouble("PRCP_AMT"));
//				 acLnRepayPlnSusp.setNormInt(rs.getDouble("NORM_INT"));
//				 acLnRepayPlnSusp.setSts(rs.getString("STS"));
//				 acLnRepayPlnSusp.setTxDate(rs.getString("TX_DATE"));
//			     //将对象存放到list容器中    
//			     list.add(acLnRepayPlnSusp); 
//			 }
//			 System.out.println(list.size()+"!!!!");
//			 AcLnRepayPlnSuspBoImpl acLnRepayPlnSuspBoImpl = new AcLnRepayPlnSuspBoImpl();
			 boolean ea = acLnRepayPlnSuspBo.validAcLnRepayPlnSusp("100000000158","00000");
			 System.out.println(ea);
			 
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formacc0321 = formService.getFormData("acc0321");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formacc0321 = formService.getFormData("acc0321");
		getFormValue(formacc0321);
		acLnRepayPlnSusp = new AcLnRepayPlnSusp();
		setObjValue(formacc0321, acLnRepayPlnSusp);
		acLnRepayPlnSusp.setBrNo(User.getBrno(this.getHttpRequest()));
		acLnRepayPlnSusp.setTxDate(User.getSys_date(this.getHttpRequest()));
		acLnRepayPlnSuspBo.insert(acLnRepayPlnSusp);
		getObjValue(formacc0321, acLnRepayPlnSusp);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formacc0321 = formService.getFormData("acc0321");
		getFormValue(formacc0321);
		acLnRepayPlnSusp = new AcLnRepayPlnSusp();
		setObjValue(formacc0321, acLnRepayPlnSusp);
		acLnRepayPlnSuspBo.update(acLnRepayPlnSusp);
		getObjValue(formacc0321, acLnRepayPlnSusp);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formacc321 = formService.getFormData("acc321");
		acLnRepayPlnSusp = new AcLnRepayPlnSusp();
		acLnRepayPlnSusp.setWsId(wsId);
		acLnRepayPlnSuspBo.del(acLnRepayPlnSusp);
		this.addActionMessage("删除成功");
		acLnRepayPlnSusp = new AcLnRepayPlnSusp();
		acLnRepayPlnSusp.setBrNo(User.getBrno(this.getHttpRequest()));
		Ipage ipage = this.getIpage();
		acLnRepayPlnSuspList = (List) acLnRepayPlnSuspBo.findByPage(ipage, acLnRepayPlnSusp).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formacc0321 = formService.getFormData("acc0321");
		acLnRepayPlnSusp = new AcLnRepayPlnSusp();
		acLnRepayPlnSusp.setWsId(wsId);
		acLnRepayPlnSusp = acLnRepayPlnSuspBo.getById(acLnRepayPlnSusp);
		getObjValue(formacc0321, acLnRepayPlnSusp);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formacc0321 = formService.getFormData("acc0321");
		 getFormValue(formacc0321);
		 validateFormData(formacc0321);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formacc0321 = formService.getFormData("acc0321");
		 getFormValue(formacc0321);
		 validateFormData(formacc0321);
  	}
	public AcLnRepayPlnSusp getAcLnRepayPlnSusp() {
		return acLnRepayPlnSusp;
	}
	public void setAcLnRepayPlnSusp(AcLnRepayPlnSusp  acLnRepayPlnSusp) {
		this.acLnRepayPlnSusp = acLnRepayPlnSusp;
	}
	public List<AcLnRepayPlnSusp> getAcLnRepayPlnSuspList() {
		return acLnRepayPlnSuspList;
	}
	public void setAcLnRepayPlnSuspList(List<AcLnRepayPlnSusp> acLnRepayPlnSuspList) {
		this.acLnRepayPlnSuspList = acLnRepayPlnSuspList;
	}
	public AcLnRepayPlnSuspBo getAcLnRepayPlnSuspBo() {
		return acLnRepayPlnSuspBo;
	}
	public void setAcLnRepayPlnSuspBo(AcLnRepayPlnSuspBo acLnRepayPlnSuspBo) {
		this.acLnRepayPlnSuspBo = acLnRepayPlnSuspBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormacc0321() {
		return formacc0321;
	}
	public void setFormacc0321(FormData formacc0321) {
		this.formacc0321 = formacc0321;
	}
	public FormData getFormacc321() {
		return formacc321;
	}
	public void setFormacc321(FormData formacc321) {
		this.formacc321 = formacc321;
	}
	public void setWsId(String wsId){
		this.wsId = wsId;
	}		
	public String getWsId(){
		return wsId;
	}
}