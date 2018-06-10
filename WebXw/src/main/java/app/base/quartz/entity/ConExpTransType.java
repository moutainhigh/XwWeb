package app.base.quartz.entity;

public enum ConExpTransType {
	BY_HOUR("1"),BY_DAY("2"),BY_WEEK("3"),BY_MONTH("4"),BY_YEAR("5"),BY_TIMES("0");
	
	private String value;
	
	private ConExpTransType(String value){
		this.value = value;
	}
	
	public String getValue(){
		return this.value;
	}
	
	public static ConExpTransType getTypeByValue(String value){
		for(ConExpTransType type:ConExpTransType.values()){
			if(type.getValue().equals(value))
				return type;
		}
		return null;
	}
	
}
