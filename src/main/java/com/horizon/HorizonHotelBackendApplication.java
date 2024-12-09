package com.horizon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HorizonHotelBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(HorizonHotelBackendApplication.class, args);
    }

}
