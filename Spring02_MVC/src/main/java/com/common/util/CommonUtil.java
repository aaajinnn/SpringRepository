package com.common.util;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component //최상위 어노테이션(컨트롤러도 아니고 서비스도 아니지만 이 클래스를 메모리에 넣어서 쓰기 위함)
public class CommonUtil {

	public String addMsgLoc(Model m, String msg, String loc) {
		m.addAttribute("msg", msg);
		m.addAttribute("loc", loc);
		return "message";
	}
	
	public String addMsgBack(Model m, String msg) {
		m.addAttribute("msg", msg);
		m.addAttribute("loc", "javascript:history.back()");
		return "message";
	}
	
	public String addMsgPopup(Model m, String msg) {
		m.addAttribute("msg", msg);
		m.addAttribute("mode", "popup");
		return "message";
	}
}
