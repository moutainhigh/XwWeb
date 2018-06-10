package accounting.plat.core;


public class AccountingException extends Exception{
	
	/**
	 * 
	 */
	//private static final long serialVersionUID = -2332975704789408379L;
	
	private String errCode;               //异常码
	
	private int level=0;                  //异常级别
	
	/**
 	 * @return level 交易机构号
 	 */
	public int getLevel() {
		return level;
	}
	/**
 	 * @设置  异常级别
 	 * @param level
 	 */

	private void setLevel(int level) {
		this.level = level;
	}

	/**
 	 * @return errCode 异常码
 	 */
	public String getErrCode() {
		return errCode;
	}
	/**
 	 * @设置  异常码
 	 * @param errCode
 	 */
	private void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	private AccountingException() {
		super();
	}

	private AccountingException(String message, Throwable cause, String errCode) {
		super(message, cause);
		this.errCode = errCode;
	}
	private AccountingException(String message, String errCode) {
		super(message);
		this.errCode = errCode;
	}

	private AccountingException(String message, Throwable cause) {
		super(message, cause);
	}
	/**
	 *   构造方法
	 *   当 检验 的时候，不通过，需要以异常的形式进行反馈。
	 *   但是这种情况不是程序运行问题，而是业务及其数据的问题，所有级别level定义为1
	 * @param message   需要抛出的反馈信息 
	 */
	public AccountingException(String message) {
		super(message);
		level=1;
	}
	/**
	 *   构造方法
	 *   当调用某方法报错的时候，作为调用方不知道错误的原因，所有把这类问题归纳为程序异常，定义级别为 0
	 * @param cause   Throwable的所有子类   即所有的异常类的对象
	 */
	public AccountingException(Throwable cause) {
		super(cause);
		level=0;
	}
}
