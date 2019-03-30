package ass4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class CompressCheck {

	public static HashMap<String,Integer> hm = new HashMap<>();
	public static ArrayList<String> arr = new ArrayList<>();
	public static ArrayList<String> arr1 = new ArrayList<String>();
	public static ArrayList<String> arr3 = new ArrayList<String>();
	public static ArrayList<String> arr2 = new ArrayList<>();
	public static long to;
	
	public static void main(String [] args) {
		//System.out.println("a");
		for(int i=0;i<1000003;i++) {
			arr.add(i,"");
		//	arr1.add(i,"");
		}
		for(int i=0;i<128;i++) {
			arr.set(i,(char)i+"");
			//arr1.add(i,"");
		}
	//	System.out.println(arr);
	//	for(int i=0;i<256;i++) {
			
//			if(getvalue(getkey(String.valueOf((char)i))).equals("")) {
//				arr.set(getkey(String.valueOf((char)i)),String.valueOf((char)i));
//			}else if(!getvalue(getkey(String.valueOf((char)i))).equals(String.valueOf((char)i))){
//				for(int k=getkey(String.valueOf((char)i));k<arr.size();k++) {
//					if(arr.get(k).equals("")) {
//						arr.set(k, String.valueOf((char)i));						
//						break;
//					}
//				
//					if(i==arr.size()-1 && check==true) {
//						check=false;
//						i=0;
//					}
//					if(i==arr.size()-1 && check==false) {
//						arr.add(old1);
//						old1=String.valueOf(old1.charAt(old1.length()-1));	
//						check=true;
//						break;
//					}
					
			//	}
		//	}
			//arr.set(i1, String.valueOf((char)i));

	//	}
		//System.out.println(arr.size());
		try {
			//FileInputStream fstream1 =new FileInputStream(args[0]);
			FileInputStream fstream1 =new FileInputStream("/Users/tanmaygoyal/eclipse-workspace/tanmay/src/ass4/new_large-3.txt");
			
			Scanner s2 = new Scanner(fstream1);
			String str="";
			int i=0;
			while(s2.hasNext()) {
				str=str+s2.next();
				//System.out.println(i++);
			}
			//System.out.println(str);
			Compressed(str);
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
//		catch(IndexOutOfBoundsException e) {
//			CompressCheck c = new CompressCheck();
//			System.out.println(c.to+"hi");
//		}
		
		//System.out.println("");
		for(int i=0;i<arr2.size();i++)
			System.out.print(Integer.parseInt(arr2.get(i),2));
		
		//System.out.println(arr2.size()+"jjttyy");
		//decompressed(args);
		int g=0;
		for(int h=0;h<arr.size();h++) {
			if(!arr.get(h).equals(""))
				g++;
		}
		System.out.println(g);
	}
	
	public static int getkey(String s) {
		if(s.length()==1)
			return getindex(((long)s.charAt(0)));
		else {
			long sum=1;
			for(int i=0;i<s.length();i++) {
				sum=sum*(int)(s.charAt(i));
			}
			//System.out.println(sum/10+"poop");
			return getindex(sum);
		}
			
	}
	
	public static int getkeyf(String s) {
		if(s.length()==1)
			return getindex(((long)s.charAt(0)),s);
		else {
			long sum=1;
			for(int i=0;i<s.length();i++) {
				sum=sum*(int)(s.charAt(i));
			}
			return getindex(sum,s);
		}
			
	}
	
	public static int getindex(long s,String str) {
		boolean check1 = true;
		long j=0;long k =(int)(s%arr.size());
		if(arr.get((int)(s%arr.size())).equals(str))
			return (int)(s%arr.size());
		else {
			for(int i=0;i<arr.size();i++) {
				j=(k+i*i)%arr.size();
				if(arr.get((int)j).equals(str))
					return (int)j;
				
//				if(i==arr.size()-1 && check1==true) {
//					check1=false;
//					i=0;
//				}
				
			}
		}
		System.out.println(str+"65");
			return 0;	
		
	}
	
	public static int getindex(long s) {	
		//System.out.println(s+"po");
			return (int)(s%arr.size());
		
	}
	public static String getvalue(int s) {
		//System.out.println(s);
		if(!arr.get(s).equals("")){
			return arr.get(s);
		}else
			return "";
		
	}
	
	
	public static void Compressed(String str) {
		String new1="";String old1="";
		
		for(int k=0;k<str.length();k++) {
			boolean check=false;
			old1=str.charAt(k)+"";
			//System.out.println(str.charAt(k)+"j");
			if(getvalue(getkey(new1+old1)).equals(new1+old1)) {
				//System.out.println(new1+" ga  "+old1);
				new1=new1+old1;
				//System.out.println(new1+" fa  "+old1);
			}
			else if(getvalue(getkey(new1+old1)).equals("")) {
				arr.set(getkey(new1+old1),new1+old1);
				//System.out.println(getkey(new1+old1)+"tan");
				if(arr2.size()<1048576)
					arr2.add(Integer.toBinaryString(getkeyf(new1)));
				System.out.println(new1+old1+" "+getkeyf(new1+old1));
				new1=old1;	
				
			}else {
				long j=0;long l = getkey(new1+old1);
				for(int i=0;i<arr.size();i++) {
					j=(l+i*i)%arr.size();
					
					if(arr.get((int)j).equals(new1+old1)) {
						to=j;
						new1=new1+old1;
						check=true;
						break;
					}
					if(arr.get((int)j).equals("")) {
						arr.set((int)j, new1+old1);		
						System.out.println(new1+old1+" "+j);
						if(arr2.size()<1048576)
							arr2.add(Integer.toBinaryString(getkeyf(new1)));
						check=true;
						new1=old1;							
						break;
					}					
				}
				if(check==false) {
//					arr.add(new1+old1);
//					System.out.println(new1+old1+" "+arr.indexOf(new1+old1));
//					if(arr2.size()<1048576)
//						arr2.add(Integer.toBinaryString(getkeyf(new1)));
//					new1=old1;	
					System.out.println("tanmay");
				}
			}			
			
		}
		if(!new1.equals("")) {
			if(arr2.size()<1048576)
				arr2.add(Integer.toBinaryString(getkeyf(new1)));
		}
		
	}

	public static void decompressed(String [] args){
				try {
			//FileInputStream fstream =new FileInputStream(args[1]);
			FileInputStream fstream =new FileInputStream("/Users/tanmaygoyal/eclipse-workspace/tanmay/src/ass4/two.txt");
			
			Scanner s1 = new Scanner(fstream);
			//Scanner s2 = new Scanner(fstream1);
			
			while(s1.hasNextLine()) {
				System.out.print(arr.get(Integer.parseInt(s1.nextLine(),2)));
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
