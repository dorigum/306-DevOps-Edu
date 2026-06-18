package com.jadecross.hiring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class HiringApplication {

    public static void main(String[] args) {
        SpringApplication.run(HiringApplication.class, args);
    }

}
