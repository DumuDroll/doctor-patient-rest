package com.dddd.doctorpatientrest.web.contollers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin
public class WebSocketController {
    @MessageMapping("/hello")
    @SendTo("/topic/users")
    public String greeting(String name) {
        return "hi, " + name;
    }
}
