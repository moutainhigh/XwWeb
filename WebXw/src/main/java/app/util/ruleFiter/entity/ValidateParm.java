package app.util.ruleFiter.entity;

import java.util.ArrayList;
import java.util.List;

import app.util.ruleFiter.type.RegexpType;
import app.util.ruleFiter.type.ValidateParmType;

public class ValidateParm {
	private boolean canNull;
	private Integer maxLength;
	private Integer minLength;
	private Integer maxValue;
	private Integer minValue;
	private String startStr;
	private String endStr;
	private String containStr;
	private boolean onlyNumber;
	private String complexNumber;
	private RegexpType regexpType;
	
	private List<ValidateParmType> warningTypeList;
	
	public boolean isCanNull() {
		return canNull;
	}
	public void setCanNull(boolean canNull) {
		this.canNull = canNull;
	}
	public Integer getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}
	public Integer getMinLength() {
		return minLength;
	}
	public void setMinLength(Integer minLength) {
		this.minLength = minLength;
	}
	public Integer getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(Integer maxValue) {
		this.maxValue = maxValue;
	}
	public Integer getMinValue() {
		return minValue;
	}
	public void setMinValue(Integer minValue) {
		this.minValue = minValue;
	}
	public String getStartStr() {
		return startStr;
	}
	public void setStartStr(String startStr) {
		this.startStr = startStr;
	}
	public String getEndStr() {
		return endStr;
	}
	public void setEndStr(String endStr) {
		this.endStr = endStr;
	}
	public String getContainStr() {
		return containStr;
	}
	public void setContainStr(String containStr) {
		this.containStr = containStr;
	}
	public boolean isOnlyNumber() {
		return onlyNumber;
	}
	public void setOnlyNumber(boolean onlyNumber) {
		this.onlyNumber = onlyNumber;
	}
	public RegexpType getRegexpType() {
		return regexpType;
	}
	public void setRegexpType(RegexpType regexpType) {
		this.regexpType = regexpType;
	}
	
	private ValidateParm(){
		
	}
	
	public List<ValidateParmType> getWarningTypeList() {
		return warningTypeList;
	}
	public void setWarningTypeList(List<ValidateParmType> warningTypeList) {
		this.warningTypeList = warningTypeList;
	}
	
	public String getComplexNumber() {
		return complexNumber;
	}
	public void setComplexNumber(String complexNumber) {
		this.complexNumber = complexNumber;
	}
	public void setWarningType(ValidateParmType ... parmTypes ) {
		if(parmTypes!=null&& parmTypes.length>0){
			List<ValidateParmType> typeList = new ArrayList<ValidateParmType>();
			for(ValidateParmType type:parmTypes){
				typeList.add(type);
			}
			this.setWarningTypeList(typeList);
		}
	}
	
	public static ValidateParm initValidateParm(){
		ValidateParm parm = new ValidateParm();
		parm.setCanNull(true);
		parm.setRegexpType(null);
		parm.setOnlyNumber(false);
		parm.setWarningTypeList(null);
		return parm;
	}
}
