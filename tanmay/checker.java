package tanmay;

import java.util.HashMap;
import java.util.Scanner;

public class checker {

	private static MyStack <Integer> iObj1 = new MyStack<Integer>();
	private static MyStack <Integer> iObj2 = new MyStack<Integer>();
	private static MyStack <Integer> iObj3 = new MyStack<Integer>();
	private static boolean f=true;
	private static HashMap< Integer, MyStack <Integer>> hm = new HashMap<Integer, MyStack <Integer>>();

	private static int num=0;
	public checker() {
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
		hm.put(1, iObj1);
		hm.put(2, iObj2);
		hm.put(3, iObj3);
		checker call= new checker();
		Scanner s = new Scanner(System.in);
		int n=s.nextInt();
		int start = s.nextInt();
		int end = s.nextInt();
		s.close();
		//		for(int i=n;i>0;i--) {
		//			hm.get(start).push(i);
		//		}
		call.tohwithoutrecursion(n,start,end);
	}
	public void tohwithoutrecursion(int numdisks, int starter, int ender) throws EmptyStackException {

		if(starter==ender) {

		}else {
			hm.put(1, iObj1);
			hm.put(2, iObj2);
			hm.put(3, iObj3);
			if(f==true) {
				for(int i=numdisks;i>0;i--) {
					hm.get(starter).push(i);
				}
				//System.out.println(f);
				f=false;
			}
			checker call= new checker();
			int start=starter;
			int end=ender;
			int aux = call.check(start, end);
			int auxer=0;
			if(numdisks%2==0) {
				int temp = aux;
				aux=ender;
				ender = temp;
			}
			for(int j=0;j<Math.pow(2,numdisks)-2;j=j+0) {
				//System.out.println
				if(j%3==0) {
					start=starter;
					end=ender;
					auxer = call.check(start, end);
				}
				if(j%3==1) {
					//					System.out.println(starter+"hi");
					//					System.out.println(aux+"hi1");
					start=starter;
					end=aux;
					auxer = call.check(start, end);
				}
				if(j%3==2) {

					start=aux;
					end=ender;
					auxer = call.check(start, end);
				}
				if(hm.get(end).empty()==false && hm.get(start).empty()==false ) {
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
				} else {
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
			if (numdisks%2==0){
				start=aux;
				end=ender;
				auxer=call.check(start,end);
			}else {
				start=starter;
				end=ender;
				auxer=call.check(start,end);
				//System.out.println(start+""+end);
			}
			if(hm.get(end).empty()==false && hm.get(start).empty()==false ) {
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
			} else {
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
		}
	}
}
