package app.creditapp.inf.client;

public class QueryInfo implements IBody{
	private String No;

	public String getNo() {
		return No;
	}

	public void setNo(String no) {
		No = no;
	}

	@Override
	public String toXml() {
		StringBuffer s = new StringBuffer();
		s.append("<QueryInfo>");
		s.append("<No>"+this.No+"</No>");
		s.append("</QueryInfo>");
		return s.toString();
	}
	
}
