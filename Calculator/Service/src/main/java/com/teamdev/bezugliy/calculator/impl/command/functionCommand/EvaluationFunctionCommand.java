package com.teamdev.bezugliy.calculator.impl.command.functionCommand;

import com.teamdev.bezugliy.calculator.Function;
import com.teamdev.bezugliy.calculator.function.FunctionFactory;
import com.teamdev.bezugliy.calculator.impl.EvaluationContext;
import com.teamdev.bezugliy.calculator.impl.command.EvaluationCommand;

public class EvaluationFunctionCommand implements EvaluationCommand {
    private final String representation;

    public EvaluationFunctionCommand(String representation) {
        this.representation = representation;
    }
    @Override
    public void evaluate(EvaluationContext context) {
        context.pushFunction(representation);
    }
}
