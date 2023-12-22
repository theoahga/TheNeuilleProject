package com.theoahga.evaluation.api;

import com.theoahga.exception.DistanceComputingException;
import com.theoahga.model.fire.api.Fire;
import com.theoahga.model.sensor.api.Sensor;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Evaluator {
  Set<Object> evaluate() throws DistanceComputingException;
}
