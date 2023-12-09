package com.theoahga.evaluation;

import com.theoahga.evaluation.api.Evaluator;
import com.theoahga.model.fire.FireCoordinate;
import com.theoahga.model.fire.FireImpl;
import com.theoahga.model.fire.api.Fire;
import com.theoahga.model.sensor.api.Sensor;
import org.geotools.measure.Measure;
import org.locationtech.jts.geom.Coordinate;
import si.uom.SI;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class FireEvaluator implements Evaluator {
  public static Double FIRE_CREATION_PROB =
      Double.valueOf(System.getProperty("fire.creation.on.square.probability"));
  public static double FIRE_CREATION_RADIUS_MIN =
      Double.valueOf(System.getProperty("fire.creation.radius.min.metter"));
  public static double FIRE_CREATION_RADIUS_MAX =
      Double.valueOf(System.getProperty("fire.creation.radius.max.metter"));
  private Map<String, List<Coordinate>> fireSquareBounds;
  private List<Fire> fires;

  public FireEvaluator(Map<String, List<Coordinate>> fireSquareBounds) {
    this.fireSquareBounds = fireSquareBounds;
  }

  @Override
  public List<Sensor> evaluate() {

    return null;
  }

  private boolean wantCreateAFire() {
    double rand = Math.random();
    if (rand < FIRE_CREATION_PROB) {
      return true;
    }
    return false;
  }

  private void buildFire(List<Coordinate> squareBounds) {
    FireCoordinate fireCoordinate = getRandomCoordinateInSquare(squareBounds);
    Measure radius = getRandomFistRadius();
    Fire newFire = new FireImpl(fireCoordinate, radius);
    fires.add(newFire);
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
}
