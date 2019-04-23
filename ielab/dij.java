package ielab;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class dij {

	static ArrayList<node> n = new ArrayList<node>(); 
	static ArrayList<node> visited = new ArrayList<node>(); 
	static ArrayList<node> notvisited = new ArrayList<node>(); 
	static ArrayList<node> no = new ArrayList<node>();

	public static void main(String[] args) {
		FileInputStream fstream;
		try {
			fstream = new FileInputStream("/Users/tanmaygoyal/Downloads/tantan.txt");
			Scanner scan = new Scanner(fstream);
			while(scan.hasNextLine()) {
				String[] s = scan.nextLine().split(" ");
				node n1 = new node(-1,Integer.parseInt(s[0]),false);
				n.add(n1);
				notvisited.add(n1);
				for(int i=1;i<s.length;i=i+2) {
					node n2 = new node(Integer.parseInt(s[i+1]),Integer.parseInt(s[i]),false);
					n.get(n.size()-1).child.add(n2);
				}


			}

			for(int y=0;y<n.size();y++) {
				System.out.println(n.get(y).value);
				for(int j=0;j<n.get(y).child.size();j++) {
					System.out.println(n.get(y).child.get(j).value+"  "+n.get(y).child.get(j).distance);
				}
				System.out.println("\n");
			}
			algo(0);

			for(int u=0;u<no.size();u++) {
				System.out.println(no.get(u).value+" "+no.get(u).updatedistance+"pppp");
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static void algo(int s) {
		node start  =n.get(s);
		node point = null;
		int r=0;
		int min=0;
		visited.add(start);
		no.add(start);

		while(no.size()!=10 && visited.size()!=0) {
			boolean check=false;
			System.out.println(visited.get(0).child);
			min=10000;
			point = null;
			r=0;
			for(int h=0;h<visited.size();h++) {



				for(int i=0;i<visited.get(h).child.size();i++) {
					//		System.out.println(visited.get(h).child.get(i).value+" yolo "+point.visited+" "+n.get(point.value).visited);

					if(visited.get(h).child.get(i).distance<min && visited.get(h).child.get(i).visited==false) {
						for(int r1=0;r1<no.size();r1++) {
							System.out.println(no.get(r1).value+"ooo"+visited.get(h).child.get(i).value);
							if(no.get(r1).value==visited.get(h).child.get(i).value)
								check=true;
						}

						//if(visited.get(h).child.get(i).distance+visited.get(h).updatedistance<=visited.get(h).child.get(i).updatedistance) {
						min=visited.get(h).child.get(i).distance;
						point = visited.get(h).child.get(i);

						r=h;
						//}

						check=false;
					}

				}

			}
			//System.out.println(point.value+"iii"+visited.get(r).value);
			visited.get(r).child.remove(point);
			//if(point!=null) {
			System.out.println(point);
			for(int u=0;u<no.size();u++) {
				System.out.println(no.get(u).value+" "+no.get(u).updatedistance+"pppp");
			}
			visited.add(n.get(point.value));
			(point).visited=true;
			n.get(point.value).visited=true;
			System.out.println(n.get(point.value).value+" yoyo "+point.visited+" "+n.get(point.value).visited);
			visited.get(visited.size()-1).updatedistance=point.distance+visited.get(r).updatedistance;

			if(visited.get(r).child.size()==0) {
				System.out.println(visited.remove(r));


			}
			boolean check2=false;
			node o=null;
			for(int l=0;l<no.size();l++) {
				if(no.get(l).value==point.value) {
					o = no.get(l);
					check2=true;
				}
			}
			if(check2==false)
				no.add(n.get(point.value));
			else {
				System.out.println(no.set(no.indexOf(o),n.get(point.value)));
				System.out.println("tru");
			}

		}

		//System.out.println(no.get(no.size()-1).value+" "+no.get(no.size()-1).updatedistance+"pppp");
		//}
	}

}
