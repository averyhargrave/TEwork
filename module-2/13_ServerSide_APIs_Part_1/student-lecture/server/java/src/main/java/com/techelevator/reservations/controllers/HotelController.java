package com.techelevator.reservations.controllers;

import com.techelevator.reservations.dao.HotelDAO;
import com.techelevator.reservations.dao.MemoryHotelDAO;
import com.techelevator.reservations.dao.MemoryReservationDAO;
import com.techelevator.reservations.dao.ReservationDAO;
import com.techelevator.reservations.models.Hotel;
import com.techelevator.reservations.models.Reservation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// This will handle API calls sent to the serve with paths specified in the @RequestMapping
// API Handlers == Controllers

@RestController // This tells the server there are controllers in here to handle specified URL paths

public class HotelController {

	// Define references for each DAO we will use
    private HotelDAO hotelDAO;
    private ReservationDAO reservationDAO;

    // Constructor for this class of Controllers
    public HotelController() {
        this.hotelDAO = new MemoryHotelDAO();					  // Instantiate an object with HotelDAO methods and assign it to the reference
        this.reservationDAO = new MemoryReservationDAO(hotelDAO); // Instantiate an object with ReservationDAO methods and assign it to the reference
    }

    /**
     * Return All Hotels
     *
     * @return a list of all hotels in the system when a GET request with the /hotels path is received by the server
     */
//  @RequestMapping - Assign a URL path and HTTP request combination to a method
    @RequestMapping(path = "/hotels", method = RequestMethod.GET) // This method will handle HTTP GET URLs sent to the server with the /hotels path
    public List<Hotel> hotelList() {
    	System.out.println("Called with the path /hotels");
        return hotelDAO.list();
    }

    /**
     * Return hotel information
     *
     * @param id the id of the hotel
     * @return all info for a given hotel
     */
    @RequestMapping(path = "/hotels/{id}") // if you don't have the method, we assume GET
    public Hotel get(@PathVariable int id) {
    	System.out.println("Called with the path /hotels/" + id);
        return hotelDAO.get(id);
    }

    // Write a controller to return all the reservations when we get the path "/reserverations"
    // We will use the ReservationsDAO findAll() method to get the reservations
    @RequestMapping(path = "/reservations", method = RequestMethod.GET)
    public List<Reservation> reservationList() {
    	System.out.println("Called with the path /reservations");
    	return reservationDAO.findAll();
    }
    
    // Write a controller to add a Reservation to the Reservation resource
    // using path = "/hotels/id/reservations"      - id is the hotelId for the Reservation
    // http://localhost8080/hotels/1/reservations - should add a reservation for the hotel whose id is 1
    // The data for the new reservation will be in the body of the POST request used to call
    // @RequestBody  - create an object from the data in the body of the request
    // @PathVariable - get the value from the data in the path 
    
    @RequestMapping(path = "hotels/{id}/reservations", method = RequestMethod.POST)
    public Reservation addReservation(@RequestBody Reservation aReservation // Take the data in the RequestBody and instantiate a Reservation object
    								 ,@PathVariable("id") int hotelId) {    // Take the id from the PathVariable and store it in an int called hotelId
	System.out.println("Called with the path /hotels/" + hotelId + "/reservations");
    	return reservationDAO.create(aReservation, hotelId); // Use the create method in reservationDAO to create a new Reservation	
    }
    
}