package com.theoahga.evaluation;

import com.theoahga.exception.PostRequestException;
import com.theoahga.http.FireManagerHttp;
import com.theoahga.http.SensorManagerHttp;
import com.theoahga.exception.GetRequestException;
import com.theoahga.exception.ZeroException;
import com.theoahga.model.sensor.api.Sensor;
import com.theoahga.utils.TestUtils;
import org.locationtech.jts.geom.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

public class EvaluationTask extends TimerTask {
  private List<Sensor> sensors = new ArrayList<>();
  private Map<String, List<Coordinate>> squareBound = TestUtils.getSquareBounds();
  private EvaluationManager evaluationManager;
  private int failCounter = 0;
  private int counter = 0;


  @Override
  public void run() {
    try {
      System.out.println(counter+" :");

      checkPrerequisites();

      // Fires & Sensors evaluation -> then it will post request the simulator-api
      evaluationManager.manageAndPublish();

      counter ++;
    } catch (PostRequestException| GetRequestException | ZeroException e) {
      failCounter++;
      if (failCounter >= 5) {
        System.out.println(
                "The server can't access to sensors definition! The server will be shut down.");
        this.cancel();
      }
      System.out.println(e.fillInStackTrace());
    }
  }

  private void checkPrerequisites() throws GetRequestException, ZeroException, PostRequestException {
    if(sensors.isEmpty()){
      sensors = SensorManagerHttp.getSensor();
      SensorManagerHttp.initSensorInfo(sensors);
      FireManagerHttp.resetFires();
    }

    if(evaluationManager == null){
      evaluationManager = new EvaluationManager(sensors,squareBound);
    }
  }

}
