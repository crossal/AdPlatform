package com.crossal;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootWebApplication {

    private static final Logger logger = Logger.getLogger(SpringBootWebApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebApplication.class, args);
    }
}
