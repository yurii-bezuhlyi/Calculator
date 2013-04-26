package com.teamdev.bezugliy.calculator.impl.parser.functionParser;

import com.teamdev.bezugliy.calculator.impl.MathExpressionReader;
import com.teamdev.bezugliy.calculator.impl.command.EvaluationCommand;
import com.teamdev.bezugliy.calculator.impl.command.functionCommand.EvaluationFunctionCommand;
import com.teamdev.bezugliy.calculator.impl.parser.MathExpressionParser;

public class FunctionParser implements MathExpressionParser {
    @Override
    public EvaluationCommand parse(MathExpressionReader reader) {
        char tmp = reader.getCurrentChar();
        if (tmp >= 'A' && tmp <= 'z') {
            StringBuffer functionName = new StringBuffer();
            functionName.append(tmp);
            reader.incReadPosition();
            while (!reader.endOfExpression()) {
                tmp = reader.getCurrentChar();
                if (tmp < 'A' || tmp > 'z') {
                    break;
                }
                functionName.append(tmp);
                reader.incReadPosition();
            }
            if (reader.endOfExpression()) {
                return null;
            }
            if (reader.getCurrentChar() == '(') {
                reader.incReadPosition();
                switch (functionName.toString().toLowerCase()){
                    case "min" :
                    case "max" :
                    case "sqrt" :
                    case "sum" :
                        return new EvaluationFunctionCommand(functionName.toString().toLowerCase());
                    default:
                        return null;
                }
            }
            else {
                return null;
            }
        }
        return null;
    }
}
