package common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import common.domain.SampleVO;

//interface에는 Mapper라는 어노테이션이 붙는다.
//spring이 인터페이스를 상속받는 클래스 객체를 대신 생성해준다
//==> proxy객체(대리객체)
//그렇게 하기위해서는 패키지를 등록해주어야함 ==> SpringWebApplication.java에 등록
@Mapper 
public interface SampleMapper {
	public int testCnt(); //추상메서드

	public List<SampleVO> getTableInfo(); //SampleMapper.xml에서 id가 getTableInfo인 애를 찾는다
}
