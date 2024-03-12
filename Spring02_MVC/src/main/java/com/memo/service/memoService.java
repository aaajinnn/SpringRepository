package com.memo.service;

import java.util.List;

import com.memo.model.MemoVO;

public interface memoService {

	int insertMemo(MemoVO vo); //C
	
	List<MemoVO> listMemo(); //R
	
	MemoVO getMemo(int no); //R
	
	int deleteMemo(int no); //D
	
	int updateMemo(MemoVO vo); //U
}
