package com.techelevator.reservations.dao;

// This is a list of the methods available for accessing Hotel information

import com.techelevator.reservations.models.Hotel;

import java.util.List;

public interface HotelDAO {

    List<Hotel> list();

    void create(Hotel hotel);

    Hotel get(int id);

}