package app.util.ruleFiter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import app.creditapp.sys.entity.Student;
import app.util.ruleFiter.entity.ProcessedData;
import app.util.ruleFiter.entity.ValidateLog;
import app.util.ruleFiter.entity.ValidateParm;
import app.util.ruleFiter.fiter.FiterEngineInterface;

public class FiterEngine {
	/*
	 * 程序目标：
	 * 1.取得从通讯接口（发送方）传递过来的报文或者数据。
	 * 2.取得规则文件中对应的规则校验规范。
	 * 3.根据校验规范转换为校验方法
	 * 4.对报文数据进行校验
	 * 5.将校验结果和报文数据一并发送给接受方。
	 */
	
	/*
	 * 注意问题：
	 * 1.发送文件格式可能有多种格式：json串，txt文件等
	 * 2.发送数据格式可能存在多种格式：json格式，或者自定义格式等。
	 * 3.规则文件要校验的字段和字段值可能有多种情况，比如限定数字，限定长度等。
	 * 4.无论校验结果为何，都要将报文数据和校验结果发送给接收方
	 */
	
	/*
	 * 开发中的一些思路要点：
	 * 1.校验规则可以采用正则。使用一个枚举类，自定义一些自带的校验规则，例如身份证，手机号码等。
	 * 		如果为其他，则根据其他条件（最大长度，最小长度，是否为空等）生成正则。
	 * 2.校验规则中的要素为非空，自定义。
	 * 3.可以设置警告等级，error，warning，success。
	 */
	
	/*
	 * 几大关键步骤
	 * 1.如何获取数据
	 * 2.如何获取规则文件信息
	 * 3.如何将规则文件信息转化为正则或其他校验方法
	 * 4.如何校验数据
	 */
	
	
	/*
	 * 准备要做的工作：
	 * processSendData();
	 * createValidateRule();
	 */
	private FiterEngineInterface filterEngineInterface;
	
	/**
	 * 获取验证结果的日志信息。
	 * @param returnAtOnce 确定是否立即返回。如果为真，则遇到任何一个错误或者警告信息立即终止判断并返回错误。<br>如果为假，则全部校验完成后返回日志信息列表
	 * @return 返回ValidateLog类，可通过warningList和errorList获取警告或者错误信息.
	 */
	public ValidateLog getValidateLogList(boolean returnAtOnce){
		//获取需要校验的数据
		SendDataProcessEngine spEngine = new SendDataProcessEngine();
		ProcessedData processedData = spEngine.processSendDataForTest();
		
		//获取加工过的需要验证的报文数据
		Map<String,String> dataMap = processedData.getProcessedDataMap();
		//对报文数据进行校验
		List<ValidateLog> logList = null;
		try {
			logList = 	filterEngineInterface.validateDataMap(processedData.getRuleId(), dataMap, returnAtOnce);
		} catch (IOException e) {
			e.printStackTrace();
		}

		ValidateLog log =  new ValidateLog();
		log.setResultlogList(logList);
		log.showValidateResult(false);
		return log;
	}
	
	/**
	 * 仅判断是否校验成功。仅存在警告信息一样返回成功。
	 * @return 返回值为真，则校验成功，数据通过，返回值为假，则校验失败。
	 */
	public boolean doValidateFiter(){
		return !(getValidateLogList(false).getErrorList().size()>0);
	}
	
	/**
	 * 获取验证结果的日志信息，在不传递参数情况下，默认将调用getValidateLogList(false)方法。返回完整的结果集
	 * @return 返回ValidateLog类，可通过warningList和errorList获取警告或者错误信息.
	 */
	public ValidateLog getValidateLogList(){
		return getValidateLogList(false);
	}
	
	public List<ValidateLog> validateDataMap(Map<String,ValidateParm> rulesMap,Map<String,String> dataMap,boolean returnAtOnce){
		return filterEngineInterface.validateDataMap(rulesMap, dataMap, returnAtOnce);
	}
	
	public void setFilterEngineInterface(FiterEngineInterface filterEngineInterface) {
		this.filterEngineInterface = filterEngineInterface;
	}

	public static void main(String[] args) throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		Student student = new Student();
//		student.setAge(23);
//		student.setCost(240.0);
//		student.setEmail("ddd123123");
//		student.setName("Tim");
		FiterEngineInterface filterEngineInterface = (FiterEngineInterface) ac.getBean("filterEngineInterface");
		ValidateLog log = filterEngineInterface.createValidateLog("101", student, false);
		log.showValidateResult(true);
//		File file = new File("C:/work/otherSpace/eclipse2015/wmxt/WebRoot/WEB-INF/classes/ruleData.xml");
//		System.out.println(file.exists());
//		FiterEngine fe = new FiterEngine();
//		fe.getValidateLogList(false);
	}
}
