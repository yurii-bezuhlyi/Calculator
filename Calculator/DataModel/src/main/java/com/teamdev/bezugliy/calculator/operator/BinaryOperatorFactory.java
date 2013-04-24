package com.teamdev.bezugliy.calculator.operator;

import com.teamdev.bezugliy.calculator.BinaryOperator;
import static com.teamdev.bezugliy.calculator.operator.AbstractBinaryOperator.Priority.*;

public final class BinaryOperatorFactory {

    public BinaryOperator createBinaryOperator(char representation) {

        switch (representation) {
            case '+':
                return new PlusBinaryOperator(LOW);
            case '-':
                return new MinusBinaryOperator(LOW);
            case '*':
                return new MultiplyBinaryOperator(MEDIUM);
            case '/':
                return new DivideBinaryOperator(MEDIUM);
            case '^':
                return new PowerBinaryOperator(HIGH);

            default:
                return null;
        }
    }
}
