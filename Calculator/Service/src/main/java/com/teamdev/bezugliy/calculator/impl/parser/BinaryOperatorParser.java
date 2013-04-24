package com.teamdev.bezugliy.calculator.impl.parser;

import com.teamdev.bezugliy.calculator.BinaryOperator;
import com.teamdev.bezugliy.calculator.impl.MathExpressionReader;
import com.teamdev.bezugliy.calculator.impl.command.EvaluateBinaryOperatorCommand;
import com.teamdev.bezugliy.calculator.impl.command.EvaluationCommand;
import com.teamdev.bezugliy.calculator.operator.BinaryOperatorFactory;

public class BinaryOperatorParser implements MathExpressionParser {

    private final BinaryOperatorFactory factory =
            new BinaryOperatorFactory();

    @Override
    public EvaluationCommand parse(MathExpressionReader reader) {

        if (reader.endOfExpression()) {
            return null;
        }

        final char representation = reader.getCurrentChar();

        final BinaryOperator binaryOperator = factory.createBinaryOperator(
                representation);

        if (binaryOperator != null) {
            reader.incReadPosition();
            return new EvaluateBinaryOperatorCommand(binaryOperator);
        }

        return null;
    }
}
