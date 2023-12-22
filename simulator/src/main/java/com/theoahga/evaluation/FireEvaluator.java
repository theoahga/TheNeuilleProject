package com.theoahga.evaluation;

import com.theoahga.evaluation.api.Evaluator;
import com.theoahga.model.fire.FireCoordinate;
import com.theoahga.model.fire.FireImpl;
import com.theoahga.model.fire.api.Fire;
import com.theoahga.model.sensor.api.Sensor;
import org.geotools.measure.Measure;
import org.locationtech.jts.geom.Coordinate;
import si.uom.SI;

import java.sql.SQLOutput;
import java.util.*;

import static com.theoahga.evaluation.values.FireEvaluatorValues.*;

public class FireEvaluator implements Evaluator {

  private Map<String, List<Coordinate>> fireSquareBounds;
  private List<Fire> fires;

  public FireEvaluator(Map<String, List<Coordinate>> fireSquareBounds, List<Fire> fires) {
    this.fireSquareBounds = fireSquareBounds;
    this.fires = fires;
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

    List<Fire> firesqsd = evaluateAllFires();

    updateFire.addAll(firesqsd);

    return (Set)updateFire;
  }

  private List<Fire> evaluateAllFires() {
    List<Fire> firesUpdated = new ArrayList<>();
    for (Fire fire : fires){
      fire.setRadius(new Measure((fire.getRadius().doubleValue()*1.05), SI.METRE));
      firesUpdated.add(fire);
      // TODO : look for inetervention related to this fire
    }

    return firesUpdated;
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
}
