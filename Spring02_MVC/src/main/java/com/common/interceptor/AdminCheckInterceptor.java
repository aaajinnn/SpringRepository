package com.common.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.user.domain.MemberVO;

public class AdminCheckInterceptor extends HandlerInterceptorAdapter{ // implements HandlerInterceptor{

//������ ���������� �����ڸ� �̿��� �� �ֵ��� servlet-context.xml�� mapping�ϼ���
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession ses = request.getSession();
		MemberVO user = (MemberVO)ses.getAttribute("loginUser"); // �α��� �� Ű�� ����
		if(user.getMstate() != 9) {
			request.setAttribute("msg", "�����ڸ� ������ �� �ֽ��ϴ�.");
			String loc = request.getContextPath() + "/index";
			request.setAttribute("loc", loc);
			
			RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/views/message.jsp");
			disp.forward(request, response);
			
			return false;
		}
		return true;
	}
	
}
