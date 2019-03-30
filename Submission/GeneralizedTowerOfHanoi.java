package Submission;

import java.util.HashMap;
import java.util.Scanner;

public class GeneralizedTowerOfHanoi {
	private static boolean f =false;
	private static GeneralizedTowerOfHanoi ine = new GeneralizedTowerOfHanoi();
	private static GeneralizedTowerOfHanoi inew = new GeneralizedTowerOfHanoi();

	private static MyStack <Integer> iObj1 = new MyStack<Integer>();
	private static MyStack <Integer> iObj2 = new MyStack<Integer>();
	private static MyStack <Integer> iObj3 = new MyStack<Integer>();
	private static boolean g=true;
	private static HashMap< Integer, MyStack <Integer>> hm = new HashMap<Integer, MyStack <Integer>>();

	private static int num=0;
	
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
				}else {

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
		
			GeneralizedTowerOfHanoi.gtoh_with_recursion(n,start,r,b);
			System.out.println("hi");
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
			inew.iObj1 = inew.iObj1.adder;
			inew.iObj2 = inew.iObj2.adder;
			inew.iObj3 = inew.iObj3.adder;
			while(true){
				
				if(inew.iObj1 == null){
					System.out.println("emp1");
					break;
				}
				System.out.println(inew.iObj1.object);
				inew.iObj1 = inew.iObj1.next;
			}
			while(true){
				if(inew.iObj2 == null){
					System.out.println("emp2");
					break;
				}
				System.out.println(inew.iObj2.object);
				inew.iObj2 = inew.iObj2.next;
			}
			while(true){
				if(inew.iObj3 == null){
					System.out.println("emp3");
					break;
				}
				System.out.println(inew.iObj3.object);
				inew.iObj3 = inew.iObj3.next;
			}
		}
		
	}
	
	public static void toh_without_recursion(int num_disks, int start_pos, int end_pos) throws EmptyStackException {
		GeneralizedTowerOfHanoi ina = new GeneralizedTowerOfHanoi();
		hm.put(1, iObj1);
		hm.put(2, iObj2);
		hm.put(3, iObj3);
		if(g==true) {
		for(int i=num_disks;i>0;i--) {
			hm.get(start_pos).push(i);
		}
		g=false;
	}
		if(start_pos==end_pos) {
			
		}else {
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
		}
	}

}
