package com.teamdev.bezugliy.calculator.impl.parser;
import com.teamdev.bezugliy.calculator.impl.MathExpressionReader;
import com.teamdev.bezugliy.calculator.impl.command.EvaluationCommand;
import com.teamdev.bezugliy.calculator.impl.command.OpeningBracketCommand;

public class OpeningBracketParser implements MathExpressionParser {

    @Override
    public EvaluationCommand parse(MathExpressionReader reader) {

        if (reader.endOfExpression()) {
            return null;
        }

        if (reader.getCurrentChar() == '(') {
            reader.incReadPosition();
            return new OpeningBracketCommand();
        }

        return null;
    }
}
