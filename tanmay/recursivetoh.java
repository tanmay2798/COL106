package tanmay;

import java.util.Scanner;

public class recursivetoh {

	public recursivetoh() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n=s.nextInt();
		int start = s.nextInt();
		int end = s.nextInt();
		recursivetoh in = new recursivetoh();
		in.tohwithrecursion(n,start,end);
	}

	private int check (int a, int b) {
		if(a==1 && b==2 || a==2 && b==1)
			return 3;
		if(a==1 && b==3 || a==3 && b==1)
			return 2;
		if(a==3 && b==2 || a==2 && b==3)
			return 1;
		else
			return 0;

	}

	public void tohwithrecursion(int numdisks, int startpos, int endpos) {
		if(startpos==endpos) {

		}else {
			if(numdisks==1) {
				System.out.println(startpos+" "+endpos);
			}else {
				recursivetoh ina = new recursivetoh();

				ina.tohwithrecursion(numdisks-1,startpos,check(startpos,endpos));
				ina.tohwithrecursion(1,startpos,endpos);
				ina.tohwithrecursion(numdisks-1,check(startpos,endpos),endpos);	
			}
		}
	}
}
