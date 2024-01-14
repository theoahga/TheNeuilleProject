package com.theoahga.simulationapi.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

@Controller
@RequestMapping(path = "itinary")
public class ItinaryController {

    @PostMapping(value = "/createItinary", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createItinerary(@RequestBody String body) throws NoSuchAlgorithmException, KeyManagementException, IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode node;
        try {
            node = objectMapper.readTree(body);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        JsonNode startNode = node.get("start");
        String startLat = startNode.get("lat").asText();
        String startLon = startNode.get("lon").asText();

        JsonNode endNode = node.get("end");
        String endLat = endNode.get("lat").asText();
        String endLon = endNode.get("lon").asText();

        String url = "https://wxs.ign.fr/calcul/geoportail/" +
                "itineraire/rest/1.0.0/route?resource=bdtopo-osrm&" +
                "start="+startLon+"%2C"+startLat+"&" +
                "end="+endLon+"%2C"+endLat+"&" +
                "profile=car&" +
                "optimization=fastest&" +
                "constraints=&" +
                "getSteps=true&" +
                "getBbox=true&" +
                "distanceUnit=meter&" +
                "timeUnit=minute&crs=EPSG%3A4326";

        var sslContext = SSLContext.getInstance("TLS");
        var trustManager = new X509TrustManager() {

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[]{};
            }

            @Override
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        };
        sslContext.init(null, new TrustManager[]{trustManager}, new SecureRandom());
        HttpClient client = HttpClient.newBuilder()
                .sslContext(sslContext)
                .build();


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


        return ResponseEntity.ok(response.body());
    }

}
