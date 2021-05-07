public class FPHexCalc
{
    //fields
    private String userHex; //stores user inputted hex
    private char sign; //stores sign of number
    private String decimal; //stores user inputted decimal
    private int exponent; //stores biased exponent, EX: 136
    private int mantissa; //stores user inputted mantissa
    private String binaryExp; //stores exponent of number in binary
    private String binary; //stores full binary number
    private int EXP; //stores exponent after conversion to decimal
    private String MAN; //stores binary significand
    private int unbias; //stores unbiased exponent
    private int exps; //stores exponent bit amountEX: 8
    private StringBuilder print = new StringBuilder(); //used for the toString method

    //SETTERS
    public void setUserHex(String userHex) {this.userHex = userHex;}
    public void setSign(char sign){this.sign=sign;}
    public void setDecimal(String decimal) {this.decimal=decimal;}
    public void setExponent(int exponent) {this.exponent=exponent;}
    public void setMantissa(int mantissa) {this.mantissa=mantissa;}
    public void setBinaryExp(String binaryExp) {this.binaryExp=binaryExp;}
    public void setBinary(String binary) {this.binary=binary;}
    public void setEXP(int EXP) {this.EXP=EXP;}
    public void setMAN(String MAN) {this.MAN=MAN;}
    public void setUnbias(int unbias) {this.unbias=unbias;}
    public void setExps(int exps) {this.exps=exps;}

    //GETTERS
    public String getUserHex(){return userHex;}
    public String getSign(){String s = " "; return s.valueOf(sign);}
    public String getDecimal(){return decimal;}
    public String getBinaryExp(){return binaryExp;}
    public String getBinary(){return binary;}
    public int getExponent(){return exponent;}
    public int getMantissa(){return mantissa;}
    public int getEXP(){return EXP;}
    public int getUnbias(){return unbias;}
    public int getExps(){return exps;}
    public String getMAN(){return MAN;}

    /*
        No Arg Constructor, sets everything to empty string or 0,
     */
    public FPHexCalc()
    {
        userHex = "";
        sign = '0';
        decimal = "";
        exponent = 0;
        mantissa =0;
        binaryExp = "";
        binary = "";
        EXP = 0;
        MAN = "";
        unbias = 0;
        exps = 0;
        print.append("\nRESULTS");
        print.append("\n---------------------------------------------------------\n");
    }

     /*
            Hex to binary
     */
    public void toBinary()
    {
        //make new instance of binary convert
        HexConvert convert = new HexConvert();

        //convert decimal to binary
        convert.HexToBinary(userHex.trim());

        //store binary number
        binary = convert.getBinary();

        print.append("\nHexadecimal: "+userHex);
        print.append("\nBinary: " + binary);
    }

    public void sectionOff()
    {
        sign = binary.charAt(0); //used to store the sign bit


        print.append("\nSign: "+ sign);

        //extract the exponent based off # of bits allocated, store in binaryExp field
        binaryExp = binary.substring(1,exps+1);
        print.append("\nBinary exponent: " + binaryExp);


        String man = binary.substring(exps+1); //extract the significand portion
        print.append("\nBinary Significand: "+man);
        MAN = man; //store raw significand
    }
    
    /*
        Method that turns binary exponent to decimal
     */
    public void toDecimal()
    {

        char[] s = new char[exps]; //array for each digit of binary exponent

        //loop through and put each bit into array
        for(int i = 0; i<exps;i++)
        {
            s[i] = binaryExp.charAt(i); //stores each bit in array
        }

        //new array to store reversed string
        char[] s1 = new char[exps];

        int exp = exps; //used to decrement original array

        //loop through and reverse string
        for(int i = 0; i<exps;i++)
        {
            s1[i]=s[exp-1]; //reverse string
            exp--; //decrement index
        }

        //new stringbuilder to manipulate string
        StringBuilder sb = new StringBuilder();

        //loop through and append the reversed bits to stringbuilder object
        for(int i = 0; i<exps; i++)
        {
            sb.append(s1[i]);
        }

        //incrementer to hold decimal value of binary number
        int total = 0;

        //loop through and calculate decimal number of binary exponent
        for(int i = 0; i<exps; i++)
        {
            if(sb.charAt(i)=='1')
            {
                total = total + (int)Math.pow(2,i);
            }

        }

        EXP = total; //stores the decimal value of the exponent in the EXP field
        print.append("\nBiased Exponent: "+EXP);
    }

    /*
        Method that returns the bias of the exponent
    */
    public int getBias() {
        return (int) (Math.pow(2.0, (exps - 1)) - 1); //returns formula: (2^(n-1))-1, where n is the exponent
    }

    /*
            Method that Calculates the raw decimal exponent
         */
    public void calcExp()
    {
        EXP = EXP - getBias();
        print.append("\nUnbiased (raw) exponent: "+EXP);
    }

    /*
        Method to Denormalize the Significand of the floating point number
     */
    public String Denormalize()
    {
        StringBuilder s = new StringBuilder();

        s.append(1);
        s.append('.');
        s.append(MAN);

        //System.out.println(MAN); //prints out mantissa
        return s.toString();
    }

    /*
        Method that Moves the decimal Point
     */
    public void movePoint()
    {
        StringBuilder s = new StringBuilder(Denormalize()); //used for manipulating denormalized string

        //s.deleteCharAt(1);

        //if the absolute value of the exponent minus the significand length is positive
        //it means that there needs to be 0's inserted or appended
        if(Math.abs(EXP)-MAN.length()>0)
        {
            //if the exponent is negative, then 0s should be inserted before
            if (EXP < 0)
            {
                int e = -1 * EXP; //turn exponent positive for looping purposes

                //loop through to INSERT zeros to front
                for (int i = 0; i < e; i++)
                {
                    s.insert(0, 0); //insert extra 0s as needed
                }

            }
            //if the exponent is positive, append 0s
            else
                {
                    //Loop through to append 0s
                for (int i = 0; i < EXP - MAN.length(); i++)
                {
                    s.append(0); //add 0s to end of string
                }

            }

        }
        //used if there is an integer and fractional part
        else
        {
            //if exponent is positive, move the decimal point according to the exponent
            if(EXP>0)
            {
                s.insert((Math.abs(EXP)+2),'.'); //insert decimal point

                s.deleteCharAt(1); //delete first decimal point
            }
            //if exponent is negative, insert 0s before number
            else
            {
                s.deleteCharAt(1); //delete decimal point

                int e1 = -1 * EXP; //turn exp into positive for looping purposes

                //insert respective amount of 0's
                for (int d = 0; d < e1; d++)
                {
                    s.insert(0, 0); //insert 0s at beginning of string
                }
            }
        }
                MAN = s.toString(); //store new significand

        print.append("\nAfter moving decimal: " + MAN); //append to the printing statement
    }

    /*
        Method that finishes the calculations
     */
    public void finish()
    {
        String ints = ""; //used to hold the substring of the integer part
        String hey = ""; //used to hold the substring of the fractional part

        int EXP = 10;
        int total1 =0; //accumulator for the integer decimal value after conversion
        int total = 0;
        double tt = 0; //accumulator for the fractional decimal value after conversion

        //new array for each digit of binary exponent
        char[] s3 = new char[MAN.length()];

        //loop through and put each bit into array
        for(int i = 0; i<MAN.length();i++)
        {
            s3[i] = MAN.charAt(i); //stores each bit in array
        }

        //new array to store reversed string
        char[] s1 = new char[MAN.length()];

        int exp = MAN.length(); //used to decrement original array

        //loop through and reverse string
        for(int i = 0; i<MAN.length();i++)
        {
            s1[i]=s3[exp-1]; //reverse string
            exp--; //decrement index
        }

        //new stringbuilder to manipulate string
        StringBuilder sb = new StringBuilder();

        //loop through and append the reversed bits to stringbuilder object
        for(int i = 0; i<MAN.length(); i++)
        {
            sb.append(s1[i]); //append reversed bits
        }

        //if there is a decimal point in the significand
        if(MAN.indexOf('.')!=-1)
        {
            //loop through the string
            for (int k = 0; k < MAN.length(); k++)
            {
                //find the decimal point in the string
                if (sb.charAt(k) == '.')
                {
                    ints = sb.substring(0, k); //extract the integer part

                    hey = sb.substring(k+1); //extract the fractional part
                }
            }

            //incrementer to calculate the fractional part of the number (after the decimal)
            int l2 = 0;

            //loop through and calculate fractional part of the number
            for (int i1 = 0; i1 < ints.length(); i1++)
            {
                //when a set bit is found, calculate the value of the fractional part

                if (ints.charAt(i1) == '1')
                {
                    l2 = -1*(ints.length()-i1); //find the place value of the fractional part

                    tt = tt + Math.pow(2, l2); //calculate the value of the fractional part
                }
                 //decrement the place value as it is passed
                //System.out.println(tt);
            }

            //loop through and calculate integer part of the number
            for(int i2 = 0; i2<hey.length(); i2++)
            {
                //check if a bit is set
                if(hey.charAt(i2)=='1')
                {
                    total1 = total1 + (int)Math.pow(2,i2); //calculate the integer part of the number
                }
            }

            //new stringbuilder for string manipulation
            StringBuilder s = new StringBuilder();

            //new stringbuilder with the decimal value of the binary fractional part
            StringBuilder tri = new StringBuilder(Double.toString(tt));

            tri.deleteCharAt(0); //delete the decimal point

            //concantenate the integer part and fractional part together
            s.append(total1);
            //s.append('.');
            s.append(tri);

            if (sign == '1') //if the number is negative, print out a negative sign
            {
                //add negative sign
                print.append("\nANSWER IN DECIMAL: " + "-" + s.toString());
            }
            else //if it is positive, leave as is
            {
                print.append("\nANSWER IN DECIMAL: " + s.toString());
            }

        }
        else
        {
            if(EXP<0) {
                for (int i1 = 0; i1 < MAN.length(); i1++)
                {
                    if (sb.charAt(i1) == '1')
                    {
                        total = total + (int) Math.pow(2, -i1);
                    }
                }
            }
            else
            {
                for (int i1 = 0; i1 < MAN.length(); i1++)
                {
                    if (sb.charAt(i1) == '1')
                    {
                        total = total + (int) Math.pow(2, i1);
                    }
                }
            }
            if(sign=='1')
            {
                print.append("\nANSWER IN DECIMAL: " + "-" + total);
            }
            else
            {
                print.append("\nANSWER IN DECIMAL: " + total);
            }
        }
        }

        /*
            Constructor, initializes the hexadecimal field, and the bit amount for the significand and exponent
            @param userHex: accepts a hexadecimal number for conversion
            @param mantissa: accepts the bit amount for the significand
            @param exps: accepts the bit amount for the exponent
         */
        public FPHexCalc(String userHex,int mantissa, int exps)
        {
            this.userHex = userHex;
            this.mantissa = mantissa;
            this.exps = exps;

            //append the results separator
            print.append("\nRESULTS");
            print.append("\n---------------------------------------------------------\n");
            
        }

        /*
            toString method, prints out the StringBuilder object that
            appended all output info throughout the methods
         */
        public String toString()
        {
            return print.toString();
        }

    }

