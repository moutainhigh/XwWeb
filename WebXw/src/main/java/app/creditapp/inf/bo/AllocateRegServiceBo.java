package  app.creditapp.inf.bo;

import app.base.ServiceException;
import app.creditapp.inf.entity.ReqData;

/**
 * @作者 DHCC-ZLC
 * @日期 2016-8-24
 * @描述
 */
public interface AllocateRegServiceBo {

	/**
	 * 单笔收拨款接口调用
	 */
	public String saveActTransactionInfos(ReqData reqData)  throws ServiceException;
	
}