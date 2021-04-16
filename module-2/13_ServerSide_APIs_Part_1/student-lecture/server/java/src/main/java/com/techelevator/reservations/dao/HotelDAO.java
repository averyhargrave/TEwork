package com.techelevator.reservations.dao;

// This is a list of methods available for accessing hotel info

import com.techelevator.reservations.models.Hotel;

import java.util.List;

public interface HotelDAO {

    List<Hotel> list();

    void create(Hotel hotel);

    Hotel get(int id);

}