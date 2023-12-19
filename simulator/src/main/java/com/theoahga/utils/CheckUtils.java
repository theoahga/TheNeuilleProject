package com.theoahga.utils;

import com.theoahga.exception.ZeroException;

public class CheckUtils {
  public static void notNull(Object obj) throws NullPointerException {
    if (obj == null) {
      throw new NullPointerException("An object is null");
    }
  }

  public static void notZero(int integer) throws ZeroException {
    if (integer == 0) {
      throw new ZeroException("An object is set to 0");
    }
  }
}
