#include <stdio.h>
#include <stdlib.h>
#include <math.h>
/*Student Name: Andre Wakil
ID: 260777355*/

void dec2base(int input, int base, char* str){
    //String to represent numbers in bases higher than 10 (b>10)
    char baseConversion[]="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    //remainder and index variables declared
    int rem,i=0;
    //loop that creates string of remainders in backwards order
    while(input!=0){
        rem=input%base;
        input/=base;
        str[i]=baseConversion[rem];
        i++;
    }

}
void revStr(char* str, int length){
    //index variable declared outside for-loop because of C syntax
    int i;
    //char pointer is initialized to point to a character array (string) of length given as an argument
    char *result=(char*)malloc(sizeof(char)*(length));

    //loop 1: reverse str and store it in result
    for(i=0;i<length;i++){
        result[i]=str[length-i-1];

    }
    //loop2: copy newly-reversed char array
    for(i=0;i<length;i++){
        str[i]=result[i];
    }
    //set the character after the last index to the terminating null character so that when the string terminates
    //properly when printed in main function
    *(str+length)='\0';
}
int main(int argc, char* argv[]){
    //declaration of two integers, number and base where the sscanf function will copy the arguments from the command line
    int number, base;

    //if only one argument is passed, base 2 conversion is assumed
    if(argc==2) {
        base = 2;
        sscanf(argv[1], "%d", &number);
        //No negative values allowed
        if(number<0){
            printf("Negative number inputed.\nProgram ended");
            return 0;
        }
    }
        //if two arguments are passed (number and base), then these values are scanned by sscanf and stored in the variables number and base respectively
    else if(argc==3){
        sscanf(argv[1],"%d",&number);
        sscanf(argv[2],"%d",&base);

        if(number<0){
            printf("Negative number inputted.\nProgram ended");
            return 0;
        }
        //conversion must be done from decimal to a base in range of 2-36
        if(base>36||base<2){
            printf("Base is out of bounds. Conversion can only be done from base 2 to base 36.\nProgram ended");
            return 0;
        }
    }
        //otherwise, exit program
    else{
        printf("Wrong number of arguments\nProgram ended.");
        return 0;
    }
    //the length of the converted string is the integer rounded of (log-base(number)+1)
    //I used change of bases equation
    int length=(int)(log(number)/log(base))+1;
    char result[length];

    //dec2base called onto result (pass-by-reference)
    dec2base(number,base,result);
    //string is reversed
    revStr(result,length);
    //final result printed
    printf("The Base-%d form of %d is: %s \n",base,number, result);

}