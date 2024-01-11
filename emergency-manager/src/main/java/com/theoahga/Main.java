package com.theoahga;

import com.theoahga.evaluation.EvaluationTimer;
import com.theoahga.utils.PropertyUtils;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        PropertyUtils.load();

        EvaluationTimer evaluationTimer = new EvaluationTimer(Integer.parseInt(System.getProperty("evaluation.interval.second")));
        evaluationTimer.start();
    }
}