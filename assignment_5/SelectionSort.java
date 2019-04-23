//package assignment_5;

import java.util.ArrayList;

class SelectionSort 
{ 
	SelectionSort (){
		
	}
	public static void main(String [] args) {
		ArrayList <String> str = new ArrayList<String>();
		str.add("10 00");
		str.add("60 89");
		str.add("15");
		str.add("20");
		str.add("50");
		str.add("40");
		//System.out.println(sort(str));
	}
	 
	 ArrayList<String> sort(ArrayList<String> arr) 
	{ 
		int n = arr.size(); 
		int min_idx=0;
		// One by one move boundary of unsorted subarray 
		for (int i = 0; i < n-1; i++) 
		{ 
			// Find the minimum element in unsorted array 
			min_idx = i; 
			for (int j = i+1; j < n; j++) {
				String str[] = arr.get(j).split("\\s+");
				String str1[] = arr.get(min_idx).split("\\s+");
				//System.out.println(str[1]+" "+str1[1]);
				if ( Integer.parseInt(str[0])< Integer.parseInt(str1[0])) 
					min_idx = j; 
				else if(Integer.parseInt(str[0])==Integer.parseInt(str1[0]) && Integer.parseInt(str[1])<Integer.parseInt(str1[1]))
					min_idx = j; 
			}
			// Swap the found minimum element with the first 
			// element 
			String temp = arr.get(min_idx); 
			arr.set(min_idx,arr.get(i)); 
			arr.set(i,temp); 
			//}
		} 
		return arr;
	} 
}