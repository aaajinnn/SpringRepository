package ex06;

public class ServiceImpl implements Service {

	//property
	private Emp emp; // Service�� Emp�� ����������
	
	public void setEmp(Emp emp) {
		this.emp = emp;
	}
	
	@Override
	public void info() {
		System.out.printf("Name : %s\n Dept: %s\n Salary : %d\n", 
					emp.getName(), emp.getDept(), emp.getSal());

	}

}
