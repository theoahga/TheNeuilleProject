package com.theoahga.utils;

import com.theoahga.exception.IntensityUtilsException;
import org.geotools.measure.Measure;
import si.uom.SI;

public class IntensityUtils {
    public static int findIntensity(Measure measure) throws IntensityUtilsException {
        CheckUtils.notNull(measure);

        if(measure.getUnit() != SI.METRE){
            throw new IntensityUtilsException("The distance passed in parameter is not in metter");
        }

        int reach = Integer.parseInt(System.getProperty("sensor.reach.max.metter"));
        int granularity = Integer.parseInt(System.getProperty("sensor.intensity.granularity"));

        return granularity - ((int) Math.round(measure.doubleValue() / reach  * granularity));
    }
}
