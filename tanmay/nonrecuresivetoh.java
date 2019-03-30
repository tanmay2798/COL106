package tanmay;

import java.util.HashMap;
import java.util.Scanner;
import java.util.*;

public class nonrecuresivetoh {

	public nonrecuresivetoh() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws EmptyStackException {
		MyStack <Integer> iObj1 = new MyStack<Integer>();
		MyStack <Integer> iObj2 = new MyStack<Integer>();
		MyStack <Integer> iObj3 = new MyStack<Integer>();
		recursivetoh[] iObj = new recursivetoh[3];
		HashMap< Integer, MyStack <Integer>> hm = new HashMap<Integer, MyStack <Integer>>();
		hm.put(1, iObj1);
		hm.put(2, iObj2);
		hm.put(3, iObj3);
		recursivetoh call = new recursivetoh();

		Scanner s = new Scanner(System.in);
		int n=s.nextInt();
		int start = s.nextInt();
		int end = s.nextInt();
		int aux = call.check(start, end);
		if(n%2==0) {
			int temp = aux;
			aux=end;
			end = temp;
		}
		for(int i=n;i>0;i--) {
			hm.get(start).push(i);
		}


		for(int j=0;j<Math.pow(2,n)-2;j=j+0) {

			try {
				if(hm.get(start).peek()<hm.get(end).peek() ) {
					int num=0;
					num=hm.get(start).peek();
					System.out.println(start+ " "+end);
					hm.get(start).pop();
					hm.get(end).push(num);

				}else {
					int num=0;
					num=hm.get(end).peek();
					hm.get(end).pop();
					hm.get(start).push(num);
					System.out.println(end+ " "+start);
				}
			} catch (EmptyStackException e) {
				if(hm.get(end).empty()==true) {
					int num=0;
					num=hm.get(start).peek();
					hm.get(start).pop();
					hm.get(end).push(num);
					System.out.println(start+ " "+end);
				}else if(hm.get(start).empty()==true) {
					int num=0;
					num=hm.get(end).peek();
					hm.get(end).pop();
					hm.get(start).push(num);
					System.out.println(end+ " "+start);
				}
			}

			j++;

			try {
				//System.out.println("hihi");
				if(hm.get(start).peek()<hm.get(aux).peek()) {
					//	System.out.println("hihi1");
					int num=0;
					num=hm.get(start).peek();
					hm.get(start).pop();
					hm.get(aux).push(num);
					System.out.println(start+ " "+aux);
				}else {
					//	System.out.println("hihi2");
					int num=0;
					num=hm.get(aux).peek();
					hm.get(aux).pop();
					hm.get(start).push(num);
					System.out.println(aux+ " "+start);
				}
			} catch (EmptyStackException e) {
				//System.out.println("hihi3");
				if(hm.get(aux).empty()==true) {
					//System.out.println("hihi4");
					int num=0;
					num=hm.get(start).peek();
					hm.get(start).pop();
					hm.get(aux).push(num);
					System.out.println(start+ " "+aux);
				} else if(hm.get(start).empty()==true) {
					//	System.out.println("hihi5");
					int num=0;
					num=hm.get(aux).peek();
					hm.get(aux).pop();
					hm.get(start).push(num);
					System.out.println(aux+ " "+start);
				}
			}

			j++;
			try {
				if(hm.get(end).peek()<hm.get(aux).peek() ) {
					int num=0;
					num=hm.get(end).peek();
					hm.get(end).pop();
					hm.get(aux).push(num);
					System.out.println(end+ " "+aux);
				}else {
					int num=0;
					num=hm.get(aux).peek();
					hm.get(aux).pop();
					hm.get(end).push(num);
					System.out.println(aux+ " "+end);
				}
			} catch (EmptyStackException e) {
				if(hm.get(aux).empty()==true) {
					int num=0;
					num=hm.get(end).peek();
					hm.get(end).pop();
					hm.get(aux).push(num);
					System.out.println(end+ " "+aux);
				}else if(hm.get(end).empty()==true) {
					int num=0;
					num=hm.get(aux).peek();
					hm.get(aux).pop();
					hm.get(end).push(num);
					System.out.println(aux+ " "+end);
				}
			}
			j++;
			System.out.println(j);
		}
		if (n%2==1){
			try {
				if(hm.get(start).peek()<hm.get(end).peek() ) {
					int num=0;
					num=hm.get(start).peek();
					System.out.println(start+ " "+end);
					hm.get(start).pop();
					hm.get(end).push(num);

				}else {
					int num=0;
					num=hm.get(end).peek();
					hm.get(end).pop();
					hm.get(start).push(num);
					System.out.println(end+ " "+start);
				}
			} catch (EmptyStackException e) {
				if(hm.get(end).empty()==true) {
					int num=0;
					num=hm.get(start).peek();
					hm.get(start).pop();
					hm.get(end).push(num);
					System.out.println(start+ " "+end);
				}else if(hm.get(start).empty()==true) {
					int num=0;
					num=hm.get(end).peek();
					hm.get(end).pop();
					hm.get(start).push(num);
					System.out.println(end+ " "+start);
				}
			}
		}


//		iObj3 = iObj3.adder;
//		while(true){
//			
//			if(iObj3 == null){
//				break;
//			}
//			System.out.println(iObj3.object);
//			iObj3 = iObj3.next;
//		}
	}
	public void tohwithoutrecursion(int numdisks, int startpos, int endpos) throws EmptyStackException {

	}
}
