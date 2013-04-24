package com.teamdev.bezugliy.calculator.impl.command;

import com.teamdev.bezugliy.calculator.impl.EvaluationContext;

public interface EvaluationCommand {
    void evaluate(EvaluationContext context);
}
