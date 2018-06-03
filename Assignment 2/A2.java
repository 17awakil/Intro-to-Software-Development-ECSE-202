//Name: Andre Wakil
//ID: 260777355
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.format.ResolverStyle;
import java.util.Scanner;

public class A2 {
	@SuppressWarnings("resource")
	public static void main(String args[]) {
		
// Prompt user for a file name.  If no name is entered, terminate
// the program, otherwise attempt to open the file. If file open
// is not successful, prompt again for a new name.  Keep doing this
// until successful open, or a blank line is entered.
		
		System.out.println("Assignment 2 - File Sorting Program");
		Scanner sc = new Scanner(System.in);
		BufferedReader rd = null;
		
		while(rd == null) {
			System.out.print("Enter name of file to list: ");
			String filename = sc.nextLine();
			if (filename.equals("")) {
				System.out.println("Program terminated");
				System.exit(0);									// Exit
			}
// Try to open the specified file
			try {
				rd = new BufferedReader(new FileReader(filename));
			}
			catch (IOException ex) {
				System.out.println("Unable to open file, try again.");
			}
		}

// Read the file a line at a time into a string.  Print as read to the output display.
// Modify the code below as necessary.
		
		System.out.println("\n\nFile in sort order: \n");

		try {
			//Stack declared within try block with no nodes (default constructor)
			//Stack to hold names and push
			Stack reverseNamesStack=new Stack();
			
			//bTree object declared with default constructor (root==null)
			bTree namesTree=new bTree();
			
			//the instance variable s (Stack instance) of the bTree object
			namesTree.s=reverseNamesStack;
			
			while (true) {
				/*line declared as a String that stores each line of 
				 the text file for each iteration of the loop*/
				String line=rd.readLine();
				//the code will break when file stops
				if(line==null)break;
				//nodes added to the bTree and pushed onto the stack
				namesTree.addNode(line);
				
			}
			
			namesTree.inOrderTraversal(namesTree.root);
			
			System.out.println("\n\nFile in reverse sort order: \n");
			
			//another while loop that pops the stack
			while(reverseNamesStack.top!=null) {
				reverseNamesStack.pop();
			}
		}
		catch (IOException ex) {
			System.out.println("I/O Error - program terminated");
			System.exit(-1);
		}
		
		System.out.println("\n\nProgram terminated");
					

	}
}
