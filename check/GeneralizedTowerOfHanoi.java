package check;

import java.util.Scanner;

public class GeneralizedTowerOfHanoi {
	private int start;
	private int end;
	private int disks;
	
	private static boolean f =false;
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
				GeneralizedTowerOfHanoi.toh_with_recursion(num_disks, start_pos, r);
			}else {
				if(start_pos!=r && start_pos!=b && f==false) {
					if(num_disks%2==0)
						GeneralizedTowerOfHanoi.toh_with_recursion(num_disks,start_pos,r);
					else
						GeneralizedTowerOfHanoi.toh_with_recursion(num_disks, start_pos, b);
					num_disks=num_disks-2;
					f=true;
				}

				if(start_pos==b && f==false && num_disks%2==0) {
					GeneralizedTowerOfHanoi.toh_with_recursion(num_disks,start_pos,r);
					num_disks=num_disks-2;
					f=true;
				}else if(start_pos==r && f==false && num_disks%2==1) {
					GeneralizedTowerOfHanoi.toh_with_recursion(num_disks,start_pos,b);
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
				}else if(num_disks>0){

					if(num_disks%2==0) {
						GeneralizedTowerOfHanoi.toh_with_recursion(num_disks,r,ine.check(b,r));
						System.out.println(r+" "+b);
						GeneralizedTowerOfHanoi.toh_with_recursion(num_disks,ine.check(b,r),r);
					}else {
						GeneralizedTowerOfHanoi.toh_with_recursion(num_disks,b,ine.check(b,r));
						System.out.println(b+" "+r);
						GeneralizedTowerOfHanoi.toh_with_recursion(num_disks,ine.check(b,r),b);
					}
					GeneralizedTowerOfHanoi.gtoh_with_recursion(num_disks-2,start_pos,r,b);
				}
			}
		}
	}
	
	public static void toh_with_recursion(int num_disks, int start_pos, int end_pos) {
		GeneralizedTowerOfHanoi ina = new GeneralizedTowerOfHanoi();
		if(start_pos==end_pos) {

		}else {
			if(num_disks==1) {
				System.out.println(start_pos+" "+end_pos);
			}else {
				

				GeneralizedTowerOfHanoi.toh_with_recursion(num_disks-1,start_pos,ina.check(start_pos,end_pos));
				GeneralizedTowerOfHanoi.toh_with_recursion(1,start_pos,end_pos);
				GeneralizedTowerOfHanoi.toh_with_recursion(num_disks-1,ina.check(start_pos,end_pos),end_pos);	
			}
		}
	}
	
	public static void main(String[] args) throws EmptyStackException {
		Scanner s = new Scanner(System.in);
		int n=s.nextInt();
		int start = s.nextInt();
		int r = s.nextInt();
		int b = s.nextInt();
		s.close();
		GeneralizedTowerOfHanoi.gtoh_without_recursion(n,start,r,b);
			
			
		
	}
	
	public static void gtoh_without_recursion(int num_disks, int start_pos, int r,int b) throws EmptyStackException {
		if(start_pos==r && start_pos==b) {

		}else {
			if(r==b) {
				GeneralizedTowerOfHanoi.toh_without_recursion(num_disks, start_pos, r);
			}else {
				if(start_pos!=r && start_pos!=b) {
					if(num_disks%2==0)
						GeneralizedTowerOfHanoi.toh_without_recursion(num_disks, start_pos, r);
					else
						GeneralizedTowerOfHanoi.toh_without_recursion(num_disks, start_pos, b);
				}
				if(start_pos==r &&num_disks%2==1) {
					GeneralizedTowerOfHanoi.toh_without_recursion(num_disks, start_pos, b);
				}else if(start_pos==r){
					GeneralizedTowerOfHanoi.toh_without_recursion(num_disks,start_pos,start_pos);
				}
				if(start_pos==b &&num_disks%2==0) {
					GeneralizedTowerOfHanoi.toh_without_recursion(num_disks, start_pos, r);
				}else if(start_pos==b){
					GeneralizedTowerOfHanoi.toh_without_recursion(num_disks,start_pos,start_pos);
				}
				
				for(int i=num_disks-2;i>0;i=i-2) {
					if(i%2==0) {
						GeneralizedTowerOfHanoi.toh_without_recursion(i,r,ine.check(r, b));
						
						GeneralizedTowerOfHanoi.toh_without_recursion(1, r, b);

						GeneralizedTowerOfHanoi.toh_without_recursion(i, ine.check(r, b), r);
					}else {
						GeneralizedTowerOfHanoi.toh_without_recursion(i,b,ine.check(r, b));

						GeneralizedTowerOfHanoi.toh_without_recursion(1, b, r);

						GeneralizedTowerOfHanoi.toh_without_recursion(i, ine.check(r, b), b);
					}
				}
				if(num_disks%2==0)
					GeneralizedTowerOfHanoi.toh_without_recursion(1, r, b);
			}
		}
		
	}
	
	public static void toh_without_recursion(int num_disks, int start_pos, int end_pos) throws EmptyStackException {
		GeneralizedTowerOfHanoi ina = new GeneralizedTowerOfHanoi();
		if(start_pos==end_pos) {

		}else {
			MyStack <GeneralizedTowerOfHanoi> iObj1 = new MyStack<GeneralizedTowerOfHanoi>();
			GeneralizedTowerOfHanoi cur = new GeneralizedTowerOfHanoi();
			cur.disks=num_disks;
			cur.start=start_pos;
			cur.end=end_pos;
			iObj1.push(cur);
			while(!iObj1.empty()) {
				GeneralizedTowerOfHanoi new1 = new GeneralizedTowerOfHanoi();
				GeneralizedTowerOfHanoi new2 = new GeneralizedTowerOfHanoi();
				GeneralizedTowerOfHanoi new3 = new GeneralizedTowerOfHanoi();
				cur = iObj1.peek();
				iObj1.pop();

				if(cur.disks==1) {
					System.out.println(cur.start+" "+cur.end);
				}else {
					new1.disks=cur.disks-1;
					new2.disks=1;
					new3.disks=cur.disks-1;
					new1.start=cur.start;
					new2.start=cur.start;
					new3.start=ina.check(cur.start,cur.end);
					new1.end=ina.check(cur.start,cur.end);
					new2.end=cur.end;
					new3.end=cur.end;
					iObj1.push(new3);
					iObj1.push(new2);
					iObj1.push(new1);
				}
			}
		}
	}

}
