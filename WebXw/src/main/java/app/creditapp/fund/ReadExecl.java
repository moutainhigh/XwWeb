package app.creditapp.fund;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import app.creditapp.fund.entity.FundDetail;

public class ReadExecl {
	
	public List<FundDetail> readXls(File filePath) throws IOException {
	      InputStream is = new FileInputStream(filePath);
	      HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
	      FundDetail fundDetail = null;
	      List<FundDetail> list = new ArrayList<FundDetail>();
	      // 循环工作表Sheet
	      for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
	          HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
	          if (hssfSheet == null) {
	              continue;
	          }
	          // 循环行Row
	          for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
	              HSSFRow hssfRow = hssfSheet.getRow(rowNum);
	              if (hssfRow != null) {
	                  fundDetail = new FundDetail();
	                  HSSFCell tradeType = hssfRow.getCell(0);
	                  HSSFCell txAmt = hssfRow.getCell(1);
	                  HSSFCell txDate = hssfRow.getCell(2);
	                  HSSFCell filler = hssfRow.getCell(3);
	                  fundDetail.setTradeType(getValue(tradeType));
	                 /* 
	                  try {
	                	  fundDetail.setTxAmt(Double.valueOf(MessageUtil.transNumberCount(getValue(txAmt))));
						} catch (NumberFormatException e) {
							throw new NumberFormatException("数值转换过程中出现异常，转换前数字为："+txAmt);
						}*/
	                  if(getValue(txAmt)==null||"".equals(getValue(txAmt))){
	                	  fundDetail.setTxAmt(0.00);
	                  }else{
	                	  fundDetail.setTxAmt(Double.valueOf(getValue(txAmt)));
	                  }
	                  fundDetail.setTxDate(getValue(txDate));
	                  fundDetail.setFiller(getValue(filler));
	                  list.add(fundDetail);
	              }
	          }
	      }
	      return list;
	  }
	  
	  @SuppressWarnings("static-access")
	private String getValue(HSSFCell hssfCell) {
		  if(hssfCell==null){
			  return "";
		  }else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
              // 返回布尔类型的值
              return String.valueOf(hssfCell.getBooleanCellValue());
          } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
              // 返回数值类型的值
              return String.valueOf(hssfCell.getNumericCellValue());
          } else {
              // 返回字符串类型的值
              return String.valueOf(hssfCell.getStringCellValue());
          }
	  }
	  
	/*  public void write2excel(List<String> valRes,String uploadFileName) {
		  HSSFWorkbook excel = new HSSFWorkbook();
		  HSSFSheet sheet = excel.createSheet("fd");
		  HSSFRow firstRow = sheet.createRow(0);
		  HSSFCell cells[] = new HSSFCell[5];
		  String[] titles = new String[] { "id", "name", "description", "price",
		  	"credit" };
		  for (int i = 0; i < 5; i++) {
			  cells[0] = firstRow.createCell(i);
			  cells[0].setCellValue(titles[i]);
		  }
		  for (int i = 0; i < valRes.size(); i++) {
		   HSSFRow row = sheet.createRow(i + 1);
		   HSSFCell cell = row.createCell(0);
		   cell = row.createCell(4);
		   cell.setCellValue(valRes.get(i));
		  }
		        OutputStream out = null;
		        try {
		            out = new FileOutputStream(uploadFileName);
		            excel.write(out);
		            out.close();
		        } catch (FileNotFoundException e) {
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		 }*/
}
