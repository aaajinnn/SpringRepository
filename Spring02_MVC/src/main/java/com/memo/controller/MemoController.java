package com.memo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.common.util.CommonUtil;
import com.memo.model.MemoVO;
import com.memo.service.memoService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j // log객체 사용 가능
public class MemoController {

	@Autowired
	private memoService memoService;

	@Autowired
	private CommonUtil util;

	@RequestMapping(value = "/memo", method = RequestMethod.GET) // url지정
	public String memoForm() {

		return "memo/input"; // View name
		// servlet-context.xml에 등록된 InternalResourceViewResolver빈이
		// 뷰네임 앞에 접두어("/WEB-INF/views/")와 접미어(.jsp)를 붙여준다.
		// WEB-INF/views/memo/input.jsp
	}

	@RequestMapping(value = "/memo", method = RequestMethod.POST)
	public String memoInsert(Model model, MemoVO vo) { // MemoVO : html에 입력한 값을 여기에 다 넣어줌

		if (vo.getName() == null || vo.getName().trim().isBlank()) { // 입력값이 공백일경우 유효성검사

			return "redirect:memo";// redirect방식으로 이동
		}

				
		int n = 0;
				//for(int i=0;i<20;i++)
				n = this.memoService.insertMemo(vo);

		String msg = (n > 0) ? "글 등록 성공" : "글 등록 실패";
		String loc = (n > 0) ? "memoList" : "javascript:history.back()";

		model.addAttribute("msg", msg);
		model.addAttribute("loc", loc);

		return "message";
	}// ----------------------------------------

	@RequestMapping(value = "/memoList", method = RequestMethod.GET)
	public String memoList(Model model, @RequestParam(defaultValue = "1") int pageNum) {
		log.info("pageNum : " + pageNum);
		if (pageNum < 1) {
			pageNum = 1;
		}

		// 페이징처리
		int totalCount = memoService.getMemoTotalCount();
		int oneRecordPage = 5;
		int pageCount = (totalCount - 1) / oneRecordPage + 1;
		if(pageNum > pageCount) {
			pageNum = pageCount; //마지막페이지로 지정
		}
		// select...
		// where rn >0 and rn<6  ==> xml에 부등호가 들어가면 시작태그< >로 인식 ==> 마크업 혹은 character dater로 표기
		/* pageNum	oneRecordPage	start	end
		 * 	1			5 			  0		 6
		 *  2			5			  5		 11
		 *  3		    5             10	 16
		 *  
		 *  start = (pageNum-1)*5;
		 *  end = start + (oneRecordPage+1);
		 * */
		
		// 서비스 listMemo()호출, 반환값 model에 저장
		int start = (pageNum-1) * oneRecordPage; //oracle에서 db에서 끊어올 때 시작값
		int end = start + (oneRecordPage + 1); //oracle에서 db에서 끊어올 때 종료값
		
		/* 페이징 블럭 연산 ***************
		 * pagingBlock = 5;
		 * 이전블럭(◀) : prevBlock
		 * 이후블럭(▶) : nextBlock
		 * pageNum
		 * [1][2][3][4][5] ▶ | ◀[6][7][8][9][10]▶ | ◀[11][12][13][14][15]
		 * 
		 * pageNum		pagingBlock		prevBlock(이전5개)		nextBlock(이후5개)
		 * 1~5				5				0						6
		 * 6~10				5				5						11
		 * 11~15			5				10						16
		 * 
		 * prevBlock = (pageNum-1)/pagingBlock * pagingBlock;
		 * nextBlock = prevBlock + (pagingBlock+1);
		 * *******************************/
		int pagingBlock = 5;
		int prevBlock = (pageNum-1)/pagingBlock * pagingBlock;
		int nextBlock = prevBlock + (pagingBlock+1);
		
		
		List<MemoVO> arr = this.memoService.listMemo(start, end); //Oracle 사용시
		
		int offset = (pageNum-1) * oneRecordPage; //MySQL 사용시
		int limit = oneRecordPage; //MySQL 사용시
		//List<MemoVO> arr = memoService.listMemoMySQL(limit, offset); //MySQL 사용시
		
		model.addAttribute("memoArr", arr);

		// 페이징처리
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pageCount", pageCount);
		
		model.addAttribute("pagingBlock",pagingBlock);
		model.addAttribute("prevBlock",prevBlock);
		model.addAttribute("nextBlock",nextBlock);

		return "memo/list2";
	}// ----------------------------------------

	@RequestMapping(value = "/memoDel", method = RequestMethod.GET)
	public String memoDelete(Model model, @RequestParam(name = "no", defaultValue = "0") int no) { // url에 직접 쳐서 들어온다면
																									// 기본값 0으로
		log.info("no : " + no);
		if (no == 0) {
			return "redirect:memoList";
		}
		int n = memoService.deleteMemo(no);
//		String msg = (n > 0) ? "삭제 성공" : "삭제 실패";
//		String loc = "memoList";

		return "redirect:memoList";
	}// ----------------------------------------

	@RequestMapping(value = "/memoEdit", method = RequestMethod.GET)
	public String memoEditForm(Model model, @RequestParam(defaultValue = "0") int no) {
		// 유효성 체크
		if (no == 0) {
			return util.addMsgBack(model, "잘못된 경로입니다.");
		}

		// getMemo(글번호) ==> MemoVO ==> model에 저장 키값"vo"
		MemoVO vo = memoService.getMemo(no);
		model.addAttribute("vo", vo);

		return "memo/edit";
	}// ----------------------------------------

	@RequestMapping(value = "/memoEdit", method = RequestMethod.POST)
	public String memoEditEnd(Model model, MemoVO memo) { // MemoVO memo 수정한 값이 여기로 들어옴
		log.info("memo : " + memo);

		// 유효성 체크
		if (memo.getNo() == 0 || memo.getName() == null || memo.getName().trim().isBlank()) {
			return util.addMsgBack(model, "작성자를 입력해야 합니다.");
		}

		int n = memoService.updateMemo(memo); // 수정한 MemoVO memo 를 받아 넘겨
		String msg = (n > 0) ? "수정 완료" : "수정 실패";

		// model.addAttribute("mode", "popup");//mode값이 popup이면 창닫기 처리
		// model.addAttribute("msg", msg);
		// model.addAttribute("loc","memoList");
		// return "message";
		return util.addMsgPopup(model, msg);
	}
}
