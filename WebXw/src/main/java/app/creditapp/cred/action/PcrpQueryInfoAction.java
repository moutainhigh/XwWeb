package app.creditapp.cred.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import app.creditapp.corp.entity.RptParams;
import app.creditapp.cred.bo.PcrpQueryInfoBo;
import app.creditapp.cred.entity.PcrpQueryInfo;
import com.alibaba.fastjson.JSON;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * 
 * 类名称：pcrpQueryInfoAction 类描述：个人信用报告业务  征信结果查询单笔
 * 创建人：孙明义 dhcc 创建时间：2016-6-25
 * 上午01:07:30 修改人：Administrator
 * 
 * @version
 */
public class PcrpQueryInfoAction extends BaseFormBean{

	private PcrpQueryInfo		pcrpQueryInfo;
	private String				CRP_ID;
	private String				CERT_TYPE;							// 证件类型
	private String				CERT_NUM;							// 证件号码
	private String				CUST_NAME;							// 客户名称
	private String				CRP_REASON;						// 查询原因
	private String				REPORT_TYPE;						// 信用报告版式
	private String				CUST_NO;
	private String				CRP_EXPDATE;
	private String				idauthflag;						// 查询类型
	private String				AFT_ID;
	private String				CRP_FILEPATH;
	private PcrpQueryInfoBo		pcrpQueryInfoBo;
	private PcrpQueryInfo pcrpQueryInfoRep;
	private HttpServletRequest	request;

	private List tabList;
	private String query;
	private String brNo;
	private RptParams rptParams;
	/**
	 * 
	 * 
	 * @return
	 * @throws Exception
	 *             2016-6-24 创建人：sunmingyi dhcc
	 */
	public String reportSearchUrl() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		request = ServletActionContext.getRequest();
		request.setAttribute("url", CRP_FILEPATH);
		return "url";
	}

	/**
	 * 个人信用报告结果查询
	 * 
	 * @return
	 * @throws Exception
	 *             2016-6-24 创建人：sunmingyi dhcc
	 */
	@SuppressWarnings("unchecked")
	public String searchPcrpDetailById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		request= ServletActionContext.getRequest();
		pcrpQueryInfoRep = new PcrpQueryInfo();
		pcrpQueryInfoRep.setCustName(CUST_NAME);
		pcrpQueryInfoRep.setCertType(CERT_TYPE);
		pcrpQueryInfoRep.setCertNum(CERT_NUM);
		pcrpQueryInfoRep.setCrpReason(CRP_REASON);
		//调用服务器端征信结果查询：
		System.out.println("开始调用客户端。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。");
		String byCrp = pcrpQueryInfoBo.getByCrp(pcrpQueryInfoRep);
		PcrpQueryInfo pcrpQueryinfoCrp=new PcrpQueryInfo();
		//将json串转为对象
//		pcrpQueryinfoCrp = (PcrpQueryInfo) JsonUtil.getObject4JsonString(byCrp, PcrpQueryInfo.class);
		pcrpQueryinfoCrp = (PcrpQueryInfo) JSON.parseObject(byCrp, PcrpQueryInfo.class);
		//将对象放到json中
		request.setAttribute("pcrpQueryinfoCrp",pcrpQueryinfoCrp); 
		request.setAttribute("AFT_ID",AFT_ID);
		request.setAttribute("CRP_ID",CRP_ID);
		request.setAttribute("REPORT_TYPE",REPORT_TYPE);
		request.setAttribute("CRP_EXPDATE",CRP_EXPDATE);
		request.setAttribute("CUST_NAME",CUST_NAME);
		request.setAttribute("CRP_REASON",CRP_REASON);
		request.setAttribute("REPORT_TYPE",REPORT_TYPE);
		request.setAttribute("CERT_TYPE",CERT_TYPE);
		request.setAttribute("CERT_NUM",CERT_NUM);
		request.setAttribute("CUST_NO",CUST_NO);
		request.setAttribute("CRP_FILEPATH",pcrpQueryinfoCrp.getCrpFilePath());
		return "CustQueryInfoListSC" ;

	}
	//征信信息图表
	public String getViewTab() throws Exception{
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		tabList = new ArrayList();
		String[] tab = null;
		tab = new String[2];
		tab[0] = "查征笔数";
		tab[1] = "PcrpQueryInfoAction_showActionNum.action?brNo=" + brNo+ "&query="+ query + "";
		tabList.add(tab);
		
		tab = new String[2];
		tab[0] = "查征分布";
		tab[1] = "PcrpQueryInfoAction_showChart.action?brNo=" + brNo+ "&query="+ query + "";
		tabList.add(tab);
		
		return "tab";
	}
	/**
	 * 显示分布图
	 * @return
	 * @throws Exception
	 */
	public String showChart() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());

		String data = "";
		String xStr = "";
		pcrpQueryInfo = new PcrpQueryInfo();
		pcrpQueryInfo.setBrNo(brNo);
		int bank = pcrpQueryInfoBo.getBankCount(pcrpQueryInfo);
		pcrpQueryInfo = new PcrpQueryInfo();
		pcrpQueryInfo.setBrNo(brNo);
		int loc = pcrpQueryInfoBo.getBatchCount(pcrpQueryInfo)-bank;
		if(loc+bank > 0){
			data = "{name:'本地查询',value:"+loc+"}";
			xStr = "'本地查询'";
			data += ",{name:'人行查询',value:"+bank+"}";
			xStr += ",'人行查询'";			
		}
		rptParams = new RptParams();
		rptParams.setData1(xStr);
		rptParams.setData2(data);
		return "viewChart";
	}
	public String showActionNum() throws Exception{
		ActionContext.initialize(ServletActionContext.getRequest(), 
				ServletActionContext.getResponse());
		
		String xStr = "";
		String data = "";
		String[] months = this.getMonths();
		pcrpQueryInfo = new PcrpQueryInfo();
		pcrpQueryInfo.setBrNo(brNo);
		int count = pcrpQueryInfoBo.getBatchCount(pcrpQueryInfo);
		if(count > 0){
			for(int i=0; i<months.length; i++){
				pcrpQueryInfo = new PcrpQueryInfo();
				pcrpQueryInfo.setBrNo(brNo);
				pcrpQueryInfo.setCrpTime(months[i].replace("-", ""));
				
				xStr += ",'"+months[i]+"'";
				data += ","+pcrpQueryInfoBo.getBatchCount(pcrpQueryInfo);
			}
			if(xStr.length()>0){
				xStr = xStr.substring(1);
				data = data.substring(1);
			}
		}
		rptParams = new RptParams();
		rptParams.setData1(xStr);
		rptParams.setData2(data);
		return "viewNum";
	}
	//获取过去六个月月份
	public String[] getMonths(){        
		String[] months = new String[6];
        Calendar cal = Calendar.getInstance();  
        DateFormat df = new SimpleDateFormat("yyyy-MM");
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)+1); //要先+1,才能把本月的算进去</span>  
        for(int i=0; i<6; i++){  
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)-1); //逐次往前推1个月  
            Date dtFrom=cal.getTime();
            months[5-i] = df.format(dtFrom);
        }       
        return months;
    }  
//	public static String getMACAddress() throws Exception {
//		// 获取本地IP对象
//		InetAddress ia = InetAddress.getLocalHost();
//		// 获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。
//		byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
//		// 下面代码是把mac地址拼装成String
//		StringBuffer sb = new StringBuffer();
//		for (int i = 0; i < mac.length; i++) {
//			if (i != 0) {
//				sb.append("-");
//			}
//			// mac[i] & 0xFF 是为了把byte转化为正整数
//			String s = Integer.toHexString(mac[i] & 0xFF);
//			sb.append(s.length() == 1 ? 0 + s : s);
//		}
//		// 把字符串所有小写字母改为大写成为正规的mac地址并返回
//		return sb.toString().toUpperCase();
//	}

	public PcrpQueryInfo getPcrpQueryInfo() {
		return pcrpQueryInfo;
	}

	public void setPcrpQueryInfo(PcrpQueryInfo pcrpQueryInfo) {
		this.pcrpQueryInfo = pcrpQueryInfo;
	}

	public String getCRP_ID() {
		return CRP_ID;
	}

	public void setCRP_ID(String cRPID) {
		CRP_ID = cRPID;
	}

	public String getCERT_TYPE() {
		return CERT_TYPE;
	}

	public void setCERT_TYPE(String cERTTYPE) {
		CERT_TYPE = cERTTYPE;
	}

	public String getCERT_NUM() {
		return CERT_NUM;
	}

	public void setCERT_NUM(String cERTNUM) {
		CERT_NUM = cERTNUM;
	}

	public String getCUST_NAME() {
		return CUST_NAME;
	}

	public void setCUST_NAME(String cUSTNAME) {
		CUST_NAME = cUSTNAME;
	}

	public String getCRP_REASON() {
		return CRP_REASON;
	}

	public void setCRP_REASON(String cRPREASON) {
		CRP_REASON = cRPREASON;
	}

	public String getREPORT_TYPE() {
		return REPORT_TYPE;
	}

	public void setREPORT_TYPE(String rEPORTTYPE) {
		REPORT_TYPE = rEPORTTYPE;
	}

	public String getIdauthflag() {
		return idauthflag;
	}

	public void setIdauthflag(String idauthflag) {
		this.idauthflag = idauthflag;
	}

	public PcrpQueryInfoBo getPcrpQueryInfoBo() {
		return pcrpQueryInfoBo;
	}

	public void setPcrpQueryInfoBo(PcrpQueryInfoBo pcrpQueryInfoBo) {
		this.pcrpQueryInfoBo = pcrpQueryInfoBo;
	}

	public String getAFT_ID() {
		return AFT_ID;
	}

	public void setAFT_ID(String aFTID) {
		AFT_ID = aFTID;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getCUST_NO() {
		return CUST_NO;
	}

	public void setCUST_NO(String cust_no) {
		CUST_NO = cust_no;
	}

	public String getCRP_EXPDATE() {
		return CRP_EXPDATE;
	}

	public void setCRP_EXPDATE(String crp_expdate) {
		CRP_EXPDATE = crp_expdate;
	}

	public String getCRP_FILEPATH() {
		return CRP_FILEPATH;
	}

	public void setCRP_FILEPATH(String crp_filepath) {
		CRP_FILEPATH = crp_filepath;
	}

	public List getTabList() {
		return tabList;
	}

	public void setTabList(List tabList) {
		this.tabList = tabList;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getBrNo() {
		return brNo;
	}

	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}

	public RptParams getRptParams() {
		return rptParams;
	}

	public void setRptParams(RptParams rptParams) {
		this.rptParams = rptParams;
	}

}