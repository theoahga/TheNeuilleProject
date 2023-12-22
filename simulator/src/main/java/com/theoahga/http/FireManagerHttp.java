package com.theoahga.http;

import com.fasterxml.jackson.databind.JsonNode;
import com.theoahga.exception.GetRequestException;
import com.theoahga.exception.PostRequestException;
import com.theoahga.model.sensor.api.Sensor;
import com.theoahga.model.sensor.api.SensorInfo;
import com.theoahga.utils.ParsingUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FireManagerHttp {
  public static String resetFires() throws GetRequestException {
        String url =
                System.getProperty("simulator.api.host")
                        + System.getProperty("simulator.api.endpoint.resetfire");
        String res;
        try {
            res = (String) SimulatorHttp.sendGetRequest(url);
        } catch (IOException | InterruptedException e) {
            throw new GetRequestException(url);
        }

        return res;
    }
}
