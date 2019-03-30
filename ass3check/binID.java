package ass3check;


public class binID {

	public static int lh; /* for height of left subtree */		   
	public static int rh;

	public int binID;
	public int capacity;
	public Bin pointbin;
	public binID left;
	public binID right;
	public binID parent;
	//public int level;
	public obj root;

	//public Vector<obj> objects = new Vector<obj>();

	public binID() {
		this.parent = null;
		this.left = null;
		this.right = null;
		this.binID=0;
		this.capacity=0;
		this.pointbin=null;
		//	this.objects=null;
		this.root=null;
	}

	public binID(binID parent,binID left,binID right,int value, int bin,Bin pointer, obj r) {
		this.parent = parent;
		this.left = left;
		this.right = right;
		this.capacity=value;
		this.binID=bin;
		this.pointbin=pointer;
		this.root=r;

	}

	public obj AddObject(obj n,binID b) {
		if(b.root==null) {
			n.left=null;
			n.right=null;
			n.parent=null;
			b.root=n;
			//n.pointobjid=b.
		}else {
			obj new1 = new obj();
			new1=getPositionBINID(n.id, b.root);
			//obj add = new obj();
			if(n.id<new1.capacity) {
				new1.left=n;
			}else {
				new1.right=n;
			}
			n.parent=new1;
			n.left=null;
			n.right=null;
		}
		obj o = isBalanced(n);
		return transform(o,n,b);

		//PrintObject();
	}


	public obj DeleteObject(obj n,binID b) {
		System.out.println(n+"xdxdsx"+b.root);
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
			obj k = inorderBST(n.right);
			n.capacity=k.capacity;
			n.id=k.id;
			return DeleteObject(k,b);
		}
		//PrintBin(b.root,b);
		obj o = isBalanced(n.parent);
	//	System.out.println(o.id+"hjbbjbhjxjkasnxaskxnkla");
		return transform(o,n.parent,b);

	}

	public static obj inorderBST(obj n){
		if(n.left==null) {
			return n;
		}else {
			return inorderBST(n.left);
		}
	}

	public static obj getPositionBINID(int id,obj base) {

		if(id<base.id) {
			if(base.left==null) {
				//System.out.println(base.capacity+"a");
				return base; 
			}
			return getPositionBINID(id,base.left);

		}else if(id>base.id) {
			if(base.right==null) {
				//System.out.println(base.capacity+"b");
				return base; 
			}
			return getPositionBINID(id,base.right);
		}
		return null;
	}

	public static void PrintBin(obj n, binID b){

		if(n==null)
			return;
		System.out.println(n.capacity+"shantam"+n.id+" "+b.binID);
		if(n.left!=null)
			PrintBin(n.left,b);

		if(n.right!=null)
			PrintBin(n.right,b);

	}


	static obj transform(obj n, obj new1, binID b) {
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
				return temp;
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
				return temp;
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
					return temp;
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
					return transform(n,temp,b);
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
					return temp;
					//Print(binroot);
				}
				else if(lh-rh<-1 && new1.id<new1.parent.id) {   
					System.out.println("ttttt");
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
					return transform(n,temp,b);
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
					return temp;
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
					return transform(n,temp,b);
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
					return temp;
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
					return transform(n,temp,b);
				}
			}
		
		//	System.out.println("hi");
		return null;
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

}
