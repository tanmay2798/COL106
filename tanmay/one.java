package tanmay;


public class one <E> {

	private E object;
	private one<E> next;
	private one<E> adder = null;
	//private MyStack<E> base = null;

	public one() {
		adder=null;
		//base=null;		
	}

	//	private void element(E val) {
	//		this.object = val;
	//		
	//	}
	//	private void point(MyStack<E> address) {
	//		this.next = address;	
	//	}

	//	private MyStack<E> getNextAddress() {
	//        return next;
	//    }

	//	private E getValue() {
	//	    return object;
	//	}

	public void push(E item) {
		one<E> in = new one<>();
		in.object=item;
		if(adder==null) {
			//base=in;
			adder=in;

		}else {
			in.next=adder;
			adder = in;
		}
		System.out.println(item);
	}

	public E pop() throws EmptyStackException {

		one <E> in1 = new one<>();
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
			System.out.println("true");
			return true;
		}
		else {
			System.out.println("false");
			return false;
		}
	}

	public static void main(String[] args) {

		one <Integer> iObj = new one<Integer>();
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
			iObj.pop();
			iObj.pop();
			iObj.pop();
			iObj.pop();
			iObj.pop();
			iObj.pop();


			iObj.peek();

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



