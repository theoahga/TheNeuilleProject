package com.theoahga;

import com.theoahga.evaluation.EvaluationTimer;
;
import com.theoahga.exception.ZeroException;
import com.theoahga.utils.PropertyUtils;

import java.io.IOException;

public class Simulator {
  public static void main(String[] args) throws IOException, InterruptedException, ZeroException {
    PropertyUtils.load();

    EvaluationTimer evaluationTimer = new EvaluationTimer(Integer.parseInt(System.getProperty("evaluation.interval.second")));
    evaluationTimer.start();
  }
}
