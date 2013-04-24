package com.teamdev.bezugliy.calculator.function;


import com.teamdev.bezugliy.calculator.Function;

import java.math.BigDecimal;
import java.util.ArrayList;

public class MinFunction implements Function {
    @Override
    public BigDecimal evaluate(ArrayList<BigDecimal> arguments) {
        if (arguments.size() == 0)
            throw new IllegalArgumentException("Function without arguments");
        BigDecimal min = arguments.get(0);
        for (int i = 1; i < arguments.size(); i++) {
            if (arguments.get(i).compareTo(min) == -1 ) {
                min = arguments.get(i);
            }
        }
        return  min;
    }
}
