package com.teamdev.bezugliy.calculator.function;


import com.teamdev.bezugliy.calculator.Function;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

public class MaxFunction implements Function {
    @Override
    public BigDecimal evaluate(ArrayList<BigDecimal> arguments) {
        if (arguments.size() < 2)
            throw new IllegalArgumentException("Arguments must be at least two");
        BigDecimal max = arguments.get(0);
        for (int i = 1; i < arguments.size(); i++) {
            if (arguments.get(i).compareTo(max) == 1 ) {
                max = arguments.get(i);
            }
        }
        return  max;
    }
}
