/*Student Name: Andre Wakil
Student ID: 260777355*/
import java.util.*;//Java library imports for user input (Scanner object)

public class dec2base {

	public static void main(String[] args) {
		
		//Scanner object instantiation for user input
		Scanner userInput=new Scanner(System.in);
		System.out.println("Java base conversion demo:");
				
		//while loop that asks for a number and base to be computed until a negative number is inputed
		while(true) {
			
		System.out.print("Enter a number to be converted: ");
		//number to converted store in a variable of type int by method of the Scanner class (int nextInt())
		int num=userInput.nextInt();
		
		
		//if a negative number is inputed, the program ends and the while loop is terminated
		if(num<0) {
			System.out.println("Program ended");
			break;
		}
		//otherwise, program asks for base to convert to
		else {
			System.out.print("Enter a base to convert to: ");
			int base=userInput.nextInt();
			//Print the line that reverses the string returned by dec2B (which is in backwards order)
			System.out.println(num+" is represented in Base-"+base+" as "+reverseString(dec2B(num,base)));
		}
		}
		
	}
	//private and static method of a class that takes in two arguments, 
	//a decimal number and  a base to convert to, and returns a String of the converted string
	private static String dec2B(int number, int base) {
		//String to represent numbers in bases higher than 10 (b>10)
		String higherBases="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String convertedString="";//String to add
		
		//loop that creates string of remainders in backwards order
		while(number!=0) {
			//if-else statements for when remainder is bigger than 9, 
			//then it will assign the corresponding conventional letter (A=10, B=11, C=12...)
			if(number%base>9) {

				convertedString+=higherBases.charAt(number%base);
			}
			else {
				convertedString+=(number%base);
			}
			number=number/base;
			
		}
		
		return convertedString;
	}
	//method to reverse a string by traversing it from last index(length()-1) to first index (0)
	//its purpose is to revere the backwards order string from String dec2B() 
	private static String reverseString(String result) {
		String revString="";
		
		for(int i=result.length()-1;i>=0;i--) {
			revString+=result.charAt(i);
		}
		
		return revString;
	}
}
