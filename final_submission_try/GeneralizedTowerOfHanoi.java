package final_submission_try;

import java.util.EmptyStackException;
import java.util.Scanner;

public class GeneralizedTowerOfHanoi {
	private int start;
	private int end;
	private int disks;
	
	private static boolean f =false;
	private static GeneralizedTowerOfHanoi ine = new GeneralizedTowerOfHanoi();
	
	
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
			}else if(num_disks==1 && f==false){
				System.out.println(start_pos+" "+b);
			}else{
				if(start_pos!=r && start_pos!=b && f==false) {
					if(num_disks%2==0) {
						GeneralizedTowerOfHanoi.toh_with_recursion(num_disks-1,start_pos,b);
						GeneralizedTowerOfHanoi.toh_with_recursion(1,start_pos,r);
					}else {
						GeneralizedTowerOfHanoi.toh_with_recursion(num_disks-1, start_pos, r);
						GeneralizedTowerOfHanoi.toh_with_recursion(1, start_pos, b);
					}
					num_disks=num_disks-3;
					f=true;
				}

				if(start_pos==b && f==false && num_disks%2==0) {
					GeneralizedTowerOfHanoi.toh_with_recursion(num_disks-1,start_pos,ine.check(r, b));
					GeneralizedTowerOfHanoi.toh_with_recursion(1,start_pos,r);
					GeneralizedTowerOfHanoi.toh_with_recursion(num_disks-1,ine.check(r, b),start_pos);
					num_disks=num_disks-3;
					f=true;
				}else if(start_pos==r && f==false && num_disks%2==1) {
					GeneralizedTowerOfHanoi.toh_with_recursion(num_disks-1,start_pos,ine.check(r, b));
					GeneralizedTowerOfHanoi.toh_with_recursion(1,start_pos,b);
					GeneralizedTowerOfHanoi.toh_with_recursion(num_disks-1,ine.check(r, b),start_pos);
					num_disks=num_disks-3;
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
	
	private static void toh_with_recursion(int num_disks, int start_pos, int end_pos) {
		if(start_pos==end_pos) {

		}else {
			if(num_disks==1) {
				System.out.println(start_pos+" "+end_pos);
			}else {
				GeneralizedTowerOfHanoi.toh_with_recursion(num_disks-1,start_pos,ine.check(start_pos,end_pos));
				GeneralizedTowerOfHanoi.toh_with_recursion(1,start_pos,end_pos);
				GeneralizedTowerOfHanoi.toh_with_recursion(num_disks-1,ine.check(start_pos,end_pos),end_pos);	
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n=s.nextInt();
		int start = s.nextInt();
		int r = s.nextInt();
		int b = s.nextInt();
		s.close();
		GeneralizedTowerOfHanoi.gtoh_with_recursion(n,start,r,b);
		
	}
	
	public static void gtoh_without_recursion(int num_disks, int start_pos, int r,int b)  {
		GeneralizedTowerOfHanoi cur = new GeneralizedTowerOfHanoi();
		GeneralizedTowerOfHanoi cur2 = new GeneralizedTowerOfHanoi();
		GeneralizedTowerOfHanoi cur3 = new GeneralizedTowerOfHanoi();
		GeneralizedTowerOfHanoi move = new GeneralizedTowerOfHanoi();
		MyStack <GeneralizedTowerOfHanoi> iObj1 = new MyStack<GeneralizedTowerOfHanoi>();
		if(start_pos==r && start_pos==b) {

		}else {
			if(r==b) {
				cur.disks=num_disks;
				cur.start=start_pos;
				cur.end=r;
				iObj1.push(cur);				
				try {
					move=iObj1.peek();
					iObj1.pop();			
				}catch(EmptyStackException e) {
					System.out.println(e);
				}
				GeneralizedTowerOfHanoi.toh_without_recursion(move.disks, move.start, move.end);
			}else if(num_disks==1) {
				System.out.println(start_pos+" "+b);
			}
			else{
				if(start_pos!=r && start_pos!=b) {
					if(num_disks%2==0) {
						cur.disks=num_disks-1;
						cur.start=start_pos;
						cur.end=b;
						cur2.disks=1;
						cur2.start=start_pos;
						cur2.end=r;
						iObj1.push(cur2);
						iObj1.push(cur);
					}
					else {
						cur.disks=num_disks-1;
						cur.start=start_pos;
						cur.end=r;
						cur2.disks=1;
						cur2.start=start_pos;
						cur2.end=b;
						iObj1.push(cur2);
						iObj1.push(cur);
					}
					num_disks=num_disks-1;
				}
				if(start_pos==r &&num_disks%2==1) {
				
					cur.disks=num_disks-1;
					cur.start=start_pos;
					cur.end=ine.check(r,b);
					cur2.disks=1;
					cur2.start=start_pos;
					cur2.end=b;
					cur3.disks=num_disks-1;
					cur3.start=ine.check(r,b);
					cur3.end=start_pos;
					iObj1.push(cur3);
					iObj1.push(cur2);
					iObj1.push(cur);
					num_disks=num_disks-1;
				}
				if(start_pos==b &&num_disks%2==0) {
					cur.disks=num_disks-1;
					cur.start=start_pos;
					cur.end=ine.check(r, b);
					cur2.disks=1;
					cur2.start=start_pos;
					cur2.end=r;
					cur3.disks=num_disks-1;
					cur3.start=ine.check(r,b);
					cur3.end=start_pos;
					iObj1.push(cur3);
					iObj1.push(cur2);
					iObj1.push(cur);
					num_disks=num_disks-1;	
				}
				while(!iObj1.empty()) {
					try {
						move=iObj1.peek();
						GeneralizedTowerOfHanoi.toh_without_recursion(move.disks, move.start, move.end);
						iObj1.pop();
					}catch(EmptyStackException e) {
						System.out.println(e);
						break;
					}
				}
				
				
				for(int i=num_disks-2;i>0;i=i-2) {
					GeneralizedTowerOfHanoi new1 = new GeneralizedTowerOfHanoi();
					GeneralizedTowerOfHanoi new2 = new GeneralizedTowerOfHanoi();
					GeneralizedTowerOfHanoi new3 = new GeneralizedTowerOfHanoi();
					if(i%2==0) {						
						new1.disks=i;
						new1.start=r;
						new1.end=ine.check(r, b);						
						new2.disks=1;
						new2.start=r;
						new2.end=b;						
						new3.disks=i;
						new3.start=ine.check(r, b);
						new3.end=r;
						iObj1.push(new3);
						iObj1.push(new2);
						iObj1.push(new1);						
					}else {
						new1.disks=i;
						new1.start=b;
						new1.end=ine.check(r, b);		
						new2.disks=1;
						new2.start=b;
						new2.end=r;				
						new3.disks=i;
						new3.start=ine.check(r, b);
						new3.end=b;
						iObj1.push(new3);
						iObj1.push(new2);
						iObj1.push(new1);
					}
					while(!iObj1.empty()) {
						try {
							move=iObj1.peek();
							GeneralizedTowerOfHanoi.toh_without_recursion(move.disks, move.start, move.end);
							iObj1.pop();
						}catch(EmptyStackException e) {
							System.out.println(e);
							break;
						}
					}
				}
				if(num_disks%2==0)
					System.out.println(r+" "+b);
			}
		}
		
	}
	
	private static void toh_without_recursion(int num_disks, int start_pos, int end_pos) {
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
				try {
					cur = iObj1.peek();
					iObj1.pop();
				}catch(EmptyStackException e) {
					System.out.println(e);
					break;
				}
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
