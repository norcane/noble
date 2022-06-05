package com.norcane.noble.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan(basePackages = "com.norcane.noble")
public class NobleDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(NobleDemoApplication.class, args);
    }

}
