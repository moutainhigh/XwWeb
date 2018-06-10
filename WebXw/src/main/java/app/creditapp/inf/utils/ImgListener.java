package app.creditapp.inf.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
/**
 * 影像从out中取文件监听
 *
 */
public class ImgListener implements ServletContextListener {
	TaskIndex taskIndex=null;
	private ScheduledExecutorService	scheduExec=Executors.newScheduledThreadPool(2);
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		/*if (taskIndex!=null){
			taskIndex.interrupt();
			taskIndex.stop();
        }*/

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//Datadict data = new Datadict("IMG_RELATION");
		//int second=Integer.parseInt(data.new Datadict("IMG_RELATION"));//几秒查询一次
		//int start=Integer.parseInt(data.getDatadictByCode("START"));//几秒查询一次
		//scheduExec.scheduleAtFixedRate(new TaskIndex(),60000, 300000, TimeUnit.MILLISECONDS);
		
		//下面一条语句是ljx 注释掉的 监听out 文件 监听
	//	scheduExec.scheduleAtFixedRate(new TaskIndex(),120000, 300000, TimeUnit.MILLISECONDS);
	}

}
