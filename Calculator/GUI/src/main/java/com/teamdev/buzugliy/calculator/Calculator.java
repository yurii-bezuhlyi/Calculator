package com.teamdev.buzugliy.calculator;

import com.teamdev.bezugliy.calculator.impl.EvaluationStateMachine;
import com.teamdev.buzugliy.calculator.swingClass.Dialog;

import java.math.BigDecimal;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) throws Exception {
        if (args.length != 0) {
            if ("-cmd".equals(args[0])) {
                System.out.println("Input mathematical expression"+
                        '\n'+
                        "(You can use functions such as min, max, sqrt, sum)");
                Scanner sc = new Scanner(System.in);
                String s = sc.nextLine();
                final BigDecimal result = new EvaluationStateMachine().evaluate(s);
                System.out.println("result = " + result);
            }
        }
        else
        {
            Dialog dialog= new Dialog();
            dialog.setVisible(true);
        }
    }
}
