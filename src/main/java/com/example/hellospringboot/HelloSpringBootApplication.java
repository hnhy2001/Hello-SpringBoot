package com.example.hellospringboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringBootApplication {
//    private static final Loogger log = LoggerFactory.getLogger(HelloSpringBootApplication.class);
    private static final Logger log = LoggerFactory.getLogger(HelloSpringBootApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(HelloSpringBootApplication.class, args);
        log.info("Khởi động chương trình thành công");
    }


}
