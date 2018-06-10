package app.util.ruleFiter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import app.creditapp.sys.entity.Student;
import app.util.ruleFiter.clientPort.SenderInterface;
import app.util.ruleFiter.entity.ProcessedData;
import app.util.ruleFiter.entity.SendData;
import app.util.ruleFiter.type.FileType;

public class SendDataProcessEngine {
	private SenderInterface senderInterface;

	/**
	 * 设定获取数据的方式。接口中需要实现sendDataByFile方法。将传递的数据格式化为sendData类格式，其中可以包含file文件或者json数据串。<br>但必须要声明文件类型以便于解析器选择解析方式。
	 * @param senderInterface
	 */
	public void setSenderInterface(SenderInterface senderInterface) {
		this.senderInterface = senderInterface;
	}
	
	/**
	 * 通过senderInterface的设定获取输入的数据，并格式化为ProcessedData类，并将其返回。<br>
	 * 输入的数据应当包含文件格式类型，例如xml或者json，以让格式化工具选择对应的格式化方法。
	 * @return ProcessedData类中包含需要的校验ID-ruleId，以及格式化好的Map键值映射
	 */
	public ProcessedData processSendData(){
		SendData sendData = senderInterface.sendDataByFile();
		return transferSendData(sendData);
	}
	
	public ProcessedData processSendData(String ruleId,String filePath){
		SendData sendData = new SendData();
		sendData.setSendRuleId(ruleId);
		sendData.setFileType(FileType.TXT);
		sendData.setSendFileData(new File(filePath));
		return transferSendData(sendData);
	}
	
	private ProcessedData transferSendData(SendData sendData){
		ProcessedData pData = new ProcessedData();
		pData.setRuleId(sendData.getSendRuleId());
		//TODO 对传递来的文件或者数据进行处理
		/*
		 * 报文数据可能存在txt,excel,json,xml等格式
		 */
		if(sendData.getFileType() == FileType.JSON){
			pData.setProcessedDataMap(transferDataForJson(sendData.getSendStringData()));
		}
		
		return pData;
	}
	
	private Map<String, String> transferDataForJson(String jsonStr) {
		Map<String, String> valueMap = new HashMap<String, String>();
//		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
//		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
//		Iterator<?> it = jsonObject.keys();
//		String key = null;
//		String value = null;
//		while (it.hasNext()) {
//			key = String.valueOf(it.next());
//			value = String.valueOf(jsonObject.get(key));
//			valueMap.put(key, value);
//		}
		return valueMap;
	}
	
	private List<Map<String, String>> transferDataForTxt(String jsonStr) {
		List<Map<String, String>> resultMapList = new ArrayList<Map<String, String>>();
		Map<String, String> valueMap = new HashMap<String, String>();
		//TODO 完成批量获取报文数据的结果集
		return resultMapList;
	}
	
	private Map<String, String> transferDataForXML(String jsonStr) {
		Map<String, String> valueMap = new HashMap<String, String>();
		return valueMap;
	}
	
	
	/**
	 * 主要用于测试，生成一个学生类（Student）的ProcessedData对象。
	 * @return
	 */
	public ProcessedData processSendDataForTest(){
		SendData sendData = testCreateSendData();
		return transferSendData(sendData);
	}
	
	private SendData testCreateSendData(){
		Student student = new Student();
//		student.setAge(23);
//		student.setCost(240.0);
//		student.setEmail("ddd123123");
//		student.setName("Tim");
		
//		String jsonStr = JSONObject.fromObject(student).toString();
		String jsonStr = JSON.toJSONString(student);
		System.out.println(jsonStr);
		SendData sendData = new SendData();
		sendData.setFileType(FileType.JSON);
		sendData.setSendStringData(jsonStr);
		sendData.setSendRuleId("101");
		return sendData;
	}
}
