package com.multi.chatting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// 서버가 클에게 보낼 정보를 저장하는 VO
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutputMessageVO {
	
	private String from; //보내는 이
	private String to; //받는 이
	private String text; //대화내용
	private String time; //보내는 시간
}
