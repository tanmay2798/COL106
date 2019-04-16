//package Assignment5;

import java.util.ArrayList;

public class Sort { 
//	public static void main(String [] args) {
//		ArrayList <String> str = new ArrayList<String>();
//		str.add("10 00");
//		str.add("60 89");
//		str.add("15");
//		str.add("20");
//		str.add("50");
//		str.add("40");
//
//	}
	 
	 ArrayList<String> sort(ArrayList<String> arr) { 
		int n = arr.size(); 
		int minimum=0;
		
		for (int i = 0; i < n-1; i++) { 
			minimum = i; 
			for (int j = i+1; j < n; j++) {
				String str[] = arr.get(j).split("\\s+");
				String str1[] = arr.get(minimum).split("\\s+");

				if ( Integer.parseInt(str[0])< Integer.parseInt(str1[0])) 
					minimum = j; 
				else if(Integer.parseInt(str[0])==Integer.parseInt(str1[0]) && Integer.parseInt(str[1])<Integer.parseInt(str1[1]))
					minimum = j; 
			}
			String temp = arr.get(minimum); 
			arr.set(minimum,arr.get(i)); 
			arr.set(i,temp); 
		} 
		return arr;
	} 
}