package app.creditapp.batch.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



/**
 * 
 *
 */
public class CheckDateKind {	
		//判断穿竟来的时间类型并与日期比较是否满足要求
	public static boolean checkKind(String dateType, String dateDesc, String barchDate) {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		boolean flag = false;
		try {
			date = sdf.parse(barchDate);
			System.out.println(date);
			if ("1".equals(dateType)) {// 每天
				flag = checkday(date);
			} else if ("2".equals(dateType)) {// 月末
				flag = checkendMonth(date);
			} else if ("3".equals(dateType)) {// 季末
				flag = checkendseason(date);
			} else if ("4".equals(dateType)) {// 年末
				flag =  checkendyear(date);
			} else if ("5".equals(dateType)) {// 按月
				flag = checkMonthbasis(dateDesc, date);
			} else if ("7".equals(dateType)) {// 半年末
				flag = checkHalfYear(barchDate);
			} else {
				flag = checkFreeTime(dateDesc, date);// 自由  执行时间明细2-11,4-30
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return flag;
	}
		//每天
		public static boolean checkday(Date date){			
			return true;
		}
		public static Calendar date2calendar(Date date){
			Calendar calendar = Calendar.getInstance(); 
	        calendar.setTime(date);
	        return calendar;
		}
		//月末
		public static boolean checkendMonth(Date date){
			Calendar calendar = CheckDateKind.date2calendar(date); 
			calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1)); 
			if (calendar.get(Calendar.DAY_OF_MONTH) == 1) { 
			    return true; 
			} 
			return false; 
		}
		//季末
		public static boolean checkendseason(Date date){
			Calendar calendar = CheckDateKind.date2calendar(date); 
	        int month=calendar.get(Calendar.MONTH)+1;//得到月，因为从0开始的，所以要加1
	        int day=calendar.get(Calendar.DAY_OF_MONTH);//得到天
	        if(month==3||month==12){
	        	if(day==31){
	        		return true;
	        	}
	        }else if(month==6||month==9){
	        	if(day==30){
	        		return true;
	        	}
	        }
			return false;
		}
		
		//半年末
		public static boolean checkHalfYear(String batchDate){
			if(batchDate.endsWith("0630")||batchDate.endsWith("1231")){
				return true;
			}
			return false;
		}
		
		//年末
		public static boolean checkendyear(Date date){
			Calendar calendar = CheckDateKind.date2calendar(date); 
	        int month=calendar.get(Calendar.MONTH)+1;//得到月，因为从0开始的，所以要加1
	        int day=calendar.get(Calendar.DAY_OF_MONTH);//得到天
	        if(month==12&&day==31){
	        	return true;
	        }
			return false;
		}
		//按月1-28
		public static boolean checkMonthbasis(String dataDesc, Date date){
			Calendar calendar = CheckDateKind.date2calendar(date); 
	        int day=calendar.get(Calendar.DAY_OF_MONTH);//得到天
	        if(day>=1&&day<=28 && dataDesc.equals(String.valueOf(day))){
	        	return true;
	        }
			return false;
		}
		//自由定制 执行时间明细2-11,4-30
		public static boolean checkFreeTime(String dataDesc, Date date){
			Calendar calendar = CheckDateKind.date2calendar(date); 
	        int month=calendar.get(Calendar.MONTH)+1;//得到月，因为从0开始的，所以要加1
	        int day=calendar.get(Calendar.DAY_OF_MONTH);//得到天
	        String[] ddArr = dataDesc.split(",");
	        for(String dds : ddArr){
	        	String[] dArr = dds.split("-");
	        	if(dArr[0].equals(String.valueOf(month)) && dArr[1].equals(String.valueOf(day))){
	        		return true;
	        	}
	        }
			return false;
		}
		
		public static void main(String[] args) {
			boolean f = CheckDateKind.checkKind("7","3-28","20150328");
			System.out.println(f);
		}
		
}
