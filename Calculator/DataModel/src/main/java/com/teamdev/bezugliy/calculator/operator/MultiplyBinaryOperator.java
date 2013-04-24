package com.teamdev.bezugliy.calculator.operator;

import java.math.BigDecimal;

public class MultiplyBinaryOperator extends AbstractBinaryOperator {

    public MultiplyBinaryOperator(Priority priority) {
        super(priority);
    }

    @Override
    public BigDecimal evaluate(BigDecimal leftOperand, BigDecimal rightOperand) {
        return leftOperand.multiply(rightOperand);
    }

}
