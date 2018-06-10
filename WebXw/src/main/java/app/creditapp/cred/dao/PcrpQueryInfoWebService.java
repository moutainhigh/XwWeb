package app.creditapp.cred.dao;


import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 
  * 接口类名称：PcrpQueryInfoWebService
  * 类描述：查询征信报告结果客户端接口，等待映射
  *创建人：sunmingyi dhcc 
  * 创建时间：2016-6-28 上午01:21:47
  * 修改人：  
 * @version
 */
@WebService(targetNamespace = "entity.batchWS.creditapp.app")
public interface PcrpQueryInfoWebService {

	public String analyticForResult(@WebParam(name="selBatchNoJson") String selBatchNoJson);
	
	public String analyticForOneOnLocal(@WebParam(name="selBatchJson") String selBatchJson);
}
