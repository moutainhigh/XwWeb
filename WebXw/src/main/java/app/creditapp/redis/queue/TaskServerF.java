package app.creditapp.redis.queue;

import java.util.List;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisDataException;
import app.creditapp.ln.entity.LnDue;
import app.creditapp.ln.worker.WorkFforAcc;
import app.creditapp.redis.util.RedisUtil;
import app.util.ThreadPoolManager;

import com.alibaba.fastjson.JSON;

/**
 * @作者 DHCC-SONG
 * @日期 Jun 16, 2016
 * @描述 任务服务子类F [6号消息队列（待分账处理）处理器]
 */
public class TaskServerF implements Runnable {
	Logger logger = Logger.getLogger(TaskServerF.class);

	public void run() {
		Jedis jedis = RedisUtil.getThreadJedis();
		while (true) {
			try {
				List<String> list = jedis.brpop(RedisUtil.BRPOP_TIMEOUT, RedisUtil.QUEUE6);
				String message = null;
				if (list != null) {
					message = list.get(1);
					if (message != null) {
						LnDue lnDue = (LnDue) JSON.parseObject(message, LnDue.class);
						WorkFforAcc workerF = new WorkFforAcc(lnDue);
						ThreadPoolManager.getInstance6().exec(workerF); // 加入线程池进行执行
					}
				} else {
					logger.info("没有读到消息，redis链接将休息" + RedisUtil.WHILE_WAIT + "毫秒后重新阻塞读取消息,管道名称:" + RedisUtil.QUEUE6);
					Thread.sleep(RedisUtil.WHILE_WAIT);
				}
			} catch (JedisConnectionException e) {
				// e.printStackTrace();
				logger.info("Redis链接异常，请检查服务器网络，系统正在重连...:" + RedisUtil.QUEUE6);
				try {
					Thread.sleep(RedisUtil.WHILE_WAIT);
					if(jedis!=null){
						jedis.close();
					}
					jedis = RedisUtil.getThreadJedis(); // 重新建立连接
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

	public String toString() {
		return "TaskServerF";
	}
}