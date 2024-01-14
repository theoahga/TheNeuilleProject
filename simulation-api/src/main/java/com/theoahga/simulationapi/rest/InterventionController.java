package com.theoahga.simulationapi.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping(path = "intervention")
public class InterventionController {

    private final RestClient restClient = RestClient.create();
    @GetMapping(value = "/getActiveIntervention", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllWithDescriptions(@RequestParam long cid ){
        String url = System.getProperty("ermengency.api.host") + System.getProperty("ermengency.api.endpoint.getActiveIntervention")+"?cid="+cid;
        String result = restClient.get().uri(url).retrieve().body(String.class);

        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/setInterventionProgressing", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> setInterventionProgressing(){
        String url = System.getProperty("ermengency.api.host") + System.getProperty("ermengency.api.endpoint.setInterventionProgressing");
        String result = restClient.get().uri(url).retrieve().body(String.class);

        return ResponseEntity.ok(result);
    }
}
