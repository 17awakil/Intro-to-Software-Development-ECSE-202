/*Queue: First-In, First-Out (FIFO) class specified for storing Strings*/
public class Queue {
	//instance variables
	public listNode front;
	public listNode back;

	//default constructor
	public Queue() {
		front=null;
		back=null;
	}
	//constructor that points a reference variables front and back to a new listNode object with given value
	public Queue(String val) {
		front=new listNode(val);
		back=front;
	}
	/*enqueues a listNode object to the back of the queue*/
	public void enqueue(String val) {

		if(isEmpty()) {
			back=new listNode(val);
			front=back;
		}
		else {
			listNode temp=back;
			back=new listNode(val);
			temp.next=back;
		}
	}
	/*dequeues a listNode object from the front of the queue*/
	public String dequeue() {
		if(isEmpty()) {
			return "";
		}
		else {
			String s=front.val;
			front=front.next;
			return s;
		}
		
	}
	/*checks if Queue object is empty*/
	public boolean isEmpty() {
		return front==null;
	}
	
}
