package app.creditapp.acc.init;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import accounting.plat.core.init.BusinessInitializer;

public class AccountingInitServlet extends HttpServlet {

	
	public void init() throws ServletException {
		BusinessInitializer buz = new BusinessInitializer();
		try {
			System.out.println("核算系统初始化.............");
			buz.initialize();
			System.out.println("核算系统初始化成功");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("核算系统初始化失败");
		}
		
	}

}
