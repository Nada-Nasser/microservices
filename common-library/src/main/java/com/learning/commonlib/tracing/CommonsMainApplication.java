package com.learning.commonlib.tracing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommonsMainApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(CommonsMainApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE); // Disable the web server
        app.run(args);
    }
}
