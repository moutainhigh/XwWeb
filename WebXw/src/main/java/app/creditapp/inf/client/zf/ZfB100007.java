package app.creditapp.inf.client.zf;

import app.creditapp.inf.client.IBody;
import app.oscache.CachecodeUtil;

public class ZfB100007 implements IBody {

	private String No;//ÌõÄ¿ºÅ
	
	public String toXml() {
			
			StringBuffer s = new StringBuffer();
			
			s.append("<QueryInfo>");

			s.append("<No>"+CachecodeUtil.MFSPREFIX+this.No+"</No>");

			s.append("</QueryInfo>");
			return s.toString();
		}

	public String getNo() {
		return No;
	}

	public void setNo(String no) {
		No = no;
	}

}
