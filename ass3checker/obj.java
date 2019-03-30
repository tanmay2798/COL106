//package ass3checker;

public class obj {

	public int capacity;
	public int id;
	public obj left;
	public obj right;
	public obj parent;
	public Object pointobjid;
	public int h;
	
	public obj() {
		this.parent = null;
		this.left = null;
		this.right = null;
		this.capacity=0;
		this.id=0;
		this.pointobjid=null;
		this.h=0;
	}
	
//	public obj(obj parent,obj left,obj right,int value, int bin,Object b,int n) {
//		this.parent = parent;
//		this.left = left;
//		this.right = right;
//		this.capacity=value;
//		this.id=bin;
//		this.pointobjid=b;
//		this.h=n;
//	}
	
}