package final2;

import java.util.Vector;

public class check {

	public check() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		Vector <String> v = new Vector<>();
		v.add("t");
		v.add("IU");
		for(int i=0;i<v.size();i++) {
			System.out.println(v.get(i));
		}
		v.removeElement("IU");
		for(int i=0;i<v.size();i++) {
			System.out.println(v.get(i));
		}
	}

}
