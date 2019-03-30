package Assignment3;

public class check {
	
	public static Object objroot = new Object();
	public static Bin binroot = new Bin();
	public static binID binrootid = new binID();
	
	public static void main(String[] args) {	

		binroot.capacity = 10;
		binroot.left=null;
		binroot.right=null;
		binroot.binID=1;


		binrootid.left=null;
		binrootid.right=null;
		binrootid.binID=1;
		binrootid.capacity=10;

		objroot.capacity = 10;
		objroot.left=null;
		objroot.right=null;
		objroot.objectID=1;


		AddBinAgain(2,20);
		AddBinAgain(3,30);
		AddBinAgain(4,15);
		AddBinAgain(6,5);
		DeleteBin(findPosBIN(20,binroot));
	//	AddBinAgain(3,12);
		DeleteBin(findPosBIN(0,binroot));
	//	AddBinAgain(2,6);
	//	System.out.println(findPosBIN(0,binroot,4));
		Print(binroot);
		System.out.println(binroot.capacity+" "+binroot.right.capacity);
		DeleteBin(findPosBIN(0,binroot));
	//	AddBinAgain(4,4);
		Print(binroot);
		
		//	PrintobjID(binrootid);
	}
	
	public static Bin findPosBIN(int cap,Bin base1) {
		Bin n3 = new Bin();
		n3=base1;
		
		//Print(binroot);
		if(n3.right == null) {
			if(n3.capacity>cap)
				return n3;
			else
				return null;
		}else {
			return findPosBIN(cap,n3.right);
		}
	}
	public static void AddBinAgain(int id,int cap){
		//	System.out.println(id+"t"+cap);
		Bin add = new Bin();
		Bin new1 = new Bin();
		add=binroot;
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
	}
	
	public static Bin getPositionBIN(int capacity,Bin base) {
		//System.out.println(capacity+"c");
		if(capacity<base.capacity) {
			if(base.left==null) {
				//System.out.println(base.capacity+"a");
				return base; 
			}
			return getPositionBIN(capacity,base.left);

		}else if(capacity>base.capacity) {
			if(base.right==null) {
				//System.out.println(base.capacity+"b");
				return base; 
			}
			return getPositionBIN(capacity,base.right);
		}
		return null;
	}

	public static void DeleteBin(Bin n) {
	//	System.out.println(n+""+n.parent.right+"__________"+n.parent.capacity);
		if(n.left==null) {
			if(n.parent.right == n) {
				n.parent.right=n.right;
				//n.right.parent=n.parent;
			}else if(n.parent.left == n) {
				n.parent.left=n.right;
				//n.right.parent=n.parent;
			}
		}else if(n.right==null) {
			
			if(n.parent.right == n) {	
				System.out.println("tttt"+n.parent.right.capacity);
				n.parent.right=n.left;
				n.left.parent=n.parent;
				//System.out.println("tttt"+n.parent.right.capacity+"lll"+binroot.right.capacity);
			}else if(n.parent.left == n){
				
				n.parent.left=n.left;
				n.left.parent=n.parent;
				
			}
			
		}
		//System.out.println(n+"tttt"+n.parent.left.capacity+"__________"+n.parent.capacity+" "+binroot.right.capacity);
//		else {
//			Bin k = inorderBSTb(n.right);
//			n.capacity=k.capacity;
//			n.binID=k.binID;
//			DeleteBin(k);
//		}
		//Print(binroot);
	}

	

	public static void Print(Bin n) {
		if(n==null)
			return;
		if(n.left!=null)
			Print(n.left);
		System.out.println(n.capacity+"tanmay"+n.binID);
		if(n.right!=null)
			Print(n.right);
	}

}
