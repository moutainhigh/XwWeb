package app.creditapp.inf.client;

public class XMLUtil {
	public  static String createBody(IBody body){
		return "<Body>"+body.toXml()+"</Body>";
	}
	
	public  static String createBodylist(IBody body){
		return body.toXml();
	}
	
	public  static String createHead(ZFHead zfHead){
		return zfHead.toXml();
	}
	
	public  static String createHead(ZFSerchHead zFSerchHead){
		return zFSerchHead.toXml();
	}
	


	
	
}
