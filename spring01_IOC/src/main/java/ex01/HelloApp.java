package ex01;

public class HelloApp {

	public static void main(String[] args) {
		/* HelloApp�� MessageBeanKo��ü�� ���(use)�Ѵ�.
		 * ==> HelloApp�� MessageBeanKo �� �����Ѵ�(dependency) 
		 * 		(HelloApp�� MessageBeanKoŬ������ ������ ����� �� ����)
		 * 	: �̶� ������ �ִ� ��ü���� ���յ��� �߿��ϴ�.
		 * 	  ���յ��� ���ϸ� ���� ��ü�� ��ȯ�ϰ��� �� �� ������ �� �� �ִ�.
		 * 
		 * ==> �������ִ� ��ü�鳢���� ���յ��� ������ ���� ����.
		 * */
		//MessageBeanKo mb = new MessageBeanKo();
		MessageBeanEn mb = new MessageBeanEn();
		mb.sayHello("ȫ�浿");

	}

}
