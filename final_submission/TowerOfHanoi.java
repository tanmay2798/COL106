package final_submission;

import java.util.Scanner;

public class TowerOfHanoi {

	private int start;
	private int end;
	private int disks;
	private TowerOfHanoi() {
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

	public static void toh_with_recursion(int num_disks, int start_pos, int end_pos) {
		if(start_pos==end_pos) {

		}else {
			if(num_disks==1) {
				System.out.println(start_pos+" "+end_pos);
			}else {
				TowerOfHanoi ina = new TowerOfHanoi();
				TowerOfHanoi.toh_with_recursion(num_disks-1,start_pos,ina.check(start_pos,end_pos));
				TowerOfHanoi.toh_with_recursion(1,start_pos,end_pos);
				TowerOfHanoi.toh_with_recursion(num_disks-1,ina.check(start_pos,end_pos),end_pos);	
			}
		}
	}

	public static void main(String[] args) throws EmptyStackException  {
		Scanner s = new Scanner(System.in);
		int n=s.nextInt();
		int start = s.nextInt();
		int end = s.nextInt();
		s.close();
//		try {
		TowerOfHanoi.toh_without_recursion(n,start,end);
//		}catch(EmptyStackException e) {
//			
//		}
	}

	public static void toh_without_recursion(int num_disks, int start_pos, int end_pos) throws EmptyStackException {
		TowerOfHanoi ina = new TowerOfHanoi();
		if(start_pos==end_pos) {

		}else {
			MyStack <TowerOfHanoi> iObj1 = new MyStack<TowerOfHanoi>();
			TowerOfHanoi cur = new TowerOfHanoi();
			cur.disks=num_disks;
			cur.start=start_pos;
			cur.end=end_pos;
			iObj1.push(cur);
			while(!iObj1.empty()) {
				TowerOfHanoi new1 = new TowerOfHanoi();
				TowerOfHanoi new2 = new TowerOfHanoi();
				TowerOfHanoi new3 = new TowerOfHanoi();
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
