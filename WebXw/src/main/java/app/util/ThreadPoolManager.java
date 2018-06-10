package app.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

/*
 * 线程管理类
 */
public class ThreadPoolManager {
	private static final Logger logger = Logger.getLogger(ThreadPoolManager.class);
	private ThreadPoolExecutor executor = null;
	private int maxThread = 0; // 最大线程池大小
	
	private static ThreadPoolManager instance0 = null; // 0号线程池
	private static ThreadPoolManager instance1 = null; // 1号线程池
	private static ThreadPoolManager instance2 = null; // 2号线程池
	private static ThreadPoolManager instance3 = null; // 3号线程池
	private static ThreadPoolManager instance4 = null; // 4号线程池
	private static ThreadPoolManager instance5 = null; // 5号线程池
	private static ThreadPoolManager instance6 = null; // 6号线程池
	private static ThreadPoolManager instance7 = null; // 6号线程池
	private static ThreadPoolManager instance8 = null; // 6号线程池
	private static ThreadPoolManager instance10 = null; // 6号线程池
	private static ThreadPoolManager pubInstance = null; // 公用线程池
	private static String threadPoolName = null; // 线程池名称

	private ThreadPoolManager(int maxThread, int maxQueue,String threadPoolName) {
		this.threadPoolName = threadPoolName;
		this.maxThread = maxThread;
		BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(maxQueue);// 队列未来的大小已知時使用
		this.executor = new ThreadPoolExecutor(maxThread, maxThread, 60L, TimeUnit.SECONDS, workQueue);
		logger.info("线程池初始化，线程池大小：" + maxThread + " 队列大小：" + maxQueue);
	}

	/**
	 * @作者 DHCC-SONG
	 * @日期 Jun 6, 2016
	 * @方法说明：0号线程池初始化-预审批筛查和自动审批
	 * @返回参数 ThreadPoolManager
	 */
	public static synchronized ThreadPoolManager getInstance0() {
		if (instance0 != null) {
			return instance0;
		}
		instance0 = new ThreadPoolManager(15, 100,"0号线程池");// 最大线程池大小 和最大队列大小
		return instance0;
	}
	
	/**
	 * @作者 DHCC-SONG
	 * @日期 Jun 6, 2016
	 * @方法说明：1号线程池初始化-校验筛查
	 * @返回参数 ThreadPoolManager
	 */
	public static synchronized ThreadPoolManager getInstance1() {
		if (instance1 != null) {
			return instance1;
		}
		instance1 = new ThreadPoolManager(15, 100,"1号线程池");// 最大线程池大小 和最大队列大小
		return instance1;
	}
	
	/**
	 * @作者 DHCC-SONG
	 * @日期 Jun 6, 2016
	 * @方法说明：2号线程池初始化-评级拆分
	 * @返回参数 ThreadPoolManager
	 */
	public static synchronized ThreadPoolManager getInstance2() {
		if (instance2 != null) {
			return instance2;
		}
		instance2 = new ThreadPoolManager(15, 100,"2号线程池");// 最大线程池大小 和最大队列大小
		return instance2;
	}
	
	/**
	 * @作者 DHCC-SONG
	 * @日期 Jun 6, 2016
	 * @方法说明：3号线程池初始化-自动审批
	 * @返回参数 ThreadPoolManager
	 */
	public static synchronized ThreadPoolManager getInstance3() {
		if (instance3 != null) {
			return instance3;
		}
		instance3 = new ThreadPoolManager(15, 100,"3号线程池");// 最大线程池大小 和最大队列大小
		return instance3;
	}
	
	/**
	 * @作者 DHCC-SONG
	 * @日期 Jun 6, 2016
	 * @方法说明：4号线程池初始化-生成合同
	 * @返回参数 ThreadPoolManager
	 */
	public static synchronized ThreadPoolManager getInstance4() {
		if (instance4 != null) {
			return instance4;
		}
		instance4 = new ThreadPoolManager(15, 100,"4号线程池");// 最大线程池大小 和最大队列大小
		return instance4;
	}
	
	/**
	 * @作者 DHCC-SONG
	 * @日期 Jun 6, 2016
	 * @方法说明：5号线程池初始化-生成借据
	 * @返回参数 ThreadPoolManager
	 */
	public static synchronized ThreadPoolManager getInstance5() {
		if (instance5 != null) {
			return instance5;
		}
		instance5 = new ThreadPoolManager(15, 100,"5号线程池");// 最大线程池大小 和最大队列大小
		return instance5;
	}
	/**
	 * @作者 DHCC-SONG
	 * @日期 Jun 6, 2016
	 * @方法说明：5号线程池初始化-分账处理
	 * @返回参数 ThreadPoolManager
	 */
	public static synchronized ThreadPoolManager getInstance6() {
		if (instance6 != null) {
			return instance6;
		}
		instance6 = new ThreadPoolManager(15, 100,"6号线程池");// 最大线程池大小 和最大队列大小
		return instance6;
	}
	/**
	 * @作者 DHCC-SONG
	 * @日期 Jun 6, 2016
	 * @方法说明：10号线程池-扣款
	 * @返回参数 ThreadPoolManager
	 */
	public static synchronized ThreadPoolManager getInstance7() {
		if (instance7 != null) {
			return instance7;
		}
		instance7 = new ThreadPoolManager(15, 100,"7号线程池");// 最大线程池大小 和最大队列大小
		return instance7;
	}	/**
	 * @作者 DHCC-SONG
	 * @日期 Jun 6, 2016
	 * @方法说明：10号线程池-扣款
	 * @返回参数 ThreadPoolManager
	 */
	public static synchronized ThreadPoolManager getInstance8() {
		if (instance8 != null) {
			return instance8;
		}
		instance8 = new ThreadPoolManager(15, 100,"8号线程池");// 最大线程池大小 和最大队列大小
		return instance8;
	}
	/**
	 * @作者 DHCC-SONG
	 * @日期 Jun 6, 2016
	 * @方法说明：10号线程池-扣款
	 * @返回参数 ThreadPoolManager
	 */
	public static synchronized ThreadPoolManager getInstance10() {
		if (instance10 != null) {
			return instance10;
		}
		instance10 = new ThreadPoolManager(15, 100,"10号线程池");// 最大线程池大小 和最大队列大小
		return instance10;
	}
	
	/**
	 * @作者 DHCC-SONG
	 * @日期 Jun 6, 2016
	 * @方法说明：公共线程池初始化
	 * @返回参数 ThreadPoolManager
	 */
	public static synchronized ThreadPoolManager getPubInstance() {
		if (pubInstance != null) {
			return pubInstance;
		}
		pubInstance = new ThreadPoolManager(15, 100,"公共线程池");// 最大线程池大小 和最大队列大小
		return pubInstance;
	}
	
	
	/**
	 * 线程执行
	 * @param runnable 任务
	 * @return -1 队列已满 0 立即执行 >0排队中
	 */
	public void exec(Runnable runnable) {
		try {
			executor.execute(runnable);// 加入线程池并执行
			int count = executor.getActiveCount();
			if (count >= maxThread) {
				count = executor.getQueue().size();
				logger.info("任务[" + runnable.toString() + "]加入排队中:" + count);
			} else {
				logger.info("任务[" + runnable.toString() + "]开始执行");
			}
		} catch (Exception e) {
			logger.info(threadPoolName+"线程忙，稍等10ms");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			exec(runnable);  // 迭代调用
		}
	}
	

	/**
	 * 线程执行
	 * 
	 * @param runnable
	 *            任务
	 * @return -1 队列已满 0 立即执行 >0排队中
	 */
	public int execute(Runnable runnable) {
		try {
			executor.execute(runnable);// 加入线程池并执行
			int count = executor.getActiveCount();
			if (count >= maxThread) {
				count = executor.getQueue().size();
			} else {
				count = 0;
			}
			return count;
		} catch (Exception e) {
			return -1;
		}
	}

	public boolean isRunning() {
		int count = executor.getActiveCount();
		return count >= 1;
	}

	public void shutdown() {
		if (executor != null && !executor.isShutdown()) {
			executor.shutdown();
			logger.info("线程池关闭");
		}
	}
}