//package Assignment5;

import java.util.ArrayList;

public class com_node {
	
	//public char key;
	public int start;
	public int last;
	public int index;
	public int length;
	public ArrayList<com_node> children = new ArrayList<>();
	//public ArrayList<Integer> index = new ArrayList<>();
	public boolean end;

	public com_node() {
		this.start=-1;
		this.last=-1;
		this.end=false;
		this.children=null;
		this.length=-1;
		
	}
	public com_node(int start,int last, int index, int length,ArrayList<com_node> children,boolean end) {
		this.start=start;
		this.last=last;
		this.end=end;
		this.children=children;
		this.length=length;
		this.index=index;
		
	}

}

