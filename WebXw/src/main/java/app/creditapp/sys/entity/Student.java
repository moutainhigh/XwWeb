package app.creditapp.sys.entity;

import app.base.BaseDomain;

public class Student extends BaseDomain{
	
	private String stu_Id;
	private String stu_Name;
	private String stu_Age;
	private String stu_Sex;
	private String stu_Phone;
	public String getStu_Id() {
		return stu_Id;
	}
	public void setStu_Id(String stu_Id) {
		this.stu_Id = stu_Id;
	}
	public String getStu_Name() {
		return stu_Name;
	}
	public void setStu_Name(String stu_Name) {
		this.stu_Name = stu_Name;
	}
	public String getStu_Age() {
		return stu_Age;
	}
	public void setStu_Age(String stu_Age) {
		this.stu_Age = stu_Age;
	}
	public String getStu_Sex() {
		return stu_Sex;
	}
	public void setStu_Sex(String stu_Sex) {
		this.stu_Sex = stu_Sex;
	}
	public String getStu_Phone() {
		return stu_Phone;
	}
	public void setStu_Phone(String stu_Phone) {
		this.stu_Phone = stu_Phone;
	}
	

}
