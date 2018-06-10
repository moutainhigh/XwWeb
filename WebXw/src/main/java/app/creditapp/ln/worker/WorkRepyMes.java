package app.creditapp.ln.worker;
import java.util.Map;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import app.base.SourceTemplate;
import app.creditapp.acc.loan.bo.AcDebitSuspBo;
import app.creditapp.redis.util.RedisUtil;
/**
 * @作者 DHCC-SONG
 * @日期 Jun 6, 2016
 * @描述 业务处理A节点 是否重复 筛查 拆分客户 评级 准入
 */
public class WorkRepyMes implements Runnable {
	Logger logger = Logger.getLogger(WorkRepyMes.class);
	private String message;
	
	public WorkRepyMes(String message) {
		this.message = message;
	}
	/***
	 * @作者 DHCC-SONG
	 * @日期 Jun 29, 2016
	 * @方法说明：支付报文发送
	 * @返回参数 void
	 */
	public void run() {
		try {
		    if(message==null||"".equals(message)){
		    	  logger.error("接收到数据为空！");
		    }else{
		    	AcDebitSuspBo acDebitSuspBo = (AcDebitSuspBo) SourceTemplate.getContext().getBean("acDebitSuspBo");

		    	Map<String,String> map = acDebitSuspBo.validateAcDebitSusp(message);
		    	for(String wsId : map.keySet()){
	    			Jedis jedis = RedisUtil.getJedis();
	    			jedis.lpush(RedisUtil.QUEUE7, map.get(wsId));// 
	    			jedis.close();
	    		}
		    }
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
}
