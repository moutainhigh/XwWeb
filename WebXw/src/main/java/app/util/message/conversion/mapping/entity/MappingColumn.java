package app.util.message.conversion.mapping.entity;

import java.io.Serializable;

import app.util.message.conversion.type.DataType;

public class MappingColumn implements Serializable{
	private static final long serialVersionUID = 2110406820176053346L;
	private String columnName;
	private DataType dataType;
	private String dataValue;
	private String defaultValue;
	
	public MappingColumn(String columnName, DataType dataType) {
		super();
		this.columnName = columnName;
		this.dataType = dataType;
	}
	
	public MappingColumn(String columnName, DataType dataType, String dataValue) {
		super();
		this.columnName = columnName;
		this.dataType = dataType;
		this.dataValue = dataValue;
	}
	
	public String getDataValue() {
		return dataValue;
	}
	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}

	public MappingColumn() {
		super();
	}

	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public DataType getDataType() {
		return dataType;
	}
	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}
	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	@Override
	public String toString() {
		return "字段名为"+this.columnName + "，字段类型为"+this.dataType ;
	}
	
}
