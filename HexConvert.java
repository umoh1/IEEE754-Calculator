public class HexConvert
{
    //fields
    private String hexadecimal;
    private String decimal;
    private String binary;

    /*
        Method that converts a hex number to a binary number
        @param String binary: takes a binary number as input
     */
    public void HexToBinary(String Hexadecimal)
    {
        StringBuilder hex = new StringBuilder(); //make string builder to append hex digits

        hexadecimal = Hexadecimal; //set field to Hex passed to method
        
        //convert hex digits to binary
        for(int i = 0; i<Hexadecimal.length(); i++) {
            if (Hexadecimal.charAt(i) == ('0')) //for hex #0
            {
                hex.append("0000");
            } else if (Hexadecimal.charAt(i) == '1') //for hex #1
            {
                hex.append("0001");
            } else if (Hexadecimal.charAt(i) == '2') //for hex #2
            {
                hex.append("0010");
            } else if (Hexadecimal.charAt(i) == '3') //for hex #3
            {
                hex.append("0011");
            } else if (Hexadecimal.charAt(i) == '4') //for hex #4
            {
                hex.append("0100");
            } else if (Hexadecimal.charAt(i) == '5') //for hex #5
            {
                hex.append("0101");
            } else if (Hexadecimal.charAt(i) == '6') //for hex #6
            {
                hex.append("0110");
            } else if (Hexadecimal.charAt(i) == '7') //for hex #7
            {
                hex.append("0111");
            } else if (Hexadecimal.charAt(i) == '8') //for hex #8
            {
                hex.append(1000);
            } else if (Hexadecimal.charAt(i) == '9') //for hex #9
            {
                hex.append(1001);
            } else if (Hexadecimal.charAt(i) == 'A') //for hex #10
            {
                hex.append(1010);
            } else if (Hexadecimal.charAt(i) == 'B') //for hex #11
            {
                hex.append(1011);
            } else if (Hexadecimal.charAt(i) == 'C') //for hex #12
            {
                hex.append(1100);
            } else if (Hexadecimal.charAt(i) == 'D') //for hex #13
            {
                hex.append(1101);
            } else if (Hexadecimal.charAt(i) == 'E') //for hex #14
            {
                hex.append(1110);
            } else if (Hexadecimal.charAt(i) == 'F') //for hex #15
            {
                hex.append(1111);
            } else //anything else, which there shouldn't be
            {
                hex.append("Error");
            }
        }
        
        binary = hex.toString(); //turn stringbuilder into string, store in binary field
    }

    /*
        This method converts hexadecimal to decimal
        @param Hexadecimal: takes in a hexadecimal as input
     */
    public void HexToDecimal(String Hexadecimal)
    {
        BinaryConvert convert = new BinaryConvert(); //make new instance of binary convert
        
        //convert hex to binary
        HexToBinary(Hexadecimal);
        
        //convert binary to decimal
        convert.BinarytoDecimal(binary); 
        
        //store result in decimal field
        decimal = convert.getDecimal(); 

    }
    
    //setters
    public void setHexadecimal(String hexadecimal) {this.hexadecimal = hexadecimal;}
    public void setDecimal(String decimal){this.decimal = decimal;}
    public void setBinary(String binary){this.binary = binary;}

    //getters
    public String getHexadecimal(){return hexadecimal;}
    public String getDecimal(){return decimal;}
    public String getBinary(){return binary;}
}
