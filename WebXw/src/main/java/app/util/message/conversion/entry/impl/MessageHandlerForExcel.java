package app.util.message.conversion.entry.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import app.util.message.conversion.entry.MessageHandler;

public class MessageHandlerForExcel implements MessageHandler{
	protected String MESSAGE_FILE_NAME = "messageData.xlsx";
	protected String WRITE_FILE_NAME = "writeData.xlsx";
	@Override
	public List<List<String>> readDataListFromMessage() throws IOException {
		ResourceLoader loader = new DefaultResourceLoader();  
		Resource resource = loader.getResource(MESSAGE_FILE_NAME);  
		return readDataListFromXSSFWorkbook(resource);
	}

	@Override
	public List<List<String>> readDataListFromMessage(String filePath)
			throws FileNotFoundException, IOException {
		FileSystemResource loader = new FileSystemResource(filePath); 
		String fileType = filePath.substring(filePath.lastIndexOf("."));
		if(".xls".equals(fileType)){
			return readDataListFromHSSFWorkbook(loader);
		}else if(".xlsx".equals(fileType)){
			return readDataListFromXSSFWorkbook(loader);
		}
		throw new IOException("文件格式不正确！请检查文件路径和格式是否为xls或者xlsx");
	}
	
	/**
	 * 根据数据流进行处理。
	 * Excel读取数据也是按行开始。应判断出起始行，然后根据起始行开始读数，直到文件结束，或者遇见结束行
	 * 一行数据为一个List，全部数据为List<List>.
	 * 只负责读取有效值，别的不关心
	 * @param resource
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	private List<List<String>> readDataListFromXSSFWorkbook(Resource resource) throws FileNotFoundException, IOException{
		System.out.println("开始读取文件"+ resource.getFile().getAbsolutePath());
        InputStream is = new FileInputStream(resource.getFile());
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        // Read the Sheet
        List<List<String>> resultList = new ArrayList<List<String>>();
        try {
			for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {//遍历所有sheet
//				resultList = readContenFormOneSheet(xssfWorkbook,numSheet,resultList);	
				XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
				if (xssfSheet == null) {
					continue;
				}
				// Read the Row
				
				List<String> cellResultList = new ArrayList<String>();
				for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
					cellResultList = new ArrayList<String>();
					XSSFRow xssfRow = xssfSheet.getRow(rowNum);
					if (xssfRow != null) {
						for(int cellNum = 0; cellNum < xssfRow.getLastCellNum();cellNum ++){
//							System.out.print(xssfRow.getCell(cellNum));
							cellResultList.add(String.valueOf(xssfRow.getCell(cellNum)));
						}
					}
//					System.out.println();
					resultList.add(cellResultList);
				}
			}
			return resultList;
		} finally{
			if(xssfWorkbook !=null)xssfWorkbook.close();
			if(is!=null)is.close();
		}
	}
	

//	private List<List<String>> readContenFormOneSheet(XSSFWorkbook xssfWorkbook,int numberSheet,List<List<String>> resultList){
//		XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numberSheet);
//		if (xssfSheet == null) {
//			return resultList;
//		}
//		// Read the Row
//		
//		List<String> cellResultList = new ArrayList<String>();
//		for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
//			cellResultList = new ArrayList<String>();
//			XSSFRow xssfRow = xssfSheet.getRow(rowNum);
//			if (xssfRow != null) {
//				for(int cellNum = 0; cellNum < xssfRow.getLastCellNum();cellNum ++){
//					cellResultList.add(String.valueOf(xssfRow.getCell(cellNum)));
//				}
//			}
//			resultList.add(cellResultList);
//		}
//		return resultList;
//	}
	/**
	 * 读取xls文件的数据。
	 * @return
	 * @throws IOException 
	 */
	private List<List<String>> readDataListFromHSSFWorkbook(Resource resource) throws IOException{
		System.out.println("开始读取文件"+ resource.getFile().getAbsolutePath());
        InputStream is = new FileInputStream(resource.getFile());
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        // Read the Sheet
        List<List<String>> resultList = new ArrayList<List<String>>();
        try {
			for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
				HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
				if (hssfSheet == null) {
					continue;
				}
				// Read the Row
				
				List<String> cellResultList = new ArrayList<String>();
				for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
					cellResultList = new ArrayList<String>();
					HSSFRow hssfRow = hssfSheet.getRow(rowNum);
					if (hssfRow != null) {
						for(int cellNum = 0; cellNum < hssfRow.getLastCellNum();cellNum ++){
							cellResultList.add(String.valueOf(hssfRow.getCell(cellNum)));
						}
					}
					resultList.add(cellResultList);
				}
			}
			return resultList;
		} finally{
			if(hssfWorkbook !=null)hssfWorkbook.close();
			if(is!=null)is.close();
		}
	}

	@Override
	public boolean writeToMessage(List<List<String>> dataList, boolean isAddContent) throws IOException {
		ResourceLoader loader = new DefaultResourceLoader();  
		String filePath = loader.getResource(WRITE_FILE_NAME).getFile().getPath();  
		return writeToMessage(dataList,isAddContent,filePath);
	}

	@Override
	public boolean writeToMessage(List<List<String>> dataList, boolean isAddContent, String filePath) throws IOException {
		boolean writeFlag = false;
		String fileType = filePath.substring(filePath.lastIndexOf("."));
		if(".xls".equals(fileType)){
			writeFlag = writeToHSSFWorkbook(dataList,filePath,isAddContent);
		}else if(".xlsx".equals(fileType)){
			writeFlag = writeToXSSFWorkbook(dataList, filePath,isAddContent);
		}
		  
		return writeFlag;
	}
	
	/**
	 * 生成以xls为后缀名的报表，（Excel97适用）
	 * @param dataList
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	private boolean writeToHSSFWorkbook(List<List<String>> dataList,String filePath,boolean isAddContent) throws IOException{
		HSSFWorkbook wb = null;
		FileOutputStream out = null;
		try {
			wb = createHSSFWorkbook(filePath,isAddContent);
			HSSFSheet sheet = wb.getSheetAt(wb.getActiveSheetIndex());
			
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			for(int rowIndex=0;rowIndex<dataList.size();rowIndex++){
				int createIndex = rowIndex;
				if(sheet.getLastRowNum()>0) createIndex =  (sheet.getLastRowNum()+1);
				HSSFRow rowNew = sheet.createRow((short) createIndex);
				List<String> rowResultList = dataList.get(rowIndex);
				for(int cellIndex = 0; cellIndex<rowResultList.size();cellIndex++){
					if(rowIndex == 0)sheet.setColumnWidth(cellIndex, 3000);
					HSSFCell cell = rowNew.createCell(cellIndex);
					cell.setCellValue(rowResultList.get(cellIndex).toString());
					cell.setCellStyle(style);
				}
			}
			out = new FileOutputStream(filePath);
			out.flush();  
			wb.write(out);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			 if(out!=null)out.close();
			 if(wb!=null)wb.close();
		}
	}
	
	private HSSFWorkbook createHSSFWorkbook(String filePath,boolean isAddContent){
		HSSFWorkbook wb = null;
		if(!isAddContent){
			wb = new HSSFWorkbook();
			return wb;
		}
		try {
			FileInputStream fs=new FileInputStream(filePath);  //获取d://test.xls  
			POIFSFileSystem ps=new POIFSFileSystem(fs);  //使用POI提供的方法得到excel的信息  
			wb = new HSSFWorkbook(ps);
		} catch (Exception e) {
			wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("sheet"); 
			sheet.setActive(true);
		}
		return wb;
	}
	
	private boolean writeToXSSFWorkbook(List<List<String>> dataList,String filePath,boolean isAddContent) throws IOException{
		XSSFWorkbook xb = null;
		FileOutputStream os = null;
		XSSFSheet sheet = null;
		int rowNumber = 0;
		try {
			xb = createXSSFWorkbook(filePath,isAddContent);
			sheet = xb.getSheetAt(xb.getActiveSheetIndex());
			
			rowNumber = sheet.getLastRowNum();
			if(rowNumber>0)rowNumber++;
			
	        XSSFCellStyle style = xb.createCellStyle();
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//			HSSFSheet sheet = wb.getSheetAt(wb.getActiveSheetIndex());
			for(int rowIndex=0;rowIndex<dataList.size();rowIndex++){
				XSSFRow row = sheet.createRow(rowNumber+rowIndex);
				List<String> rowList = dataList.get(rowIndex);
				for(int cellIndex = 0; cellIndex<rowList.size();cellIndex++){
					if(rowIndex == 0)sheet.setColumnWidth(cellIndex, 3000);
					XSSFCell cell = row.createCell(cellIndex);
					cell.setCellValue(rowList.get(cellIndex));
					cell.setCellStyle(style);
				}
			}
			os = new FileOutputStream(filePath,isAddContent);
			os.flush();
			xb.write(os);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
		    if(os!=null)os.close();
		    if(xb!=null)xb.close();
		}
	}
	
	private XSSFWorkbook createXSSFWorkbook(String filePath,boolean isAddContent){
		XSSFWorkbook xb = null;
		if(isAddContent){
			try {
				xb = new XSSFWorkbook(filePath);
			} catch (Exception e) {
				xb =  new XSSFWorkbook();
				xb.createSheet("sheet1");
			}
		}else{
			xb =  new XSSFWorkbook();
			xb.createSheet("sheet1");
		}
		return xb;
	}
	
	/**
	 * 从别处copy过来，涵盖一些类的操作方法
	 * @param filePath
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	@Deprecated
	private void copyFromOther(String filePath) throws IOException{
		HSSFWorkbook wb = new HSSFWorkbook();

        // 创建Excel的工作sheet,对应到一个excel文档的tab
        HSSFSheet sheet = wb.createSheet("sheet1");

        // 设置excel每列宽度
        sheet.setColumnWidth(0, 4000);
        sheet.setColumnWidth(1, 3500);

        // 创建字体样式
        HSSFFont font = wb.createFont();
        font.setFontName("Verdana");
        font.setBoldweight((short) 100);
        font.setFontHeight((short) 300);
        font.setColor(HSSFColor.BLUE.index);

        // 创建单元格样式
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        // 设置边框
        style.setBottomBorderColor(HSSFColor.RED.index);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);

        style.setFont(font);// 设置字体

        // 创建Excel的sheet的一行
        HSSFRow row = sheet.createRow(0);
        row.setHeight((short) 500);// 设定行的高度
        // 创建一个Excel的单元格
        HSSFCell cell = row.createCell(0);

        // 合并单元格(startRow，endRow，startColumn，endColumn)
        sheet.addMergedRegion(new org.apache.poi.hssf.util.CellRangeAddress(0, 0, 0, 2));

        // 给Excel的单元格设置样式和赋值
        cell.setCellStyle(style);
        cell.setCellValue("hello world");

        // 设置单元格内容格式
        HSSFCellStyle style1 = wb.createCellStyle();
        style1.setDataFormat(HSSFDataFormat.getBuiltinFormat("h:mm:ss"));

        style1.setWrapText(true);// 自动换行

        row = sheet.createRow(1);

        // 设置单元格的样式格式

        cell = row.createCell(0);
        cell.setCellStyle(style1);
        cell.setCellValue(new Date());

        // 创建超链接
        HSSFHyperlink link = new HSSFHyperlink(HSSFHyperlink.LINK_URL);
        link.setAddress("http://www.baidu.com");
        cell = row.createCell(1);
        cell.setCellValue("百度");
        cell.setHyperlink(link);// 设定单元格的链接

        FileOutputStream os = new FileOutputStream(filePath);
        wb.write(os);
        os.close();
        wb.close();
	}
	
	public static void main(String[] args) throws Exception {    
        FileInputStream fs=new FileInputStream("/C:/test/test7.xls");  //获取d://test.xls  
        POIFSFileSystem ps=new POIFSFileSystem(fs);  //使用POI提供的方法得到excel的信息  
        HSSFWorkbook wb=new HSSFWorkbook(ps);    
        HSSFSheet sheet=wb.getSheetAt(0);  //获取到工作表，因为一个excel可能有多个工作表  
        HSSFRow row=sheet.getRow(0);  //获取第一行（excel中的行默认从0开始，所以这就是为什么，一个excel必须有字段列头），即，字段列头，便于赋值  
        System.out.println(sheet.getLastRowNum()+" "+row.getLastCellNum());  //分别得到最后一行的行号，和一条记录的最后一个单元格  
          
        FileOutputStream out=new FileOutputStream("/C:/test/test7.xls");  //向d://test.xls中写数据  
//        row=sheet.createRow((short)(sheet.getLastRowNum()+1)); //在现有行号后追加数据  
//        row.createCell(0).setCellValue("leilei"); //设置第一个（从0开始）单元格的数据  
//        row.createCell(1).setCellValue(24); //设置第二个（从0开始）单元格的数据 
        
        List<String> rowList = new ArrayList<String>();
		List<List<String>> dataList = new ArrayList<List<String>>();
		for(int rowIndex=0;rowIndex<10;rowIndex++){
			rowList = new ArrayList<String>();
			for(int cell=0;cell<10;cell++){
				rowList.add("row"+row+",cell"+cell);
			}
			dataList.add(rowList);
		}
        
		 for(int rowIndex=0;rowIndex<dataList.size();rowIndex++){
			HSSFRow rowNew = sheet.createRow((short)(sheet.getLastRowNum()+1+ rowIndex));
			List<String> rowResultList = dataList.get(rowIndex);
			for(int cellIndex = 0; cellIndex<rowList.size();cellIndex++){
				if(rowIndex == 0)sheet.setColumnWidth(cellIndex, 3000);
				HSSFCell cell = rowNew.createCell(cellIndex);
				cell.setCellValue(rowResultList.get(cellIndex));
//				cell.setCellStyle(style);
			}
		}
  
          
        out.flush();  
        wb.write(out);    
        out.close();    
        System.out.println(row.getPhysicalNumberOfCells()+" "+row.getLastCellNum()); 
        wb.close();
    }    

}
