package com.multi.chatting;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class ChatController {
	
	@GetMapping("/chatForm")
	public String chatForm() {
		
		return "webChat/chatUI2";
	}//------------------------
	
	@MessageMapping("/chat")
	@SendTo("/topic/messages")
	public OutputMessageVO send(MessageVO vo) {
		log.info("������ ���� ���� => " + vo.toString());
		//System.out.println("������ ���� ���� => " + vo.toString());
		String time = new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(new Date());
		log.info("time : " + time);
		
		OutputMessageVO message = new OutputMessageVO(vo.getFrom(), vo.getTo(), vo.getText(),time);

		return message;
	}//--------------------------
	
	//ê�� ����========================
	@GetMapping("/chatbotForm")
	public String chatbotForm() {
		
		return "webChat/chatBot";
	}//==================================
	
	@MessageMapping("/chatbot")
	//@SendTo("/topic/messages2") //[x]������ ����ڿ��Ը� �������� topic�� �ƴ϶� queue�� �����ؾ� �ϰ� @SendToUser()�� ����ؾ� ��
	@SendToUser("/queue/messages") //������ ��� ������ Ŭ���Ը� �޽����� ������. (1:1)   �����ϴ� �������� '/user'���ξ �ٿ�����
	public ResponseMessage sendToUser(MessageVO vo) {
		log.info("**********************");
		log.info("������ ���� ���� => " + vo.toString());
		String time = new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(new Date());
		ResponseMessage res = new ResponseMessage(vo.getFrom(), vo.getText(), null, time);
		
		String menu = "";
		switch(vo.getText()) {
		case "1":
			menu = "ê �� >> 10)�ȭ   11)����   12)����";
			break;
		case "2":
			menu = "ê �� >> 20)�����ȸ   21)�ֹ���ǰ ��ȸ";
			break;
		case "10":
			menu = "ê �� >> 100)����Ű  200)Ǫ��   300)���ڽ���";
			break;
		case "100":
			menu = "ê �� >> 1000)����Ű �ȭ ���� ����   1)ó������ ���ư���";
			break;
		case "1000":
			menu = "ê �� >> 1001)����Ű ����ȭ(3����)   1002)����Ű �౸ȭ(12����)   100)���� �޴�";
			break;
		default:
			menu = "ê �� >> ������ ��ȣ�� �޴��� �����ϴ�. �ٽ� �Է��ϼ���.";
			break;
		}//switch
		res.setMenu(menu);
		
		return res;
	}
	
	
	
}
