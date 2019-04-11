package assignment_5;

import java.util.ArrayList;

public class node {
	
	public char key;
	public ArrayList<node> children = new ArrayList<>();
	public ArrayList<Integer> index = new ArrayList<>();
	public boolean end;

	public node() {
		this.end=false;
		this.children=null;
		this.index=null;
		
	}

}

