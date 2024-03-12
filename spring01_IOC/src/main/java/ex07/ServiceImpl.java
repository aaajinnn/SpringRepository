package ex07;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
/* @Value :기본자료형, String 유형을 주입할 때 사용
 * @Autowired : byType으로 주입한다. 자료유형이 같은 객체가 있으면 주입한다. (@Bean으로 등록된)
 * 				찾는순서: 자료형> @Qualifier("빈이름")
 * @Resource  : byName으로 주입한다. pom.xml에 라이브러리를 등록해야 사용 가능하다.
 * @Inject : ==>pom.xml에 라이브러리를 등록해야 사용 가능하다. byType> @Qualifier
 * 
 * */
public class ServiceImpl implements Service {

	// 스프링타입에 Emp타입의 객체가 있다면 여기로 주입. byType으로 주입
	//@Autowired
	@Inject 
	@Qualifier("emp2") // 이렇게 부가적인 정보를 주어야 오류가 나지 안흔다.
	private Emp emp;
	
	// Bean의 이름이 member인 객체가 있으면 주입한다.
	@Resource(name="member") //pom.xml에서 Add My Module 부분에서 등록해야 사용가능
	private Member user;
	// 위처럼 @Inject, @Qualifier("emp2") 두개를 하느니 @Resource(name="member")로 하나로 쓰는게 편함
	
	@Override
	public void info1() {
		System.out.println("Name : "+ emp.getName());
		System.out.println("Dept : " + emp.getDept());
		System.out.println("Salary : " + emp.getSal()); 
		//=> null ==>appConfig에서 넣어주면됨 ==> 어노테이션 이용
		
	}

	@Override
	public void info2() {
		user.showInfo();
		
	}

}
