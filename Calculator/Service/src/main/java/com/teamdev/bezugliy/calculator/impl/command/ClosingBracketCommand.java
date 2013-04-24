package com.teamdev.bezugliy.calculator.impl.command;

import com.teamdev.bezugliy.calculator.impl.EvaluationContext;

public class ClosingBracketCommand implements EvaluationCommand {

    @Override
    public void evaluate(EvaluationContext context) {
        context.pushClosingBracket();
    }
}
