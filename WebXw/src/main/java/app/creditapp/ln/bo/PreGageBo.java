package  app.creditapp.ln.bo;

import java.util.List;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.inf.entity.WsIn2004_1;
import app.creditapp.inf.entity.WsOut2006_1_1;
import app.creditapp.ln.entity.PreGage;

/**
* Title: PreGageBo.java
* Description:
**/
public interface PreGageBo {

	public PreGage getById(PreGage preGage) throws ServiceException;

	public void del(PreGage preGage) throws ServiceException;

	public void insert(PreGage preGage) throws ServiceException;

	public void update(PreGage preGage) throws ServiceException;

	public Ipage findByPage(Ipage ipg, PreGage preGage) throws ServiceException;

	public Ipage findByPageQuotaForLn(Ipage ipage, PreGage preGage)throws ServiceException;
	
	public void insertfor2004_1(WsIn2004_1 wsIn2004_1) throws ServiceException;
	//接口ws2006  wsout2006_1_1 输出值
	public List<WsOut2006_1_1> ws2006_1_1list(PreGage preGage) throws ServiceException;

}