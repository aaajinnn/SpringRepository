package day01;
//https://school.programmers.co.kr/learn/courses/30/lessons/120814
import java.util.Scanner;

public class ���ڳ����Ա�_1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("�������� ����� ���� �Է��ϼ���=>");
		int n = sc.nextInt();
		
		Solution3 st = new Solution3();
		int res = st.solution(n);
		System.out.println(res);

	}

}

class Solution3 {
    public int solution(int n) {
    	 int answer = 0;
         int k=n/7;
         if(n%7==0){
            answer=k*1;
          }else if(n%7!=0){
            answer=k*1+1;
          }
         return answer;
    }
}