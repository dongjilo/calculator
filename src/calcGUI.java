import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class calcGUI extends JFrame implements ActionListener {

    //Create required objects
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JTextField textField = new JTextField();

    JButton[] numButtons = new JButton[10];
    JButton[] funcButtons = new JButton[10];
    JButton addButton, difButton, mulButton, divButton, sqrtButton;
    JButton decButton, eqlButton, delButton, clrButton, negButton;

    Font font = new Font("Open Sans", Font.PLAIN, 16);
    Font lcdFont = new Font("DS-Digital", Font.BOLD, 25);


    DecimalFormat df = new DecimalFormat("#.#######");

    double n1 = 0, n2 = 0, eq = 0;
    char operators;

    //define the method to start the application
    public void calculatorGUI(){

        // Configure the main frame
        frame.setTitle("Calculator");
        frame.setSize(420, 550);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setResizable(false);

        // Configure the text field
        textField.setBounds(50,50,300,50);
        textField.setEditable(false);
        textField.setFont(lcdFont);
        textField.setHorizontalAlignment(SwingConstants.RIGHT);

        // Define the functional buttons
        addButton = new JButton("+");
        difButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");

        decButton = new JButton(".");
        eqlButton = new JButton("=");
        delButton = new JButton("DEL");
        clrButton = new JButton("CLR");
        negButton = new JButton("(-)");
        sqrtButton = new JButton("sqrt");

        // Adding the functional buttons to an array
        funcButtons[0] = addButton;
        funcButtons[1] = difButton;
        funcButtons[2] = mulButton;
        funcButtons[3] = divButton;
        funcButtons[4] = decButton;
        funcButtons[5] = eqlButton;
        funcButtons[6] = delButton;
        funcButtons[7] = clrButton;
        funcButtons[8] = negButton;
        funcButtons[9] = sqrtButton;

        // Configure the functional buttons
        for (int i = 0; i < 10; i++) {
            funcButtons[i].addActionListener(this);
            funcButtons[i].setFocusable(false);
            funcButtons[i].setBackground(Color.decode("#F5F5F5"));
            funcButtons[i].setForeground(Color.decode("#4D4D4D"));
            funcButtons[i].setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            funcButtons[i].setFont(font);
            funcButtons[i].setBorder(BorderFactory.createLineBorder(Color.decode("#D9D9D9")));
            funcButtons[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        // Configure the number buttons
        for (int i = 0; i < 10; i++) {
            numButtons[i] = new JButton(String.valueOf(i));
            numButtons[i].addActionListener(this);
            numButtons[i].setFocusable(false);
            numButtons[i].setBackground(Color.decode("#F5F5F5"));
            numButtons[i].setForeground(Color.decode("#4D4D4D"));
            numButtons[i].setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            numButtons[i].setFont(font);
            numButtons[i].setBorder(BorderFactory.createLineBorder(Color.decode("#D9D9D9")));
            numButtons[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }


        // Configure the panel
        panel.setBounds(50, 150, 300, 300);
        panel.setLayout(new GridLayout(5,4, 10, 10));

        // Adding components to the panel
        panel.add(sqrtButton);
        panel.add(negButton);
        panel.add(delButton);
        panel.add(clrButton);

        panel.add(numButtons[1]);
        panel.add(numButtons[2]);
        panel.add(numButtons[3]);
        panel.add(addButton);
        panel.add(numButtons[4]);
        panel.add(numButtons[5]);
        panel.add(numButtons[6]);
        panel.add(difButton);
        panel.add(numButtons[7]);
        panel.add(numButtons[8]);
        panel.add(numButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numButtons[0]);
        panel.add(eqlButton);
        panel.add(divButton);

        // Adding the panel to the main frame
        frame.add(panel);
        frame.add(textField);
        frame.setVisible(true);

    }

    // This method is called when any button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {

        String textCurrent;
        boolean isZero = textField.getText().equals("0");

        // check if number buttons are pressed then appends
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numButtons[i]) {
                textCurrent = textField.getText();
                // if the first number clicked is zero, replace it with the number button value
                if (isZero) {
                    textField.setText(String.valueOf(i));
                } else {
                    // otherwise, append the number button value to the current value
                    textField.setText(textCurrent + i);
                }
                break;
            }
        }

        // check if negative button is pressed
        if (e.getSource() == negButton) {
            textCurrent = textField.getText();
            // if the current value doesn't start with a "-", add one
            if (!textCurrent.startsWith("-")){
                textCurrent = "-" + textCurrent;
            }else {
                // if it already starts with a "-", remove it
                textCurrent = textCurrent.substring(1);
            }
            textField.setText(textCurrent);
        }

        // check if decimal button is pressed
        if (e.getSource() == decButton) {
            textCurrent = textField.getText();
            // if the current value does not contain ".", add one
            if (!textCurrent.contains(".")) {
                textField.setText(textCurrent + ".");
            }
        }

        //check if the sqrt button is pressed
        if (e.getSource() == sqrtButton) {
            textCurrent = textField.getText();
            eq = Math.sqrt(Double.parseDouble(textCurrent));
            textField.setText(String.valueOf(df.format(eq)));
        }

        // check if the add button is pressed
        if (e.getSource() == addButton) {
            n1 = Double.parseDouble(textField.getText());
            operators = '+';
            textField.setText("");
        }

        // check if the subract button is pressed
        if (e.getSource() == difButton) {
            n1 = Double.parseDouble(textField.getText());
            operators = '-';
            textField.setText("");
        }

        // check if the multiply button is pressed
        if (e.getSource() == mulButton) {
            n1 = Double.parseDouble(textField.getText());
            operators = '*';
            textField.setText("");
        }

        // check if the divide button is pressed
        if (e.getSource() == divButton) {
            n1 = Double.parseDouble(textField.getText());
            operators = '/';
            textField.setText("");
        }

        // check if the equal button is pressed
        if (e.getSource() == eqlButton) {
            n2 = Double.parseDouble(textField.getText());


            // perform the corresponding operation based on the operator variable
            switch (operators) {
                case '+':
                    eq = n1 + n2;
                    break;
                case '-':
                    eq = n1 - n2;
                    break;
                case '*':
                    eq = n1 * n2;
                    break;
                case '/':
                    eq = n1 / n2;
                    break;
            }

            // format the result using DecimalFormat
            textField.setText(String.valueOf(df.format(eq)));
        }

        // check if clear button is pressed
        if (e.getSource() == clrButton) {
            textField.setText("");
        }

        // check if delete button is pressed
        if (e.getSource() == delButton) {
            textCurrent = textField.getText();
            // if the current value contains at least one character, remove the last character
            if (textCurrent.length() > 0) {
                textCurrent = textCurrent.substring(0, textCurrent.length() - 1);
                textField.setText(textCurrent);
            }
        }

    }

    public static void main(String[] args) {
        calcGUI gui = new calcGUI();
        gui.calculatorGUI();
    }
}
