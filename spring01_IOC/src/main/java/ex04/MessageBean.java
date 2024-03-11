package ex04;

public interface MessageBean {
	
	void sayHello();
	void sayHi(String ...names);//... => 들어오는 만큼 받겠다 => names는 String[] 배열 타입
}
