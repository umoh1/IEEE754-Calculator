
/**
 * Write a description of class FPGUI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.awt.*;
public class FloatingPointGUI extends JFrame
{
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

    //panel
    private JPanel panel;
    //private GridLayout grid;

    /*
     * Creates labels
     *
     */
    public void createLabels()
    {
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
        expslide = new JSlider(); //make slider for exponent
        signslide = new JSlider(); //make slider for the significand
    }

    public void createTextArea()
    {
        //make textareas
        printBinary = new JTextArea(10,10);
        printDecimal= new JTextArea(10,10);
        printHex= new JTextArea(10,10);
        printBias= new JTextArea(10,10);
        printExp= new JTextArea(10,10);
        printMan= new JTextArea(10,10);
        printSign= new JTextArea(10,10);
        printTotalBits= new JTextArea(10,10);

        //make text fields
        decIn = new JTextField(10);
        hexIn = new JTextField(10);
    }

    //create buttons
    public void createButton()//HAVE TO MAKE ACTION LISTENER FOR BUTTON
    {
        showSteps = new JButton("Show Steps"); //button for show steps
        calc = new JButton("Calculate"); //make button for calculating
    }

    //create panel
    public void createPanel()
    {
        panel = new JPanel(); //make new panel

        //add slider labels and sliders to panel
        expInput.setBounds(10,10,80,50);
        panel.add(expInput); //add exp label

        expslide.setBounds(40,10,100,60);
        panel.add(expslide); //add exponent slider
        panel.add(manInput);  //add mantisa label
        panel.add(signslide); //add mantissa slider

        //add decimal labels and decimal input to panel
        panel.add(decInput); // add decimal label
        panel.add(decIn); //add decimal text field

        //add hex labels and hex input to panel
        panel.add(hexInput); //add hex label
        panel.add(hexIn); //add hex tex field

        add(panel);
    }

    //
    public FloatingPointGUI()
    {
        createLabels();

        createSliders();

        createTextArea();

        createButton();

        createPanel();
    }
}
