package com.teamdev.bezugliy.calculator;

import java.math.BigDecimal;

public interface BinaryOperator extends Comparable<BinaryOperator> {
    BigDecimal evaluate(BigDecimal leftOperand, BigDecimal rightOperand);
}
