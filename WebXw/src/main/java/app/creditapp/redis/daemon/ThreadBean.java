package app.creditapp.redis.daemon;

public class ThreadBean {

	private Thread thread;
	private Runnable run;
	private String threadDesc;
	public String getThreadDesc() {
		return threadDesc;
	}
	public void setThreadDesc(String threadDesc) {
		this.threadDesc = threadDesc;
	}
	public Thread getThread() {
		return thread;
	}
	public void setThread(Thread thread) {
		this.thread = thread;
	}
	public Runnable getRun() {
		return run;
	}
	public void setRun(Runnable run) {
		this.run = run;
	}
	
	
}
