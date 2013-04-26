package com.teamdev.bezugliy.calculator.tests;

import com.teamdev.bezugliy.calculator.impl.EvaluationStateMachine;
import com.teamdev.bezugliy.calculator.impl.EvaluationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.JUnitCore;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class TestCalculator {
    private EvaluationStateMachine evaluationStateMachine;
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void createClass() {
        evaluationStateMachine = new EvaluationStateMachine();
    }
    @After
    public void deleteClass() {
        evaluationStateMachine = null;
    }

    @Test
    public void expressionWithBrackets() throws EvaluationException {
        assertEquals("Wrong handling of brackets", BigDecimal.valueOf(39), evaluationStateMachine.evaluate("3*(5+8)"));
    }
    @Test
    public void testExpectedException() throws EvaluationException {//Exception test
        exception.expect(EvaluationException.class);
        evaluationStateMachine.evaluate("3++3");
    }
    @Test
    public void getFunctionSum() throws EvaluationException {
        assertEquals("Wrong result in the function sum", BigDecimal.valueOf(60), evaluationStateMachine.evaluate("sum(23,37)"));
    }
    @Test
    public void getFunctionMax() throws EvaluationException {
        assertEquals("Wrong result in the function max", BigDecimal.valueOf(37), evaluationStateMachine.evaluate("max(23,37)"));
    }
    @Test
    public void getFunctionMin() throws EvaluationException {
        assertEquals("Wrong result in the function min", BigDecimal.valueOf(23), evaluationStateMachine.evaluate("min(23,37)"));
    }
    @Test
    public void getFunctionSqrt() throws EvaluationException {
        assertEquals("Wrong result in the function sqrt", BigDecimal.valueOf(4.5), evaluationStateMachine.evaluate("sqrt(20.25)"));
    }
    @Test
    public void getFunctionInFunction() throws EvaluationException {
        assertEquals("Error of function brackets", BigDecimal.valueOf(9), evaluationStateMachine.evaluate("sqrt(sum(23,3,4,5)+min(150,47,46,456))"));
    }
    @Test
     public void checkSpacesRemoving() throws EvaluationException {
        assertEquals("Error in removing spaces", BigDecimal.valueOf(6), evaluationStateMachine.evaluate("2   + 4"));
    }
    @Test
    public void getDivide() throws EvaluationException {
        assertEquals("Wrong result of dividing", BigDecimal.valueOf(0.5), evaluationStateMachine.evaluate("2   / 4"));
    }
    @Test
     public void getMultiply() throws EvaluationException {
        assertEquals("Wrong result of multiplying", BigDecimal.valueOf(8), evaluationStateMachine.evaluate("2   * 4"));
    }
    @Test
    public void getPower() throws EvaluationException {
        assertEquals("Wrong result of raising to a power", BigDecimal.valueOf(9765625), evaluationStateMachine.evaluate("25^5"));
    }
    @Test
    public void getMinus() throws EvaluationException {
        assertEquals("Wrong result of subtracting", BigDecimal.valueOf(-2), evaluationStateMachine.evaluate("2   - 4"));
    }
    @Test
    public void getRealNumber() throws EvaluationException {
        assertEquals("Error number with a point", BigDecimal.valueOf(752.065), evaluationStateMachine.evaluate("45.34+34.9*20.25"));
    }
    @Test
    public void getFunctionInsideBrackets() throws EvaluationException {
        assertEquals("Error function inside brackets", BigDecimal.valueOf(935.0), evaluationStateMachine.evaluate("34*(23+sqrt(20.25))"));
    }
    @Test
    public void getBracketsInsideFunction() throws EvaluationException {
        assertEquals("Error brackets inside function", BigDecimal.valueOf(9), evaluationStateMachine.evaluate("sqrt((23+4)*3)"));
    }
    @Test
    public void checkNegativeNumber() throws EvaluationException {
        assertEquals("Error with minus", BigDecimal.valueOf(2), evaluationStateMachine.evaluate("5+-3"));
    }
    public static void main(String[] args) {
        JUnitCore core = new JUnitCore();
        core.addListener(new CalcListener());
        core.run(TestCalculator.class);
    }
}
