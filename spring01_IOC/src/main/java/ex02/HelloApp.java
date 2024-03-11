package ex02;

public class HelloApp {

	public static void main(String[] args) {
		// 인터페이스 타입 변수 = 자식 객체();
		// 타입선언은 바꿀필요없이 자식객체만 변경하면 된다.
		MessageBean mb = new MessageBeanEn(); //부모타입 선언, 자식객체 생성
		mb.sayHello("Peter");
		
		// ==> 객체간의 결합력이 다소 느슨해짐. 
		// HelloApp과 MessageBean 구현 객체간의 의존성이 약함

	}

}
