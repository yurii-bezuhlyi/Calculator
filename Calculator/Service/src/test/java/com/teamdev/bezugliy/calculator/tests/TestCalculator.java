package com.teamdev.bezugliy.calculator.tests;

import com.teamdev.bezugliy.calculator.impl.Calculator;
import com.teamdev.bezugliy.calculator.impl.EvaluationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.JUnitCore;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class TestCalculator {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void getBracketsTest() throws EvaluationException {
        Calculator c = new Calculator();
        assertEquals("Wrong handling of brackets", BigDecimal.valueOf(39), c.evaluate("3*(5+8)"));
    }
    @Test
    public void getDeadlockTest() throws EvaluationException {//Exception test
        exception.expect(EvaluationException.class);
        Calculator c = new Calculator();
        c.evaluate("3++3");
    }
    @Test
    public void getFunctionSumTest() throws EvaluationException {
        Calculator c = new Calculator();
        assertEquals("Bug in the function sum", BigDecimal.valueOf(60), c.evaluate("sum(23,37)"));
    }
    @Test
    public void getFunctionMaxTest() throws EvaluationException {
        Calculator c = new Calculator();
        assertEquals("Bug in the function max", BigDecimal.valueOf(37), c.evaluate("max(23,37)"));
    }
    @Test
    public void getFunctionMinTest() throws EvaluationException {
        Calculator c = new Calculator();
        assertEquals("Bug in the function min", BigDecimal.valueOf(23), c.evaluate("min(23,37)"));
    }
    @Test
    public void getFunctionSqrtTest() throws EvaluationException {
        Calculator c = new Calculator();
        assertEquals("Bug in the function sqrt", BigDecimal.valueOf(4.5), c.evaluate("sqrt(20.25)"));
    }
    @Test
    public void getFunctionInFunctionTest() throws EvaluationException {
        Calculator c = new Calculator();
        assertEquals("Error of function brackets", BigDecimal.valueOf(9), c.evaluate("sqrt(sum(23,3,4,5)+min(150,47,46,456))"));
    }
    @Test
     public void getSpacesTest() throws EvaluationException {
        Calculator c = new Calculator();
        assertEquals("Error in removing spaces", BigDecimal.valueOf(6), c.evaluate("2   + 4"));
    }
    @Test
    public void getDivideTest() throws EvaluationException {
        Calculator c = new Calculator();
        assertEquals("Error divide", BigDecimal.valueOf(0.5), c.evaluate("2   / 4"));
    }
    @Test
     public void getMultiplyTest() throws EvaluationException {
        Calculator c = new Calculator();
        assertEquals("Error multiply", BigDecimal.valueOf(8), c.evaluate("2   * 4"));
    }
    @Test
    public void getMinusTest() throws EvaluationException {
        Calculator c = new Calculator();
        assertEquals("Error multiply", BigDecimal.valueOf(-2), c.evaluate("2   - 4"));
    }
    @Test
    public void getRealNumberTest() throws EvaluationException {
        Calculator c = new Calculator();
        assertEquals("Error number with a point", BigDecimal.valueOf(752.065), c.evaluate("45.34+34.9*20.25"));
    }
    @Test
    public void getFunctionInsideBracketsTest() throws EvaluationException {
        Calculator c = new Calculator();
        assertEquals("Error function inside brackets", BigDecimal.valueOf(935), c.evaluate("34*(23+sqrt(20.25))"));
    }
    @Test
    public void getBracketsInsideFunctionTest() throws EvaluationException {
        Calculator c = new Calculator();
        assertEquals("Error brackets inside function", BigDecimal.valueOf(9), c.evaluate("sqrt((23+4)*3)"));
    }
    public static void main(String[] args) {
        JUnitCore core = new JUnitCore();
        core.addListener(new CalcListener());
        core.run(TestCalculator.class);
    }
}
