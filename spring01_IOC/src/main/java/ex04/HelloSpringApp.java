package ex04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class HelloSpringApp {

	public static void main(String[] args) {
		// 여기서 Lokkup해서 찾아쓸것임
		
		String resource = "src/main/java/ex04/appContext.xml";
		ApplicationContext ctx = new FileSystemXmlApplicationContext(resource);
		
		//mb1을 찾아서 sayHello()호출하기
		MessageBean mb1 = ctx.getBean("mb3", MessageBean.class); // MessageBean.class : MessageBean타입으로 형변환
		mb1.sayHello(); // setter에 넣어주지 않았기때문에 null값 출력
		mb1.sayHi("최상엽","조원상","신광일","신예찬");
		
		//mb2를 찾아서 sayHello()호출하기


	}

}
