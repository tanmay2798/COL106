package assignment2;

import java.util.Vector;

public class check {

	public check() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[]args) {
		Vector<String> v = new Vector<>();
		Node root = new Node();
		Node n = new Node();
		n=root;
		for(int i=0;i<7;i++) {
			n = new Node();
			n.object="tan"+i;
			v.add(n.object);
			
		}
//		n=root;
//		for(int i=0;i<7;i++) {	
//			v.add(n.object);
//		}
		
//		v.add("tan");
//		v.add("tan1");
//		v.add("tan2");
//		v.add("tan3");
//		v.add("tan4");
//		v.add("tan5");
		v.removeElement("tan2");
		for(int i=0;i<v.size();i++) {
			System.out.println(v.get(i));
		}
	}

}
