package utils;


import com.theoahga.exception.IntensityUtilsException;
import com.theoahga.utils.IntensityUtils;
import org.geotools.measure.Measure;
import org.junit.jupiter.api.Test;
import si.uom.SI;

import static org.junit.Assert.assertEquals;

public class IntensityUtilsTest {
    @Test
    public void shouldGiveIntensityWithDistance() throws IntensityUtilsException {
        System.setProperty("sensor.reach.max.metter", String.valueOf(100));
        System.setProperty("sensor.intensity.granularity", String.valueOf(9));

        Measure m1 = new Measure(57.1,SI.METRE);
        Measure m2 = new Measure(33.1,SI.METRE);
        Measure m3 = new Measure(10.1,SI.METRE);
        Measure m4 = new Measure(1,SI.METRE);
        Measure m5 = new Measure(84.7,SI.METRE);

        assertEquals(4,IntensityUtils.findIntensity(m1));
        assertEquals(6,IntensityUtils.findIntensity(m2));
        assertEquals(8,IntensityUtils.findIntensity(m3));
        assertEquals(9,IntensityUtils.findIntensity(m4));
        assertEquals(1,IntensityUtils.findIntensity(m5));
    }
}
