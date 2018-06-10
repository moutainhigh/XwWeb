package app.util.message.conversion.mapping.entity;

import java.util.List;

public class MappingEntity {
	private String tableName;
	private List<MappingColumn> columnList;
	private String sequenceName;
	private String returnColumnName;
	private String ignoreAll;
	public String getIgnoreAll() {
		return ignoreAll;
	}
	public void setIgnoreAll(String ignoreAll) {
		this.ignoreAll = ignoreAll;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public List<MappingColumn> getColumnList() {
		return columnList;
	}
	public void setColumnList(List<MappingColumn> columnList) {
		this.columnList = columnList;
	}
	
	public String getSequenceName() {
		return sequenceName;
	}
	public void setSequenceName(String sequenceName) {
		this.sequenceName = sequenceName;
	}
	public String getReturnColumnName() {
		return returnColumnName;
	}
	public void setReturnColumnName(String returnColumnName) {
		this.returnColumnName = returnColumnName;
	}
	public MappingEntity(String tableName, List<MappingColumn> columnList) {
		super();
		this.tableName = tableName;
		this.columnList = columnList;
	}
	
	public MappingEntity(String tableName, List<MappingColumn> columnList,
			String sequenceName, String returnColumnName) {
		super();
		this.tableName = tableName;
		this.columnList = columnList;
		this.sequenceName = sequenceName;
		this.returnColumnName = returnColumnName;
	}
	@Override
	public String toString() {
		String result = "MappingEntity ：tableName=" + tableName + ", columnList=";
		for(MappingColumn entity:getColumnList()){
			result += entity.toString();
		}
		if(getSequenceName()!=null)result += " 存在属性：序列表，表名是"+getSequenceName();
		if(getReturnColumnName()!=null)result += " 要求返回字段名："+getReturnColumnName();
		return result;
	}
	
}
