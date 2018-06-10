package app.creditapp.batch.util;

import java.io.IOException;
import java.util.Properties;

public class FilePathUtil {
	
		private static Properties pathProperties;
		
		static {
			 pathProperties = new Properties();
			try {
				pathProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("path.properties"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		//批量节点调用的shell路径
		public static String getShellPath(){
			return pathProperties.getProperty("shellPath");
		}
		
		//获取数据文件备份路径
		public static String getdataFileBakPath(){
			return pathProperties.getProperty("dataFileBakPath");
		}
		
}
