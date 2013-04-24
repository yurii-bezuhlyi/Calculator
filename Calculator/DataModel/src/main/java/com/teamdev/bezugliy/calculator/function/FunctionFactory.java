package com.teamdev.bezugliy.calculator.function;

import com.teamdev.bezugliy.calculator.Function;

public class FunctionFactory {
    public Function createFunction(String representation) {

        switch (representation) {
            case "max":
                return new MaxFunction();
            case "min":
                return new MinFunction();
            case "sqrt":
                return new SqrtFunction();
            case "sum":
                return new SumFunction();
            default:
                return null;
        }
    }
}
