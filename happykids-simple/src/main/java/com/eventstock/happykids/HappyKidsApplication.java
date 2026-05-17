package com.eventstock.happykids;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HappyKidsApplication {
    public static void main(String[] args) {
        SpringApplication.run(HappyKidsApplication.class, args);
        System.out.println("\n✅ Happy Kids corriendo en http://localhost:3000\n");
    }
}
