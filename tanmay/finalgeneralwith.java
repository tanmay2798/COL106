package tanmay;

import java.util.Scanner;

public class finalgeneralwith {
	private static boolean f =false;
	
	public finalgeneralwith() {
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
	private static recursivetoh in = new recursivetoh();
	private static finalgeneralwith ine = new finalgeneralwith();
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n=s.nextInt();
		int start = s.nextInt();
		int red = s.nextInt();
		int black = s.nextInt();
		ine.gtohwithrecursion(n,start,red,black);
	}
	
	public void gtohwithrecursion(int numdisks, int startpos, int red, int black) {
		if(startpos==red && startpos==black) {
		}else {
			if(red==black) {
				in.tohwithrecursion(numdisks, startpos, red);
			}else {
				if(startpos!=red && startpos!=black && f==false) {
					if(numdisks%2==0)
						in.tohwithrecursion(numdisks,startpos,red);
					else
						in.tohwithrecursion(numdisks, startpos, black);
					numdisks=numdisks-2;
					f=true;
				}

				if(startpos==black && f==false && numdisks%2==0) {
					in.tohwithrecursion(numdisks,startpos,red);
					numdisks=numdisks-2;
					f=true;
				}else if(startpos==red && f==false && numdisks%2==1) {
					in.tohwithrecursion(numdisks,startpos,black);
					numdisks=numdisks-2;
					f =true;
				}else if(startpos==red && f==false && numdisks%2==0){
					numdisks=numdisks-2;
					f=true;
				}else if(startpos==black && f==false && numdisks%2==1){
					numdisks=numdisks-2;
					f=true;
				}
				if(numdisks==1 || numdisks==0) {
					if(numdisks%2==0)
						System.out.println(red+" "+black);
					else {
						System.out.println(black+" "+ine.check(black,red));
						System.out.println(black+" "+red);
						System.out.println(ine.check(black,red)+" "+black);
					}
				}else {

					if(numdisks%2==0) {
						in.tohwithrecursion(numdisks,red,ine.check(black,red));
						System.out.println(red+" "+black);
						in.tohwithrecursion(numdisks,ine.check(black,red),red);
					}else {
						in.tohwithrecursion(numdisks,black,ine.check(black,red));
						System.out.println(black+" "+red);
						in.tohwithrecursion(numdisks,ine.check(black,red),black);
					}
					ine.gtohwithrecursion(numdisks-2,startpos,red,black);
				}
			}
		}
	}
}
