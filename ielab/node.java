package ielab;

import java.util.ArrayList;

public class node {
	
	public ArrayList<node> child = new ArrayList<node>();
	public int distance;
	public int updatedistance;
	public int value;
	public boolean visited; 
	
	public node() {
		this.child=new ArrayList<node>();
		this.visited=false;
		this.updatedistance=0;
	}
	public node(int distance1,int value1,boolean b) {
		this.child=new ArrayList<node>();
		this.visited=b;
		this.distance= distance1;
		this.value=value1;
		this.updatedistance=0;
	}
}
