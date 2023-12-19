package model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theoahga.model.sensor.SensorImpl;
import com.theoahga.model.sensor.SensorFactory;
import com.theoahga.exception.ZeroException;
import com.theoahga.model.sensor.api.Sensor;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SensorFactoryTest {
  private final ObjectMapper objectMapper = new ObjectMapper();

  @Test
  public void shouldTransformJsonNodeToSensor() throws IOException {
    String sensor =
        "{ "
            + "\"id\":\"cid-1\", "
            + "\"lat\" : 45,"
            + "\"lon\": 4 , "
            + "\"address\" : \"Rue du test Unitaire\"}";

    JsonNode jsonSensor = objectMapper.readTree(sensor.getBytes());

    Sensor newSensor = SensorFactory.createSensorFromJson(jsonSensor);
    assertEquals("cid-1", newSensor.getId());
    assertEquals(Double.valueOf(45), newSensor.getCoordinate().y, 5);
    assertEquals(Double.valueOf(4), newSensor.getCoordinate().x, 5);
    assertEquals("Rue du test Unitaire", newSensor.getAddress());
  }

  @Test
  public void shouldTransformJsonNodesToSensors() throws IOException, ZeroException {
    String sensors =
        "["
            + "{ \"id\":\"cid-1\","
            + "\"lat\" : 45,"
            + "\"lon\": 4 ,"
            + "\"address\" : \"Rue du test Unitaire\"},"
            + "{\"id\":\"cid-2\","
            + " \"lat\":46,"
            + " \"lon\":7,"
            + " \"address\":\"Chez JB\"}"
            + "]";
    JsonNode jsonSensor = objectMapper.readTree(sensors.getBytes());

    List<Sensor> sensorsList = SensorFactory.createSensorsFromJson(jsonSensor);

    Sensor s1 = sensorsList.get(0);
    Sensor s2 = sensorsList.get(1);

    assertEquals("cid-1", s1.getId());
    assertEquals(Double.valueOf(45), s1.getCoordinate().y, 5);
    assertEquals(Double.valueOf(4), s1.getCoordinate().x, 5);
    assertEquals("Rue du test Unitaire", s1.getAddress());

    assertEquals("cid-2", s2.getId());
    assertEquals(Double.valueOf(46), s2.getCoordinate().y, 5);
    assertEquals(Double.valueOf(7), s2.getCoordinate().x, 5);
    assertEquals("Chez JB", s2.getAddress());
  }
}
