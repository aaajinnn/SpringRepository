package ex03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class HelloApp {

	public static void main(String[] args) {
		//스프링 컨테이너를 통해 필요한 객체를 DL(Dependency Lookup)해보자
		String resource = "src/main/java/ex03/applicationContext.xml"; //  context파일이 있는 경로
		
		//스프링 컨테이너 생성
		ApplicationContext ctx = new FileSystemXmlApplicationContext(resource); //스프링 컨테이너
		
		//의존성있는 필요한 객체를 찾는다.
		// 변수는 인터페이스 타입으로, ctx로 룩업 (반환타입 오브젝이므로 강제형변환)
		MessageBean mb = (MessageBean) ctx.getBean("mbEn"); // Lookup
		mb.sayHello("김지수");
	}

}
