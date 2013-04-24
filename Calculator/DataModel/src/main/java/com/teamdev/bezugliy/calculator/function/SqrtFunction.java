package com.teamdev.bezugliy.calculator.function;

import com.teamdev.bezugliy.calculator.Function;

import java.math.BigDecimal;
import java.util.ArrayList;

public class SqrtFunction implements Function {
    @Override
    public BigDecimal evaluate(ArrayList<BigDecimal> arguments) {
        if (arguments.size() > 1 || arguments.size() == 0)
            throw new IllegalArgumentException("Must be only one argument");
        double d = arguments.get(0).doubleValue();
        return new BigDecimal(Math.sqrt(d));
    }
}
