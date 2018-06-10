package app.creditapp.redis.daemon;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import app.creditapp.redis.util.RedisUtil;


public class ThreadDaemon implements Runnable {
	Logger logger = Logger.getLogger(ThreadDaemon.class);
	@Override
	public void run() {
		while(true){
			Vector<ThreadBean> tlist = ThreadFactory.tlist;
			if(tlist.size()==0){return;}
			for(int i=0; i<tlist.size(); i++){
				ThreadBean tb = tlist.get(i);
				if(!tb.getThread().isAlive()){
                    logger.error("***************线程："+tb.getThread().getId()+":"+tb.getThreadDesc()+"重新运行！");
					ThreadBean ttb = ThreadFactory.create(tb.getRun());
					tlist.set(i, ttb);
					ttb.getThread().start();
				}
			}
			try {
	            TimeUnit.SECONDS.sleep(RedisUtil.CHECK_TIME); // 200秒检查一次
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        logger.error("守护线程检测业务节点服务线程完成，线程总数："+tlist.size()+"个");
//	        System.out.println("线程总数："+tlist.size()+"个");
//	        for(ThreadBean tb : tlist)
//	        	System.out.println("线程id："+tb.getThread().getId());
		}
	}
}
