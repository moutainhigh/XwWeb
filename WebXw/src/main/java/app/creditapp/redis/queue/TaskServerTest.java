package app.creditapp.redis.queue;

import redis.clients.jedis.Jedis;
import app.creditapp.ln.entity.Message;
import app.creditapp.ln.worker.Queue1Listener;
import app.creditapp.redis.util.RedisUtil;

/**
 * 测试消息发送
 */
public class TaskServerTest implements Runnable{
	public void run() {
		Jedis jedis = RedisUtil.getJedis();
		final Queue1Listener listener = new Queue1Listener();
		//可以订阅多个频道  
		//订阅得到信息在lister的onMessage(...)方法中进行处理  
		//jedis.subscribe(listener, "foo", "watson");  
		jedis.subscribe(listener, "xiaosong");
		//也用数组的方式设置多个频道  
		//jedis.subscribe(listener, new String[]{"hello_foo","hello_test"});  
		  
		//这里启动了订阅监听，线程将在这里被阻塞  
		//订阅得到信息在lister的onPMessage(...)方法中进行处理  
		//jedis.psubscribe(listener, new String[]{"hello_*"});//使用模式匹配的方式设置频道  
	}
	
	public static void main(String[] args) throws Exception {
		Jedis jedis = RedisUtil.getJedis();
		//Jedis jedis = new Jedis("10.7.101.38",6379);
		Message m = null;
		try {
			for(int i = 1;i<=10;i++){
				m = new Message();
				m.setId(i);
				m.setContent("测试JEDIS"+i);
				//new String(SerializeUtil.serialize(m));
				//jedis.publish("queue1","测试JEDIS"+i);
				//jedis.publish("xiaosong1",JsonUtil.getJsonString4JavaPOJO(m));
				jedis.lpush("xiaosong1", "fgdgfdasfdsfdas");
				System.out.println(i+"消息已经发送");
			}
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
}
