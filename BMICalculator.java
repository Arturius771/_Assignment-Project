//name: Artur Foden email: fodena@tcd.ie phone: 0852447630
//Assignment project for IBAT Introduction to Computer Programming Sep-Nov 2017
//Last edited 2017-12-20 Netbeans 8.2
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BMICalculator {
    public static void main(String[] args) {        
        BMIFrame bmiframe = new BMIFrame();
        bmiframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bmiframe.setSize(316,310);//width, height
        bmiframe.setLocation(500,300);//position
        bmiframe.setTitle("BMI Calculator");
        bmiframe.setVisible(true);
    }//end method main    
}//end class BMICalculator

class BMIFrame extends JFrame {
    private JLabel instructionLabel = new JLabel("Please enter your weight and height:");
    private JLabel weightLabel = new JLabel("Weight:");
    private JLabel heightLabel = new JLabel("Height:");
    private JLabel or1Label = new JLabel("or");
    private JLabel or2Label = new JLabel("or");
    private JLabel kgLabel = new JLabel("KGs:");
    private JLabel cmLabel = new JLabel("CMs:");
    private JLabel stLabel = new JLabel("Stones:");
    private JLabel lbLabel = new JLabel("Pounds:");
    private JLabel ftLabel = new JLabel("Feet:");
    private JLabel inLabel = new JLabel("Inches:");
    private JTextField kgField = new JTextField("0.00");
    private JTextField cmField = new JTextField("0.00");
    private JTextField stField = new JTextField("0.00");
    private JTextField lbField = new JTextField("0.00");
    private JTextField ftField = new JTextField("0.00");
    private JTextField inField = new JTextField("0.00");
    private JTextField resultField = new JTextField("Result");
    private JButton calculateButton = new JButton("Calculate");
    private JButton clearButton = new JButton("Clear");
    private JButton quitButton = new JButton("Quit");
    private JPanel buttonArea = new JPanel();
    private GridLayout grid = new GridLayout(1,3);
    
    public BMIFrame(){  
        setLayout(null);
        ButtonHandler bh = new ButtonHandler();
        
        instructionLabel.setBounds(10,10,250,25);
        add(instructionLabel);
        weightLabel.setBounds(20,55,250,25);
        add(weightLabel);
        heightLabel.setBounds(20,110,250,25);
        add(heightLabel);
        
        or1Label.setBounds(130,55,25,25);
        add(or1Label);
        or2Label.setBounds(130,110,25,25);
        add(or2Label);
        
        kgLabel.setBounds(75,30,50,25);
        add(kgLabel);          
        cmLabel.setBounds(75,85,50,25);
        add(cmLabel);           
        stLabel.setBounds(155,30,50,25);
        add(stLabel);           
        lbLabel.setBounds(215,30,50,25);
        add(lbLabel);           
        ftLabel.setBounds(155,85,50,25);
        add(ftLabel);          
        inLabel.setBounds(215,85,50,25);
        add(inLabel);//text labels       
        
        kgField.setBounds(75,55,45,25);
        kgField.setHorizontalAlignment(SwingConstants.RIGHT);
        add(kgField);         
        cmField.setBounds(75,110,45,25);
        cmField.setHorizontalAlignment(SwingConstants.RIGHT);
        add(cmField);           
        stField.setBounds(155,55,45,25);
        stField.setHorizontalAlignment(SwingConstants.RIGHT);
        add(stField);            
        lbField.setBounds(215,55,45,25);
        lbField.setHorizontalAlignment(SwingConstants.RIGHT);
        add(lbField);            
        ftField.setBounds(155,110,45,25);
        ftField.setHorizontalAlignment(SwingConstants.RIGHT);
        add(ftField);            
        inField.setBounds(215,110,45,25);
        inField.setHorizontalAlignment(SwingConstants.RIGHT);
        add(inField);//input fields
        
        resultField.setBounds(10,155,280,25);
        resultField.setEditable(false);
        resultField.setHorizontalAlignment(SwingConstants.CENTER);
        add(resultField);//result field
        
        buttonArea.setBounds(10,200,280,60);
        buttonArea.setLayout(grid);
        buttonArea.add(calculateButton);
        buttonArea.add(clearButton);
        buttonArea.add(quitButton);
        add(buttonArea);//buttons        
        
        calculateButton.addActionListener(bh);
        clearButton.addActionListener(bh);
        quitButton.addActionListener(bh);//add action listeners for button functionality
    }//end constructor BMIFrame()
    
    private class ButtonHandler implements ActionListener{
        public void actionPerformed(ActionEvent event){
            
            if(event.getSource() == quitButton)
                System.exit(0);//quit the programme
            else if(event.getSource() == clearButton){
                kgField.setText("0.00");
                cmField.setText("0.00");
                stField.setText("0.00");
                lbField.setText("0.00");
                ftField.setText("0.00");
                inField.setText("0.00");
                resultField.setText("Result");
                return;
            }//end else if clearButton event            
            
            if(event.getSource() == calculateButton){
                try{
                    double result = 0;
                    double weight = 0;
                    double height = 0;
                    double kg = Double.parseDouble(kgField.getText());//kilos
                    double m = Double.parseDouble(cmField.getText()) / 100;//metres
                    double st = Double.parseDouble(stField.getText()) * 14;//stone input converted to pounds
                    double lb = Double.parseDouble(lbField.getText());//pounds
                    double ft = Double.parseDouble(ftField.getText()) * 12;//foot input converted to inches
                    double in = Double.parseDouble(inField.getText());//inches
                    String bmiValue = new String("");//Underweight, Normal, Overweight, Obese
                    
                    if(kg > 0){
                        weight = kg;
                    }
                    else{
                        weight = (st+lb) * 0.453592;//adds imperial and converts to metric
                    }
                    if(m > 0){
                        height = m;
                    }
                    else{
                        height = (ft+in) * 0.0254;//adds imperial and converts to metric
                    }                    
                    
                    InvalidRangeCheck(weight, height, kg, m, st, lb, ft, in);//validates the user's input
                    
                    result = weight / (height * height);//Calculates BMI
                    
                    if(result < 18.5){
                        bmiValue = "Underweight";
                    }
                    else if(result >= 18.5 && result <= 24.9){
                        bmiValue = "Normal (healthy weight)";
                    }
                    else if(result > 25 && result <= 29.9){
                        bmiValue = "Overweight";
                    }
                    else if(result > 30){
                        bmiValue = "Obese";
                    }//end if else setBmiValue string statements                      
                    
                    String resultText = String.format("%s: %,3.1f BMI", bmiValue, result);//Displays the result eg. "Normal Weight: 20 BMI"
                    resultField.setText(resultText);                     
                }//end try
                catch(Exception exception){                      
                    JOptionPane.showMessageDialog(BMIFrame.this, exception.toString(), "Error Message", JOptionPane.WARNING_MESSAGE);//Displays error message for all exceptions                    
                }//end catch                 
            }//end if calculateButton event     
        }//end method action event
        
        public void InvalidRangeCheck(double weight, double height, double kg, double m, double st, double lb, double ft, double in) throws Exception{
            if(weight <= 0 || height <= 0 || kg < 0 || m < 0 || st < 0 || lb < 0 || ft < 0 || in < 0)
                throw new Exception("Please enter a number greater than 0");//checks for negative numbers in any input field
            if(weight > 500 || height > 2.7)
                throw new Exception("Input too high, please try again");//checks to make sure the input is within human limits - very unliked anyone heavier or taller than this
            if(height <= 0.5)
                throw new Exception("Height is too low");//checks to make sure the height is within human limits - very few adults are shorter than this                                       
            if(kg != 0 && st != 0 || kg !=0 && lb != 0 || m != 0 && ft != 0 || m != 0 && in != 0)
                throw new Exception("You've entered your weight or height twice");//displays a warning message if weight or height is in both metric and imperial fields - the calculation will favor the metric input
        }//end method InvalidRangeException
    }//end private class ButtonHandler
}//end class BMIFrame