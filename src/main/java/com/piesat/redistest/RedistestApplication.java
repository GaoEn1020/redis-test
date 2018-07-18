package com.piesat.redistest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.piesat.redistest","pie.data.cache"})
public class RedistestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedistestApplication.class, args);
    }
}
