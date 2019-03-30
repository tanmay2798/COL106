package avltree;


public class tree {

	public static int lh; /* for height of left subtree */		   
	public static int rh;

	public static node root= new node();
	public static void main(String[] args) {
		root.parent = null;

		root.value=1;
		root.left=null;
		root.right = null;
		add(2);

		add(3);
				add(4);
				add(5);
				add(6);

		//Print(root);
	}

	public static void Print(node n) {
		if(n==null)
			return;
		
		System.out.println(n.value);
		
		if(n.left!=null)
			Print(n.left);
		
		if(n.right!=null)
			Print(n.right);
	}

	public static void add(int value) {
		node b=getPositionBINID(value,root);
		node a = new node();
		if(value<b.value) {
			b.left=a;
		}else {
			b.right=a;
		}

		a.parent=b;
		a.value=value;
		a.left=null;
		a.right=null;
		//	Print(root);
		System.out.println(isBalanced(root)+"utkarsh");
		//	Print(root);
		System.out.println("uuuuu");
		Print(root);
		node n = isBalanced(a);
		if(n==null) {

		}else {
			transform(n,a);
		}

	}





	static void transform(node n, node new1) {
		if(n==root) {
			if(lh-rh>1 && new1.value<new1.parent.value) {
				node y = n.left;
				node z = n;
				node temp = new node(y,null,z.right,z.value);
				System.out.println(y.value+"ttttt"+z.value);
				if(y.right!=null)
					temp.left = new node(y.parent,y.right.left,y.right.right,y.right.value);
				else
					temp.left=null;

				System.out.println(root.left.value+"tanmay1");
				y.right=temp;
				root = y;
//				temp.value = z.value;
//				temp.parent=y;
//				temp.right=z.right;
				//root=y;
				
				Print(root);
			}else if(lh-rh>1 && new1.value>new1.parent.value) {   
				Print(root);
				node y = n.left.right;
				node z = n.left;
				node temp = new node(z.parent,z,y.right,y.value);
				System.out.println(y.value+""+z.value);

				System.out.println(root.left.value+"tanmay0");
				z.parent.left=temp;
				temp.parent=z.parent;
				y.parent=z.parent;
				z.right=y.left;
				Print(root);
				
				transform(n,temp);
			}
			
			
			
			
			
			if(lh-rh<-1 && new1.value>new1.parent.value) {   
			//	node x = n.right.right;
				node y = n.right;
				node z = n;
				node temp = new node(y,z.left,null,z.value);
				System.out.println(y.value+"ttttt"+z.value);
				if(y.left!=null)
					temp.right = new node(y.parent,y.left.left,y.left.right,y.left.value);
				else
					temp.right=null;

				//x.left=y.left;
				//y.left.parent=null;
				y.left=temp;
				//root = new node(null,y.left,y.right,y.value);
				
				y.parent=null;
				root=y;
//				temp.value = z.value;
//				temp.parent=y;
//				temp.left=z.left;
				//root=y;
				//y.parent=null;
				//root.right.parent=root.right;
				System.out.println(new1.parent.value+"tanmay1122w13");
				System.out.println(root.right.value+"tanmay2");
				Print(root);
			}else if(lh-rh<-1 && new1.value<new1.parent.value) {   
				//System.out.println(root.left.value+"tanmay3");
				Print(root);
				node y = new1;
				node z = new1.parent;
				node temp = new node(z.parent,y.left,z,y.value);
				System.out.println(y.value+""+z.value);
				
				z.parent.right=temp;
				temp.parent=z.parent;
				y.parent=z.parent;
				z.left=y.right;
				z.parent=temp;
				Print(root);
				System.out.println(root.value+"tanmay1122w13");
				transform(n,new1);
				
			}

		}else {
			if(lh-rh<-1 && new1.value>new1.parent.value) {   
				System.out.println(lh+"    "+rh);
				node y = n.right;
				node z = n;
				node temp = new node(y,z.left,null,z.value);
				System.out.println(y.value+""+z.value);
				if(y.left!=null)
					temp.right = new node(y.parent,y.left.left,y.left.right,y.left.value);
				else
					temp.right=null;

				y.left=temp;

				System.out.println("_____>"+temp.right);
				if(z.parent.right==z) {
					z.parent.right=y;
					y.parent=z.parent;
				}else if(z.parent.left==z) {
					z.parent.left=y;
					y.parent=z.parent;
				}
				Print(root);
			}else if(lh-rh<-1 && new1.value<new1.parent.value) {   
				Print(root);
				node y = new1;
				node z = new1.parent;
				node temp = new node(z.parent,y.left,z,y.value);
				System.out.println(y.value+""+z.value);

				z.parent.right=temp;
				temp.parent=z.parent;
				y.parent=z.parent;
				z.left=y.right;
				Print(root);
				transform(n,new1);
			}

			if(lh-rh>1 && new1.value<new1.parent.value) {
				Print(root);
				node y = n.left;
				node z = n;
				node temp = new node(y,null,z.right,z.value);
				System.out.println(y.value+""+z.value+""+temp.value);
				if(y.right!=null)
					temp.left = new node(y.parent,y.right.left,y.right.right,y.right.value);
				else
					temp.left=null;

				y.right=temp;
				System.out.println(y.right.value+""+y.left+""+temp.value);

				if(z.parent.right==z) {
					z.parent.right=y;
					y.parent=z.parent;
				}else if(z.parent.left==z) {
					System.out.println("_____>"+temp.left);
					z.parent.left=y;
					y.parent=z.parent;
				}
				Print(root);
			}else if(lh-rh>1 && new1.value>new1.parent.value) {   
				Print(root);
				node y = new1;
				node z = new1.parent;
				node temp = new node(z.parent,z,y.right,y.value);
				System.out.println(y.value+""+z.value);

				z.parent.left=temp;
				temp.parent=z.parent;
				y.parent=z.parent;
				z.right=y.left;
				Print(root);
				transform(n,new1);
			}
		}

	}

	static node isBalanced(node n) {
		/* for height of right subtree */
		// Print(root);
		/* If tree is empty then return true */
		if (n == null ) 
			return null; 


		/* Get the height of left and right sub trees */
		lh = height(n.left); 
		rh = height(n.right); 
		  System.out.println(lh+"    vfevev     "+rh+"    "+n.value);
		if (Math.abs(lh - rh) <= 1)  {
			 System.out.println(lh+"    vfevev     "+rh+"   "+n.value);
			return isBalanced(n.parent);
		}

		/* If we reach here then tree is not height-balanced */
		return n;

	}

	static int height(node node)  
	{ 
		/* base case tree is empty */
		if (node == null) 
			return 0; 

		/* If tree is not empty then height = 1 + max of left 
         height and right heights */
		return 1 + Math.max(height(node.left), height(node.right)); 
	} 

	public static node getPositionBINID(int id,node base) {
		//System.out.println(capacity+"c");
		if(id<base.value) {
			if(base.left==null) {
				//System.out.println(base.capacity+"a");
				return base; 
			}
			return getPositionBINID(id,base.left);

		}else if(id>base.value) {
			if(base.right==null) {
				//System.out.println(base.capacity+"b");
				return base; 
			}
			return getPositionBINID(id,base.right);
		}
		return null;
	}

}
