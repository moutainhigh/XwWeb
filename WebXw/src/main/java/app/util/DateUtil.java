package app.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;

import app.base.ServiceException;
import app.base.SourceTemplate;
import app.creditapp.dao.SysGlobalDAO;
import app.creditapp.entity.SysGlobal;

/**
 * 功能描述：日期处理工具类（基于Calendar） 主要功能：日期校验；获取系统当前日期（可自定义系统日期）；判断闰年；获取连个日期之间的天数，月数；
 * 判定日期的前后；将字符串转换为Date或Calendar等... 日期格式默认：yyyyMMdd
 * 
 * @see null
 * @修改日志：1.0
 */
public class DateUtil extends Object {

	/**
	 * 当前操作系统日期 Calendar
	 */
	private static Calendar calendar = new GregorianCalendar(
			TimeZone.getDefault());
	/**
	 * 日期格式 默认：yyyyMMdd
	 */
	private static String pattern = "yyyyMMdd";
	/**
	 * 时间格式 默认：HH:mm:ss
	 */
	private static String timePattern = "HH:mm:ss";

	/**
	 * 年
	 */
	private static int year = 0;
	/**
	 * 月
	 */
	private static int month = 0;
	/**
	 * 日
	 */
	private static int day = 0;
	/**
	 * 时
	 */
	private static int hour = 0;
	/**
	 * 分
	 */
	private static int minute = 0;
	/**
	 * 秒
	 */
	private static int second = 0;

	/**
	 * 静态初始化（默认系统当前日期和时间）
	 */
	static {
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH) + 1;
		day = calendar.get(Calendar.DAY_OF_MONTH);
		hour = calendar.get(Calendar.HOUR_OF_DAY);
		minute = calendar.get(Calendar.MINUTE);
		second = calendar.get(Calendar.SECOND);
	}

	/**
	 * 构造方法
	 */
	private DateUtil() {
		// Do Nothing
	}

	/**
	 * 取系统营业日期
	 * 
	 * @return
	 */
	public static String getSysDate() {
		SysGlobalDAO dao = SourceTemplate.getSpringContextInstance().getBean(
				"sysGlobalDAO", SysGlobalDAO.class);
		SysGlobal sysGlobal = dao.getSysGlobal();
		return sysGlobal.getSys_date();
	}

	/**
	 * 取系统信息
	 * 
	 * @return
	 */
	public static SysGlobal getSysGlobal() {
		SysGlobalDAO dao = SourceTemplate.getSpringContextInstance().getBean(
				"sysGlobalDAO", SysGlobalDAO.class);
		SysGlobal sysGlobal = dao.getSysGlobal();
		return sysGlobal;
	}

	/**
	 * 功能描述：自定义系统时间。（谨慎使用）
	 * 
	 * @param strdate
	 *            自定义日期字符串，格式：yyyymmdd
	 * @修改日志：1.0
	 */
	public static void setSysDate(String strdate) {
		if (isDateStr(strdate)) {
			calendar = parseCalendar(strdate);

			year = calendar.get(Calendar.YEAR);
			month = calendar.get(Calendar.MONTH) + 1;
			day = calendar.get(Calendar.DAY_OF_MONTH);
			hour = calendar.get(Calendar.HOUR_OF_DAY);
			minute = calendar.get(Calendar.MINUTE);
			second = calendar.get(Calendar.SECOND);
		}
	}

	/**
	 * 功能描述： 初始化系统日期(当前系统日期)调用setSysDate()后会用到次方法重新初始化系统日期时间 为当前日期时间
	 * 
	 * @修改日志：1.0
	 */
	public static void initSys() {
		calendar = new GregorianCalendar(TimeZone.getDefault());

		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH) + 1;
		day = calendar.get(Calendar.DAY_OF_MONTH);
		hour = calendar.get(Calendar.HOUR_OF_DAY);
		minute = calendar.get(Calendar.MINUTE);
		second = calendar.get(Calendar.SECOND);
	}

	/**
	 * 功能描述：获取系统当前日期---年
	 * 
	 * @return int 年
	 * @修改日志：1.0
	 */
	public static int getYear() {
		return year;
	}

	/**
	 * 功能描述：获取系统当前日期---年
	 * 
	 * @return String 年
	 * @修改日志：1.0
	 */
	public static String getStrYear() {
		return String.valueOf(year);
	}

	/**
	 * 功能描述：获取系统当前日期---月
	 * 
	 * @return int 月
	 * @修改日志：1.0
	 */
	public static int getMonth() {
		return month;
	}

	/**
	 * 功能描述：获取系统当前日期---月
	 * 
	 * @return String 月
	 * @修改日志：1.0
	 */
	public static String getStrMonth() {
		return month >= 10 ? String.valueOf(month) : "0"
				+ String.valueOf(month);
	}

	/**
	 * 功能描述：获取系统当前日期---日
	 * 
	 * @return int 日
	 * @修改日志：1.0
	 */
	public static int getDay() {
		return day;
	}

	/**
	 * 功能描述：获取系统当前日期---日
	 * 
	 * @return String 日
	 * @修改日志：1.0
	 */
	public static String getStrDay() {
		return day < 10 ? "0" + String.valueOf(day) : String.valueOf(day);
	}

	/**
	 * 
	 * 功能描述：获取系统时间--小时
	 * 
	 * @return int
	 * @修改日志：1.0
	 */
	public static int getHour() {
		return hour;
	}

	/**
	 * 
	 * 功能描述：获取系统时间--分钟
	 * 
	 * @return int
	 * @修改日志：
	 */
	public static int getMinute() {
		return minute;
	}

	/**
	 * 
	 * 功能描述：获取系统时间--秒
	 * 
	 * @return int
	 * @修改日志：
	 */
	public static int getSecond() {
		return second;
	}

	/**
	 * 功能描述：获取系统当前日期---年月日 （格式：yyyymmdd）
	 * 
	 * @return String 年月日
	 * @修改日志：1.0
	 */
	public static String getDate() {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = calendar.getTime();
		return format.format(date);
	}
	/**
	 * 功能描述：获取系统当前日期---年月日 （格式：yyyymmdd）
	 * 
	 * @return String 时分秒
	 * @修改日志：1.0
	 */
	public static String getTime() {
		Calendar calendarTime = new GregorianCalendar(TimeZone.getDefault());
		SimpleDateFormat format = new SimpleDateFormat(timePattern);
		Date date = calendarTime.getTime();
		return format.format(date);
	}

	/**
	 * 功能描述：根据预定格式取系统当前日期---年月日
	 * 
	 * @param ptn
	 *            日期格式
	 * @return String
	 * @修改日志：1.0
	 */
	public static String getDate(String ptn) {
		SimpleDateFormat format = new SimpleDateFormat(ptn);
		Date date = calendar.getTime();
		return format.format(date);
	}

	/**
	 * 功能描述：获取系统时间 格式：yyyymmdd hh:mm:ss
	 * 
	 * @return String
	 * @修改日志：1.0
	 */
	public static String getDateTime() {
		Calendar calendarTime = new GregorianCalendar(TimeZone.getDefault());
		SimpleDateFormat format = new SimpleDateFormat(pattern + " "
				+ timePattern);
		Date date = calendarTime.getTime();
		return format.format(date);
	}

	/**
	 * 
	 * 功能描述：获取预定义格式的系统时间
	 * 
	 * @param datePtn
	 *            日期格式
	 * @param timePtn
	 *            时间格式
	 * @return String
	 * @修改日志：1.0
	 */
	public static String getDateTime(String datePtn, String timePtn) {
		SimpleDateFormat format = new SimpleDateFormat(datePtn + " " + timePtn);
		Date date = calendar.getTime();
		return format.format(date);
	}

	/**
	 * 功能描述：判断给定日期（格式yyyymmdd）是否在系统日期之前，是（或等于）：true，否：false
	 * 
	 * @param strdate
	 *            给定日期
	 * @return boolean
	 * @修改日志：1.0
	 */
	public static boolean isBefore(String strdate) {
		Calendar cal = parseCalendar(strdate);
		return cal.before(calendar);
	}
	/**
	 * 功能描述：判断给定日期（格式yyyymmdd）是否在系统日期之前，是（或等于）：true，否：false
	 * 
	 * @param strdate
	 *            给定日期
	 * @return boolean
	 * @修改日志：1.0
	 */
	public static boolean isBeforeSydate(String strdate) {
		Calendar cal1 = parseCalendar(strdate);//921
		Calendar cal2 =   parseCalendar(getSysDate());//920
		return cal2.before(cal1);
	}
	/**
	 * 
	 * 功能描述：判断给定的两个日期的前后。strdate1在strdate2之前（或同一天），返回true，反之，false
	 * 
	 * @param strdate1
	 *            日期1
	 * @param strdate2
	 *            日期2
	 * @return boolean
	 * @修改日志：1.0
	 */
	public static boolean isBefore(String strdate1, String strdate2) {
		Calendar cal1 = parseCalendar(strdate1);
		Calendar cal2 = parseCalendar(strdate2);
		return cal1.before(cal2);
	}

	/**
	 * 
	 * 功能描述：计算在当前系统日期增加或减少 n 天后的日期
	 * 
	 * @param days
	 *            增加或减少的天数，正数增加，反之减少
	 * @修改日志：
	 */
	public static String addByDay(int days) {
		initSys();
		calendar.add(Calendar.DATE, days);
		return getDate();
	}

	/**
	 * 
	 * 功能描述：计算在给定的日期加上或减去 n 天后的日期
	 * 
	 * @param datestr
	 *            给定的日期
	 * @param days
	 *            正数增加，反之减少
	 * @return String
	 * @修改日志：
	 */
	public static String addByDay(String datestr, int days) {
		Calendar cal = parseCalendar(datestr);
		cal.add(Calendar.DATE, days);
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = cal.getTime();
		return format.format(date);
	}
	/**
	 * 功能描述：给定时间加一月，格式为   yyyyMMdd
	 * @param time 给定时间
	 * @return 增加之后的时间
	 * @throws ParseException 
	 */
	public static String addBMonth(String time) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		java.util.Date date=sdf.parse(time); 
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, -1);
		return sdf.format(c.getTime());
	}
	/**
	 * 
	 * 功能描述：获得给定日期与系统当前日期之间的天数
	 * 
	 * @param strdate
	 *            给定的日期字符串
	 * @return long 天数
	 * @修改日志：1.0
	 */
	public static long getDays(String strdate) {
		Calendar cal = parseCalendar(strdate);
		Calendar cal1 = parseCalendar(getDate());
		long millis = Math.abs(cal.getTimeInMillis() - cal1.getTimeInMillis());
		return millis / (24 * 60 * 60 * 1000);
	}

	/**
	 * 
	 * 功能描述：获得给定的两个日期之间相差的天数（日期不分前后）
	 * 
	 * @param fromdate
	 *            日期字符串 格式：yyyymmdd
	 * @param todate
	 *            日期字符串 格式：yyyymmdd
	 * @return long
	 * @修改日志：1.0
	 */
	public static long getDaysBetween(String fromdate, String todate) {
		Calendar from = parseCalendar(fromdate);
		Calendar to = parseCalendar(todate);
		long millis = Math.abs(from.getTimeInMillis() - to.getTimeInMillis());
		return millis / (24 * 60 * 60 * 1000);
	}

	/**
	 * 返回Date *
	 * 
	 * @param str
	 *            yyyy-MM-dd String
	 * @return Date
	 */
	public static Date parseTenStrToDate(String str) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		try {
			d = df.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 返回Date *
	 * 
	 * @param str
	 *            yyyyMMdd String
	 * @return Date
	 */
	public static Date parseEightStrToDate(String str) {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date d = null;
		try {
			d = df.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 返回String *
	 * 
	 * @param date
	 *            Date
	 * @return String yyyyMMdd
	 */
	public static String parseDateToEightStr(Date date) {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(date);
	}

	/**
	 * 返回String *
	 * 
	 * @param date
	 *            Date
	 * @return String yyyy-MM-dd
	 */
	public static String parseDateToTenStr(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}

	/**
	 * 
	 * 功能描述：获得给定日期与系统当前日期之间的月数，不记天数
	 * 
	 * @param strdate
	 *            给定的日期字符串
	 * @return long 月数
	 * @修改日志：待定
	 */
	private static long getMonths(String strdate) {
		long months = getMonth() - Integer.parseInt(strdate.substring(4, 6));
		long years = getYear() - Integer.parseInt(strdate.substring(0, 4));
		if (!isBefore(strdate)) {
			months = -months;
			years = -years;
		}
		if (months >= 0) {
			return years * 12 + months;
		} else {
			return (years - 1) * 12 + months + 12;
		}
	}

	/**
	 * 
	 * 功能描述：获得两个日期之间的月差数，不记天数
	 * 
	 * @param strdate1
	 * @param strdate2
	 * @return long
	 * @修改日志：待定
	 */
	public static long getMonths(String strdate1, String strdate2) {
		long m = 0;
		setSysDate(strdate1);
		m = getMonths(strdate2);
		initSys();
		return m;
	}

	/**
	 * 
	 * 功能描述：获得给定日期与系统当前日期之间的月数和天数
	 * 
	 * @param strdate
	 *            给定的日期字符串
	 * @return long[] 下标0月数，1天数
	 * @修改日志：待定
	 */
	public static long[] getMonthsAndDays(String strdate) {
		long m = getMonths(strdate);
		int d = getDay() - Integer.parseInt(strdate.substring(6, 8));
		String date = "";
		if (!isBefore(strdate)) {
			d = -d;
			date = strdate;
		} else {
			date = getDate();
		}
		while (d < 0) {
			m--;
			d += getDaysOfMonth(date);
		}
		long[] md = { m, d };
		return md;
	}

	/**
	 * 
	 * 功能描述：获得给定两个日期之间的月数和天数
	 * 
	 * @param strdate1
	 * @param strdate2
	 * @return long[] 下标0月数，1天数
	 * @修改日志：
	 */
	public static long[] getMonthsAndDays(String strdate1, String strdate2) {
		long m = getMonths(strdate1, strdate2);
		int d = Integer.parseInt(strdate1.substring(6, 8))
				- Integer.parseInt(strdate2.substring(6, 8));
		String date = "";
		if (!isBefore(strdate1, strdate2)) {
			date = strdate1;
		} else {
			d = -d;
			date = strdate2;
		}
		while (d < 0) {
			m--;
			d += getDaysOfMonth(date);
		}
		long[] md = { m, d };
		return md;
	}

	/**
	 * 功能描述：判断字符串是否可以转换为日期型 是：true，否：false
	 * 
	 * @param strdate
	 *            预转换字符串
	 * @return boolean
	 * @修改日志：1.0
	 */
	public static boolean isDateStr(String strdate) {
		if (strdate.length() != 8) {
			return false;
		}

		String reg = "^(\\d{4})((0([1-9]{1}))|(1[012]))((0[1-9]{1})|([1-2]([0-9]{1}))|(3[0|1]))$";

		if (Pattern.matches(reg, strdate)) {
			return getDaysOfMonth(strdate) >= Integer.parseInt(strdate
					.substring(6, 8));
		} else {
			return false;
		}
	}

	/**
	 * 功能描述：判断是否是闰年（年限1000--9999）是：true，否：false
	 * 
	 * @param strdate
	 *            预判断年 格式yyyymmdd 或 yyyy
	 * @return boolean
	 * @修改日志：1.0
	 */
	public static boolean isLeapYear(String strdate) {
		int y = Integer.parseInt(strdate.substring(0, 4));
		if (y <= 999) {
			return false;
		}
		if (y % 400 == 0 || (y % 4 == 0 && y % 100 != 0)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 功能描述：获取某一月份的天数
	 * 
	 * @param strdate
	 *            日期 格式：yyyymmdd 或 yyyymm
	 * @return int
	 * @修改日志：1.0
	 */
	public static int getDaysOfMonth(String strdate) {
		int m = Integer.parseInt(strdate.substring(4, 6));
		switch (m) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			return 31;
		case 4:
		case 6:
		case 9:
		case 11:
			return 30;
		case 2:
			if (isLeapYear(strdate)) {
				return 29;
			} else {
				return 28;
			}
		default:
			return 0;
		}
	}
	/**
	 * 得到某年某月的天数 *
	 * 
	 * @param year
	 *            int
	 * @param month
	 *            int
	 * @return int
	 */
	public static int getMonthDays(int year, int month) {
		int days = 1;
		boolean isrn = (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) ? true
				: false;
		switch (month) {
		case 1:
			days = 31;
			break;
		case 2:
			if (isrn)
				days = 29;
			else
				days = 28;
			break;
		case 3:
			days = 31;
			break;
		case 4:
			days = 30;
			break;
		case 5:
			days = 31;
			break;
		case 6:
			days = 30;
			break;
		case 7:
			days = 31;
			break;
		case 8:
			days = 31;
			break;
		case 9:
			days = 30;
			break;
		case 10:
			days = 31;
			break;
		case 11:
			days = 30;
			break;
		case 12:
			days = 31;
		}
		return days;
	}

	/**
	 * 功能描述：把字符串转换为Calendar
	 * 
	 * @param strdate
	 *            预转换的字符串
	 * @return Calendar
	 * @修改日志：1.0
	 */
	public static Calendar parseCalendar(String strdate) {
		if (isDateStr(strdate)) {
			int year = Integer.parseInt(strdate.substring(0, 4));
			int month = Integer.parseInt(strdate.substring(4, 6)) - 1;
			int day = Integer.parseInt(strdate.substring(6, 8));
			return new GregorianCalendar(year, month, day);
		} else {
			return null;
		}
	}

	/**
	 * 功能描述：将字符串转换为Date型日期 日期格式yyyymmdd
	 * 
	 * @param strdate
	 *            预转换的字符串
	 * @return Date
	 * @修改日志：1.0
	 */
	public static Date parseDate(String strdate) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date d = null;
		try {
			d = format.parse(strdate);
		} catch (Exception pe) {
			pe.printStackTrace();
		}
		return d;
	}

	public static final String getDateTime(Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";
		if (aDate != null) {
			df = new SimpleDateFormat(pattern);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * 根据日期取得星期几
	 * 
	 * @param DateStr yyyyMMdd
	 * @return
	 */
	public static String getWeekDay(String DateStr) {
		SimpleDateFormat formatYMD = new SimpleDateFormat("yyyyMMdd");// formatYMD表示的是yyyyMMdd格式
		SimpleDateFormat formatD = new SimpleDateFormat("E");// "E"表示"day in week"
		Date d = null;
		String weekDay = "";
		try {
			d = formatYMD.parse(DateStr);// 将String 转换为符合格式的日期
			weekDay = formatD.format(d);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return weekDay;
	}

	/**
	 * 根据每月第一天星期几和一月天数确定行数
	 * 
	 * @param week
	 * @param days
	 * @return
	 */
	public static int line(String week, int days) {
		int line = 0;
		if (week.equals("星期日")) {
			if (days >= 29) {
				line = 5;
			} else {
				line = 4;
			}
		} else if (week.equals("星期一")) {
			line = 5;
		} else if (week.equals("星期二")) {
			line = 5;
		} else if (week.equals("星期三")) {
			line = 5;
		} else if (week.equals("星期四")) {
			line = 5;
		} else if (week.equals("星期五")) {
			if (days >= 31) {
				line = 6;
			} else {
				line = 5;
			}
		} else if (week.equals("星期六")) {
			if (days >= 30) {
				line = 6;
			} else {
				line = 5;
			}
		}
		return line;
	}

	/**
	 * 根据参数busidate返回上一月份的6位日期(YYYYMM格式)
	 * 
	 * @param busidate yyyyMM
	 * @return
	 */
	public static String getLastMonth(String busidate) {
		String last_month = null;
		if (busidate == null || busidate.length() < 6)
			return null;
		int month = Integer.parseInt(busidate.substring(4));
		int year = Integer.parseInt(busidate.substring(0, 4));
		if (month == 1) {
			last_month = (year - 1) + "12";
		} else {
			month--;
			if (month > 9) {
				last_month = year + String.valueOf(month);
			} else {
				last_month = year + "0" + String.valueOf(month);
			}
		}
		return last_month;
	}

	/**
	 * 返回日期经过若干月后的日期(传入几位日期便返回多少位的格式)
	 * 
	 * @param dateStr yyyy-MM-dd
	 * @param hkm
	 *            int
	 * @return String
	 */
	public static String getDateStr(String dateStr, int hkm) {
		String st_return = "";
		boolean isEight = true;
		if (dateStr.split("-").length == 3) {
			isEight = false;
		} else {
			dateStr = dateStr.substring(0, 4) + "-" + dateStr.substring(4, 6)
					+ "-" + dateStr.substring(6, 8);
		}
		try {
			DateFormat daf_date = DateFormat.getDateInstance(DateFormat.MEDIUM,
					Locale.CHINA);
			daf_date.parse(dateStr);
			Calendar calendar = daf_date.getCalendar();
			calendar.add(Calendar.MONTH, hkm);
			String st_m = "";
			String st_d = "";
			int y = calendar.get(Calendar.YEAR);
			int m = calendar.get(Calendar.MONTH)+1;
			int d = calendar.get(Calendar.DAY_OF_MONTH);
			if (m <= 9) {
				st_m = "0" + m;
			} else {
				st_m = "" + m;
			}
			if (d <= 9) {
				st_d = "0" + d;
			} else {
				st_d = "" + d;
			}
			if (isEight) {
				st_return = y + "" + st_m + "" + st_d;
			} else {
				st_return = y + "-" + st_m + "-" + st_d;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return st_return;
	}

	/**
	 * 返回两位数据字串 *
	 * 
	 * @param sz
	 *            int
	 * @return String
	 */
	public static String bZero(int sz) {
		return (sz < 10 ? ("0" + String.valueOf(sz)) : String.valueOf(sz));
	}

	/**
	 * 将YYYYMMDD转换成YYYY-MM-DD
	 * 
	 * @param date
	 * @return
	 */
	public static String getStr(String date) {
		if (null == date || "".equals(date))
			return "";
		String dateStr = "";
		try {
			Date d = parseEightStrToDate(date);
			dateStr = parseDateToTenStr(d);
		} catch (Exception e) {
			dateStr = date;
		}
		return dateStr;
	}

	/**
	 * 将YYYYMMDD转换成YYYY-MM-DD
	 * 
	 * @param date
	 * @return
	 */
	public static String changeToShow(String date) {
		return date != null && date.length() == 8 ? date.substring(0, 4) + "-"
				+ date.substring(4, 6) + "-" + date.substring(6, 8) : "";
	}
	/**
	 * 将YYYY-MM-DD转换成YYYYMMDD
	 * 
	 * @param date
	 * @return
	 */
	public static String changeToDB(String date) {
		return date == null ? "" : date.replaceAll("-", "");

	}
	/**
	 * 把日期型转化成字符串型 *
	 * 
	 * @param date
	 *            Date
	 * @return String
	 */
	public static String dateToStr(Date date) {
		String str = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			str = sdf.format(date);
		} catch (Exception ex) {
			str = "";
		}
		return str;
	}

	/**
	 * 比较日期大小 *
	 * 
	 * @param date1
	 *            String yyyy-MM-dd
	 * @param date2
	 *            String yyyy-MM-dd
	 * @return int date1>date2 return 1 date1==date2 return 0 date1<date2 return
	 *         -1
	 */
	public static int compareDate(String date1, String date2) {
		Date date11 = parseTenStrToDate(date1);
		Date date22 = parseTenStrToDate(date2);
		return date11.compareTo(date22);
	}

	/**
	 * 返回两个日期间隔的天数 *
	 * 
	 * @param beginDate
	 *            String yyyy-MM-dd
	 * @param endDate
	 *            String yyyy-MM-dd
	 * @return int
	 */
	public static int getBetweenDays(String beginDate, String endDate) {
		boolean exchangeFlag = false;
		if (DateUtil.compareDate(beginDate, endDate) == 1) {
			String temp = beginDate;
			beginDate = endDate;
			endDate = temp;
			exchangeFlag = true;
		}
		int sum = 0;
		int beginYear = getCurrentYear(beginDate);
		int beginMonth = getCurrentMonth(beginDate);
		int beginDay = getCurrentDay(beginDate);
		int endYear = getCurrentYear(endDate);
		int endMonth = getCurrentMonth(endDate);
		int endDay = getCurrentDay(endDate);
		String startDateStr = String.valueOf(beginYear) + "-"
				+ bZero(beginMonth) + "-01";

		int sumMonth = (endYear - beginYear + 1) * 12 - (beginMonth)
				- (12 - endMonth);
		for (int i = 0; i < sumMonth; i++) {
			String dateStr = getDateStr(startDateStr, i);
			sum = sum + getMonthDays(getCurrentYear(dateStr),
							getCurrentMonth(dateStr));
		}

		sum = sum - beginDay + endDay;
		if (exchangeFlag) {
			sum = -sum;
		}
		return sum;
	}
	/**
	 * 将YYYY-MM-DD转换成YYYYMMDD
	 * 
	 * @param date
	 * @return
	 */
	public static String getYYYYMMDD(String date) {
		if (date == null) {
			return "";
		} else if (date.length() == 8) {
			return date;
		} else {
			return date.replace("-", "");
		}
	}
	/**
	 * 得到给定日期的年份 （获取yyyy-MM-dd中的yyyy）
	 * 
	 * @param dateStr
	 *            String
	 * @return int
	 */
	public static int getCurrentYear(String dateStr) {
		String date[] = dateStr.split("-");
		return Integer.parseInt(date[0], 10);
	}
	/**
	 * 得到给定日期的月份 （获取yyyy-MM-dd中的MM）
	 * 
	 * @param dateStr
	 *            String
	 * @return int
	 */
	public static int getCurrentMonth(String dateStr) {
		String date[] = dateStr.split("-");
		return Integer.parseInt(date[1], 10);
	}
	/**
	 * 得到给定日期的天数 （获取yyyy-MM-dd中的dd）
	 * 
	 * @param dateStr
	 *            String
	 * @return int
	 */
	public static int getCurrentDay(String dateStr) {
		String date[] = dateStr.split("-");
		return Integer.parseInt(date[2], 10);
	}

	/**
	 * 判断date1是否比date2早 date1<date2 --true date1>=date2 --false 日期格式:yyyy-MM-dd
	 * 
	 * @param date1
	 * @param date2
	 * @return true/false
	 */
	public static boolean checkDate1BeforeDate2(String date1, String date2) {
		Date d1 = parseTenStrToDate(date1);
		Date d2 = parseTenStrToDate(date2);
		if (d1.before(d2)) {
			return true;
		}
		return false;
	}
	/**
	 * 获取给定日期的前一天
	 * 
	 * @param date
	 *            格式yyyy-MM-dd
	 * @return
	 */
	public static String getYesterday(String date) {
		try {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			Date d = sf.parse(date);
			cal.setTime(d);
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - 1);
			date = sf.format(cal.getTime());
		} catch (Exception e) {
		}
		return date;
	}
	public static Calendar getCalendar() {
		return calendar;
	}
	
	public static int hnline(String week, int days) {
		int line = 0;
		if (week.equals("星期日") || week.equals("周日") || week.startsWith("Sun")) {
			if (days >= 29) {
				line = 5;
			} else {
				line = 4;
			}
		} else if (week.equals("星期一") || week.equals("周一")
				|| week.startsWith("Mon")) {
			line = 5;
		} else if (week.equals("星期二") || week.equals("周二")
				|| week.startsWith("Tue")) {
			line = 5;
		} else if (week.equals("星期三") || week.equals("周三")
				|| week.startsWith("Wed")) {
			line = 5;
		} else if (week.equals("星期四") || week.equals("周四")
				|| week.startsWith("Thu")) {
			line = 5;
		} else if (week.equals("星期五") || week.equals("周五")
				|| week.startsWith("Fri")) {
			if (days >= 31) {
				line = 6;
			} else {
				line = 5;
			}
		} else if (week.equals("星期六") || week.equals("周六")
				|| week.startsWith("Sat")) {
			if (days >= 30) {
				line = 6;
			} else {
				line = 5;
			}
		}
		return line;
	}
	
	/**
	 * 
	 * @作者 DHCC-ZLC
	 * @日期 2016-6-27
	 * @方法说明：float转 decimal
	 * @返回参数 BigDecimal
	 */
	public static BigDecimal FoDcm(Float value)throws ServiceException {
		BigDecimal decimal = new BigDecimal(Float.toString(value));
       return decimal;
    }
	/**
	 * 
	 * @作者 DHCC-ZLC
	 * @日期 2016-6-27
	 * @方法说明：Double转 decimal
	 * @返回参数 BigDecimal
	 */
	public static BigDecimal DoDcm(Double value)throws ServiceException {
		BigDecimal decimal = new BigDecimal(Double.toString(value));
       return decimal;
    }
	/**
	 * @作者 DHCC-ZLC
	 * @日期 2016-6-28
	 * @方法说明：integer 转decimal
	 * @返回参数 BigDecimal
	 */
	public static BigDecimal IoDcm(Integer value)throws ServiceException {
		BigDecimal decimal = new BigDecimal(Integer.toString(value));
       return decimal;
    }
}
