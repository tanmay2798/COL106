package ass33;

public class Object {

	public int objectID;
	public int capacity;
	public Bin pointbin;
	public binID pointBinID;
	public Object left;
	public Object right;
	public Object parent;
	public obj pointobj;
	
	public Object() {
		this.parent = null;
		this.left = null;
		this.right = null;
		this.objectID=0;
		this.capacity=0;
		this.pointBinID=null;
		this.pointbin=null;
		this.pointobj=null;
	}
	
	public Object(Object parent,Object left,Object right,int value, int bin,binID pointer,Bin b, obj obj) {
		this.parent = parent;
		this.left = left;
		this.right = right;
		this.capacity=value;
		this.objectID=bin;
		this.pointBinID=pointer;
		this.pointbin=b;
		this.pointobj=obj;
	}
}
