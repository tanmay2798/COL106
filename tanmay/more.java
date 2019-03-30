package tanmay;

import java.util.Scanner;

public class more {

	public more() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws EmptyStackException {
		Scanner s = new Scanner(System.in);
		int n=s.nextInt();
		int start = s.nextInt();
		int red = s.nextInt();
		int black = s.nextInt();
		checker in = new checker();
		recursivetoh ine = new recursivetoh();
		for(int i=n;i>0;i--) {
			if(start==1) {
				in.iObj1.push(i);
			}
			if(start==2) {
				in.iObj2.push(i);
			}
			if(start==3) {
				in.iObj3.push(i);
			}
		}
		gtohwithoutrecursions(n,start,red,black);
	}
	public static void gtohwithoutrecursions(int numdisks, int start, int red,int black) throws EmptyStackException {
		recursivetoh ine = new recursivetoh();
		checker in = new checker();
		start=start;
		red=red;
		if(start==red && start==black) {

		}else {
			if(red==black) {
				checker.tohwithoutrecursion(numdisks, start, red);
			}else {
				if(start!=red && start!=black) {
					if(numdisks%2==0)
						checker.tohwithoutrecursion(numdisks, start, red);
					else
						checker.tohwithoutrecursion(numdisks, start, black);
				}
				if(start==red &&numdisks%2==1) {
					checker.tohwithoutrecursion(numdisks, start, black);
				}
				if(start==black &&numdisks%2==0) {
					checker.tohwithoutrecursion(numdisks, start, red);
				}
				int i;
				System.out.println("HI");
				for(i=numdisks-2;i>0;i=i-3) {
					//	System.out.println(red+" hi "+black);
					if(i%2==0) {
						checker.tohwithoutrecursion(i,red,ine.check(red, black));

						System.out.println(red+" "+black);

						checker.tohwithoutrecursion(i-1, ine.check(red, black), black);
						System.out.println(ine.check(red, black)+" "+red);
						
					}else {
						checker.tohwithoutrecursion(i,black,ine.check(red, black));

						System.out.println(black+"h "+red);

						checker.tohwithoutrecursion(i-1, ine.check(red, black), red);
						System.out.println(ine.check(red, black)+" h"+black);
					}
				}
				if(i%2==1)
					System.out.println(red+" "+black);
			}
		}
	}
}
