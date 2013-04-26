package com.teamdev.bezugliy.calculator.impl.parser;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParsePosition;
import com.teamdev.bezugliy.calculator.impl.MathExpressionReader;
import com.teamdev.bezugliy.calculator.impl.command.EvaluateNumberCommand;
import com.teamdev.bezugliy.calculator.impl.command.EvaluationCommand;

public class NumberParser implements MathExpressionParser {

    /*private static final NumberFormat NUMBER_FORMAT =
            new DecimalFormat("0.0"); */

    @Override
    public EvaluationCommand parse(MathExpressionReader reader) {

        char tmp = reader.getCurrentChar();
        StringBuffer numberTmp = new StringBuffer();
        if (tmp == '-' && !reader.endOfExpression()) {
            numberTmp.append(tmp);
            reader.incReadPosition();
            if (reader.outExpression())
                return null;
            tmp = reader.getCurrentChar();
        }
        if (tmp >= '0' && tmp <= '9') {
            numberTmp.append(tmp);
            reader.incReadPosition();
            while (!reader.outExpression()) {
                tmp = reader.getCurrentChar();
                if (tmp != '.' && (tmp < '0' || tmp > '9'))
                    break;
                numberTmp.append(tmp);
                reader.incReadPosition();
            }
            if (tmp == '.')
                numberTmp.append('0');
            return new EvaluateNumberCommand(
                    new BigDecimal(numberTmp.toString()));
        }
        return null;
    }
}
