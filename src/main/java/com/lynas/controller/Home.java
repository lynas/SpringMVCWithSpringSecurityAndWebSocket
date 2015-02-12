package com.lynas.controller;

import com.lynas.service.Greeting;
import com.lynas.service.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Home {

    @RequestMapping(value = "/")
    public String home(){
        return "main";
    }

    @RequestMapping(value = "/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN_USER')")
    public String home2(){
        return "admin";
    }

    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(3000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }
}


