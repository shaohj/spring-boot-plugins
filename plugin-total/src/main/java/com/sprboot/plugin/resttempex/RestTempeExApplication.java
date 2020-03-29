package com.sprboot.plugin.resttempex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestTempeExApplication {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "resttempex");
        SpringApplication.run(RestTempeExApplication.class, args);
    }
}
