package ex07;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ex07.Service;

public class HelloAppEx07 {
	
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		
		Service svc = ctx.getBean("service", Service.class);
		svc.info1();
		System.out.println("********************");
		svc.info2();
	}
}
