package assignment_5;

import java.util.ArrayList;

public class trie {

	public static node root =new node();

	public static void main(String[] args) {
		String str = "BANANAbanana";
		String str1=str;
		root.key='\0';
		root.end=false;
		int k=-1;
		for(int j=0;j<str1.length();j++) {
			str = str1.substring(j,str1.length());
			k++;
			node r=root;
			//System.out.println(str);
			for(int i=0;i<str.length();i++) {
				r=insert(str.charAt(i),r,k+i);
			}
			node f = new node();
			f.end=true;
			r.end=true;
			if(r.children==null) {
				r.children=new ArrayList<node>();
				r.children.add(f);
			}
			else
				r.children.add(f);
		}
		node f = new node();
		f.end=true;
		root.children.add(f);
		//System.out.println(search("NA"));
		System.out.println(search("na"));
		//		System.out.println(search("hdi"));
		//		System.out.println(search("hv"));
	}

	static node insert(char c,node r,int pos) {

		if(r.children==null) {
			//System.out.println("k");
			node n = new node();
			n.key=c;
			r.children = new ArrayList<node>();
			if(n.index==null) {
				n.index = new ArrayList<Integer>();
				n.index.add(pos);
			}
			else
				n.index.add(pos);
			r.children.add(n);
			return n;
		}else {
			for(int j=0;j<r.children.size();j++) {
				if(r.children.get(j).key==(c)) {
					r.children.get(j).index.add(pos);
					return r.children.get(j);
				}
			}
			//System.out.println(c+"  l"+pos);
			node n = new node();
			n.key=c;
			if(n.index==null) {
				n.index = new ArrayList<Integer>();
				n.index.add(pos);
			}
			else
				n.index.add(pos);
			r.children.add(n);
			//		for(int k=0;k<r.children.size();k++)
			//			System.out.print(r.children.get(k).key+"12");
			//	System.out.println("");
			return r.children.get(r.children.indexOf(n));
		}

	}

	static boolean search(String str) {
		node r =root;
		boolean check = false;
		for(int i=0;i<str.length();i++) {
			if(r.children==null)
				return false;
			check=false;
			for(int j=0;j<r.children.size();j++) {
				if(r.children.get(j).key==str.charAt(i)) {
					if(i==str.length()-1) {
						for(int m=0;m<r.children.get(j).index.size();m++) {
							//							ArrayList<Integer> in = new ArrayList<Integer>();
							//							in.add(r.children.get(j).index.get(m));
							System.out.println(r.children.get(j).index.get(m)-(str.length()-1)+" "+r.children.get(j).index.get(m));
						}
						System.out.println("");
					}
					r=r.children.get(j);
					check=true;
					break;
				}
			}
			if(check==false)
				break;
		}
		if(check==true)
			return true;
		else
			return false;
	}

}
