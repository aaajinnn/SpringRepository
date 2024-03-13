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
@Log4j // log��ü ��� ����
public class MemoController {

	@Autowired
	private memoService memoService;

	@Autowired
	private CommonUtil util;

	@RequestMapping(value = "/memo", method = RequestMethod.GET) // url����
	public String memoForm() {

		return "memo/input"; // View name
		// servlet-context.xml�� ��ϵ� InternalResourceViewResolver����
		// ����� �տ� ���ξ�("/WEB-INF/views/")�� ���̾�(.jsp)�� �ٿ��ش�.
		// WEB-INF/views/memo/input.jsp
	}

	@RequestMapping(value = "/memo", method = RequestMethod.POST)
	public String memoInsert(Model model, MemoVO vo) { // MemoVO : html�� �Է��� ���� ���⿡ �� �־���

		if (vo.getName() == null || vo.getName().trim().isBlank()) { // �Է°��� �����ϰ�� ��ȿ���˻�

			return "redirect:memo";// redirect������� �̵�
		}

				
		int n = 0;
				//for(int i=0;i<20;i++)
				n = this.memoService.insertMemo(vo);

		String msg = (n > 0) ? "�� ��� ����" : "�� ��� ����";
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

		// ����¡ó��
		int totalCount = memoService.getMemoTotalCount();
		int oneRecordPage = 5;
		int pageCount = (totalCount - 1) / oneRecordPage + 1;
		if(pageNum > pageCount) {
			pageNum = pageCount; //�������������� ����
		}
		// select...
		// where rn >0 and rn<6  ==> xml�� �ε�ȣ�� ���� �����±�< >�� �ν� ==> ��ũ�� Ȥ�� character dater�� ǥ��
		/* pageNum	oneRecordPage	start	end
		 * 	1			5 			  0		 6
		 *  2			5			  5		 11
		 *  3		    5             10	 16
		 *  
		 *  start = (pageNum-1)*5;
		 *  end = start + (oneRecordPage+1);
		 * */
		
		// ���� listMemo()ȣ��, ��ȯ�� model�� ����
		int start = (pageNum-1) * oneRecordPage; //oracle���� db���� ����� �� ���۰�
		int end = start + (oneRecordPage + 1); //oracle���� db���� ����� �� ���ᰪ
		
		/* ����¡ �� ���� ***************
		 * pagingBlock = 5;
		 * ������(��) : prevBlock
		 * ���ĺ�(��) : nextBlock
		 * pageNum
		 * [1][2][3][4][5] �� | ��[6][7][8][9][10]�� | ��[11][12][13][14][15]
		 * 
		 * pageNum		pagingBlock		prevBlock(����5��)		nextBlock(����5��)
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
		
		
		List<MemoVO> arr = this.memoService.listMemo(start, end); //Oracle ����
		
		int offset = (pageNum-1) * oneRecordPage; //MySQL ����
		int limit = oneRecordPage; //MySQL ����
		//List<MemoVO> arr = memoService.listMemoMySQL(limit, offset); //MySQL ����
		
		model.addAttribute("memoArr", arr);

		// ����¡ó��
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pageCount", pageCount);
		
		model.addAttribute("pagingBlock",pagingBlock);
		model.addAttribute("prevBlock",prevBlock);
		model.addAttribute("nextBlock",nextBlock);

		return "memo/list2";
	}// ----------------------------------------

	@RequestMapping(value = "/memoDel", method = RequestMethod.GET)
	public String memoDelete(Model model, @RequestParam(name = "no", defaultValue = "0") int no) { // url�� ���� �ļ� ���´ٸ�
																									// �⺻�� 0����
		log.info("no : " + no);
		if (no == 0) {
			return "redirect:memoList";
		}
		int n = memoService.deleteMemo(no);
//		String msg = (n > 0) ? "���� ����" : "���� ����";
//		String loc = "memoList";

		return "redirect:memoList";
	}// ----------------------------------------

	@RequestMapping(value = "/memoEdit", method = RequestMethod.GET)
	public String memoEditForm(Model model, @RequestParam(defaultValue = "0") int no) {
		// ��ȿ�� üũ
		if (no == 0) {
			return util.addMsgBack(model, "�߸��� ����Դϴ�.");
		}

		// getMemo(�۹�ȣ) ==> MemoVO ==> model�� ���� Ű��"vo"
		MemoVO vo = memoService.getMemo(no);
		model.addAttribute("vo", vo);

		return "memo/edit";
	}// ----------------------------------------

	@RequestMapping(value = "/memoEdit", method = RequestMethod.POST)
	public String memoEditEnd(Model model, MemoVO memo) { // MemoVO memo ������ ���� ����� ����
		log.info("memo : " + memo);

		// ��ȿ�� üũ
		if (memo.getNo() == 0 || memo.getName() == null || memo.getName().trim().isBlank()) {
			return util.addMsgBack(model, "�ۼ��ڸ� �Է��ؾ� �մϴ�.");
		}

		int n = memoService.updateMemo(memo); // ������ MemoVO memo �� �޾� �Ѱ�
		String msg = (n > 0) ? "���� �Ϸ�" : "���� ����";

		// model.addAttribute("mode", "popup");//mode���� popup�̸� â�ݱ� ó��
		// model.addAttribute("msg", msg);
		// model.addAttribute("loc","memoList");
		// return "message";
		return util.addMsgPopup(model, msg);
	}
}
