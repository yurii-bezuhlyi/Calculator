package com.teamdev.bezugliy.calculator.impl;

import com.teamdev.bezugliy.calculator.impl.swingClass.Dialog;
import com.teamdev.bezugliy.calculator.statemachine.AbstractStateMachine;

import java.math.BigDecimal;
import java.util.Scanner;

public class Calculator extends AbstractStateMachine<
        BigDecimal,
        EvaluationContext,
        EvaluationState,
        EvaluationMatrix,
        EvaluationService,
        EvaluationException> {

    private final EvaluationMatrix matrix = new EvaluationMatrix();

    private final EvaluationService evaluationService = new EvaluationService();

    @Override
    protected EvaluationMatrix getTransitionMatrix() {
        return matrix;
    }

    @Override
    protected EvaluationService getStateRecognizer() {
        return evaluationService;
    }

    @Override
    protected void deadlock(EvaluationContext context) throws EvaluationException {
        final int position = context.getExpressionReader().
                getReadPosition();

        System.out.println("Deadlock at state: " + context.getState());

        System.out.println("Parse position: " + position);

        throw new EvaluationException("Invalid expression format.",
                position);
    }

    public BigDecimal evaluate(String mathExpression)
            throws EvaluationException {
        return run(new EvaluationContext(mathExpression));
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 0) {
            if (args[0] == "cmd") {
                System.out.println("Input mathematical expression"+
                        '\n'+
                        "(You can use functions such as min, max, sqrt, sum)");
                Scanner sc = new Scanner(System.in);
                String s = sc.nextLine();
                final BigDecimal result = new Calculator().evaluate(s);
                System.out.println("result = " + result);
            }
        }
        else
        {
            Dialog dialog= new Dialog();//создаем фрейм
            dialog.setVisible(true);
        }
    }
}
