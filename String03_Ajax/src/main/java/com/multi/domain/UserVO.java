package com.multi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //기본생성자만 제공
@AllArgsConstructor // 인자생성자 제공 => 이렇게할경우는 기본생성자 사라짐
@NoArgsConstructor // 기본생성자
public class UserVO {

	private int no;
	private String name;
	private String addr;
	private String phone;
	
}
