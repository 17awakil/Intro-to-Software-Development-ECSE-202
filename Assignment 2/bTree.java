public class bTree {
	//instance variables
	public bNode root;
	public Stack s; //Stack instance to be referred to in inOrderTraversal()
	
	//constructors
	
	//default constructor (no parameter)
	//sets root to null
	public bTree() {
		root=null;
	}
	
	//constructor with String parameter
	public bTree(String name) {
		root=new bNode(name);
	}
	//addNode calls onto a private method
	public void addNode(String name) {
		if(root == null)
			root=new bNode(name);
		else
			addNode(root,name);
	}
	//private method traverses the binary tree recursively and adds a bNode object
	//to the correct subtree and leaf (right or left) base on alphabetical order
	private void addNode(bNode current, String name) {
		//using cases insensitive version compareTo() to reason through recursion
		
		if(name.compareToIgnoreCase(current.name)<0) {
			if(current.left==null) {
				current.left=new bNode(name);
			}
			else {
			addNode(current.left, name);
			}
		}
		else if(name.compareToIgnoreCase(current.name)>0){
			if(current.right==null) {
				current.right=new bNode(name);
			}
			else {
				addNode(current.right,name);
			}
		}
		
	}
	//Traverse the binary tree recursively first to the left node, then the root, then the right
	//node (left, root, right)
	public void inOrderTraversal(bNode n) {
		//if the first node visited is null, the method is exited
		if(n==null) {return;}
		
		if(n.left!=null) {
			inOrderTraversal(n.left);
		}
		//prints the name
		System.out.println(n.name); 
		//pushing the names in order to the stack
		s.push(n.name);
		//right node traversal
		if(n.right!=null) {
			inOrderTraversal(n.right);
		}

	}
}
