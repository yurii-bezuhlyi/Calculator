package com.teamdev.bezugliy.calculator.tests;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

class CalcListener extends RunListener {
    @Override
    public void testFailure(Failure fail) {
        System.out.println("Failed:" + fail.getDescription().getDisplayName() + " [" + fail.getMessage() + "]");
    }
    @Override
    public void testStarted(Description desc) {
        System.out.println("Started:" + desc.getDisplayName());
    }

    @Override
    public void testFinished(Description desc) {
        System.out.println("Finished:" + desc.getDisplayName());
    }
}
