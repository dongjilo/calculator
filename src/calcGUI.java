import CustomComponents.myButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Objects;

public class calcGUI extends JFrame implements ActionListener {

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JTextField textField = new JTextField();

    myButton[] numButtons = new myButton[10];
    myButton[] funcButtons = new myButton[10];
    myButton addButton, difButton, mulButton, divButton, sqrtButton;
    myButton decButton, eqlButton, delButton, clrButton, negButton;

    DecimalFormat df = new DecimalFormat("#.#######");

    double n1 = 0, n2 = 0, eq = 0;
    char operators;

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
        try{
            Font font1 = Font.createFont(Font.PLAIN,
                            Objects.requireNonNull(getClass().getResourceAsStream("/Fonts/DS-DIGI.TTF")))
                    .deriveFont(48f);
            textField.setFont(font1);
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
        textField.setHorizontalAlignment(SwingConstants.RIGHT);

        // Define the functional buttons
        addButton = new myButton("+");
        difButton = new myButton("-");
        mulButton = new myButton("*");
        divButton = new myButton("/");

        decButton = new myButton(".");
        eqlButton = new myButton("=");
        delButton = new myButton("DEL");
        clrButton = new myButton("CLR");
        negButton = new myButton("(-)");
        sqrtButton = new myButton("sqrt");

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

        // Add events to individual components
        for (int i = 0; i < 10; i++) {
            numButtons[i] = new myButton(String.valueOf(i));
            funcButtons[i].addActionListener(this);
            numButtons[i].addActionListener(this);
        }

        // Configure the panel
        panel.setBounds(50, 150, 300, 300);
        panel.setLayout(new GridLayout(5,4, 10, 10));

        // Adding components to the panel
        panel.add(sqrtButton);
        panel.add(negButton);
        panel.add(delButton);
        panel.add(clrButton);

        for (int i = 0; i < 4; i++) {
            panel.add(numButtons[i]);
        }
        panel.add(addButton);
        for (int i = 4; i < 7; i++) {
            panel.add(numButtons[i]);
        }
        panel.add(difButton);
        for (int i = 7; i < 10; i++) {
            panel.add(numButtons[i]);
        }
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

        if (e.getSource() == decButton) {
            textCurrent = textField.getText();
            // if the current value does not contain ".", add one
            if (!textCurrent.contains(".")) {
                textField.setText(textCurrent + ".");
            }
        }

        if (e.getSource() == sqrtButton) {
            textCurrent = textField.getText();
            eq = Math.sqrt(Double.parseDouble(textCurrent));
            textField.setText(String.valueOf(df.format(eq)));
        }

        if (e.getSource() == addButton) {
            n1 = Double.parseDouble(textField.getText());
            operators = '+';
            textField.setText("");
        }

        if (e.getSource() == difButton) {
            n1 = Double.parseDouble(textField.getText());
            operators = '-';
            textField.setText("");
        }

        if (e.getSource() == mulButton) {
            n1 = Double.parseDouble(textField.getText());
            operators = '*';
            textField.setText("");
        }

        if (e.getSource() == divButton) {
            n1 = Double.parseDouble(textField.getText());
            operators = '/';
            textField.setText("");
        }

        if (e.getSource() == eqlButton) {
            n2 = Double.parseDouble(textField.getText());

            switch (operators) {
                case '+' -> eq = n1 + n2;
                case '-' -> eq = n1 - n2;
                case '*' -> eq = n1 * n2;
                case '/' -> eq = n1 / n2;
            }

            textField.setText(String.valueOf(df.format(eq)));
        }

        if (e.getSource() == clrButton) {
            textField.setText("");
        }

        if (e.getSource() == delButton) {
            textCurrent = textField.getText();
            // if the current value contains at least one character, remove the last character
            if (textCurrent.length() > 0) {
                textCurrent = textCurrent.substring(0, textCurrent.length() - 1);
                textField.setText(textCurrent);
            }
        }

    }
}