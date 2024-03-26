package ex01;
//Target : 핵심 로직을 갖는 객체
public class ServiceImpl implements Service {

	// 핵심 로직을 수행하는 메서드
	@Override
	public void sayHello(String... names) {
		
		if(names!=null) {
			for(String name:names) {
				System.out.println("Hello! " + name);
			}
		}

	}

}
