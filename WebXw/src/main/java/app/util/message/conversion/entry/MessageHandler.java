package app.util.message.conversion.entry;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * 主要目标为对报文数据操作
 * 1.获取报文数据（另外实现）
 * 2.将处理好的数据转化为报文，并写入文件
 *
 */
public interface MessageHandler {
	
	/**
	 * 根据报文内容获取有序的数据集合
	 * List中存放的读取出来的数据，List为有序，因此值是有顺序的。在转换时，会根据顺序和映射配置中的顺序进行匹配
	 * @return 有序的数据结果集
	 * @throws IOException 
	 */
	List<List<String>> readDataListFromMessage() throws IOException;
	List<List<String>> readDataListFromMessage(String filePath) throws FileNotFoundException, IOException;
	
	/**
	 * 根据有序结果集，将值写进文件当中
	 * @param dataList
	 * @return
	 * @throws IOException 
	 */
	boolean writeToMessage(List<List<String>> dataList,boolean isAddContent) throws IOException;
	boolean writeToMessage(List<List<String>> dataList,boolean isAddContent,String filePath) throws IOException;
}
