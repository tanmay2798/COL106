//package ass4;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.nio.charset.StandardCharsets;

import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.Scanner;


public class Compress {

	public static ArrayList<Node> arr = new ArrayList<Node>();
	public static ArrayList<String> arr2 = new ArrayList<>();
	public static long dictionary=-1;
	public static int collision=0;;

	public static void main(String [] args) {
		for(int i=0;i<Math.pow(2,19)-1;i++) {
			Node n = new Node("","");
			arr.add(n);
		}
		for(int i=0;i<128;i++) {
			Node n = new Node((char)i+"",Integer.toBinaryString(i));
			arr.set(i,n);
			//System.out.println(" "+i+" ");
			dictionary++;
		}
		System.out.println(dictionary);
		try {
			Scanner scanner = new Scanner(Paths.get(args[0]),StandardCharsets.UTF_8.name());
			String content = scanner.useDelimiter("\\A").next();
			scanner.close();
			Compressed(content);


		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		OutputStream os;
		try {
			os = new FileOutputStream(args[1]);
			byte code[] = new byte[2*arr2.size()];
			int j=0;
			for(int i=0;i<arr2.size();i=i+1) {
				code[j++] = (byte) ((Integer.parseInt(arr2.get(i),2)>>8)); // 0 is the signed 8-bit value of 00000000
				code[j++] =(byte) (Integer.parseInt(arr2.get(i),2) & 0xff);// -128 is the signed 8-bit value of 1000000
			}

			try {
				os.write(code);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		System.out.println(dictionary);
	}

	public static int getkey(String s) {
		if(s.length()==1)
			return getindex(((long)s.charAt(0)));
		else {
			long sum=0;
			for(int i=0;i<s.length();i++) {
				sum=(31*sum+(s.charAt(i)))%1500450271;
				//sum=(31*sum+(s.charAt(i)));
			}
			//System.out.println(sum);
			return getindex(sum);
		}

	}

	public static int getkeyf(String s) {
		if(s.length()==1)
			return getindex(((long)s.charAt(0)),s);
		else {
			long sum=0;
			for(int i=0;i<s.length();i++) {
				//System.out.println();
				//sum=(31*sum+(s.charAt(i)));
				sum=(31*sum+(s.charAt(i)))%1500450271;
			}
			//System.out.println(sum);
			return getindex(sum,s);
		}

	}

	public static int getindex(long s,String str) {
		long j=0;long k =(int)(s%arr.size());
		if(arr.get((int)(s%arr.size())).key.equals(str))
			return (int)(s%arr.size());
		else {
			for(long i=0;i<arr.size();i++) {
				j=(k+i*i)%arr.size();
				//if(arr.get((int)j)!=null) {
				if(!arr.get((int)j).key.equals("")) {
					if(arr.get((int)j).key.equals(str))
						return (int)j;
				}
				else
					break;
			}
		}
		return -1;	
	}

	public static int getindex(long s) {	

		return (int)(s%arr.size());

	}
	public static Node getvalue(long s) {
		//System.out.println(dictionary);
		if(!arr.get((int)s).key.equals("")){
			return arr.get((int)s);
		}else
			return null;

	}


	public static void Compressed(String str) {
		String new1="";String old1="";

		for(int k=0;k<str.length();k++) {
			//System.out.println(str+"65"+dictionary);
			old1=str.charAt(k)+"";

			int h=getkey(new1+old1);
			if(getvalue(h)!=null) {
				if(getvalue(h).key.equals(new1+old1)) 
					new1=new1+old1;
				else {
					//	System.out.println(collision++);
					long j=0;long l = h;
					if(dictionary<Math.pow(2, 16)-1) {

						for(long i=0;i<arr.size();i++) {
							//System.out.println(collision++);
							j=(l+i*i)%arr.size();
							if(arr.get((int)j).key.equals(new1+old1)) {						
								new1=new1+old1;

								break;
							}

							if(arr.get((int)j).key.equals("")) {
								if(dictionary<Math.pow(2, 16)-1) {
									Node n = new Node(new1+old1,Long.toBinaryString(++dictionary));
									arr.set((int)j, n);
								}
								arr2.add(getvalue(getkeyf(new1)).value);


								new1=old1;							
								break;
							}

						}
					}else {

						int k1=getkeyf(new1+old1);
						int k2=getkeyf(new1);
						if(k1!=-1) {

							if(getvalue(k1).key.equals(new1+old1))
								new1=new1+old1;
							else {
								arr2.add(getvalue(k2).value);
								new1=old1;	
							}
						}else {

							arr2.add(getvalue(k2).value);
							new1=old1;	
						}

					}

				}
			}
			else if(getvalue(h)==null) {
				if(dictionary<Math.pow(2, 16)-1) {
					Node n = new Node(new1+old1,Long.toBinaryString(++dictionary));
					arr.set(h,n);

				}
				arr2.add(getvalue(getkeyf(new1)).value);


				new1=old1;	
			}else {

			}
		}
		if(!new1.equals("")) {
			arr2.add(getvalue(getkeyf(new1)).value);
		}

	}

}
