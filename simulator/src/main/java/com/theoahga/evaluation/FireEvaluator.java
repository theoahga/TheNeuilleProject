package com.theoahga.evaluation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.theoahga.evaluation.api.Evaluator;
import com.theoahga.exception.DistanceComputingException;
import com.theoahga.exception.GetRequestException;
import com.theoahga.exception.PostRequestException;
import com.theoahga.http.FireManagerHttp;
import com.theoahga.http.SensorManagerHttp;
import com.theoahga.model.Itinary;
import com.theoahga.model.fire.FireCoordinate;
import com.theoahga.model.fire.FireImpl;
import com.theoahga.model.fire.api.Fire;
import com.theoahga.model.sensor.api.Sensor;
import com.theoahga.utils.GeometryUtils;
import org.geotools.measure.Measure;
import org.locationtech.jts.geom.Coordinate;
import si.uom.SI;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

import static com.theoahga.evaluation.values.FireEvaluatorValues.*;
import static com.theoahga.evaluation.values.SensorEvaluatorValues.SENSOR_REACH_MAX;

public class FireEvaluator implements Evaluator {

  private Map<String, List<Coordinate>> fireSquareBounds;
  private List<Fire> fires;
  private List<Sensor> sensors;
  private Map<Integer, Itinary> itinaryMap;

  public FireEvaluator(Map<String, List<Coordinate>> fireSquareBounds, List<Fire> fires, List<Sensor> sensors, Map<Integer, Itinary> itinaryMap) {
    this.fireSquareBounds = fireSquareBounds;
    this.fires = fires;
    this.sensors = sensors;
    this.itinaryMap = itinaryMap;
  }

  @Override
  public Set<Object> evaluate() {
    Set<Fire> updateFire = new HashSet<>();

      // TODO: reafactor
      for (Map.Entry<String, List<Coordinate>> entry : fireSquareBounds.entrySet()){
        if (wantCreateAFire()){
          Fire newFire = buildFire(entry.getValue());
          updateFire.add(newFire);
        }
      }

    List<Fire> firesqsd = null;
    try {
      firesqsd = evaluateAllFires();
    } catch (DistanceComputingException | GetRequestException | PostRequestException e) {
      throw new RuntimeException(e);
    }

    updateFire.addAll(firesqsd);

      return (Set)updateFire;
    }

    private List<Fire> evaluateAllFires() throws DistanceComputingException, GetRequestException, PostRequestException {
      List<Fire> firesUpdated = new ArrayList<>();
      for (Fire fire : fires){
        JsonNode activeInterventionInPending = SensorManagerHttp.getActiveIntervention(Integer.parseInt(findCloserSensor(fire).getId()));
        if (!(activeInterventionInPending instanceof MissingNode) && activeInterventionInPending.get("status").asText().equals("PENDING")) {
          fire.setRadius(new Measure((fire.getRadius().doubleValue()*1.05), SI.METRE));
          firesUpdated.add(fire);

          if(itinaryMap.get(activeInterventionInPending.get("id").asInt()) == null){
            createItinary(activeInterventionInPending);
          }

        } else if (!(activeInterventionInPending instanceof MissingNode) &&  activeInterventionInPending.get("status").asText().equals("PROGRESSING")) {
          double rand = Math.random();
          if (rand < 0.5) {
            fire.setRadius(new Measure((fire.getRadius().doubleValue()*0.50), SI.METRE));
            firesUpdated.add(fire);
          }else{
            fire.setRadius(new Measure(0, SI.METRE));
            firesUpdated.add(fire);
          }
        }else {
          fire.setRadius(new Measure((fire.getRadius().doubleValue()*1.05), SI.METRE));
          firesUpdated.add(fire);
        }


      }

      evaluateItinaries();

      return firesUpdated;
    }

  private void createItinary(JsonNode activeInterventionInPending) throws PostRequestException {
    itinaryMap.put(activeInterventionInPending.get("id").asInt(),SensorManagerHttp.createItinary(activeInterventionInPending));
  }

  private void evaluateItinaries() throws PostRequestException {
    List<Map<String,Object>> truckGPSPoints = new ArrayList<>();
    for (Map.Entry<Integer,Itinary> entry : itinaryMap.entrySet()){
      evaluate(entry.getValue());

      if(entry.getValue().getPourcent() == 100){
        SensorManagerHttp.setInterventionProgressing(entry.getValue().getInterventionID());
      }
      truckGPSPoints.add(entry.getValue().getVehicleCoor());
    }

    SensorManagerHttp.publishTrucksGps(truckGPSPoints);
  }

  private void evaluate(Itinary itinary) {
    LocalDate currentDate = LocalDate.now();
    Period totalDuration = Period.between(itinary.getStartDate(), itinary.getEstimatedEndDate());
    Period passedDuration = Period.between(itinary.getStartDate(), currentDate);

    double pourcentage;
    if (totalDuration.toTotalMonths()< passedDuration.toTotalMonths()){
      pourcentage = 100;
    }else {
      pourcentage = ((double) passedDuration.toTotalMonths() / totalDuration.toTotalMonths()) * 100;
    }

    itinary.setPourcent(pourcentage);
  }

  private boolean wantCreateAFire() {
      double rand = Math.random();
      if (rand < FIRE_CREATION_PROB) {
        return true;
      }
      return false;
    }

    private Fire buildFire(List<Coordinate> squareBounds) {
      FireCoordinate fireCoordinate = getRandomCoordinateInSquare(squareBounds);
      Measure radius = getRandomFistRadius();
      Fire newFire = new FireImpl(fireCoordinate, radius);
      fires.add(newFire);

      return newFire;
    }

    private FireCoordinate getRandomCoordinateInSquare(List<Coordinate> squareBounds) {

      FireCoordinate fireCoordinate = null;
      if (squareBounds.size() == 2) {
        Coordinate c1 = squareBounds.get(0);
        Coordinate c2 = squareBounds.get(1);

        Coordinate lowerLeft = new Coordinate(Math.min(c1.x, c2.x), Math.min(c1.y, c2.y));
        Coordinate upperRight = new Coordinate(Math.max(c1.x, c2.x), Math.max(c1.y, c2.y));

        double randomX = lowerLeft.x + Math.random() * (upperRight.x - lowerLeft.x);
        double randomY = lowerLeft.y + Math.random() * (upperRight.y - lowerLeft.y);

        fireCoordinate = new FireCoordinate(randomX, randomY);
      } else {
        // TODO -> exception
      }

      return fireCoordinate;
    }

    private Measure getRandomFistRadius() {
      Random random = new Random();
      Double rand =
          FIRE_CREATION_RADIUS_MIN
              + (FIRE_CREATION_RADIUS_MAX - FIRE_CREATION_RADIUS_MIN) * random.nextDouble();
      Measure radius = new Measure(rand, SI.METRE);
      return radius;
    }

    public List<Fire> getFires() {
        return fires;
      }

    public Sensor findCloserSensor(Fire fire) throws DistanceComputingException {
      Measure distanceMin = null;
      Sensor sensorMinDistance = null;
      for (Sensor sensor : sensors){
        Measure distanceBetweenFireAndSensor = GeometryUtils.computeDistance(fire, sensor);
        if(distanceMin == null || distanceBetweenFireAndSensor.doubleValue() < distanceMin.doubleValue()){
          distanceMin = distanceBetweenFireAndSensor;
          sensorMinDistance = sensor;
        }
      }

      return sensorMinDistance;
    }
}
