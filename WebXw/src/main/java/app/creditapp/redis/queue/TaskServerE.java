package app.creditapp.redis.queue;

import java.util.List;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisDataException;
import app.creditapp.ln.worker.WorkEforDue;
import app.creditapp.pact.entity.LnPact;
import app.creditapp.redis.util.RedisUtil;
import app.util.ThreadPoolManager;

import com.alibaba.fastjson.JSON;

/**
 * @作者 DHCC-SONG
 * @日期 Jun 16, 2016
 * @描述 任务服务子类E [5号消息队列（待分账处理）处理器]
 */
public class TaskServerE implements Runnable {
	    Logger logger = Logger.getLogger(TaskServerE.class);
	    
		public void run() {
			Jedis jedis = RedisUtil.getThreadJedis();
			while(true){
				try {
					
					List<String> list = jedis.brpop(RedisUtil.BRPOP_TIMEOUT, RedisUtil.QUEUE5);
					String message = null;
					if( list != null ){
						message = list.get(1);
						if(message != null){
							LnPact lnPact = (LnPact)JSON.parseObject(message, LnPact.class);
							WorkEforDue workerE = new WorkEforDue(lnPact);
							ThreadPoolManager.getInstance5().exec(workerE); // 加入线程池进行执行
						}
					} else {
						logger.info("没有读到消息，redis链接将休息"+RedisUtil.WHILE_WAIT+"毫秒后重新阻塞读取消息,管道名称:"+RedisUtil.QUEUE5);
						Thread.sleep(RedisUtil.WHILE_WAIT);
					}
				}catch (JedisConnectionException e){
					//e.printStackTrace();
					logger.info("Redis链接异常，请检查服务器网络，系统正在重连...:"+RedisUtil.QUEUE5);
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
		public String toString(){
			return "TaskServerE";
		}
	}