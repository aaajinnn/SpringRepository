package com.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.board.domain.BoardVO;
import com.board.domain.PagingVO;
import com.multi.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

@Service("/boardService")
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private final BoardMapper bMapper;

	@Override
	public int insertBoard(BoardVO board) {
		return bMapper.insertBoard(board);
	}

	@Override
	public List<BoardVO> getBoardAll() { //pagingVO 지움
		return bMapper.getBoardAll();
	}

	@Override
	public int getTotalCount() { //pagingVO 지움
		return bMapper.getTotalCount();
	}

	@Override
	public BoardVO selectBoardByNum(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateReadnum(int num) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBoard(int num) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateBoard(BoardVO board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int rewriteBoard(BoardVO board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BoardVO selectRefLevSunbun(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateSunbun(BoardVO parent) {
		// TODO Auto-generated method stub
		return 0;
	}

}
