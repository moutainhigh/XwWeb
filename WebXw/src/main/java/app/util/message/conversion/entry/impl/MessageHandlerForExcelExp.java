package app.util.message.conversion.entry.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import app.util.message.conversion.entry.MessageExcelHandler;

public class MessageHandlerForExcelExp extends MessageHandlerForExcel implements MessageExcelHandler{

	@Override
	public List<List<String>> readDataListFromMessage(int sheetNumber) throws IOException {
		ResourceLoader loader = new DefaultResourceLoader();  
		Resource resource = loader.getResource(super.MESSAGE_FILE_NAME);  
		return readDataListFromXSSFWorkbook(resource,sheetNumber);
	}
	@Override
	public List<List<String>> readDataListFromMessage(String filePath, int sheetNumber) throws FileNotFoundException, IOException {
		FileSystemResource loader = new FileSystemResource(filePath); 
		String fileType = filePath.substring(filePath.lastIndexOf("."));
		if(".xls".equals(fileType)){
			return readDataListFromHSSFWorkbook(loader,sheetNumber);
		}else if(".xlsx".equals(fileType)){
			return readDataListFromXSSFWorkbook(loader,sheetNumber);
		}
		throw new IOException("文件格式不正确！请检查文件路径和格式是否为xls或者xlsx");
	}

	private List<List<String>> readDataListFromXSSFWorkbook(Resource resource,int sheetNumber) throws FileNotFoundException, IOException{
		System.out.println("开始读取文件"+ resource.getFile().getAbsolutePath());
        InputStream is = new FileInputStream(resource.getFile());
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        // Read the Sheet
        List<List<String>> resultList = new ArrayList<List<String>>();
        try {
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(sheetNumber);
			if (xssfSheet == null)return null; 
			// Read the Row
			
			List<String> cellResultList = new ArrayList<String>();
			for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				cellResultList = new ArrayList<String>();
				XSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if (xssfRow != null) {
					for(int cellNum = 0; cellNum < xssfRow.getLastCellNum();cellNum ++){
						cellResultList.add(String.valueOf(xssfRow.getCell(cellNum)));
					}
				}
				resultList.add(cellResultList);
			}
			return resultList;
		} finally{
			if(xssfWorkbook !=null)xssfWorkbook.close();
			if(is!=null)is.close();
		}
	}
	
	/**
	 * 读取xls文件的数据。
	 * @return
	 * @throws IOException 
	 */
	private List<List<String>> readDataListFromHSSFWorkbook(Resource resource,int sheetNumber) throws IOException{
		System.out.println("开始读取文件"+ resource.getFile().getAbsolutePath());
        InputStream is = new FileInputStream(resource.getFile());
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        // Read the Sheet
        List<List<String>> resultList = new ArrayList<List<String>>();
        try {
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(sheetNumber);
			if (hssfSheet == null) {
				return null;
			}
			
			List<String> cellResultList = new ArrayList<String>();
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				cellResultList = new ArrayList<String>();
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				HSSFRow hssfRowTitle = hssfSheet.getRow(0);
				if (hssfRow != null) {
					int b = hssfRowTitle.getLastCellNum();
					for(int cellNum = 0; cellNum < b;cellNum ++){
						String s = String.valueOf(hssfRow.getCell(cellNum));
						if("null".equals(s)){
							cellResultList.add("");
						}else{
							cellResultList.add(s);
						}
//						cellResultList.add(String.valueOf(hssfRow.getCell(cellNum)));
					}
				}
				resultList.add(cellResultList);
			}
			return resultList;
		} finally{
			if(hssfWorkbook !=null)hssfWorkbook.close();
			if(is!=null)is.close();
		}
	}
}
