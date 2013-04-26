package com.teamdev.bezugliy.calculator.tests;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import java.util.logging.Logger;

class CalcListener extends RunListener {
    private static Logger log = Logger.getLogger(CalcListener.class.getName());
    @Override
    public void testFailure(Failure fail) {
        log.info("Failed:" + fail.getDescription().getDisplayName() + " [" + fail.getMessage() + "]");
    }
    @Override
    public void testStarted(Description desc) {
        log.info("Started:" + desc.getDisplayName());
    }

    @Override
    public void testFinished(Description desc) {
        log.info("Finished:" + desc.getDisplayName());
    }
}
