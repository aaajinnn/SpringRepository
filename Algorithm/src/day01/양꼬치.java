package day01;

import java.util.Scanner;

//https://school.programmers.co.kr/learn/courses/30/lessons/120830
public class 양꼬치 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("몇인분 인가요?=>");
		int n = sc.nextInt();
		System.out.println("음료수는 몇개인가요?=>");
		int k = sc.nextInt();
		
		Solution2 st = new Solution2();
		int res = st.solution(n, k);
		System.out.println(res);

	}

}

class Solution2 {
    public int solution(int n, int k) {
        int answer = (n*12000)+(k*2000)-(n/10*2000);
        return answer;
    }
}
