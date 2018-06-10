package app.util;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
/**
 * 
 *
 */
public class ConfigUtil {

		public static String getProjectPath() {
			return new File("").getAbsolutePath()+"\\";
		}
		
		public static String getDbConfigPath(String dbFileName) {
			return getProjectPath()+"conf\\"+dbFileName;
		}
		
		public static String getFieldTypePath(String fieldTypeFileName) {
			return getProjectPath()+"conf\\"+fieldTypeFileName;
		}
		
		public static String getTableNamesPath(String tableNamesFileName) {
			return getProjectPath()+"conf\\"+tableNamesFileName;
		}
		
		public static String getExOrImOrPakPath(String exOrimFileName) {
			return getProjectPath()+"conf\\"+exOrimFileName;
		}
		
		public static String getOutputJavaFilePath(String javaFileName){
			return getProjectPath()+"conf\\results\\"+javaFileName;
		}
		
		public static Document getDocument(String configPath) {
			DocumentBuilderFactory documentBuilderFactory =null;
			DocumentBuilder documentBuilder = null;
			Document doc = null;
			try {
			 documentBuilderFactory = DocumentBuilderFactory.newInstance();
			 documentBuilder = documentBuilderFactory.newDocumentBuilder();
			 doc = documentBuilder.parse(configPath);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}catch(SAXException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
			return doc;
		}
		
		
		
		public static String tableName2JavaName(String tableName) {
			//ac_gage_reg
			StringBuffer sbf = new StringBuffer("");
			String[] tableNames = tableName.trim().toLowerCase().split("_");
			for(int i=0; i<tableNames.length;i++) {
					sbf.append(tableNames[i].substring(0, 1).toUpperCase())
						.append(tableNames[i].substring(1,tableNames[i].length()).toLowerCase());
			}
			sbf.append(".java");
			return sbf.toString();
		}
		
		public static String tableName2ClassName(String tableName) {
			String className = tableName2JavaName(tableName);
			className = className.substring(0,className.indexOf('.'));
			return className;
			
		}
		
		public static String columnName2JavaFieldName(String columnName) {
			StringBuffer sbf = new StringBuffer("");
			String columnNames[] = columnName.trim().toLowerCase().split("_");
			for(int i=0; i<columnNames.length; i++) {
				if(i==0) {
					sbf.append(columnNames[i].toLowerCase());
				}else {
					sbf.append(columnNames[i].substring(0,1).toUpperCase())
						.append(columnNames[i].substring(1,columnNames[i].length()).toLowerCase());
				}
			}
			return sbf.toString();
		}
		
		public static String columnName2GetMethodName(String columnName) {
			String javaFieldName = columnName2JavaFieldName(columnName);
			String getMethodName = new StringBuffer("get").append(javaFieldName.substring(0,1).toUpperCase()).append(javaFieldName.substring(1,javaFieldName.length())).toString();
			return getMethodName;
		}
		
		public static String columnName2SetMethodName(String columnName) {
			String javaFieldName = columnName2JavaFieldName(columnName);
			String setMethodName = new StringBuffer("set").append(javaFieldName.substring(0,1).toUpperCase()).append(javaFieldName.substring(1,javaFieldName.length())).toString();
			return setMethodName;
		}
		
		
		
		
		
		
		
}
