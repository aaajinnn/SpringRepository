package ex04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class HelloSpringApp {

	public static void main(String[] args) {
		// ���⼭ Lokkup�ؼ� ã�ƾ�����
		
		String resource = "src/main/java/ex04/appContext.xml";
		ApplicationContext ctx = new FileSystemXmlApplicationContext(resource);
		
		//mb1�� ã�Ƽ� sayHello()ȣ���ϱ�
		MessageBean mb1 = ctx.getBean("mb3", MessageBean.class); // MessageBean.class : MessageBeanŸ������ ����ȯ
		mb1.sayHello(); // setter�� �־����� �ʾұ⶧���� null�� ���
		mb1.sayHi("�ֻ�","������","�ű���","�ſ���");
		
		//mb2�� ã�Ƽ� sayHello()ȣ���ϱ�


	}

}