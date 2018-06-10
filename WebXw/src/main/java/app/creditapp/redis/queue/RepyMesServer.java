package app.creditapp.redis.queue;

import java.util.List;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisDataException;
import app.creditapp.ln.worker.WorkRepyMes;
import app.creditapp.redis.util.RedisUtil;
import app.util.ThreadPoolManager;

/**
 * @作者 DHCC-SONG
 * @日期 Jun 16, 2016
 * @描述	[7号消息队列（与支付系统发送通讯报文）处理器]
 */
public class RepyMesServer implements Runnable {
	    Logger logger = Logger.getLogger(RepyMesServer.class);
	    
		public void run() {
			Jedis jedis = RedisUtil.getThreadJedis();
			while(true){
				try {
					List<String> list = jedis.brpop(RedisUtil.BRPOP_TIMEOUT, RedisUtil.QUEUE10);
					String message = null;
					if( list != null ){
						message = list.get(1);
						if(message != null){
							WorkRepyMes msg = new WorkRepyMes(message);
							ThreadPoolManager.getInstance10().exec(msg); //  加入线程池进行执行
						}
					} else {
						logger.info("没有读到消息，redis链接将休息"+RedisUtil.WHILE_WAIT+"毫秒后重新阻塞读取消息,管道名称:"+RedisUtil.QUEUE10);
						Thread.sleep(RedisUtil.WHILE_WAIT);
					}
				}catch (JedisConnectionException e){
					logger.info("Redis链接异常，请检查服务器网络，系统正在重连...:"+RedisUtil.QUEUE10);
					try {
						Thread.sleep(RedisUtil.WHILE_WAIT);
						jedis = RedisUtil.getThreadJedis();  // 重新建立连接
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} catch (JedisDataException de) {
					try {
						Thread.sleep(RedisUtil.WHILE_WAIT);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
	}