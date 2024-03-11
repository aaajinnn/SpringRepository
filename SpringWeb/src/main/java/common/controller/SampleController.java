package common.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import common.domain.SampleVO;
import common.mapper.SampleMapper;

@Controller
public class SampleController {
	
	@Autowired
	private SampleMapper sampleMapper;
	//SampleMapper를 구현한 객체 => 스프링이 알아서 함 
	//(우리는 interface이름만 알면됨 대신 어노테이션을 붙여서 객체를 자동으로 주입해 사용함)
	// => AutoWired 어노테이션을 붙이면 스프링이 알아서 해당 객체를 주입한다 ==> DI(Dependency Injection)
	@RequestMapping("/sample.do")
	public String testMyBatis(Model model) {
		System.out.println("sampleMapper : " + sampleMapper);
		
		int tableCount = sampleMapper.testCnt();
		
		model.addAttribute("msg", "scott계정의 테이블 수를 알아봅시다.");
		model.addAttribute("tableCount", tableCount);
		
		return "sample";
	}//-------------------------------
	
	@RequestMapping("/tableInfo.do")
	public String getTableInfo(Model model) {
		
		List<SampleVO> arr = sampleMapper.getTableInfo();
		
		model.addAttribute("tabList", arr);
		
		return "tabInfo";
	}
}
