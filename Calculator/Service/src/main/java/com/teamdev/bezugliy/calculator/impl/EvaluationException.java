package com.teamdev.bezugliy.calculator.impl;

public class EvaluationException extends Exception {

    private int errorPosition;

    public EvaluationException(String message, int errorPosition) {
        super(message);
        this.errorPosition = errorPosition;
    }

    public int getErrorPosition() {
        return errorPosition;
    }
}
