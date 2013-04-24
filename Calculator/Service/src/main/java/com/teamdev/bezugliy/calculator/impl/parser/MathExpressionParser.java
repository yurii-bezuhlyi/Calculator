package com.teamdev.bezugliy.calculator.impl.parser;

import com.teamdev.bezugliy.calculator.impl.MathExpressionReader;
import com.teamdev.bezugliy.calculator.impl.command.EvaluationCommand;

public interface MathExpressionParser {
    EvaluationCommand parse(MathExpressionReader reader);
}
