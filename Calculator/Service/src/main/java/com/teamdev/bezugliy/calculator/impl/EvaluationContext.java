package com.teamdev.bezugliy.calculator.impl;

import com.teamdev.bezugliy.calculator.BinaryOperator;
import com.teamdev.bezugliy.calculator.Function;
import com.teamdev.bezugliy.calculator.function.FunctionFactory;
import com.teamdev.bezugliy.calculator.statemachine.StateMachineContext;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.util.*;

public class EvaluationContext implements StateMachineContext<
        EvaluationState, BigDecimal> {

    private EvaluationState state;
    private final MathExpressionReader expressionReader;

    private final Deque<BigDecimal> operandStack = new ArrayDeque<>();
    private final Deque<BinaryOperator> operatorStack = new ArrayDeque<>();
    private final Deque<List<Integer>> bracketStack = new ArrayDeque<>();
    private final Deque<NavigableMap<String,List<Integer>>> functionStack = new ArrayDeque<>();

    public EvaluationContext(String expression) {
        expressionReader = new MathExpressionReader(expression);
    }

    public MathExpressionReader getExpressionReader() {
        return expressionReader;
    }

    @Override
    public EvaluationState getState() {
        return state;
    }

    @Override
    public void setState(EvaluationState state) {
        this.state = state;
    }

    public void pushOperand(BigDecimal operand) {
        operandStack.push(operand);
    }

    public int getFunctionStackSize() {
        return functionStack.size();
    }

    public void calculate() {
        BinaryOperator topOperator = null;
        do {
            topOperator = operatorStack.peek();

            if ((bracketStack.size() != 0 && operatorStack.size() <= bracketStack.peek().get(0)) ||
                    functionStack.size() != 0 && operatorStack.size() <= functionStack.peek().firstEntry().getValue().get(0))
                break;                           //example: <"sum", 3, 1>  sum - function, 3 - operatorStack size, 1 - number of function arguments

            if (topOperator != null) {
                executeBinaryOperator(topOperator);
                operatorStack.pop();
            }

        } while (topOperator != null);
    }
    public void calculateAfterComma() {
        if (getExpressionReader().getExpression().charAt(getExpressionReader().getReadPosition()-1) != ')')
        {
            calculate();
        }
    }

    public void pushBinaryOperator(BinaryOperator binaryOperator) {
        BinaryOperator topOperator = null;
        do {
            topOperator = operatorStack.peek();

            if ((bracketStack.size() != 0 && operatorStack.size() <= bracketStack.peek().get(0)) ||
                    (functionStack.size() != 0 && operatorStack.size() <= functionStack.peek().firstEntry().getValue().get(0)))
                break;                           //example: <"sum", 3, 1>  sum - function, 3 - operatorStack size, 1 - number of function arguments
            if (topOperator != null) {

                if (binaryOperator.compareTo(topOperator) < 1) {
                    executeBinaryOperator(topOperator);
                    operatorStack.pop();
                } else {
                    break;
                }
            }

        } while (topOperator != null);


        operatorStack.push(binaryOperator);
    }
    public void pushFunction(String function/*Function function*/) {
        NavigableMap</*Function*/String,List<Integer>> nm = new TreeMap<String/*Function*/,List<Integer>>();
        List<Integer> l = new ArrayList<Integer>();
        l.add(0,operatorStack.size());
        l.add(1,0);
        l.add(2,getExpressionReader().getReadPosition());
        nm.put(function,l);
        functionStack.push(nm);
    }
    public void incCountFunctionStackLastOperators(){
        if (operandStack.size() == 0) {
            return;
        }
        NavigableMap<String,List<Integer>> nm = functionStack.pop();
        List<Integer> l = nm.get(nm.firstKey());
        int i = l.get(1);
        i ++;
        l.add(1,i);
        nm.put(nm.firstKey(),l);
        functionStack.push(nm);
    }

    private void executeBinaryOperator(BinaryOperator topOperator) {
        final BigDecimal rightOperand = operandStack.pop();
        final BigDecimal leftOperand = operandStack.pop();

        final BigDecimal result = topOperator.evaluate(
                leftOperand, rightOperand);

        pushOperand(result);
    }

    public void popBinaryOperators() {
        while (!operatorStack.isEmpty()) {
            executeBinaryOperator(operatorStack.pop());
        }
    }

    public void pushOpeningBracket() {
        List<Integer> l = new ArrayList<Integer>();
        l.add(0,operatorStack.size());
        l.add(1,getExpressionReader().getReadPosition());
        bracketStack.push(l);
    }


    public void pushClosingBracket() {
        if ((functionStack.size() > 0 && bracketStack.size() > 0 && functionStack.peek().firstEntry().getValue().get(2) < bracketStack.peek().get(1))
                || functionStack.size() == 0) {

            final int operatorStackSize = bracketStack.pop().get(0);
            while (operatorStack.size() > operatorStackSize) {
                executeBinaryOperator(operatorStack.pop());
            }
        }
        else {
            incCountFunctionStackLastOperators();
            calculate();
            final NavigableMap</*Function*/String,List<Integer>> nm = functionStack.pop();
            final List<Integer> l = nm.get(nm.firstKey());
            int i = l.get(1);
            ArrayList<BigDecimal> ss = new ArrayList<>();

            while (i > 0) {
                ss.add(operandStack.pop());
                i--;
            }
            String function = nm.firstKey();
            Function f = new FunctionFactory().createFunction(function);
            final BigDecimal result = f.evaluate(ss);

            pushOperand(result);
        }
    }

    @Override
    public BigDecimal getResult() {
        return operandStack.pop();
    }
}