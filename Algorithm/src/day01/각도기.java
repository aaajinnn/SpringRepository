package day01;
//https://school.programmers.co.kr/learn/courses/30/lessons/120829
import java.util.*;
public class ������ {
/*������ 0�� �ʰ� 90�� �̸��� ����, 
 * 90���� ����, 90�� �ʰ� 180�� �̸��� �а� 180���� ������ �з��մϴ�. 
 * �� angle�� �Ű������� �־��� �� 
 * ������ �� 1, ������ �� 2, �а��� �� 3, ���� �� 4�� return�ϵ��� solution �Լ��� �ϼ����ּ���.

���� : 0 < angle < 90
���� : angle = 90
�а� : 90 < angle < 180
�� : angle = 180
 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("angle�� �Է��ϼ���=>");
		int angle = sc.nextInt();

		Solution1 st = new Solution1();
		int res = st.solution(angle);
		System.out.println(res);
	}

}//////////////////

//������
class Solution1 {
    public int solution(int angle) {
        int answer = 0;
        if(angle>0 && angle<90) {
        	answer = 1;//����
        }else if(angle == 90) {
        	answer = 2;//����
        }else if(angle<180) {
        	answer = 3;//�а�
        }else if(angle==180) {
        	answer = 4;//��
        }
		return answer;
    }
}
/*
 * //�粿ġ class Solution2 { public int solution(int n, int k) { int answer =
 * (n*12000)+(k*2000)-(n/10*2000); return answer; } }
 * 
 * //���ڳ����Ա�1 class Solution3 { public int solution(int n) { int answer = 0; int
 * k=n/7; if(n%7==0){ answer=k*1; }else if(n%7!=0){ answer=k*1+1; } return
 * answer; } }
 * 
 * //���ڳ����Ա�3 class Solution4 { public int solution(int slice, int n) { int
 * answer = 0; int k = n/slice; if(n%slice!=0){ answer = k*1+1; }else
 * if(n%slice==0){ answer = k*1; } return answer; } }
 * 
 * //���ڳ����Ա�2 class Solution5 { public int solution(int n) { int answer = 0; int
 * max = 0; for (int i=1; i<=n && i<=6; i++) { if (n%i==0 && 6%i==0) { max = i;
 * } } return answer = n*6/max/6; } }
 * 
 * //�ʰ������ιޱ� class Solution6 { public int solution(int price) { int answer = 0;
 * if( price >= 500000) { answer = (int)(price*0.8); } else if( price >=
 * 300000){ answer = (int)(price * 0.9); }else if (price >= 100000){ answer =
 * (int)(price * 0.95); } else { answer = price; } return answer; } }
 */
