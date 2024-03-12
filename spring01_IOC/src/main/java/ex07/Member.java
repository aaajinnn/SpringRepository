package ex07;

import org.springframework.beans.factory.annotation.Value;

public class Member {
	
	@Value("hong") //������̼��� �̿��� ���� => Field Injection
	private String userId;
	@Value("010-1234-1234")
	private String tel;
	
	public void showInfo() {
		System.out.println("UserId : " + userId);
		System.out.println("Tel : "+ tel);
	}
}
