package com.theoahga.evaluation.values;

public class FireEvaluatorValues {
    public static Double FIRE_CREATION_PROB =
            Double.valueOf(System.getProperty("fire.creation.on.square.probability"));
    public static double FIRE_CREATION_RADIUS_MIN =
            Double.valueOf(System.getProperty("fire.creation.radius.min.metter"));
    public static double FIRE_CREATION_RADIUS_MAX =
            Double.valueOf(System.getProperty("fire.creation.radius.max.metter"));
}
