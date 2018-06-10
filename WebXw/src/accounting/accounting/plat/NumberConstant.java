package accounting.plat;

/**
 * @describe 用于定义浮点类型数据相关信息
 * 
 */
public final class NumberConstant {

	/**
	 * 默认利息保留位数。
	 */
	public static int DEFAULT_INTST_DIG_COUNT = 12 ;

	/**
	 * 利率保留位数
	 */
	public static int DEFAULT_RATE_DIG_COUNT = 12 ;

	/**
	 * 利率比较值
	 */
	public static double DEFAULT_RATE_COMPARE_VALUE = 1E-9;

	/**
	 * 金额中间计算保留位数
	 */
	public static int DEFAULT_AMT_COMPARE_DIG_COUNT = 11;

	/**
	 * 浮点数比较值
	 */
	public static double DEFAULT_FLOAT_COMPARE_VALUE = 1E-8;
	
	/**
	 * 金额比较值
	 */
	public static double DEFAULT_AMOUNT_COMPARE_VALUE = 1E-7;
	
	/**
	 * 金额与0的比较值
	 */
	public static double DEFAULT_AMOUNT_ZERO_VALUE = 1E-4;

	/**
	 * 0的浮点数
	 */
	public final static Double ZERO_DOUBLE = Double.valueOf(0);
}
