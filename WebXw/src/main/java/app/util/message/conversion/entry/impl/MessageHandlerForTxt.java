package app.util.message.conversion.entry.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.util.message.conversion.entry.MessageHandler;

public class MessageHandlerForTxt implements MessageHandler {
	private final String splitStr = "\\|\\+\\|";
	private final String splitStrToWrite = "|+|";
	private final boolean isChangeEmptyStrToNull = false;
	@Override
	public List<List<String>>  readDataListFromMessage() throws IOException {
		String filePath = ClassLoader.getSystemResource("").getPath()+"messageSend.text";
		return readDataListFromMessage(filePath);
	}

	@Override
	public List<List<String>>  readDataListFromMessage(String filePath) throws IOException {
		File file = new File(filePath);
		if(file.exists()){
			List<List<String>> resultList = new ArrayList<List<String>>();
			BufferedReader bReader = new BufferedReader(new FileReader(file));
			String messageForLine = null;
			try {
				List<String> lineResultList = new ArrayList<String>();
				while((messageForLine = bReader.readLine())!=null){
					lineResultList = new ArrayList<String>();
					String[] lineArray = messageForLine.split(splitStr);
					if(isChangeEmptyStrToNull){
						for(String str:lineArray){
							if(str.isEmpty())lineResultList.add(null);
							else lineResultList.add(str);
						}
					}else{
						lineResultList = Arrays.asList(messageForLine.split(splitStr));
					}
					resultList.add(lineResultList);
				}
				return resultList;
			}finally{
				bReader.close();
			}
		}else{
			throw new FileNotFoundException();
		}
	}

	@Override
	public boolean writeToMessage(List<List<String>> dataList,boolean isAddContent) throws IOException {
		String filePath = ClassLoader.getSystemResource("").getPath()+"messageSend.text";
		return writeToMessage(dataList,isAddContent,filePath);
	}

	@Override
	public boolean writeToMessage(List<List<String>> dataList,boolean isAddContent, String filePath) throws IOException {
		File file = new File(filePath);
		if(!file.exists()){
			file.createNewFile();
		}
		BufferedWriter output = new BufferedWriter(new FileWriter(file,isAddContent));   
		try {
			StringBuilder sb = new StringBuilder();
			for(List<String> lineList:dataList){
				sb = new StringBuilder();
				int count = lineList.size();
				for(String value:lineList){
					count -- ;
					sb.append(value);
					if(count>0)sb.append(splitStrToWrite);
				}
				sb.append("\n");
				output.write(sb.toString());
			}
			output.flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			output.close();
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		MessageHandler mh = new MessageHandlerForTxt();
		String readPath = "C:/work/otherSpace/eclipse2015/wmxtcms/src/main/java/app/util/message/conversion/test/testData.txt";
		String writePath = "C:/work/otherSpace/eclipse2015/wmxtcms/src/main/java/app/util/message/conversion/test/testData11.txt";
		List<List<String>> resultList = mh.readDataListFromMessage(readPath);
		List<List<String>> dataList = new ArrayList<List<String>>();
		List<String> lineDataList = new ArrayList<String>();
		for(List<String> rList:resultList){
			lineDataList = new ArrayList<String>();
			for(String value:rList){
//				System.out.println(value);
				lineDataList.add(value);
			}
			dataList.add(lineDataList);
//			System.out.println("---------------------------------------------------");
		}
		
		System.out.println(mh.writeToMessage(dataList,true, writePath));
	}
}
