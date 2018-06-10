package app.util;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import app.base.SourceTemplate;
import app.oscache.BaseCache;
import app.oscache.MBaseCache;


public class CacheInitServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CacheInitServlet() {
	}

	public void init(ServletConfig servletconfig) throws ServletException {
		super.init(servletconfig);
		ApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		SourceTemplate.setContext(applicationContext);
		try {
			System.out.println("****************开始加载数据字典 ***************");
			BaseCache bc = MBaseCache.getCache();
			bc.initCache();
			System.out.println("****************结束 ***************");
			
			System.out.println("************开始初始化系统状态**************");
			bc.initSystemStatus();
//			bc.initHoliday();
			System.out.println("****************结束 ***************");
			
			//System.out.println("************开始初始化审批人员**************");
			//bc.initApprover();
			//System.out.println("****************结束 ***************");
			
			System.out.println("************开始加载按钮权限**************");
			bc.initButton();
			System.out.println("****************结束 ***************");
			
			System.out.println("**************开始加载安全审计**************");
			bc.initSecurity();
			System.out.println("****************结束 ***************");
			
			System.out.println("**************加载REQUIRE_LOG_TABLE**************");
			//bc.initRequireLogTable();
			System.out.println("****************结束 ***************");
		} catch (Exception w) {
			w.printStackTrace();
			System.out.println("CACHE-INIT----数据字典加载到缓存出错！");
			throw new ServletException("Unable to open datasource.");
		}
	}
}