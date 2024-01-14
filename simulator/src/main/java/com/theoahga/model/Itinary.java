package com.theoahga.model;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Point;

import java.time.LocalDate;
import java.util.*;

public class Itinary {
    private final int interventionID;
    private List<List<Double>> points;
    private final LocalDate startDate;
    private final LocalDate estimatedEndDate;
    private double pourcent;

    public Itinary(int interventionID, List<List<Double>> points, LocalDate startDate, LocalDate estimatedEndDate) {
        this.interventionID = interventionID;
        this.points = points;
        this.startDate = startDate;
        this.estimatedEndDate = estimatedEndDate;
        this.pourcent = 0;
    }

    public int getInterventionID() {
        return interventionID;
    }

    public List<List<Double>> getPoints() {
        return points;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEstimatedEndDate() {
        return estimatedEndDate;
    }

    public double getPourcent() {
        return pourcent;
    }

    public void setPourcent(double pourcentage) {
        this.pourcent = pourcentage;
    }

    public Map<String,Object> getVehicleCoor(){
        GeometryFactory geometryFactory = new GeometryFactory();

        List<Coordinate> coordinates = new ArrayList<>();
        for (List<Double> li :points) {
            coordinates.add(new Coordinate(li.get(0), li.get(1)));
        }

        LineString lineString = geometryFactory.createLineString(coordinates.toArray(new Coordinate[0]));

        double fraction = 0.4;

        Point interpolatedPoint = interpolate(lineString, fraction);
        Map<String,Object> map = new HashMap<>();
        map.put("lon",interpolatedPoint.getX());
        map.put("lat",interpolatedPoint.getY());
        return map;
    }

    public static Point interpolate(LineString lineString, double fraction) {
        double length = lineString.getLength();
        double targetDistance = fraction * length;

        double currentDistance = 0.0;
        Coordinate[] coordinates = lineString.getCoordinates();

        for (int i = 0; i < coordinates.length - 1; i++) {
            Coordinate start = coordinates[i];
            Coordinate end = coordinates[i + 1];
            double segmentLength = start.distance(end);

            if (currentDistance + segmentLength >= targetDistance) {
                double ratio = (targetDistance - currentDistance) / segmentLength;
                double interpolatedX = start.x + ratio * (end.x - start.x);
                double interpolatedY = start.y + ratio * (end.y - start.y);
                return lineString.getFactory().createPoint(new Coordinate(interpolatedX, interpolatedY));
            }

            currentDistance += segmentLength;
        }

        return lineString.getFactory().createPoint(coordinates[coordinates.length - 1]);
    }

}
