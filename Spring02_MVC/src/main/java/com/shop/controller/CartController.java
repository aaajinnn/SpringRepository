package com.shop.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.common.util.CommonUtil;
import com.shop.domain.CartVO;
import com.shop.service.ShopService;
import com.user.domain.MemberVO;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/user")
public class CartController {

	@Inject
	private CommonUtil util;
	
	@Inject
	private ShopService shopService;

	@PostMapping("/cartAdd") // 파라미터로 상품번호와 수량이 들어옴
	public String addCart(Model m, CartVO cvo, HttpSession session) {
		
		log.info("cvo : " + cvo);

		// 세션에서 로그인한 사람의 아이디 가져오기
		MemberVO user = (MemberVO)session.getAttribute("loginUser");
		String userid = user.getUserid();
		
		cvo.setUserid(userid);

		// 장바구니에 추가
		int n = shopService.addCart(cvo); // insert or update ==> 이미 장바구니에 넣어져있다면 수량만 수정, 없다면 새로 추가

		// 특정 회원의 장바구니 목록 가져오기
		/*
		 * List<CartVO> cartArr = shopService.selectCartView(userid);
		 * log.info("cartArr : "+ cartArr);
		 * 
		 * m.addAttribute("cartArr", cartArr);
		 */

		// return "shop/cartList"; //forward방식
		/*
		 * ==> 여기서 forward방식으로 이동하면 브라우저 새로고침시 계속 상품수량이 추가되는 현상이 발생한다 => 장바구니 총액 증가 ==>
		 * 목록으로 redirect 여기에서는 추가하는 로직만 신경쓰면되고, 목록가져오는것과 매핑하여 그렇게 목록 가져오면 된다.
		 */
		
		return "redirect:cartList";
	}// ----------------------

	@GetMapping("/cartList")
	public String cartList(Model m, HttpSession session) {
		
		//세션에서 로그인 한 사람 아이디 꺼내기
		MemberVO user = (MemberVO)session.getAttribute("loginUser");
		String userid = user.getUserid();
		
		// 특정 회원의 장바구니 목록 가져오기
		List<CartVO> cartArr = shopService.selectCartView(userid);
		//log.info("cartArr : "+ cartArr);
		
		//특정 회원의 장바구니 총액, 총포인트 가져오기
		CartVO cvo = shopService.getCartTotal(userid);
		  
		  m.addAttribute("cartArr", cartArr);
		  m.addAttribute("cartSum", cvo);
		  
		  return "shop/cartList";
	}//----------------------------
	
	@PostMapping("/cartDel")
	public String cartDelete(@RequestParam(defaultValue="0")int cnum) {
		if(cnum==0) {
			return "redirect:cartList";
		}
		
		int n = shopService.delCart(cnum);
		
		return "redirect:cartList";
	}//----------------------------
	
	@GetMapping("/cartDelAll")
	public String cartDeleteAll(HttpSession session) {
		
		//세션에서 로그인 한 사람 아이디 꺼내기
		MemberVO user = (MemberVO)session.getAttribute("loginUser");
		String userid = user.getUserid();
		
		CartVO cartVo = new CartVO();
		cartVo.setUserid(userid); //CartVO에 userid 넘기기
		int n = shopService.delCartAll(cartVo);
		
		return "redirect:cartList";
		
	}//----------------------------
	
	@PostMapping("/cartEdit")
	public String cartEdit(CartVO vo) {
		log.info("vo : " + vo);
		
		if(vo.getCnum()==0) {
			return "redirect:cartList";
		}
		
		int n = shopService.editCart(vo);
		
		return "redirect:cartList";
	}//----------------------------
	
	/*스프링의 예외 처리 방법
	 * [1] @ExceptionHandler를 이용하는 방법 ==> 예외 처리 메서드 만들어서 붙인다.
	 * [2] @ControllerAdvice를 이용하는 방법 ==> 따로 클래스를 만들어서 붙인다.(권장)
	 * [3] @ResponseStatus 를 이용해서 HTTP상태코드 처리하는 방법
	 * */
	/*
	@ExceptionHandler(NumberFormatException.class) // 다른컨트롤러에서도 예외처리를 할 때 올바른 예외관리를 하기 힘들다.
	public String exceptionHandler(Exception ex, Model m) {
		
		String msg = ex.getMessage(); //예외메시지(스텍기록을 메시지로 띄워 보여줌)
		
		return util.addMsgBack(m, msg);
	}
	*/
}
