package ex06;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration //���� configŬ������ ȯ�漳������ ����ϰڴٴ� �ǹ�
public class Config {
	// �������� �⺻������ ��(��ü)�� ���ϰ�ü(singleton)���� �����ؼ� �����Ѵ�.
	// �Ź� �ٸ� ��ü�� �����ϰ� �ʹٸ� @Scope �� => prototype���� ����
	
	
	// ��ȯ�ϴ¾ָ� Bean���� ���ڴٴ� ������̼�
	@Bean(name="e1") //<bean id="e1" class="ex06.Emp">�� ����
	//@Scope(value = "singleton") // �Ź� ���� ��ü ����
	@Scope(value = "prototype") // �Ź� �ٸ� ��ü ����(default���� singleton)
	public Emp empInfo() {
		return new Emp("Ward", "Sales", 2800);
	}
	
	//@Bean�� name�Ӽ��� ���� ������ �޼��� �̸�(empInfo2)�� ���� �̸��� �ȴ�.
	@Bean //<bean id="empInfo2" class="ex06.Emp">�� ����
	public Emp empInfo2() {
		Emp e = this.empInfo();
		e.setName("Scott");
		e.setDept("Research");
		e.setSal(3000);
		return e;
	}
	
//	@Scope(value = "prototype")
	@Bean
	public ServiceImpl service() {
		ServiceImpl svc = new ServiceImpl();
		svc.setEmp(this.empInfo());
		return svc;
	}
	
	
}