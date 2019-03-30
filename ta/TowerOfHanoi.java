package ta;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Scanner;
//import java.util.EmptyStackException;

public class TowerOfHanoi {
	
	//private static MyStack <Integer> iObj1 = new MyStack<Integer>();
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
	
	public static void main(String[] args) throws EmptyStackException {
		Scanner s = new Scanner(System.in);
		int n=s.nextInt();
		int start = s.nextInt();
		int end = s.nextInt();
		s.close();
		
		
			TowerOfHanoi.toh_without_recursion(n,start,end);
			
			
		
	}
	
	
		public int start;
		public int end;
		public int disks;
	

	
	public static void toh_without_recursion(int num_disks, int start_pos, int end_pos) throws EmptyStackException {
		TowerOfHanoi ina = new TowerOfHanoi();
		TowerOfHanoi ina1 = new TowerOfHanoi();
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
						//System.out.println(iObj1.size()+"r");
						
						cur = iObj1.peek();
						iObj1.pop();
						//System.out.println(cur.disks+""+cur.start+""+cur.end);
						//System.out.println("hi");
						
						
						if(cur.disks==1) {
							System.out.println(cur.start+" "+cur.end);
						}else {
							new1.disks=cur.disks-1;
							new2.disks=1;
							new3.disks=(cur.disks)-1;
							new1.start=cur.start;
							new2.start=cur.start;
							new3.start=6-(cur.start+cur.end);
							new1.end=ina.check(cur.start,cur.end);
							new2.end=cur.end;
							new3.end=cur.end;
//							System.out.println(new1.disks+""+new1.start+""+new1.end);
//							System.out.println(new2.disks+""+new2.start+""+new2.end);
//							System.out.println(new3.disks+""+new3.start+""+new3.end);
//							System.out.println("");
							iObj1.push(new3);
							iObj1.push(new2);
							iObj1.push(new1);
							
							
							
						}
				
					}
//					num_disks=num_disks-1;
//					ina1.disks=5;
//					ina1.start=end_pos;
//					ina1.end=start_pos;
//					iObj1.push(ina1);
			//}
//			while() {
//				System.out.print(iObj1.peek()+"");
//				iObj1.pop();
//				System.out.print(iObj1.peek());
//				iObj1.pop();
//				iObj1.pop();
//				System.out.print(iObj1.peek()+"");
//				iObj1.pop();
//				System.out.print(iObj1.peek());
//				
//			}
//			
//			
//			
//			int start=start_pos;
//			int end=end_pos;
//			int aux = ina.check(start, end);
//			int auxer=0;
//			if(num_disks%2==0) {
//				int temp = aux;
//				aux=end_pos;
//				end_pos = temp;
//			}
//			for(int j=0;j<Math.pow(2,num_disks)-2;j=j+0) {
//				if(j%3==0) {
//					start=start_pos;
//					end=end_pos;
//					auxer = ina.check(start, end);
//				}
//				if(j%3==1) {
//					start=start_pos;
//					end=aux;
//					auxer = ina.check(start, end);
//				}
//				if(j%3==2) {
//
//					start=aux;
//					end=end_pos;
//					auxer = ina.check(start, end);
//				}
//				try {
//					if(hm.get(start).peek()<hm.get(end).peek() ) {
//						num=hm.get(start).peek();
//						System.out.println(start+ " "+end);
//						hm.get(start).pop();
//						hm.get(end).push(num);
//
//					}else {
//						num=hm.get(end).peek();
//						hm.get(end).pop();
//						hm.get(start).push(num);
//						System.out.println(end+ " "+start);
//					}
//				} catch (EmptyStackException e) {
//					if(hm.get(end).empty()==true) {
//						num=hm.get(start).peek();
//						hm.get(start).pop();
//						hm.get(end).push(num);
//						System.out.println(start+ " "+end);
//					}else if(hm.get(start).empty()==true) {
//						num=hm.get(end).peek();
//						hm.get(end).pop();
//						hm.get(start).push(num);
//						System.out.println(end+ " "+start);
//					}
//				}
//				j++;
//			}
//			if (num_disks%2==0){
//				start=aux;
//				end=end_pos;
//				auxer=ina.check(start,end);
//			}else {
//				start=start_pos;
//				end=end_pos;
//				auxer=ina.check(start,end);
//				//System.out.println(start+""+end);
//			}
//			try {
//				if(hm.get(start).peek()<hm.get(end).peek() ) {
//					num=hm.get(start).peek();
//					System.out.println(start+ " "+end);
//					hm.get(start).pop();
//					hm.get(end).push(num);
//
//				}else {
//					num=hm.get(end).peek();
//					hm.get(end).pop();
//					hm.get(start).push(num);
//					System.out.println(end+ " "+start);
//				}
//			} catch (EmptyStackException e) {
//				if(hm.get(end).empty()==true) {
//					//	System.out.println(hm.get(start));
//					num=hm.get(start).peek();
//					hm.get(start).pop();
//					hm.get(end).push(num);
//					System.out.println(start+ " "+end);
//				}else if(hm.get(start).empty()==true) {
//					num=hm.get(end).peek();
//					hm.get(end).pop();
//					hm.get(start).push(num);
//					System.out.println(end+ " "+start);
//				}
//			}	
//			iObj1 = iObj1.adder;
//			iObj2 = iObj2.adder;
//			iObj3 = iObj3.adder;
//			while(true){
//				
//				if(iObj1 == null){
//					System.out.println("emp1");
//					break;
//				}
//				System.out.println(iObj1.object);
//				iObj1 = iObj1.next;
//			}
//			while(true){
//				if(iObj2 == null){
//					System.out.println("emp2");
//					break;
//				}
//				System.out.println(iObj2.object);
//				iObj2 = iObj2.next;
//			}
//			while(true){
//				if(iObj3 == null){
//					System.out.println("emp3");
//					break;
//				}
//				System.out.println(iObj3.object);
//				iObj3 = iObj3.next;
//			}
//			iObj1 = iObj1.adder;
//			while(true){
//				
//				if(iObj1 == null){
//					System.out.println("emp1");
//					break;
//				}
//				System.out.println(iObj1.object.disks);
//				System.out.println(iObj1.object.start);
//				System.out.println(iObj1.object.end);
//				System.out.println("");
//				iObj1 = iObj1.next;
//			}
		}
	}
}
