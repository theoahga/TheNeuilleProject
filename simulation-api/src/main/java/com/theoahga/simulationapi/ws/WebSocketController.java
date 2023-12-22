package com.theoahga.simulationapi.ws;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {


    @MessageMapping("/hello")
    @SendTo("/topic/messages")
    public String greeting(String name) throws Exception {
        Thread.sleep(1000);
        return "Hello, " + name + "!";
    }

}
