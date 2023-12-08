package com.theoahga.evaluation;

import com.theoahga.http.SensorManagerHttp;
import com.theoahga.exception.GetRequestException;
import com.theoahga.model.Sensor;
import com.theoahga.exception.ZeroException;
import com.theoahga.utils.TestUtils;

import java.util.Arrays;
import java.util.List;
import java.util.TimerTask;

public class EvaluationTask extends TimerTask {
  private List<Sensor> sensors = TestUtils.getTestSensors();
  private int failCounter = 0;

  @Override
  public void run() {
    try {
      if (failCounter >= 5) {
        System.out.println(
            "The server can't access to sensors definition! The server will be shut down.");
        this.cancel();
      } else if (getSensors().isEmpty()) {
        failCounter++;
        setSensors(SensorManagerHttp.getSensor());
      } else {
        // Treatment
        for (Sensor sensor : sensors) {
          System.out.println(sensor.toString());
        }
      }
    } catch (GetRequestException | ZeroException e) {
      System.out.println(e.fillInStackTrace());
    }
  }

  public List<Sensor> getSensors() {
    return sensors;
  }

  public void setSensors(List<Sensor> sensors) {
    this.sensors = sensors;
  }
}
