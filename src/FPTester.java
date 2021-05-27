import java.util.*;
public class FPTester {
    public static void main(String[] args) {

        try {
            Scanner keyboard; //used for user input
            int userinput = 0 ; //used to stop the do while loop
            char choice; //holds the users choice
            int exponent ; //holds the exponent amount
            String user; //holds the decimal for conversion
            int significand;
           do {
               keyboard = new Scanner(System.in);
               //get user input
               System.out.println("\nEnter a number in decimal or scientific notation.\n\nDecimal Example: 29.75\n\nScientific Notation Example: 6.124x10^4");
               user = keyboard.nextLine();
               user = user.trim(); //trim user input

               int check = 0; //used to check if user inputted decimal is in scientific notation

               //used to check if user inputted a number in scientific notation
               for(int i = 0; i<user.length(); i++)
               {
                   if(user.charAt(i)=='x'||user.charAt(i)=='X'||user.charAt(i)=='^'||user.charAt(i)=='*')
                   {
                       check = 1; //if check = 1, then use scientific notation
                       break;
                   }
               }

               System.out.println("Enter the total # of bits for the Exponent: ");
               exponent = keyboard.nextInt();

               System.out.println("Enter the total # of bits for the significand: ");
               significand = keyboard.nextInt();

               //make new instance of floating point calculator, pass user input to constructor
               FloatingPointCalculator cl = new FloatingPointCalculator(user, exponent, significand); //make new instance of fp calc

               //start calculations
               cl.sign();
               cl.toBinary(check);
               cl.trailingZeroes();
               cl.calculateExponent();

               //print out results
               System.out.println(cl.toString());

               //ask user if they want to go again
               System.out.println("\nDo you want to go again? Enter y for yes and -1 for no! ");
               choice = keyboard.next().charAt(0);
           } while(choice!='-');
        }
        catch(Exception e)
        {
            //print out an error message if something goes wrong
            System.out.println("An error has occurred. ");
            e.printStackTrace();
        }
        finally
        {
            System.exit(0); //exit the system
        }
    }
}
