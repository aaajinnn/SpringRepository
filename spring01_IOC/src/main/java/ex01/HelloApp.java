package ex01;

public class HelloApp {

	public static void main(String[] args) {
		/* HelloApp이 MessageBeanKo객체를 사용(use)한다.
		 * ==> HelloApp이 MessageBeanKo 에 의존한다(dependency) 
		 * 		(HelloApp은 MessageBeanKo클래스가 없으면 실행될 수 없음)
		 * 	: 이때 의존성 있는 객체들의 결합도가 중요하다.
		 * 	  결합도가 강하면 향후 객체를 교환하고자 할 때 문제가 될 수 있다.
		 * 
		 * ==> 의존성있는 객체들끼리는 결합도가 느슨한 것이 좋다.
		 * */
		//MessageBeanKo mb = new MessageBeanKo();
		MessageBeanEn mb = new MessageBeanEn();
		mb.sayHello("홍길동");

	}

}
