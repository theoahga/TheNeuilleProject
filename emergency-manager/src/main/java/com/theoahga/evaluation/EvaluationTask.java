package com.theoahga.evaluation;

import java.util.TimerTask;

public class EvaluationTask extends TimerTask {
  private int failCounter = 0;
  private int counter = 0;

  private final EmergencyEvaluator emergencyEvaluator = new EmergencyEvaluator();


  @Override
  public void run() {
    try {
      System.out.println(counter+" :");

      emergencyEvaluator.evaluate();

      counter ++;
    } catch (Exception e) {
      failCounter++;
      if (failCounter >= 5) {
        System.out.println(
                "The Emergency Manager was failed 5 times !!");
        this.cancel();
      }
      System.out.println(e.fillInStackTrace());
    }
  }


}
