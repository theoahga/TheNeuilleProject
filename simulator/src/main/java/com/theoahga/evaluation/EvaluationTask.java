package com.theoahga.evaluation;

import com.theoahga.http.SensorManagerHttp;
import com.theoahga.exception.GetRequestException;
import com.theoahga.exception.ZeroException;
import com.theoahga.model.sensor.api.Sensor;
import com.theoahga.utils.TestUtils;
import org.locationtech.jts.geom.Coordinate;

import java.util.List;
import java.util.Map;
import java.util.TimerTask;

public class EvaluationTask extends TimerTask {
  private List<Sensor> sensors = TestUtils.getTestSensors();
  private Map<String, List<Coordinate>> squareBound = TestUtils.getSquareBounds();
  private EvaluationManager evaluationManager;
  private int failCounter = 0;
  private int counter = 0;

  public EvaluationTask() {
    evaluationManager = new EvaluationManager(sensors, squareBound);
  }

  @Override
  public void run() {
    try {
      checkPrerequisites();
      evaluationManager.manageAndPublish();
      System.out.println(counter+" :");
      counter ++;
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

  private void checkPrerequisites() throws GetRequestException, ZeroException {
    if (failCounter >= 5) {
      System.out.println(
          "The server can't access to sensors definition! The server will be shut down.");
      this.cancel();
    } else if (getSensors().isEmpty()) {
      failCounter++;
      setSensors(SensorManagerHttp.getSensor());
    }
  }
}
