package com.theoahga.model.fire.api;

public enum TypeFire {
  GAZ(1),
  SOLIDE(2),
  LIQUID(3),
  METALS(4);

  private final int associatedInt;

  TypeFire(int associatedInt) {
    this.associatedInt = associatedInt;
  }

  public int getAssociatedInt() {
    return associatedInt;
  }

  public static TypeFire valueOf(int associatedInt) {
    for (TypeFire myEnum : values()) {
      if (myEnum.getAssociatedInt() == associatedInt) {
        return myEnum;
      }
    }
    throw new IllegalArgumentException("No enum constant associated with int: " + associatedInt);
  }
}
