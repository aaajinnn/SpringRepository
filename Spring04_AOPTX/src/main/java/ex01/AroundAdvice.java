package ex01;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

//Advice : 주변 로직을 갖는 공통관심사항을 구현한 객체
public class AroundAdvice implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation invo) throws Throwable {
		System.out.println("I am AroundAdvice : " + invo.getMethod() + "호출 전=====");
		Object obj = invo.proceed(); //target의 서비스임플메서드호출
		System.out.println("I am AroundAdvice : " + invo.getMethod() + "호출 후=====");
		
		return obj;
	}

}
