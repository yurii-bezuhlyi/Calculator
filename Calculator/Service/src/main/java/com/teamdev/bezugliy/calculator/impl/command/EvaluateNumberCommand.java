package com.teamdev.bezugliy.calculator.impl.command;

import com.teamdev.bezugliy.calculator.impl.EvaluationContext;

import java.math.BigDecimal;

public class EvaluateNumberCommand implements EvaluationCommand {

    private final BigDecimal number;

    public EvaluateNumberCommand(BigDecimal number) {
        this.number = number;
    }

    @Override
    public void evaluate(EvaluationContext context) {
        context.pushOperand(number);
    }
}
