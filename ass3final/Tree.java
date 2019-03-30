package ass3final;
import java.util.*;
public class Tree {

	public static int lh; /* for height of left subtree */		   
	public static int rh;

	public static Object objroot = new Object();
	public static Bin binroot = new Bin();
	public static binID binrootid = new binID();
	public static ArrayList<String> arr;

	public static ArrayList<String> contents(int bin_id){
		arr = new ArrayList<String>();
		PrintBin(bin_id);		
		return arr;
	}
	
	public static void Printobj(Object n) {
		if(n==null)
			return;
		//System.out.println(n.pointobj.pointobjid.objectID+"utkarsh"+"tanmay"+n.objectID);
		if(n.left!=null)
			Printobj(n.left);
		System.out.println(n.objectID+" utkarsh"+"tanmay "+n.pointbin);
		if(n.right!=null)
			Printobj(n.right);
	}

	public static void Print(Bin n) {
		if(n==null)
			return;

		if(n.left!=null)
			Print(n.left);
		System.out.println(n.capacity+"   "+n +"  "+n.binID);

		if(n.right!=null)
			Print(n.right);
	}

	public static void PrintobjID(binID n) {
		if(n==null)
			return;
		//System.out.println(n.binID+"     "+n.pointbin.capacity);
		if(n.left!=null)
			PrintobjID(n.left);
		System.out.println(n.binID+"   aditya   "+n);
		if(n.right!=null)
			PrintobjID(n.right);
	}

	public static void main(String[] args) {	

		binroot.capacity = 3;
		binroot.left=null;
		binroot.right=null;
		binroot.binID=0;
		binroot.pointBinID=binrootid;
		//binroot.level=0;


		binrootid.left=null;
		binrootid.right=null;
		binrootid.binID=0;
		binrootid.capacity=3;
		binrootid.pointbin=binroot;
		//binrootid.level=0;

		objroot.capacity = 2;
		objroot.left=null;
		objroot.right=null;
		objroot.objectID=0;
		objroot.parent=null;

		add_bin(1,5);
	
		add_bin(2,7);
	
		add_bin(3,11);

		add_bin(4,13);

		add_bin(5,17);
		add_bin(6,19);
		add_bin(7,23);
		add_bin(8,31);
		add_bin(9,9);
		add_bin(10,4);
		add_bin(11,6);
		add_bin(12,3);
		add_bin(13,9);
	
		Bin b = findPosBIN(objroot.capacity, binroot, objroot.objectID);	
		if(b!=null) {
			binID bin = new binID();
			bin = binrootid;
			bin = findPosBINID(b.binID, bin);

			objroot.pointBinID=bin;

			obj o = new obj();
			o.capacity=objroot.capacity;
			o.id=objroot.objectID;
			o.left=null;
			o.right=null;
			o.parent=null;
			o.pointobjid=objroot;
			bin.root=(o);
			objroot.pointobj=o;
			bin.capacity=bin.capacity-objroot.capacity;
			//	System.out.println(b.capacity);
			b.capacity=b.capacity-objroot.capacity;
			//	System.out.println(b.capacity);
			DeleteBin(b);
			//Print(binroot);
			AddBinAgain(b.binID,b.capacity);
			objroot.pointbin=objroot.pointBinID.pointbin;
		}
//		//		//2
		add_object(1,4);
		add_object(2,6);
		add_object(3,10);
		add_object(4,12);
		add_object(5,16);
		add_object(6,18);
		add_object(7,30);
		add_object(8,29);
		add_object(9,8);
		add_object(10,5);
		add_object(11,10);
		add_object(12,8);
//		delete_object(2);
//		delete_object(0);
//		delete_object(4);
//		delete_object(3);
//		delete_object(1);

		//Print(binroot);
		Printobj(objroot);
		PrintobjID(binrootid);
		Print(binroot);

	}

	public static int delete_object(int rem){
		Object remove = new Object();
		remove = findPosObject(rem, objroot);
		binID b = new binID();
		b=remove.pointBinID;
		//System.out.println(remove+"tt");
		Bin b1 = new Bin();
		b1=remove.pointbin;    //checker
		//Print(binroot);
		//System.out.println(objroot.pointbin+"   "+b.pointbin.capacity + " "+b1);
		DeleteBin(b.pointbin);


		//Print(binroot);
		//System.out.println("");

		b1.capacity=b1.capacity+remove.capacity;
		remove.pointBinID.capacity=remove.pointBinID.capacity+remove.capacity;
		DeleteObjectsmall(remove.pointobj,b);

		//		for(int i=0;i<remove.pointBinID.objects.size();i++) {
		//			System.out.println(remove.pointBinID.objects.get(i).id+"kkk");
		//		}
		AddBinAgain(b.binID,b.capacity);
		//Print(binroot);
		//b.level=b.parent.level+1;
		//System.out.println("uu");
		Object temp = remove.parent;
		//System.out.println(remove.objectID+"vhvhvhbjbjkknkjhcghvjkn jhgnjkjkbjbj");
		//Printobj(objroot);
		DeleteObject(remove);
		//Printobj(objroot);
		//System.out.println("vhvhvhbjbjkknkjhcghvjkn jhgnjkjkbjbj");
		Object n = isBalanced(temp);
		//System.out.println(n+"vhvhvhbjbjkknkjhcghvjkn jhgnjkjkbjbj");
		if(n==null) {

		}else {
			transform(n,temp);
		}
		//Printobj(objroot);
		if(b!=null)
			return b.binID;
		else
			return 0;
	}

	public static void DeleteBin(Bin n) {
		//	Print(binroot);
		if(n==binroot) {
			//System.out.println("hi");
			if(n.left==null && n.right==null) {
				//	System.out.println("hi");
				binroot=null;
				return;
			}else if(n.right==null) {
				//	System.out.println("hi");
				binroot=n.left;
				return;
			}else {
				//	System.out.println("hi");
				Bin k = inorderBSTb(n.right);
				n.capacity=k.capacity;
				n.binID=k.binID;
				n.pointBinID=k.pointBinID;

				DeleteBin(k);
			}	
		}else if(n.left==null) {
			//System.out.println(n.parent.left+"llllll"+n.pointBinID.capacity+" "+n);

			if(n.right!=null) {
				//System.out.println("tanmay3");
				if(n.parent.right == n) {
					n.parent.right=n.right;
					n.right.parent=n.parent;
				}else if(n.parent.left == n) {

					n.parent.left=n.right;
					n.right.parent=n.parent;

				}
			}else {

				if(n.parent.right == n) {
					//System.out.println("tanmay");
					//Print(binroot);
					//System.out.println("tanmay1"+n.parent+""+n.capacity+""+n.parent.right);
					//Print(binroot);
					//Bin temp = new Bin(n.parent,n.left,n.right,n.capacity,n.binID,n.pointBinID);
					n.parent.right=null;
					n.parent=null;
					n.pointBinID=null;
					//n.binID=0;
					//System.out.println("tanmay1"+n.parent+""+n.capacity+""+n.parent.capacity);
					//n=null;
					//Print(binroot);
				}else if(n.parent.left == n) {
					//System.out.println("tanmay2");
					n.parent.left=n.right;
					//	n.right=n.parent;
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
			DeleteBin(k);
		}
		//Print(binroot);
	}

	public static Bin inorderBSTb(Bin n){
		if(n.left==null) {
			return n;
		}else {
			return inorderBSTb(n.left);
		}
	}

	public static void add_bin(int id,int cap){
		Bin add = new Bin();
		Bin new1 = new Bin();
		add=binroot;
		add=getPositionBIN(cap,add);

		if(cap<add.capacity) {
			add.left=new1;
		}else {
			add.right=new1;
		}

		new1.binID=id;
		new1.capacity=cap;
		new1.left=null;
		new1.right=null;
		new1.parent=add;
		//new1.level=new1.parent.level+1;

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
		//new1binid.level=new1binid.parent.level+1;
		new1binid.pointbin=new1;
		new1.pointBinID=new1binid;

		Bin n = isBalanced(new1);
		//System.out.println(n);
		if(n==null) {

		}else {
		//	transform(n,new1);
		}

		binID n1 = isBalanced(new1binid);
		if(n1==null) {

		}else {
		  //transform(n1,new1binid);
		}
	}



	static void transform(Bin n, Bin new1) {
		//Print(binroot);
		//System.out.println("");

		if(n==binroot && new1==binroot) {
			if(lh-rh>1 ) {
				Bin y = n.left;
				Bin z = n;

				Bin temp = new Bin(y,null,z.right,z.capacity,z.binID,z.pointBinID);
				temp.pointBinID.pointbin=temp;
				y.pointBinID.pointbin=y;
				if(temp.right!=null)
					temp.right.parent=temp;
				if(y.right!=null) {
					temp.left = new Bin(y.parent,y.right.left,y.right.right,y.right.capacity,y.right.binID,y.right.pointBinID);
					temp.left.parent=temp;
				}else
					temp.left=null;
				//temp.left.parent=temp;
				//temp.right.parent=temp;
				y.right=temp;
				binroot=y;
				y.parent=null;

			}

			if(lh-rh<-1) {  

				Bin y = n.right;
				Bin z = n;
				Bin temp = new Bin(y,z.left,null,z.capacity,z.binID,z.pointBinID);
				temp.pointBinID.pointbin=temp;
				y.pointBinID.pointbin=y;
				if(temp.left!=null)
					temp.left.parent=temp;
				//System.out.println(y.capacity+"uuuuuu"+z.capacity);
				if(y.left!=null) {
					temp.right = new Bin(y.parent,y.left.left,y.left.right,y.left.capacity,y.left.binID,y.left.pointBinID);
					temp.right.parent=temp;
				}else
					temp.right=null;

				//				temp.left.parent=temp;
				//				temp.right.parent=temp;
				y.left=temp;
				//temp.capacity = z.capacity;
				//temp.parent=y;
				//temp.left=z.left;
				binroot=y;
				y.parent=null;

				//Print(binroot);
			}
			//System.out.println("ttttt");
		}else if(n==binroot) {
			if(lh-rh>1 && new1.capacity<new1.parent.capacity) {
				Bin y = n.left;
				Bin z = n;
				Bin temp = new Bin(y,null,z.right,z.capacity,z.binID,z.pointBinID);
				temp.pointBinID.pointbin=temp;
				y.pointBinID.pointbin=y;
				if(temp.right!=null)
					temp.right.parent=temp;
				if(y.right!=null) {
					temp.left = new Bin(y.parent,y.right.left,y.right.right,y.right.capacity,y.right.binID,y.right.pointBinID);
					temp.left.parent=temp;
				}else
					temp.left=null;
				//temp.left.parent=temp;
				//temp.right.parent=temp;
				y.right=temp;
				binroot=y;
				y.parent=null;
				//Print(binroot);
			}else if(lh-rh>1 && new1.capacity>=new1.parent.capacity) {   
				//Print(binroot);
				Bin y = n.left.right;
				Bin z = n.left;
				Bin temp = new Bin(z.parent,z,y.right,y.capacity,y.binID,y.pointBinID);
				//				if(temp.left!=null)
				//					temp.left.parent=temp;
				//				if(temp.right!=null)
				//					temp.right.parent=temp;
				temp.pointBinID.pointbin=z;
				y.pointBinID.pointbin=temp;
				z.parent.left=temp;
				//temp.parent=z.parent;
				//y.parent=z.parent;
				z.right=y.left;
				z.parent=temp;
				//Print(binroot);
				transform(n,temp);
			}

			if(lh-rh<-1 && new1.capacity>=new1.parent.capacity) {  
				Bin y = n.right;
				Bin z = n;
				Bin temp = new Bin(y,z.left,null,z.capacity,z.binID,z.pointBinID);
				temp.pointBinID.pointbin=temp;
				y.pointBinID.pointbin=y;
				if(temp.left!=null)
					temp.left.parent=temp;
				//System.out.println(y.capacity+"uuuuuu"+z.capacity);
				if(y.left!=null) {
					temp.right = new Bin(y.parent,y.left.left,y.left.right,y.left.capacity,y.left.binID,y.left.pointBinID);
					temp.right.parent=temp;
				}else
					temp.right=null;

				//				temp.left.parent=temp;
				//				temp.right.parent=temp;
				y.left=temp;
				//temp.capacity = z.capacity;
				//temp.parent=y;
				//temp.left=z.left;
				binroot=y;
				y.parent=null;
				//Print(binroot);
			}else if(lh-rh<-1 && new1.capacity<new1.parent.capacity) {   
				//Print(binroot);
				Bin y = n.right.left;
				Bin z = n.right;

				Bin temp = new Bin(z.parent,y.left,z,y.capacity,y.binID,y.pointBinID);
				temp.pointBinID.pointbin=z;
				y.pointBinID.pointbin=temp;
				//System.out.println(y.capacity+""+z.capacity);

				//				if(temp.left!=null)
				//					temp.left.parent=temp;
				//				if(temp.right!=null)
				//					temp.right.parent=temp;
				z.parent.right=temp;
				//temp.parent=z.parent;
				//y.parent=z.parent;
				z.left=y.right;
				z.parent=temp;
				//Print(binroot);
				transform(n,temp);
			}

		}else {
			if(lh-rh<-1 && new1.capacity>=new1.parent.capacity) {   

				Bin y = n.right;
				Bin z = n;
				Bin temp = new Bin(y,z.left,null,z.capacity,z.binID,z.pointBinID);
				temp.pointBinID.pointbin=temp;
				y.pointBinID.pointbin=y;
				if(temp.left!=null)
					temp.left.parent=temp;

				if(y.left!=null) {
					temp.right = new Bin(y.parent,y.left.left,y.left.right,y.left.capacity,y.left.binID,y.left.pointBinID);
					temp.right.parent=temp;
				}else
					temp.right=null;

				y.left=temp;


				//temp.right.parent=temp;

				if(z.parent.right==z) {
					z.parent.right=y;
					y.parent=z.parent;
				}else if(z.parent.left==z) {
					z.parent.left=y;
					y.parent=z.parent;
				}
				//Print(binroot);
			}else if(lh-rh<-1 && new1.capacity<new1.parent.capacity) {   
				//Print(binroot);
				Bin y = n.right.left;
				Bin z = n.right;
				Bin temp = new Bin(z.parent,y.left,z,y.capacity,y.binID,y.pointBinID);
				temp.pointBinID.pointbin=z;
				y.pointBinID.pointbin=temp;
				//				if(temp.left!=null)
				//					temp.left.parent=temp;
				//				if(temp.right!=null)
				//					temp.right.parent=temp;

				z.parent.right=temp;
				//temp.parent=z.parent;
				//y.parent=z.parent;
				z.left=y.right;
				z.parent=temp;
				//Print(binroot);
				transform(n,temp);
			}

			if(lh-rh>1 && new1.capacity<new1.parent.capacity) {
				//Print(binroot);
				Bin y = n.left;
				Bin z = n;
				Bin temp = new Bin(y,null,z.right,z.capacity,z.binID,z.pointBinID);
				temp.pointBinID.pointbin=temp;
				y.pointBinID.pointbin=y;
				if(temp.right!=null)
					temp.right.parent=temp;
				//	System.out.println(y.capacity+""+z.capacity+""+temp.capacity);
				if(y.right!=null) {
					temp.left = new Bin(y.parent,y.right.left,y.right.right,y.right.capacity,y.right.binID,z.right.pointBinID);
					temp.left.parent=temp;
				}else
					temp.left=null;

				y.right=temp;
				//	System.out.println(y.right.capacity+""+y.left+""+temp.capacity);

				if(z.parent.right==z) {
					z.parent.right=y;
					y.parent=z.parent;
				}else if(z.parent.left==z) {
					//System.out.println("_____>"+temp.left);
					z.parent.left=y;
					y.parent=z.parent;
				}
				//Print(binroot);
			}else if(lh-rh>1 && new1.capacity>=new1.parent.capacity) {   
				//Print(binroot);
				Bin y = n.left.right;
				Bin z = n.left;
				Bin temp = new Bin(z.parent,z,y.right,y.capacity,y.binID,y.pointBinID);
				temp.pointBinID.pointbin=z;
				y.pointBinID.pointbin=temp;
				//	System.out.println(y.capacity+""+z.capacity);

				//				if(temp.left!=null)
				//					temp.left.parent=temp;
				//				if(temp.right!=null)
				//					temp.right.parent=temp;

				z.parent.left=temp;
				//temp.parent=z.parent;
				//y.parent=z.parent;
				z.right=y.left;
				z.parent=temp;
				//Print(binroot);
				transform(n,temp);
			}
		}

	}

	static void transform(binID n, binID new1) {
		//Print(binroot);

		if(n==binrootid && new1==binrootid) {
			if(lh-rh>1 ) {

				binID y = n.left;
				binID z = n;
				binID temp = new binID(y,null,z.right,z.capacity,z.binID,z.pointbin,z.root);
				y.pointbin.pointBinID=y;
				z.pointbin.pointBinID=temp;
				if(temp.right!=null) {
					temp.right.parent=temp;
				}
				if(y.right!=null) {
					temp.left = new binID(y.parent,y.right.left,y.right.right,y.right.capacity,y.right.binID,y.right.pointbin,y.right.root);
					temp.left.parent=temp;
				}else
					temp.left=null;

				y.right=temp;
				//				temp.capacity = z.capacity;
				//				temp.parent=y;
				//				temp.right=z.right;
				binrootid=y;
				y.parent=null;

			}

			if(lh-rh<-1) {  

				binID y = n.right;
				binID z = n;
				binID temp = new binID(y,z.left,null,z.capacity,z.binID,z.pointbin,z.root);
				y.pointbin.pointBinID=y;
				z.pointbin.pointBinID=temp;
				if(temp.left!=null) {
					temp.left.parent=temp;
				}
				//	System.out.println(y.capacity+"ttttt"+z.capacity);
				if(y.left!=null) {
					temp.right = new binID(y.parent,y.left.left,y.left.right,y.left.capacity,y.left.binID,y.left.pointbin,y.left.root);
					temp.right.parent=temp;
				}else
					temp.right=null;

				y.left=temp;
				//				temp.capacity = z.capacity;
				//				temp.parent=y;
				//				temp.left=z.left;
				binrootid=y;
				y.parent=null;

				//Print(binroot);
			}
			//System.out.println("ttttt");
		}else if(n==binrootid) {
			if(lh-rh>1 && new1.binID<new1.parent.binID) {
				binID y = n.left;
				binID z = n;
				binID temp = new binID(y,null,z.right,z.capacity,z.binID,z.pointbin,z.root);
				y.pointbin.pointBinID=y;
				z.pointbin.pointBinID=temp;
				if(temp.right!=null) {
					temp.right.parent=temp;
				}
				if(y.right!=null) {
					temp.left = new binID(y.parent,y.right.left,y.right.right,y.right.capacity,y.right.binID,y.right.pointbin,y.right.root);
					temp.left.parent=temp;
				}else
					temp.left=null;

				y.right=temp;
				//				temp.capacity = z.capacity;
				//				temp.parent=y;
				//				temp.right=z.right;
				binrootid=y;
				y.parent=null;
				//Print(binroot);
			}else if(lh-rh>1 && new1.binID>new1.parent.binID) {   
				//Print(binroot);
				binID y = n.left.right;
				binID z = n.left;
				binID temp = new binID(z.parent,z,y.right,y.capacity,y.binID,y.pointbin,y.root);
				//		System.out.println(y.capacity+""+z.capacity);
				y.pointbin.pointBinID=temp;
				z.pointbin.pointBinID=z;
				z.parent.left=temp;
				//temp.parent=z.parent;
				//y.parent=z.parent;
				z.right=y.left;
				//Print(binroot);
				transform(n,temp);
			}

			if(lh-rh<-1 && new1.binID>new1.parent.binID) {  
				binID y = n.right;
				binID z = n;
				binID temp = new binID(y,z.left,null,z.capacity,z.binID,z.pointbin,z.root);
				y.pointbin.pointBinID=y;
				z.pointbin.pointBinID=temp;
				if(temp.left!=null) {
					temp.left.parent=temp;
				}
				//	System.out.println(y.capacity+"ttttt"+z.capacity);
				if(y.left!=null) {
					temp.right = new binID(y.parent,y.left.left,y.left.right,y.left.capacity,y.left.binID,y.left.pointbin,y.left.root);
					temp.right.parent=temp;
				}else
					temp.right=null;

				y.left=temp;
				//				temp.capacity = z.capacity;
				//				temp.parent=y;
				//				temp.left=z.left;
				binrootid=y;
				y.parent=null;
				//Print(binroot);
			}else if(lh-rh<-1 && new1.binID<new1.parent.binID) {   
				//Print(binroot);
				binID y = n.right.left;
				binID z = n.right;
				binID temp = new binID(z.parent,y.left,z,y.capacity,y.binID,y.pointbin,y.root);
				//	System.out.println(y.capacity+""+z.capacity);
				y.pointbin.pointBinID=temp;
				z.pointbin.pointBinID=z;
				z.parent.right=temp;
				//temp.parent=z.parent;
				//y.parent=z.parent;
				z.left=y.right;
				//Print(binroot);
				transform(n,temp);
			}

		}else {
			if(lh-rh<-1 && new1.binID>new1.parent.binID) {   

				binID y = n.right;
				binID z = n;
				binID temp = new binID(y,z.left,null,z.capacity,z.binID,z.pointbin,z.root);
				y.pointbin.pointBinID=y;
				z.pointbin.pointBinID=temp;
				if(temp.left!=null) {
					temp.left.parent=temp;
				}
				//	System.out.println(y.capacity+""+z.capacity);
				if(y.left!=null) {
					temp.right = new binID(y.parent,y.left.left,y.left.right,y.left.capacity,y.left.binID,y.left.pointbin,y.left.root);
					temp.right.parent=temp;
				}else
					temp.right=null;

				y.left=temp;

				//				System.out.println("_____>"+temp.right);
				if(z.parent.right==z) {
					z.parent.right=y;
					y.parent=z.parent;
				}else if(z.parent.left==z) {
					z.parent.left=y;
					y.parent=z.parent;
				}
				//Print(binroot);
			}else if(lh-rh<-1 && new1.binID<new1.parent.binID) {   
				//Print(binroot);
				binID y = n.right.left;
				binID z = n.right;
				binID temp = new binID(z.parent,y.left,z,y.capacity,y.binID,y.pointbin,y.root);
				//System.out.println(y.capacity+""+z.capacity);
				y.pointbin.pointBinID=temp;
				z.pointbin.pointBinID=z;
				z.parent.right=temp;
				//temp.parent=z.parent;
				//y.parent=z.parent;
				z.left=y.right;
				//Print(binroot);
				transform(n,temp);
			}

			if(lh-rh>1 && new1.binID<new1.parent.binID) {
				//Print(binroot);
				binID y = n.left;
				binID z = n;
				binID temp = new binID(y,null,z.right,z.capacity,z.binID,z.pointbin,z.root);
				y.pointbin.pointBinID=y;
				z.pointbin.pointBinID=temp;
				if(temp.right!=null) {
					temp.right.parent=temp;
				}
				//	System.out.println(y.capacity+""+z.capacity+""+temp.capacity);
				if(y.right!=null) {
					temp.left = new binID(y.parent,y.right.left,y.right.right,y.right.capacity,y.right.binID,z.right.pointbin,z.root);
					temp.left.parent=temp;
				}else
					temp.left=null;

				y.right=temp;
				//	System.out.println(y.right.capacity+""+y.left+""+temp.capacity);

				if(z.parent.right==z) {
					z.parent.right=y;
					y.parent=z.parent;
				}else if(z.parent.left==z) {
					//System.out.println("_____>"+temp.left);
					z.parent.left=y;
					y.parent=z.parent;
				}
				//Print(binroot);
			}else if(lh-rh>1 && new1.binID>new1.parent.binID) {   
				//Print(binroot);
				binID y = n.left.right;
				binID z = n.left;
				binID temp = new binID(z.parent,z,y.right,y.capacity,y.binID,y.pointbin,y.root);
				//System.out.println(y.capacity+""+z.capacity);
				y.pointbin.pointBinID=temp;
				z.pointbin.pointBinID=z;
				z.parent.left=temp;
				//temp.parent=z.parent;
				//y.parent=z.parent;
				z.right=y.left;
				//Print(binroot);
				transform(n,temp);
			}
		}

	}


	static void transform(Object n, Object new1) {
		//System.out.println(n.objectID+" "+(lh-rh));
		//Printobj(objroot);
		if(n==objroot && new1==objroot) {
			if(lh-rh>1 ) {

				Object y = n.left;
				Object z = n;
				Object temp = new Object(y,null,z.right,z.capacity,z.objectID,z.pointBinID,z.pointbin,z.pointobj);

				if(temp.right!=null) {
					temp.right.parent=temp;
				}
				if(y.right!=null) {
					temp.left = new Object(y.parent,y.right.left,y.right.right,y.right.capacity,y.right.objectID,y.right.pointBinID,y.right.pointbin,y.right.pointobj);
					temp.left.parent=temp;
				}else
					temp.left=null;

				y.right=temp;
				//				temp.capacity = z.capacity;
				//				temp.parent=y;
				//				temp.right=z.right;
				objroot=y;
				y.parent=null;

			}

			if(lh-rh<-1) {  

				Object y = n.right;
				Object z = n;
				Object temp = new Object(y,z.left,null,z.capacity,z.objectID,z.pointBinID,z.pointbin,z.pointobj);
				//System.out.println(y.capacity+"ttttt"+z.capacity);
				if(temp.left!=null) {
					temp.left.parent=temp;
				}
				if(y.left!=null) {
					temp.right = new Object(y.parent,y.left.left,y.left.right,y.left.capacity,y.left.objectID,y.left.pointBinID,y.left.pointbin,y.left.pointobj);
					temp.right.parent=temp;
				}else
					temp.right=null;

				y.left=temp;
				//				temp.capacity = z.capacity;
				//				temp.parent=y;
				//				temp.left=z.left;
				objroot=y;
				y.parent=null;
			}
		}else if(n==objroot) {

			if(lh-rh>1 && new1.objectID<new1.parent.objectID ) {

				Object y = n.left;
				Object z = n;
				Object temp = new Object(y,null,z.right,z.capacity,z.objectID,z.pointBinID,z.pointbin,z.pointobj);

				if(temp.right!=null) {
					temp.right.parent=temp;
				}
				if(y.right!=null) {
					temp.left = new Object(y.parent,y.right.left,y.right.right,y.right.capacity,y.right.objectID,y.right.pointBinID,y.right.pointbin,y.right.pointobj);
					temp.left.parent=temp;
				}else
					temp.left=null;

				y.right=temp;
				//				temp.capacity = z.capacity;
				//				temp.parent=y;
				//				temp.right=z.right;
				objroot=y;
				y.parent=null;
				//Print(binroot);
			}
			else if(lh-rh>1 && new1.objectID>new1.parent.objectID) { 

				//Print(binroot);
				Object y = n.left.right;
				Object z = n.left;
				Object temp = new Object(z.parent,z,y.right,y.capacity,y.objectID,y.pointBinID,y.pointbin,y.pointobj);
				//		System.out.println(y.capacity+""+z.capacity);

				z.parent.left=temp;
				//	temp.parent=z.parent;
				//y.parent=z.parent;
				z.right=y.left;

				z.parent=temp;
				//Print(binroot);
				transform(n,temp);
			}

			if(lh-rh<-1 && new1.objectID>new1.parent.objectID) {  

				Object y = n.right;
				Object z = n;
				Object temp = new Object(y,z.left,null,z.capacity,z.objectID,z.pointBinID,z.pointbin,z.pointobj);
				//System.out.println(y.capacity+"ttttt"+z.capacity);
				if(temp.left!=null) {
					temp.left.parent=temp;
				}
				if(y.left!=null) {
					temp.right = new Object(y.parent,y.left.left,y.left.right,y.left.capacity,y.left.objectID,y.left.pointBinID,y.left.pointbin,y.left.pointobj);
					temp.right.parent=temp;
				}else
					temp.right=null;

				y.left=temp;
				//				temp.capacity = z.capacity;
				//				temp.parent=y;
				//				temp.left=z.left;
				objroot=y;
				y.parent=null;
				//Print(binroot);
			}
			else if(lh-rh<-1 && new1.objectID<new1.parent.objectID) {   
				//	System.out.println("ttttt");
				//Print(binroot);
				Object y = n.right.left;
				Object z = n.right;
				Object temp = new Object(z.parent,y.left,z,y.capacity,y.objectID,y.pointBinID,y.pointbin,y.pointobj);
				//	System.out.println(y.capacity+""+z.capacity);

				z.parent.right=temp;
				//temp.parent=z.parent;
				//y.parent=z.parent;
				z.left=y.right;
				z.parent=temp;
				//Print(binroot);
				transform(n,temp);
			}
			//System.out.println("ttttt");
		}else {
			if(lh-rh<-1 && new1.objectID>new1.parent.objectID) {   

				Object y = n.right;
				Object z = n;
				Object temp = new Object(y,z.left,null,z.capacity,z.objectID,z.pointBinID,z.pointbin,z.pointobj);
				//	System.out.println(y.capacity+""+z.capacity);
				if(temp.left!=null) {
					temp.left.parent=temp;
				}
				if(y.left!=null) {
					temp.right = new Object(y.parent,y.left.left,y.left.right,y.left.capacity,y.left.objectID,y.left.pointBinID,y.left.pointbin,y.left.pointobj);
					temp.right.parent=temp;
				}else
					temp.right=null;

				y.left=temp;

				//				System.out.println("_____>"+temp.right);
				if(z.parent.right==z) {
					z.parent.right=y;
					y.parent=z.parent;
				}else if(z.parent.left==z) {
					z.parent.left=y;
					y.parent=z.parent;
				}
				//Print(binroot);
			}
			else if(lh-rh<-1 && new1.objectID<new1.parent.objectID) {   
				//Print(binroot);
				Object y = n.right.left;
				Object z = n.right;
				Object temp = new Object(z.parent,y.left,z,y.capacity,y.objectID,y.pointBinID,y.pointbin,y.pointobj);
				//System.out.println(y.capacity+""+z.capacity);

				z.parent.right=temp;
				//temp.parent=z.parent;
				//y.parent=z.parent;
				z.left=y.right;
				z.parent=temp;
				//Print(binroot);
				transform(n,temp);
			}

			if(lh-rh>1 && new1.objectID<new1.parent.objectID) {
				//Print(binroot);
				Object y = n.left;
				Object z = n;
				Object temp = new Object(y,null,z.right,z.capacity,z.objectID,z.pointBinID,z.pointbin,z.pointobj);
				//	System.out.println(y.capacity+""+z.capacity+""+temp.capacity);
				if(temp.right!=null) {
					temp.right.parent=temp;
				}
				if(y.right!=null) {
					temp.left = new Object(y.parent,y.right.left,y.right.right,y.right.capacity,y.right.objectID,y.right.pointBinID,y.right.pointbin,y.right.pointobj);
					temp.left.parent=temp;
				}else
					temp.left=null;

				y.right=temp;
				//	System.out.println(y.right.capacity+""+y.left+""+temp.capacity);

				if(z.parent.right==z) {
					z.parent.right=y;
					y.parent=z.parent;
				}else if(z.parent.left==z) {
					//System.out.println("_____>"+temp.left);
					z.parent.left=y;
					y.parent=z.parent;
				}
				//Print(binroot);
			}
			else if(lh-rh>1 && new1.objectID>new1.parent.objectID) {   
				//Print(binroot);
				Object y = n.left.right;
				Object z = n.left;
				Object temp = new Object(z.parent,z,y.right,y.capacity,y.objectID,y.pointBinID,y.pointbin,y.pointobj);
				//System.out.println(y.capacity+""+z.capacity);

				z.parent.left=temp;
				//	temp.parent=z.parent;
				//y.parent=z.parent;
				z.right=y.left;
				//Print(binroot);
				transform(n,temp);
			}
		}
		//Printobj(objroot);

	}

	static void transform(obj n, obj new1, binID b) {
		//PrintBin(b.root,b);
		//if(new1.parent!=null) {
		//Printobj(objroot);

		if(n==b.root && new1==b.root) {
			if(lh-rh>1 ) {

				obj y = n.left;
				obj z = n;
				obj temp = new obj(y,null,z.right,z.capacity,z.id,z.pointobjid);

				if(temp.right!=null) {
					temp.right.parent=temp;
				}
				if(y.right!=null) {
					temp.left = new obj(y.parent,y.right.left,y.right.right,y.right.capacity,y.right.id,y.right.pointobjid);
					temp.left.parent=temp;
				}else
					temp.left=null;

				temp.pointobjid.pointobj=temp;
				y.right=temp;
				//				temp.capacity = z.capacity;
				//				temp.parent=y;
				//				temp.right=z.right;
				b.root=y;
				y.parent=null;

			}

			if(lh-rh<-1) {  

				obj y = n.right;
				obj z = n;
				obj temp = new obj(y,z.left,null,z.capacity,z.id,z.pointobjid);
				//System.out.println(y.capacity+"ttttt"+z.capacity);
				if(temp.left!=null) {
					temp.left.parent=temp;
				}
				if(y.left!=null) {
					temp.right = new obj(y.parent,y.left.left,y.left.right,y.left.capacity,y.left.id,y.left.pointobjid);
					temp.right.parent=temp;
				}else
					temp.right=null;

				temp.pointobjid.pointobj=temp;
				y.left=temp;
				//				temp.capacity = z.capacity;
				//				temp.parent=y;
				//				temp.left=z.left;
				b.root=y;
				y.parent=null;

				//Print(binroot);
			}
			//System.out.println("ttttt");
		}else if(n==b.root) {

			if(lh-rh>1 && new1.id<new1.parent.id ) {

				obj y = n.left;
				obj z = n;
				obj temp = new obj(y,null,z.right,z.capacity,z.id,z.pointobjid);

				if(temp.right!=null) {
					temp.right.parent=temp;
				}
				if(y.right!=null) {
					temp.left = new obj(y.parent,y.right.left,y.right.right,y.right.capacity,y.right.id,y.right.pointobjid);
					temp.left.pointobjid.pointobj=temp;
					temp.left.parent=temp;
				}else
					temp.left=null;

				z.pointobjid.pointobj=temp;
				//y.right.pointobjid.pointobj=temp.
				y.right=temp;
				//				temp.capacity = z.capacity;
				//				temp.parent=y;
				//				temp.right=z.right;
				b.root=y;
				y.parent=null;

			}
			else if(lh-rh>1 && new1.id>new1.parent.id) { 

				//Print(binroot);
				obj y = n.left.right;
				obj z = n.left;
				obj temp = new obj(z.parent,z,y.right,y.capacity,y.id,y.pointobjid);
				//		System.out.println(y.capacity+""+z.capacity);

				temp.pointobjid.pointobj=temp;
				z.parent.left=temp;
				//	temp.parent=z.parent;
				//y.parent=z.parent;
				z.right=y.left;

				z.parent=temp;
				//Print(binroot);
				transform(n,temp,b);
			}

			if(lh-rh<-1 && new1.id>new1.parent.id) {  

				obj y = n.right;
				obj z = n;
				obj temp = new obj(y,z.left,null,z.capacity,z.id,z.pointobjid);
				//System.out.println(y.capacity+"ttttt"+z.capacity);
				if(temp.left!=null) {
					temp.left.parent=temp;
				}
				if(y.left!=null) {
					temp.right = new obj(y.parent,y.left.left,y.left.right,y.left.capacity,y.left.id,y.left.pointobjid);
					temp.right.pointobjid.pointobj=temp;
					temp.right.parent=temp;
				}else
					temp.right=null;

				temp.pointobjid.pointobj=temp;
				y.left=temp;
				//				temp.capacity = z.capacity;
				//				temp.parent=y;
				//				temp.left=z.left;
				b.root=y;
				y.parent=null;

				//Print(binroot);
			}
			else if(lh-rh<-1 && new1.id<new1.parent.id) {   
				//System.out.println("ttttt");
				//Print(binroot);
				obj y = n.right.left;
				obj z = n.right;
				obj temp = new obj(z.parent,y.left,z,y.capacity,y.id,y.pointobjid);
				//		System.out.println(n.id+"                 cdcvsdvcdsfv      "+z.capacity);

				temp.pointobjid.pointobj=temp;
				z.parent.right=temp;
				//temp.parent=z.parent;
				//	y.parent=z.parent;
				z.left=y.right;
				z.parent=temp;
				//Print(binroot);
				transform(n,temp,b);
			}
			//System.out.println("ttttt");
		}else {

			//	System.out.println(new1.id+""+b.root);
			if(lh-rh<-1 && new1.id>new1.parent.id) {   

				obj y = n.right;
				obj z = n;
				obj temp = new obj(y,z.left,null,z.capacity,z.id,z.pointobjid);
				//	System.out.println(y.capacity+""+z.capacity);
				if(temp.left!=null) {
					temp.left.parent=temp;
				}
				if(y.left!=null) {
					temp.right = new obj(y.parent,y.left.left,y.left.right,y.left.capacity,y.left.id,y.left.pointobjid);
					temp.right.pointobjid.pointobj=temp;
					temp.right.parent=temp;
				}else
					temp.right=null;

				temp.pointobjid.pointobj=temp;
				y.left=temp;

				//				System.out.println("_____>"+temp.right);
				if(z.parent.right==z) {
					z.parent.right=y;
					y.parent=z.parent;
				}else if(z.parent.left==z) {
					z.parent.left=y;
					y.parent=z.parent;
				}

				//Print(binroot);
			}
			else if(lh-rh<-1 && new1.id<new1.parent.id) {   
				//Print(binroot);
				obj y = n.right.left;
				obj z = n.right;
				obj temp = new obj(z.parent,y.left,z,y.capacity,y.id,y.pointobjid);
				//System.out.println(y.capacity+""+z.capacity);

				temp.pointobjid.pointobj=temp;
				z.parent.right=temp;
				//temp.parent=z.parent;
				//y.parent=z.parent;
				z.left=y.right;
				z.parent=temp;
				//Print(binroot);
				transform(n,temp,b);
			}

			if(lh-rh>1 && new1.id<new1.parent.id) {
				//Print(binroot);
				obj y = n.left;
				obj z = n;
				obj temp = new obj(y,null,z.right,z.capacity,z.id,z.pointobjid);
				//	System.out.println(y.capacity+""+z.capacity+""+temp.capacity);
				if(temp.right!=null) {
					temp.right.parent=temp;
				}
				if(y.right!=null) {
					temp.left = new obj(y.parent,y.right.left,y.right.right,y.right.capacity,y.right.id,y.right.pointobjid);
					temp.left.pointobjid.pointobj=temp;
					temp.left.parent=temp;
				}else
					temp.left=null;

				temp.pointobjid.pointobj=temp;
				y.right=temp;
				//	System.out.println(y.right.capacity+""+y.left+""+temp.capacity);

				if(z.parent.right==z) {
					z.parent.right=y;
					y.parent=z.parent;
				}else if(z.parent.left==z) {
					//System.out.println("_____>"+temp.left);
					z.parent.left=y;
					y.parent=z.parent;
				}

				//Print(binroot);
			}
			else if(lh-rh>1 && new1.id>new1.parent.id) {   
				//Print(binroot);
				obj y = n.left.right;
				obj z = n.left;
				obj temp = new obj(z.parent,z,y.right,y.capacity,y.id,y.pointobjid);
				//System.out.println(y.capacity+""+z.capacity);

				temp.pointobjid.pointobj=temp;
				z.parent.left=temp;
				//	temp.parent=z.parent;
				//y.parent=z.parent;
				z.right=y.left;
				//Print(binroot);
				transform(n,temp,b);
			}
		}

		//	System.out.println("hi");
		//return null;
	}

	public static int add_object(int id,int cap){
		//Print(binroot);
		Bin b = findPosBIN(cap, binroot, id);
		//System.out.println(b.parent.capacity);
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
			//new1.level=new1.parent.level+1;
			//System.out.println(b.parent+"tanmay                      j ");
			DeleteBin(b);
			//System.out.println(b+"tanmay                      l ");

			binID bin = new binID();
			bin = binrootid;
			bin = findPosBINID(b.binID, bin);

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
				//bin.root=(o);
			}
			obj no = isBalanced(o);
			//System.out.println(n+"vhvhvhbjbjkknkjhcghvjkn jhgnjkjkbjbj");
			if(no==null) {

			}else {
				transform(no,o,bin);
			}
			//	System.out.println("");
			new1.pointobj=findPosobjsmall(o.id,bin.root);
			new1.pointobj.pointobjid=new1;
			//	System.out.println(new1.poi);
			bin.capacity=bin.capacity-new1.capacity;
			b.capacity=b.capacity-new1.capacity;
			//			System.out.println(bin.capacity);
			//			System.out.println(b.capacity);


			//Print(binroot);
			AddBinAgain(b.binID,b.capacity);
			new1.pointbin=new1.pointBinID.pointbin;

			Object n = isBalanced(new1);
			//System.out.println(new1.pointBinID+"     vhvhvhbjbjkknkjhcghvjkn jhgnjkjkbjbj");
			if(n==null) {

			}else {
				transform(n,new1);
			}

		}
		if(b!=null)
			return b.binID;
		else
			return 0;
	}


	static int height(obj node)  
	{ 
		/* base case tree is empty */
		if (node == null) 
			return 0; 

		/* If tree is not empty then height = 1 + max of left 
         height and right heights */
		return 1 + Math.max(height(node.left), height(node.right)); 
	} 

	static obj isBalanced(obj n) {
		/* for height of right subtree */
		// Print(root);
		/* If tree is empty then return true */
		if (n == null ) 
			return null; 


		/* Get the height of left and right sub trees */
		lh = height(n.left); 
		rh = height(n.right); 

		//System.out.println(lh+"         "+rh+"    "+n.id);
		if (Math.abs(lh - rh) <= 1)  
			return isBalanced(n.parent); 

		//System.out.println(lh+"      "+rh+"   "+n.objectID);
		/* If we reach here then tree is not height-balanced */
		return n;

	}


	public static obj getPositionObjectsmall(int id,obj base) {

		if(id<base.id) {
			if(base.left==null) {
				//System.out.println(base.capacity+"a");
				return base; 
			}
			return getPositionObjectsmall(id,base.left);

		}else if(id>base.id) {
			if(base.right==null) {
				//System.out.println(base.capacity+"b");
				return base; 
			}
			return getPositionObjectsmall(id,base.right);
		}
		return null;
	}


	public static obj findPosobjsmall(int id,obj base) {
		//	System.out.println(id+"nknnk");
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
		//	System.out.println(n.id+"  "+b.root.id +" "+b.binID);
		if(n==b.root) {
			//System.out.println("hi");
			if(n.left==null && n.right==null) {
				//	System.out.println("hi");
				b.root=null;
				return;
			}else if(n.right==null) {
				//	System.out.println("hi");
				b.root=n.left;
				return;
			}else {
				//	System.out.println("hi");
				obj k = inorderBST(n.right);
				n.capacity=k.capacity;
				n.id=k.id;
				n.pointobjid=k.pointobjid;
				n.pointobjid.pointobj=n;
				DeleteObjectsmall(k,b);

			}
		}else if(n.left==null) {
			//System.out.println("hi"+n.id);
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
					//n.right=n.parent;

				}else if(n.parent.left == n){
					n.parent.left=n.right;
					//n.right=n.parent;

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
			n.pointobjid.pointobj=n;
			//System.out.println(n.id+"ffff");
			DeleteObjectsmall(k,b);
		}
		//Printobj(objroot);
		obj o = isBalanced(n.parent);
		//System.out.println("");
		transform(o,n.parent,b);
		//		Printobj(objroot);
		//		System.out.println("end");
	}

	public static obj inorderBST(obj n){
		//System.out.println(n.id+"ucndicdnsja");
		if(n.left==null) {
			System.out.println(n.id);
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
		System.out.println(n.pointobjid.pointobj.id+"_________"+n.capacity);
		arr.add(n.pointobjid.pointobj.id+"_________"+n.capacity);
		if(n.left!=null)
			PrintBin(n.left);
		//System.out.println(n.objectID+"utkarsh"+"tanmay"+n.objectID);
		if(n.right!=null)
			PrintBin(n.right);
	}

	public static void AddBinAgain(int id,int cap){
		//		System.out.println(id+"t"+cap);
		//		Print(binroot);
		Bin add = new Bin();
		Bin new1 = new Bin();
		add=binroot;
		//System.out.println(add.capacity+"34");
		add=getPositionBIN(cap,add);
		//System.out.println(add);
		if(cap<add.capacity) {
			add.left=new1;
		}else {
			add.right=new1;
		}
		new1.binID=id;
		new1.capacity=cap;
		new1.left=null;
		new1.right=null;
		new1.parent=add;
		new1.pointBinID=findPosBINID(id,binrootid);
		//	Print(binroot);
		//System.out.println(add.level+" "+new1.level+" "+add.binID);
		//	new1.level=add.level+1;
		//System.out.println(add.level+" "+new1.level+" "+add.binID);

		Bin n = isBalanced(new1);
		//System.out.println(n);
		if(n==null) {

		}else {
			transform(n,new1);
		}
		//System.out.println(findPosBINID(id,binrootid)+"shantam"+new1.pointBinID);

		//System.out.println("shantam"+new1.pointBinID);
		//new1.pointBinID.pointbin.pointBinID=new1.pointBinID;
		new1.pointBinID.pointbin=new1;

		//		Print(binroot);
		//		System.out.println("");

	}

	public static Bin getPositionBIN(int capacity,Bin base) {
		//Print(binroot);
		if(capacity<base.capacity) {
			if(base.left==null) {
				//	System.out.println(base.capacity+"a");
				return base; 
			}
			return getPositionBIN(capacity,base.left);

		}else if(capacity>=base.capacity) {
			if(base.right==null) {
				//System.out.println(base.capacity+"b");
				return base; 
			}
			return getPositionBIN(capacity,base.right);
		}
		return null;
	}

	public static binID getPositionBINID(int id,binID base) {
		//System.out.println(capacity+"c");
		if(id<base.binID) {
			if(base.left==null) {
				//System.out.println(base.capacity+"a");
				return base; 
			}
			return getPositionBINID(id,base.left);

		}else if(id>base.binID) {
			if(base.right==null) {
				//System.out.println(base.capacity+"b");
				return base; 
			}
			return getPositionBINID(id,base.right);
		}
		return null;
	}

	public static Object getPositionObjectID(int id,Object base) {
		//System.out.println(capacity+"c");
		if(id<base.objectID) {
			if(base.left==null) {
				//System.out.println(base.capacity+"a");
				return base; 
			}
			return getPositionObjectID(id,base.left);

		}else if(id>base.objectID) {
			if(base.right==null) {
				//System.out.println(base.capacity+"b");
				return base; 
			}
			return getPositionObjectID(id,base.right);
		}
		return null;
	}

	public static Bin findPosBIN(int cap,Bin n3, int id) {
		//Bin n3 = new Bin();
		//n3=base;
		if(n3.right == null) {
			if(n3.capacity>cap)
				return n3;
			else
				return null;
		}else {
			return findPosBIN(cap,n3.right,id);
		}
	}

	public static Bin findPosBINi(Bin n3) {
		//Bin n3 = new Bin();
		//n3=base;
		if(n3.right == null) {
			return n3;
		}else {
			return findPosBINi(n3.right);
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
		//System.out.println(id+"u"+base.capacity+"t"+base.objectID);
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
		//		//System.out.println(n.objectID+"xdxdsx");
		//		if(n==objroot) {
		//			//System.out.println("hi");
		//			if(n.left==null && n.right==null) {
		//			//	System.out.println("hi");
		//				objroot=null;
		//				return;
		//			}else if(n.right==null) {
		//			//	System.out.println("hi");
		//				objroot=n.left;
		//				return;
		//			}else {
		//			//	System.out.println("hi");
		//				Object k = inorderBST(n.right);
		//				n.capacity=k.capacity;
		//				n.objectID=k.objectID;
		//				n.pointbin=k.pointbin;
		//				n.pointBinID=k.pointBinID;
		//				n.pointobj=k.pointobj;
		//				DeleteObject(k);
		//			}	
		//		}else 
		if(n.left==null) {
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
					//n.right=n.parent;

				}else if(n.parent.left == n){
					n.parent.left=n.right;
					//n.right=n.parent;

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
			DeleteObject(k);
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
		/* for height of right subtree */
		// Print(root);
		/* If tree is empty then return true */
		if (n == null ) 
			return null; 

		//System.out.println(lh+"      "+rh);
		/* Get the height of left and right sub trees */
		lh = height(n.left); 
		rh = height(n.right); 
		// System.out.println(lh+"         "+rh+"    "+n.binID);
		if (Math.abs(lh - rh) <= 1)  
			return isBalanced(n.parent); 

		/* If we reach here then tree is not height-balanced */
		return n;

	}

	static int height(binID node)  
	{ 
		/* base case tree is empty */
		if (node == null) 
			return 0; 

		/* If tree is not empty then height = 1 + max of left 
         height and right heights */
		return 1 + Math.max(height(node.left), height(node.right)); 
	} 


	static Bin isBalanced(Bin n) {
		/* for height of right subtree */
		// Print(root);
		/* If tree is empty then return true */
		if (n == null ) 
			return null; 

		//System.out.println(lh+"      "+rh);
		/* Get the height of left and right sub trees */
		lh = height(n.left); 
		rh = height(n.right); 
		//  System.out.println(lh+"         "+rh);
		if (Math.abs(lh - rh) <= 1)  
			return isBalanced(n.parent); 

		/* If we reach here then tree is not height-balanced */
		return n;

	}

	static int height(Bin node)  
	{ 
		/* base case tree is empty */
		if (node == null) 
			return 0; 

		/* If tree is not empty then height = 1 + max of left 
         height and right heights */
		return 1 + Math.max(height(node.left), height(node.right)); 
	} 

	static Object isBalanced(Object n) {
		/* for height of right subtree */
		// Print(root);
		/* If tree is empty then return true */
		if (n == null ) 
			return null; 


		/* Get the height of left and right sub trees */
		lh = height(n.left); 
		rh = height(n.right); 

		//System.out.println(lh+"         "+rh+"    "+n.objectID);
		//Printobj(objroot);
		if (Math.abs(lh - rh) <= 1)  
			return isBalanced(n.parent); 

		//System.out.println(lh+"      "+rh+"   "+n.objectID);
		/* If we reach here then tree is not height-balanced */
		return n;

	}

	static int height(Object node)  
	{ 
		/* base case tree is empty */
		if (node == null) 
			return 0; 

		/* If tree is not empty then height = 1 + max of left 
         height and right heights */
		return 1 + Math.max(height(node.left), height(node.right)); 
	} 
}
