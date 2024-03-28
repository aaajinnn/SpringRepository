package day01;
//https://school.programmers.co.kr/learn/courses/30/lessons/120829
import java.util.*;
public class 각도기 {
/*각에서 0도 초과 90도 미만은 예각, 
 * 90도는 직각, 90도 초과 180도 미만은 둔각 180도는 평각으로 분류합니다. 
 * 각 angle이 매개변수로 주어질 때 
 * 예각일 때 1, 직각일 때 2, 둔각일 때 3, 평각일 때 4를 return하도록 solution 함수를 완성해주세요.

예각 : 0 < angle < 90
직각 : angle = 90
둔각 : 90 < angle < 180
평각 : angle = 180
 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("angle을 입력하세요=>");
		int angle = sc.nextInt();

		Solution1 st = new Solution1();
		int res = st.solution(angle);
		System.out.println(res);
	}

}//////////////////

//각도기
class Solution1 {
    public int solution(int angle) {
        int answer = 0;
        if(angle>0 && angle<90) {
        	answer = 1;//예각
        }else if(angle == 90) {
        	answer = 2;//직각
        }else if(angle<180) {
        	answer = 3;//둔각
        }else if(angle==180) {
        	answer = 4;//평각
        }
		return answer;
    }
}
/*
 * //양꼬치 class Solution2 { public int solution(int n, int k) { int answer =
 * (n*12000)+(k*2000)-(n/10*2000); return answer; } }
 * 
 * //피자나눠먹기1 class Solution3 { public int solution(int n) { int answer = 0; int
 * k=n/7; if(n%7==0){ answer=k*1; }else if(n%7!=0){ answer=k*1+1; } return
 * answer; } }
 * 
 * //피자나눠먹기3 class Solution4 { public int solution(int slice, int n) { int
 * answer = 0; int k = n/slice; if(n%slice!=0){ answer = k*1+1; }else
 * if(n%slice==0){ answer = k*1; } return answer; } }
 * 
 * //피자나눠먹기2 class Solution5 { public int solution(int n) { int answer = 0; int
 * max = 0; for (int i=1; i<=n && i<=6; i++) { if (n%i==0 && 6%i==0) { max = i;
 * } } return answer = n*6/max/6; } }
 * 
 * //옷가게할인받기 class Solution6 { public int solution(int price) { int answer = 0;
 * if( price >= 500000) { answer = (int)(price*0.8); } else if( price >=
 * 300000){ answer = (int)(price * 0.9); }else if (price >= 100000){ answer =
 * (int)(price * 0.95); } else { answer = price; } return answer; } }
 */
