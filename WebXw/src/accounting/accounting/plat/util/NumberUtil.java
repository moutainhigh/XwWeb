package accounting.plat.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import accounting.plat.NumberConstant;

public class NumberUtil {

	
	/**
	 * 两个利率相减,返回四舍五入
	 * 
	 * @param left1 算式左边第一个
	 * @param left2 算式左边第二个
	 * @return
	 */
	public static double rateSubs(double left1, double left2) {
		BigDecimal dec1 = getBigDecimal(left1,
				NumberConstant.DEFAULT_RATE_DIG_COUNT + 1);

		BigDecimal dec2 = getBigDecimal(left2,
				NumberConstant.DEFAULT_RATE_DIG_COUNT + 1);
		BigDecimal dec3 = dec1.subtract(dec2);
		
		return getBigDecimal(dec3).doubleValue();
	}
	
	/**
	 * 两个利率相除,返回四舍五入
	 * 
	 * @param left1 算式左边第一个
	 * @param left2 算式左边第二个
	 * @return
	 */
	public static double rateDiv(double left1, double left2) {
		return div(left1, left2, NumberConstant.DEFAULT_RATE_DIG_COUNT);
	}

	/**
	 * 两个利率相除,返回四舍五入
	 * 
	 * @param left1 算式左边第一个
	 * @param left2 算式左边第二个
	 * @param digCount 保留小数点位数
	 * @return
	 */
	public static double rateDiv(double left1, double left2, int digCount) {
		return div(left1, left2, digCount);
	}

	/**
	 * 利率相乘
	 * 
	 * @param left1 算式左边第一个
	 * @param left2 算式左边第二个
	 * @return
	 */
	public static double rateMult(double left1, double left2) {
		return mult(left1, left2, NumberConstant.DEFAULT_RATE_DIG_COUNT) ;
	}

	/**
	 * 利率相乘
	 * 
	 * @param left1 算式左边第一个
	 * @param left2 算式左边第二个
	 * @param digCount 小数点保留位数
	 * @return
	 */
	public static double rateMult(double left1, double left2,int digCount) {
		return mult(left1, left2, digCount, RoundingMode.HALF_UP ) ;
	}

	/**
	 * 判断两个利率是否相等
	 * 
	 * @param left1 算式左边第一个
	 * @param left2 算式左边第二个
	 * @return
	 */
	public static boolean isRateEqual(double rate1, double rate0) {
		double result = Math.abs(rateSubs(rate1, rate0));
		
		return getDouble(result, NumberConstant.DEFAULT_RATE_DIG_COUNT) <= NumberConstant.DEFAULT_RATE_COMPARE_VALUE;
	}

	/**
	 * rate1是否大于rate0
	 * 
	 * @param rate1
	 * @param rate0
	 * @return
	 */
	public static boolean isRateGreat(double rate1, double rate0) {
		double result = Math.abs(rateSubs(rate1, rate0));

		return getDouble(result, NumberConstant.DEFAULT_RATE_DIG_COUNT) > NumberConstant.DEFAULT_RATE_COMPARE_VALUE;
	}

	/**
	 * 两个数想减
	 * 
	 * @param left1 算式左边第一个
	 * @param left2 算式左边第二个
	 * @return
	 */
	public static double amtSubs(double left1, double left2) {
		BigDecimal dec1 = getBigDecimal(left1,
				NumberConstant.DEFAULT_AMT_COMPARE_DIG_COUNT + 1);
		BigDecimal dec2 = getBigDecimal(left2,
				NumberConstant.DEFAULT_AMT_COMPARE_DIG_COUNT + 1);
		BigDecimal dec3 = dec1.subtract(dec2);
		
		return getBigDecimal(dec3).doubleValue();
	}

	/**
	 * 两个数相乘。
	 * 
	 * @param left1 算式左边第一个
	 * @param left2 算式左边第二个
	 * @return
	 */
	public static double amtMult(double left1, double left2) {
		return mult(left1, left2, NumberConstant.DEFAULT_AMT_COMPARE_DIG_COUNT) ;
	}
	
	/**
	 * 两个数乘法
	 * 
	 * @param left1 算式左边第一个
	 * @param left2 算式左边第二个
	 * @param digCount  保留位数
	 * @param mode      小数点保留方式
	 * 
	 * @return 
	 */
	public static double mult(double left1, double left2, int digCount, RoundingMode mode) {
		BigDecimal dec1 = BigDecimal.valueOf(left1) ;
		BigDecimal dec2 = BigDecimal.valueOf(left2) ;

		BigDecimal dec3 = dec1.multiply(dec2) ;

		dec3 = dec3.setScale(digCount, mode);
		
		return dec3.doubleValue();
	}
	
	/**
	 * 两个数相乘,四舍五入
	 * 
	 * @param left1 算式左边第一个
	 * @param left2 算式左边第二个
	 * @param digCount  保留位数
	 * @return
	 */
	public static double mult(double left1, double left2, int digCount) {
		return mult(left1, left2, digCount, RoundingMode.HALF_UP);
	}

	/**
	 * 两个数相乘
	 * 
	 * @param left1 算式左边第一个
	 * @param left2 算式左边第二个
	 * @param digCount  保留位数
	 * @param roundingMode 整数化方法
	 * @return
	 */
	
	public static double amtMult(double left1, double left2, int digCount, RoundingMode roundingMode) {
		BigDecimal amt = getBigDecimal(left1 * left2, digCount + 1, roundingMode);

		return amt.doubleValue();
	}
	
	public static void main(String[] args) {
		System.out.println(amtMult(0.0345, 0.0345, 3, RoundingMode.HALF_UP));
	}

	/**
	 * 两个数除法,四舍五入
	 * 
	 * @param left1 算式左边第一个
	 * @param left2 算式左边第二个
	 * @param digCount  保留位数
	 * 
	 * @return 
	 */
	public static double div(double left1, double left2, int digCount){

		return div(left1, left2, digCount, RoundingMode.HALF_UP) ;
	}
	
	/**
	 * 两个数想除法
	 * 
	 * @param left1 算式左边第一个
	 * @param left2 算式左边第二个
	 * @param digCount  保留位数
	 * @param mode      小数点保留方式
	 * 
	 * @return 
	 */
	public static double div(double left1, double left2, int digCount, RoundingMode mode) {
		BigDecimal dec1 = getBigDecimal(left1, digCount+1, mode);
		BigDecimal dec2 = getBigDecimal(left2, digCount+1, mode);
		
		BigDecimal dec3 = dec1.divide(dec2, digCount, mode);
		
		return dec3.doubleValue();
	}

	
	/**
	 * 两个金额相除
	 * 
	 * @param left1 算式左边第一个
	 * @param left2 算式左边第二个
	 * 
	 * @return 
	 */
	public static double amtDiv(double left1, double left2) {

		return div(left1, left2, NumberConstant.DEFAULT_AMT_COMPARE_DIG_COUNT);
	}
	
	/**
	 * 两个金额相除,保留小数点位数
	 * 
	 * @param left1 算式左边第一个
	 * @param left2 算式左边第二个
	 * @param dig 小数点位数
	 * 
	 * @return 
	 */
	public static double amtDiv(double left1, double left2, int dig) {

		return div(left1, left2, dig);
	}

	/**
	 * 获取金额，四舍五入
	 * 
	 * @param amt   金额
	 * @return
	 */
	public static double getDouble(double amt) {
		
		return getDouble(amt, RoundingMode.HALF_UP) ;
	}

	/**
	 * 按照格式化获取金额
	 * 
	 * @param amt    金额
	 * @param mode   小数点保留方式
	 * @return
	 */
	public static double getDouble(double amt, RoundingMode mode) {
		BigDecimal dec1 = BigDecimal.valueOf(amt);
		dec1 = dec1.setScale(NumberConstant.DEFAULT_AMT_COMPARE_DIG_COUNT, mode);
		
		return dec1.doubleValue();
	}

	/**
	 * 获取最小值。
	 * 
	 * @param amt0
	 * @param amt1
	 * @return
	 */
	public static double getMin(double amt0, double amt1) {
		if (isAmtGreat(amt1, amt0)) {
			return amt0;
		}// 小于等于
		return amt1;
	}
	
	/**
	 * 指定保留位数，四舍五入。
	 * 
	 * @param amt        金额 
	 * @param digCount   小数点保留位数
	 * @return double
	 */
	public static double getDouble(double amt, int digCount) {
		
		return getDouble(amt, digCount, RoundingMode.HALF_UP) ;
	}

	/**
	 * 指定保留位数及小数点保留方式。
	 * 
	 * @param amt        金额 
	 * @param digCount   小数点保留位数
	 * @param mode   	 小数点保留方式
	 * @return double
	 */
	public static double getDouble(double amt, int digCount, RoundingMode mode) {
		BigDecimal dec1 = BigDecimal.valueOf(amt);
		dec1 = dec1.setScale(digCount, mode);
		
		return dec1.doubleValue();
	}
	
	/**
	 * 指定保留位数，四舍五入。
	 * 
	 * @param amt        金额 
	 * @param digCount   小数点保留位数
	 * 
	 * @return BigDecimal
	 */
	public static BigDecimal getBigDecimal(double amt, int digCount) {
		BigDecimal dec1 = getBigDecimal(amt, digCount, RoundingMode.HALF_UP);
		
		return dec1;
	}

	/**
	 * 指定保留位数及小数点保留方式。
	 * 
	 * @param amt        金额 
	 * @param digCount   小数点保留位数
	 * @param mode   	 小数点保留方式
	 * 
	 * @return BigDecimal
	 */
	public static BigDecimal getBigDecimal(double amt, int digCount, RoundingMode mode) {
		BigDecimal dec1 = BigDecimal.valueOf(amt);
		dec1 = dec1.setScale(digCount, mode);
		
		return dec1;
	}

	/**
	 * double转BigDecimal，默认金额保留位数四舍五入
	 * 
	 * @param amt        金额 
	 * 
	 * @return BigDecimal
	 */
	public static BigDecimal getBigDecimal(double amt) {
		BigDecimal dec1 = getBigDecimal(amt, RoundingMode.HALF_UP);
		
		return dec1;
	}

	/**
	 * double转BigDecimal，默认金额保留位数
	 * 
	 * @param amt        金额 
	 * @param mode       小数点保留方式
	 * 
	 * @return BigDecimal
	 */
	public static BigDecimal getBigDecimal(double amt, RoundingMode mode) {
		BigDecimal dec1 = BigDecimal.valueOf(amt);
		dec1 = dec1.setScale(NumberConstant.DEFAULT_AMT_COMPARE_DIG_COUNT, mode);
		
		return dec1;
	}

	/**
	 * BigDecimal四舍五入，保留默认金额位数
	 * 
	 * @param amt        金额 
	 * 
	 * @return BigDecimal
	 */
	public static BigDecimal getBigDecimal(BigDecimal amt) {
		amt = getBigDecimal(amt, RoundingMode.HALF_UP);
		
		return amt;
	}

	/**
	 * BigDecimal，保留默认金额位数
	 * 
	 * @param amt        金额
	 * @param mode       小数点保留方式 
	 * 
	 * @return BigDecimal
	 */
	public static BigDecimal getBigDecimal(BigDecimal amt, RoundingMode mode) {
		amt = amt.setScale(NumberConstant.DEFAULT_AMT_COMPARE_DIG_COUNT, mode);
		
		return amt;
	}

	/**
	 * 两个相加
	 * @param left1 算式左边第一个
	 * @param left2 算式左边第二个
	 * @return
	 */
	public static double amtAdd(double left1, double left2) {
		
		BigDecimal dec1 = getBigDecimal(left1,
				NumberConstant.DEFAULT_AMT_COMPARE_DIG_COUNT + 1);
		BigDecimal dec2 = getBigDecimal(left2,
				NumberConstant.DEFAULT_AMT_COMPARE_DIG_COUNT + 1);
		BigDecimal dec3 = dec1.add(dec2);
		
		return getBigDecimal(dec3).doubleValue();
	}

	/**
	 * 第一个数是比较第二个数 如果左边>右边返回1 如果相等 返回0 如果左边<右边返回负数
	 * 
	 * @param left1
	 * @param left2
	 * @return
	 */
	public static int amtCompare(double left1, double left2) {
		double result = amtSubs(left1, left2);
		if (result > NumberConstant.DEFAULT_AMOUNT_ZERO_VALUE) {
			return 1;
		}
		if (Math.abs(result) <= NumberConstant.DEFAULT_AMOUNT_ZERO_VALUE)
			return 0;
		return -1;
	}
	/**
	 * 判断两个金额是否相等，返回true、false
	 * 
	 * @param amt1
	 * @param amt2
	 * 
	 * @return boolean
	 */
	public static boolean isAmtEqual(double amt1, double amt0) {
		double result = Math.abs(amtSubs(amt1, amt0));
		return getDouble(result, 2) < NumberConstant.DEFAULT_AMOUNT_ZERO_VALUE;
	}

	/**
	 * 第一个数是否大于第二个数，返回true、false
	 * 
	 * @param amt1
	 * @param amt0
	 * 
	 * @return boolean
	 */
	public static boolean isAmtGreat(double amt1, double amt0) {
		double result = amtSubs(amt1, amt0);
		if (result > NumberConstant.DEFAULT_AMOUNT_ZERO_VALUE) {
			return true;
		}
		return false;
	}

	/**
	 * 第一个数是否大于等于第二个数，返回true、false
	 * 
	 * @param amt1
	 * @param amt0
	 * 
	 * @return boolean
	 */
	public static boolean isAmtGreatAndEqual(double amt1, double amt0) {
		double result = amtSubs(amt1, amt0);
		if (result >= -NumberConstant.DEFAULT_AMOUNT_ZERO_VALUE) {
			return true;
		}
		return false;
	}

	/**
	 * 第一个数是否大于等于第二个数，返回true、false
	 * 
	 * @param amt1
	 * @param amt0
	 * 
	 * @return boolean
	 */
	public static boolean isAmtGreatEqual(double amt1, double amt0) {
		return isAmtGreatAndEqual(amt1, amt0);
	}

	/**
	 * 判断金额是否大于0，返回true、false
	 * 
	 * @param amt
	 * 
	 * @return boolean
	 */
	public static boolean isAmtGreatThanZero(double amt) {
		if (amt > NumberConstant.DEFAULT_AMOUNT_ZERO_VALUE) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断金额是否小于0，返回true、false
	 * 
	 * @param amt
	 * 
	 * @return boolean
	 */
	public static boolean isAmtLessThanZero(double amt) {
		return amt < -NumberConstant.DEFAULT_AMOUNT_ZERO_VALUE;
	}

	/**
	 * 判断一个金额是否<=0;
	 * 
	 * @param amt
	 * 
	 * @return boolean
	 */
	public static boolean isAmtLessThanOrEqualZero(double amt) {
		if (amt <= NumberConstant.DEFAULT_AMOUNT_ZERO_VALUE) {
			return true;
		}
		return false;
	}

	/**
	 * 判断金额是否=0,返回true、false
	 * 
	 * @param amt
	 * 
	 * @return boolean
	 */
	public static boolean isAmtEqualZero(double amt) {
		if (Math.abs(amt) <= NumberConstant.DEFAULT_AMOUNT_ZERO_VALUE) {
			return true;
		}
		return false;
	}

	/**
	 * 格式化浮点数。
	 * 
	 * @param d
	 * @param count数位
	 * @return
	 */
	public static String formatDec(double d, int count) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < count; i++) {
			sb.append("0");
		}
		NumberFormat format = new DecimalFormat("#0." + sb.toString());
		return format.format(d);
	}

}
