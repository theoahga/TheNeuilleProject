package com.theoahga.simulationapi.controller;

import com.theoahga.simulationapi.model.Greeting;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping(value = "/greeting", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Greeting> greeting(@RequestParam String name) {
        return ResponseEntity.ok(new Greeting(counter.incrementAndGet(), String.format(template, name)));
    }
}