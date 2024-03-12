package com.memo.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.memo.model.MemoVO;
import com.multi.mapper.MemoMapper;
//비즈니스계층, 서비스계층
@Service("memoService") 
//@Service //이름을 붙이지 않으면 memoServiceImpl <=객체이름이 됨
//com.memo.service 패키지명을 servlet-context.xml의 컴포넌트스킨에 포함시켜야만 부를수있다
public class MemoServiceImpl implements memoService {

	@Inject // bytype으로 주입
	private MemoMapper memoMapper;
	
	@Override
	public int insertMemo(MemoVO vo) {
		return memoMapper.insertMemo(vo);
	}
	
	@Override
	public List<MemoVO> listMemo() {
		return memoMapper.listMemo();
	}

	@Override
	public MemoVO getMemo(int no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteMemo(int no) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateMemo(MemoVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
