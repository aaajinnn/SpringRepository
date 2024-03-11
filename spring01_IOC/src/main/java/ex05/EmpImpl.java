package ex05;

import java.util.Date;

public class EmpImpl implements Emp {
	
	// property
	private String name;
	private int sal;
	private Date hiredate;
	
	public EmpImpl(String name, int sal, Date hdate) {
		this.name = name;
		this.sal = sal;
		this.hiredate = hdate;
	}
	
	public EmpImpl(String name, int sal) {
		this(name, sal, null);
	}
	
	public EmpImpl(String name) {
		this(name, 0, null);
	}

	@Override
	public void info1() {
		System.out.println("name : " + name);
	}

	@Override
	public void info2() {
		this.info1();
		System.out.println("salary : " + sal);

	}

	@Override
	public void info3() {
		this.info2();
		System.out.println("hiredate : " + hiredate);

	}

}
