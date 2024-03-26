package ex01;

import org.springframework.aop.framework.ProxyFactory;

public class AOPTest {

	public static void main(String[] args) {
		// 1. �ٽɷ����� ���� ��ü => target
		Service target = new ServiceImpl();
		System.out.println("terger : " + target);//ServiceImpl
		
		// 2. ������� ���� ��ü => Aspect(�߻�������) => Advice(���� ������ �ڵ�)
		AroundAdvice advice = new AroundAdvice();
		
		// 3. Proxy ��ü ��� => ProxyFactory���ؼ�
		ProxyFactory pf = new ProxyFactory();
		pf.setTarget(target);
		pf.addAdvice(advice); //�ּ�ó���ϸ� ȣ����,ȣ���� ���� ��¾ȵ�
		
		//4. Proxy���ؼ� �޼��� ȣ��
		ServiceImpl proxy = (ServiceImpl)pf.getProxy();
		System.out.println("proxy : " + proxy); //ServiceImpl
		proxy.sayHello("���","���̽Ľ�");
		

	}

}
