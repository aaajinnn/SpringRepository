package com.common.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.user.domain.MemberVO;

/*Interceptor
 *  - ��Ʈ�ѷ��� ����Ǳ� ���� ���� ó���� ���� ������ 
 *    ������������ ���ͼ��Ϳ��� �����Ѵ�.
 *  - ���� ���
 *  1. ���ͼ��� ����
 *     [1] HandlerInterceptor�������̽��� ��ӹ޴� ��� (implements)
 *     [2] HandlerInterceptorAdapter �߻�Ŭ������ ��ӹ޴� ��� (extends)
 *      
 *  2. ���ͼ��� ��� => servlet-context.xml���� ����ϰ� ���� ������ ����
	 *  <!-- interceptor ================================================== -->
	 *  <interceptors> 
			<!-- �α��� üũ ���ͼ��� -->
			<interceptor>
				<mapping path="/user/**"/>
				<mapping path="/admin/**"/>
				<beans:bean class="com.common.interceptor.LoginCheckInterceptor"/>	
			</interceptor>
			
			<!-- ������ üũ ���ͼ��� -->
		</interceptors>
 *  */


public class LoginCheckInterceptor implements HandlerInterceptor{

	//[1]preHandle() : ����ó�� Controller�� �����ϱ� ���� ȣ��Ǵ� �޼���
	//[2]postHandle() : ����ó�� Controller�� ������ ��, ���� �並 �����ϱ� ���� ȣ��Ǵ� �޼���
	//[3] afterCompletion() : ����ó�� Controller�� �����ϰ� �並 ������ �Ŀ� ȣ��Ǵ� �޼���
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession ses = request.getSession(); //���Ǿ���
		MemberVO user = (MemberVO)ses.getAttribute("loginUser"); // �α��� �� Ű�� ����
		if(user==null) {
			request.setAttribute("msg", "�α����ؾ� �̿��Ͻ� �� �ֽ��ϴ�.");
			String loc = request.getContextPath() + "/login";
			request.setAttribute("loc", loc);
			
			RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/views/message.jsp");
			disp.forward(request, response);
			
			return false; // false�� ��ȯ�ϸ� ��Ʈ�ѷ��� �Ѿ�� ����
		}
		return true; //true�� ��ȯ�ϸ� ��Ʈ�ѷ��� �����
	}
	
	
	
}//////////////////////////