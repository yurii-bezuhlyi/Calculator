package com.teamdev.bezugliy.calculator.impl.swingClass;

import com.teamdev.bezugliy.calculator.impl.Calculator;
import com.teamdev.bezugliy.calculator.impl.EvaluationException;

import javax.swing.*;
import static javax.swing.GroupLayout.Alignment.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

class ButtonPanel extends JPanel {
    JTextField textField=new JTextField(20);
    JTextArea textArea=new JTextArea(5,20);
    JButton calcButton = new JButton("calculate");
    JButton clearButton = new JButton("clear");
    public ButtonPanel(){
        JLabel labelExpression = new JLabel("Mathematical expression:");
        JLabel labelResult = new JLabel("Result:");
        textArea.setLineWrap(true);
        CalcAction calcAction = new CalcAction();
        calcButton.addActionListener(calcAction);
        ClearAction clearAction = new ClearAction();
        clearButton.addActionListener(clearAction);
        textField.addActionListener(calcAction);
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(labelExpression)
                        .addComponent(labelResult))
                .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(textField)
                        .addComponent(textArea))
                .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(calcButton)
                        .addComponent(clearButton))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(labelExpression)
                        .addComponent(textField)
                        .addComponent(calcButton))
                .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(labelResult)
                        .addComponent(textArea)
                        .addComponent(clearButton))
        );

    }
    private class CalcAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                final BigDecimal result = new Calculator().evaluate(textField.getText());
                textArea.setText(result.toString());
            } catch (EvaluationException e1) {
                textField.setCaretPosition(e1.getErrorPosition());
                textArea.setText(e1.getMessage());
                calcButton.setFocusable(false);
                textArea.setFocusable(false);
                textField.setFocusable(true);
            }
        }
    }
    private class ClearAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            textField.setText("");
            textArea.setText("");
        }
    }
    }
