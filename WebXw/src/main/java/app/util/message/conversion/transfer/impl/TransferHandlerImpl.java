package app.util.message.conversion.transfer.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import app.util.message.conversion.db.DataBaseHandler;
import app.util.message.conversion.entity.TransferResult;
import app.util.message.conversion.entry.MessageExcelHandler;
import app.util.message.conversion.entry.MessageHandler;
import app.util.message.conversion.mapping.MappingHandler;
import app.util.message.conversion.mapping.entity.MappingColumn;
import app.util.message.conversion.mapping.entity.MappingEntity;
import app.util.message.conversion.transfer.TransferHandler;
import app.util.message.conversion.type.DataType;
import app.util.message.conversion.uitl.MessageUtil;
import app.util.message.conversion.validate.MessageValidate;
import app.util.ruleFiter.entity.ValidateLog;

public class TransferHandlerImpl implements TransferHandler{
	
	private MessageHandler messageHandler;
	private MappingHandler mappingHandler;
	private DataBaseHandler databaseHandler;
	private MessageValidate messageValidate;
	private MessageExcelHandler messageExcelHandler;
	
	
	private boolean isAddContent = true;
	
	@Override
	public void isAddFileContent(boolean isAdd) {
		isAddContent = isAdd;
	}
	
	public TransferHandlerImpl(){
	}
	
	public TransferHandlerImpl(String mappingConfigPath){
	}
	
	public void refreshMappingConfig() throws IOException{
		mappingHandler.refreshConfig();
	}

	@Override
	public boolean messageToData(String mappingId) throws IOException {
		return messageToData(mappingId, null);
	}

	@Override
	public boolean messageToData(String mappingId, String filePath) throws IOException {
		List<MappingEntity> entityList = createMappingEntityList(mappingId,filePath);
		return databaseHandler.insertDataIntoDb(entityList);
	}
	
	public boolean messageToDataWithValidate(String mappingId, String ruleId,String filePath) throws IOException {
		List<MappingEntity> entityList = createMappingEntityList(mappingId,filePath);
		ValidateLog log = messageValidate.validateMessage(ruleId, entityList);
		System.out.println("出错的报文记录数有："+log.getErrorList().size());
		return databaseHandler.insertDataIntoDb(entityList);
	}
	
	public boolean messageToDataWithValidate(String mappingId, String ruleId) throws IOException {
		return messageToDataWithValidate(mappingId,ruleId,null);
	}
	
	public boolean messageToDataWithValidate(String[] mappingIdArgs, String[] ruleIdArgs) throws Exception{
		return messageToDataWithValidate(mappingIdArgs,ruleIdArgs,null);
	}
	
	public boolean messageToDataWithValidate(String[] mappingIdArgs, String[] ruleIdArgs,String filePth) throws Exception{
		if(mappingIdArgs.length != ruleIdArgs.length)throw new Exception("校验ID的参数个数与规则ID的参数个数不一致，请检查输入！");
		if(mappingIdArgs.length == 1)return messageToDataWithValidate(mappingIdArgs[0],ruleIdArgs[0],filePth);
		
		List<MappingEntity> entityList = new ArrayList<MappingEntity>();
		int errorNumber = 0;
		boolean isAllInsertSuccess = false;
		for(int index = 0; index < mappingIdArgs.length; index++){
			//createMappingEntityList 要提供一个数字，用来获取sheetNumber
			entityList = createMappingEntityList(mappingIdArgs[index],filePth,index);
			//log的错误需要进行统计
			ValidateLog log = messageValidate.validateMessage(ruleIdArgs[index], entityList);
			errorNumber += log.getErrorList().size();
			//insertDataIntoDb中的List为汇总后的结果
			isAllInsertSuccess = databaseHandler.insertDataIntoDb(entityList);
			if(!isAllInsertSuccess)System.out.println("第"+index+"组数据插入时出现错误，请检查后重新插入。");
		}
		System.out.println("出错的报文记录数有："+errorNumber);
		return isAllInsertSuccess;
	}
	
	public List<TransferResult>  messageToDataForComplex(String[] mappingIdArgs, String[] ruleIdArgs,String filePth) throws Exception{
		if(mappingIdArgs.length != ruleIdArgs.length)throw new Exception("校验ID的参数个数与规则ID的参数个数不一致，请检查输入！");
		List<TransferResult> reusltList = new ArrayList<TransferResult>();
		List<MappingEntity> entityList = new ArrayList<MappingEntity>();
		TransferResult reuslt;
		for(int index = 0; index < mappingIdArgs.length; index++){
			entityList = createMappingEntityList(mappingIdArgs[index],filePth,index);
			reuslt = new TransferResult();
			reuslt = getBackColumnValue(entityList,reuslt);
			reuslt.setErrorDataNumber(messageValidate.validateMessage(ruleIdArgs[index], entityList).getErrorList().size());
			reuslt.setWorkSuccess(databaseHandler.insertDataIntoDb(entityList));
			reusltList.add(reuslt);
		}
		return reusltList;
	}
	
	public List<TransferResult>  messageToDataForComplex(String[] mappingIdArgs, String[] ruleIdArgs,String filePth,Map<String,String> replaceDefaultMap) throws Exception{
		if(mappingIdArgs.length != ruleIdArgs.length)throw new Exception("校验ID的参数个数与规则ID的参数个数不一致，请检查输入！");
		List<TransferResult> reusltList = new ArrayList<TransferResult>();
		List<MappingEntity> entityList = new ArrayList<MappingEntity>();
		TransferResult reuslt = null;
		String  tab1 = "ln_acct_mid";
		String  tab2 = "ln_gage_mid";
		String  tab3 = "ln_rel_mid";
		
		for(int index = 0; index < mappingIdArgs.length; index++){
			
			entityList = createMappingEntityList(mappingIdArgs[index],filePth,index,replaceDefaultMap);
			if(entityList!=null&&entityList.size()!=0){
				ValidateLog log = messageValidate.validateMessage(ruleIdArgs[index], entityList);
				int errorNum = log.getErrorList().size();
				if(errorNum==0){
					String res = databaseHandler.insertDataIntoDbStr(entityList,mappingIdArgs[index]);
					if("true".equals(res)){
						reuslt = new TransferResult();
						reuslt.setWorkSuccess(true);
						reuslt = getBackColumnValue(entityList,reuslt);
						reuslt.setErrorDataNumber(errorNum);
					}else if("false".equals(res)){
						reuslt = new TransferResult();
						reuslt.setWorkSuccess(false);
						reuslt = getBackColumnValue(entityList,reuslt);
						reuslt.setErrorDataNumber(errorNum);
					}else if("repeat".equals(res)){
						reusltList.clear();
						reuslt = new TransferResult();
						reuslt.setErrorDataNumber(-99);
						reusltList.add(reuslt);
						break;
					}
					reusltList.add(reuslt);
				}else{
					reusltList.clear();
					reuslt = new TransferResult();
					reuslt.setErrorDataNumber(-88);
					reuslt.setTransferId(log.getErrorMessage());
					reusltList.add(reuslt);
					//数据插入过程中出错，把ln_acct_mid,ln_gage_mid,ln_rel_mid表中app_id为空的数据删除
					boolean flag1 = databaseHandler.del(tab1);
					boolean flag2 = databaseHandler.del(tab2);
					boolean flag3 = databaseHandler.del(tab3);
					if(flag1 && flag2 && flag3){
						System.out.println("删除成功");
					}
					
					break;
				}
			}
		}
		return reusltList;
	}
	
	private TransferResult getBackColumnValue(List<MappingEntity> entityList,TransferResult result){
		MappingEntity entity = entityList.get(0);
		result.setGetRebackColumn(entity.getReturnColumnName());
		for(MappingColumn column: entity.getColumnList()){
			if(column.getColumnName().equals(entity.getReturnColumnName())){
				result.setGetRebackValue(column.getDataValue());
				break;
			}
		}
		return result;
	}
	
	private List<MappingEntity> createMappingEntityList(String mappingId, String filePath)throws IOException{
		return createMappingEntityList(mappingId,filePath,null,null);	
	}
	
	private List<MappingEntity> createMappingEntityList(String mappingId, String filePath,Map<String,String> replaceValueMap)throws IOException{
		return createMappingEntityList(mappingId,filePath,null,replaceValueMap);	
	}
	
	private List<MappingEntity> createMappingEntityList(String mappingId, String filePath,Integer checkNumber)throws IOException{
		return  createMappingEntityList(mappingId,filePath,checkNumber,null);


	}
	
	/**
	 * checkNumber 实际为获取sheet的页码数
	 * @作者 DHCC-LIYABIN
	 * @日期 2016年7月29日
	 * @方法说明：
	 * @返回参数 List<MappingEntity>
	 */
	private List<MappingEntity> createMappingEntityList(String mappingId, String filePath,Integer checkNumber,Map<String,String> replaceValueMap)throws IOException{
		//获取报文数据以及转换数据
		List<List<String>> allMessageList = null;
		if(checkNumber == null){
			if(filePath ==null)allMessageList = messageHandler.readDataListFromMessage();
			else allMessageList = messageHandler.readDataListFromMessage(filePath);
		}else{
			if(filePath ==null)allMessageList = messageExcelHandler.readDataListFromMessage();
			else allMessageList = messageExcelHandler.readDataListFromMessage(filePath,checkNumber);
		}
		
		MappingEntity entity = mappingHandler.findMappingEntityById(mappingId);
		
		List<MappingColumn> columnList = null;
		List<MappingEntity> entityList = new ArrayList<MappingEntity>();
		
		//赋值翻译
		for(List<String> lineMessageList:allMessageList){
			if("true".equalsIgnoreCase(entity.getIgnoreAll())){
				boolean flag = false;
				for(int i=0,j=0;i<lineMessageList.size();i++){
					if(!"".equals(lineMessageList.get(i))&&lineMessageList.get(i)!=null){
						flag = true;
						break;
					}
				}
				if(!flag){
					continue;
				}
			}
			try {
				columnList = MessageUtil.deepCopy(entity.getColumnList());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			if(columnList.size() != lineMessageList.size()){
				System.out.println("元素个数："+columnList.size());
				System.out.println("信息个数："+lineMessageList.size());
				int coutSequenceTypeNumber = 0;
				int countIgnoreTypeNumber = 0;
				for(MappingColumn mc:columnList){
					if(mc.getDataType() == DataType.SEQUENCE) coutSequenceTypeNumber ++; 
					if(mc.getDataType() == DataType.IGNORE) countIgnoreTypeNumber ++;
				}
				if(columnList.size() == coutSequenceTypeNumber+ countIgnoreTypeNumber + lineMessageList.size()){
					System.out.println("警告：配置文件的字段个数：【"+columnList.size()+"】 包含 【" +coutSequenceTypeNumber+"】个序列字段以及【"+countIgnoreTypeNumber+"】个忽略字段，相减后与与报文数据的字段个数：【"+lineMessageList.size()+"】匹配,程序将继续进行，但可能存在风险操作！");
				}else{
					throw new IndexOutOfBoundsException("需要导入的字段个数：【"+(columnList.size()-coutSequenceTypeNumber-countIgnoreTypeNumber)+"】与实际导入的字段个数：【"+lineMessageList.size()+"】不匹配，转换失败");
				}
			}
			columnIterator: for(int i=0,j=0;i<columnList.size();i++){
				if(columnList.get(i).getDataType() == DataType.SEQUENCE){
					continue;
				}
				
				if(replaceValueMap!=null && !replaceValueMap.isEmpty()){
					for(String replaceName : replaceValueMap.keySet()){
						if(replaceName.equals(columnList.get(i).getColumnName())){
							columnList.get(i).setDataValue(replaceValueMap.get(replaceName));
							continue columnIterator;
						}
					}
				}
				if(columnList.get(i).getDataType() == DataType.IGNORE){
					if("GAGE_AMT".equals(columnList.get(i).getColumnName())&&"0011".equals(mappingId)){
						columnList.get(i).setDataValue(columnList.get(8).getDataValue());
					}
					if("GAGE_AMT".equals(columnList.get(i).getColumnName())&&"0007".equals(mappingId)){
						columnList.get(i).setDataValue(columnList.get(7).getDataValue());
					}
					if("APP_AMT".equals(columnList.get(i).getColumnName())){
						columnList.get(i).setDataValue(columnList.get(32).getDataValue());
					}
					continue;
				}
				if(columnList.get(i).getDataType() == DataType.DEFAULT){
					columnList.get(i).setDataValue(columnList.get(i).getDefaultValue());
				}else{
					if(columnList.get(i).getDataType() == DataType.NUMBER){
						//防止java读取数字自动转化为科学计数方式。如果字段类型为number时，将尝试把值转化为数值格式
						try {
							columnList.get(i).setDataValue(MessageUtil.transNumberCount(lineMessageList.get(j)));
						} catch (NumberFormatException e) {
							throw new NumberFormatException("数值转换过程中出现异常，转换前数字为："+lineMessageList.get(j));
						}
					}else{
						columnList.get(i).setDataValue(lineMessageList.get(j));
					}
					j++;
				}
			}
			entityList.add(new MappingEntity(entity.getTableName(),columnList,entity.getSequenceName(),entity.getReturnColumnName()));
		}
		return entityList;	
	}

	@Override
	public boolean dataToMessage(String mappingId, String sql) throws IOException {
		return dataToMessage(mappingId, sql, null);
	}

	@Override
	public boolean dataToMessage(String mappingId, String sql, String filePth) throws IOException {
		List<Map<String,String>> resultList = databaseHandler.exportDataFromDb(sql);
		return writeToMessage(mappingId,filePth,resultList,false);
	}
	
	@Override
	public boolean objectToMessage(String mappingId, List<?> objList) throws Exception {
		return objectToMessage(mappingId,objList,null);
	}
	
	@Override
	public boolean objectToMessage(String mappingId, List<?> objList,String filePth) throws Exception {
		List<Map<String,String>> resultList = databaseHandler.exportDataFromDb(objList);
		return writeToMessage(mappingId,filePth,resultList,true);
	}
	
	/**
	 * 
	 * @param mappingId
	 * @param filePth
	 * @param resultList
	 * @param isLower 是否忽略大小写（需要在生成查询数据resultList时，将所有的key值小写化）
	 * @return
	 * @throws IOException
	 */
	private boolean writeToMessage(String mappingId,String filePth,List<Map<String,String>> resultList,boolean isLower) throws IOException{
		List<String> lineList = new ArrayList<String>();
		List<List<String>> messageList = new ArrayList<List<String>>();
		MappingEntity entity = mappingHandler.findMappingEntityById(mappingId);
		for(Map<String,String> resultMap:resultList){
			lineList = new ArrayList<String>();
			for(MappingColumn mc : entity.getColumnList()){
				if(isLower)
					lineList.add(resultMap.get(mc.getColumnName().toLowerCase()));
				else
					lineList.add(resultMap.get(mc.getColumnName()));
			}
			messageList.add(lineList);
		}
		if(filePth==null)return messageHandler.writeToMessage(messageList,isAddContent);
		return messageHandler.writeToMessage(messageList,isAddContent, filePth);
	}
	

	public static void main(String[] args) throws IOException {
		String mappingConifg = "C:/work/otherSpace/eclipse2015/wmxtcms/src/main/java/app/util/message/conversion/test/mappingConfig.txt";
		String readFilePath = "C:/work/otherSpace/eclipse2015/wmxtcms/src/main/java/app/util/message/conversion/test/testData.txt";
		TransferHandlerImpl th = new TransferHandlerImpl(mappingConifg);
		th.refreshMappingConfig();
		th.messageToDataWithValidate("0001",readFilePath,"102");
	}

	public void setMessageHandler(MessageHandler messageHandler) {
		this.messageHandler = messageHandler;
	}

	public void setMappingHandler(MappingHandler mappingHandler) {
		this.mappingHandler = mappingHandler;
	}

	public void setDatabaseHandler(DataBaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}

	public void setMessageValidate(MessageValidate messageValidate) {
		this.messageValidate = messageValidate;
	}

	public MessageExcelHandler getMessageExcelHandler() {
		return messageExcelHandler;
	}

	public void setMessageExcelHandler(MessageExcelHandler messageExcelHandler) {
		this.messageExcelHandler = messageExcelHandler;
	}
	
}
