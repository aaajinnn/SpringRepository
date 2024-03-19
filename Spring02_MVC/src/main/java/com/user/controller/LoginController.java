package com.user.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.common.exception.NotUserException;
import com.common.util.CommonUtil;
import com.user.domain.MemberVO;
import com.user.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequiredArgsConstructor //==>생성자 주입
public class LoginController {
	
	private final MemberService mService;
	private final CommonUtil util;
	
	@GetMapping("/login")
	public String loginForm() {
		
		return "member/login";
	}
	
	@PostMapping("/login")
	public String loginEnd(Model m, HttpSession session, MemberVO tmpUser, 
			boolean saveId, HttpServletResponse response) 
		throws NotUserException
	{
		log.info("tmpUser : " + tmpUser);
		log.info("saveId : " + saveId); //아이디 저장 체크박스 값(체크했을시 true, 안했을시 false)
		
		if(tmpUser.getUserid()==null || tmpUser.getPwd() == null || 
				tmpUser.getUserid().trim().isBlank() || tmpUser.getPwd().trim().isBlank()) {
			return util.addMsgBack(m, "아이디, 비밀번호를 모두 입력하세요.");
		}
		
		MemberVO loginUser = mService.loginCheck(tmpUser);
		//아이디, 비번이 일치하지 않으면 NotUesException발생 => exception발생하지않으면 null, 아이디비번 제대로 일치
		if(loginUser!=null) { //일치한다면
			session.setAttribute("loginUser", loginUser);
			//쿠키생성
			Cookie ck = new Cookie("uid", loginUser.getUserid()); //key,value
			if(saveId) { //아이디 저장에 체크했다면
				ck.setMaxAge(7*24*60*60);
			}else {//체크 하지않았다면
				ck.setMaxAge(0);
			}
			ck.setPath("/");
			//응답 객체에 쿠키 끼워넣기
			response.addCookie(ck);
		}//if------
		return "redirect:index";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// 세션변수 모두 무효화
		session.invalidate();
		
		return "redirect:index";
	}
}
