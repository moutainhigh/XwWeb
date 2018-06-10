package app.creditapp.inf.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * 调用预拨款接口，等待映射
 **/

@WebService(targetNamespace = "http://webservice.iss.com")
public interface AllocateRegService {
	// 单笔调用收拨款接口
	@WebResult(name = "out", targetNamespace = "http://webservice.iss.com")
	@WebMethod
	public String saveActTransactionInfos(
			@WebParam(name = "in0", targetNamespace = "http://webservice.iss.com") String reqData);

	@WebResult(name = "out", targetNamespace = "http://webservice.iss.com")
	@WebMethod
	public java.lang.String getActTransactionState(
			@WebParam(name = "in0", targetNamespace = "http://webservice.iss.com") java.lang.String in0);
}