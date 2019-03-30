package tanmay;


public class MyStack <E> {

	
	private E object;
	private MyStack<E> next;
	private MyStack<E> adder = null;
	

	public MyStack() {
		adder=null;		
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
		//System.out.println(item);
	}

	public E pop() {

		MyStack <E> in1 = new MyStack<>();
		if(adder==null) {
			try {
				throw new EmptyStackException();
			} catch (EmptyStackException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
				return null;
			}
		}
		E i = adder.object;
		in1=adder.next;
		adder.object=null;
		adder.next=null;
		adder=in1;
		return i;		
	}

	public E peek() {
		if(adder==null) {
			try {
				throw new EmptyStackException();
			} catch (EmptyStackException e) {
				System.out.println(e);
				return null;
			}
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
			iObj.pop();
			iObj.pop();
			iObj.pop();
			iObj.pop();
			iObj.pop();
			iObj.pop();
			iObj.pop();
			System.out.println(iObj.peek());

		
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

