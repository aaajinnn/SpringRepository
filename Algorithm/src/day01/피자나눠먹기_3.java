package day01;
//https://school.programmers.co.kr/learn/courses/30/lessons/120816
import java.util.Scanner;

public class 피자나눠먹기_3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("피자 조각 수를 입력하세요=>");
		int slice = sc.nextInt();
		System.out.println("먹는 사람 수를 입력하세요=>");
		int n = sc.nextInt();
		
		Solution4 st = new Solution4();
		int res = st.solution(slice, n);
		System.out.println(res);

	}

}

class Solution4 {
    public int solution(int slice, int n) {
        int answer = 0;
        int k = n/slice;
        if(n%slice!=0){
          answer = k*1+1;
        }else if(n%slice==0){
          answer = k*1;
        }
        return answer;
    }
}