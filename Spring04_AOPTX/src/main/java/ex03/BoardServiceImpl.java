package ex03;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
//Target
@Service 
public class BoardServiceImpl implements BoardService {

	List<String> dbArr=new ArrayList<>();//DB������ �Ѵٰ� ����
	
	@Override
	public int insertBoard(String msg) {
		System.out.println("�ٽɷ��� �����ϴ� �޼��� insertBoard(): "+msg);
		dbArr.add(msg);
		//int a=10/0; //�׽�Ʈ�� ���� �Ϻη� �����߻�(after-throwing, around�׽�Ʈ�� �ּ� Ǯ�� �׽�Ʈ)
		return dbArr.size();
	}

}