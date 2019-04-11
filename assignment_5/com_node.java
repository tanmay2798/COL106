package assignment_5;

import java.util.ArrayList;

public class com_node {
	
	//public char key;
	public int start;
	public int last;
	public int index;
	public ArrayList<com_node> children = new ArrayList<>();
	//public ArrayList<Integer> index = new ArrayList<>();
	public boolean end;

	public com_node() {
		this.start=-1;
		this.last=-1;
		this.end=false;
		this.children=null;
	//	this.index=null;
		
	}

}

