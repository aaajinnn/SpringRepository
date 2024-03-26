package ex02;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

//Advice : ���� ���ɻ���
public class CommonAspect {
	//JoinPoing : Target��ü�� ���� �޼���.
	public void trace1(JoinPoint jp) {
		Signature sign = jp.getSignature(); 
		System.out.println("==before===============[" + sign.toShortString() +"]");
		//�ٽɸ޼��� ȣ��Ǳ����� �����ֱ�
	}
	
	public void trace2(JoinPoint jp) {
		Signature sign = jp.getSignature(); 
		System.out.println("==after===============[" + sign.toShortString() +"]");
		//�ٽɸ޼��� ȣ��Ǳ����� �����ֱ�
	}
	
	public void trace3(JoinPoint jp, Integer result) {
		Signature sign = jp.getSignature(); 
		System.out.println("==after retunrint===============[" + sign.toShortString() +"] ��ȯ�� : " + result);
		//�ٽɸ޼��� ȣ��Ǳ����� �����ֱ�
	}
	
	public void trace4(JoinPoint jp, Exception ex) {
		Signature sign = jp.getSignature(); 
		System.out.println("==after throwing===============[" + sign.toShortString() +"] ==");
		if(ex!=null) {
			System.out.println("�߻��� ���� : " + ex);
		}
	}
	
	public Integer trace5(ProceedingJoinPoint jp) {
		Signature sign = jp.getSignature(); 
		System.out.println("==around(before)===============[" + sign.toShortString() +"] =start=");
		try {
			Integer n = (Integer)jp.proceed();
			return n;
		}catch(Throwable e) {
			e.printStackTrace();
			return null;
		}finally {
			System.out.println("==around(after)===============[" + sign.toShortString() +"] =end=");
		}
	}
	
}