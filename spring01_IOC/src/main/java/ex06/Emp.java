package ex06;

public class Emp {

	private String name;
	private String dept;
	private int sal;
	
	//기본생성자
	private Emp() {
		
	}
	
	//인자생성자
	public Emp(String name, String dept, int sal) {
		this.name = name;
		this.dept = dept;
		this.sal = sal;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public int getSal() {
		return sal;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}
	
}
