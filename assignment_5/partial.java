//continuos letter and zero difference && if(str1.charAt(store+r.start)==str.charAt(star)) {
// b*a in banana
package assignment_5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class partial {

	public static com_node root =new com_node();
	static String str1="";
	static ArrayList<String> in = new ArrayList<String>();
	static ArrayList<com_node> nodal = new ArrayList<com_node>();
	static Stack<Integer> pre = new Stack<>();

	static Stack<Integer> posy= new Stack<Integer>();
	public static void main(String[] args) {
		FileInputStream fstream;
		try {
			fstream = new FileInputStream("/Users/tanmaygoyal/eclipse-workspace/tanmay/src/assignment_5/b.txt");

			Scanner scan = new Scanner(fstream);
			String str= (scan.nextLine());
			str1=str;
			int k=-1;
			for(int j=0;j<str1.length();j++) {
				str = str1.substring(j);
				k++;
				com_node r=root;
				for(int i=0;i<str.length();i++) {
					r=insert(str.charAt(i),r,k+i,str,j);
					if(r==null)
						break;
				}
			}

			int total = Integer.parseInt(scan.nextLine());
			root.end=false;
			for(int i=0;i<total;i++) {
				String str2=scan.nextLine();
//				if(str2.contains("*") && !str2.equals("*")) {
//					String s2="";
//					String first=str2.substring(0,str2.indexOf('*'));
//					String second=str2.substring(str2.indexOf('*')+1);
//					search(first,root,true);
//					//System.out.println("yy"+pre.size());
//					for(int j=0;j<nodal.size();j++) {
//						
//						for(int l=0;s2.length()+second.length()<str1.length()-first.length()-1;l++) {
//							System.out.println("ii"+in.size());
//							search(s2+second,nodal.get(j),false);
//							s2=s2+'?';
//							System.out.println("pp"+in.size());
//						}
//						pre.pop();
//						s2="";
//					}
//				}else
//					search(str2,root,true);
				
				if(str2.contains("*") && !str2.equals("*")) {
					String s2="";
					for(int j=0;str2.replace("*", s2).length()<str1.length();j++) {
						search(str2.replace("*", s2),root,true);
						s2=s2+'?';
					}
				}else
					search(str2,root,true);
				//System.out.println(in.size());
				SelectionSort s = new SelectionSort();
				ArrayList<String> s1 = new ArrayList<String>();
				s1=s.sort(in);
				//	ArrayList<String> s1=in;
				for(int j=0;j<s1.size();j++)
					System.out.println(s1.get(j));
				in.clear();
				nodal.clear();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	static com_node insert(char c,com_node r,int pos,String str,int point) {
		int posold=pos;
		int skip=0;
		//System.out.println("----->     "+str);
		if(r.children==null) {
			com_node n = new com_node();
			n.start=pos;
			n.last=str1.length()-1;
			r.children = new ArrayList<com_node>();
			n.index=pos;
			n.end=true;
			r.children.add(n);
			return null;
		}else {

			for(int j=0;j<r.children.size();j++) {

				if(str1.charAt(r.children.get(j).start)==(c)) {
					//System.out.println("llll    v"+str+" "+r.children.get(j).start);
					if(str.length()>=1+r.children.get(j).last-r.children.get(j).start) {
						boolean g=false;	
						//						if(str.length()==1+r.children.get(j).last-r.children.get(j).start ) {
						//							for(int i=r.children.get(j).start;i<=r.children.get(j).last;i++) {
						//								
						//							}
						//						}

						for(int i=r.children.get(j).start;i<=r.children.get(j).last;i++) {
							//		System.out.println(r.children.get(j).start+" yy "+r.children.get(j).last);
							if(str1.charAt(i)==str.charAt(i-r.children.get(j).start)) {
								g=true;
							}else {
								com_node n = new com_node();

								n.last=r.children.get(j).last;
								n.start=i;
								n.end=false;
								n.index=r.children.get(j).index;
								if(n.index==55)
									System.out.println(n.index+"a");
								r.children.get(j).last=i-1;
								r.children.get(j).end=false;
								if(r.children.get(j).children!=null) {
									n.children=new ArrayList<com_node>();
									for(int l=0;l<r.children.get(j).children.size();l++)
										n.children.add(r.children.get(j).children.get(l));
								}
								r.children.get(j).children.clear();
								r.children.get(j).children.add(n);
								g=false;
								break;
							}
						}
						if(g==true) {
							//System.out.println(str+"12");
							//if(atr)
							skip=1+r.children.get(j).last-r.children.get(j).start;
							//		System.out.println("1"+pos+" "+skip);
							str=str.substring(skip);
							pos=pos+skip;
						}else {
							//		System.out.println("2");
							//System.out.println(str);
							skip=1+r.children.get(j).last-r.children.get(j).start;
							str=str.substring(skip);
							pos=pos+skip;
						}
						r=r.children.get(j);
						//r.index=posold;
						j=-1;
						//System.out.println(str+" 11");
						if(str.equals("")) {
							r.index=posold;
							r.end=true;
							return null;
						}
						c=str.charAt(0);
					}else {
						//System.out.println("ddddd    "+j+""+str);
						int k=r.children.get(j).start;
						for(int i=0;i<str.length();i++) {

							if(str1.charAt(pos++)==str1.charAt(k++))
							{
								//	System.out.println("yo");
							}
							else {

								boolean checker =false;
								//		System.out.println(str+"     tanmay"+k+" "+pos);
								com_node n= new com_node();
								n.start=k-1;

								//n.last=str.length()-1;///////
								n.last=r.children.get(j).last;
								//System.out.println(r+" "+root);
								n.index=r.children.get(j).index;	
								//								if(n.index==55)
								//									System.out.println(n.index+"b"+n.start+" "+n.last);
								com_node n1= new com_node();
								n1.start=pos-1;
								n1.last=str1.length()-1;
								n1.index=posold;
								if(n1.index==55)
									System.out.println(n1.index+"c");
								n.end=r.children.get(j).end;
								r.children.get(j).end=false;
								n1.end=true;
								r.children.get(j).last=k-2;	
								r.children.get(j).index=r.children.get(j).start;
								//		System.out.println(n.start+" hh "+n.last+" "+r.children.get(j).start);
								//								if(r.children.get(j).index==55)
								//									System.out.println(r.children.get(j).index+"e"+r.children.get(j).start);
								if(r.children.get(j).children!=null) {
									//		System.out.println("1ll"+str);
									for(int l=0;l<r.children.get(j).children.size();l++) {
										if(str1.charAt(r.children.get(j).children.get(l).start)==str1.charAt(pos-1)) {
											n.end=r.children.get(j).end;

											n.children=new ArrayList<com_node>();
											for(int o=0;o<r.children.get(j).children.size();o++)
												n.children.add(r.children.get(j).children.get(o));

											//		System.out.println("tanmayee12");
											r.children.get(j).children.clear();
											//r.children.get(j).end=false;
											r.children.get(j).children.add(n);
											r.children.get(j).children.add(n1);
											checker=true;
										}
									}
									if(checker==false) {
										r.children.get(j).children.add(n);
										r.children.get(j).children.add(n1);
									}


								}else {
									//System.out.println(2);
									r.children.get(j).end=false;     
									r.children.get(j).children = new ArrayList<com_node>();
									r.children.get(j).children.add(n);
									r.children.get(j).children.add(n1);
									//	System.out.println(r.children.get(j).children.get(0).start);
								}
								return null;
							}
							//System.out.println(str+k+" "+pos);
						}
						//		System.out.println(str+k+" "+posold+" "+r.children.get(j).index);
						if(str.length()==1 && r.children.get(j).start==r.children.get(j).last) {
							r.children.get(j).index=posold;
							if(r.children.get(j).index==55)
								System.out.println(r.children.get(j).index+"f");
							r.children.get(j).end=true;
							return null;
						}
						//System.out.println(str+k+" "+pos+""+r.children.get(j).start+" "+r.children.get(j).last);
						com_node n= new com_node();
						n.start=k;
						n.end=r.children.get(j).end;
						n.last=r.children.get(j).last;
						r.children.get(j).last=k-1;
						if(r.children.get(j).children!=null) {
							//		System.out.println("tanmay"+r.children.get(j));
							n.end=r.children.get(j).end;
							n.children=new ArrayList<com_node>();
							for(int l=0;l<r.children.get(j).children.size();l++)
								n.children.add(r.children.get(j).children.get(l));
							r.children.get(j).children.clear();

							n.index=r.children.get(j).index;
							if(n.index==55)
								System.out.println(n.index+"g"+n.end);
							r.children.get(j).index=posold;
							if(r.children.get(j).index==55)
								System.out.println(r.children.get(j).index+"i");
							r.children.get(j).children.add(n);
							//System.out.println(n.end);
						}else {

							r.children.get(j).children = new ArrayList<com_node>();
							n.index=r.children.get(j).index;
							if(n.index==55)
								System.out.println(n.index+"h");
							r.children.get(j).index=posold;
							if(r.children.get(j).index==55)
								System.out.println(r.children.get(j).index+"j");
							r.children.get(j).children.add(n);
						}
						return null;
					}
				}
			}
			com_node n = new com_node();
			//r.index=
			n.index=posold;

			n.start=pos;
			n.end=true;
			r.children.add(n);
			n.last=str1.length()-1;
			//			if(n.index==55)
			//				System.out.println(n.index+"h"+skip+" "+n.start+" "+n.last+" "+pos+str);
			return null;
		}

	}

	static boolean search(String str,com_node r,boolean check2) {
		//System.out.println(str +check2);
		if(str.equals("*")){
			for(int i=0;i<str1.length();i++) {
				for(int j=i;j<str1.length();j++)
					System.out.println(i+" "+j);
			}
			return true;
		}

		boolean check = false;
		boolean checker = false;

		posy.clear();



		check=false;
		int i=0;
		Stack<com_node> q1 = new Stack<com_node>();
		if(check2==true) {
			for(int u=r.children.size()-1;u>=0;u--) {
				if(str1.charAt(r.children.get(u).start)==str.charAt(i) || str.charAt(i)=='?') {
					q1.add(r.children.get(u));	
				}
				else
					check=false;
				
			}
			posy.add(0);
		}else {
			int p=0;
			//System.out.println(r.start+" "+r.length+" "+r.last +" "+r.children);
			for(int y=pre.peek();y<=r.last && p<str.length();y++) {
				if(str1.charAt(y)!=str.charAt(p++)) {
					if(str.charAt(p-1)!='?') {
						checker=true;
						break;
					}
				}
			}
			if(p==str.length() && checker==false) {
				partial.printy(r,str,check2);
			}
			//System.out.println(checker+" kk "+str.charAt(p-1));
			if(checker==false) {
				if(r.children!=null) {
					for(int h=r.children.size()-1;h>=0;h--) {
						if(str1.charAt(r.children.get(h).start)==str.charAt(p) || str.charAt(p)=='?') {
							posy.add(p);
							q1.add(r.children.get(h));
						}
						else
							check=false;
					}

				}
			}
			checker=false;
		}
		//System.out.println(q1.size());
		while(q1.size()!=0) {
			r=q1.pop();
			if(posy.empty()) {
				i=0;
			}
			else {
				i=posy.pop();
			}
			check=true;
			int k=0;
			//System.out.println(i+" uu "+k);
			for(k=r.start;k<=r.last && i<str.length();k++) {
				if(str1.charAt(k)!=str.charAt(i++)) {

					if(str.charAt(i-1)!='?') {

						checker=true;
						break;
					}
				}
			}
			if(i==str.length() && checker==false) {

				partial.printy(r,str,check2);

				check=true;
			}
			else if(checker==false){

				if(r.children!=null) {

					for(int h=r.children.size()-1;h>=0 && i!=str.length();h--) {
						if(str1.charAt(r.children.get(h).start)==str.charAt(i) || str.charAt(i)=='?') {
							posy.add(i);
							q1.add(r.children.get(h));
						}
						else
							check=false;
					}

				}
			}
			if(checker==true && q1.size()==0) {
				checker=false;
				break;
			}else
				checker=false;
		}
		return true;

	}

	static void printy(com_node r,String s,boolean check) {

		Queue<com_node> q = new LinkedList<com_node>();
		q.add(r);

		while(q.size()!=0) {

			if(q.peek().end==true) {
				if(check==true) {
					nodal.add(q.peek());
					in.add(q.peek().index+" "+(q.peek().index+s.length()-1));
					pre.add(q.peek().index+s.length());
				}else {
					//System.out.println(pre.peek()+s+" iiiiiiiiiiiii"+q.peek().index);
					in.add(q.peek().index+" "+(q.peek().index+s.length()-1));
				}

			}
			com_node n = q.remove();
			if(n.children!=null) {
				for(int j=0;j<n.children.size();j++) {
					q.add(n.children.get(j));
				}
			}
		}
	}

}
