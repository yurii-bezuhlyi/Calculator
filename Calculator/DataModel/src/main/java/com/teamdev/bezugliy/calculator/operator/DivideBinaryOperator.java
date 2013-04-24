package com.teamdev.bezugliy.calculator.operator;

import java.math.BigDecimal;

public class DivideBinaryOperator extends AbstractBinaryOperator {

    public DivideBinaryOperator(Priority priority) {
        super(priority);
    }

    @Override
    public BigDecimal evaluate(BigDecimal leftOperand, BigDecimal rightOperand) {

        if (BigDecimal.ZERO.compareTo(rightOperand) == 0) {
            throw new IllegalArgumentException("Division by zero.");
        }
        final double a,b,c;
        a = leftOperand.doubleValue();
        b = rightOperand.doubleValue();
        c = a/b;
        //return leftOperand.divide(rightOperand);
        return new BigDecimal(c);
    }
}
