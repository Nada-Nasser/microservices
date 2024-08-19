package com.learning.messaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.WebApplicationType;

@SpringBootApplication
public class MessagingApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MessagingApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE); // Disable the web server
        app.run(args);
    }
}
