package com.memo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.memo.model.MemoVO;
import com.memo.service.memoService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j // log객체 사용 가능
public class MemoController {

	@Autowired
	private memoService memoService;
	
	@RequestMapping(value="/memo", method=RequestMethod.GET) // url지정
	public String memoForm() {
		
		return "memo/input"; //View name
		//servlet-context.xml에 등록된 InternalResourceViewResolver빈이
		//뷰네임 앞에 접두어("/WEB-INF/views/")와 접미어(.jsp)를 붙여준다.
		//WEB-INF/views/memo/input.jsp
	}
	
	@RequestMapping(value="/memo", method=RequestMethod.POST)
	public String memoInsert(Model model, MemoVO vo) { // MemoVO : html에 입력한 값을 여기에 다 넣어줌
		
		if(vo.getName()==null || vo.getName().trim().isBlank()) { //입력값이 공백일경우 유효성검사
			
			return "redirect:memo";//redirect방식으로 이동
		}

		
		int n = this.memoService.insertMemo(vo);
		
		
		String msg=(n>0)?"글 등록 성공":"글 등록 실패";
		String loc=(n>0)?"memoList":"javascript:history.back()";
		
		model.addAttribute("msg", msg);
		model.addAttribute("loc",loc);
		
		return "message";
	}//----------------------------------------
	
	@RequestMapping(value="/memoList",method=RequestMethod.GET)
	public String memoList(Model model) {
		//서비스 listMemo()호출, 반환값 model에 저장
		List<MemoVO> memo = this.memoService.listMemo();
		
		model.addAttribute("memoList", memo);
		log.info("memo 출력됨" + memo);
		return "memo/list";
	}
}
