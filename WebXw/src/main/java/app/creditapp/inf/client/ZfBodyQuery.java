package app.creditapp.inf.client;

import java.util.List;

public class ZfBodyQuery implements IBody{
	private String QueryType;//查询类型
	private String StartDate;//开始日期
	private String EndDate;//结束日期
	private List<QueryInfo> QueryList;//
	@Override
	public String toXml() {
		StringBuffer s = new StringBuffer();
		s.append("<Body>");
		s.append("<QueryType>"+this.QueryType+"</QueryType>");
		s.append("<StartDate>");
		s.append(this.StartDate==null?"":this.StartDate);
		s.append("</StartDate>");
		s.append("<EndDate>");
		s.append(this.EndDate==null?"":this.EndDate);
		s.append("</EndDate>");
		s.append("<QueryList>");
		
		return s.toString();
	}
	public String getQueryType() {
		return QueryType;
	}
	public void setQueryType(String queryType) {
		QueryType = queryType;
	}
	public String getStartDate() {
		return StartDate;
	}
	public void setStartDate(String startDate) {
		StartDate = startDate;
	}
	public String getEndDate() {
		return EndDate;
	}
	public void setEndDate(String endDate) {
		EndDate = endDate;
	}
	public List<QueryInfo> getQueryList() {
		return QueryList;
	}
	public void setQueryList(List<QueryInfo> queryList) {
		QueryList = queryList;
	}
}
