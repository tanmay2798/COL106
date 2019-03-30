package ass3check;
import java.util.*;


public class Tree {

	public static int lh; /* for height of left subtree */		   
	public static int rh;

	public static obj root = new obj();
	public static Object objroot = new Object();
	public static Bin binroot = new Bin();
	public static binID binrootid = new binID();
	public static ArrayList<String> arr = new ArrayList<>();

	public static void main(String[] args) {	

//Print(binroot);
		
		
//				Print(binroot);
//				PrintobjID(binrootid);
//				Printobj(objroot);
		
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
				//objroot.level=0;

				//		Print(binroot);
				//PrintobjID(binrootid);
				AddBin(1,5);
				//Print(binroot);
				//PrintobjID(binrootid);
				AddBin(2,7);
				//		Print(binroot);
				//		PrintobjID(binrootid);
				AddBin(3,11);
				//		Print(binroot);
				//		PrintobjID(binrootid);
				AddBin(4,13);

				AddBin(5,17);
				AddBin(6,19);
				AddBin(7,23);
				AddBin(8,31);
				AddBin(9,9);
				AddBin(10,4);
				AddBin(11,6);
				AddBin(12,3);
				AddBin(13,9);

				Bin b = findPosBIN(objroot.capacity, binroot, objroot.objectID);	
				if(b!=null) {
					binID bin = new binID();
					bin = binrootid;
					bin = findPosBINID(b.binID, bin);

					objroot.pointBinID=bin;
					
					obj o = new obj();
					o.capacity=objroot.capacity;
					o.id=objroot.objectID;
					o.pointobjid=objroot;
					bin.AddObject(o, bin);
//					if(ob!=null)
//						objroot.pointobj=ob;
//					else
					objroot.pointobj=o;
					bin.capacity=bin.capacity-objroot.capacity;
					//	System.out.println(b.capacity);
					b.capacity=b.capacity-objroot.capacity;
					//	System.out.println(b.capacity);
					DeleteBin(b);
					//Print(binroot);
					AddBinAgain(b.binID,b.capacity);
					objroot.pointbin=b;
				}
				AddObject(1,4);
				AddObject(2,6);
				AddObject(3,10);
				AddObject(4,12);
				AddObject(5,16);
				AddObject(6,18);
				AddObject(7,30);
				AddObject(8,29);
				AddObject(9,8);
				AddObject(10,5);
				AddObject(11,10);
				AddObject(12,8);
				Print(binroot);
				PrintobjID(binrootid);
				Printobj(objroot);
//				DeleteObject(2);
//				DeleteObject(0);
//				DeleteObject(4);
//				DeleteObject(3);
//				DeleteObject(1);
		//		try {
		//			FileInputStream fstream =new FileInputStream(args[0]);
		//			Scanner s1 = new Scanner(fstream);
		//			int i= Integer.parseInt(s1.nextLine());
		//			String[] str = s1.nextLine().split("\\s+");
		//
		//			String CEO=str[1];
		//			arr.add(CEO);
		//			root.object=CEO;
		//			root.level=1;
		//			root.parent=null;
		//			first.capacity=CEO;
		//			first.point=root;
		//			first.left=null;
		//			first.right=null;
		//			AddEmployee(str[0],str[1]);	
		//
		//			for(int j=0;j<i-2;j++) {
		//				str = s1.nextLine().split("\\s+");
		//				AddEmployee(str[0],str[1]);	
		//				//System.out.println(str[0]+"3432");
		//			}
		//			//System.out.println("hi");
		//			i= Integer.parseInt(s1.nextLine());
		//			//System.out.println(i+"tanmay");
		//			for(int j=0;j<i;j++) {
		//				str = s1.nextLine().split("\\s+");
		//				//System.out.println("tanmay"+str.toString());
		//				if(str[0].equals("0")) {
		//					AddEmployee(str[1],str[2]);	
		//				}
		//				if(str[0].equals("1")) {
		//					DeleteBin(str[1],str[2]);	
		//				}
		//				if(str[0].equals("2")) {
		//					LowestCommonBoss(str[1],str[2]);	
		//				}
		//				if(str[0].equals("3")) {
		//					PrintEmployees(root);
		//				//	PrintEmployeesin(first)	;
		//				}
		//			}
		//			s1.close();
		//		} catch (FileNotFoundException e) {
		//			// TODO Auto-generated catch block
		//			System.out.println("File not found");
		//		} catch (java.util.NoSuchElementException e) {
		//			//e.printStackTrace();
		//		} catch (java.lang.NullPointerException e) {
		//			System.out.println("Name not found");
		//		}
	}

	public static void PrintBin(int id) {
		binID b = findPosBINID(id, binrootid);
		b.PrintBin(b.root,b);
	}

	public static void AddBin(int id,int cap){
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
			transform(n,new1);
		}

		binID n1 = isBalanced(new1binid);
		if(n1==null) {

		}else {
			transform(n1,new1binid);
		}
	}



	static void transform(Bin n, Bin new1) {
		//Print(binroot);
		//System.out.println("");
		if(n==binroot) {
			if(lh-rh>1 && new1.capacity<new1.parent.capacity) {
				Bin y = n.left;
				Bin z = n;
				Bin temp = new Bin(y,null,z.right,z.capacity,z.binID,z.pointBinID);
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
		if(n==binrootid) {
			if(lh-rh>1 && new1.binID<new1.parent.binID) {
				binID y = n.left;
				binID z = n;
				binID temp = new binID(y,null,z.right,z.capacity,z.binID,z.pointbin,z.root);
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
		if(n==objroot) {

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

	public static void AddObject(int id,int cap){
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
			o.capacity=cap;
			o.id=id;
			o.pointobjid=new1;
			bin.AddObject(o, bin);
//			if(ob!=null)
//				new1.pointobj=ob;
//			else
			//	new1.pointobj=o;
			//new1.pointobj=o;
			//System.out.println(b.capacity);
			bin.capacity=bin.capacity-new1.capacity;
			b.capacity=b.capacity-new1.capacity;
			//			System.out.println(bin.capacity);
			//			System.out.println(b.capacity);


			//Print(binroot);
			AddBinAgain(b.binID,b.capacity);
			new1.pointbin=b;

			Object n = isBalanced(new1);
			//System.out.println(n+"vhvhvhbjbjkknkjhcghvjkn jhgnjkjkbjbj");
			if(n==null) {

			}else {
				transform(n,new1);
			}

		}
	}

	public static void DeleteObject(int rem){
		Object remove = new Object();
		remove = findPosObject(rem, objroot);
		binID b = remove.pointBinID;
		//b=;
		System.out.println(remove.pointobj+"tt");
		Bin b1 = new Bin();
		b1=remove.pointbin;

		DeleteBin(b.pointbin);

		b1.capacity=b1.capacity+remove.capacity;
		remove.pointBinID.capacity=remove.pointBinID.capacity+remove.capacity;
		//		for(int i=0;i<remove.pointBinID.objects.size();i++) {
		//			System.out.println(remove.pointBinID.objects.get(i).id+"kkk");
		//		}

		//		System.out.println(remove.pointBinID.objects.remove(remove.pointobj));
		b.DeleteObject(remove.pointobj,b);

		//		for(int i=0;i<remove.pointBinID.objects.size();i++) {
		//			System.out.println(remove.pointBinID.objects.get(i).id+"kkk");
		//		}
		AddBinAgain(b.binID,b.capacity);
		//b.level=b.parent.level+1;
		//System.out.println(b.level+"uu");
		Object temp = remove.parent;
		DeleteObject(remove);
		Object n = isBalanced(temp);
		//System.out.println(n+"vhvhvhbjbjkknkjhcghvjkn jhgnjkjkbjbj");
		if(n==null) {

		}else {
			transform(n,temp);
		}

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

	public static void DeleteBin(Bin n) {
		//System.out.println(n.capacity+"llllll"+n.pointBinID.capacity);
		//	Print(binroot);
		if(n.left==null) {

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

	public static void DeleteObject(Object n) {
		//System.out.println(n.parent.objectID+"xdxdsx");
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
			n.objectID=k.objectID;///////////////
			n.pointbin=k.pointbin;
			n.pointBinID=k.pointBinID;
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

	public static void Print(Bin n) {
		if(n==null)
			return;
		System.out.println(n.binID+"tanmay"+n);
		if(n.left!=null)
			Print(n.left);

		if(n.right!=null)
			Print(n.right);
	}

	public static void Printobj(Object n) {
		if(n==null)
			return;
		System.out.println(n.capacity+"utkarsh"+"tanmay"+n.objectID);
		if(n.left!=null)
			Printobj(n.left);
		//System.out.println(n.objectID+"utkarsh"+"tanmay"+n.objectID);
		if(n.right!=null)
			Printobj(n.right);
	}

	public static void PrintobjID(binID n) {
		if(n==null)
			return;
		System.out.println(n.binID+"     "+n.pointbin);
		if(n.left!=null)
			PrintobjID(n.left);
		//System.out.println(n.binID+"aditya"+n.capacity+"yyyyyyy");
		if(n.right!=null)
			PrintobjID(n.right);
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
