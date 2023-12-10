package com.theoahga.utils;

import com.theoahga.model.fire.api.TypeFire;

public class SensorStateUtils {
    public static TypeFire generateRamdonTypeFire(){
        int nbType = TypeFire.values().length;
        int rand = (int) (Math.floor(Math.random() * nbType) + 1);

        return TypeFire.valueOf(rand);
    }
}
