/*Last-In, First-Out (LIFO) data structure*/
public class Stack {
	//top node
	public listNode top;
	//default constructor
	public Stack() {
	    top=null;
	}
	//constructor
	public Stack(String val) {
	    top=new listNode(val);
	}

	/*pushes listNode objects onto stack*/
	public void push(String val) {
	    //if-else block checks if Stack is empty before performing push accordingly
		if(isEmpty()) {
			top=new listNode(val);
		}
		else {
			listNode temp=top;
			top=new listNode(val);
			top.next=temp;
			
		}
	}
	/*pops the top Node*/
	public String pop() {

        //if-else block checks if Stack is empty before performing pop accordingly
        if(isEmpty()) {
			return "";
		}
		else {
			String val=top.val;
			top=top.next;
			return val;
		}
	}
	/*checks if Stack is empty*/
	public boolean isEmpty() {
		return top==null;
	}
}
