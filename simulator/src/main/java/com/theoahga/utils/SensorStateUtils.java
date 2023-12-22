package com.theoahga.utils;

import com.theoahga.model.fire.api.TypeFire;
import java.util.Random;

public class SensorStateUtils {
    public static TypeFire generateRamdonTypeFire(){
        int nbType = TypeFire.values().length;
        int rand = new Random().nextInt(nbType - 1 ) + 1;

        return TypeFire.valueOf(rand);
    }
}
