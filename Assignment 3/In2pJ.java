
import java.util.StringTokenizer;
import java.util.Scanner;
//Student Name: Andre Wakil
//ID:260777355

/*Implementation of the Shunting Yard Algorithm
 * that parses an algebraic expression in infix form and converts to
 * postfix form*/

public class In2pJ {
	public static void main(String[] args) {
		//Scanner object for user input
		Scanner kbReader=new Scanner(System.in);
		System.out.print("Enter string: ");
		String userInput=kbReader.nextLine();
		//StringTokenizer object instantiated to read operators as delimiters
		StringTokenizer st=new StringTokenizer(userInput,"+-*/()^%", true);

		//Queue object to be outputed as postfix notation of infix input from user
		Queue output=new Queue();
		//Stack to store operators
		Stack operators=new Stack();

		//Shunting Yard Algorithm
        //while loop will run so long as the String st has more tokens
        //in other words, it will run until the String is fully parsed
		while(st.hasMoreTokens()) {
			//nextToken() stored into a String so that the method is called once per iteration of the while loop
			String token=st.nextToken();

			//Two cases when parsing an infix statement

            //Case 1: token is an operator
			if(isOperator(token)) {
			    //while the "token" is (of lesser precedence than the operator on top of the stack) OR (of equal precedence AND the operator on top of the stack is left associative)
                //pop operator off stack and enqueue to output queue
                //this only applies if the stack is not empty (!operators.isEmpty())
				while(!operators.isEmpty() && (getPrecedence(operators.top.val)>getPrecedence(token)|| (getPrecedence(operators.top.val)==getPrecedence(token) && isLeftAssociative(operators.top.val)))) {
					output.enqueue(operators.pop());
					}
					//push token onto operators stack
					operators.push(token);
				
			}
			//Case 2: token is an operand(number)
			else {
			    //enqueue token onto output queue
				output.enqueue(token);
			}
		}

		//Now enqueue remaining operators onto output queue
		while(!operators.isEmpty()) {
			output.enqueue(operators.pop());
		}

		System.out.print("Postfix: ");
        //Print output queue
		while(!output.isEmpty()) {
			System.out.print(output.dequeue()+" ");
		}

	}
	/*checks if a given token String is an operator*/
	public static boolean isOperator(String val) {
		return val.equals("+")||val.equals("-")||val.equals("*")||val.equals("/")||val.equals("(")||val.equals(")")||val.equals("^")||val.equals("%");
				
	}
	/*method that returns the precedence of an operator*/
	public static int getPrecedence(String val) {
		int precedence;
		
		if(val.equals("(")||val.equals(")")) {
			precedence=4;
		}
		else if(val.equals("^")) {
			precedence=3;
		}
		else if(val.equals("*")||val.equals("/")||val.equals("%")) {
			precedence=2;
		}
		else {
			precedence=1;
		}
		return precedence;
	}
	/*check if an operator is left-associative*/
	public static boolean isLeftAssociative(String val) {
		return val.equals("*")||val.equals("/")||val.equals("+")||val.equals("-")||val.equals("%");
	}
}
