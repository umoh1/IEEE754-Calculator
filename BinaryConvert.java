import java.lang.Math;
public class BinaryConvert

{
    //fields
    private String decimal;
    private String hexadecimal;
    private String binary;
    private String octal;
    private int mantissaBit; //stores the mantissa bit amount
    private int exponent; //stores the raw decimal exponent
    private String userDecimal; //from Floating point calculator, stores raw exponent decimal value

    public void setUserDecimal(String user)
    {
        this.userDecimal = user;
    }
    public String getUserDecimal()
    {
        return userDecimal;
    }
    public void setExponent(int exponent)
    {
        this.exponent = exponent;
    }
    public int getExponent()
    {
        return this.exponent;
    }
    public void setMantissaBit(int exponentBit)
    {
        this.mantissaBit = exponentBit;
    }
    public int getMantissaBit()
    {
        return mantissaBit;
    }

    /*
        Method that turns binary number into decimal number
        @param binary: accepts binary number as input
     */
    public void BinarytoDecimal(String binary)
    {
        //hold length of binary string
        int length = binary.length();

        //array for each digit of binary number
        char[] s = new char[length];

        //loop through and put each bit into array
        for(int i = 0; i<length;i++)
        {
            s[i] = binary.charAt(i); //stores each bit in array
        }

        //new array to store reversed string
        char[] s1 = new char[length];

        int down = length; //used to decrement original array

        //loop through and reverse string
        for(int i = 0; i<length;i++)
        {
            s1[i]=s[down-1]; //reverse string
            down--; //decrement index
        }

        //new stringbuilder to manipulate string
        StringBuilder sb = new StringBuilder();

        //loop through and append the reversed bits to stringbuilder object
        for(int i = 0; i<down; i++)
        {
            sb.append(s1[i]); //append to stringbuilder
        }

        //incrementer to hold decimal value of binary number
        int total = 0;

        //loop through and calculate decimal number of binary exponent
        for(int i = 0; i<down; i++)
        {
            if(sb.charAt(i)=='1')
            {
                total = total + (int)Math.pow(2,i); //calculate value of set bits
            }

        }

        //stores the decimal value of the exponent in the decimal field
        decimal = decimal.valueOf(total); 

    }

    /*
        Method that turns a number from scientific notation to a floating point binary number
        @param Decimal: accepts a decimal number as input
     */
    public void SNDecimaltoFPBinary(String Decimal)
    {
        //String[] parts = Decimal.split([]);
        Decimal = Decimal.trim();
        //put scientific number in string builder to delete decimal
        StringBuilder s1 = new StringBuilder(Decimal);

        //if it is negative, delete negative sign
        if(Decimal.charAt(0)=='-')
        {
            s1.deleteCharAt(0); //delete negative sign
        }

        //store unsigned string
        Decimal = s1.toString();

        //split the numbers from scientific notation
        String[] snsplit = Decimal.split("[xX^]");

        //store split numbers
        double num = Double.parseDouble(snsplit[0]); //turn the decimal number into a double
        double base = Double.parseDouble(snsplit[1]); //turn the base into a double
        double exponent = Double.parseDouble(snsplit[2]); //turn the exponent into a double

        //convert to base 2 exponent
        double logBase2 = Math.log10(num*Math.pow(base,exponent))/Math.log10(2);

        //extract the integer from the fractional part of logBase2
        String extract1 = Double.toString(logBase2);

        //use to hold integer and fractional part
        String[] extract2 = extract1.split("[.]");

        //turn the fractional part back into a fraction (split turns it into a integer)
        StringBuilder sb = new StringBuilder(); //new string builder for manipulation of extract2[1] (fraction)
        sb.append(".");
        sb.append(extract2[1]);

        String dpart = sb.toString(); //turn fractional part to string
        double dpartconvert = Double.parseDouble(dpart); //turn fractional part into a double
        int ipart = Integer.parseInt(extract2[0]); //store the integer part

       // System.out.println("For testing: SN BASE: 2^" +dpartconvert +" x 2^"+ipart); //print for testing

        Decimal = Double.toString(Math.pow(2,dpartconvert)); //convert the decimal version of string to scientific notation

        this.exponent = ipart; //store the exponent

        //FROM HERE, THE CODE FROM DECIMAL TO FPBINARY SHOULD WORK
        DecimaltoFPBinary(Decimal);
    }
    /*
        Method that turns a regular decimal number into floating point binary number
        @param Decimal: accepts a decimal number as input
     */
    public void DecimaltoFPBinary(String Decimal)
    {
        //make new String builder for string manipulation
        StringBuilder s1 = new StringBuilder(Decimal.trim());

        //if it is negative, delete negative sign
        if(Decimal.charAt(0)=='-')
        {
            s1.deleteCharAt(0);

        }

        //store unsigned string
        Decimal = s1.toString();


        String ints = ""; //used to hold the substring of the integer part
        String hey = ""; //used to hold the substring of the fractional part

        //loop through the string to check for FRACTIONS
        for (int i = 0; i < Decimal.length(); i++) {
            if (Decimal.charAt(i) == '/') 
            {
                String dec1 = Decimal.substring(0, i); //extract the first number
                String dec2 = Decimal.substring(i+1); //extract the second number
                
                double iparts = Double.parseDouble(dec1); //turn the first number to a double
                double iparts1 = Double.parseDouble(dec2); //turn the second number to a double
                
                //store decimal version of fraction in here
                Decimal = Double.toString(iparts/iparts1); 
                System.out.println(Decimal);
            }
        }
        
        //loop through string to check for REGULAR DECIMALS
        for (int i = 0; i < Decimal.length(); i++) {
            
            if (Decimal.charAt(i) == '.') //find the decimal point in the string
            {
                ints = Decimal.substring(0, i); //extract the integer part
                hey = Decimal.substring(i); //extract the fractional part
            }
            
        }
        int ipart = 0;
        if(ints.length()!=0) {
            ipart = Integer.parseInt(ints); //turn integer part of substring to int
        }

        String e = Integer.toBinaryString(ipart); //turn integer part of substring to binary

        double fpart = Double.parseDouble(hey); //turn fractional part into a double

        String ints1 = "", hey1 = ""; //hold empty strings for substrings

        StringBuilder s = new StringBuilder(); //new string builder

        double hold = Double.parseDouble(hey) * 2; //multiply by 2

        int h = Double.toString(hold).indexOf("."); //find index of the decimal point

        int i =0; //accumulator

        //int user = Integer.parseInt(userDecimal);

            while((!hey1.equals(".0"))&&i<mantissaBit+30)
            {
                ints1 = Double.toString(hold).substring(0, h); //extract the integer part

                hey1 = Double.toString(hold).substring(h); //extract the fractional part

                s.append(ints1); //append to string

                hold = Double.parseDouble(hey1)*2; //multiply fractional part again by 2

                i++; //increment
            }


        //get fractional part of number

        //store binary number in field
        binary = e + '.'+s.toString();
     //   System.out.println("FOR TESTING FROM BINARY CONVERT: "+binary + "\nMantissa bit: "+mantissaBit);
    }

    /*
        Method that turns a binary number into a hexadecimal
        @param binary: accepts a binary number as input
     */
    public void BinaryToHex(String binary)
    {
        //turn the binary number into stringbuilder for processing
        StringBuilder s = new StringBuilder(binary);

        int rem = s.length()%4; //used to see if length of word is divisible by 4

        int k = 0; //used as the start incrementer for substring
        int j = 4; //used as the end incrementer for substring

        int num = 0; //used to hold the total number of nibbles

        //make groups of 4 for conversion
        if(rem!=0)
        {
            //add leading zeros to make it divisble by 4
            for(int i = 0; i<rem; i++)
            {
                s.insert(0,0);   //add leading zeros
            }
            num = s.length()/4; //store length in num, decides how many groups of 4
        }
        else {
            num = s.length() / 4; //used to decide how many groups of 4 there are

        }
        
        //string array to hold nibbles
        String[] sa = new String[num];

        //put the nibbles into a string array
        for(int i = 0; i<num;i++)
        {
            sa[i]=s.substring(k,j); //extract 4 digit substring

            k=k+4; //increment start by 4

            j=j+4; //increment end by 4
        }

        StringBuilder hex = new StringBuilder(); //make string builder to append hex digits

        //assign hex digits and append them
        for(int i = 0; i<sa.length; i++)
        {
            if(sa[i].equals("0000")) //for hex #0
            {
                hex.append(0);
            }
            else if(sa[i].equals("0001")) //for hex #1
            {
                hex.append(1);
            }
            else if(sa[i].equals("0010")) //for hex #2
            {
                hex.append(2);
            }
            else if(sa[i].equals("0011")) //for hex #3
            {
                hex.append(3);
            }
            else if(sa[i].equals("0100")) //for hex #4
            {
                hex.append(4);
            }
            else if(sa[i].equals("0101")) //for hex #5
            {
                hex.append(5);
            }
            else if(sa[i].equals("0110")) //for hex #6
            {
                hex.append(6);
            }
            else if(sa[i].equals("0111")) //for hex #7
            {
                hex.append(7);
            }
            else if(sa[i].equals("1000")) //for hex #8
            {
                hex.append(8);
            }
            else if(sa[i].equals("1001")) //for hex #9
            {
                hex.append(9);
            }
            else if(sa[i].equals("1010")) //for hex #10
            {
                hex.append('A');
            }
            else if(sa[i].equals("1011")) //for hex #11
            {
                hex.append('B');
            }
            else if(sa[i].equals("1100")) //for hex #12
            {
                hex.append('C');
            }
            else if(sa[i].equals("1101")) //for hex #13
            {
                hex.append('D');
            }
            else if(sa[i].equals("1110")) //for hex #14
            {
                hex.append('E');
            }
            else if(sa[i].equals("1111")) //for hex #15
            {
                hex.append('F');
            }
            else //anything else, which there shouldn't be
            {
                hex.append("ERROR");
            }
        }
        hexadecimal = hex.toString(); //store hex number

    }

    /*
        Method turns Decimal into a hexadecimal
        @param Decimal : accepts decimal number as input
     */
    public void DecimalToHexadecimal(String Decimal)
    {
        hexadecimal = Integer.toHexString(Integer.parseInt(Decimal));
    }
    
    /*
        Method turns Decimal into a octal
        @param Decimal : accepts decimal number as input
     */
    public void DecimalToOctal(String Decimal)
    {
        octal = Integer.toOctalString(Integer.parseInt(Decimal));
    }

    /*
        Method turns Decimal into a binary
        @param Decimal : accepts decimal number as input
     */
    public void DecimalToIntBinary(String Decimal)
    {
        binary = Integer.toBinaryString(Integer.parseInt(Decimal));
    }
    
    //setters
    public void setHexadecimal(String hexadecimal) {this.hexadecimal = hexadecimal;}
    public void setDecimal(String decimal){this.decimal = decimal;}
    public void setBinary(String binary){this.binary = binary;}
    public void setOctal(String octal){this.octal = octal;}

    //getters
    public String getHexadecimal(){return hexadecimal;}
    public String getDecimal(){return decimal;}
    public String getBinary(){return binary;}
    public String getOctal(){return octal;}
}
