package ass33;


public class binID {

	public int binID;
	public int capacity;
	public Bin pointbin;
	public binID left;
	public binID right;
	public binID parent;
	public obj root;
	
	public binID() {
		this.parent = null;
		this.left = null;
		this.right = null;
		this.binID=0;
		this.capacity=0;
		this.pointbin=null;
		this.root=null;
	}
	
	public binID(binID parent,binID left,binID right,int value, int bin,Bin pointer, obj o) {
		this.parent = parent;
		this.left = left;
		this.right = right;
		this.capacity=value;
		this.binID=bin;
		this.pointbin=pointer;
		this.root=o;
	}
	
}
