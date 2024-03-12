package com.multi.campus;

import java.sql.Connection;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.multi.mapper.SampleMapper;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml") // root-context.xml에 빈으로 등록한애들 테스트할때 얘를 읽어서 찾아서 사용
@Log4j //log기록 남기기(lombok설치 후 사용)
public class SampleTest {
	
	@Resource(name = "dataSource-dbcp")
	private DataSource ds;
	
	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;
	
	@Autowired //대리객체 네임 모를때 @Autowired 사용 ==> 패키지 읽어 프록시개체 만들어 메모리에 올려줌
	private SampleMapper mapper; // 타입을 주입받아 @Test에서 얻어와
	
	@Test
	public void testConnection() {
		
		System.out.println("ds얻기 성공 : " + ds);
		
		// 테스트 코드
		try(Connection con = ds.getConnection()){
			System.out.println("con얻기 성공 : " + con);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}//-------------------
	
	@Test
	public void testSqlSessionTemplate() {
		System.out.println("sqlSessionTemplate얻어옴 : " + sqlSession);
	}//-------------------
	
	@Test
	public void testSampleMapper() {
		int cnt = mapper.getMemberCount();
		System.out.println("java_member회원 수 : "+cnt);
	}
	
	@Test
	public void testMemberNames() {
		List<String> names = mapper.getMemberNames();
		System.out.println("java_member names : " + names);
	}
	
	
}
