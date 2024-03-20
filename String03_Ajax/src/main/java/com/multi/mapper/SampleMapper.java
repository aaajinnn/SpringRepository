package com.multi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SampleMapper {

	//java_member테이블에서 회원수 가져오는 추상메서드(단위 테스트하기위함)
	@Select("select count(id) from java_member") //매퍼파일 따로 만들지않고 어노테이션으로 할 수 있음
	public int getMemberCount();
	
	public List<String> getMemberNames(); //SampleMapper.xml ==> mybatis-config.xml에 등록되어있어야함
	
}
