package tanmay;

import java.util.HashMap;
import java.util.Scanner;

public class TowerOfHanoi {
	
	private static MyStack <Integer> iObj1 = new MyStack<Integer>();
	private static MyStack <Integer> iObj2 = new MyStack<Integer>();
	private static MyStack <Integer> iObj3 = new MyStack<Integer>();
	private static boolean f=true;
	private static HashMap< Integer, MyStack <Integer>> hm = new HashMap<Integer, MyStack <Integer>>();

	private static int num=0;

	public TowerOfHanoi() {
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
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n=s.nextInt();
		int start = s.nextInt();
		int end = s.nextInt();
		
		try {
			TowerOfHanoi.toh_without_recursion(n,start,end);
		} catch (EmptyStackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public static void toh_without_recursion(int num_disks, int start_pos, int end_pos) throws EmptyStackException {
		TowerOfHanoi ina = new TowerOfHanoi();
		if(start_pos==end_pos) {

		}else {
			hm.put(1, iObj1);
			hm.put(2, iObj2);
			hm.put(3, iObj3);
			if(f==true) {
				for(int i=num_disks;i>0;i--) {
					hm.get(start_pos).push(i);
				}
				//System.out.println(f);
				f=false;
			}
			
			int start=start_pos;
			int end=end_pos;
			int aux = ina.check(start, end);
			int auxer=0;
			if(num_disks%2==0) {
				int temp = aux;
				aux=end_pos;
				end_pos = temp;
			}
			for(int j=0;j<Math.pow(2,num_disks)-2;j=j+0) {
				//System.out.println
				if(j%3==0) {
					start=start_pos;
					end=end_pos;
					auxer = ina.check(start, end);
				}
				if(j%3==1) {
					//					System.out.println(start_pos+"hi");
					//					System.out.println(aux+"hi1");
					start=start_pos;
					end=aux;
					auxer = ina.check(start, end);
				}
				if(j%3==2) {

					start=aux;
					end=end_pos;
					auxer = ina.check(start, end);
				}
				try {
					if(hm.get(start).peek()<hm.get(end).peek() ) {
						num=hm.get(start).peek();
						System.out.println(start+ " "+end);
						hm.get(start).pop();
						hm.get(end).push(num);

					}else {
						num=hm.get(end).peek();
						hm.get(end).pop();
						hm.get(start).push(num);
						System.out.println(end+ " "+start);
					}
				} catch (EmptyStackException e) {
					if(hm.get(end).empty()==true) {
						num=hm.get(start).peek();
						hm.get(start).pop();
						hm.get(end).push(num);
						System.out.println(start+ " "+end);
					}else if(hm.get(start).empty()==true) {
						num=hm.get(end).peek();
						hm.get(end).pop();
						hm.get(start).push(num);
						System.out.println(end+ " "+start);
					}
				}
				j++;
			}
			if (num_disks%2==0){
				start=aux;
				end=end_pos;
				auxer=ina.check(start,end);
			}else {
				start=start_pos;
				end=end_pos;
				auxer=ina.check(start,end);
				//System.out.println(start+""+end);
			}
			try {
				if(hm.get(start).peek()<hm.get(end).peek() ) {
					num=hm.get(start).peek();
					System.out.println(start+ " "+end);
					hm.get(start).pop();
					hm.get(end).push(num);

				}else {
					num=hm.get(end).peek();
					hm.get(end).pop();
					hm.get(start).push(num);
					System.out.println(end+ " "+start);
				}
			} catch (EmptyStackException e) {
				if(hm.get(end).empty()==true) {
					//	System.out.println(hm.get(start));
					num=hm.get(start).peek();
					hm.get(start).pop();
					hm.get(end).push(num);
					System.out.println(start+ " "+end);
				}else if(hm.get(start).empty()==true) {
					num=hm.get(end).peek();
					hm.get(end).pop();
					hm.get(start).push(num);
					System.out.println(end+ " "+start);
				}
			}	
		}
	}
}
