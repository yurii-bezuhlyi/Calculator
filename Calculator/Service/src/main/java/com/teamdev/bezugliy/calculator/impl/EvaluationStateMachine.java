package com.teamdev.bezugliy.calculator.impl;

import com.teamdev.bezugliy.calculator.statemachine.AbstractStateMachine;

import java.math.BigDecimal;

public class EvaluationStateMachine extends AbstractStateMachine<
        BigDecimal,
        EvaluationContext,
        EvaluationState,
        EvaluationMatrix,
        EvaluationService,
        EvaluationException> {

    private final EvaluationMatrix matrix = new EvaluationMatrix();

    private final EvaluationService evaluationService = new EvaluationService();

    @Override
    protected EvaluationMatrix getTransitionMatrix() {
        return matrix;
    }

    @Override
    protected EvaluationService getStateRecognizer() {
        return evaluationService;
    }

    @Override
    protected void deadlock(EvaluationContext context) throws EvaluationException {
        final int position = context.getExpressionReader().
                getReadPosition();

        System.out.println("Deadlock at state: " + context.getState());

        System.out.println("Parse position: " + position);

        throw new EvaluationException("Invalid expression format.",
                position);
    }

    public BigDecimal evaluate(String mathExpression)
            throws EvaluationException {
        return run(new EvaluationContext(mathExpression));
    }
}
