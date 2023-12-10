package utils;

import com.theoahga.evaluation.SensorEvaluator;
import com.theoahga.exception.DistanceComputingException;
import com.theoahga.model.fire.FireCoordinate;
import com.theoahga.model.fire.FireImpl;
import com.theoahga.model.fire.api.Fire;
import com.theoahga.model.sensor.SensorCoordinate;
import com.theoahga.model.sensor.SensorImpl;
import com.theoahga.model.sensor.SensorStateImpl;
import com.theoahga.model.sensor.api.Sensor;
import com.theoahga.utils.GeometryUtils;
import org.geotools.measure.Measure;
import org.junit.jupiter.api.Test;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.operation.TransformException;
import si.uom.SI;

import java.io.IOException;

import static com.theoahga.utils.GeometryUtils.computeDistance;
import static org.junit.Assert.assertEquals;

public class GeometryUtilsTest {
    @Test
    public void shouldMeasureDistancePointAndFireWithRadius() throws DistanceComputingException {
        FireCoordinate fireCoordinate = new FireCoordinate(4.868702313440087,45.78393588346722);
        Measure radius = new Measure(1, SI.METRE);
        Fire fire = new FireImpl(fireCoordinate,radius);

        Sensor sensor = new SensorImpl("","",new SensorCoordinate(45.78381284704242, 4.8698785011860775),new SensorStateImpl());

        Measure distanceInMetter = computeDistance(fire,sensor);
        assertEquals(91.48211410413758,distanceInMetter.doubleValue(), 0);

        Measure radius1 = new Measure(10, SI.METRE);
        fire.setRadius(radius1);

        Measure distanceInMetter1 = computeDistance(fire,sensor);
        assertEquals(82.48211410413758,distanceInMetter1.doubleValue(), 0);
    }
}
