package com.example.demojson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoJsonApplication {

    public static void main(String[] args) {
        createSpringApplication().run(args);
    }

    public static SpringApplication createSpringApplication() {
        return new SpringApplication(DemoJsonApplication.class);
    }

}
