package ex07;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
/* @Value :�⺻�ڷ���, String ������ ������ �� ���
 * @Autowired : byType���� �����Ѵ�. �ڷ������� ���� ��ü�� ������ �����Ѵ�. (@Bean���� ��ϵ�)
 * 				ã�¼���: �ڷ���> @Qualifier("���̸�")
 * @Resource  : byName���� �����Ѵ�. pom.xml�� ���̺귯���� ����ؾ� ��� �����ϴ�.
 * @Inject : ==>pom.xml�� ���̺귯���� ����ؾ� ��� �����ϴ�. byType> @Qualifier
 * 
 * */
public class ServiceImpl implements Service {

	// ������Ÿ�Կ� EmpŸ���� ��ü�� �ִٸ� ����� ����. byType���� ����
	//@Autowired
	@Inject 
	@Qualifier("emp2") // �̷��� �ΰ����� ������ �־�� ������ ���� �����.
	private Emp emp;
	
	// Bean�� �̸��� member�� ��ü�� ������ �����Ѵ�.
	@Resource(name="member") //pom.xml���� Add My Module �κп��� ����ؾ� ��밡��
	private Member user;
	// ��ó�� @Inject, @Qualifier("emp2") �ΰ��� �ϴ��� @Resource(name="member")�� �ϳ��� ���°� ����
	
	@Override
	public void info1() {
		System.out.println("Name : "+ emp.getName());
		System.out.println("Dept : " + emp.getDept());
		System.out.println("Salary : " + emp.getSal()); 
		//=> null ==>appConfig���� �־��ָ�� ==> ������̼� �̿�
		
	}

	@Override
	public void info2() {
		user.showInfo();
		
	}

}
