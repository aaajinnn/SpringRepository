package com.multi.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.memo.model.MemoVO;

@Mapper
public interface MemoMapper {
	
	//MemoService와 똑같이 작성해도 됨
	int insertMemo(MemoVO vo); //C
	
	List<MemoVO> listMemo(); //R ==> 페이징처리 안할 때
	
	List<MemoVO> listMemoPaging(Map<String, Object> map); // ==> 페이징처리 할 때
	
	MemoVO getMemo(int no); //R
	
	int deleteMemo(int no); //D
	
	int updateMemo(MemoVO vo); //U
	
	int getMemoTotalCount();

}
