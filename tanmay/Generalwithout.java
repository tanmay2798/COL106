package tanmay;

import java.util.Scanner;

public class Generalwithout {

	public Generalwithout() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws EmptyStackException {
		Scanner s = new Scanner(System.in);
		int n=s.nextInt();
		int start = s.nextInt();
		int end = s.nextInt();
		checker in = new checker();
		recursivetoh ine = new recursivetoh();
		
		int[]arr = new int[n];
		
		for(int i=n;i>0;i--) {
			if(start==1) {
				in.iObj1.push(i);
			}
			if(start==2) {
				in.iObj2.push(i);
			}
			if(start==3) {
				in.iObj3.push(i);
			}
		}
		for(int k=0;k<n;k++) {
			if(k%2==0) {
				arr[k]=start;
			}else {
				arr[k]=end;
			}
		}
		gtohwithoutrecursion(n,start,arr);
	}
	public static void gtohwithoutrecursion(int numdisks, int start, int[] end) throws EmptyStackException {
		recursivetoh ine = new recursivetoh();
		checker in = new checker();
		int aux = ine.check(start, end[1]);
		if(numdisks%2==1) {
			int temp = aux;
			aux=end[1];
			end[1] = temp;
		}
		int k=0;
		for(int i=numdisks-1;i>0;i--) {
			//System.out.println(i);
			//System.out.println(start+" hi "+end[1]);
			for(int j=0;j<Math.pow(2,i)-2;j=j+0) {
				
				in.tohwithoutrecursion(1,start,end[1]);
				j++;
				in.tohwithoutrecursion(1,start,aux);
				j++;
				in.tohwithoutrecursion(1,aux,end[1]);
				j++;
			}
			if (i%2==1){
				in.tohwithoutrecursion(1,start,end[1]);
				int temp=start;
				start=end[1];
				end[1]=temp;
				
				int tempe = aux;
				aux=end[1];
				end[1] = tempe;
			}else {
				int tempe = aux;
				aux=end[1];
				end[1] = tempe;
				
				int temp=start;
				start=end[1];
				end[1]=temp;
			}
		}
	}
}
