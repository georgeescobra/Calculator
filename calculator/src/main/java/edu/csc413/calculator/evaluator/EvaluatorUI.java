package edu.csc413.calculator.evaluator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Vector;

public class EvaluatorUI extends JFrame implements ActionListener {
    //this is to keep track on whether or not the equals button was activated
    private boolean resultFlag = false;
    private TextField txField = new TextField();
    private Panel buttonPanel = new Panel();

    // total of 20 buttons on the calculator,
    // numbered from left to right, top to bottom
    // bText[] array contains the text for corresponding buttons
    private static final String[] bText = {
        "7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3",
        "*", "0", "^", "=", "/", "(", ")", "C", "CE"
    };

    /**
     * C  is for clear, clears entire expression
     * CE is for clear expression, clears last entry up until the last operator.
     */
    private Button[] buttons = new Button[bText.length];

    public static void main(String argv[]) {
        EvaluatorUI calc = new EvaluatorUI();
    }

    public EvaluatorUI() {
        setLayout(new BorderLayout());
        this.txField.setPreferredSize(new Dimension(600, 50));
        this.txField.setFont(new Font("Courier", Font.BOLD, 28));

        add(txField, BorderLayout.NORTH);
        txField.setEditable(false);

        add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setLayout(new GridLayout(5, 4));

        //create 20 buttons with corresponding text in bText[] array
        Button bt;
        for (int i = 0; i < EvaluatorUI.bText.length; i++) {
            bt = new Button(bText[i]);
            bt.setFont(new Font("Courier", Font.BOLD, 28));
            buttons[i] = bt;
        }

        //add buttons to button panel
        for (int i = 0; i < EvaluatorUI.bText.length; i++) {
            buttonPanel.add(buttons[i]);
        }

        //set up buttons to listen for mouse input
        for (int i = 0; i < EvaluatorUI.bText.length; i++) {
            buttons[i].addActionListener(this);
        }

        setTitle("Calculator");
        setSize(400, 400);
        setLocationByPlatform(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent arg0) {
        if(!txField.getText().isEmpty()){
            //this button clears everything up until the last operator
            if(arg0.getActionCommand().equals("C")){
                try {
                    //gets the expression on the screen
                    String temp = txField.getText();
                    Vector holder = new Vector();
                    //finds the last occurrence of all the operators and pushes them into a vector holder
                    holder.add(temp.lastIndexOf('+'));
                    holder.add(temp.lastIndexOf('-'));
                    holder.add(temp.lastIndexOf('/'));
                    holder.add(temp.lastIndexOf('*'));
                    holder.add(temp.lastIndexOf('^'));
                    holder.add(temp.lastIndexOf('('));
                    holder.add(temp.lastIndexOf(')'));
                    //this finds the max/last index of the last operator
                    Object max = Collections.max(holder);
                    if ((Integer) max == 0) {
                        txField.setText(null);
                    } else {
                        //prints out the substring starting from temp.0 to last operator seen
                        txField.setText(temp.substring(0, (Integer) max));
                    }
                }catch (StringIndexOutOfBoundsException e){
                    //this exception is thrown when no operators can be found e.g. an expression like "3"
                    //and just sets the field to null
                    txField.setText(null);
                }
                //this button clears everything
            }else if(arg0.getActionCommand().equals("CE")){
                txField.setText(null);
                //this triggers the evaluation drivers
            }else if(arg0.getActionCommand().equals("=")){
                //I copied the variables from the EvalDriver because I was lazy
                int res;
                Evaluator ev = new Evaluator();
                res = ev.eval(txField.getText());
                txField.setText(Integer.toString(res));
                //I set the flag to true so that if the user keeps typing after getting the result
                //the txtField will reset back to null and starts with the next user input
                resultFlag = true;
            }else if (resultFlag){
                txField.setText(arg0.getActionCommand());
                resultFlag = false;
            }else{
                txField.setText(txField.getText() + arg0.getActionCommand());
            }
            //this checks if the textfield is empty
        }else if(txField.getText().isEmpty()){
            //this just makes sure that no errors are thrown when user presses
            //C, CE, = when the txtField is empty
            if(arg0.getActionCommand().equals("C") || arg0.getActionCommand().equals("CE") || arg0.getActionCommand().equals("=")){
                txField.setText(null);
            }else {
                txField.setText(arg0.getActionCommand());
            }
        }

    }

}
