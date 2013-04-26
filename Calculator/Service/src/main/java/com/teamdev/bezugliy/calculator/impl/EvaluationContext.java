package com.teamdev.bezugliy.calculator.impl;

import com.teamdev.bezugliy.calculator.BinaryOperator;
import com.teamdev.bezugliy.calculator.Function;
import com.teamdev.bezugliy.calculator.function.FunctionFactory;
import com.teamdev.bezugliy.calculator.statemachine.StateMachineContext;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.util.*;

import static com.teamdev.bezugliy.calculator.impl.EvaluationState.START;

public class EvaluationContext implements StateMachineContext<
        EvaluationState, BigDecimal> {

    private EvaluationState state;
    private final MathExpressionReader expressionReader;

    private final Deque<BigDecimal> operandStack = new ArrayDeque<>();
    private final Deque<BinaryOperator> operatorStack = new ArrayDeque<>();
    private final Deque<ElementOfBracketStack> bracketStack = new ArrayDeque<>();
    private final Deque<ElementOfFunctionStack> functionStack = new ArrayDeque<>();

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

            if ((bracketStack.size() != 0 && operatorStack.size() <= bracketStack.peek().getNumberOperatorsBeforeBracket()) ||
                    functionStack.size() != 0 && operatorStack.size() <= functionStack.peek().getNumberOperatorsBeforeFunction())
                break;

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

            if ((bracketStack.size() != 0 && operatorStack.size() <= bracketStack.peek().getNumberOperatorsBeforeBracket()) ||
                    (functionStack.size() != 0 && operatorStack.size() <= functionStack.peek().getNumberOperatorsBeforeFunction()))
                break;
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
    public void pushFunction(String functionName) {
        //1-st parameter - function name, 2-nd - number of function arguments,
        //3-rd - function position in expression, 4-th - operators stack size
        functionStack.push(new ElementOfFunctionStack(functionName,0,getExpressionReader().getReadPosition(),operatorStack.size()));
    }
    public void incCountFunctionStackLastOperators(){
        if (operandStack.size() == 0) {
            return;
        }
        ElementOfFunctionStack elementOfFunctionStack = functionStack.pop();
        elementOfFunctionStack.incNumberOfFunctionArguments();
        functionStack.push(elementOfFunctionStack);
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
    public boolean insertComma() {
        if (getFunctionStackSize() > 0) {
            calculateAfterComma();
            incCountFunctionStackLastOperators();
            getExpressionReader().incReadPosition();
            setState(START);
            return true;
        }
        return false;
    }

    public void pushOpeningBracket() {
        bracketStack.push(new ElementOfBracketStack(operatorStack.size(),getExpressionReader().getReadPosition()));
    }


    public void pushClosingBracket() {
        if ((functionStack.size() > 0 && bracketStack.size() > 0 && functionStack.peek().getFunctionPositionInExpression() < bracketStack.peek().getBracketPositionInExpression())
                || functionStack.size() == 0) {

            final int operatorStackSize = bracketStack.pop().getNumberOperatorsBeforeBracket();
            while (operatorStack.size() > operatorStackSize) {
                executeBinaryOperator(operatorStack.pop());
            }
        }
        else {
            incCountFunctionStackLastOperators();
            calculate();
            final ElementOfFunctionStack elementOfFunctionStack = functionStack.pop();
            int i = elementOfFunctionStack.getNumberOfFunctionArguments();
            ArrayList<BigDecimal> functionArguments = new ArrayList<>();

            while (i > 0) {
                functionArguments.add(operandStack.pop());
                i--;
            }
            final String functionName = elementOfFunctionStack.getFunctionName();
            Function function = new FunctionFactory().createFunction(functionName);
            final BigDecimal result = function.evaluate(functionArguments);

            pushOperand(result);
        }
    }

    @Override
    public BigDecimal getResult() {
        return operandStack.pop();
    }
}