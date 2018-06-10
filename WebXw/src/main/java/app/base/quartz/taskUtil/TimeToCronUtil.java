package app.base.quartz.taskUtil;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import app.base.quartz.entity.ConExpTransType;

public class TimeToCronUtil {
	/*** 
     *  
     * @param date 
     * @param dateFormat : e.g:yyyy-MM-dd HH:mm:ss 
     * @return 
     */  
    private static String formatDateByPattern(Date date,String dateFormat){  
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);  
        String formatTimeStr = null;  
        if (date != null) {  
            formatTimeStr = sdf.format(date);  
        }  
        return formatTimeStr;  
    }  
    /**
     * convert Date to cron ,eg.  "0 06 10 15 1 ? 2014" 
     * @param date  : 时间点 
     * 按照日历
     * @return 
     */  
    public static String dayToCron(java.util.Date  date){  
        String dateFormat="ss mm HH dd MM ? yyyy";  
        return formatDateByPattern(date, dateFormat);  
    } 
    
    /**
     * 将时间节点转换为long类型的时间间隔。
     * @param intervalArgs为不定参数，按顺序分别指向：
     * 0-日
     * 1-时
     * 2-分
     * 3-秒
     * 4-周
     * 5-月
     * 6-年
     *\n
     *时间为累加的关系，
     *举例：intervalToCron(2,3,12,4,56),总间隔时间为2天+3个小时+12分钟+56秒+0周+0月+0年
     * @return
     */
    public static long intervalToCron(int ... intervalArgs){
    	long intervalTime = 0;
    	for(int index =0;index < intervalArgs.length;index++){
    		switch (index) {
			case 0:
				intervalTime += intervalArgs[index] *1L * 24 * 60 * 60 * 1000;
				break;
			case 1:
				intervalTime += intervalArgs[index] *1L * 60 * 60 * 1000;
				break;
			case 2:
				intervalTime += intervalArgs[index] *1L * 60 * 1000;
				break;
			case 3:
				intervalTime += intervalArgs[index] *1L * 1000;
				break;
			case 4:
				intervalTime += intervalArgs[index] *1L * 24 * 60 * 60 * 1000 * 7;
				break;
			case 5:
				intervalTime += intervalArgs[index] *1L * 24 * 60 * 60 * 1000 * 30;
				break;
			case 6:
				intervalTime += intervalArgs[index] *1L * 24 * 60 * 60 * 1000 * 365;
				break;
			default:
				break;
			}
    	}
    	return intervalTime;
    }
    
    /**
     * 
     * @方法说明：
     * 1.传进来的数组格式应为{日，时，分，秒，周，月，年}
     * 2.其中周数组可以用1-7（1=星期日）或者（“SUN, MON, TUE, WED, THU, FRI,SAT"）代表
     * 3.没有值的部分，请用null或*
     * 4.transType 为转换方式，1-按天，2-按月，3-按周，4-按年
     * 5.翻译后的cronExpress表达式为{秒，分，时，日，月，周，[年]}
     * @返回参数 String
     */
    public static String transConExpression(String[][] dateArrays,ConExpTransType tranType){
    	StringBuilder conExpress = new StringBuilder();
    	switch (tranType) {
    	case BY_TIMES:
    		if(dateArrays[3]==null){
    			conExpress.append("0");//秒
    		}else{
    			conExpress.append(wreaveNewTimeCon(transArrayToStar(dateArrays[3])));//秒
    		}
    		conExpress.append(" ").append(wreaveNewTimeCon(transArrayToStar(dateArrays[2])))//分
			.append(" ").append(wreaveNewTimeCon(transArrayToStar(dateArrays[1])))//时
			.append(" ").append(wreaveNewTimeCon(transArrayToStar(dateArrays[0])))//日
			.append(" ").append(wreaveNewTimeCon(transArrayToStar(dateArrays[5])));//月
			if(dateArrays[4]==null){
				conExpress.append(" ?");
			}else{
				conExpress.append(" ").append(wreaveNewTimeCon(transArrayToStar(dateArrays[4])));//周
			}
    		return conExpress.toString();
		case BY_HOUR:
			if(dateArrays[3]==null){
    			conExpress.append("0");//秒
    		}else{
    			conExpress.append(wreaveNewTimeCon(transArrayToStar(dateArrays[3])));//秒
    		}
			conExpress.append(" ").append(wreaveNewTimeCon(transArrayToStar(dateArrays[2])).replace("*/", "")).append(" ");//分
				if(dateArrays[1].length == 1){
					conExpress.append("*/").append(dateArrays[1][0]).append(" * * ?");//每隔N小时执行一次
				}else{
					conExpress.append(" * * * ?");
				}
			return conExpress.toString();
		case BY_DAY:
			conExpress.append(transArrayToZero(dateArrays[3]))//秒
			.append(" ").append(transArrayToZero(dateArrays[2]))//分
			.append(" ").append(transArrayToZero(dateArrays[1])).append(" ");//时
			if(dateArrays[0].length == 1){
				conExpress.append("*/").append(dateArrays[0][0]).append(" * ?");//每隔N天执行一次
			}else{
				conExpress.append(" * * ?");
			}
			return conExpress.toString();
		case BY_MONTH:
			conExpress.append(transArrayToZero(dateArrays[3]))//秒
			.append(" ").append(transArrayToZero(dateArrays[2]))//分
			.append(" ").append(transArrayToZero(dateArrays[1]))//时
			.append(" ").append(transArrayToZero(dateArrays[0]))//日
			.append(" * ?");
			return conExpress.toString();
		case BY_WEEK:
			conExpress.append(transArrayToZero(dateArrays[3]))//秒
			.append(" ").append(transArrayToZero(dateArrays[2]))//分
			.append(" ").append(transArrayToZero(dateArrays[1]))//时
			.append(" ").append(transArrayToStr(dateArrays[0], "?"))//日
			.append(" * ").append(transArrayToZero(dateArrays[4]));
			return conExpress.toString();
		case BY_YEAR:
			conExpress.append(transArrayToZero(dateArrays[3]))//秒
			.append(" ").append(transArrayToZero(dateArrays[2]))//分
			.append(" ").append(transArrayToZero(dateArrays[1]))//时
			.append(" ").append(transArrayToZero(dateArrays[0]))//日
			.append(" ").append(transArrayToZero(dateArrays[5]))//月
			.append(" ").append(transArrayToZero(dateArrays[4]))//周
			.append(" *");
			return conExpress.toString();
					
		default:
			return null;
		}
    }
    
    public static String transConExpression(String[] dateArrays,ConExpTransType tranType){
    	String[][] result = new String[dateArrays.length][1];
    	
    	for(int i=0;i<dateArrays.length;i++){
    		result[i][0] = dateArrays[i];
    	}
    	return transConExpression(result,tranType);
    }
    
    public static String transConExpression(String dateTime,String[][] dateArrays,ConExpTransType tranType){
    	String[][] datetimeArray = new String[2][1];
    	String[] timeArgs = dateTime.split(":");
    	datetimeArray[0][0] = String.valueOf(Integer.valueOf(timeArgs[0]));
    	datetimeArray[1][0] = String.valueOf(Integer.valueOf(timeArgs[1]));
    	String[][] result = Arrays.copyOf(datetimeArray, 2+dateArrays.length);
    	System.arraycopy(dateArrays, 0, result, 2, dateArrays.length);
    	return transConExpression(result,tranType);
    }
    
    private static String transArrayToStar(String[] arrays){
    	return transArrayToStr(arrays,"*");
    }
    
    private static String transArrayToZero(String[] arrays){
    	return transArrayToStr(arrays, "0");
    }
    
    private static String transArrayToStr(String[] arrays,String defaultValue){
    	if(arrays !=null && (arrays.length>1 || (arrays.length == 1 && arrays[0]!=null && !arrays[0].isEmpty() && StringUtils.isNumeric(arrays[0])))){
    		return Arrays.toString(arrays).replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", "");
    	}else{
    		return defaultValue;
    	}
    }
    
    private static String wreaveNewTimeCon(String value){
    	if(value==null || value.equals("*") || value.isEmpty() || "null".equals(value)){
    		return "*";
    	}else{
    		return "*/"+value;
    	}
    }
    
    
    public static void main(String[] args) {
//		System.out.println(TimeToCronUtil.intervalToCron(2,3,12,4,56,3));
    	String[] arrays = {"2","3","0","0",null,null,null};//每个月3日5点12分
    	System.out.println(transConExpression(arrays,ConExpTransType.BY_MONTH));
//    	String[] arrays = {"3","5","12"};//每个月3日5点12分
//    	String rs = TimeToCronUtil.transArrayToStr(arrays);
//    	System.out.println(rs);
	}
}
