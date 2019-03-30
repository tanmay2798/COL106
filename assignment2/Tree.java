package assignment2;                                                                                                    
import java.util.*;                                                                                                     
public class Tree {                                                                                                     
                                                                                                                        
	public static Vector<Node2> names = new Vector<Node2>();                                                            
	public static Node root = new Node();                                                                               
	public static void main(String[] args) {                                                                            
		Node n = new Node();                                                                                            
		                                                                                                                
		Node2 first = new Node2();                                                                                      
		String CEO="tanmay";                                                                                            
		String boss="";                                                                                                 
		String employee="";                                                                                             
                                                                                                                        
		root.object=CEO;                                                                                                
		root.parent=null;                                                                                               
		//			if(boss==CEO) {                                                                                     
		//				root.children.add(employee);                                                                    
		//			}else {                                                                                             
                                                                                                                        
		//}                                                                                                             
		first.element=CEO;                                                                                              
		first.point=root;                                                                                               
		names.add(first);                                                                                               
                                                                                                                        
		//		System.out.println(names.get(0).element);                                                               
                                                                                                                        
		AddEmployee("kshtij","tanmay");                                                                                 
		AddEmployee("akash","tanmay");                                                                                  
		AddEmployee("manish","akash");                                                                                  
		AddEmployee("arjun","akash");                                                                                   
		AddEmployee("vipul","kshtij");                                                                                  
		AddEmployee("tushar","kshtij");                                                                                 
		AddEmployee("pranjal","akash");                                                                                 
		AddEmployee("arsh","kshtij");                                                                                   
		AddEmployee("utkarsh","arsh");                                                                                  
		AddEmployee("shanatm","arsh");                                                                                  
//		DeleteEmployee("akash","kshtij");                                                                               
//		DeleteEmployee("arsh","manish");                                                                                
//		LowestCommonBoss("utkarsh","arsh");                                                                             
//		AddEmployee("utkarsh","arsh");                                                                                  
//		AddEmployee("shanatm","arsh");                                                                                  
		//		for(int i=0;i<names.size();i++) {                                                                       
		//			System.out.println(names.get(i).element);                                                           
		//		}                                                                                                       
		PrintEmployees();                                                                                               
		                                                                                                                
//		while(n.children.size()!=0) {                                                                                   
//			int k=root.children.size();                                                                                 
//			//System.out.println(k+"");                                                                                 
//			for(int j=0;j<k;j++) {                                                                                      
//				for(int i=0;i<root.children.size();i++) {			                                                    
//					System.out.println(root.children.get(i).object+"12"+root.children.get(i).parent.object);            
//				}                                                                                                       
//				root=n.children.get(j);                                                                                 
//			}                                                                                                           
//			n=n.children.get(0);                                                                                        
//		}	                                                                                                            
//		int k=1;                                                                                                        
//		while(n.children.size()!=0) {                                                                                   
//			                                                                                                            
//			for(int i=0;i<k;i++) {                                                                                      
//				getChildren(root);                                                                                      
//			}                                                                                                           
//			k=n.children.size();                                                                                        
//			root=root.children.get(k);                                                                                  
//		}                                                                                                               
	}                                                                                                                   
	                                                                                                                    
	public static void PrintEmployees() {                                                                               
		System.out.println(names.get(0).point.object+"12"+null);                                                        
		for(int i=1;i<names.size();i++) {                                                                               
			System.out.println(names.get(i).point.object+"12"+names.get(i).point.parent.object);                        
			//System.out.println(names.get(i).point.children.size());                                                   
		}                                                                                                               
	}                                                                                                                   
	                                                                                                                    
	public static void LowestCommonBoss(String one,String two){                                                         
		Node new1 = new Node();                                                                                         
		Node old = new Node();                                                                                          
		//Node check = new Node();                                                                                      
		old = getNode(one);                                                                                             
		new1 = getNode(two);                                                                                            
		Vector <String > ones = new Vector<>();                                                                         
		Vector <String > twos = new Vector<>();                                                                         
		System.out.println("k");                                                                                        
		int p=0,q=0;                                                                                                    
		while(old!=root) {                                                                                              
			ones.add(old.object);                                                                                       
			old=old.parent;                                                                                             
			p++;                                                                                                        
		}                                                                                                               
		System.out.println(ones);			                                                                            
		System.out.println(p);                                                                                          
		while(new1!=root) {                                                                                             
			twos.add(new1.object);                                                                                      
			new1=new1.parent;                                                                                           
			q++;                                                                                                        
		}                                                                                                               
		ones.add(root.object);twos.add(root.object);                                                                    
		System.out.println(twos);	                                                                                    
		System.out.println(q);                                                                                          
		String str="";                                                                                                  
		                                                                                                                
		//if(p>q) {                                                                                                     
			while(ones.get(p-1).equalsIgnoreCase(twos.get(q-1)) && p>=0 && q>=0) {                                      
				p--;                                                                                                    
				q--;                                                                                                    
				System.out.println((p));                                                                                
				System.out.println((q));                                                                                
			}                                                                                                           
		//}                                                                                                             
		System.out.println(ones.get(p));                                                                                
                                                                                                                        
	}                                                                                                                   
                                                                                                                        
	public static void AddEmployee(String emp,String bos){                                                              
		Node new1 = new Node();                                                                                         
		Node old = new Node();                                                                                          
		Node2 n2 = new Node2();                                                                                         
                                                                                                                        
		old = getNode(bos);                                                                                             
                                                                                                                        
		new1.object = emp;                                                                                              
		new1.parent=old;                                                                                                
		old.children.add(new1);                                                                                         
                                                                                                                        
		n2.element = emp;                                                                                               
		n2.point = new1;                                                                                                
                                                                                                                        
		names.add(n2);                                                                                                  
		//Collections.sort(names);                                                                                      
	}                                                                                                                   
	                                                                                                                    
	public static void DeleteEmployee(String rem,String add){                                                           
		Node new1 = new Node();                                                                                         
		Node old = new Node();                                                                                          
	                                                                                                                    
	                                                                                                                    
		old = getNode(rem);                                                                                             
		new1 = getNode(add);                                                                                            
		                                                                                                                
		names.removeElement(getNode2(rem));                                                                             
		for(int i=0;i<old.children.size();i++) {                                                                        
			old.children.get(i).parent=new1;                                                                            
			new1.children.add(old);                                                                                     
		}                                                                                                               
		                                                                                                                
		old.parent.children.removeElement(old);                                                                         
		//Collections.sort(names);                                                                                      
	}                                                                                                                   
                                                                                                                        
	public static Node getNode(String s) {                                                                              
		for(int i=0;i<names.size();i++) {                                                                               
			if(s.equalsIgnoreCase(names.get(i).element)){                                                               
			//	System.out.println(names.get(i).element+"hihi");                                                        
				return names.get(i).point;                                                                              
			}                                                                                                           
		}                                                                                                               
		return null;                                                                                                    
                                                                                                                        
	}                                                                                                                   
	                                                                                                                    
	public static Node getChildren(Node n) {                                                                            
		for(int i=0;i<n.children.size();i++) {                                                                          
			System.out.println(n.children.get(i));                                                                      
		}                                                                                                               
		return null;                                                                                                            
	}                                                                                                                   
	                                                                                                                    
	public static Node2 getNode2(String s) {                                                                            
		for(int i=0;i<names.size();i++) {                                                                               
			if(s.equalsIgnoreCase(names.get(i).element)){                                                               
			//	System.out.println(names.get(i).element+"hihi");                                                        
				return names.get(i);                                                                                    
			}                                                                                                           
		}                                                                                                               
		return null;                                                                                                    
                                                                                                                        
	}                                                                                                                   
                                                                                                                        
                                                                                                                        
}                                                                                                                       
                                                                                                                        