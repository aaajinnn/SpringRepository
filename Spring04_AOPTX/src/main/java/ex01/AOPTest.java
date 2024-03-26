package ex01;

import org.springframework.aop.framework.ProxyFactory;

public class AOPTest {

	public static void main(String[] args) {
		// 1. 핵심로직을 갖는 객체 => target
		Service target = new ServiceImpl();
		System.out.println("terger : " + target);//ServiceImpl
		
		// 2. 공통관심 사항 객체 => Aspect(추상적개념) => Advice(실제 구현한 코드)
		AroundAdvice advice = new AroundAdvice();
		
		// 3. Proxy 객체 얻기 => ProxyFactory통해서
		ProxyFactory pf = new ProxyFactory();
		pf.setTarget(target);
		pf.addAdvice(advice); //주석처리하면 호출전,호출후 문구 출력안됨
		
		//4. Proxy통해서 메서드 호출
		ServiceImpl proxy = (ServiceImpl)pf.getProxy();
		System.out.println("proxy : " + proxy); //ServiceImpl
		proxy.sayHello("루시","데이식스");
		

	}

}
