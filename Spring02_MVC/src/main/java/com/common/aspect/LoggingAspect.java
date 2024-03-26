package com.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
//@EnableAspectJAutoProxy //이 어노테이션을 붙여줘야함
public class LoggingAspect {

	@Pointcut("execution(* com.shop..*Service.*(..))") //반환값은 뭐가되든 상관없고 모든 서비스의 모든메서드에 적용하겠다
	private void loggingTarget() {
		// 주의 : 메서드는 void여야함 ==> @Pointcut을 이용할때
	}
	
	//어드바이스가 저장될 메서드
	@Around("loggingTarget()")
	public Object trace(ProceedingJoinPoint jpoint) throws Throwable{
		Object[] arr = jpoint.getArgs();
		if(arr!=null) {
			for(Object args:arr) {
				System.out.println(args + ",");
			}
			System.out.println();
		}
		String str = jpoint.getSignature().toLongString();
		System.out.println(str + "시작================");
		long start = System.currentTimeMillis();
		try {
			Object result = jpoint.proceed(); //target의 메서드를 부름
			return result;
		}finally {
			long end = System.currentTimeMillis();
			System.out.println(str + "종료================");
			System.out.println(str + "실행시간 : " + (end-start) + "ms");
		}
		
	}
}
