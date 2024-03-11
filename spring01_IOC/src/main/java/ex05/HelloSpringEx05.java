package ex05;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpringEx05 {

	public static void main(String[] args) {
		// 여기서 Lookup
		
		String resource = "classpath:ex05/emp.xml"; // 컴파일돼서 build되는 classpath
		// classpath : D:\multicampus\Spring-workspace\spring01_IOC\target\classes
		
		// 스프링 컨테이너
		ApplicationContext ctx = new ClassPathXmlApplicationContext(resource);
		
		Emp e = ctx.getBean("e2", Emp.class);
		e.info1();
		System.out.println("--------------------");
		ctx.getBean("e3", Emp.class).info2();
		System.out.println("--------------------");
		ctx.getBean("e4", Emp.class).info3();
	}

}
