package ex03;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
//Target
@Service 
public class BoardServiceImpl implements BoardService {

	List<String> dbArr=new ArrayList<>();//DB역할을 한다고 생각
	
	@Override
	public int insertBoard(String msg) {
		System.out.println("핵심로직 수행하는 메서드 insertBoard(): "+msg);
		dbArr.add(msg);
		//int a=10/0; //테스트를 위해 일부러 에러발생(after-throwing, around테스트시 주석 풀고 테스트)
		return dbArr.size();
	}

}
