package tanmay;

import java.util.Scanner;

public class efficient {

	public efficient() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws EmptyStackException {
		Scanner s = new Scanner(System.in);
		int n=s.nextInt();
		int start = s.nextInt();
		int red = s.nextInt();
		int black = s.nextInt();
		checker in = new checker();
		recursivetoh ine = new recursivetoh();
		for(int i=n;i>0;i--) {
			if(start==1) {
				in.iObj1.push(i);
			}
		}
		gtohwithoutrecursions(n,start,red,black);
	}
	public static void gtohwithoutrecursions(int numdisks, int start, int red,int black) throws EmptyStackException {
		recursivetoh ine = new recursivetoh();
		checker in = new checker();
		
		
		start=start;
		red=red;
		int aux=black;
		System.out.println(aux);
		for(int j=0;j<Math.pow(2,numdisks)-2;j=j+0) {
			
			in.tohwithoutrecursion(1,start,red);
			j++;
			in.tohwithoutrecursion(1,start,aux);
			j++;
			in.tohwithoutrecursion(1,aux,red);
			j++;
		}
		if (numdisks%2==1){
			in.tohwithoutrecursion(1,start,red);
		}
		int temp = aux;
		aux=red;
		red=temp;
		aux = ine.check(black, red);
		
//		temp = red;
//		red = black;
//		black = temp;
		
//		temp =black;
//		black=aux;
//		aux=black;
				
		
		
//		if(numdisks%2==0) {
//			int temp = aux;
//			aux=end[1];
//			end[1] = temp;
//		}
		for(int i=numdisks-2;i>0;i=i-2) {
			//System.out.println(i);
			System.out.println(red+" hi "+aux);
			for(int j=0;j<Math.pow(2,i)-1;j=j+0) {
				
				in.tohwithoutrecursion(1,red,black);
				j++;
				in.tohwithoutrecursion(1,red,aux);
				j++;
				in.tohwithoutrecursion(1,aux,black);
				j++;
			}
			in.tohwithoutrecursion(1,red,black);
			temp=red;
			red=aux;
			aux=temp;
			for(int j=0;j<Math.pow(2,i)-1;j=j+0) {
				
				in.tohwithoutrecursion(1,red,black);
				j++;
				in.tohwithoutrecursion(1,red,aux);
				j++;
				in.tohwithoutrecursion(1,aux,black);
				j++;
			}
			temp=red;
			red=aux;
			aux=temp;
//			if (i%2==0){
//				in.tohwithoutrecursion(1,start,end[1]);
//				int temp=start;
//				start=end[1];
//				end[1]=temp;
//				
//				int tempe = aux;
//				aux=end[1];
//				end[1] = tempe;
//			}else {
//				int tempe = aux;
//				aux=end[1];
//				end[1] = tempe;
//				
//				int temp=start;
//				start=aux;
//				aux=temp;
			//}
		}
		in.tohwithoutrecursion(1,red,black);
	}
}
