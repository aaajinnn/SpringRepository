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
@Log4j // log��ü ��� ����
public class MemoController {

	@Autowired
	private memoService memoService;
	
	@RequestMapping(value="/memo", method=RequestMethod.GET) // url����
	public String memoForm() {
		
		return "memo/input"; //View name
		//servlet-context.xml�� ��ϵ� InternalResourceViewResolver����
		//����� �տ� ���ξ�("/WEB-INF/views/")�� ���̾�(.jsp)�� �ٿ��ش�.
		//WEB-INF/views/memo/input.jsp
	}
	
	@RequestMapping(value="/memo", method=RequestMethod.POST)
	public String memoInsert(Model model, MemoVO vo) { // MemoVO : html�� �Է��� ���� ���⿡ �� �־���
		
		if(vo.getName()==null || vo.getName().trim().isBlank()) { //�Է°��� �����ϰ�� ��ȿ���˻�
			
			return "redirect:memo";//redirect������� �̵�
		}

		
		int n = this.memoService.insertMemo(vo);
		
		
		String msg=(n>0)?"�� ��� ����":"�� ��� ����";
		String loc=(n>0)?"memoList":"javascript:history.back()";
		
		model.addAttribute("msg", msg);
		model.addAttribute("loc",loc);
		
		return "message";
	}//----------------------------------------
	
	@RequestMapping(value="/memoList",method=RequestMethod.GET)
	public String memoList(Model model) {
		//���� listMemo()ȣ��, ��ȯ�� model�� ����
		List<MemoVO> memo = this.memoService.listMemo();
		
		model.addAttribute("memoList", memo);
		log.info("memo ��µ�" + memo);
		return "memo/list";
	}
}
