package com.multi.campus;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.common.util.CommonUtil;
import com.memo.model.MemoVO;
import com.shop.service.SampleTxService;
import com.user.domain.MemberVO;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class SampleTxController {

	@Inject
	private SampleTxService service;
	
	@Inject
	private CommonUtil util;
	
	@GetMapping("/tx")
	public String transactionText(Model m ) {
		String str = "또다시 잘 될까요? 잘되면 500마일리지를 적립해요";
		//str += "엄청 많은 글을 써요 엄청 많은 글을 써요 엄청 많은 글을 써요";
		//str += "엄청 많은 글을 써요 엄청 많은 글을 써요 엄청 많은 글을 써요";
		//str += "엄청 많은 글을 써요 엄청 많은 글을 써요 엄청 많은 글을 써요";
		//str += "엄청 많은 글을 써요 엄청 많은 글을 써요 엄청 많은 글을 써요"; //=> 에러가 나지만 마일리지 적립은 됨 
					//=> 이걸해결하기 위해 Controller에서 @Trasactional 어노테이션을 붙여줌
		
		MemberVO user = new MemberVO();
		user.setUserid("choi");
		user.setMileage(500); //적립해줄 마일리지
		
		MemoVO memo = new MemoVO();
		memo.setMsg(str);
		memo.setName(user.getUserid()); //choi
		
		/////////////////////////////
		service.addMemo(memo, user);
		/////////////////////////////
		
		String msg = user.getUserid() +"님 글 한줄 메모장에 등록 완료";
		String loc ="index";
		return util.addMsgLoc(m, msg, loc);
	}
}
