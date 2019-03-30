package ass3final;


public class Bin {
	public int capacity;
	public int binID;
	//linked list = new linked();
	public Bin left;
	public Bin right;
	public Bin parent;
	public binID pointBinID;
	//public int level;
	
	public Bin() {
		this.parent = null;
		this.left = null;
		this.right = null;
		this.binID=0;
		this.capacity=0;
		this.pointBinID=null;
		//this.level=0;
	}
	
	public Bin(Bin parent,Bin left,Bin right,int value, int bin,binID pointer) {
		this.parent = parent;
		this.left = left;
		this.right = right;
		this.capacity=value;
		this.binID=bin;
		this.pointBinID=pointer;
	}
}
