package com.teamdev.bezugliy.calculator.operator;

import java.math.BigDecimal;

public class MinusBinaryOperator extends AbstractBinaryOperator {

    public MinusBinaryOperator(Priority priority) {
        super(priority);
    }

    @Override
    public BigDecimal evaluate(BigDecimal leftOperand, BigDecimal rightOperand) {
        return leftOperand.subtract(rightOperand);
    }

}
