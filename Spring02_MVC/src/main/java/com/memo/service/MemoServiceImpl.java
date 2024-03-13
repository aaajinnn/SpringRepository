package com.memo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public List<MemoVO> listMemo(int start, int end) {
		Map<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		
		return memoMapper.listMemoPaging(map);
	}

	public List<MemoVO> listMemoMySQL(int limit, int offset){
		Map<String, Object> map = new HashMap<>();
		map.put("limit", limit);
		map.put("offset", offset);
		
		return memoMapper.listMemoPaging(map);
	}
	
	@Override
	public MemoVO getMemo(int no) {
		return memoMapper.getMemo(no);
	}

	@Override
	public int deleteMemo(int no) {
		return memoMapper.deleteMemo(no);
	}

	@Override
	public int updateMemo(MemoVO vo) {
		return memoMapper.updateMemo(vo);
	}

	@Override
	public int getMemoTotalCount() {
		return memoMapper.getMemoTotalCount();
	}

}
