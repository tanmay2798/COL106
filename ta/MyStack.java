package ta;
//import java.util.EmptyStackException;

public class MyStack <E> {

	
	E object;
	MyStack<E> next;
	MyStack<E> adder = null;
	
	

	public MyStack() {
		adder=null;		
	}
	public int size() throws EmptyStackException {
		MyStack<E> in = new MyStack<>();
		in=adder;
		if(in==null) {
			return 0;
		}
//		System.out.println(in.object+"d");
		int i=0;
		while(in!=null) {
			in= in.next;
			i++;
		}
		return i;
	}
	public void push(E item) {
		MyStack<E> in = new MyStack<>();
		in.object=item;
		if(adder==null) {
			adder=in;
		}else {
			in.next=adder;
			adder = in;
		}
	}

	public E pop() throws EmptyStackException {
		MyStack <E> in1 = new MyStack<>();
		if(adder==null) {
			throw new EmptyStackException();
		}
		in1=adder.next;
		adder.object=null;
		adder.next=null;
		adder=in1;
		return null;		
	}

	public E peek() throws EmptyStackException{
		if(adder==null) {
			throw new EmptyStackException();
		}
		return adder.object;
	}

	public boolean empty() {		
		if(adder==null) {
		//	System.out.println("true");
			return true;
		}
		else {
		//	System.out.println("false");
			return false;
		}
	}

	public static void main(String[] args) {

		MyStack <Integer> iObj = new MyStack<Integer>();
		try {
			iObj.push(1);
			iObj.push(2);
			iObj.push(3);
			iObj.push(4);
			iObj.push(5);
			iObj.pop();
			iObj.pop();
			iObj.push(6);
			iObj.pop();
			iObj.pop();
			iObj.push(7);
			iObj.push(8);
			iObj.push(9);
			
			System.out.println(iObj.size());

		}catch(EmptyStackException e) {
			System.out.println(e) ;
		}
		iObj.empty();
		System.out.println("list");
		iObj = iObj.adder;
		while(true){
			if(iObj == null){
				break;
			}
			System.out.println(iObj.object);
			iObj = iObj.next;
		}
		
	}
}

class EmptyStackException extends Exception{
	EmptyStackException(){
		super();
	}
}

