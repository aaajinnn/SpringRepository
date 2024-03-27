package com.multi.mapper;

import java.util.List;

import com.board.domain.BoardVO;
import com.board.domain.PagingVO;

public interface BoardMapper {

	int insertBoard(BoardVO board);
	// �Խø�� ��������

	List<BoardVO> getBoardAll(); //pagingVO ����

	int getTotalCount();//�˻��� �� �Խñ� �� �������� //pagingVO ����

	// �۹�ȣ�� �ش��ϴ� �� ��������
	BoardVO selectBoardByNum(int num);   
	// ��ȸ�� �����ϱ�
	int updateReadnum(int num);
	   
	
	int deleteBoard(int num);
	int updateBoard(BoardVO board);

	// �亯��(������) �Խ��ǿ��� �亯�� �ޱ�
	int rewriteBoard(BoardVO board); //[�亯��]
	BoardVO selectRefLevSunbun(int num);//[�亯��]
	int updateSunbun(BoardVO parent);//[�亯��]
}