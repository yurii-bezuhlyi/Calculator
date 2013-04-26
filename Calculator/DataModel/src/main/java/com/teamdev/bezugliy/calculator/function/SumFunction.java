package com.teamdev.bezugliy.calculator.function;


import com.teamdev.bezugliy.calculator.Function;

import java.math.BigDecimal;
import java.util.ArrayList;

public class SumFunction implements Function {
    @Override
    public BigDecimal evaluate(ArrayList<BigDecimal> arguments) {
        if (arguments.size() < 2)
            throw new IllegalArgumentException("Arguments must be at least two");
        //BigDecimal sumResult = new BigDecimal(0);
        double summa = 0;
        for (BigDecimal argument : arguments) {
            summa += argument.doubleValue();
            //sumResult.add(new BigDecimal(argument.doubleValue()));
        }
        return new BigDecimal(summa);
    }
}
