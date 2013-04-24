package com.teamdev.bezugliy.calculator.impl;

import java.util.EnumMap;
import java.util.Map;

import com.teamdev.bezugliy.calculator.impl.command.EvaluationCommand;
import com.teamdev.bezugliy.calculator.impl.parser.functionParser.FunctionParser;
import com.teamdev.bezugliy.calculator.statemachine.StateRecognizer;
import com.teamdev.bezugliy.calculator.impl.parser.*;
import static com.teamdev.bezugliy.calculator.impl.EvaluationState.*;

public class EvaluationService implements
        StateRecognizer<EvaluationState, EvaluationContext> {

    private Map<EvaluationState, MathExpressionParser> parserRegistry =
            new EnumMap<EvaluationState, MathExpressionParser>
                    (EvaluationState.class) {{
                    put(NUMBER, new NumberParser());
                    put(BINARY_OPERATOR, new BinaryOperatorParser());
                    put(OPENING_BRACKET, new OpeningBracketParser());
                    put(CLOSING_BRACKET, new ClosingBracketParser());
                    put(FUNCTION, new FunctionParser());
                    put(FINISH, new EndOfExpressionParser());
                }};


    @Override
    public boolean accept(EvaluationState possibleState,
                          EvaluationContext context) {

        context.getExpressionReader().skipSpaces();
        if (!context.getExpressionReader().outExpression() && context.getExpressionReader().getCurrentChar() == ',') {
            if (context.getFunctionStackSize() > 0) {
                context.calculateAfterComma();
                context.incCountFunctionStackLastOperators();
                context.getExpressionReader().incReadPosition();
                context.setState(START);
                return true;
            }
            return false;
        }

        final MathExpressionParser parser = parserRegistry.get(possibleState);

        if (parser == null) {
            throw new IllegalStateException(
                    "Parser not found for state: " + possibleState);
        }
        if (context.getExpressionReader().outExpression() && possibleState != FINISH)
            return false;
        final EvaluationCommand command =
                parser.parse(context.getExpressionReader());

        if (command == null) {
            return false;
        }

        command.evaluate(context);

        return true;
    }
}