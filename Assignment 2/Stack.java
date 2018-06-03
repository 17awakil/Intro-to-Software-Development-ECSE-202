public class Stack{
	//the stack works by a last in - first out basis (LIFO)
	//instance variable to keep track of top listNode object
	listNode top;
	
	//default constructor with no parameters/arguments required
	public Stack() {
		top=null;
	}
	//another constructor that sets the first node/ top node to a node with the name inputted
	public Stack(String name) {
		top=new listNode(name);
	}
	//adds a node to the stack
	public void push(String name) {
		if(top==null) {
			top=new listNode(name);
		}
		else {
			//top reference stored in temporary node
			listNode temp=top;
			//top refers to a new object with the new name added
			top=new listNode(name);
			//the old 'top' is now the new top's next node
			top.next=temp;
			
		}
	}
	public void pop() {
		//if the top node is null, exit method
		if(top==null) {
			return;
		}
		else {
			//otherwise print name, and make top refer to the next node
			System.out.println(top.name);
			top=top.next;
		}

		}
		
	}
