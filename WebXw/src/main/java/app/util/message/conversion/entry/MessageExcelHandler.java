package app.util.message.conversion.entry;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface MessageExcelHandler extends MessageHandler{
	List<List<String>> readDataListFromMessage(String filePath,int sheetNumber) throws FileNotFoundException, IOException; 
	List<List<String>> readDataListFromMessage(int sheetNumber) throws FileNotFoundException, IOException; 
}
