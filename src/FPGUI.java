
/**
 * Write a description of class FPGUI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FPGUI extends JFrame {
    //fields

    //sliders
    private JSlider expslide;
    private JSlider signslide;

    //text areas
    private JTextArea printBinary;
    private JTextArea printDecimal;
    private JTextArea printHex;
    private JTextArea printBias;
    private JTextArea printExp;
    private JTextArea printMan;
    private JTextArea printSign;
    private JTextArea printTotalBits;

    //button
    private JButton calc;
    private JButton showSteps;

    //create labels
    private JLabel decInput;
    private JLabel hexInput;
    private JLabel expInput;
    private JLabel manInput;
    private JLabel hexOut;
    private JLabel decOut;
    private JLabel biasOut;
    private JLabel expValueOut;
    private JLabel manValueOut;
    private JLabel binaryOut;
    private JLabel totalBitsOut;
    private JLabel signOut;

    //create textfields
    private JTextField decIn;
    private JTextField hexIn;

    //calculating classes
    private FPHexCalc hexadecimal = new FPHexCalc();
    private FloatingPointCalculator decimal  = new FloatingPointCalculator();

    //panel
    //private JPanel panel;
    //private BorderLayout border;

    /*
     * Creates labels
     */
    public void createLabels() {
        //initialize labels
        decInput = new JLabel("Decimal: "); //for decimal input

        hexInput = new JLabel("Hexadecimal: "); //for hex input

        expInput = new JLabel("Exponent Slider value: "); //for exponent slider

        manInput = new JLabel("Significand Slider value"); //for significand slider

        hexOut = new JLabel("Hexadecimal value: "); //for hex output

        decOut = new JLabel("Decimal value: "); //for decimal output

        biasOut = new JLabel("Bias value: "); //for bias output

        expValueOut = new JLabel("Decimal value of Exponent: "); //for dec value of exponenet

        manValueOut = new JLabel("Decimal Value of Signficand: "); //for decimal value of significand

        binaryOut = new JLabel("Binary value: "); //for binary value

        totalBitsOut = new JLabel("Total bits used: "); //for total bits used

        signOut = new JLabel("Sign: "); //for the sign
    }

    //create sliders
    public void createSliders() //specify slider values
    {
        //initialize sliders
        expslide = new JSlider(1, 16); //make slider for exponent
        ExpSlide slider1 = new ExpSlide();
        expslide.addChangeListener(slider1);


        signslide = new JSlider(1, 64); //make slider for the significand
        SignSlide slider2 = new SignSlide();
        signslide.addChangeListener(slider2);

    }

    class ExpSlide implements ChangeListener
    {

        @Override
        public void stateChanged(ChangeEvent e)
        {
            decimal.setMantissa(signslide.getValue());
            hexadecimal.setMantissa(expslide.getValue());
        }
    }

    class SignSlide implements ChangeListener
    {

        @Override
        public void stateChanged(ChangeEvent e)
        {
            decimal.setUserExponent(expslide.getValue());
            hexadecimal.setExps(expslide.getValue());
        }
    }


    public void createTextArea() {
        //make textareas
        printBinary = new JTextArea(1, 10);
        printDecimal = new JTextArea(1, 10);
        printHex = new JTextArea(1, 10);
        printBias = new JTextArea(1, 10);
        printExp = new JTextArea(1, 10);
        printMan = new JTextArea(1, 10);
        printSign = new JTextArea(1, 10);
        printTotalBits = new JTextArea(1, 10);

        //make text fields
        decIn = new JTextField(10);
        hexIn = new JTextField(10);
    }

    //create buttons
    public void createButton()//HAVE TO MAKE ACTION LISTENER FOR BUTTON
    {
        showSteps = new JButton("Show Steps"); //button for show steps

        calc = new JButton("Calculate"); //make button for calculating
        if(!(decIn.getText().trim().equals("")))
        {
            calculateDecimaltoHex dc = new calculateDecimaltoHex();
            calc.addActionListener(dc);
        }
        else
        {
            calculateHexToDecimal dc = new calculateHexToDecimal();
            calc.addActionListener(dc);
        }
    }

    class calculateHexToDecimal implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            /*when calculate is clicked, append these to a textarea, Hex to Decimal
                Hexadecimal form
                Binary form
                Sign -
                Binary exponent
                Decimal exponent
                Binary significand
                Unbiased exponent
                After moving decimal
                Decimal
             */

            //do calculations
            hexadecimal.toBinary();
            hexadecimal.sectionOff();
            hexadecimal.getBias();
            hexadecimal.toDecimal();
            hexadecimal.calcExp();
            hexadecimal.movePoint();
            hexadecimal.finish();

            //print out calculations in text area
            hexadecimal.setUserHex(hexIn.getText()); //set hexadecimal for calculations

            //print to text areas
            printHex.append(hexadecimal.getUserHex()); //print hex
            printBinary.append(hexadecimal.getBinary());
            printSign.append(hexadecimal.getSign());
            printExp.append(hexadecimal.getBinaryExp());
            String s ="";
            s =s.valueOf(hexadecimal.getBias());
            printBias.append(s);
            printMan.append(hexadecimal.getMAN());
            printDecimal.append(hexadecimal.getDecimal());


        }
    }


    class calculateDecimaltoHex implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            /*
                Append to text Area
                Decimal number
                Binary number
                Exponent value in decimal
                Biased exponent in decimal
                Floating Point number
                Total # of bits used
                Hexadecimal
             */

            //get text from areas for calculations
            decimal.setDecimal(decIn.getText().trim());
            //decimal.setNums(expslide.getValue(),signslide.getValue());
            
            //go through calculations
            decimal.sign();
            //decimal.toBinary();
            //cl.Normalize();
            //decimal.lastStep();
            //decimal.calcExp();
            decimal.toString();

            //decimal.setDec(decIn.getText()); //set decimal
            //append to text areas
            //printHex.append(decimal.toHex()); //print hex
            printBinary.append(hexadecimal.getBinary());
            printSign.append(hexadecimal.getSign());
            printExp.append(hexadecimal.getBinaryExp());
            String s ="";
            s =s.valueOf(hexadecimal.getBias());
            printBias.append(s);
            printMan.append(hexadecimal.getMAN());
            printDecimal.append(hexadecimal.getDecimal());
        }
    }

    //create panel
    public void createPanel()
    {
        JPanel panel = new JPanel(); //make new panel
        panel.setLayout(null); //set layout to null

        //add slider labels and sliders to panel
        expInput.setBounds(10,20,150,25);
        panel.add(expInput); //add exp label
        expslide.setBounds(200,20,150,25);
        panel.add(expslide); //add exponent slider

        manInput.setBounds(400,20,100,25);
        panel.add(manInput);  //add mantissa label
        signslide.setBounds(600,20,100,25);
        panel.add(signslide); //add mantissa slider

        //add decimal labels and decimal input to panel
        decInput.setBounds(300,300,100,25);
        panel.add(decInput); // add decimal label
        decIn.setBounds(360,300,100,25);
        panel.add(decIn); //add decimal text field

        //add hex labels and hex input to panel
        hexInput.setBounds(250, 350, 100, 25);
        panel.add(hexInput); //add hex label
        hexIn.setBounds(360, 350, 100, 25);
        panel.add(hexIn); //add hex tex field

        calc.setBounds(330, 400, 100, 25);
        panel.add(calc); //calculations button

        //add text areas
        binaryOut.setBounds(700, 350, 100, 25);
        panel.add(binaryOut);
        printBinary.setBounds(800, 350, 100, 25);
        panel.add(printBinary);

        decOut.setBounds(700, 400, 100, 25);
        panel.add(decOut);
        printDecimal.setBounds(800, 400, 100, 25);
        panel.add(printDecimal);

        hexOut.setBounds(700, 450, 100, 25);
        panel.add(hexOut);
        printHex.setBounds(800, 450, 100, 25);
        panel.add(printHex);

        biasOut.setBounds(700, 500, 100, 25);
        panel.add(biasOut);
        printBias.setBounds(800, 500, 100, 25);
        panel.add(printBias);

        expValueOut.setBounds(700, 550, 100, 25);
        panel.add(expValueOut);
        printExp.setBounds(800, 550, 100, 25);
        panel.add(printExp);

        manValueOut.setBounds(700, 600, 100, 25);
        panel.add(manValueOut);
        printMan.setBounds(800, 600, 100, 25);
        panel.add(printMan);

        signOut.setBounds(700, 650, 100, 25);
        panel.add(signOut);
        printSign.setBounds(800, 650, 100, 25);
        panel.add(printSign);

        totalBitsOut.setBounds(700, 700, 100, 25);
        panel.add(totalBitsOut);
        printTotalBits.setBounds(800, 700, 100, 25);
        panel.add(printTotalBits);

        add(panel);
    }

    //
    public FPGUI()
    {
        createLabels();

        createSliders();

        createTextArea();

        createButton();

        createPanel();
    }

    public static void main(String[] args)
    {
        //make instance of degree works frame
        FPGUI frame = new FPGUI();

        //set size to 1600 p width, 900 p height, covers most of the screen
        frame.setSize(1600,900);

        //set default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set the frame title
        frame.setTitle("Floating Point GUI");

        //set the frame visible
        frame.setVisible(true);
    }
}

