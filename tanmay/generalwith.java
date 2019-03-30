package tanmay;

import java.util.Scanner;

public class generalwith {
	static boolean f =false;
	static boolean f1 =false;
	public generalwith() {
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
	
	static recursivetoh in = new recursivetoh();
	static generalwith ine = new generalwith();
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n=s.nextInt();
		int start = s.nextInt();
		int red = s.nextInt();
		int black = s.nextInt();
		gtohwithrecursion(n,start,red,black);
	}
	public static boolean flag() {
		return true;
	}
	public static boolean odd(int n) {	
		if(n%2==1)
			return true;
		else
			return false;
	}
	public static void gtohwithrecursion(int numdisks, int startpos, int red, int black) {
		if(startpos==red && startpos==black) {
		
		}else {

			if(red==black) {
				in.tohwithrecursion(numdisks, startpos, red);
			}else {
				boolean c = odd(numdisks);
				if(c==true) {
					int temp = red;
					red=black;
					black=temp;
				}
				if(startpos!=red && startpos!=black && f==false) {

					in.tohwithrecursion(numdisks-1,startpos,ine.check(startpos,red));
					in.tohwithrecursion(1,startpos,red);
					in.tohwithrecursion(numdisks-1,ine.check(startpos,red),red);
					System.out.println(startpos+"ho "+red);
					numdisks=numdisks-1;
					f=flag();
				}

				if(startpos==black && f==false) {
					System.out.println(f);
					in.tohwithrecursion(numdisks-1,startpos,ine.check(startpos,red));
					in.tohwithrecursion(1,startpos,red);
					in.tohwithrecursion(numdisks-1,ine.check(startpos,red),red);
					numdisks=numdisks-1;
					f=flag();
				}else if(startpos==red && f==false) {
					in.tohwithrecursion(numdisks-2,startpos,ine.check(startpos,black));
					in.tohwithrecursion(1,startpos,black);
					in.tohwithrecursion(numdisks-2,ine.check(startpos,black),black);
					numdisks=numdisks-2;
					f =flag();
				}
				if(c==true) {
					int temp = red;
					red=black;
					black=temp;
				}
				if(numdisks==1) {
					System.out.println(red+"h"+black);
				}else {

					if(numdisks%2==1) {
						//System.out.println(red+"hh "+black);
						in.tohwithrecursion(numdisks-1,red,ine.check(black,red));
						in.tohwithrecursion(1,red,black);
						in.tohwithrecursion(numdisks-1,ine.check(black,red),black);
					}else {
						in.tohwithrecursion(numdisks-1,black,ine.check(black,red));
						in.tohwithrecursion(1,black,red);
						in.tohwithrecursion(numdisks-1,ine.check(black,red),red);
					}
					generalwith.gtohwithrecursion(numdisks-1,startpos,red,black);
				}
			}
		}
	}

}
