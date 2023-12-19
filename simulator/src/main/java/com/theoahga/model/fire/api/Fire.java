package com.theoahga.model.fire.api;

import org.geotools.measure.Measure;
import org.locationtech.jts.geom.Coordinate;

public interface Fire {
  Coordinate getCoordinate();

  Measure getRadius();

  void setRadius(Measure radius1);
}
