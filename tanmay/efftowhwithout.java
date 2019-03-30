package tanmay;

import java.util.Scanner;

public class efftowhwithout {

	public efftowhwithout() {
		// TODO Auto-generated constructor stub
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
	public static void main(String[] args) throws EmptyStackException {
		Scanner s = new Scanner(System.in);
		int n=s.nextInt();
		int start = s.nextInt();
		int red = s.nextInt();
		int black = s.nextInt();
		efftowhwithout ine=new efftowhwithout();
		ine.gtohwithoutrecursions(n,start,red,black);
	}
	public void gtohwithoutrecursions(int numdisks, int start, int red,int black) throws EmptyStackException {
		efftowhwithout ine = new efftowhwithout();
		checker call= new checker();
		if(start==red && start==black) {

		}else {
			if(red==black) {
				call.tohwithoutrecursion(numdisks, start, red);
			}else {
				if(start!=red && start!=black) {
					if(numdisks%2==0)
						call.tohwithoutrecursion(numdisks, start, red);
					else
						call.tohwithoutrecursion(numdisks, start, black);
				}
				if(start==red &&numdisks%2==1) {
					call.tohwithoutrecursion(numdisks, start, black);
				}
				if(start==black &&numdisks%2==0) {
					call.tohwithoutrecursion(numdisks, start, red);
				}
				for(int i=numdisks-2;i>0;i=i-2) {
					if(i%2==0) {
						call.tohwithoutrecursion(i,red,ine.check(red, black));

						System.out.println(red+" "+black);

						call.tohwithoutrecursion(i, ine.check(red, black), red);
					}else {
						call.tohwithoutrecursion(i,black,ine.check(red, black));

						System.out.println(black+" "+red);

						call.tohwithoutrecursion(i, ine.check(red, black), black);
					}
				}
				if(numdisks%2==0)
					System.out.println(red+" "+black);
			}
		}
	}
}
