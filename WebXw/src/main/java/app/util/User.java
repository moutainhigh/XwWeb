package app.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class User {

	public User() {
	}
	//换位
	private static void CBexchange(char ac[], char ac1[]) {
		System.out.println("密码长度"+ac1.length);
		if(ac1.length==6){
			ac[0] = ac1[2];
			ac[1] = ac1[5];
			ac[2] = ac1[0];
			ac[3] = ac1[4];
			ac[4] = ac1[3];
			ac[5] = ac1[1];
		}else{
			int j=0;
			for(int i=0; i<ac1.length; i++){
				j=ac1.length-1-i;
				ac[i] = ac1[j];
			}
		}
	}

	private static void ShuffData(char ac[], int i) {
		int j = 0;
		switch (i) {			
		case 16: // '\020'
			ac[j++] ^= '\001';
			// fall through

		case 15: // '\017'
			ac[j++] ^= '\t';
			// fall through

		case 14: // '\016'
			ac[j++] ^= '\017';
			// fall through

		case 13: // '\r'
			ac[j++] ^= '\002';
			// fall through

		case 12: // '\f'
			ac[j++] ^= '\f';
			// fall through

		case 11: // '\013'
			ac[j++] ^= '\b';
			// fall through

		case 10: // '\n'
			ac[j++] ^= '\002';
			// fall through

		case 9: // '\t'
			ac[j++] ^= '\007';
			// fall through

		case 8: // '\b'
			ac[j++] ^= '\006';
			// fall through

		case 7: // '\007'
			ac[j++] ^= '\n';
			// fall through

		case 6: // '\006'
			ac[j++] ^= '\r';
			// fall through

		case 5: // '\005'
			ac[j++] ^= '\0';
			// fall through

		case 4: // '\004'
			ac[j++] ^= '\b';
			// fall through

		case 3: // '\003'
			ac[j++] ^= '\003';
			// fall through

		case 2: // '\002'
			ac[j++] ^= '\t';
			// fall through

		case 1: // '\001'
			ac[j++] ^= '\013';
			// fall through

		default:
			return;
		}
	}
	//解密
	public static String CBunshuff(String s) {
		char ac[] = new char[s.length()];
		char ac1[] = s.toCharArray();
		ShuffData(ac1, s.length());
		CBexchange(ac, ac1);
		return new String(ac);
	}
	//加密
	public static String CBshuff(String s) {
		char ac[] = new char[s.length()];
		char ac1[] = s.toCharArray();
		CBexchange(ac, ac1);
		ShuffData(ac, s.length());
		return new String(ac);
	}
	/**
	 * 取得系统时间
	 * @return
	 */
	public static String getTime() {
		Date date = new Date();
		SimpleDateFormat simpledateformat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return simpledateformat.format(date);
	}
	
	/**
	 * 取得系统时间HH:mm:ss
	 * @return
	 */
	public static String getTime1(){
		Date date = new Date();
		SimpleDateFormat simpledateformat = new SimpleDateFormat(
				"HH:mm:ss");
		return simpledateformat.format(date);
	}
	
	/**
	 * 检查操作员是否有权限
	 * @param httpservletrequest
	 * @param i
	 * @return
	 */
	public static boolean hasRight(HttpServletRequest httpservletrequest, int i) {
		String s = (String) httpservletrequest.getSession().getAttribute(
				"rights");
		if (s == null) {
			return false;
		} else {
			char ac[] = s.toCharArray();
			return ac[i] == '1';
		}
	}
	/**
	 * 检查操作员是否有权限
	 * @param httpservletrequest
	 * @param i
	 * @return
	 */
	public static boolean hasRightcr(HttpServletRequest httpservletrequest,
			int i) {
		String s = (String) httpservletrequest.getSession().getAttribute(
				"rightscr");
		if (s == null) {
			return false;
		} else {
			char ac[] = s.toCharArray();
			return ac[i] == '1';
		}
	}
	/**
	 * 检查字符转指定位置是否为1
	 * @param s
	 * @param i
	 * @return
	 */
	public static boolean hasRightcr(String s, int i){            
	    if(s == null)
	       return false;
	   	char ac[] = s.toCharArray();
	    return ac[i] == '1';
	 	} 
	/**
	 * 总行客户经理所在部门
	 * @param httpservletrequest
	 * @return
	 */
	public static String getBrDepart(HttpServletRequest httpservletrequest) {
		String s = (String) httpservletrequest.getSession().getAttribute("brDepart");
		return s == null?"":s.trim();
	}
	/**
	 * 事业部编号
	 * @param httpservletrequest
	 * @return
	 */
	public static String getBrno(HttpServletRequest httpservletrequest) {
//		String s = (String) httpservletrequest.getSession().getAttribute("brno");
//		return s == null?"":s.trim();
		return "";
	}
	
	/**
	 * 事业部编号
	 * @param httpservletrequest
	 * @return
	 */
	public static String getWfOrgNo(HttpServletRequest httpservletrequest) {
		String s = (String) httpservletrequest.getSession().getAttribute("wfOrgNo");
		return s == null?"":s.trim();
	}
	/**
	 * 机构名称
	 * @param httpservletrequest
	 * @return
	 */
	public static String getBrname(HttpServletRequest httpservletrequest) {
		String s = (String) httpservletrequest.getSession().getAttribute("brName");
		return (s==null||s.equals(""))?"北京国际信托有限公司":s;
	}
	
	public static String getParentBrno(HttpServletRequest httpservletrequest) {
		String s = (String) httpservletrequest.getSession().getAttribute("parentBrno");
		return s == null?"":s.trim();
	}
	public static String getParentBsno(HttpServletRequest httpservletrequest) {
		String s = (String) httpservletrequest.getSession().getAttribute("parentBsno");
		return s == null?"":s.trim();
	}
	/**
	 * 金融机构号
	 * @param httpservletrequest
	 * @return
	 */
	public static String getBsno(HttpServletRequest httpservletrequest) {
		String s = (String) httpservletrequest.getSession().getAttribute("bsno");
		return s == null?"":s.trim();
	}
	
	/**
	 * 金融机构号
	 * @param httpservletrequest
	 * @return
	 */
	public static String getBsname(HttpServletRequest httpservletrequest) {
		String s = (String) httpservletrequest.getSession().getAttribute("bsName");
		return s == null?"":s.trim();
	}
	
	/**
	 * 机构名称
	 * @param httpservletrequest
	 * @return
	 */
	public static String getDealerCifNo(HttpServletRequest httpservletrequest) {
		String s = (String) httpservletrequest.getSession().getAttribute("dealerCifNo");
		return (s==null||s.equals(""))?"":s;
	}
	/**
	 * 操作员登录号
	 * @param httpservletrequest
	 * @return
	 */
	public static String getLoginid(HttpServletRequest httpservletrequest) {
		String s = (String) httpservletrequest.getSession().getAttribute("loginid");
		return s == null?"":s;
	}
	
	public static String getTlrno(HttpServletRequest httpservletrequest) {
		String s = (String) httpservletrequest.getSession().getAttribute("loginid");
		return s == null?"":s;
	}
	/**
	 * 操作员ID
	 * @param httpservletrequest
	 * @return
	 */
	public static String getUserid(HttpServletRequest httpservletrequest) {
		String s = String.valueOf(httpservletrequest.getSession().getAttribute("userid"));
		return s == null?"":s;
	}
	/**
	 * 操作员姓名
	 * @param httpservletrequest
	 * @return
	 */
	public static String getDisplayName(HttpServletRequest httpservletrequest) {
		String s = (String) httpservletrequest.getSession().getAttribute("displayname");
		return s == null?"":s;
	}
	
	/**
	 * 
	 * @Title: getRoleno 
	 * @Description:  审批角色
	 * @param httpservletrequest
	 * @return [] 
	 * @return String []
	 */
	public static String getWkfUserRoleNos(HttpServletRequest httpservletrequest) {
		String s = (String) httpservletrequest.getSession().getAttribute("wkfRoleNos");
		return s == null?"":s;
	}
	
	public static String getWkfUserRoleNames(HttpServletRequest httpservletrequest) {
		String s = (String) httpservletrequest.getSession().getAttribute("wkfrolenames");
		return s == null?"":s;
	}
	
	

	
	/**
	 * @作者 DHCC-SONG
	 * @日期 Aug 5, 2016
	 * @方法说明：根据角色编号判断是否有权限
	 * @返回参数 String
	 */
	public static String getLoginIdByAuth(HttpServletRequest httpservletrequest) {
		String s = (String) httpservletrequest.getSession().getAttribute("roleNos");
		String loginid = "";
		
		//1001	运营经理
		//1002	运营人员
		//2001	业务经理v
		//2002	业务人员
		//8888	业务管理员
		//9999	技术管理员
		//3001	外包人员
		//0000    系统管理员
		
		if(s.contains("0000") ){ // 能查询所有权限
			
		}else{ // 只能查询自己
			loginid = (String) httpservletrequest.getSession().getAttribute("loginid");
		}
		
		return loginid == null?"":loginid;
	}
	
	/**
	 * 机构是否有放款权限
	 * @param httpservletrequest
	 * @return
	 */
	public static String getBoff(HttpServletRequest httpservletrequest) {
		String s = (String) httpservletrequest.getSession().getAttribute("boff");
		return s == null?"":s;
	}
	/**
	 * 浮点数截取整数
	 * @param a
	 * @return
	 */
	public static String getInt(double a) {
		String s = (String.valueOf(a).substring(0, String.valueOf(a).indexOf(".")));
		return s == null?"":s;
	}
	/**
	 * 机构级别
	 * @param httpservletrequest
	 * @return
	 */
	public static String getFicode(HttpServletRequest httpservletrequest) {
		String s = (String) httpservletrequest.getSession().getAttribute("ficode");
		return s == null?"":s;
	}

	/**
	 * 操作员自定义分页显示行数（现在在页面中选择）
	 * @param httpservletrequest
	 * @return
	 */
	public static String getAuth(HttpServletRequest httpservletrequest) {
		String s = (String) httpservletrequest.getSession().getAttribute("auth");
		return s == null?"":s;
	}
	/**
	 * 操作员对应菜单
	 * @param httpservletrequest
	 * @return
	 */
	public static String getMenuStr(HttpServletRequest httpservletrequest) {
		String s = (String) httpservletrequest.getSession().getAttribute("menustr");
		return s == null?"":s;
	}
	
	public static String getTrade(HttpServletRequest httpservletrequest) {
		String s = (String) httpservletrequest.getSession().getAttribute("trade");
		return s == null?"":s;
	}
	/**
	 * 操作员角色编号
	 * @param httpservletrequest
	 * @return
	 */
	public static String getRoleno(HttpServletRequest httpservletrequest) {
		String s = (String) httpservletrequest.getSession().getAttribute("roleno");
		return s == null?"":s;
	}
	
	public static String getAllRoleno(HttpServletRequest httpservletrequest) {
		String s = (String) httpservletrequest.getSession().getAttribute("allRoleNo");
		return s == null?"":s;
	}
	/**
	 * 行员级别
	 * @param httpservletrequest
	 * @return
	 */
	public static String getOp_lev(HttpServletRequest httpservletrequest) {//操作员级别,泸州商行特有的
		String s = (String) httpservletrequest.getSession().getAttribute("op_lev");
		return s == null?"":s;
	}
	public static String getGlobal(HttpServletRequest httpservletrequest) {//glo
		String s = (String) httpservletrequest.getSession().getAttribute("sys_date");
		return s == null?"":s;
	}
	/**
	 * 
	 * 功能描述： 审批状态是否被注销
	 * @param httpservletrequest
	 * @return
	 * @修改日志：
	 */
	public static String getState(HttpServletRequest httpservletrequest) {//glo
		String s = (String) httpservletrequest.getSession().getAttribute("state");
		return s == null?"":s;
	}
	/**
	 * 
	 * 功能描述：是否授权
	 * @param httpservletrequest
	 * @return
	 * @修改日志：
	 */
	public static String getAccState(HttpServletRequest httpservletrequest) {//glo
		String s = (String) httpservletrequest.getSession().getAttribute("accstate");
		return s == null?"":s;
	}
	/**
	 * 
	 * 功能描述：授权审批的岗位
	 * @param httpservletrequest
	 * @return
	 * @修改日志：
	 */
	public static String getAccPost(HttpServletRequest httpservletrequest) {//glo
		String s = (String) httpservletrequest.getSession().getAttribute("accspost");
		return s == null?"":s;
	}
	/**
	 * 操作员所属地区行政区划
	 * @param httpservletrequest
	 * @return
	 */
	public static String getAreano(HttpServletRequest httpservletrequest){
		String s = (String)httpservletrequest.getSession().getAttribute("areano");
		return s == null?"":s;
	}
	/**
	 * 
	 * 功能描述：区分黄河银行还是其他机构的
	 * @param httpservletrequest
	 * @return
	 * @修改日志：
	 */
	public static String getAcflg(HttpServletRequest httpservletrequest) {
		String s = (String) httpservletrequest.getSession().getAttribute("acflg");
		return s == null?"":s;
	}
	
	/**
	 * 审批人员是否有审批权限
	 * @param httpservletrequest
	 * @return
	 */
	public static String getAppstate(HttpServletRequest httpservletrequest){
		String s = (String)httpservletrequest.getSession().getAttribute("appstate");
		return s == null?"":s;
	}
	/**
	 * 客户经理是否有办理新增（新增、比上年末新增、只贴、转帖）贷款权限
	 * @param httpservletrequest
	 * @return
	 */
	public static String getNew(HttpServletRequest httpservletrequest){
		String s = (String)httpservletrequest.getSession().getAttribute("new");
		return s == null?"":s;
	}
	/**
	 * 客户经理是否有办理转贷（借新还旧、收回再贷、资产重组）权限
	 * @param httpservletrequest
	 * @return
	 */
	public static String getSwitch(HttpServletRequest httpservletrequest){
		String s = (String)httpservletrequest.getSession().getAttribute("switch");
		return s == null?"":s;
	}
	/**
	 * 客户经理是否有办理质押贷款权限
	 * @param httpservletrequest
	 * @return
	 */
	public static String getImpawn(HttpServletRequest httpservletrequest){
		String s = (String)httpservletrequest.getSession().getAttribute("impawn");
		return s == null?"":s;
	}
	/**
	 * 客户经理是否有办理抵押贷款权限
	 * @param httpservletrequest
	 * @return
	 */
	public static String getPledge(HttpServletRequest httpservletrequest){
		String s = (String)httpservletrequest.getSession().getAttribute("pledge");
		return s == null?"":s;
	}
	/**
	 * 客户经理是否有办理保证贷款权限
	 * @param httpservletrequest
	 * @return
	 */
	public static String getVou(HttpServletRequest httpservletrequest){
		String s = (String)httpservletrequest.getSession().getAttribute("vou");
		return s == null?"":s;
	}
	/**
	 * 客户经理是否有办理信用贷款权限
	 * @param httpservletrequest
	 * @return
	 */
	public static String getCredit(HttpServletRequest httpservletrequest){
		String s = (String)httpservletrequest.getSession().getAttribute("credit");
		return s == null?"":s;
	}
	/**
	 * 取客户号
	 * @param httpservletrequest
	 * @return
	 */
	public static String getCifno(HttpServletRequest httpservletrequest){
		String s = (String)httpservletrequest.getSession().getAttribute("cifno");
		return s == null?"":s;
	}
	/**
	 * 取客户名
	 * @param httpservletrequest
	 * @return
	 */
	public static String getCifname(HttpServletRequest httpservletrequest){
		String s = (String)httpservletrequest.getSession().getAttribute("cifname");
		return s == null?"":s;
	}
	/**
	 * 取证件号
	 * @param httpservletrequest
	 * @return
	 */
	public static String getIdnum(HttpServletRequest httpservletrequest){
		String s = (String)httpservletrequest.getAttribute("idnum");
		return s == null?"":s;
	}
	/**
	 * 
	 * 
	 * 功能描述：数字格式化
	 * @param number
	 * 					数字
	 * @param precision
	 * 					精度
	 * @see
	 * @修改日志：
	 *
	 */
	public static String numFormat(double number,int precision){
		DecimalFormat myformat=null;
		if(precision==2){
		myformat = new DecimalFormat("###0.00");//使用系统默认的格式
		}else if(precision==1){
			myformat = new DecimalFormat("###0.0");//使用系统默认的格式	
		}else if(precision==4){
			myformat = new DecimalFormat("###0.0000");//使用系统默认的格式	
		}else if(precision==0){
			myformat = new DecimalFormat("###0");//使用系统默认的格式	
		}
		return myformat.format(number);
	}
	public static String getBigNum(String bigNum){
		BigDecimal bd = new BigDecimal(bigNum);
		return bd.toPlainString();
	}
	/**
	 * 功能描述：自动获得合同到期日期
	 * @param month 间隔月
	 * @param day 间隔日
	 * @param startdate 起始日期
	 * @修改日志：
	 */
//	public String setcalendar(String startdate, String month, String day) throws ParseException {
//		String r = "";
//		Calendar mycal = Calendar.getInstance();
//		// Date mydat = null;
//		Date mydate;
//		SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
//		try {
//			mydate = formater.parse(startdate);
//			mycal.setTime(mydate);
//			mydate.setMonth(mydate.getMonth() + Integer.parseInt(month.equals("") ? "0" : month));
//			mydate.setDate(mydate.getDate() + (Integer.parseInt(day.equals("") ? "0" : day)));
//			r = formater.format(mydate);
//		} catch (ParseException e1) {
//			e1.printStackTrace();
//		}
//		return r;
//	}
	
	/**
	 * 功能描述：获得输入日期的前一天
	 * @param curDate 	 
	 * @修改日志：
	 */
	public static String getBhdate(String curDate) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String lastday="";
	    Calendar mycal=Calendar.getInstance();
		mycal.set(Integer.parseInt(curDate.substring(0,4)), Integer.parseInt(curDate.substring(4,6))-1, Integer.parseInt(curDate.substring(6,8))-1);
		lastday=df.format(mycal.getTime());
		return lastday;
	}
	/**
	 * 
	 * @param httpservletrequest
	 * @return  返回上月月份(YYYY-MM)
	 * Jul 7, 2011
	 * @desc
	 */
	public static String getLastMonth(HttpServletRequest httpservletrequest){
		String s =   getSys_date(httpservletrequest);
		if( s == null)
			return "";
		int intMonth = Integer.valueOf(s.substring(4,6))-1;
		int intYear = Integer.valueOf(s.substring(0,4));
		if(1==intMonth){
			intMonth = 12;
			 intYear--;
		}
		return intMonth < 10? String.valueOf(intYear)+"-0"+String.valueOf(intMonth):String.valueOf(intYear)+"-"+String.valueOf(intMonth);
		
	}
	/**
	 * 
	 * @param httpservletrequest
	 * @return  返回上年年份(YYYY)
	 * Jul 7, 2011 seasons
	 * @desc
	 */
	public static String getLastYear(HttpServletRequest httpservletrequest){
		String s =   getSys_date(httpservletrequest);
		if( s == null)
			return "";
		int intYear = Integer.valueOf(s.substring(0,4));		
		return String.valueOf(--intYear);
		
	}
	/**
	 * 
	 * @param httpservletrequest
	 * @return  返回上季度(YYYY-0?)?代表第几季度
	 * Jul 7, 2011 seasons
	 * @desc
	 */
	public static String getLastseasons(HttpServletRequest httpservletrequest){
		String s =   getSys_date(httpservletrequest);
		if( s == null)
			return "";
		int intSeasons = (Integer.valueOf(s.substring(4,6))-1)/3;
		int intYear = Integer.valueOf(s.substring(0,4));
		if(0==intSeasons){
			intSeasons = 4;
			 intYear--;
		}	
		return String.valueOf(intYear)+"-0"+String.valueOf(intSeasons);
		
	}
	/**
	 * 
	 * 功能描述：取得系统营业时间
	 * @param httpservletrequest
	 * @return
	 * @修改日志：
	 */
	public static String getSys_date(HttpServletRequest httpservletrequest){
		String s = (String)httpservletrequest.getSession().getAttribute("sys_date");
		return s == null?"":s;
	}
	//列表默认显示行数
	public static int getPageSize(){
		return 10;
	}
	/**
	 * 
	 * 功能描述：取得文档上传大小限制
	 * @param httpservletrequest
	 * @return double
	 */
	public static double getDocSize(HttpServletRequest httpservletrequest){
		Double s = (Double)httpservletrequest.getSession().getAttribute("doc_size");//kb
		return s == null?0:s.doubleValue();
	}
	
	public static String gethnTime() {
		Date date = new Date();
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMddHHmmss");
		return simpledateformat.format(date);
	}
}
