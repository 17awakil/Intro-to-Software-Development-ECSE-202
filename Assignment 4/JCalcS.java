import java.util.StringTokenizer;
import java.util.Scanner;
//Student Name: Andre Wakil
//ID:260777355

/*Implementation of the Shunting Yard Algorithm
 * that parses an algebraic expression in infix form and converts to
 * postfix form, which is then ready to be evaluated*/
public class JCalcS {
    public static void main(String[] args) {
        //Scanner object for user input
        System.out.println("Infix to Postfix Interpreter");
        Scanner kbReader=new Scanner(System.in);

        //while loop that prompts user for an expression
            while(true){
                System.out.print("Enter expression (blank line to exit):");
                String userInput=kbReader.nextLine();
                evaluatePostfix(infixToPostfix(userInput));
                System.out.println();
                //will stop the program if empty string is inputted by user
                if(userInput.equals("")){
                    break;
                }
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
    /*parses an infix expression (String) and returns a postfix queue*/
    public static Queue infixToPostfix(String str){
        //StringTokenizer object instantiated to read operators as delimiters
        StringTokenizer st=new StringTokenizer(str,"+-*/()^%", true);
        String result="";
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
        return output;
    }
    /*evaluation of a postfix expresion to a single evaluation term*/
    public static void evaluatePostfix(Queue queue){
        //counter for evaluation number i.e. Eval1, Eval2, ...
        int counter=0;
        Stack evalStack=new Stack();

        //loop through postfix queue while it is not empty
        while(!queue.isEmpty()){
            /*String at the front of the queue stored in a variable called token so that the dequeue() method is called
            once per iteration*/
            String token=queue.dequeue();

            //2 cases when evaluating a postfix expression

            //Case 1:if token is an operator, then pop two operands and evaluate them with the operator
            if(isOperator(token)){
                String operand2= evalStack.pop();
                String operand1=evalStack.pop();
                System.out.println("Eval"+(++counter)+":"+"<"+operand1+token+operand2+">");
                evalStack.push("Eval"+counter);
            }
            //Case 2: otherwise, push the token onto the evalStack
            else{
                evalStack.push(token);
            }
        }


    }
}

