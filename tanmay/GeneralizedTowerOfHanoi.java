package tanmay;

import java.util.Scanner;

public class GeneralizedTowerOfHanoi {
	private static boolean f =false;
	private static TowerOfHanoi in = new TowerOfHanoi();
	private static GeneralizedTowerOfHanoi ine = new GeneralizedTowerOfHanoi();

	public GeneralizedTowerOfHanoi() {
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
	
	public static void gtoh_with_recursion(int num_disks, int start_pos, int r, int b) {
		if(start_pos==r && start_pos==b) {
		}else {
			if(r==b) {
				TowerOfHanoi.toh_with_recursion(num_disks, start_pos, r);
			}else {
				if(start_pos!=r && start_pos!=b && f==false) {
					if(num_disks%2==0)
						TowerOfHanoi.toh_with_recursion(num_disks,start_pos,r);
					else
						TowerOfHanoi.toh_with_recursion(num_disks, start_pos, b);
					num_disks=num_disks-2;
					f=true;
				}

				if(start_pos==b && f==false && num_disks%2==0) {
					TowerOfHanoi.toh_with_recursion(num_disks,start_pos,r);
					num_disks=num_disks-2;
					f=true;
				}else if(start_pos==r && f==false && num_disks%2==1) {
					TowerOfHanoi.toh_with_recursion(num_disks,start_pos,b);
					num_disks=num_disks-2;
					f =true;
				}else if(start_pos==r && f==false && num_disks%2==0){
					num_disks=num_disks-2;
					f=true;
				}else if(start_pos==b && f==false && num_disks%2==1){
					num_disks=num_disks-2;
					f=true;
				}
				if(num_disks==1 || num_disks==0) {
					if(num_disks%2==0)
						System.out.println(r+" "+b);
					else {
						System.out.println(b+" "+ine.check(b,r));
						System.out.println(b+" "+r);
						System.out.println(ine.check(b,r)+" "+b);
					}
				}else {

					if(num_disks%2==0) {
						TowerOfHanoi.toh_with_recursion(num_disks,r,ine.check(b,r));
						System.out.println(r+" "+b);
						TowerOfHanoi.toh_with_recursion(num_disks,ine.check(b,r),r);
					}else {
						TowerOfHanoi.toh_with_recursion(num_disks,b,ine.check(b,r));
						System.out.println(b+" "+r);
						TowerOfHanoi.toh_with_recursion(num_disks,ine.check(b,r),b);
					}
					GeneralizedTowerOfHanoi.gtoh_with_recursion(num_disks-2,start_pos,r,b);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n=s.nextInt();
		int start = s.nextInt();
		int r = s.nextInt();
		int b = s.nextInt();
		GeneralizedTowerOfHanoi.gtoh_with_recursion(n,start,r,b);
	}
	
	public static void gtoh_without_recursions(int num_disks, int start_pos, int r,int b) throws EmptyStackException {
		//efftowhwithout ine = new efftowhwithout();
		
		if(start_pos==r && start_pos==b) {

		}else {
			if(r==b) {
				TowerOfHanoi.toh_without_recursion(num_disks, start_pos, r);
			}else {
				if(start_pos!=r && start_pos!=b) {
					if(num_disks%2==0)
						TowerOfHanoi.toh_without_recursion(num_disks, start_pos, r);
					else
						TowerOfHanoi.toh_without_recursion(num_disks, start_pos, b);
				}
				if(start_pos==r &&num_disks%2==1) {
					TowerOfHanoi.toh_without_recursion(num_disks, start_pos, b);
				}
				if(start_pos==b &&num_disks%2==0) {
					TowerOfHanoi.toh_without_recursion(num_disks, start_pos, r);
				}
				for(int i=num_disks-2;i>0;i=i-2) {
					if(i%2==0) {
						TowerOfHanoi.toh_without_recursion(i,r,ine.check(r, b));

						System.out.println(r+" "+b);

						TowerOfHanoi.toh_without_recursion(i, ine.check(r, b), r);
					}else {
						TowerOfHanoi.toh_without_recursion(i,b,ine.check(r, b));

						System.out.println(b+" "+r);

						TowerOfHanoi.toh_without_recursion(i, ine.check(r, b), b);
					}
				}
				if(num_disks%2==0)
					System.out.println(r+" "+b);
			}
		}
	}

}
