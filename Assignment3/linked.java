package Assignment3;

public class linked {
	public linked head;
	
	public int element;
	public linked left;
	public linked right;
	public linked direct;
	
	public void add(int i) {
		linked in = new linked();
		in.element=i;
		if(head.right==null) {
			head.right=in;
			in.left=head;
		}else {
			in.right=head.right;
			head.right.left=in;
			head.right=in;
			in.left=head;		
		}
		in.direct=head;		
	}
	
	public void delete(linked i) {
		i.left.right=i.right;
		i.right.left=i.left;
	}
	
//	public static void main(String[] args) {
//		linked in = new linked();
//		head.element=5;
//		in.add(3);
//		in.add(5);
//		in.add(7);
//		in.add(9);
//		in.delete(head.right.right);
//		in=head.right;
//		while(in!=null) {
//			System.out.println(in.element+" "+in.direct.element);
//			in=in.right;
//		}
//	}
}

