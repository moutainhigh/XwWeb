package app.util.message.conversion.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import app.util.message.conversion.entry.MessageHandler;

public class TestExcelMessage {
	private MessageHandler messageHandlerForExcel;
	private MessageHandler messageHandlerForTxt;

	public void setMessageHandlerForExcel(MessageHandler messageHandlerForExcel) {
		this.messageHandlerForExcel = messageHandlerForExcel;
	}

	public void setMessageHandlerForTxt(MessageHandler messageHandlerForTxt) {
		this.messageHandlerForTxt = messageHandlerForTxt;
	}

	public void testExcelRead(String filePath) throws FileNotFoundException, IOException{
		messageHandlerForExcel.readDataListFromMessage(filePath);
	}
	
	public List<List<String>>  testTxtRead(String filePath) throws FileNotFoundException, IOException{
		return messageHandlerForTxt.readDataListFromMessage(filePath);
	}
	
	public boolean testExcelWrite(List<List<String>> dataList,String filePath) throws FileNotFoundException, IOException{
		return messageHandlerForExcel.writeToMessage(dataList, true, filePath);
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		TestExcelMessage tem = (TestExcelMessage) ac.getBean("testexcelmessage");
////		tem.testExcelRead("/C:/test/test.xlsx");
//		
//		for(int i=0;i<5;i++){
//			List<String> rowList = new ArrayList<String>();
//			List<List<String>> dataList = new ArrayList<List<String>>();
//			for(int row=0;row<10;row++){
//				rowList = new ArrayList<String>();
//				for(int cell=0;cell<10;cell++){
//					rowList.add("row"+row+",cell"+cell);
//				}
//				dataList.add(rowList);
//			}
//			boolean flag = tem.testExcelWrite(dataList, "/C:/test/test7.xls");
//			System.out.println(flag);
//		}
		
		tem.testExcelWrite(tem.testTxtRead("/C:/test/testData.txt"), "/C:/test/test8.xlsx");
	}
	
}
