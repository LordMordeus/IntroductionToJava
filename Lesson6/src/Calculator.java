import javax.swing.*;
import java.awt.GridLayout;
import java.awt.BorderLayout;
public class Calculator {
    JPanel windowContent;
    JTextField displayField;
    JButton buttonPoint;
    JButton buttonResult;
    JPanel panel1;
    JPanel panel2;
    JButton buttonPlus;
    JButton buttonMinus;
    JButton buttonMultiply;
    JButton buttonDivision;

    JButton [] numButtons= new JButton[10];

    Calculator(){
        windowContent= new JPanel();
        BorderLayout bl = new BorderLayout();
        windowContent.setLayout(bl);
        displayField = new JFormattedTextField();
        displayField.setHorizontalAlignment(SwingConstants.RIGHT);
        windowContent.add("North",displayField);

        for (int i=0; i<10; i++){
            numButtons[i]=new JButton(""+i);
        }

        buttonPoint = new JButton(".");
        buttonResult = new JButton("=");
        buttonPlus = new JButton("+");
        buttonMinus = new JButton("-");
        buttonMultiply = new JButton("*");
        buttonDivision = new JButton("/");
        panel2 = new JPanel();
        GridLayout glp2 =new GridLayout(4,1);
        panel2.setLayout(glp2);
        panel2.add(buttonPlus);
        panel2.add(buttonMinus);
        panel2.add(buttonMultiply);
        panel2.add(buttonDivision);
        windowContent.add("East", panel2);
        panel1 = new JPanel();
        GridLayout gl =new GridLayout(4,4);
        panel1.setLayout(gl);

        for (int i=1; i<=10; i++){
            if (i==10){
                panel1.add(numButtons[0]);
                continue;
            }
            panel1.add(numButtons[i]);
        }

        panel1.add(buttonPoint);
        panel1.add(buttonResult);
        windowContent.add("Center", panel1);
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(windowContent);
        frame.pack();
        frame.setVisible(true);

        CalculatorActions calcEngine = new CalculatorActions(this);

        numButtons[0].addActionListener(calcEngine);
        numButtons[1].addActionListener(calcEngine);
        numButtons[2].addActionListener(calcEngine);
        numButtons[3].addActionListener(calcEngine);
        numButtons[4].addActionListener(calcEngine);
        numButtons[5].addActionListener(calcEngine);
        numButtons[6].addActionListener(calcEngine);
        numButtons[7].addActionListener(calcEngine);
        numButtons[8].addActionListener(calcEngine);
        numButtons[9].addActionListener(calcEngine);
        buttonDivision.addActionListener(calcEngine);
        buttonPoint.addActionListener(calcEngine);
        buttonResult.addActionListener(calcEngine);
        buttonPlus.addActionListener(calcEngine);
        buttonMinus.addActionListener(calcEngine);
        buttonMultiply.addActionListener(calcEngine);

    }
    public static void main(String[] args) {
        Calculator calc = new Calculator();
    }
}