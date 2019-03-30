package ass4;

import java.util.ArrayList;
import java.util.HashMap;

public class check {

	public static HashMap<String,Integer> hm = new HashMap<>();
	public static ArrayList<String> arr = new ArrayList<>(5);
	
	public static void main(String [] args) {
		//System.out.println("a");
		for(int i=0;i<10;i++) {
			arr.add(i,"");
		}
//		for(int i=97;i<102;i++) {
//			
//			int i1 = getindex(getkey(String.valueOf((char)i)));
//
//			arr.set(i1, String.valueOf((char)i));
//
//		}

		char i='a';
		System.out.println(i);
	}
	
	public static int getkey(String s) {
		if(s.length()==1)
			return getindex((int)s.charAt(0));
		else {
			int sum=0;
			for(int i=0;i<s.length();i++) {
				sum=sum+(int)(s.charAt(i));
			}
			return getindex(sum);
		}
			
	}
	
	public static int getindex(int s) {
		//int i = Integer.parseInt(s);
		return s%7;
		
	}
	public static String getvalue(int s) {
		if(!arr.get(s).equals("")){
			return arr.get(s);
		}else
			return "";
		
	}
	
	public static void Compressed(String str) {
		String new1="";String old1="";
		
		for(int k=0;k<str.length();k++) {
			boolean check=true;
			old1= (old1+str.charAt(k));
			System.out.println(old1);
			if(getvalue(getkey(old1)).equals("")) {
				arr.set(getkey(old1),old1);
				System.out.println(arr.get(getkey(old1))+getkey(old1));
				old1=String.valueOf(old1.charAt(old1.length()-1));
			}else if(!getvalue(getkey(old1)).equals(old1)){
				int j=1;
				for(int i=getkey(old1);i<arr.size();i++) {
					if(arr.get(i).equals("")) {
						arr.set(i, old1);						
						old1=String.valueOf(old1.charAt(old1.length()-1));	
						System.out.println(old1+"hh");
						break;
					}
					//i=i+j*j;	
					if(i>=9 && check==true) {
						check=false;
						i=0;
					}
					
				}
			}
				
		}
		
//		for(int i=0;i<str.length();i++) {
//			old1= (old1+str.charAt(i));
//			if(hm.get(old1)==null || !hm.containsKey(old1)) {
//				hm.put(old1,hm.size()+1);
//				System.out.println(hm.get(old1)+old1);
//				old1=String.valueOf(old1.charAt(old1.length()-1));
//				
//			}
//			else {
//				
//			}
//				
//		}
		//
	}

}
