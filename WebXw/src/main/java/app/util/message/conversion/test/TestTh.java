package app.util.message.conversion.test;

public class TestTh {
	private String id;
	private String idNo;
	private String name;
	private Integer age;
	private Double salary;
	
	public TestTh() {
		super();
	}
	public TestTh(String id, String idNo, String name, Integer age,
			Double salary) {
		super();
		this.id = id;
		this.idNo = idNo;
		this.name = name;
		this.age = age;
		this.salary = salary;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
}
