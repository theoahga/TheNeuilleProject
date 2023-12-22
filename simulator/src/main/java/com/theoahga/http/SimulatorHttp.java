package com.theoahga.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public final class SimulatorHttp {

  public static Object sendGetRequest(String urlToGet) throws IOException, InterruptedException {
    if (!urlToGet.startsWith("http")) {
      urlToGet = "http://" + urlToGet;
    }

    HttpClient client = HttpClient.newHttpClient();

    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(urlToGet)).build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    ObjectMapper objectMapper = new ObjectMapper();

    JsonNode jsonNode;


    try {
      return objectMapper.readTree(response.body());
    } catch (JsonProcessingException e) {
      // Nothing to do
    }

    return response.body();

  }

  public static JsonNode sendPostRequest(String urlToPost, String requestBody) throws IOException, InterruptedException {
    if (!urlToPost.startsWith("http")) {
      urlToPost = "http://" + urlToPost;
    }

    HttpClient client = HttpClient.newHttpClient();

    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(urlToPost))
            .header("Content-Type", "application/json")  // Set the content type if your server expects JSON
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode jsonNode = objectMapper.readTree(response.body());

    return jsonNode;
  }
}
