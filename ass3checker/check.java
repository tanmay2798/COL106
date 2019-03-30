//package ass3checker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.util.Pair;
public class check {
	public static String[] str;
	public static void main(String[] args) {
//		try {
//			FileInputStream fstream =new FileInputStream("/Users/tanmaygoyal/eclipse-workspace/tanmay/src/ass3checker/largecheck.java");
//			FileInputStream fstream1 =new FileInputStream("/Users/tanmaygoyal/Downloads/large_ans.txt");
//			
//			Scanner s1 = new Scanner(fstream);
//			Scanner s2 = new Scanner(fstream1);
//			
//			while(s1.hasNext() || s2.hasNext()) {
//				System.out.println("hi");
//				if(!s1.nextLine().equalsIgnoreCase(s2.nextLine())) {
//						System.out.println(s1.nextLine()+" out "+s2.nextLine());
//						break;
//				}
//					
//			}
//			
//			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		BestFit b = new BestFit();
		try {
			FileInputStream fstream =new FileInputStream(args[0]);
			Scanner s1 = new Scanner(fstream);
			while(s1.hasNextLine()) {
				str = s1.nextLine().split("\\s+");
				if(str[0].equals("1")) {
					b.add_bin(Integer.parseInt(str[1]),Integer.parseInt(str[2]));	

				}
				if(str[0].equals("2")) {
					System.out.println(b.add_object(Integer.parseInt(str[1]),Integer.parseInt(str[2])));	


				}
				if(str[0].equals("3")) {
					System.out.println(b.delete_object(Integer.parseInt(str[1])));	
				}
				if(str[0].equals("4")) {
					
					for(int i=0;i<b.contents(Integer.parseInt(str[1])).size();i++) {
						Pair <Integer,Integer> ans = b.contents(Integer.parseInt(str[1])).get(i);
						System.out.println(ans.getKey()+" "+ans.getValue());
					
					}//System.out.println("");
				}
			}
			s1.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found");
		} catch (java.util.NoSuchElementException e) {
			//e.printStackTrace();FDle
		}
	}

}
