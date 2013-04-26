package com.teamdev.bezugliy.calculator.impl;


public class ElementOfFunctionStack {
    public ElementOfFunctionStack(String functionName,int numberOfFunctionArguments,
                                  int functionPositionInExpression, int numberOperatorsBeforeFunction) {
        this.functionName = functionName;
        this.numberOfFunctionArguments = numberOfFunctionArguments;
        this.functionPositionInExpression = functionPositionInExpression;
        this.numberOperatorsBeforeFunction = numberOperatorsBeforeFunction;
    }
    final private String functionName;
    private int numberOfFunctionArguments;
    final private int functionPositionInExpression;
    final private int numberOperatorsBeforeFunction;

    public void incNumberOfFunctionArguments() {
        this.numberOfFunctionArguments ++;
    }
    public String getFunctionName() {
        return  this.functionName;
    }
    public int getNumberOfFunctionArguments() {
        return  this.numberOfFunctionArguments;
    }
    public int getFunctionPositionInExpression() {
        return  this.functionPositionInExpression;
    }
    public int getNumberOperatorsBeforeFunction() {
        return  this.numberOperatorsBeforeFunction;
    }
}
