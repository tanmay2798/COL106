//package ass3checker;
import java.io.FileInputStream;
import javafx.util.Pair; 
import java.io.FileNotFoundException;
import java.util.*;

public class BestFit {
	public static String[] str;
	public static int lh; /* for height of left subtree */		   
	public static int rh;
	public static boolean check =true;
	public static boolean check1 =true;
	public static Object objroot = new Object();
	public static Bin binroot = new Bin();
	public static binID binrootid = new binID();
	public static ArrayList<String> arr;
	public static ArrayList <Pair <Integer,Integer> > l1;

	public BestFit() {
		check =true;
		check1 =true;
		objroot = new Object();
		binroot = new Bin();
		binrootid = new binID();

	}

	public List<Pair<Integer, Integer>> contents(int bin_id){
		l1 = new ArrayList <Pair <Integer,Integer> > (); 
		PrintBin(bin_id);
		return l1;
	}

	public static void updateHeight(Bin n1) {
		Bin n = new Bin();
		n=n1;
		if(n==null) 
			return;
		if(n.left==null && n.right==null) {
			n.h=1;
			return;
		}
		if(n.left!=null && n.right!=null) {
			if(n.h==1+Math.max(n.left.h, n.right.h))
				return;
		}
		if(n.left!=null && n.right==null && n.h!=1+n.left.h)
			n.h=1+n.left.h;
		else if(n.left==null && n.right!=null && n.h!=1+n.right.h)
			n.h=1+n.right.h;
		else if(n.left!=null && n.right!=null)
			n.h=1+Math.max(n.left.h,n.right.h);
		else
			return;

		updateHeight(n.parent);
		return;
	}
	public static void updateHeight(binID n1) {
		binID n = new binID();
		n=n1;
		if(n==null) 
			return;
		if(n.left==null && n.right==null) {
			n.h=1;
			return;
		}
		if(n.left!=null && n.right!=null) {
			if(n.h==1+Math.max(n.left.h, n.right.h))
				return;
		}
		if(n.left!=null && n.right==null && n.h!=1+n.left.h)
			n.h=1+n.left.h;
		else if(n.left==null && n.right!=null && n.h!=1+n.right.h)
			n.h=1+n.right.h;
		else if(n.left!=null && n.right!=null)
			n.h=1+Math.max(n.left.h,n.right.h);
		else
			return;

		updateHeight(n.parent);
		return;
	}
	public static void updateHeight(Object n) {

		if(n==null) 
			return;
		if(n.left==null && n.right==null) {
			n.h=1;
			return;
		}
		if(n.left!=null && n.right!=null) {
			if(n.h==1+Math.max(n.left.h, n.right.h))
				return;
		}
		if(n.left!=null && n.right==null && n.h!=1+n.left.h)
			n.h=1+n.left.h;
		else if(n.left==null && n.right!=null && n.h!=1+n.right.h)
			n.h=1+n.right.h;
		else if(n.left!=null && n.right!=null)
			n.h=1+Math.max(n.left.h,n.right.h);
		else
			return;

		updateHeight(n.parent);
		return;
	}
	public static void updateHeight(obj n) {

		if(n==null) 
			return;
		if(n.left==null && n.right==null) {
			n.h=1;
			return;
		}
		if(n.left!=null && n.right!=null) {
			if(n.h==1+Math.max(n.left.h, n.right.h))
				return;
		}
		if(n.left!=null && n.right==null && n.h!=1+n.left.h)
			n.h=1+n.left.h;
		else if(n.left==null && n.right!=null && n.h!=1+n.right.h)
			n.h=1+n.right.h;
		else if(n.left!=null && n.right!=null)
			n.h=1+Math.max(n.left.h,n.right.h);
		else
			return;

		updateHeight(n.parent);
		return;
	}

	public static void Printobj(Object n) {
		if(n==null)
			return;
		System.out.println(n.capacity+" utkarsh"+"tanmay "+n.h+" "+n.objectID);
		if(n.left!=null)
			Printobj(n.left);


		if(n.right!=null)
			Printobj(n.right);
	}

	public static void Print(Bin n) {
		if(n==null)
			return;
		System.out.println(n.capacity+"  go "+n.pointBinID.pointbin.binID +" "+n.h);

		if(n.left!=null)
			Print(n.left);

		if(n.right!=null)
			Print(n.right);
	}

	public static void PrintobjID(binID n) {
		if(n==null)
			return;

		System.out.println(n.capacity+"   aditya  "+n.binID+" "+n.h);
		if(n.left!=null)
			PrintobjID(n.left);

		if(n.right!=null)
			PrintobjID(n.right);
	}

	//	public static void main(String[] args) {	
	//		try {
	//			FileInputStream fstream =new FileInputStream("/Users/tanmaygoyal/Downloads/new_medium.txt");
	//			Scanner s1 = new Scanner(fstream);
	//			while(s1.hasNextLine()) {
	//				str = s1.nextLine().split("\\s+");
	//				if(str[0].equals("1")) {
	//					add_bin(Integer.parseInt(str[1]),Integer.parseInt(str[2]));	
	//
	//				}
	//				if(str[0].equals("2")) {
	//					add_object(Integer.parseInt(str[1]),Integer.parseInt(str[2]));	
	//
	//
	//				}
	//				if(str[0].equals("3")) {
	//					delete_object(Integer.parseInt(str[1]));	
	//				}
	//				if(str[0].equals("4")) {
	//					contents1(Integer.parseInt(str[1]));
	//					//System.out.println("");
	//				}
	//			}
	//			s1.close();
	//		} catch (FileNotFoundException e) {
	//			// TODO Auto-generated catch block
	//			System.out.println("File not found");
	//		} catch (java.util.NoSuchElementException e) {
	//			//e.printStackTrace();FDle
	//		} 
	//		//		catch (NullPointerException e) {
	//		//			System.out.println(str[1]+"hh"+e);
	//		//		}
	//		//
	////				PrintobjID(binrootid);
	////				Print(binroot);
	////				Printobj(objroot);
	////
	////		//isBalanced(binroot.right.right);
	////		System.out.println(height(binroot.left)+" "+height(binroot.right));
	////		System.out.println(height(binroot.left.left)+" "+height(binroot.left.right));
	////		System.out.println(height(binroot.right.left)+" "+height(binroot.right.right));
	////		System.out.println(height(binroot.left.left.left)+" "+height(binroot.left.left.right));
	////		System.out.println(height(binroot.left.right.left)+" "+height(binroot.left.right.right));
	////		System.out.println(height(binroot.right.right.left)+" "+height(binroot.right.right.right));
	////		System.out.println(height(binroot.right.left.left)+" "+height(binroot.right.left.right));
	////		System.out.println("");
	////		System.out.println(height(objroot.left)+" "+height(objroot.right));
	////		System.out.println(height(objroot.left.left)+" "+height(objroot.left.right));
	////		System.out.println(height(objroot.right.left)+" "+height(objroot.right.right));
	////		System.out.println(height(objroot.left.left.left)+" "+height(objroot.left.left.right));
	////		System.out.println(height(objroot.left.right.left)+" "+height(objroot.left.right.right));
	////		System.out.println(height(objroot.right.right.left)+" "+height(objroot.right.right.right));
	////		System.out.println(height(objroot.right.left.left)+" "+height(objroot.right.left.right));
	////		System.out.println("");
	////		System.out.println(height(binrootid.left)+" "+height(binrootid.right));
	////		System.out.println(height(binrootid.left.left)+" "+height(binrootid.left.right));
	////		System.out.println(height(binrootid.right.left)+" "+height(binrootid.right.right));
	////		System.out.println(height(binrootid.left.left.left)+" "+height(binrootid.left.left.right));
	////		System.out.println(height(binrootid.left.right.left)+" "+height(binrootid.left.right.right));
	////		System.out.println(height(binrootid.right.right.left)+" "+height(binrootid.right.right.right));
	////		System.out.println(height(binrootid.right.left.left)+" "+height(binrootid.right.left.right));
	//	}

	public int delete_object(int rem){

		Object remove = findPosObject(rem, objroot);
		if(remove!=null) {
			binID b = new binID();
			b=remove.pointBinID;

			Bin b1 = remove.pointBinID.pointbin; 
			remove.pointBinID.capacity=remove.pointBinID.capacity+remove.capacity;
			b1.capacity=b1.capacity+remove.capacity;
			DeleteBin(b1);
			updateHeight(b1.parent);
			Bin n1 = isBalanced(b1.parent);

			if(n1==null) {

			}else {
				transform(n1,b1.parent);
			}


			DeleteObjectsmall(remove.pointobj,b);
			updateHeight(remove.pointobj.parent);

			AddBinAgain(b.binID,b.capacity);
			Object obj = findPosObject(rem, objroot);
			DeleteObject(obj);
			updateHeight(obj.parent);

			Object n = isBalanced(remove.parent);

			if(n==null) {

			}else {
				transform( n,remove.parent);//checkerssss
			}
			if(b!=null) {
				//	System.out.println(b.binID);
				return b.binID;
			}
			else
				return 0;
		}else
			return 0;
	}

	public static void DeleteBin(Bin n) {
		if(n==binroot) {

			if(n.left==null && n.right==null) {

				binroot=null;
				return;
			}else if(n.right==null) {

				binroot=n.left;
				return;
			}else {

				Bin k = inorderBSTb(n.right);
				n.capacity=k.capacity;
				n.binID=k.binID;
				n.pointBinID=k.pointBinID;
				k.pointBinID.pointbin=n;
				DeleteBin(k);
				updateHeight(k.parent);
			}	
		}else if(n.left==null) {

			if(n.right!=null) {
				if(n.parent.right == n) {
					n.parent.right=n.right;
					n.right.parent=n.parent;
				}else if(n.parent.left == n) {
					n.parent.left=n.right;
					n.right.parent=n.parent;

				}
			}else {

				if(n.parent.right == n) {
					n.parent.right=n.right;

				}else if(n.parent.left == n) {
					n.parent.left=n.right;
				}
			}
		}else if(n.right==null) {

			if(n.parent.right == n) {

				n.parent.right=n.left;
				n.left.parent=n.parent;

			}else if(n.parent.left == n){

				n.parent.left=n.left;
				n.left.parent=n.parent;

			}
		}else {
			Bin k = inorderBSTb(n.right);
			n.capacity=k.capacity;
			n.binID=k.binID;
			n.pointBinID=k.pointBinID;
			k.pointBinID.pointbin=n;
			k.pointBinID=null;
			DeleteBin(k);
			updateHeight(k.parent);
		}
	}

	public static Bin inorderBSTb(Bin n){
		if(n.left==null) {
			return n;
		}else {
			return inorderBSTb(n.left);
		}
	}

	public void add_bin(int id,int cap){
		if(check1==true) {
			check1=false;
			binroot.capacity = cap;
			binroot.left=null;
			binroot.right=null;
			binroot.parent=null;
			binroot.binID=id;
			binroot.pointBinID=binrootid;

			binrootid.left=null;
			binrootid.right=null;
			binrootid.parent=null;
			binrootid.binID=id;
			binrootid.capacity=cap;
			binrootid.pointbin=binroot;
		}else {

			Bin add = new Bin();
			Bin new1 = new Bin();
			add=binroot;
			add=getPositionBIN(cap,id,add);

			if(cap<add.capacity) {
				add.left=new1;
			}else if(cap>add.capacity) {
				add.right=new1;
			}else if(cap==add.capacity){
				if(id<add.binID) {
					add.left=new1;
				}else {
					add.right=new1;
				}
			}

			new1.binID=id;
			new1.capacity=cap;
			new1.left=null;
			new1.right=null;
			new1.parent=add;
			new1.h=1;
			updateHeight(add);

			binID addbinid = new binID();
			binID new1binid = new binID();
			addbinid=binrootid;
			addbinid=getPositionBINID(id,addbinid);

			if(id<addbinid.binID) {
				addbinid.left=new1binid;
			}else {
				addbinid.right=new1binid;
			}

			new1binid.binID=id;
			new1binid.capacity=cap;
			new1binid.left=null;
			new1binid.right=null;
			new1binid.parent=addbinid;
			new1binid.h=1;
			updateHeight(addbinid);
			new1binid.pointbin=new1;
			new1.pointBinID=new1binid;

			Bin n = isBalanced(new1);
			if(n==null) {

			}else {
				transform(n,new1);
			}

			binID n1 = isBalanced(new1binid);
			if(n1==null) {

			}else {
				transform(n1,new1binid);
			}
		}
	}

	static void transform(Bin n, Bin new1) {
		if(n==binroot) {
			if(lh-rh>1 && (n.left.left!=null )&&n.left.right==null) {
				Bin y = n.left.right;

				n.parent=n.left;
				n.left.right=n;
				binroot=n.left;
				binroot.parent=null;
				if(y!=null) {
					y.pointBinID.pointbin=y;
					n.left=y;
					n.left.parent=n;
					if(y.right!=null)
						y.right.parent=y;
					if(y.left!=null)
						y.left.parent=y;
				}else
					n.left=null;

				updateHeight(n);updateHeight(n.parent);
				return;
			}else if(lh-rh>1 && n.left.left==null && n.left.right!=null) {   
				Bin y = n.left.right;
				Bin z = n.left;
				y.pointBinID.pointbin=y;
				z.pointBinID.pointbin=z;

				z.parent.left=y;

				z.right=y.left;
				y.left=z;
				y.parent=z.parent;
				z.parent=y;
				if(y!=null) {
					if(y.left!=null)
						y.left.parent=y;
					if(y.right!=null)
						y.right.parent=y;
				}
				if(z!=null) {
					if(z.left!=null)
						z.left.parent=z;
					if(z.right!=null)
						z.right.parent=z;
				}
				updateHeight(z);updateHeight(y);
				transform(n,y);
				return;

			}else if(lh-rh>1){
				if(n.left.left!=null && n.left.right!=null) {
					if((n.left.left.h)>=(n.left.right.h)) {

						Bin y = n.left.right;

						n.parent=n.left;
						n.left.right=n;
						binroot=n.left;
						binroot.parent=null;
						if(y!=null) {
							y.pointBinID.pointbin=y;
							n.left=y;
							n.left.parent=n;
							if(y.right!=null)
								y.right.parent=y;
							if(y.left!=null)
								y.left.parent=y;
						}else
							n.left=null;
						updateHeight(n);updateHeight(n.parent);

					}else {
						Bin y = n.left.right;
						Bin z = n.left;
						y.pointBinID.pointbin=y;
						z.pointBinID.pointbin=z;

						z.parent.left=y;

						z.right=y.left;
						y.left=z;
						y.parent=z.parent;
						z.parent=y;
						if(y!=null) {
							if(y.left!=null)
								y.left.parent=y;
							if(y.right!=null)
								y.right.parent=y;
						}
						if(z!=null) {
							if(z.left!=null)
								z.left.parent=z;
							if(z.right!=null)
								z.right.parent=z;
						}
						updateHeight(z);updateHeight(y);
						transform(n,y);
						return;
					}
				}
			}else if(lh-rh<-1 && (n.right.right!=null)&&n.right.left==null) {  
				Bin y = n.right.left;
				n.parent=n.right;
				n.right.left=n;
				binroot=n.right;
				binroot.parent=null;
				if(y!=null) {
					y.pointBinID.pointbin=y;
					n.right=y;
					n.right.parent=n;
					if(y.right!=null)
						y.right.parent=y;
					if(y.left!=null)
						y.left.parent=y;
				}else
					n.right=null;
				updateHeight(n);updateHeight(n.parent);
				return;
			}else if(lh-rh<-1 && n.right.right==null && n.right.left!=null) {   
				Bin y = n.right.left;
				Bin z = n.right;
				y.pointBinID.pointbin=y;
				z.pointBinID.pointbin=z;

				z.parent.right=y;

				z.left=y.right;
				y.right=z;
				y.parent=z.parent;
				z.parent=y;
				if(y!=null) {
					if(y.left!=null)
						y.left.parent=y;
					if(y.right!=null)
						y.right.parent=y;
				}
				if(z!=null) {
					if(z.left!=null)
						z.left.parent=z;
					if(z.right!=null)
						z.right.parent=z;
				}
				updateHeight(z);updateHeight(y);
				transform(n,y);
				return;
			}else if(lh-rh<-1) {
				if(n.right.right!=null && n.right.left!=null) {
					if((n.right.right.h)>=(n.right.left.h)) {
						Bin y = n.right.left;
						n.parent=n.right;
						n.right.left=n;
						binroot=n.right;
						binroot.parent=null;
						if(y!=null) {
							y.pointBinID.pointbin=y;
							n.right=y;
							n.right.parent=n;
							if(y.right!=null)
								y.right.parent=y;
							if(y.left!=null)
								y.left.parent=y;
						}else
							n.right=null;
						updateHeight(n);updateHeight(n.parent);
						return;

					}else {
						Bin y = n.right.left;
						Bin z = n.right;
						y.pointBinID.pointbin=y;
						z.pointBinID.pointbin=z;

						z.parent.right=y;

						z.left=y.right;
						y.right=z;
						y.parent=z.parent;
						z.parent=y;
						if(y!=null) {
							if(y.left!=null)
								y.left.parent=y;
							if(y.right!=null)
								y.right.parent=y;
						}
						if(z!=null) {
							if(z.left!=null)
								z.left.parent=z;
							if(z.right!=null)
								z.right.parent=z;
						}
						updateHeight(z);updateHeight(y);
						transform(n,y);
						return;
					}
				}
			}

		}else {
			if(lh-rh<-1 && (n.right.right!=null) && n.right.left==null) {   
				if(n.parent.right==n) {
					n.parent.right=n.right;
					n.right.parent=n.parent;
				}else if(n.parent.left==n) {
					n.parent.left=n.right;
					n.right.parent=n.parent;
				}
				Bin y = n.right.left;

				n.parent=n.right;
				n.right.left=n;
				//			
				if(y!=null) {
					y.pointBinID.pointbin=y;
					n.right=y;
					n.right.parent=n;
					if(y.right!=null)
						y.right.parent=y;
					if(y.left!=null)
						y.left.parent=y;
				}else
					n.right=null;
				updateHeight(n);updateHeight(n.parent);updateHeight(n.parent.parent);
				if(n.parent.parent.left!=null)
					lh=n.parent.parent.left.h;
				else 
					lh=0;

				if(n.parent.parent.right!=null)
					rh=n.parent.parent.right.h;
				else 
					rh=0;

				if(Math.abs(lh-rh)>1){						
					transform(n.parent.parent,n.parent.parent);
				}
				return;

			}else if(lh-rh<-1 && n.right.right==null && n.right.left!=null) {   

				Bin y = n.right.left;
				Bin z = n.right;
				y.pointBinID.pointbin=y;
				z.pointBinID.pointbin=z;

				z.parent.right=y;

				z.left=y.right;
				y.right=z;
				y.parent=z.parent;
				z.parent=y;
				if(y!=null) {
					if(y.left!=null)
						y.left.parent=y;
					if(y.right!=null)
						y.right.parent=y;
				}
				if(z!=null) {
					if(z.left!=null)
						z.left.parent=z;
					if(z.right!=null)
						z.right.parent=z;
				}
				updateHeight(z);updateHeight(y);
				transform(n,y);
				return;
			}else if(lh-rh<-1) {
				if(n.right.right!=null && n.right.left!=null) {
					if((n.right.right.h)>=(n.right.left.h)) {
						if(n.parent.right==n) {
							n.parent.right=n.right;
							n.right.parent=n.parent;
						}else if(n.parent.left==n) {
							n.parent.left=n.right;
							n.right.parent=n.parent;
						}
						Bin y = n.right.left;

						n.parent=n.right;
						n.right.left=n;
						//			
						if(y!=null) {
							y.pointBinID.pointbin=y;
							n.right=y;
							n.right.parent=n;
							if(y.right!=null)
								y.right.parent=y;
							if(y.left!=null)
								y.left.parent=y;
						}else
							n.right=null;
						updateHeight(n);updateHeight(n.parent);updateHeight(n.parent.parent);

						if(n.parent.parent.left!=null)
							lh=n.parent.parent.left.h;
						else 
							lh=0;

						if(n.parent.parent.right!=null)
							rh=n.parent.parent.right.h;
						else 
							rh=0;
						if(Math.abs(lh-rh)>1){					
							transform(n.parent.parent,n.parent.parent);
						}

						return;
					}else {
						Bin y = n.right.left;
						Bin z = n.right;
						y.pointBinID.pointbin=y;
						z.pointBinID.pointbin=z;

						z.parent.right=y;

						z.left=y.right;
						y.right=z;
						y.parent=z.parent;
						z.parent=y;
						if(y!=null) {
							if(y.left!=null)
								y.left.parent=y;
							if(y.right!=null)
								y.right.parent=y;
						}
						if(z!=null) {
							if(z.left!=null)
								z.left.parent=z;
							if(z.right!=null)
								z.right.parent=z;
						}
						updateHeight(z);updateHeight(y);

						transform(n,y);
						return;
					}
				}
			}else if(lh-rh>1 && (n.left.left!=null) && n.left.right==null) {
				if(n.parent.right==n) {
					n.parent.right=n.left;
					n.left.parent=n.parent;
				}else if(n.parent.left==n) {
					n.parent.left=n.left;
					n.left.parent=n.parent;
				}
				Bin y = n.left.right;
				n.parent=n.left;
				n.left.right=n;
				if(y!=null) {
					y.pointBinID.pointbin=y;
					n.left=y;
					n.left.parent=n;
					if(y.right!=null)
						y.right.parent=y;
					if(y.left!=null)
						y.left.parent=y;
				}else
					n.left=null;
				updateHeight(n);updateHeight(n.parent);updateHeight(n.parent.parent);
				if(n.parent.parent.left!=null)
					lh=n.parent.parent.left.h;
				else 
					lh=0;

				if(n.parent.parent.right!=null)
					rh=n.parent.parent.right.h;
				else 
					rh=0;
				if(Math.abs(lh-rh)>1){					
					transform(n.parent.parent,n.parent.parent);
				}
				return;
			}else if(lh-rh>1 && n.left.left==null && n.left.right!=null) {   

				Bin y = n.left.right;
				Bin z = n.left;
				y.pointBinID.pointbin=y;
				z.pointBinID.pointbin=z;

				z.parent.left=y;

				z.right=y.left;
				y.left=z;
				y.parent=z.parent;
				z.parent=y;
				if(y!=null) {
					if(y.left!=null)
						y.left.parent=y;
					if(y.right!=null)
						y.right.parent=y;
				}
				if(z!=null) {
					if(z.left!=null)
						z.left.parent=z;
					if(z.right!=null)
						z.right.parent=z;
				}
				updateHeight(z);updateHeight(y);
				transform(n,y);
				return;
			}else if(lh-rh>1) {
				if(n.left.left!=null && n.left.right!=null) {
					if((n.left.left.h)>=(n.left.right.h)) {
						if(n.parent.right==n) {
							n.parent.right=n.left;
							n.left.parent=n.parent;
						}else if(n.parent.left==n) {
							n.parent.left=n.left;
							n.left.parent=n.parent;
						}
						Bin y = n.left.right;

						n.parent=n.left;
						n.left.right=n;

						if(y!=null) {
							y.pointBinID.pointbin=y;
							n.left=y;
							n.left.parent=n;
							if(y.right!=null)
								y.right.parent=y;
							if(y.left!=null)
								y.left.parent=y;
						}else
							n.left=null;
						updateHeight(n);updateHeight(n.parent);updateHeight(n.parent.parent);
						if(n.parent.parent.left!=null)
							lh=n.parent.parent.left.h;
						else 
							lh=0;

						if(n.parent.parent.right!=null)
							rh=n.parent.parent.right.h;
						else 
							rh=0;
						if(Math.abs(lh-rh)>1){					
							transform(n.parent.parent,n.parent.parent);
						}
						return;
					}else {
						Bin y = n.left.right;
						Bin z = n.left;
						y.pointBinID.pointbin=y;
						z.pointBinID.pointbin=z;

						z.parent.left=y;

						z.right=y.left;
						y.left=z;
						y.parent=z.parent;
						z.parent=y;
						if(y!=null) {
							if(y.left!=null)
								y.left.parent=y;
							if(y.right!=null)
								y.right.parent=y;
						}
						if(z!=null) {
							if(z.left!=null)
								z.left.parent=z;
							if(z.right!=null)
								z.right.parent=z;
						}
						updateHeight(z);updateHeight(y);
						transform(n,y);
						return;
					}
				}
			}
		}
	}

	static void transform(binID n, binID new1) {
		if(n==binrootid) {

			if(lh-rh>1 && (n.left.left!=null )&&n.left.right==null) {
				binID y = n.left.right;

				n.parent=n.left;
				n.left.right=n;
				binrootid=n.left;
				binrootid.parent=null;
				if(y!=null) {
					y.pointbin.pointBinID=y;
					if(y.right!=null)
						y.right.parent=y;
					if(y.left!=null)
						y.left.parent=y;
					n.left=y;
					n.left.parent=n;
				}else
					n.left=null;
				updateHeight(n);updateHeight(n.parent);
				return;
			}else if(lh-rh>1 && n.left.left==null && n.left.right!=null) {    
				binID y = n.left.right;
				binID z = n.left;
				y.pointbin.pointBinID=y;
				z.pointbin.pointBinID=z;

				z.parent.left=y;

				z.right=y.left;
				y.left=z;
				y.parent=z.parent;
				z.parent=y;
				if(y!=null) {
					if(y.left!=null)
						y.left.parent=y;
					if(y.right!=null)
						y.right.parent=y;
				}
				if(z!=null) {
					if(z.left!=null)
						z.left.parent=z;
					if(z.right!=null)
						z.right.parent=z;
				}
				updateHeight(z);updateHeight(y);
				transform(n,y);
				return;
			}else if(lh-rh>1){
				if(n.left.left!=null && n.left.right!=null) {
					if((n.left.left.h)>=(n.left.right.h)) {
						binID y = n.left.right;

						n.parent=n.left;
						n.left.right=n;
						binrootid=n.left;
						binrootid.parent=null;
						if(y!=null) {
							y.pointbin.pointBinID=y;
							if(y.right!=null)
								y.right.parent=y;
							if(y.left!=null)
								y.left.parent=y;
							n.left=y;
							n.left.parent=n;
						}else
							n.left=null;
						updateHeight(n);updateHeight(n.parent);
						return;
					}else {
						binID y = n.left.right;
						binID z = n.left;
						y.pointbin.pointBinID=y;
						z.pointbin.pointBinID=z;

						z.parent.left=y;

						z.right=y.left;
						y.left=z;
						y.parent=z.parent;
						z.parent=y;
						if(y!=null) {
							if(y.left!=null)
								y.left.parent=y;
							if(y.right!=null)
								y.right.parent=y;
						}
						if(z!=null) {
							if(z.left!=null)
								z.left.parent=z;
							if(z.right!=null)
								z.right.parent=z;
						}
						updateHeight(z);updateHeight(y);
						transform(n,y);
						return;
					}
				}
			}else if(lh-rh<-1 && (n.right.right!=null)&&n.right.left==null) {  
				binID y = n.right.left;

				n.parent=n.right;
				n.right.left=n;
				binrootid=n.right;
				binrootid.parent=null;
				if(y!=null) {
					y.pointbin.pointBinID=y;
					if(y.right!=null)
						y.right.parent=y;
					if(y.left!=null)
						y.left.parent=y;
					n.right=y;
					n.right.parent=n;
				}else
					n.right=null;
				updateHeight(n);updateHeight(n.parent);
				return;

			}else if(lh-rh<-1 && n.right.right==null && n.right.left!=null) {    
				binID y = n.right.left;
				binID z = n.right;
				y.pointbin.pointBinID=y;
				z.pointbin.pointBinID=z;

				z.parent.right=y;

				z.left=y.right;
				y.right=z;
				y.parent=z.parent;
				z.parent=y;
				if(y!=null) {
					if(y.left!=null)
						y.left.parent=y;
					if(y.right!=null)
						y.right.parent=y;
				}
				if(z!=null) {
					if(z.left!=null)
						z.left.parent=z;
					if(z.right!=null)
						z.right.parent=z;
				}
				updateHeight(z);updateHeight(y);
				transform(n,y);
				return;
			}else if(lh-rh<-1) {
				if(n.right.right!=null && n.right.left!=null) {
					if((n.right.right.h)>=(n.right.left.h)) {
						binID y = n.right.left;

						n.parent=n.right;
						n.right.left=n;
						binrootid=n.right;
						binrootid.parent=null;
						if(y!=null) {
							y.pointbin.pointBinID=y;
							if(y.right!=null)
								y.right.parent=y;
							if(y.left!=null)
								y.left.parent=y;
							n.right=y;
							n.right.parent=n;
						}else
							n.right=null;
						updateHeight(n);updateHeight(n.parent);
						return;
					}else {
						binID y = n.right.left;
						binID z = n.right;
						y.pointbin.pointBinID=y;
						z.pointbin.pointBinID=z;

						z.parent.right=y;

						z.left=y.right;
						y.right=z;
						y.parent=z.parent;
						z.parent=y;
						if(y!=null) {
							if(y.left!=null)
								y.left.parent=y;
							if(y.right!=null)
								y.right.parent=y;
						}
						if(z!=null) {
							if(z.left!=null)
								z.left.parent=z;
							if(z.right!=null)
								z.right.parent=z;
						}
						updateHeight(z);updateHeight(y);
						transform(n,y);
						return;
					}
				}
			}

		}else {
			if(lh-rh<-1 && (n.right.right!=null) && n.right.left==null) {     

				if(n.parent.right==n) {
					n.parent.right=n.right;
					n.right.parent=n.parent;
				}else if(n.parent.left==n) {
					n.parent.left=n.right;
					n.right.parent=n.parent;
				}
				binID y = n.right.left;

				n.parent=n.right;
				n.right.left=n;			
				if(y!=null) {
					y.pointbin.pointBinID=y;
					n.right=y;
					n.right.parent=n;
					if(y.right!=null)
						y.right.parent=y;
					if(y.left!=null)
						y.left.parent=y;
				}else
					n.right=null;
				updateHeight(n);updateHeight(n.parent);updateHeight(n.parent.parent);
				if(n.parent.parent.left!=null)
					lh=n.parent.parent.left.h;
				else 
					lh=0;

				if(n.parent.parent.right!=null)
					rh=n.parent.parent.right.h;
				else 
					rh=0;

				if(Math.abs(lh-rh)>1){						
					transform(n.parent.parent,n.parent.parent);
				}
				return;
			}else if(lh-rh<-1 && n.right.right==null && n.right.left!=null) {   
				binID y = n.right.left;
				binID z = n.right;
				y.pointbin.pointBinID=y;
				z.pointbin.pointBinID=z;

				z.parent.right=y;

				z.left=y.right;
				y.right=z;
				y.parent=z.parent;
				z.parent=y;
				if(y!=null) {
					if(y.left!=null)
						y.left.parent=y;
					if(y.right!=null)
						y.right.parent=y;
				}
				if(z!=null) {
					if(z.left!=null)
						z.left.parent=z;
					if(z.right!=null)
						z.right.parent=z;
				}
				updateHeight(z);updateHeight(y);
				transform(n,y);
				return;
			}else if(lh-rh<-1) {
				if(n.right.right!=null && n.right.left!=null) {
					if((n.right.right.h)>=(n.right.left.h)) {
						if(n.parent.right==n) {
							n.parent.right=n.right;
							n.right.parent=n.parent;
						}else if(n.parent.left==n) {
							n.parent.left=n.right;
							n.right.parent=n.parent;
						}
						binID y = n.right.left;

						n.parent=n.right;
						n.right.left=n;
						//			
						if(y!=null) {
							y.pointbin.pointBinID=y;
							n.right=y;
							n.right.parent=n;
							if(y.right!=null)
								y.right.parent=y;
							if(y.left!=null)
								y.left.parent=y;
						}else
							n.right=null;
						updateHeight(n);updateHeight(n.parent);updateHeight(n.parent.parent);
						if(n.parent.parent.left!=null)
							lh=n.parent.parent.left.h;
						else 
							lh=0;

						if(n.parent.parent.right!=null)
							rh=n.parent.parent.right.h;
						else 
							rh=0;

						if(Math.abs(lh-rh)>1){						
							transform(n.parent.parent,n.parent.parent);
						}
						return;
					}else {
						binID y = n.right.left;
						binID z = n.right;
						y.pointbin.pointBinID=y;
						z.pointbin.pointBinID=z;

						z.parent.right=y;

						z.left=y.right;
						y.right=z;
						y.parent=z.parent;
						z.parent=y;
						if(y!=null) {
							if(y.left!=null)
								y.left.parent=y;
							if(y.right!=null)
								y.right.parent=y;
						}
						if(z!=null) {
							if(z.left!=null)
								z.left.parent=z;
							if(z.right!=null)
								z.right.parent=z;
						}
						updateHeight(z);updateHeight(y);
						transform(n,y);
						return;
					}
				}
			}else if(lh-rh>1 && (n.left.left!=null) && n.left.right==null) {
				if(n.parent.right==n) {
					n.parent.right=n.left;
					n.left.parent=n.parent;
				}else if(n.parent.left==n) {
					n.parent.left=n.left;
					n.left.parent=n.parent;
				}
				binID y = n.left.right;
				n.parent=n.left;
				n.left.right=n;
				if(y!=null) {
					y.pointbin.pointBinID=y;
					n.left=y;
					n.left.parent=n;
					if(y.right!=null)
						y.right.parent=y;
					if(y.left!=null)
						y.left.parent=y;
				}else
					n.left=null;
				updateHeight(n);updateHeight(n.parent);updateHeight(n.parent.parent);
				if(n.parent.parent.left!=null)
					lh=n.parent.parent.left.h;
				else 
					lh=0;

				if(n.parent.parent.right!=null)
					rh=n.parent.parent.right.h;
				else 
					rh=0;
				if(Math.abs(lh-rh)>1){					
					transform(n.parent.parent,n.parent.parent);
				}
				return;

			}else if(lh-rh>1 && n.left.left==null && n.left.right!=null) {   
				binID y = n.left.right;
				binID z = n.left;
				y.pointbin.pointBinID=y;
				z.pointbin.pointBinID=z;

				z.parent.left=y;

				z.right=y.left;
				y.left=z;
				y.parent=z.parent;
				z.parent=y;
				if(y!=null) {
					if(y.left!=null)
						y.left.parent=y;
					if(y.right!=null)
						y.right.parent=y;
				}
				if(z!=null) {
					if(z.left!=null)
						z.left.parent=z;
					if(z.right!=null)
						z.right.parent=z;
				}
				updateHeight(z);updateHeight(y);
				transform(n,y);
				return;
			}else if(lh-rh>1) {
				if(n.left.left!=null && n.left.right!=null) {
					if((n.left.left.h)>=(n.left.right.h)) {
						if(n.parent.right==n) {
							n.parent.right=n.left;
							n.left.parent=n.parent;
						}else if(n.parent.left==n) {
							n.parent.left=n.left;
							n.left.parent=n.parent;
						}
						binID y = n.left.right;
						n.parent=n.left;
						n.left.right=n;
						if(y!=null) {
							y.pointbin.pointBinID=y;
							n.left=y;
							n.left.parent=n;
							if(y.right!=null)
								y.right.parent=y;
							if(y.left!=null)
								y.left.parent=y;
						}else
							n.left=null;
						updateHeight(n);updateHeight(n.parent);updateHeight(n.parent.parent);
						if(n.parent.parent.left!=null)
							lh=n.parent.parent.left.h;
						else 
							lh=0;

						if(n.parent.parent.right!=null)
							rh=n.parent.parent.right.h;
						else 
							rh=0;
						if(Math.abs(lh-rh)>1){					
							transform(n.parent.parent,n.parent.parent);
						}
						return;
					}else {
						binID y = n.left.right;
						binID z = n.left;
						y.pointbin.pointBinID=y;
						z.pointbin.pointBinID=z;

						z.parent.left=y;

						z.right=y.left;
						y.left=z;
						y.parent=z.parent;
						z.parent=y;
						if(y!=null) {
							if(y.left!=null)
								y.left.parent=y;
							if(y.right!=null)
								y.right.parent=y;
						}
						if(z!=null) {
							if(z.left!=null)
								z.left.parent=z;
							if(z.right!=null)
								z.right.parent=z;
						}
						updateHeight(z);updateHeight(y);
						transform(n,y);
						return;
					}
				}
			}
		}

	}


	static void transform(Object n, Object new1) {

		if(n==objroot) {

			if(lh-rh>1 && (n.left.left!=null )&&n.left.right==null) {
				Object y = n.left.right;

				n.parent=n.left;
				n.left.right=n;
				objroot=n.left;
				objroot.parent=null;
				if(y!=null) {
					y.pointobj.pointobjid=y;
					n.left=y;
					n.left.parent=n;
					if(y.right!=null)
						y.right.parent=y;
					if(y.left!=null)
						y.left.parent=y;
				}else
					n.left=null;
				updateHeight(n);updateHeight(n.parent);
				return;
			}else if(lh-rh>1 && n.left.left==null && n.left.right!=null) {   

				Object y = n.left.right;
				Object z = n.left;
				y.pointobj.pointobjid=y;
				z.pointobj.pointobjid=z;

				z.parent.left=y;

				z.right=y.left;
				y.left=z;
				y.parent=z.parent;
				z.parent=y;
				if(y!=null) {
					if(y.left!=null)
						y.left.parent=y;
					if(y.right!=null)
						y.right.parent=y;
				}
				if(z!=null) {
					if(z.left!=null)
						z.left.parent=z;
					if(z.right!=null)
						z.right.parent=z;
				}
				updateHeight(z);updateHeight(y);
				transform(n,y);
				return;
			}else if(lh-rh>1){
				if(n.left.left!=null && n.left.right!=null) {
					if((n.left.left.h)>=(n.left.right.h)) {
						Object y = n.left.right;

						n.parent=n.left;
						n.left.right=n;
						objroot=n.left;
						objroot.parent=null;
						if(y!=null) {
							y.pointobj.pointobjid=y;
							n.left=y;
							n.left.parent=n;
							if(y.right!=null)
								y.right.parent=y;
							if(y.left!=null)
								y.left.parent=y;
						}else
							n.left=null;
						updateHeight(n);updateHeight(n.parent);
						return;

					}else {
						Object y = n.left.right;
						Object z = n.left;
						y.pointobj.pointobjid=y;
						z.pointobj.pointobjid=z;

						z.parent.left=y;

						z.right=y.left;
						y.left=z;
						y.parent=z.parent;
						z.parent=y;
						if(y!=null) {
							if(y.left!=null)
								y.left.parent=y;
							if(y.right!=null)
								y.right.parent=y;
						}
						if(z!=null) {
							if(z.left!=null)
								z.left.parent=z;
							if(z.right!=null)
								z.right.parent=z;
						}
						updateHeight(z);updateHeight(y);
						transform(n,y);
						return;
					}
				}
			}else if(lh-rh<-1 && (n.right.right!=null)&&n.right.left==null) {   
				Object y = n.right.left;

				n.parent=n.right;
				n.right.left=n;
				objroot=n.right;
				objroot.parent=null;
				if(y!=null) {
					y.pointobj.pointobjid=y;
					n.right=y;
					n.right.parent=n;
					if(y.right!=null)
						y.right.parent=y;
					if(y.left!=null)
						y.left.parent=y;
				}else
					n.right=null;
				updateHeight(n);updateHeight(n.parent);
				return;			
			}else if(lh-rh<-1 && n.right.right==null && n.right.left!=null) {   
				Object y = n.right.left;
				Object z = n.right;

				y.pointobj.pointobjid=y;
				z.pointobj.pointobjid=z;

				z.parent.right=y;

				z.left=y.right;
				y.right=z;
				y.parent=z.parent;
				z.parent=y;
				if(y!=null) {
					if(y.left!=null)
						y.left.parent=y;
					if(y.right!=null)
						y.right.parent=y;
				}
				if(z!=null) {
					if(z.left!=null)
						z.left.parent=z;
					if(z.right!=null)
						z.right.parent=z;
				}
				updateHeight(z);updateHeight(y);
				transform(n,y);
				return;
			}else if(lh-rh<-1) {
				if(n.right.right!=null && n.right.left!=null) {
					if((n.right.right.h)>=(n.right.left.h)) {
						Object y = n.right.left;

						n.parent=n.right;
						n.right.left=n;
						objroot=n.right;
						objroot.parent=null;

						if(y!=null) {
							y.pointobj.pointobjid=y;
							n.right=y;
							n.right.parent=n;
							if(y.right!=null)
								y.right.parent=y;
							if(y.left!=null)
								y.left.parent=y;
						}else
							n.right=null;
						updateHeight(n);updateHeight(n.parent);
						return;			
					}else {
						Object y = n.right.left;
						Object z = n.right;

						y.pointobj.pointobjid=y;
						z.pointobj.pointobjid=z;

						z.parent.right=y;

						z.left=y.right;
						y.right=z;
						y.parent=z.parent;
						z.parent=y;
						if(y!=null) {
							if(y.left!=null)
								y.left.parent=y;
							if(y.right!=null)
								y.right.parent=y;
						}
						if(z!=null) {
							if(z.left!=null)
								z.left.parent=z;
							if(z.right!=null)
								z.right.parent=z;
						}
						updateHeight(z);updateHeight(y);
						transform(n,y);
						return;
					}
				}
			}
		}else {
			if(lh-rh<-1 && (n.right.right!=null) && n.right.left==null) {     

				if(n.parent.right==n) {
					n.parent.right=n.right;
					n.right.parent=n.parent;
				}else if(n.parent.left==n) {
					n.parent.left=n.right;
					n.right.parent=n.parent;
				}
				Object y = n.right.left;

				n.parent=n.right;
				n.right.left=n;
				//			
				if(y!=null) {
					y.pointobj.pointobjid=y;
					n.right=y;
					n.right.parent=n;
					if(y.right!=null)
						y.right.parent=y;
					if(y.left!=null)
						y.left.parent=y;
				}else
					n.right=null;
				updateHeight(n);updateHeight(n.parent);updateHeight(n.parent.parent);
				if(n.parent.parent.left!=null)
					lh=n.parent.parent.left.h;
				else 
					lh=0;

				if(n.parent.parent.right!=null)
					rh=n.parent.parent.right.h;
				else 
					rh=0;
				if(Math.abs(lh-rh)>1){						
					transform(n.parent.parent,n.parent.parent);
				}
				return;				
			}else if(lh-rh<-1 && n.right.right==null && n.right.left!=null) {   
				Object y = n.right.left;
				Object z = n.right;
				y.pointobj.pointobjid=y;
				z.pointobj.pointobjid=z;

				z.parent.right=y;

				z.left=y.right;
				y.right=z;
				y.parent=z.parent;
				z.parent=y;
				if(y!=null) {
					if(y.left!=null)
						y.left.parent=y;
					if(y.right!=null)
						y.right.parent=y;
				}
				if(z!=null) {
					if(z.left!=null)
						z.left.parent=z;
					if(z.right!=null)
						z.right.parent=z;
				}
				updateHeight(z);updateHeight(y);
				transform(n,y);
				return;
			}else if(lh-rh<-1) {
				if(n.right.right!=null && n.right.left!=null) {
					if((n.right.right.h)>=(n.right.left.h)) {
						if(n.parent.right==n) {
							n.parent.right=n.right;
							n.right.parent=n.parent;
						}else if(n.parent.left==n) {
							n.parent.left=n.right;
							n.right.parent=n.parent;
						}
						Object y = n.right.left;

						n.parent=n.right;
						n.right.left=n;
						//			
						if(y!=null) {
							y.pointobj.pointobjid=y;
							n.right=y;
							n.right.parent=n;
							if(y.right!=null)
								y.right.parent=y;
							if(y.left!=null)
								y.left.parent=y;
						}else
							n.right=null;
						updateHeight(n);updateHeight(n.parent);updateHeight(n.parent.parent);
						if(n.parent.parent.left!=null)
							lh=n.parent.parent.left.h;
						else 
							lh=0;

						if(n.parent.parent.right!=null)
							rh=n.parent.parent.right.h;
						else 
							rh=0;
						if(Math.abs(lh-rh)>1){						
							transform(n.parent.parent,n.parent.parent);
						}
						return;
					}else {
						Object y = n.right.left;
						Object z = n.right;
						y.pointobj.pointobjid=y;
						z.pointobj.pointobjid=z;

						z.parent.right=y;

						z.left=y.right;
						y.right=z;
						y.parent=z.parent;
						z.parent=y;
						if(y!=null) {
							if(y.left!=null)
								y.left.parent=y;
							if(y.right!=null)
								y.right.parent=y;
						}
						if(z!=null) {
							if(z.left!=null)
								z.left.parent=z;
							if(z.right!=null)
								z.right.parent=z;
						}
						updateHeight(z);updateHeight(y);
						transform(n,y);
						return;
					}
				}
			}else if(lh-rh>1 && (n.left.left!=null) && n.left.right==null) {
				if(n.parent.right==n) {
					n.parent.right=n.left;
					n.left.parent=n.parent;
				}else if(n.parent.left==n) {
					n.parent.left=n.left;
					n.left.parent=n.parent;
				}
				Object y = n.left.right;

				n.parent=n.left;
				n.left.right=n;
				if(y!=null) {
					y.pointobj.pointobjid=y;
					n.left=y;
					n.left.parent=n;
					if(y.right!=null)
						y.right.parent=y;
					if(y.left!=null)
						y.left.parent=y;
				}else
					n.left=null;
				updateHeight(n);updateHeight(n.parent);updateHeight(n.parent.parent);
				if(n.parent.parent.left!=null)
					lh=n.parent.parent.left.h;
				else 
					lh=0;

				if(n.parent.parent.right!=null)
					rh=n.parent.parent.right.h;
				else 
					rh=0;
				if(Math.abs(lh-rh)>1){					
					transform(n.parent.parent,n.parent.parent);
				}
				return;
			}else if(lh-rh>1 && n.left.left==null && n.left.right!=null) { 
				Object y = n.left.right;
				Object z = n.left;
				y.pointobj.pointobjid=y;
				z.pointobj.pointobjid=z;

				z.parent.left=y;

				z.right=y.left;
				y.left=z;
				y.parent=z.parent;
				z.parent=y;
				if(y!=null) {
					if(y.left!=null)
						y.left.parent=y;
					if(y.right!=null)
						y.right.parent=y;
				}
				if(z!=null) {
					if(z.left!=null)
						z.left.parent=z;
					if(z.right!=null)
						z.right.parent=z;
				}
				updateHeight(z);updateHeight(y);
				transform(n,y);
				return;
			}else if(lh-rh>1) {
				if(n.left.left!=null && n.left.right!=null) {
					if((n.left.left.h)>=(n.left.right.h)) {
						if(n.parent.right==n) {
							n.parent.right=n.left;
							n.left.parent=n.parent;
						}else if(n.parent.left==n) {
							n.parent.left=n.left;
							n.left.parent=n.parent;
						}
						Object y = n.left.right;

						n.parent=n.left;
						n.left.right=n;
						if(y!=null) {
							y.pointobj.pointobjid=y;
							n.left=y;
							n.left.parent=n;
							if(y.right!=null)
								y.right.parent=y;
							if(y.left!=null)
								y.left.parent=y;
						}else
							n.left=null;
						updateHeight(n);updateHeight(n.parent);updateHeight(n.parent.parent);
						if(n.parent.parent.left!=null)
							lh=n.parent.parent.left.h;
						else 
							lh=0;

						if(n.parent.parent.right!=null)
							rh=n.parent.parent.right.h;
						else 
							rh=0;
						if(Math.abs(lh-rh)>1){					
							transform(n.parent.parent,n.parent.parent);
						}
						return;
					}else {
						Object y = n.left.right;
						Object z = n.left;
						y.pointobj.pointobjid=y;
						z.pointobj.pointobjid=z;

						z.parent.left=y;

						z.right=y.left;
						y.left=z;
						y.parent=z.parent;
						z.parent=y;
						if(y!=null) {
							if(y.left!=null)
								y.left.parent=y;
							if(y.right!=null)
								y.right.parent=y;
						}
						if(z!=null) {
							if(z.left!=null)
								z.left.parent=z;
							if(z.right!=null)
								z.right.parent=z;
						}
						updateHeight(z);updateHeight(y);
						transform(n,y);
						return;
					}
				}
			}
		}
	}

	static void transform(obj n, obj new1, binID b) {
		if(n!=null && new1!=null) {
			if(n==b.root || n.parent==null) {
				if(lh-rh>1 && (n.left.left!=null )&&n.left.right==null) {
					obj y = n.left.right;

					n.parent=n.left;
					n.left.right=n;
					b.root=n.left;
					b.root.parent=null;
					if(y!=null) {
						y.pointobjid.pointobj=y;
						n.left=y;
						n.left.parent=n;
						if(y.right!=null)
							y.right.parent=y;
						if(y.left!=null)
							y.left.parent=y;
					}else
						n.left=null;
					updateHeight(n);updateHeight(n.parent);
					return;

				}else if(lh-rh>1 && n.left.left==null && n.left.right!=null) {   
					obj y = n.left.right;
					obj z = n.left;
					y.pointobjid.pointobj=y;
					z.pointobjid.pointobj=z;

					z.parent.left=y;

					z.right=y.left;
					y.left=z;
					y.parent=z.parent;
					z.parent=y;
					if(y!=null) {
						if(y.left!=null)
							y.left.parent=y;
						if(y.right!=null)
							y.right.parent=y;
					}
					if(z!=null) {
						if(z.left!=null)
							z.left.parent=z;
						if(z.right!=null)
							z.right.parent=z;
					}
					updateHeight(z);updateHeight(y);
					transform(n,y,b);
					return;
				}else if(lh-rh>1){
					if((n.left.left)!=null && (n.left.right)!=null) {
						if((n.left.left.h)>=(n.left.right.h)) {
							obj y = n.left.right;

							n.parent=n.left;
							n.left.right=n;
							b.root=n.left;
							b.root.parent=null;
							if(y!=null) {
								y.pointobjid.pointobj=y;
								n.left=y;
								n.left.parent=n;
								if(y.right!=null)
									y.right.parent=y;
								if(y.left!=null)
									y.left.parent=y;
							}else
								n.left=null;
							updateHeight(n);updateHeight(n.parent);
							return;
						}else {
							obj y = n.left.right;
							obj z = n.left;
							y.pointobjid.pointobj=y;
							z.pointobjid.pointobj=z;

							z.parent.left=y;

							z.right=y.left;
							y.left=z;
							y.parent=z.parent;
							z.parent=y;
							if(y!=null) {
								if(y.left!=null)
									y.left.parent=y;
								if(y.right!=null)
									y.right.parent=y;
							}
							if(z!=null) {
								if(z.left!=null)
									z.left.parent=z;
								if(z.right!=null)
									z.right.parent=z;
							}
							updateHeight(z);updateHeight(y);
							transform(n,y,b);
							return;
						}
					}
				}else if(lh-rh<-1 && (n.right.right!=null)&&n.right.left==null) {  
					obj y = n.right.left;
					n.parent=n.right;
					n.right.left=n;
					b.root=n.right;
					b.root.parent=null;
					if(y!=null) {
						y.pointobjid.pointobj=y;
						n.right=y;
						n.right.parent=n;
						if(y.right!=null)
							y.right.parent=y;
						if(y.left!=null)
							y.left.parent=y;
					}else
						n.right=null;
					updateHeight(n);updateHeight(n.parent);
					return;			
				}else if(lh-rh<-1 && n.right.right==null && n.right.left!=null) {   
					obj y = n.right.left;
					obj z = n.right;
					y.pointobjid.pointobj=y;
					z.pointobjid.pointobj=z;

					z.parent.right=y;

					z.left=y.right;
					y.right=z;
					y.parent=z.parent;
					z.parent=y;
					if(y!=null) {
						if(y.left!=null)
							y.left.parent=y;
						if(y.right!=null)
							y.right.parent=y;
					}
					if(z!=null) {
						if(z.left!=null)
							z.left.parent=z;
						if(z.right!=null)
							z.right.parent=z;
					}

					transform(n,y,b);
					updateHeight(z);updateHeight(y);
					return;
				}else if(lh-rh<-1) {
					if((n.right.right!=null)&&(n.right.left!=null)) {
						if((n.right.right.h)>=(n.right.left.h)) {
							obj y = n.right.left;
							n.parent=n.right;
							n.right.left=n;
							b.root=n.right;
							b.root.parent=null;
							if(y!=null) {
								y.pointobjid.pointobj=y;
								n.right=y;
								n.right.parent=n;
								if(y.right!=null)
									y.right.parent=y;
								if(y.left!=null)
									y.left.parent=y;
							}else
								n.right=null;
							updateHeight(n);updateHeight(n.parent);
							return;			

						}else {
							obj y = n.right.left;
							obj z = n.right;
							y.pointobjid.pointobj=y;
							z.pointobjid.pointobj=z;

							z.parent.right=y;

							z.left=y.right;
							y.right=z;
							y.parent=z.parent;
							z.parent=y;
							if(y!=null) {
								if(y.left!=null)
									y.left.parent=y;
								if(y.right!=null)
									y.right.parent=y;
							}
							if(z!=null) {
								if(z.left!=null)
									z.left.parent=z;
								if(z.right!=null)
									z.right.parent=z;
							}

							transform(n,y,b);
							updateHeight(z);updateHeight(y);
							return;
						}
					}
				}
			}else {
				if(lh-rh<-1 && (n.right.right!=null) && n.right.left==null) {   
					if(n.parent.right==n) {
						n.parent.right=n.right;
						n.right.parent=n.parent;
					}else if(n.parent.left==n) {
						n.parent.left=n.right;
						n.right.parent=n.parent;
					}
					obj y = n.right.left;

					n.parent=n.right;
					n.right.left=n;
					//			
					if(y!=null) {
						y.pointobjid.pointobj=y;
						n.right=y;
						n.right.parent=n;
						if(y.right!=null)
							y.right.parent=y;
						if(y.left!=null)
							y.left.parent=y;
					}else
						n.right=null;
					updateHeight(n);updateHeight(n.parent);updateHeight(n.parent.parent);
					if(n.parent.parent.left!=null)
						lh=n.parent.parent.left.h;
					else 
						lh=0;

					if(n.parent.parent.right!=null)
						rh=n.parent.parent.right.h;
					else 
						rh=0;

					if(Math.abs(lh-rh)>1){						
						transform(n.parent.parent,n.parent.parent,b);
					}
					return;
				}else if(lh-rh<-1 && n.right.right==null && n.right.left!=null) {   
					obj y = n.right.left;
					obj z = n.right;
					y.pointobjid.pointobj=y;
					z.pointobjid.pointobj=z;

					n.right=y;

					z.left=y.right;
					y.right=z;
					y.parent=z.parent;
					z.parent=y;
					if(y!=null) {
						if(y.left!=null)
							y.left.parent=y;
						if(y.right!=null)
							y.right.parent=y;
					}
					if(z!=null) {
						if(z.left!=null)
							z.left.parent=z;
						if(z.right!=null)
							z.right.parent=z;
					}
					updateHeight(z);updateHeight(y);
					transform(n,n.right,b);
					//Print(binroot);
					return;
				}else if(lh-rh<-1) {
					if(n.right.right!=null && n.right.left!=null) {
						if((n.right.right.h)>=(n.right.left.h)) {
							if(n.parent.right==n) {
								n.parent.right=n.right;
								n.right.parent=n.parent;
							}else if(n.parent.left==n) {
								n.parent.left=n.right;
								n.right.parent=n.parent;
							}
							obj y = n.right.left;

							n.parent=n.right;
							n.right.left=n;
							//			
							if(y!=null) {
								y.pointobjid.pointobj=y;
								n.right=y;
								n.right.parent=n;
								if(y.right!=null)
									y.right.parent=y;
								if(y.left!=null)
									y.left.parent=y;
							}else
								n.right=null;
							updateHeight(n);updateHeight(n.parent);updateHeight(n.parent.parent);
							if(n.parent.parent.left!=null)
								lh=n.parent.parent.left.h;
							else 
								lh=0;

							if(n.parent.parent.right!=null)
								rh=n.parent.parent.right.h;
							else 
								rh=0;

							if(Math.abs(lh-rh)>1){						
								transform(n.parent.parent,n.parent.parent,b);
							}
							return;
						}else {
							obj y = n.right.left;
							obj z = n.right;
							y.pointobjid.pointobj=y;
							z.pointobjid.pointobj=z;

							n.right=y;

							z.left=y.right;
							y.right=z;
							y.parent=z.parent;
							z.parent=y;
							if(y!=null) {
								if(y.left!=null)
									y.left.parent=y;
								if(y.right!=null)
									y.right.parent=y;
							}
							if(z!=null) {
								if(z.left!=null)
									z.left.parent=z;
								if(z.right!=null)
									z.right.parent=z;
							}
							updateHeight(z);updateHeight(y);
							transform(n,n.right,b);
							return;
						}
					}

				}else if(lh-rh>1 && (n.left.left!=null) && n.left.right==null) {

					if(n.parent.right==n) {
						n.parent.right=n.left;
						n.left.parent=n.parent;
					}else if(n.parent.left==n) {
						n.parent.left=n.left;
						n.left.parent=n.parent;
					}
					obj y = n.left.right;
					n.parent=n.left;
					n.left.right=n;
					if(y!=null) {
						y.pointobjid.pointobj=y;
						n.left=y;
						n.left.parent=n;
						if(y.right!=null)
							y.right.parent=y;
						if(y.left!=null)
							y.left.parent=y;
					}else
						n.left=null;
					updateHeight(n);updateHeight(n.parent);updateHeight(n.parent.parent);
					if(n.parent.parent.left!=null)
						lh=n.parent.parent.left.h;
					else 
						lh=0;

					if(n.parent.parent.right!=null)
						rh=n.parent.parent.right.h;
					else 
						rh=0;
					if(Math.abs(lh-rh)>1){					
						transform(n.parent.parent,n.parent.parent,b);
					}
					return;
					//Print(binroot);
				}else if(lh-rh>1 && n.left.left==null && n.left.right!=null) {   
					obj y = n.left.right;
					obj z = n.left;
					y.pointobjid.pointobj=y;
					z.pointobjid.pointobj=z;

					z.parent.left=y;

					z.right=y.left;
					y.left=z;
					y.parent=z.parent;
					z.parent=y;
					if(y!=null) {
						if(y.left!=null)
							y.left.parent=y;
						if(y.right!=null)
							y.right.parent=y;
					}
					if(z!=null) {
						if(z.left!=null)
							z.left.parent=z;
						if(z.right!=null)
							z.right.parent=z;
					}
					updateHeight(z);updateHeight(y);
					transform(n,y,b);
					return;
				}else if(lh-rh>1) {
					if(n.left.left!=null && n.left.right!=null) {
						if((n.left.left.h)>=(n.left.right.h)) {

							if(n.parent.right==n) {
								n.parent.right=n.left;
								n.left.parent=n.parent;
							}else if(n.parent.left==n) {
								n.parent.left=n.left;
								n.left.parent=n.parent;
							}
							obj y = n.left.right;

							n.parent=n.left;
							n.left.right=n;
							if(y!=null) {
								y.pointobjid.pointobj=y;
								n.left=y;
								n.left.parent=n;
								if(y.right!=null)
									y.right.parent=y;
								if(y.left!=null)
									y.left.parent=y;
							}else
								n.left=null;
							updateHeight(n);updateHeight(n.parent);updateHeight(n.parent.parent);
							if(n.parent.parent.left!=null)
								lh=n.parent.parent.left.h;
							else 
								lh=0;

							if(n.parent.parent.right!=null)
								rh=n.parent.parent.right.h;
							else 
								rh=0;
							if(Math.abs(lh-rh)>1){					
								transform(n.parent.parent,n.parent.parent,b);
							}
							return;
						}else {
							obj y = n.left.right;
							obj z = n.left;
							y.pointobjid.pointobj=y;
							z.pointobjid.pointobj=z;

							z.parent.left=y;

							z.right=y.left;
							y.left=z;
							y.parent=z.parent;
							z.parent=y;
							if(y!=null) {
								if(y.left!=null)
									y.left.parent=y;
								if(y.right!=null)
									y.right.parent=y;
							}
							if(z!=null) {
								if(z.left!=null)
									z.left.parent=z;
								if(z.right!=null)
									z.right.parent=z;
							}
							updateHeight(z);updateHeight(y);
							transform(n,y,b);
							return;
						}
					}
				}
			}
		}

	}

	public int add_object(int id,int cap){
		if(check==true) {
			objroot.capacity=cap;
			objroot.objectID=id;
			objroot.left=null;
			objroot.right=null;
			objroot.parent=null;
			objroot.h=1;
			check=false;
			Bin b = findPosBIN(objroot.capacity, binroot, objroot.objectID);	
			if(b!=null) {
				binID bin = new binID();
				bin = binrootid;
				bin = b.pointBinID;/////////

				objroot.pointBinID=bin;

				obj o = new obj();
				o.capacity=objroot.capacity;
				o.id=objroot.objectID;
				o.left=null;
				o.right=null;
				o.parent=null;
				o.h=1;
				o.pointobjid=objroot;
				bin.root=(o);
				objroot.pointobj=o;
				bin.capacity=bin.capacity-objroot.capacity;
				b.capacity=b.capacity-objroot.capacity;
				DeleteBin(b);
				updateHeight(b.parent);
				Bin n1 = isBalanced(b.parent);
				if(n1==null) {

				}else {
					transform(n1,b.parent);
				}
				AddBinAgain(b.binID,b.capacity);
				objroot.pointbin=objroot.pointBinID.pointbin;
			}
			if(b!=null) {
				return b.binID;
			}
			else
				return 0;
		}else {
			Bin b = findPosBIN(cap, binroot, id);
			if(b!=null) {
				Object add = new Object();
				Object new1 = new Object();
				add=objroot;
				add=getPositionObjectID(id,add);

				if(id<add.objectID) {
					add.left=new1;
				}else {
					add.right=new1;
				}

				new1.objectID=id;
				new1.capacity=cap;
				new1.left=null;
				new1.right=null;
				new1.parent=add;
				new1.h=1;
				updateHeight(add);

				binID bin = new binID();
				bin = binrootid;
				bin = b.pointBinID;///////

				new1.pointBinID=bin;

				obj o = new obj();
				obj addo = new obj();
				o.capacity=cap;
				o.id=id;

				if(bin.root==null) {

					o.left=null;
					o.right=null;
					o.parent=null;
					o.pointobjid=new1;
					o.h=1;
					bin.root=o;

				}else {

					addo=getPositionObjectsmall(id, bin.root);
					if(id<addo.id) {
						addo.left=o;
					}else {
						addo.right=o;
					}
					o.parent=addo;
					o.left=null;
					o.right=null;
					o.pointobjid=new1;
					o.h=1;
					updateHeight(addo);
				}
				obj no = isBalanced(o);
				if(no==null) {

				}else {
					transform(no,o,bin);
				}
				new1.pointobj=findPosobjsmall(id,bin.root);
				new1.pointobj.pointobjid=new1;
				bin.capacity=bin.capacity-new1.capacity;
				b.capacity=b.capacity-cap;
				DeleteBin(b);
				updateHeight(b.parent);
				Bin n1 = isBalanced(b.parent);
				if(n1==null) {

				}else {
					transform(n1,b.parent);
				}


				b=AddBinAgain(b.binID,b.capacity);

				new1.pointbin=new1.pointBinID.pointbin;
				Object n = isBalanced(new1);
				if(n==null) {

				}else {
					transform(n,new1);
				}
			}
			if(b!=null) {
				return b.binID;
			}
			else
				return 0;
		}
	}


	//	static int height(obj node)  
	//	{ 
	//
	//		if (node == null) 
	//			return 0; 
	//
	//		return 1 + Math.max(height(node.left), height(node.right)); 
	//	} 

	static obj isBalanced(obj n) {

		if (n == null ) 
			return null; 

		if (n.left!=null)
			lh = (n.left.h); 
		else
			lh=0;

		if(n.right!=null)
			rh = (n.right.h); 
		else
			rh=0;

		if (Math.abs(lh - rh) <= 1)  
			return isBalanced(n.parent); 

		return n;

	}


	public static obj getPositionObjectsmall(int id,obj base) {

		if(id<base.id) {
			if(base.left==null) {
				return base; 
			}
			return getPositionObjectsmall(id,base.left);

		}else if(id>base.id) {
			if(base.right==null) {
				return base; 
			}
			return getPositionObjectsmall(id,base.right);
		}
		return null;
	}


	public static obj findPosobjsmall(int id,obj base) {
		obj n3 = new obj();
		n3=base;
		if(id==(base.id)) {
			return n3;
		}else {
			if(id<(base.id)) {
				if(id==(base.id)) {
					return n3;
				}
				return findPosobjsmall(id,base.left);

			}else if(id>(base.id)) {
				if(id==(base.id)) {
					return n3;
				}
				return findPosobjsmall(id,base.right);
			} 
		}
		return null;
	}



	public static void DeleteObjectsmall(obj n,binID b) {
		if(n==b.root) {
			if(n.left==null && n.right==null) {
				b.root=null;
				return;
			}else if(n.right==null) {
				b.root=n.left;
				return;
			}else {
				obj k = inorderBST(n.right);
				n.capacity=k.capacity;
				n.id=k.id;
				n.pointobjid=k.pointobjid;
				k.pointobjid.pointobj=n;
				DeleteObjectsmall(k,b);
				updateHeight(k.parent);

			}
		}else if(n.left==null) {
			if(n.right!=null) {

				if(n.parent.right == n) {
					n.parent.right=n.right;
					n.right.parent=n.parent;

				}else if(n.parent.left == n){
					n.parent.left=n.right;
					n.right.parent=n.parent;
				}
			}else {
				if(n.parent.right == n) {
					n.parent.right=n.right;

				}else if(n.parent.left == n){
					n.parent.left=n.right;

				}

			}
		}else if(n.right==null) {
			if(n.parent.right == n) {
				n.parent.right=n.left;
				n.left.parent=n.parent;

			}else if(n.parent.left == n){
				n.parent.left=n.left;
				n.left.parent=n.parent;

			}
		}else {
			obj k = inorderBST(n.right);
			n.capacity=k.capacity;
			n.id=k.id;
			n.pointobjid=k.pointobjid;
			k.pointobjid.pointobj=n;
			DeleteObjectsmall(k,b);
			updateHeight(k.parent);
		}
		obj o = isBalanced(n.parent);
		transform(o,n.parent,b);
	}

	public static obj inorderBST(obj n){
		if(n.left==null) {
			return n;
		}else {
			return inorderBST(n.left);
		}
	}

	public static void PrintBin(int id){
		binID b = findPosBINID(id, binrootid);
		PrintBin(b.root);
	}

	public static void PrintBin(obj n) {
		if(n==null)
			return;		
		if(n.left!=null)
			PrintBin(n.left);
		l1.add(new Pair <Integer,Integer> (n.id, n.capacity));
		//System.out.println(n.id+" "+n.capacity);
		if(n.right!=null)
			PrintBin(n.right);
	}

	public static Bin AddBinAgain(int id,int cap){

		if(binroot==null) {
			Bin n = new Bin();
			binroot=n;
			binroot.binID=id;
			binroot.capacity=cap;
			binroot.left=null;
			binroot.right=null;
			binroot.parent=null;			
			binroot.pointBinID=findPosBINID(id,binrootid);
			binroot.pointBinID.pointbin=binroot;
			binroot.h=1;
			return binroot;
		}else {

			Bin add = new Bin();
			Bin new1 = new Bin();
			add=binroot;
			add=getPositionBIN(cap,id,add);
			add.pointBinID.pointbin=add;
			if(cap<add.capacity) {
				add.left=new1;
			}else if(cap>add.capacity) {
				add.right=new1;
			}else if(cap==add.capacity){
				if(id>add.binID) {
					add.right=new1;
				}else {
					add.left=new1;
				}
			}
			new1.binID=id;
			new1.capacity=cap;
			new1.left=null;
			new1.right=null;
			new1.parent=add;
			new1.h=1;
			new1.pointBinID=findPosBINID(id,binrootid);
			updateHeight(add);
			Bin n = isBalanced(new1);
			if(n==null) {

			}else {
				transform(n,new1);//checkerssssssssss
			}

			new1.pointBinID.pointbin=new1;
			return new1;
		}

	}

	public static Bin getPositionBIN(int capacity,int id,Bin base) {
		//Print(binroot);
		if(capacity<base.capacity) {
			if(base.left==null) {
				return base; 
			}
			return getPositionBIN(capacity,id,base.left);

		}else if(capacity>base.capacity) {
			if(base.right==null) {
				return base; 
			}
			return getPositionBIN(capacity,id,base.right);
		}else if(capacity==base.capacity){
			if(id<base.binID) {
				if(base.left==null) {
					return base; 
				}
				return getPositionBIN(capacity,id,base.left);
			}else if(id>base.binID) {
				if(base.right==null) {
					return base; 
				}
				return getPositionBIN(capacity,id,base.right);
			}
		}
		return null;
	}

	public static binID getPositionBINID(int id,binID base) {
		if(id<base.binID) {
			if(base.left==null) {
				return base; 
			}
			return getPositionBINID(id,base.left);

		}else if(id>base.binID) {
			if(base.right==null) {
				return base; 
			}
			return getPositionBINID(id,base.right);
		}
		return null;
	}

	public static Object getPositionObjectID(int id,Object base) {
		if(id<base.objectID) {
			if(base.left==null) {
				return base; 
			}
			return getPositionObjectID(id,base.left);

		}else if(id>base.objectID) {
			if(base.right==null) {
				return base; 
			}
			return getPositionObjectID(id,base.right);
		}
		return null;
	}

	public static Bin findPosBIN(int cap,Bin n3, int id) {
		if(n3.right == null) {
			if(n3.capacity>=cap)
				return n3;
			else
				return null;
		}else {
			return findPosBIN(cap,n3.right,id);
		}
	}

	public static binID findPosBINID(int id,binID base) {
		binID n3 = new binID();
		n3=base;
		if(id==(base.binID)) {
			return n3;
		}else {
			if(id<(base.binID)) {
				if(id==(base.binID)) {
					return n3;
				}
				return findPosBINID(id,base.left);

			}else if(id>(base.binID)) {

				return findPosBINID(id,base.right);
			} 
		}
		return null;
	}

	public static Object findPosObject(int id,Object base) {

		Object n3 = new Object();
		n3=base;
		if(id==(base.objectID)) {
			return n3;
		}else {
			if(id<(base.objectID)) {
				if(id==(base.objectID)) {
					return n3;
				}
				return findPosObject(id,base.left);

			}else if(id>(base.objectID)) {

				return findPosObject(id,base.right);
			} 
		}
		return null;
	}



	public static void DeleteObject(Object n) {
		if(n==objroot) {
			if(n.left==null && n.right==null) {
				objroot=null;
				return;
			}else if(n.right==null) {
				objroot=n.left;
				return;
			}else {
				Object k = inorderBST(n.right);
				n.capacity=k.capacity;
				n.objectID=k.objectID;
				n.pointbin=k.pointbin;
				n.pointBinID=k.pointBinID;
				n.pointobj=k.pointobj;
				k.pointobj.pointobjid=n;
				DeleteObject(k);
				updateHeight(k.parent);
			}	
		}else if(n.left==null) {
			if(n.right!=null) {

				if(n.parent.right == n) {
					n.parent.right=n.right;
					n.right.parent=n.parent;

				}else if(n.parent.left == n){
					n.parent.left=n.right;
					n.right.parent=n.parent;

				}
			}else {

				if(n.parent.right == n) {

					n=null;

				}else if(n.parent.left == n){
					n.parent.left=n.right;
				}

			}
		}else if(n.right==null) {
			if(n.parent.right == n) {
				n.parent.right=n.left;
				n.left.parent=n.parent;

			}else if(n.parent.left == n){
				n.parent.left=n.left;
				n.left.parent=n.parent;

			}
		}else {
			Object k = inorderBST(n.right);
			n.capacity=k.capacity;
			n.objectID=k.objectID;
			n.pointbin=k.pointbin;
			n.pointBinID=k.pointBinID;
			n.pointobj=k.pointobj;
			k.pointobj.pointobjid=n;
			DeleteObject(k);
			updateHeight(k.parent);
		}

	}

	public static Object inorderBST(Object n){
		if(n.left==null) {
			return n;
		}else {
			return inorderBST(n.left);
		}
	}

	static binID isBalanced(binID n) {

		if (n == null ) 
			return null; 

		if (n.left!=null)
			lh = (n.left.h); 
		else
			lh=0;

		if(n.right!=null)
			rh = (n.right.h); 
		else
			rh=0;
		//System.out.println(lh+"  "+rh);
		if (Math.abs(lh - rh) <= 1)  
			return isBalanced(n.parent); 

		return n;

	}

	//	static int height(binID node)  
	//	{ 
	//
	//		if (node == null) 
	//			return 0; 
	//
	//		return 1 + Math.max(height(node.left), height(node.right)); 
	//	} 


	static Bin isBalanced(Bin n) {

		if (n == null ) 
			return null; 
		if (n.left!=null)
			lh = (n.left.h); 
		else
			lh=0;

		if(n.right!=null)
			rh = (n.right.h); 
		else
			rh=0;
		//System.out.println(lh+"  "+rh);
		if (Math.abs(lh - rh) <= 1)  
			return isBalanced(n.parent); 

		return n;

	}

	//	static int height(Bin node)  
	//	{ 
	//		if (node == null) 
	//			return 0; 
	//
	//		return 1 + Math.max(height(node.left), height(node.right)); 
	//	} 

	static Object isBalanced(Object n) {

		if (n == null ) 
			return null; 

		if (n.left!=null)
			lh = (n.left.h); 
		else
			lh=0;

		if(n.right!=null)
			rh = (n.right.h); 
		else
			rh=0;
		if (Math.abs(lh - rh) <= 1)  
			return isBalanced(n.parent); 
		return n;

	}

	//	static int height(Object node)  {
	//
	//		if (node == null) 
	//			return 0; 
	//
	//		return 1 + Math.max(height(node.left), height(node.right)); 
	//	} 
}
