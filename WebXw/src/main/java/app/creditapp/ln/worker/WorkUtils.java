package app.creditapp.ln.worker;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import app.util.DBUtils;

/**
 * @作者 DHCC-王涛
 * @日期 Jul 28, 2016
 * @描述 进件全流程公共类 调用存储过程 单例模式
 */
public class WorkUtils {
	
	private static WorkUtils instance = null;
	private static Map<String,Float> rgAppRateMap = null;
	
	public static Map<String, Float> getRgAppRateMap() {
		if (rgAppRateMap != null) {
			return rgAppRateMap;
		}
		rgAppRateMap = new HashMap<String,Float>();
		return rgAppRateMap;
	}

	private static Map<String,String> rulesNameMap = null;
	
	public static Map<String, String> getRulesNameMap() {
		if (rulesNameMap != null) {
			return rulesNameMap;
		}
		rulesNameMap = new HashMap<String,String>();
		return rulesNameMap;
	}
	
	private WorkUtils() {
	}
	
	public static synchronized WorkUtils getInstance() {
		if (instance != null) {
			return instance;
		}
		instance = new WorkUtils();
		return instance;
	}
	
	/***
	 * @作者 wangtao
	 * @日期 Jul 11, 2016
	 * @方法说明：调用存储过程进行预审批内部规则筛查
	 * @返回参数 筛查结果
	 */
	public String proc_pre_screen(String batch_no){
		Connection conn = null;
		CallableStatement proc = null;
		String _val_result = "0"; // 默认筛查不通过
		try {
			conn = DBUtils.getConn();
		  	// 调用存储过程进行内部规则筛查
	      	proc = conn.prepareCall("{ call PKG_PRE_APPLY.PROC_PRE_SCREEN(?,?) }"); //设置存储过程
	      	proc.setString(1, batch_no);//设置第一个参数输入参数
	      	proc.registerOutParameter(2, Types.VARCHAR);//第二个参数输出参数,是VARCHAR类型的
	      	proc.execute();// 执行
	      	_val_result = proc.getString(2);//获得输出参数
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeStatement(proc);
			DBUtils.closeConnection(conn);
		}
	    return _val_result;
	}
	
	/***
	 * @作者 wangtao
	 * @日期 Jul 11, 2016
	 * @方法说明：调用存储过程进行重复进件判断
	 * @返回参数 筛查结果
	 */
	public String proc_pact_repeat(String app_id){
		Connection conn = null;
		CallableStatement proc = null;
		String _val_result = "0"; // 默认筛查不通过
		try {
			conn = DBUtils.getConn();
		  	// 调用存储过程进行内部规则筛查
	      	proc = conn.prepareCall("{ call PKG_LN_APPLY.PROC_PACT_REPEAT(?,?) }"); //设置存储过程
	      	proc.setString(1, app_id);//设置第一个参数输入参数
	      	proc.registerOutParameter(2, Types.VARCHAR);//第二个参数输出参数,是VARCHAR类型的
	      	proc.execute();// 执行
	      	_val_result = proc.getString(2);//获得输出参数
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeStatement(proc);
			DBUtils.closeConnection(conn);
		}
	    return _val_result;
	}
	
	/***
	 * @作者 wangtao
	 * @日期 Jul 11, 2016
	 * @方法说明：调用存储过程进行内部规则筛查
	 * @返回参数 筛查结果
	 */
	public String proc_ln_screen(String app_id){
		Connection conn = null;
		CallableStatement proc = null;
		String _val_result = "0"; // 默认筛查不通过
		try {
			conn = DBUtils.getConn();
		  	// 调用存储过程进行内部规则筛查
	      	proc = conn.prepareCall("{ call PKG_LN_APPLY.PROC_LN_SCREEN(?,?) }"); //设置存储过程
	      	proc.setString(1, app_id);//设置第一个参数输入参数
	      	proc.registerOutParameter(2, Types.VARCHAR);//第二个参数输出参数,是VARCHAR类型的
	      	proc.execute();// 执行
	      	_val_result = proc.getString(2);//获得输出参数
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeStatement(proc);
			DBUtils.closeConnection(conn);
		}
	    return _val_result;
	}
	/***
	 * @作者 wangtao
	 * @日期 Jul 11, 2016
	 * @方法说明：调用存储过程进行客户信息拆分
	 * @返回参数 筛查结果
	 */
	public String proc_cif_split(String app_id){
		Connection conn = null;
		CallableStatement proc = null;
		String _split_result="0"; // 拆分成功标志，默认失败
		try {
			conn = DBUtils.getConn();
		  	//  调用存储过程进行客户信息拆分
		  	proc = conn.prepareCall("{ call PKG_LN_APPLY.PROC_CIF_SPLIT(?,?) }"); //调用存储过程
    	  	proc.setString(1, app_id); // 设置第一个参数输入参数
	      	proc.registerOutParameter(2, Types.VARCHAR); // 第二个参数输出参数,是VARCHAR类型的
	      	proc.execute(); // 执行
	      	_split_result = proc.getString(2);//获得输出参数
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeStatement(proc);
			DBUtils.closeConnection(conn);
		}
	    return _split_result;
	}
	/***
	 * @作者 wangtao
	 * @日期 Jul 11, 2016
	 * @方法说明：调用存储过程生成正式进件申请表
	 * @返回参数 筛查结果
	 */
	public String proc_mid_toreg(String app_id){
		Connection conn = null;
		CallableStatement proc = null;
		String _mid_toreg="0"; // 生成正式进件申请表成功标志，默认失败
		try {
			conn = DBUtils.getConn();
			// 调用存储过程生成正式进件申请表
		    proc = conn.prepareCall("{ call PKG_LN_APPLY.PROC_MID_TOREG(?,?) }"); //设置存储过程
		    proc.setString(1, app_id);//设置第一个参数输入参数
		    proc.registerOutParameter(2, Types.VARCHAR);//第二个参数输出参数,是VARCHAR类型的
		    proc.execute();// 执行
		    _mid_toreg = proc.getString(2);//获得输出参数
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeStatement(proc);
			DBUtils.closeConnection(conn);
		}
	    return _mid_toreg;
	}
	/***
	 * @作者 wangtao
	 * @日期 Jul 11, 2016
	 * @方法说明：调用存储过程进行正式进件信息拆分
	 * @返回参数 筛查结果
	 */
	public String proc_apply_split(String app_id){
		Connection conn = null;
		CallableStatement proc = null;
		String _split_result="0"; // 拆分成功标志，默认失败
		try {
			conn = DBUtils.getConn();
			//  调用存储过程进行正式进件信息拆分
			proc = conn.prepareCall("{ call PKG_LN_APPLY.PROC_APPLY_SPLIT(?,?) }"); //调用存储过程
			proc.setString(1, app_id); // 设置第一个参数输入参数
			proc.registerOutParameter(2, Types.VARCHAR); // 第二个参数输出参数,是VARCHAR类型的
			proc.execute(); // 执行
			_split_result = proc.getString(2);//获得输出参数
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeStatement(proc);
			DBUtils.closeConnection(conn);
		}
	    return _split_result;
	}
	/***
	 * @作者 wangtao
	 * @日期 Jul 11, 2016
	 * @方法说明：自动审批后 调用存储过程 根据审批结果 更新申请表申请状态 更新业务阶段表 并生成批意见表
	 * @返回参数 筛查结果
	 */
	public String proc_up_status(String app_id,String app_sts,String app_desc){
		Connection conn = null;
		CallableStatement proc = null;
		String _up_result = "0"; // 默认为失败
		try {
			conn = DBUtils.getConn();
		  	//  自动审批后 调用存储过程 根据审批结果 更新申请表申请状态 更新业务阶段表 并生成批意见表
		  	proc = conn.prepareCall("{ call PKG_LN_APPROVE.PROC_UP_STATUS(?,?,?,?) }"); //设置存储过程
			proc.setString(1, app_id);//设置第1个参数输入参数
			proc.setString(2, app_sts);        //设置第2个参数输入参数
			proc.setString(3, app_desc);        //设置第3个参数输入参数
			proc.registerOutParameter(4, Types.VARCHAR);//第4个参数输出参数,是VARCHAR类型的
			proc.execute();// 执行
			_up_result = proc.getString(4);//获得输出参数
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeStatement(proc);
			DBUtils.closeConnection(conn);
		}
	    return _up_result;
	}
	/***
	 * @作者 wangtao
	 * @日期 Jul 11, 2016
	 * @方法说明：调用存储过程 生成合同
	 * @返回参数 筛查结果
	 */
	public String proc_apply_topact(String app_id,String appr_type){
		Connection conn = null;
		CallableStatement proc = null;
		String _topact_sts = "0";// 生成合同成功标志 1成功 0失败
		try {
			conn = DBUtils.getConn();
			//  调用存储过程 生成合同
		  	proc = conn.prepareCall("{ call PKG_LN_PACT.PROC_APPLY_TOPACT(?,?,?) }"); //设置存储过程
		  	proc.setString(1, app_id); // 设置第1个参数输入参数 业务编号
		  	proc.setString(2, appr_type); // 设置第2个参数输入参数 审批类型
		  	proc.registerOutParameter(3, Types.VARCHAR);//第2个参数输出参数,是VARCHAR类型的
		  	proc.execute();// 执行
		  	_topact_sts = proc.getString(3);//获得输出参数
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeStatement(proc);
			DBUtils.closeConnection(conn);
		}
	    return _topact_sts;
	}
	/***
	 * @作者 wangtao
	 * @日期 Jul 11, 2016
	 * @方法说明：调用存储过程 生成借据
	 * @返回参数 筛查结果
	 */
	public String proc_pact_todue(String app_id){
		Connection conn = null;
		CallableStatement proc = null;
		String _todue_sts = "0";// 生成借据成功标志 1成功 0失败
		try {
			conn = DBUtils.getConn();
			// 调用存储过程生成借据
			proc = conn.prepareCall("{ call PKG_LN_DUE.PROC_PACT_TODUE(?,?) }");
			proc.setString(1, app_id); // 设置第1个参数输入参数 合同号
			proc.registerOutParameter(2, Types.VARCHAR);//第2个参数输出参数,是VARCHAR类型的
			proc.execute();// 执行
			_todue_sts = proc.getString(2);//获得输出参数
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeStatement(proc);
			DBUtils.closeConnection(conn);
		}
	    return _todue_sts;
	}
	/***
	 * @作者 wangtao
	 * @日期 Jul 11, 2016
	 * @方法说明：调用存储过程进行分账
	 * @返回参数 筛查结果
	 */
	public String proc_acc_fund(String due_no,String proj_no,Double due_amt,String mtr_date){
		Connection conn = null;
		CallableStatement proc = null;
		String _split_sts = "03";// 分账成功标志 [02成功 03失败]
		try {
			conn = DBUtils.getConn();
			// 调用存储过程进行分账
			proc = conn.prepareCall("{ call PKG_LN_DUE.PROC_ACC_FUND(?,?,?,?) }"); //设置存储过程
			proc.setString(1, due_no); // 设置第1个参数输入参数 借据编号
			proc.setString(2, proj_no); // 设置第2个参数输入参数 合作项目编号
			proc.setDouble(3, due_amt); // 设置第3个参数输入参数 借据金额
			proc.registerOutParameter(4, Types.VARCHAR);// 第5个参数输出参数,是VARCHAR类型的
			proc.execute();// 执行
			_split_sts = proc.getString(4);// 分账成功标志 [02成功 03失败]
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeStatement(proc);
			DBUtils.closeConnection(conn);
		}
	    return _split_sts;
	}
	/***
	 * @作者 wangtao
	 * @日期 Jul 11, 2016
	 * @方法说明：调用存储过程根据pactId删除合同表，借据表和申请有关的表
	 * @返回参数 筛查结果
	 */
	public String proc_pact_del(String pact_id){
		Connection conn = null;
		CallableStatement proc = null;
		String _del_result = "0"; // 默认删除失败
		try {
			conn = DBUtils.getConn();
		  	// 调用存储过程进行内部规则筛查
	      	proc = conn.prepareCall("{ call PKG_LN_PACT.PROC_PACT_DEL(?,?) }"); //设置存储过程
	      	proc.setString(1, pact_id);//设置第一个参数输入参数
	      	proc.registerOutParameter(2, Types.VARCHAR);//第二个参数输出参数,是VARCHAR类型的
	      	proc.execute();// 执行
	      	_del_result = proc.getString(2);//获得输出参数
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeStatement(proc);
			DBUtils.closeConnection(conn);
		}
	    return _del_result;
	}
	
	public void proc_up_stage(String appId,String columns,String value,String rsDesc){
		Connection conn = null;
		CallableStatement proc = null;
		try {
			  conn = DBUtils.getConn();
			  //conn = DBUtil.getConnection(); // 单独调用进行测试用
			  // 调用存储过程更新ln_stage业务阶段表
		      proc = conn.prepareCall("{ call PKG_LN_APPLY.PROC_UP_STAGE(?,?,?,?) }"); //设置存储过程
		      proc.setString(1, appId); //设置第1个参数输入参数
		      proc.setString(2, columns); //设置第2个参数输入参数
		      proc.setString(3, value); //设置第3个参数输入参数
		      proc.setString(4, rsDesc); //设置第4个参数输入参数
		      proc.execute(); // 执行
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeStatement(proc);
			DBUtils.closeConnection(conn);
		}
	}
	//自动审批失败删除申请表
	public String proc_apply_del(String appId){
		Connection conn = null;
		CallableStatement proc = null;
		String _del_result = "0"; // 默认删除失败
		try {
			  conn = DBUtils.getConn();
			  //conn = DBUtil.getConnection(); // 单独调用进行测试用
			  // 调用存储过程更新ln_stage业务阶段表
		      proc = conn.prepareCall("{ call PKG_LN_APPLY.PROC_APPLY_DEL(?,?) }"); //设置存储过程
		      proc.setString(1, appId); //设置第1个参数输入参数
		      proc.registerOutParameter(2, Types.VARCHAR);//第二个参数输出参数,是VARCHAR类型的
		      proc.execute(); // 执行
		      _del_result = proc.getString(2);//获得输出参数
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeStatement(proc);
			DBUtils.closeConnection(conn);
		}
		return _del_result;
	}
	//同步项目
	public void proc_proj_acct(){
		Connection conn = null;
		CallableStatement proc = null;
		try {
			  conn = DBUtils.getConn();
			  // 调用存储过程
		      proc = conn.prepareCall("{ call PKG_BAT_PATCH.PROC_PROJ_ACCT}");
		      proc.execute(); // 执行
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeStatement(proc);
			DBUtils.closeConnection(conn);
		}
	}
}
