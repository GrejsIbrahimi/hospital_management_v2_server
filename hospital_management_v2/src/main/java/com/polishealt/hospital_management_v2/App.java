package com.polishealt.hospital_management_v2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.polishealt.hospital_management_v2")  // SIGUROHU që kjo është këtu!
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

