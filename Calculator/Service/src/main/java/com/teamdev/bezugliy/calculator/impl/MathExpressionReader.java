package com.teamdev.bezugliy.calculator.impl;

public class MathExpressionReader {

    private final String expression;
    private int readPosition = 0;

    public MathExpressionReader(String expression) {

        if (expression == null) {
            throw new NullPointerException("Null expression passed.");
        }

        if (expression.trim().length() == 0) {
            throw new IllegalArgumentException("Empty expression parsed.");
        }

        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }

    public int getReadPosition() {
        return readPosition;
    }

    public char getCurrentChar() {
        return expression.charAt(readPosition);
    }

    public void incReadPosition() {
        readPosition++;
    }

    public void setReadPosition(int readPosition) {
        this.readPosition = readPosition;
    }

    public boolean endOfExpression() {
        return readPosition + 1 >= expression.length();
    }
    public boolean outExpression() {
        return readPosition >= expression.length();
    }
    public void skipSpaces() {
        while (readPosition < expression.length()) {
            if (expression.charAt(readPosition) != ' ' && expression.charAt(readPosition) != '\t') {
                break;
            }
            incReadPosition();
        }
    }
}
