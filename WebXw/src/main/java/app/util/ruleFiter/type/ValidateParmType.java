package app.util.ruleFiter.type;

public enum ValidateParmType {
	isNull("null"),maxLength("maxLength"),minLength("minLength"),
	maxValue("maxValue"),minValue("minValue"),startStr("startStr"),
	endStr("endStr"),containStr("containStr"),onlyNumber("onlyNumber"),
	regexpType("regexpType");
	
	private String typeName;
	private ValidateParmType(String typeName){
		this.typeName = typeName;
	}
	
	public String getName(){
		return this.typeName;
	}
	
	public static ValidateParmType returnTypeByName(String inputName){
		for(ValidateParmType type:values()){
			if(type.getName().equals(inputName))return type;
		}
		return null;
	}
}
