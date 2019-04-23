//package ass4;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;



public class Decompress {

	//public static HashMap<String,Integer> hm = new HashMap<>();
	public static ArrayList<String> arr = new ArrayList<>();
	public static ArrayList<String> arr1 = new ArrayList<String>();
	public static ArrayList<String> arr3 = new ArrayList<String>();
	public static int dictionary=-1;
	
	//public static ArrayList<String> arr2 = new ArrayList<>();

	public static void main(String [] args) {
		//System.out.println("a");
		for(int i=0;i<Math.pow(2,19)-1;i++) {
			arr.add(i,"");
			//	arr1.add(i,"");
		}
		for(int i=0;i<128;i++) {
			arr.set(i,(char)i+"");
			dictionary++;
		}
		try {
			File file = new File(args[1]);
			FileOutputStream fos;
			fos = new FileOutputStream(file);
			PrintStream ps = new PrintStream(fos);
			System.setOut(ps);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			byte[] a = Files.readAllBytes(Paths.get(args[0]));
			for(int h=0;h<a.length;) {

				//long k = (((a[h++]) << 8) | (a[h++] & 0xff)); 
				long k = (((a[h++]) << 8));
				k|=(a[h++] & 0xff); 

				if(k<0)
					k=(long) (k+Math.pow(2, 16));
					arr3.add(Integer.toBinaryString((int)k));
			}
			Decompressed();


		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}


	public static String getvalue(int s) {

		if(!arr.get(s).equals("")){
			return arr.get(s);
		}else
			return "";

	}


	public static void Decompressed() {
		
		String old1=arr3.get(0);String new1=old1;
		//arr.set(getkeyf(getvalue(Integer.parseInt(new1,2))+getvalue(Integer.parseInt(old1,2)).charAt(0)),getvalue(Integer.parseInt(new1,2))+getvalue(Integer.parseInt(old1,2)).charAt(0));
		System.out.print(getvalue((Integer.parseInt(arr3.get(0),2))));
		for(int k=1;k<arr3.size();k++) {
			//System.out.println(k+" "+arr3.size());
			old1=arr3.get(k);
			//System.out.println((old1));
			if(!getvalue(Integer.parseInt(old1,2)).equals("")) {
				System.out.print(getvalue(Integer.parseInt(old1,2)));
				if(dictionary<Math.pow(2, 16)-1) {
					arr.set(++dictionary, getvalue(Integer.parseInt(new1,2))+getvalue(Integer.parseInt(old1,2)).charAt(0));
				}
				new1=old1;
			}else if(getvalue(Integer.parseInt(old1,2)).equals("")) {
				//System.out.println(Integer.parseInt(old1,2)+old1);
				if(dictionary<Math.pow(2, 16)-1) {
					arr.set(++dictionary,getvalue(Integer.parseInt(new1,2))+getvalue(Integer.parseInt(new1,2)).charAt(0));
				}
				System.out.print(getvalue(Integer.parseInt(old1,2)));
				new1=old1;					
			}			

		}


	}

}
