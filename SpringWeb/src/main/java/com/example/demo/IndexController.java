package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//POJO (Plain Old Java Object)방식 지향 : 특정 api에 의존하지 않고 순수 자바 객체로도 기능을 수행하게끔 하는 방식
@Controller
public class IndexController {
	
	//execute할필요 없음 대신 뷰네임을 받아 mapping
	@RequestMapping("/index.do")
	public String index(Model model) {
		System.out.println("index()메서드 들어옴");
		//request에 저장해도 되지만 spring에서는 Model, ModelMap, ModelAndView에 저장
		model.addAttribute("msg", "반가워요 스프링~~");
		
		return "index"; // 뷰네임을 반환(default : forward이동방식)
		//application.properties에 접두어, 접미사 지정
		// /WEB-INF/views/index.jsp
	}
}
