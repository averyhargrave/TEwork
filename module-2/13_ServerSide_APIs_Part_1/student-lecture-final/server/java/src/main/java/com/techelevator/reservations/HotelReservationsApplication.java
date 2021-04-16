package com.techelevator.reservations;

// This is our API server - this is what we start

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotelReservationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelReservationsApplication.class, args);
    }

}
