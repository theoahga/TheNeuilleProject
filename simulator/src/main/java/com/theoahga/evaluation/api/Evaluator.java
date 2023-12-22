package com.theoahga.evaluation.api;

import com.theoahga.exception.DistanceComputingException;
import java.util.Set;

public interface Evaluator {
  Set<Object> evaluate() throws DistanceComputingException;
}
