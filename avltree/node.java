package avltree;

public class node {
	public node parent;
	public int value;
	public node left;
	public node right;
	public node() {
		this.parent = null;
		this.left = null;
		this.right = null;
		this.value=0;
	}
	public node(node parent,node left,node right,int value) {
		this.parent = parent;
		this.left = left;
		this.right = right;
		this.value=value;
	}

}
