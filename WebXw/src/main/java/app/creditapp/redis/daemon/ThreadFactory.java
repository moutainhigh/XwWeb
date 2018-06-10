package app.creditapp.redis.daemon;

import java.util.Vector;

public class ThreadFactory {

	public static Vector<ThreadBean> tlist = new Vector<ThreadBean>();

	public static ThreadBean initcreate(Runnable run){
		Thread thread = new Thread(run);
		ThreadBean tb = new ThreadBean();
		tb.setRun(run);
		tb.setThread(thread);
		tb.setThreadDesc(run.toString());
		tlist.add(tb);
		thread.start();
//		System.out.println("线程："+thread.getId()+"开始运行！");
		return tb;
	}
	
	public static ThreadBean create(Runnable run){
		Thread thread = new Thread(run);
		ThreadBean tb = new ThreadBean();
		tb.setRun(run);
		tb.setThread(thread);
		return tb;
	}
}
