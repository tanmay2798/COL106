//package final2;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
public class Tree {

	public static Node root = new Node();
	public static Node3 first = new Node3();
	public static ArrayList<String> arr = new ArrayList<>();
	public static String[] str;

	public static void main(String[] args) {	
		try {
			FileInputStream fstream =new FileInputStream(args[0]);
			Scanner s1 = new Scanner(fstream);
			int i= Integer.parseInt(s1.nextLine());
			str= s1.nextLine().split("\\s+");

			String CEO=str[1];
			arr.add(CEO);
			root.object=CEO;
			root.level=1;
			root.parent=null;
			first.element=CEO;
			first.point=root;
			first.left=null;
			first.right=null;
			AddEmployee(str[0],str[1]);	

			for(int j=0;j<i-2;j++) {
				str = s1.nextLine().split("\\s+");
				AddEmployee(str[0],str[1]);	
			}
			i= Integer.parseInt(s1.nextLine());
			for(int j=0;j<i;j++) {
				str = s1.nextLine().split("\\s+");
				if(str[0].equals("0")) {					
					AddEmployee(str[1],str[2]);	
				}
				if(str[0].equals("1")) {
					DeleteEmployee(str[1],str[2]);	
				}
				if(str[0].equals("2")) {
					LowestCommonBoss(str[1],str[2]);	
				}
				if(str[0].equals("3")) {
					PrintEmployees(root);	
				}
			}
			s1.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found");
		} catch (java.util.NoSuchElementException e) {
			//e.printStackTrace();
		} catch (java.lang.NullPointerException e) {
			System.out.println("Name not found"+str[0]+str[1]);
		}
	}
	public static void PrintEmployeesin(Node3 n3) {
		if(n3==null)
			return;
		if(n3.left!=null)
			PrintEmployeesin(n3.left);
		System.out.println(n3.element);
		if(n3.right!=null)
			PrintEmployeesin(n3.right);
	}
	public static void AddEmployee(String emp,String bos){
		Node new1 = new Node();
		Node3 n3 = new Node3();
		Node3 n3old = new Node3();
		Node3 bose = new Node3();
		bose=findPosBST(bos,first);
		n3old=getPositionBST(emp,first);
		new1.object = emp;
		new1.parent=bose.point;
		new1.level=new1.parent.level+1;
		bose.point.children.add(new1);
		if(emp.compareTo(n3old.element)<0) {
			n3old.left=n3;
		}else {
			n3old.right=n3;
		}
		n3.element=emp;
		n3.left=null;
		n3.right=null;
		n3.point=new1;
		n3.parent=n3old;
	}

	public static void DeleteEmployee(String rem,String add){
		Node3 new1 = new Node3();
		Node3 old = new Node3();
		old = findPosBST(rem,first);
		new1 = findPosBST(add,first);
		if(old.point.level == new1.point.level) {
			for(int i=0;i<old.point.children.size();i++) {
				old.point.children.get(i).parent=new1.point;
				new1.point.children.add(old.point.children.get(i));
			}
			old.point.parent.children.removeElement(old.point);
			deleteFromBST(old);
		}else {
			System.out.println("Not of same level");
		}

		
	}

	public static void LowestCommonBoss(String one,String two){    
		Node ones = new Node();
		Node twos = new Node();
		ones=findPosBST(one,first).point;
		twos=findPosBST(two,first).point;
		String one1=ones.object;
		String two2=twos.object;		
		ArrayList<String> str1 = new ArrayList<>();
		while(ones!=root) {
			str1.add(ones.object);
			ones=ones.parent;
		}
		str1.add(root.object);
		ArrayList<String> str2 = new ArrayList<>();
		while(twos!=root) {
			str2.add(twos.object);
			twos=twos.parent;
		}
		str2.add(root.object);
		str1.retainAll(str2);

		if(str1.get(0).equals(one1) || str1.get(0).equals(two2))
			System.out.println(str1.get(1));
		else
			System.out.println(str1.get(0));
	}

	public static void PrintEmployees(Node n) {
		Queue<Node> q = new LinkedList<>();
		Node n2 = new Node();
		q.add(n);
		while(q.size()!=0) {
			System.out.println(q.peek().object);
			n2=q.remove();
			for(int i=0;i<n2.children.size();i++) {
				q.add(n2.children.get(i));
			}		
		}

	}
	public static Node3 getPositionBST(String str,Node3 base) {
		Node3 n3 = new Node3();
		n3=base;
		if(str.compareTo(base.element)<0) {
			if(base.left==null) {
				return n3; 
			}
			return getPositionBST(str,base.left);

		}else if(str.compareTo(base.element)>0) {
			if(base.right==null) {
				return n3; 
			}
			return getPositionBST(str,base.right);
		}
		return null;
	}
	public static Node3 findPosBST(String str,Node3 base) {
		Node3 n3 = new Node3();
		n3=base;
		if(str.equals(base.element)) {
			return n3;
		}else {
			if(str.compareTo(base.element)<0) {
				if(str.equals(base.element)) {
					return n3;
				}
				return findPosBST(str,base.left);

			}else if(str.compareTo(base.element)>0) {

				return findPosBST(str,base.right);
			} 
		}
		return null;
	}

	public static void deleteFromBST(Node3 n) {
		if(n.left==null) {
			if(n.parent.right == n) {
				n.parent.right=n.right;
				n.right=n.parent;
				n.element=null;
			}else if(n.parent.left == n){
				n.parent.left=n.right;
				n.right=n.parent;
				n.element=null;
			}
		}else if(n.right==null) {
			if(n.parent.right == n) {
				n.parent.right=n.left;
				n.left=n.parent;
				n.element=null;
			}else if(n.parent.left == n){
				n.parent.left=n.left;
				n.left=n.parent;
				n.element=null;
			}
		}else {
			Node3 k = inorderBST(n.right);
			n.element=k.element;
			n.point=k.point;
			deleteFromBST(k);
		}
	}	
	public static Node3 inorderBST(Node3 n){
		if(n.left==null) {
			return n;
		}else {
			return inorderBST(n.left);
		}
	}
}
