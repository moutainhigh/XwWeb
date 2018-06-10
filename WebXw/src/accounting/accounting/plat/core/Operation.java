package accounting.plat.core;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.SQLException;

import accounting.domain.sys.AcSysLog;
import accounting.domain.sys.AfferentDomain;
import accounting.plat.PUBConstant;
import accounting.plat.dao.JdbcDao;

public abstract class Operation {

	private String id;                       // OP注册XML中的表示OP的唯一标识， 也是交易码
	private String describe;                 // OP注册XML中的描述信息
	private Connection connection;           // 连接
	private AfferentDomain afferentDomain;   // 传递业务数据对象
	private String traceNo;                    // 交易流水号
	private boolean oldAutoCommit;           // 保存传递过来连接的自动提交属性值，用于核算模块使用后再把连接的自动提交属性值还原
	//private HashMap<String, String> configTable = new HashMap<String, String>();

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	protected Connection getConnection() throws AccountingException {

		if (connection == null) {
			throw new AccountingException("连接为空");
		}
		return connection;
	}

	public String getDescribe() {
		return this.describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public AfferentDomain getAfferentDomain() {
		return afferentDomain;
	}

	public void setAfferentDomain(AfferentDomain afferentDomain) {
		this.afferentDomain = afferentDomain;
	}

	public String getTraceNo() {
		return traceNo;
	}

	protected void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
	}

	public boolean isOldAutoCommit() {
		return oldAutoCommit;
	}

	public void setOldAutoCommit(boolean oldAutoCommit) {
		this.oldAutoCommit = oldAutoCommit;
	}

	/** 
	 * 所以OP的入口方法
	 * @param afferentDomain 传递业务数据对象
	 * 
	 */
	public final Object execute(AfferentDomain afferentDomain)
			throws AccountingException {
		String message=null;	// 运行状态信息
		String flag=null;		// 运行成功标志
		if(!PUBConstant.SYS_STS.equals("0")){
			throw new AccountingException("系统当前状态不是\"正常\",不能进行交易");
		}
		try {
			this.connection.setAutoCommit(false);
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new AccountingException(e1);
		}
		Object ob = null;
		try {
			ob = doExecute(afferentDomain);
			try {
				connection.commit();
				message = "执行成功";
				flag = "Y";
			} catch (SQLException e) {
				e.printStackTrace();
				throw new AccountingException(e);
			}
		} catch(AccountingException e) {
			try {
				message = e.getMessage();
				flag = "N";
				connection.rollback();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new AccountingException(e1);
			}
			throw e;
		}catch (Exception e) {
			try {
				message = e.getMessage();
				flag = "N";
				connection.rollback();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new AccountingException(e1);
			}
			throw new AccountingException(e);
		} finally {
			try {
				insertSysLog(afferentDomain,message,flag,connection);		// 交易执行记录
				this.connection.setAutoCommit(this.oldAutoCommit);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new AccountingException(e);
			}
		}
		return ob;
	}

	/**
	 * 交易执行记录
	 * @param afferentDomain 传递业务数据对象
	 * @param message 交易运行状态信息
	 * @param flag 交易运行成功标志
	 * @throws AccountingException
	 */
	private void insertSysLog(AfferentDomain afferentDomain, String reason, String flag,Connection conn)
			throws AccountingException {
		try {
			StringBuffer message = new StringBuffer();
			AcSysLog acSysLog = new AcSysLog();
			acSysLog.setTraceNo(traceNo);					// 设置流水号
			acSysLog.setTxDt(PUBConstant.CUR_PRCS_DT);		// 设置交易日期
			acSysLog.setExecInd(flag);						// 设置交易运行成功标志 
			acSysLog.setReason(reason);				    	// 设置交易执行状态信息
			acSysLog.setTxBrNo(afferentDomain.getBrNo());	// 设置交易机构号
			acSysLog.setTxCde(id);							// 设置交易代码
			acSysLog.setUsrId(afferentDomain.getUsrId());	// 设置柜员号
			
			/**
			 * 设置交易参数列表
			 */
			BeanInfo beanInfo = Introspector.getBeanInfo(afferentDomain.getClass());
			PropertyDescriptor[] des = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor pro : des) {
				if(pro.getName().equals("class")){
					continue;
				}
				message.append(pro.getName()+"="+(pro.getReadMethod().invoke(afferentDomain)==null?"":pro.getReadMethod().invoke(afferentDomain).toString())+"&");
				if (pro.getName().equals("loanNo")) {
					// 设置借据号
					acSysLog.setLoanNo(pro.getReadMethod().invoke(afferentDomain)==null?"":pro.getReadMethod().invoke(afferentDomain).toString());
				}
			}
			acSysLog.setTraceValue01(message.substring(0, message.length()-3));
			JdbcDao.insert(acSysLog, "ac_sys_log", connection);
			connection.commit();
		} catch (Exception e1) {
			try {
				connection.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			e1.printStackTrace();
			throw new AccountingException(e1);
		}
	}

	/**
	 * 子类必须实现的方法，在该方法内进行业务逻辑处理
	 * @param afferentDomain 传递业务数据对象
	 * 
	 */
	public abstract Object doExecute(AfferentDomain afferentDomain)
			throws AccountingException;

}
