package com.theoahga.utils;

import com.theoahga.exception.DistanceComputingException;
import com.theoahga.model.fire.api.Fire;
import com.theoahga.model.sensor.api.Sensor;
import org.geotools.geometry.jts.JTS;
import org.geotools.measure.Measure;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.operation.TransformException;
import si.uom.SI;

public class GeometryUtils {
    public static Measure computeDistance(Fire fire, Sensor sensor) throws DistanceComputingException {
        Double distance = null;

        try {
            distance = JTS.orthodromicDistance(sensor.getCoordinate(),fire.getCoordinate(), DefaultGeographicCRS.WGS84) - fire.getRadius().doubleValue();
        } catch (TransformException e) {
            throw new DistanceComputingException("There is something wrong in the distance computing");
        }

        return new Measure(distance, SI.METRE);
    }
}
