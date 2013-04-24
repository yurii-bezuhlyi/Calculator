package com.teamdev.bezugliy.calculator.impl;

public enum EvaluationState {
    START,
    NUMBER,
    BINARY_OPERATOR,
    OPENING_BRACKET,
    CLOSING_BRACKET,
    FUNCTION,
    FINISH
}
