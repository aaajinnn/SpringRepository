package ex02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPTestEx02 {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("ex02/aop.xml");
		BoardService bs = ctx.getBean("boardService", BoardService.class);
		
		bs.insertBoard("첫번째 글을 등록합니다.");
		int a = 10/0; //ArithemticException
		bs.insertBoard("두번째 글도 썼어요.");
	}

}
