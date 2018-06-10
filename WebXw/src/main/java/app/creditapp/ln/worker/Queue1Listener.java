package app.creditapp.ln.worker;

import org.apache.log4j.Logger;

import redis.clients.jedis.JedisPubSub;

/**
 * @作者 DHCC-SONG
 * @日期 Jun 6, 2016
 * @描述 1号消息队列（待校验和筛查队列）监听器--作废
 */
public class Queue1Listener extends JedisPubSub{
	Logger logger = Logger.getLogger(Queue1Listener.class);
	public int i = 0;
	public int j = 0;
	// 取得订阅的消息后的处理
	@Override
	 public void onMessage(String channel, String message) {
		try {
//			Message m = (Message) JsonUtil.getObject4JsonString(message, Message.class);
//			WorkAforValidate workerA = new WorkAforValidate(m);
//			int num = ThreadPoolManager.getInstance1().execute(workerA);
//			while (true) {
//				if (num == -1) {
//					logger.info("线程忙，稍等2秒钟");
//					Thread.sleep(2000);
//					num = ThreadPoolManager.getInstance1().execute(workerA);
//				} else if (num > 0) {
//					logger.info("任务[" + m.getId() + "]加入排队中:" + num);
//					break;
//				} else {
//					logger.info("任务[" + m.getId() + "]开始执行");
//					break;
//				}
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }

	 // 初始化订阅时候的处理
	@Override
	 public void onSubscribe(String channel, int subscribedChannels) {
	   System.out.println(channel + "=" + subscribedChannels+"队列监听成功....");
	 }

	 // 取消订阅时候的处理
	@Override
	 public void onUnsubscribe(String channel, int subscribedChannels) {
	   System.out.println(channel + "=" + subscribedChannels+"#########");
	 }

	 // 初始化按表达式的方式订阅时候的处理
	@Override
	 public void onPSubscribe(String pattern, int subscribedChannels) {
	   System.out.println(pattern + "=" + subscribedChannels+"队列订阅成功");
	 }

	 // 取消按表达式的方式订阅时候的处理
	@Override
	 public void onPUnsubscribe(String pattern, int subscribedChannels) {
	   System.out.println(pattern + "=" + subscribedChannels+"@@@@@@@@@");
	 }

	 // 取得按表达式的方式订阅的消息后的处理
	@Override
	 public void onPMessage(String pattern, String channel, String message) {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(pattern + "=" + channel + "=" + message);
		
//		if (pattern.equals("hello_*")) {
//			++i;
//			new Thread(new Runnable() {
//				public void run() {
//				     try {
//				    	 if(i==3){
//				    		 Thread.currentThread().sleep(2000);
//				    		 System.out.println("休眠2000M");
//				    	 }
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					System.out.println("hello_*" + "逻辑运算");
//				}
//			}).start();
//		}
//		if (pattern.equals("hi_*")) {
//			++j;
//			new Thread(new Runnable() {
//				public void run() {
//					System.out.println("hi_*" + "逻辑运算");
//				}
//			}).start();
//		}
//		System.out.println(i+"~~~"+j);
	}


}
