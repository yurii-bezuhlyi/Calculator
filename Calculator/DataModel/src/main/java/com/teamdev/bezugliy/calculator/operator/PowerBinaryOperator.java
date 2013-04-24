package com.teamdev.bezugliy.calculator.operator;

import java.math.BigDecimal;

public class PowerBinaryOperator extends AbstractBinaryOperator {

    public PowerBinaryOperator(Priority priority) {
        super(priority);
    }

    @Override
    public BigDecimal evaluate(BigDecimal leftOperand, BigDecimal rightOperand) {
        return new BigDecimal(Math.pow(leftOperand.doubleValue(),
                rightOperand.doubleValue()));
    }
}
