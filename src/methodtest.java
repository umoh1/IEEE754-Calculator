public class methodtest 
{
    public static void main(String args[])
    {
        BinaryConvert convert = new BinaryConvert();

        //convert.6.12 x 10^4
        String str = "6.022140 x 10^23";
        convert.SNDecimaltoFPBinary(str);


        String[] splitting = str.split("[xX^]");

        for(String a: splitting)
        {
            System.out.println(a.trim());
        }
    }
}
