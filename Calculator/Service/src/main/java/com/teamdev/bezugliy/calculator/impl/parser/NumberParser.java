package com.teamdev.bezugliy.calculator.impl.parser;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParsePosition;
import com.teamdev.bezugliy.calculator.impl.MathExpressionReader;
import com.teamdev.bezugliy.calculator.impl.command.EvaluateNumberCommand;
import com.teamdev.bezugliy.calculator.impl.command.EvaluationCommand;

public class NumberParser implements MathExpressionParser {

    private static final NumberFormat NUMBER_FORMAT =
            new DecimalFormat("0.0");

    @Override
    public EvaluationCommand parse(MathExpressionReader reader) {

        /*final ParsePosition position =
                new ParsePosition(reader.getReadPosition());

        final Number number =
                NUMBER_FORMAT.parse(reader.getExpression(), position);

        if (position.getErrorIndex() < 0) {

            final int readPosition = position.getIndex();
            reader.setReadPosition(readPosition);

            return new EvaluateNumberCommand(
                    new BigDecimal(number.doubleValue()));
        }*/
        /*final ParsePosition position =
                new ParsePosition(reader.getReadPosition());

        final Number number =
                NUMBER_FORMAT.parse(reader.getExpression(), position); */
        char tmp = reader.getCurrentChar();
        if (tmp >= '0' && tmp <= '9') {
            String numberTmp = new String();
            numberTmp += tmp;

            reader.incReadPosition();
            while (!reader.outExpression()) {
                tmp = reader.getCurrentChar();
                if (tmp != '.' && (tmp < '0' || tmp > '9'))
                    break;
                numberTmp += tmp;
                reader.incReadPosition();
            }
            if (tmp == '.')
                numberTmp += '0';
            return new EvaluateNumberCommand(
                    new BigDecimal(numberTmp/*number.doubleValue()*/));
        }

        return null;
    }
}
