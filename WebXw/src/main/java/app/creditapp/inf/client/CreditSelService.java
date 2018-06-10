package app.creditapp.inf.client;

import javax.jws.WebParam;
import javax.jws.WebService;
/**
查询征信客户端接口，等待映射
**/
@WebService(targetNamespace = "entity.batchWS.creditapp.app")
public interface CreditSelService {
    //public String analytic(@WebParam(name="receiveJson") ArrayList<CreditSelPreApply> preList);
	//public String analytic(@WebParam(name="receiveJson") String receiveJson);
	
	// 单笔征信查询接口
	public String analyticForOne(@WebParam(name="receiveJson") String receiveJson);
	// 批量查询征信接口
	public String analyticForBatch(@WebParam(name="receiveJson") String receiveJson);
	
	// 单笔查询结果接口  只查询本地库
	public String analyticForOneOnLocal(@WebParam(name="selBatchJson") String selBatchJson);
	// 批量结果查询接口  只查询本地库
	public String analyticForResult(@WebParam(name="selBatchNoJson") String selBatchNoJson);

//	public int getCount(WsQueryCreditBean wsQueryCreditBean) throws DAOException;
	
}