package com.theoahga.evaluation.api;

import com.theoahga.model.sensor.api.Sensor;

import java.util.List;

public interface Evaluator {
  List<Sensor> evaluate();
}
