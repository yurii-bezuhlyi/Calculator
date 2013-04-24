package com.teamdev.bezugliy.calculator.impl.parser;

import com.teamdev.bezugliy.calculator.impl.MathExpressionReader;
import com.teamdev.bezugliy.calculator.impl.command.ClosingBracketCommand;
import com.teamdev.bezugliy.calculator.impl.command.EvaluationCommand;

public class ClosingBracketParser implements MathExpressionParser {

    @Override
    public EvaluationCommand parse(MathExpressionReader reader) {

        /*if (reader.endOfExpression()) {
            return null;
        }*/

        if (reader.getCurrentChar() == ')') {
            reader.incReadPosition();
            return new ClosingBracketCommand();
        }

        return null;
    }
}
