package com.theoahga.evaluation;

import com.theoahga.evaluation.api.Evaluator;
import com.theoahga.exception.DistanceComputingException;
import com.theoahga.exception.IntensityUtilsException;
import com.theoahga.model.fire.api.Fire;
import com.theoahga.model.fire.api.TypeFire;
import com.theoahga.model.sensor.SensorCoordinate;
import com.theoahga.model.sensor.SensorStateImpl;
import com.theoahga.model.sensor.api.Sensor;
import com.theoahga.model.sensor.api.SensorState;
import com.theoahga.utils.GeometryUtils;
import com.theoahga.utils.IntensityUtils;
import com.theoahga.utils.SensorStateUtils;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.measure.Measure;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.operation.TransformException;
import si.uom.SI;

import java.util.*;

import static com.theoahga.evaluation.values.SensorEvaluatorValues.SENSOR_REACH_MAX;

public class SensorEvaluator implements Evaluator {
  private List<Sensor> sensors;
  private List<Fire> fires;

  public SensorEvaluator(List<Sensor> sensors, List<Fire> fires) {
    this.sensors = sensors;
    this.fires = fires;
  }

  @Override
  public Set<Object> evaluate() {
    Set<Sensor> updatedSensors = new HashSet<>();

    try{
      List<Sensor> sensors = processAllSensors();
      updatedSensors.addAll(sensors);
    }catch (DistanceComputingException | IntensityUtilsException e) {
      throw new RuntimeException(e);
    }

    return (Set)updatedSensors;
  }

  public List<Sensor> processAllSensors() throws DistanceComputingException, IntensityUtilsException {
    List<Sensor> modifiedSensors = new ArrayList<>();
    for (Sensor sensor : sensors) {
      Sensor modifiedSensor = process(sensor);
      if (modifiedSensor != null) {
        modifiedSensors.add(modifiedSensor);
      }
    }
    return modifiedSensors;
  }

  private Sensor process(Sensor sensor) throws DistanceComputingException, IntensityUtilsException {
    Sensor updateSensor = null;

    SensorState state = new SensorStateImpl();

    Measure distanceMinFromFire = findCloserFire(sensor);
    if (distanceMinFromFire != null) {
      int intensity = IntensityUtils.findIntensity(distanceMinFromFire);
      state.setIntensity(intensity);
      if(sensor.getState().getType() == TypeFire.OFF){
        state.setType(SensorStateUtils.generateRamdonTypeFire());
      }else{
        state.setType(sensor.getState().getType());
      }
    }else {
      state.setType(TypeFire.OFF);
      state.setIntensity(0);
    }

    if( !(state.getIntensity() == sensor.getState().getIntensity()
            && state.getType() == sensor.getState().getType()) ){
      sensor.setState(state);
      updateSensor = sensor;
    }

    return updateSensor;
  }



  private Measure findCloserFire(Sensor sensor) throws DistanceComputingException {
    Measure distanceMin = null;
    for (Fire fire : fires){
      Measure distanceBetweenFireAndSensor = GeometryUtils.computeDistance(fire, sensor);
      if(distanceBetweenFireAndSensor.getUnit() == SI.METRE
              && distanceBetweenFireAndSensor.doubleValue() <= SENSOR_REACH_MAX
              && (distanceMin == null | (distanceMin != null && distanceMin.doubleValue()> distanceBetweenFireAndSensor.doubleValue()))){
        distanceMin = distanceBetweenFireAndSensor;
      }
    }

    return distanceMin;
  }

  public List<Sensor> getSensors(){
    return sensors;
  }
}
