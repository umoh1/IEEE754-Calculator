import java.util.*;
public class FPHexTester {
    public static void main(String[] args) {
        try {
            //new instance of scanner for user input
            Scanner keyboard;
            String userHex;
            int exponent;
            int mantissa;
            char choice;

            //print out questions to user
            do {
                //make new instance of scanner
                keyboard = new Scanner(System.in);

                //ask for hexadecimal number
                System.out.println("Enter the number in Hexadecimal: ");
                userHex = keyboard.nextLine(); //store in userHex for processing
                userHex = userHex.trim(); //trim the userHex

                //ask for the exponent bit amount and store in exponent
                System.out.println("Enter the bit amount for the Exponent: ");
                exponent = keyboard.nextInt();

                //ask for the significand bit amount and store in mantissa
                System.out.println("Enter the bit amount for the Signficand (or mantissa)");
                mantissa = keyboard.nextInt();


                //make new instance of hex calculator class
                FPHexCalc fp = new FPHexCalc(userHex, mantissa, exponent);

                //call methods to calculate
                fp.toBinary(); //convert hex to binary
                fp.sectionOff(); //section off the sign, exponent, and significand bits
                fp.getBias(); //calculate the bias
                fp.toDecimal(); //turn the binary number to decimal
                fp.calcExp(); //calculate the decimal amount of the exponent
                fp.movePoint(); //move the decimal point
                fp.finish(); //format all fields for printing

                System.out.println(fp.toString()); //print out results

                //ask user if they want to go again
                System.out.println("\nDo you want to go again? Enter y for yes and -1 for no! ");
                choice = keyboard.next().charAt(0);
            }
            while(choice!='-');
        }
        catch(Exception e)
        {
            //print out an error message if something goes wrong
            System.out.println("An error has occured. ");
            e.printStackTrace();
        }
        finally
        {
            System.exit(0);
        }
}

}
