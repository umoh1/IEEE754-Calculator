import java.math.BigDecimal;
import java.util.*;

public class FloatingPointCalculator {

    //fields
    private int userExponent;  //holds user inputted exponent size (in bits)
    private String binaryExponent; //holds exponent in binary
    private int decimalExponent; //holds exponent in decimal form
    private int userMantissa; //holds user inputted mantissa size (in bits)
    private String significand; //holds binary num as string
    private String userDecimal; //holds user entered decimal number to convert
    private int sign; //holds the sign (positive: 0 or negative: 1)
    private StringBuilder print = new StringBuilder("\nRESULTS"); //holds string for printing info for each step
    private int check1;
    private int BCexponent;

    //GETTERS
    public int getuserExponent() {return userExponent;}
    public String getBinaryExp() {return binaryExponent;}
    public int getDecimalExponent() {return decimalExponent;}
    public int getUserMantissa(){return userMantissa;}
    public String getSignificand(){return significand;}
    public int getSign(){return sign;}
    public String getDecimal(){return userDecimal;}
    public String getPrint(){return print.toString();}

    //SETTERS
    public void setUserExponent(int userExponent){this.userExponent = userExponent;}
    public void setBinaryExp(String binaryExp){this.binaryExponent = binaryExp;}
    public void setEXP(int EXP){this.decimalExponent = EXP;}
    public void setMantissa(int mantissa){this.userMantissa = mantissa;}
    public void setSignificand(String significand) {this.significand = significand;}
    public void setDecimal(String decimal){this.userDecimal = decimal;}
    public void setSign(int sign) {this.sign = sign;}

    /*
        Constructor: sets the 3 most needed inputs for floating point calculations
        @param decimal: accepts a decimal number as input
        @param userExponent: accepts the exponent length in bits
        @param userMantisssa: accepts the mantissa length in bits
     */
    public FloatingPointCalculator(String decimal, int userExponent, int userMantissa)
    {
        print.append("\n----------------------------------------");
        this.userDecimal = decimal;
        //set exponent size with user input
        this.userExponent = userExponent;

        //set mantissa size with user input
        this.userMantissa = userMantissa;
    }

    /*
        No Arg constructor: sets all String fields to an empty string and
        all int fields to 0
     */
    public FloatingPointCalculator()
    {
        print.append("\n----------------------------------------");
        userExponent = 0;
        binaryExponent = "";
        decimalExponent = 0;
        userMantissa = 0;
        significand = "";
        userDecimal = "";
        sign =0;

    }

     /*
        Method that returns the total amount of bits used
     */
    public void totalBitsUsed() {
        //calculates the userExponent + the mantissa + the sign bit(1) for total bits
        int total = userExponent + userMantissa + 1;

        //appends the total bits to the output
        print.append("\nTotal number of bits used: "+ total);
    }

     /*
        Method that determines the sign of the number
     */
    public void sign()
    {
        //if the first character is a negative sign, return 1 to indicate a negative number
        if (userDecimal.charAt(0) == '-') {
            sign= 1; //negative
            print.append("\nSign: 1 (Negative)");

        } else {
            sign = 0; //positive
            print.append("\nSign: 0 (Positive)");
        }
    }

    /*
        Method that returns the bias of the userExponent
    */
    public int getBias()
    {
        return (int) (Math.pow(2.0, (userExponent - 1)) - 1); //returns formula: (2^(n-1))-1, where n is the userExponent
    }

    /*
        Method that returns the Decimal as a double
     */
    public double DecimalToDouble() {
        return Double.parseDouble(userDecimal.trim()); //turn the String into a Double and return it
    }

    /*
        Method that turns decimal number to a binary decimal
        @param check: accepts a 0 for a normal decimal, and a 1 for a scientific notated decimal
     */
    public void toBinary(int check)
    {
        this.check1 = check;
        //make new instance of binary convert
        BinaryConvert convert = new BinaryConvert();
        convert.setMantissaBit(userMantissa);

        //convert decimal to binary
        if(check==0) //if check is 0, use regular decimal
        {
            convert.DecimaltoFPBinary(userDecimal.trim());
        }
        else //if check is 1, use scientific notation
        {
            convert.SNDecimaltoFPBinary(userDecimal.trim());
            BCexponent = convert.getExponent();
        }
        //store the exponent

        print.append("\nBCExponent: "+ BCexponent);
        //store binary number in the significand
            significand = convert.getBinary();
        }

    /*
        Normalize the binary significand:
        Takes the binary significand and normalizes it by removing the
        decimal point and the first one that is found.
        also calculates the amount of decimal place jumps to use as the
        userExponent amount
     */
        public String Normalize()
        {
            if(significand.charAt(0)=='0') {
                int point = 1; //get index of the decimal point

                int one = significand.indexOf('1'); //get index of the first 1

                if(this.check1==1)
                {
                    decimalExponent=BCexponent;
                }
                else {
                    decimalExponent = point - (one); //get the userExponent number

                }

                //print out raw userExponent
                print.append("\nRaw Exponent Decimal Value: " + decimalExponent);

                //store binary number to a stringbuilder for processing
                StringBuilder s = new StringBuilder(significand);

                s.delete(0,one+1); //delete the leading zeroes

                //s.deleteCharAt(point-1); //deletes decimal point

                significand =s.toString(); //store number in significand field

                //print.append("\nFOR TESTING FROM NORMALIZE: "+significand);
            }
            else
                {
                    int point = significand.indexOf('.'); //get index of the decimal point

                    int one = significand.indexOf('1'); //get index of the first 1

                    if(this.check1==1)
                    {
                        decimalExponent = BCexponent;
                    }
                    else {
                        decimalExponent = point - (one + 1); //get the userExponent number

                    }

                    //print out raw userExponent
                    print.append("\nRaw Exponent Decimal Value: " + decimalExponent);

                    //store binary number to a stringbuilder for processing
                    StringBuilder s = new StringBuilder(significand);

                    s.deleteCharAt(one); //deletes the first one

                    s.deleteCharAt(point - 1); //deletes decimal point

                    significand =s.toString();
                  //  print.append("\nFOR TESTING FROM NORMALIZE: "+significand);
            }
            BinaryConvert convert = new BinaryConvert();
            convert.setUserDecimal(userDecimal);

            if(significand.length()>userMantissa)
            {
                return significand.substring(0, userMantissa); //return as string
            }
            else {
                return significand;
            }
        }

        /*
            Calculates the userExponent
         */
        public void calculateExponent()
        {
            int biased = decimalExponent + getBias(); //bias the Exponent
            
            print.append("\nBias: " + getBias()); //add Biasto print statement

            //turn userExponent into binary
            String s = Integer.toBinaryString(biased);


            if(s.length()<userExponent) //if the length of exponent binary string is less than the alotted amount
            {
                //turn to stringbuilder for processing
                StringBuilder sb = new StringBuilder();

                //add leading zeroes
                for(int i =0; i<userExponent-s.length();i++)
                {
                    sb.append(0); //add leading zeroes
                }
                sb.append(s); //add number
                s = sb.toString(); //set new string to s
            }


            //add biased exponent to print statement
            print.append("\nBiased exponent: "+biased);
            
            binaryExponent = s; //return the binary string
          //  print.append("\nFOR TESTING IN CALCULATE EXPONENT: "+ s);
        }

        /*
            Method that adds trailing zeros to the significand
         */
        public String trailingZeroes()
        {
            //turn normalized number into string builder for editing
            StringBuilder s = new StringBuilder(Normalize());
           // print.append("\nFOR TESTING FROM TRAILING ZEROES: "+s);
            //get the number of bits needed to complete significand
            int len = userMantissa - s.length();

            //append 0s to the end of the string
            for(int i = 0; i<len; i++)
            {
                s.append(0); //add 0s to the end of the string
            }

            //put the edited string into the binaryNum field
            significand = s.toString();

            return s.toString(); //return string
        }

        /*
            Method: puts together sign, userExponent and mantissa, and turns it into hexadecimal format
         */
        public void toHex()
        {
            //make new stringbuilder to hold full floating point word
            StringBuilder s = new StringBuilder();

            //append the binary sign, userExponent and significand together
            s.append(sign); //sign
            s.append(binaryExponent); //exponent
            s.append(significand);

            //make new instance of binary convert class
            BinaryConvert convert = new BinaryConvert();

            //convert binary floating point number to hex
            convert.BinaryToHex(s.toString());
          //  print.append("\nFOR TESTING IN TOHEX(): "+s.toString());
            //add the hexadecimal to the print statement
            print.append("\nHexadecimal: " +convert.getHexadecimal());
            
        }

        /*
              toString() method, prints out the finished floating point number in binary
         */
        public String toString()
        {
            //make new stringbuilder to hold full floating point word
            StringBuilder s = new StringBuilder();

            //append the binary sign, userExponent and significand together
            s.append(sign);
            s.append(" ");
            s.append(binaryExponent);
            s.append(" ");
            s.append(significand);

            
            totalBitsUsed(); //call to append total bits used

            //add the full number to the print statement
            print.append("\nThe floating point number in binary is: " + s.toString());
            
            toHex(); //call to append hex 
            
            
            //return the full print statements
            return print.toString();

        }

}


