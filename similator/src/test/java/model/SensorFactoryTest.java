package model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theoahga.model.Sensor;
import com.theoahga.model.SensorFactory;
import com.theoahga.exception.ZeroException;
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
    assertEquals(newSensor.getId(), "cid-1");
    assertEquals(newSensor.getCoordinate().getLattitude(), 45, 5);
    assertEquals(newSensor.getCoordinate().getLongitude(), 4, 5);
    assertEquals(newSensor.getAddress(), "Rue du test Unitaire");
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

    assertEquals(s1.getId(), "cid-1");
    assertEquals(s1.getCoordinate().getLattitude(), 45, 5);
    assertEquals(s1.getCoordinate().getLongitude(), 4, 5);
    assertEquals(s1.getAddress(), "Rue du test Unitaire");

    assertEquals(s2.getId(), "cid-2");
    assertEquals(s2.getCoordinate().getLattitude(), 46, 5);
    assertEquals(s2.getCoordinate().getLongitude(), 7, 5);
    assertEquals(s2.getAddress(), "Chez JB");
  }
}
