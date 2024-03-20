package com.common.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.user.domain.MemberVO;

public class AdminCheckInterceptor extends HandlerInterceptorAdapter{ // implements HandlerInterceptor{

//관리자 페이지에는 관리자만 이용할 수 있도록 servlet-context.xml에 mapping하세요
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession ses = request.getSession();
		MemberVO user = (MemberVO)ses.getAttribute("loginUser"); // 로그인 시 키값 얻어옴
		if(user.getMstate() != 9) {
			request.setAttribute("msg", "관리자만 접근할 수 있습니다.");
			String loc = request.getContextPath() + "/index";
			request.setAttribute("loc", loc);
			
			RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/views/message.jsp");
			disp.forward(request, response);
			
			return false;
		}
		return true;
	}
	
}
