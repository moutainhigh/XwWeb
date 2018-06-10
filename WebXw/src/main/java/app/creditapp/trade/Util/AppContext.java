package app.creditapp.trade.Util;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppContext {
	private static AppContext instance;
	private AbstractApplicationContext appContext;

	public synchronized static AppContext getInstance() {
		if (instance == null) {
			instance = new AppContext();
		}
		return instance;
	}

	private AppContext() {
		//appContext = new ClassPathXmlApplicationContext("/serverappContext.xml");
		appContext = new ClassPathXmlApplicationContext("/applicationContext.xml");
	}

	public AbstractApplicationContext getAppContext() {
		return appContext;
	}
}
