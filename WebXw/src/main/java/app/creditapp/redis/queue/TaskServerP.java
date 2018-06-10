package app.creditapp.redis.queue;

import java.util.List;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisDataException;
import app.creditapp.inf.entity.WsIn2001;
import app.creditapp.ln.worker.WorkPforPreScree;
import app.creditapp.redis.util.RedisUtil;
import app.util.ThreadPoolManager;

import com.alibaba.fastjson.JSON;

/**
 * @作者 DHCC-SONG
 * @日期 Jun 16, 2016
 * @描述 任务服务子类A [1号消息队列（待验证和评级队列）处理器]
 */
public class TaskServerP implements Runnable {
	    Logger logger = Logger.getLogger(TaskServerP.class);
	    
		public void run() {
			Jedis jedis = RedisUtil.getThreadJedis();
			while(true){
				try {
	//				System.out.println("队列"+channel+"长度len-->"+jedis.llen(channel));
					List<String> list = jedis.brpop(RedisUtil.BRPOP_TIMEOUT, RedisUtil.QUEUE0);
					String message = null;
					if( list != null ){
						message = list.get(1);
						if(message != null){
//							String batchNo = (WsIn2001)JSON.parseObject(message, WsIn2001.class);
							WorkPforPreScree workerP = new WorkPforPreScree(message);
							ThreadPoolManager.getInstance1().exec(workerP); //  加入线程池进行执行
						}
					} else {
						logger.info("没有读到消息，redis链接将休息"+RedisUtil.WHILE_WAIT+"毫秒后重新阻塞读取消息,管道名称:"+RedisUtil.QUEUE0);
						Thread.sleep(RedisUtil.WHILE_WAIT);
					}
				}catch (JedisConnectionException e){
					//e.printStackTrace();
					logger.info("Redis链接异常，请检查服务器网络，系统正在重连...:"+RedisUtil.QUEUE0);
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
			return "TaskServerP";
		}
	}