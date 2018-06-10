package app.creditapp.redis.queue;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisDataException;
import app.creditapp.ln.entity.LnApplyMid;
import app.creditapp.ln.worker.WorkAforValidate;
import app.creditapp.redis.util.RedisUtil;
import app.util.ThreadPoolManager;

import com.alibaba.fastjson.JSON;

/**
 * @作者 DHCC-SONG
 * @日期 Jun 16, 2016
 * @描述 任务服务子类A [1号消息队列（待验证和评级队列）处理器]
 */
public class TaskServerA implements Runnable {
	    Logger logger = Logger.getLogger(TaskServerA.class);
	    
		public void run() {
			Jedis jedis = RedisUtil.getThreadJedis();
			while(true){
				try {
	//				System.out.println("队列"+channel+"长度len-->"+jedis.llen(channel));
					List<String> list = jedis.brpop(RedisUtil.BRPOP_TIMEOUT, RedisUtil.QUEUE1);
					String message = null;
					if( list != null ){
						message = list.get(1);
						if(message != null){
							LnApplyMid lnApplyMid = (LnApplyMid)JSON.parseObject(message, LnApplyMid.class);
							WorkAforValidate workerA = new WorkAforValidate(lnApplyMid);
							ThreadPoolManager.getInstance1().exec(workerA); //  加入线程池进行执行
						}
					} else {
						logger.info("没有读到消息，redis链接将休息"+RedisUtil.WHILE_WAIT+"毫秒后重新阻塞读取消息,管道名称:"+RedisUtil.QUEUE1);
						Thread.sleep(RedisUtil.WHILE_WAIT);
					}
				}catch (JedisConnectionException e){
					//e.printStackTrace();
					logger.info("Redis链接异常，请检查服务器网络，系统正在重连...:"+RedisUtil.QUEUE1);
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
			return "TaskServerA";
		}
	
		public static void main(String[] args) throws Exception {
			 Logger loggerdd = Logger.getLogger(TaskServerA.class);
			//Jedis jedis = RedisUtil.getJedis();
			Jedis jedis = new Jedis("10.7.101.38",6379,0);
//			for(int i=0;i<10000;i++){
//				if(i%3==0){
//					Thread.sleep((i%4)*(i%2)*200);
//					loggerdd.info("写入了。。。。。");
//				}
				jedis.lpush("songqishuai1", "DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");// 只写入业务申请编号
//			}
			 
			 ResourceBundle prop = ResourceBundle.getBundle("path");
				String pushMessageServerPath = prop.getString("pushMessageServerPath").trim();
//			
//			Message m = null;
//			try {
//				while(true){
//					//System.out.println("len-->"+jedis.llen("xiaosong1"));
//					System.out.println("message-->" + jedis.brpop(0, "wangtao1").get(1));
//				}
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
		}
	}