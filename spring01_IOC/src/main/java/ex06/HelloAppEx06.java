package ex06;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloAppEx06 {

	public static void main(String[] args) {
		//FileSystemXmlApplicationContext, ClassPathXmlApplicationContext ==> xml설정시
		//AnnotationConfigApplicationContext ==> 자바로 설정시
		ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
		
		Emp e1 = ctx.getBean("e1", Emp.class);
		System.out.println(e1.getName() + "/" + e1.getDept() + "/" + e1.getSal());
		
		Emp e2 = ctx.getBean("empInfo2", Emp.class);
		System.out.println(e2.getName() + "/" + e2.getDept() + "/" + e2.getSal());
		System.out.println("************************");
		
		//service빈 룩업해서 info호출해보기
		Service service = ctx.getBean("service", Service.class);
		service.info();
		
	}

}
