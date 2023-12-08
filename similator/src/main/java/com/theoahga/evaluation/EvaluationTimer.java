package com.theoahga.evaluation;

import java.util.Timer;

public class EvaluationTimer {
  private final int interfaceMillisSecond;

  public EvaluationTimer(int intervalSecond) {
    this.interfaceMillisSecond = intervalSecond * 1000;
  }

  public void start() {
    Timer timer = new Timer();
    timer.scheduleAtFixedRate(new EvaluationTask(), 0, interfaceMillisSecond);
  }
}
