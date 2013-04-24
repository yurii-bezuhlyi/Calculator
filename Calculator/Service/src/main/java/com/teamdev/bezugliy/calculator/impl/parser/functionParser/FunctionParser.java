package com.teamdev.bezugliy.calculator.impl.parser.functionParser;

import com.teamdev.bezugliy.calculator.impl.MathExpressionReader;
import com.teamdev.bezugliy.calculator.impl.command.EvaluationCommand;
import com.teamdev.bezugliy.calculator.impl.command.functionCommand.EvaluationFunctionCommand;
import com.teamdev.bezugliy.calculator.impl.parser.MathExpressionParser;

public class FunctionParser implements MathExpressionParser {
    @Override
    public EvaluationCommand parse(MathExpressionReader reader) {
        char tmp = reader.getCurrentChar();
        int i = 0;
        if (tmp >= 'A' && tmp <= 'z') {
            String funcTmp = new String();
            funcTmp += tmp;
            i = reader.getReadPosition();
            while (true) {
                tmp = reader.getExpression().charAt(++i);
                if (tmp >= 'A' && tmp <= 'z') {
                    funcTmp += tmp;
                }
                else {
                    break;
                }
            }
            if (reader.getExpression().charAt(i) == '(') {
                reader.setReadPosition(++i);
                funcTmp = funcTmp.toLowerCase();
                switch (funcTmp){
                    case "min" :
                    case "max" :
                    case "sqrt" :
                    case "sum" :
                        return new EvaluationFunctionCommand(funcTmp);
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
