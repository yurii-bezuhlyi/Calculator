package com.teamdev.bezugliy.calculator.impl;


public class ElementOfBracketStack {
    public ElementOfBracketStack(int numberOperatorsBeforeBracket, int bracketPositionInExpression) {
        this.numberOperatorsBeforeBracket = numberOperatorsBeforeBracket;
        this.bracketPositionInExpression = bracketPositionInExpression;
    }
    final private int numberOperatorsBeforeBracket;
    final private int bracketPositionInExpression;

    public int getNumberOperatorsBeforeBracket() {
        return this.numberOperatorsBeforeBracket;
    }
    public int getBracketPositionInExpression() {
        return this.bracketPositionInExpression;
    }
}
