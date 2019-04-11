package assignment_5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class compressed_trie {

	public static com_node root =new com_node();
	//public static int point_old=0;
	public static ArrayList<Integer> in = new ArrayList<Integer>();

	public static void main(String[] args) {
		//String str = "WOLF, meeting with a Lamb astray from the fold, resolved not tolay violent hands on him, but to find some plea to justify to theLamb the Wolf's right to eat him.  He thus addressed him" ;
		String str = "abcabxabcd$";
		String str1=str;

		root.end=false;
		int k=-1;
		for(int j=0;j<str1.length();j++) {
			str = str1.substring(j,str1.length());
			k++;
			com_node r=root;
			for(int i=0;i<str.length();i++) {
				r=insert(str.charAt(i),r,k+i,str,j,str1);
				if(r==null)
					break;
			}
			//System.out.println(root.children.get(0).start+" "+root.children.get(0).last);
			com_node f = new com_node();
			f.end=true;
			//			r.end=true;
			//			if(r.children==null) {
			//				r.children=new ArrayList<com_node>();
			//				r.children.add(f);
			//			}
			//			else
			//				r.children.add(f);
		}
		com_node f = new com_node();
		f.end=true;
		root.children.add(f);
		//	com_node t=root;
		//		while(t.children!=null) {
		//			System.out.println(t.children.get(0).start+" "+t.children.get(0).last);
		//			t=t.children.get(0);
		//		}
		//System.out.println(root)
		//		System.out.println("");
		System.out.println(" "+root.children.get(3).start+" jj "+root.children.get(3).last+" "+root.children.get(3).index);
		//		System.out.println(search("x",str1));
		//		System.out.println(search("xx",str1));
		//		System.out.println(search("xxx",str1));
		System.out.println(search("b",str1));
		//		System.out.println(search("ab",str1));
		//		System.out.println(search("de",str1));
		//		System.out.println(search("xxxxx",str1));
		//		System.out.println(search(str1+"xx",str1));
	}

	static com_node insert(char c,com_node r,int pos,String str,int point,String str1) {
		int posold=pos;
		//System.out.println(str+"\n");
		int h1=0;
		if(r.children==null) {
			com_node n = new com_node();
			n.start=pos;
			n.last=str1.length()-1;
			r.children = new ArrayList<com_node>();
			n.index=pos;
			//			if(n.index==null) {
			//				n.index = new ArrayList<Integer>();
			//				n.index.add(pos);
			//			}
			//			else
			//				n.index.add(pos);
			r.children.add(n);
			return null;
		}else {

			for(int j=0;j<r.children.size();j++) {
				//System.out.println(str1.charAt(r.children.get(j).start)+"         "+str);
				if(str1.charAt(r.children.get(j).start)==(c)) {

					//System.out.println(c+" "+" n "+str1.charAt(r.children.get(j).start) + str);
					if(str.length()>1+r.children.get(j).last-r.children.get(j).start) {
						str=str.substring(h1+1+r.children.get(j).last-r.children.get(j).start);
						pos=pos+1+r.children.get(j).last-r.children.get(j).start;
						r=r.children.get(j);
						j=-1;
						c=str.charAt(0);
					}else {
						int k=r.children.get(j).start;
						for(int i=0;i<str.length();i++) {

							if(str1.charAt(pos++)==str1.charAt(k++))
							{
								System.out.println("yo");
							}
							else {
								boolean checker =false;
								//		System.out.println(str+"     tanmay"+k+" "+pos);
								com_node n= new com_node();
								n.start=k-1;

								n.last=str1.length()-1;
								//System.out.println(r+" "+root);
								n.index=r.children.get(j).index;	

								com_node n1= new com_node();
								n1.start=pos-1;
								n1.last=str1.length()-1;
								n1.index=posold;

								r.children.get(j).last=k-2;	
								r.children.get(j).index=r.children.get(j).start;	
								if(r.children.get(j).children!=null) {
									//	System.out.println("1");
									for(int l=0;l<r.children.get(j).children.size();l++) {
										if(str1.charAt(r.children.get(j).children.get(l).start)==str1.charAt(pos-1)) {
											n.children=new ArrayList<com_node>();
											for(int o=0;o<r.children.get(j).children.size();o++)
												n.children.add(r.children.get(j).children.get(o));

											r.children.get(j).children.clear();
											//		System.out.println("tanmayee");
											r.children.get(j).children.add(n);
											checker=true;
										}
									}
									if(checker==false) {
										r.children.get(j).children.add(n1);
									}


								}else {
									//System.out.println(str+" "+"tanmay");
									//	System.out.println(j+" "+str+" "+r.children.get(0)+"         2"+root.children.get(0));
									r.children.get(j).children = new ArrayList<com_node>();
									r.children.get(j).children.add(n);
									r.children.get(j).children.add(n1);
									//	System.out.println(r.children.get(j).children.get(0).start);
								}
								return null;
							}
							//System.out.println(str+k+" "+pos);
						}

						if(str.length()==1 && r.children.get(j).start==r.children.get(j).last)
							return null;
						//System.out.println(str+k+" "+pos+""+r.children.get(j).start+" "+r.children.get(j).last);
						com_node n= new com_node();
						n.start=k;
						//System.out.println(str+" "+"tanmay");
						n.last=r.children.get(j).last;
						r.children.get(j).last=k-1;
						if(r.children.get(j).children!=null) {
							n.children=new ArrayList<com_node>();
							for(int l=0;l<r.children.get(j).children.size();l++)
								n.children.add(r.children.get(j).children.get(l));
							r.children.get(j).children.clear();
							r.children.get(j).index=pos;
							n.index=r.children.get(j).index;
							r.children.get(j).children.add(n);
						}else {

							r.children.get(j).children = new ArrayList<com_node>();
							n.index=posold;
							r.children.get(j).children.add(n);
						}
						return null;
					}
				}

			}
			com_node n = new com_node();
			n.index=pos;
			n.start=pos;
			r.children.add(n);
			n.last=str1.length()-1;
			return null;
		}

	}

	static boolean search(String str,String str1) {
		com_node r =root;
		boolean check = false;
		//for(int i=0;i<str.length();) {

		check=false;
		int i=0;
		for(int j=0;j<r.children.size() && i<str.length();) {

			//System.out.println(str);
			if(str1.charAt(r.children.get(j).start)==str.charAt(i) || str.charAt(i)=='?') {
				//	System.out.println(j+" ll "+r.children.size()+" "+str.charAt(i));
				check=true;
				for(int k=r.children.get(j).start;k<=r.children.get(j).last && i<str.length();k++) {
					if(str1.charAt(k)!=str.charAt(i++)) {
						if(str.charAt(i-1)!='?')
							return false;
					}
					//					if(str1.charAt(k)==str.charAt(str.length()-1))
					//						in.add(k);
					//break;	
				}
				//					if(i==str.length()-1) {
				//						for(int m=0;m<r.children.get(j).index.size();m++) {
				//							//							ArrayList<Integer> in = new ArrayList<Integer>();
				//							//							in.add(r.children.get(j).index.get(m));
				//							System.out.println(r.children.get(j).index.get(m)-(str.length()-1)+" "+r.children.get(j).index.get(m));
				//						}
				//						System.out.println("");
				//					}

				r=r.children.get(j);
				j=-1;
				//System.out.println(i+" "+str.length()+"  "+r.children);
				if(i!=str.length() && (r.children==null) )
					return false;
				if(i==str.length() && r.children==null) {
					check=true;
					break;
				}
			}else
				check=false;
			j++;

		}
		System.out.println(r.start+" "+r.last);
		if(check==false)
			return false;
		else {
			Queue<com_node> q = new LinkedList<com_node>();
			q.add(r);
			System.out.println("");
			while(q.size()!=0) {
				if(q.peek().children==null)
					System.out.println(q.peek().index);
				com_node n = q.remove();
				if(n.children!=null) {
				for(int j=0;j<n.children.size();j++) {
					q.add(n.children.get(j));
				}
				}
			}
			

			return true;
		}
	}

}
