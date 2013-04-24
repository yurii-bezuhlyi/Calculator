package com.teamdev.bezugliy.calculator;

import java.math.BigDecimal;
import java.util.ArrayList;

public interface Function {
    BigDecimal evaluate(ArrayList<BigDecimal> arguments/*BigDecimal... arguments*/);
}