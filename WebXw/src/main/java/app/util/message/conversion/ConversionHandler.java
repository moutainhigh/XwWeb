package app.util.message.conversion;

import java.io.IOException;
import java.net.URL;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import app.util.message.conversion.transfer.TransferHandler;

import com.core.struts.BaseFormBean;

/**
 * 主要目的是对报文信息进行转换。
 * 目前确定的信息是
 * 1.分隔符使用|+|格式
 * 2.组件分为四个：报文数据，数据库对应的表格，表字段与报文字段的顺序映射，转换器
 * 3.报文数据可以转换为数据库值进行插入，数据库数据也可以转换为报文。
 * 4.分包设计：
 * 	1.报文数据入口
 * 	2.映射文件入口
 * 	3.转换器组件
 * 	4.插入或读取数据库
 * 5.导库设计流程方案
 * 	1.获取报文-接口
 * 	2.根据约定寻找到映射关系配置
 * 	3.根据配置初始化javabean或者map
 * 	4.执行数据库方法，将值插入表中。
 * 	
 * 	输入：报文数据，映射配置ID
 * 	输出：将数据插入数据库中，返回处理库结果
 * 6.出库设计流程方案
 * 	1.根据约定编号找到映射配置关系，
 * 	2.根据数据查询ID或者sql语句找到数据库表格，并读取数据。
 * 	3.将读出的map或者javabean，通过映射配置关系进行转换
 * 	4.将转换后的结果写入报文文件中。
 * 
 * 	输入：映射配置ID，查询数据的sql或者条件。
 * 	输出：写入报文文件，返回处理结果
 *
 */
public class ConversionHandler extends BaseFormBean{
	private TransferHandler transferHandlerExcel;
	
	public void setTransferHandler(TransferHandler transferHandlerExcel) {
		this.transferHandlerExcel = transferHandlerExcel;
	}

	public String testThAction () throws Exception{
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		
//		String mappingConifg = "C:/work/otherSpace/eclipse2015/wmxtcms/src/main/java/app/util/message/conversion/test/mappingConfig.txt";
//		String readFilePath = "C:/work/otherSpace/eclipse2015/wmxtcms/src/main/java/app/util/message/conversion/test/testData.txt";
		String readFilePath = "C:/test/text8.xlsx";
		String writeFilePath = "C:/work/otherSpace/eclipse2015/wmxtcms/src/main/java/app/util/message/conversion/test/writeData.txt";
//		TransferHandler th = (TransferHandler) ac.getBean("transferHandler");
		transferHandlerExcel.refreshMappingConfig();
//		th.messageToData("0001",readFilePath);
//		String sql = "select id,idno,name,age,salary from TESTTH";
//		th.dataToMessage("0001", sql, writeFilePath);
//		
//		List<TestTh> testTsList = new ArrayList<TestTh>();
//		testTsList.add(new TestTh("000001","idno0001","name00001",10,10.01));
//		testTsList.add(new TestTh("000002","idno0002","name00002",11,11.01));
//		testTsList.add(new TestTh("000003","idno0003","name00003",12,12.01));
//		testTsList.add(new TestTh("000004","idno0004","name00004",13,13.01));
//		testTsList.add(new TestTh("000005","idno0005","name00005",14,14.01));
//		testTsList.add(new TestTh("000006","idno0006","name00006",15,15.01));
//		th.objectToMessage("0001", testTsList, writeFilePath);
		
		//测试1000条数据的插入
		long starttime = new Date().getTime();
		transferHandlerExcel.messageToDataWithValidate("0001", "102", readFilePath);
		long endtime = new Date().getTime();
		System.out.println("执行时间为:"+(endtime - starttime)+"毫秒");
//		System.out.println(System.getProperty("webapp.root"));
//		System.out.println(ConversionHandler.class.getClass().getResource("/"));
//		System.out.println(this.getHttpRequest().getSession().getServletContext().getRealPath(""));
		URL S =this.getClass().getResource("/");
		System.out.println(S.getPath());
		return "success";
	}
	
	public void teseExcel(String readFilePath) throws IOException{
		//测试1000条数据的插入
		transferHandlerExcel.refreshMappingConfig();
		long starttime = new Date().getTime();
//		transferHandlerExcel.messageToData("0001", readFilePath);
		transferHandlerExcel.dataToMessage("0001", "select * from TESTTH",readFilePath);
		long endtime = new Date().getTime();
		System.out.println("执行时间为:"+(endtime - starttime)+"毫秒");
	}
	
	public String testExcelTrans() throws Exception{
		String readFilePath = "C:/test/test8.xlsx";
		String writeFilePath = "C:/test/test9.xlsx";
		teseExcel(writeFilePath);
		return "success";
	}
	
	public static void main(String[] args) throws IOException {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		ConversionHandler conversionHandler = (ConversionHandler) ac.getBean("conversionHandler");
		//测试1000条数据的插入
		long starttime = new Date().getTime();
		String readFilePath = "C:/test/test8.xlsx";
		conversionHandler.teseExcel(readFilePath);
		long endtime = new Date().getTime();
		System.out.println("执行时间为:"+(endtime - starttime)+"毫秒");
	}
}
