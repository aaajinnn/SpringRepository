package com.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
//@EnableAspectJAutoProxy //�� ������̼��� �ٿ������
public class LoggingAspect {

	@Pointcut("execution(* com.shop..*Service.*(..))") //��ȯ���� �����ǵ� ������� ��� ������ ���޼��忡 �����ϰڴ�
	private void loggingTarget() {
		// ���� : �޼���� void������ ==> @Pointcut�� �̿��Ҷ�
	}
	
	//�����̽��� ����� �޼���
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
		System.out.println(str + "����================");
		long start = System.currentTimeMillis();
		try {
			Object result = jpoint.proceed(); //target�� �޼��带 �θ�
			return result;
		}finally {
			long end = System.currentTimeMillis();
			System.out.println(str + "����================");
			System.out.println(str + "����ð� : " + (end-start) + "ms");
		}
		
	}
}