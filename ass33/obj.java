package ass33;

public class obj {

	public int capacity;
	public int id;
	public obj left;
	public obj right;
	public obj parent;
	public Object pointobjid;
	
	public obj() {
		this.parent = null;
		this.left = null;
		this.right = null;
		this.capacity=0;
		this.id=0;
		this.pointobjid=null;
	}
	
	public obj(obj parent,obj left,obj right,int value, int bin,Object b) {
		this.parent = parent;
		this.left = left;
		this.right = right;
		this.capacity=value;
		this.id=bin;
		this.pointobjid=b;
	}
	
}