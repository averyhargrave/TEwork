/**********************************************************************************/
/* 02-Consuming Web Services - Post-Put-Delete-HotelService - Lecture Final       */
/*********************************************************************************/
package com.techelevator.hotels.services;

import com.techelevator.hotels.models.Hotel;
import com.techelevator.hotels.models.Reservation;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

public class HotelService {

  private final String BASE_URL;
  private final RestTemplate restTemplate = new RestTemplate();
  private final ConsoleService console = new ConsoleService();

  public HotelService(String url) {
    BASE_URL = url;
  }
  /**
   * Create a new reservation in the hotel reservation system - the API hosted on our server
   *
   * @param newReservation
   * @return Reservation
   */
  public Reservation addReservation(String newReservation) {
    Reservation aReservation = makeReservation(newReservation);  // Call a helper method (below) to make a reservation object from a String

    // It's always good to be sure have an object before you start processing
    if (aReservation == null) {
    	System.out.println("Invalid reservation information entered, please retry");  // We may add this to consoleService later
    	return null;  
    }
    
    // Now that we have a Reservation object, we need to send to the API to add it to Reservation resource
    // We need to do a HTTP POST request to add the reservation
    //
    // An HTTP POST request requires headers to describe data being sent
    //                               and the data to be placed in the request body
    //
    HttpHeaders theHeaders = new HttpHeaders();   // Define an object to hold the header information for the request
    theHeaders.setContentType(MediaType.APPLICATION_JSON);  // Include in the headers that we are sending JSON formatted data
                                                            // MediaType is a group of valid constants for request data types
    
    // An HttpEntity is an object with properly formated components of a request
    //    We use HttpEntity so we don't have to know how to properly format the components of the request
    HttpEntity anEntity = new HttpEntity(aReservation, theHeaders); // Construct a entity object for the request containing
                                                                    //    the data in theReservation object and the headers we created
    
    // Call the API to add the reservation = it will return the reservation it added with the new Reservation Id
    // Use the postForObject method in RestTemplate class - serialize an object and put data in request
    //                                         API-URL                  ,data+header, class of Object to be serialized
    try {
    	aReservation = restTemplate.postForObject(BASE_URL + "reservations", anEntity,    Reservation.class);
    }
    catch (RestClientResponseException exceptionObject) {  // if there is a response error display status code and message
    	console.printError(exceptionObject.getRawStatusCode() + exceptionObject.getStatusText());
    }
    catch (ResourceAccessException exceptionObject) {      // if there is an error with the API trying to access the data resource
    	console.printError(exceptionObject.getMessage());
    }
    return aReservation;
  }


  /**
   * Updates an existing reservation by replacing the old one with a new
   * reservation
   *
   * @param CSV
   * @return
   */
  public Reservation updateReservation(String CSV) {
	  Reservation aReservation = makeReservation(CSV);  // Call a helper method (below) to make a reservation object from a String

	    // It's always good to be sure have an object before you start processing
	    if (aReservation == null) {
	    	System.out.println("Invalid reservation information entered, please retry");  // We may add this to consoleService later
	    	return null;  
	    }
	    // Now that we have a Reservation object, we need to send to the API to add it to Reservation resource
	    // We need to do a HTTP POST request to add the reservation
	    //
	    // An HTTP POST request requires headers to describe data being sent
	    //                               and the data to be placed in the request body
	    //
	    HttpHeaders theHeaders = new HttpHeaders();   // Define an object to hold the header information for the request
	    theHeaders.setContentType(MediaType.APPLICATION_JSON);  // Include in the headers that we are sending JSON formatted data
	                                                            // MediaType is a group of valid constants for request data types
	    
	    // An HttpEntity is an object with properly formated components of a request
	    //    We use HttpEntity so we don't have to know how to properly format the components of the request
	    HttpEntity anEntity = new HttpEntity(aReservation, theHeaders); // Construct a entity object for the request containing
	                                                                    //    the data in theReservation object & headers we created
	    
	    // Call the API to add the reservation = it will return the reservation it added with the new Reservation Id
	    // Use the postForObject method in RestTemplate class - serialize an object and put data in request
	    //                                         API-URL with id to update  ,data+header, class of Object to be serialized
	    try {
	    	restTemplate.put(BASE_URL + "reservations/" + aReservation.getId(), anEntity,    Reservation.class);  // Nothing is returned from a PUT because 
	    }	                                                                              // it knows you already have the object
	    catch (RestClientResponseException exceptionObject) {  // if there is a response error display status code and message
	    	console.printError(exceptionObject.getRawStatusCode() + exceptionObject.getStatusText());
	    }
	    catch (ResourceAccessException exceptionObject) {      // if there is an error with the API trying to access the data resource
	    	console.printError(exceptionObject.getMessage());
	    }
	    return aReservation;
  }

  /**
   * Delete an existing reservation
   *
   * @param id
   */
  public void deleteReservation(int id) {
	// Since there is no data required for delete - all we need is the key for the resource to delete
	  try {
		  restTemplate.delete(BASE_URL + "reservations/" + id);  // Call the API with a HTTP DELETE request for the id given
	  }
	  catch (RestClientResponseException exceptionObject) {  // if there is a response error display status code and message
	    	console.printError(exceptionObject.getRawStatusCode() + exceptionObject.getStatusText());
	  }
	  catch (ResourceAccessException exceptionObject) {      // if there is an error with the API trying to access the data resource
	    	console.printError(exceptionObject.getMessage());
	  	}
	  }

  /* DON'T MODIFY ANY METHODS BELOW */
  /* DON'T MODIFY ANY METHODS BELOW */
  /* DON'T MODIFY ANY METHODS BELOW */
  /* DON'T MODIFY ANY METHODS BELOW */
  /* DON'T MODIFY ANY METHODS BELOW */
  /* DON'T MODIFY ANY METHODS BELOW */
  /* DON'T MODIFY ANY METHODS BELOW */
  /* DON'T MODIFY ANY METHODS BELOW */
  /* DON'T MODIFY ANY METHODS BELOW */
  

  /**
   * List all hotels in the system
   *
   * @return
   */
  public Hotel[] listHotels() {
    Hotel[] hotels = null;
    try {
      hotels = restTemplate.getForObject(BASE_URL + "hotels", Hotel[].class);
    } catch (RestClientResponseException ex) {
      // handles exceptions thrown by rest template and contains status codes
      console.printError(ex.getRawStatusCode() + " : " + ex.getStatusText());
    } catch (ResourceAccessException ex) {
      // i/o error, ex: the server isn't running
      console.printError(ex.getMessage());
    }
    return hotels;
  }

  /**
   * Get the details for a single hotel by id
   *
   * @param id
   * @return Hotel
   */
  public Hotel getHotel(int id) {
    Hotel hotel = null;
    try {
      hotel = restTemplate.getForObject(BASE_URL + "hotels/" + id, Hotel.class);
    } catch (RestClientResponseException ex) {
      console.printError(ex.getRawStatusCode() + " : " + ex.getStatusText());
    } catch (ResourceAccessException ex) {
      console.printError(ex.getMessage());
    }
    return hotel;
  }

  /**
   * List all reservations in the hotel reservation system
   *
   * @return Reservation[]
   */
  public Reservation[] listReservations() {
    Reservation[] reservations = null;
    try {
      reservations = restTemplate.getForObject(BASE_URL + "reservations", Reservation[].class);
    } catch (RestClientResponseException ex) {
      console.printError(ex.getRawStatusCode() + " : " + ex.getStatusText());
    } catch (ResourceAccessException ex) {
      console.printError(ex.getMessage());
    }
    return reservations;
  }

  /**
   * List all reservations by hotel id.
   *
   * @param hotelId
   * @return Reservation[]
   */
  public Reservation[] listReservationsByHotel(int hotelId) {
    Reservation[] reservations = null;
    try {
      reservations = restTemplate.getForObject(BASE_URL + "hotels/" + hotelId + "/reservations", Reservation[].class);
    } catch (RestClientResponseException ex) {
      console.printError(ex.getRawStatusCode() + " : " + ex.getStatusText());
    } catch (ResourceAccessException ex) {
      console.printError(ex.getMessage());
    }
    return reservations;
  }

  /**
   * Find a single reservation by the reservationId
   *
   * @param reservationId
   * @return Reservation
   */
  public Reservation getReservation(int reservationId) {
    Reservation reservation = null;
    try {
      reservation = restTemplate.getForObject(BASE_URL + "reservations/" + reservationId, Reservation.class);
    } catch (RestClientResponseException ex) {
      console.printError(ex.getRawStatusCode() + " : " + ex.getStatusText());
    } catch (ResourceAccessException ex) {
      console.printError(ex.getMessage());
    }
    return reservation;
  }

  private Reservation makeReservation(String CSV) {
    String[] parsed = CSV.split(",");

    // invalid input (
    if (parsed.length < 5 || parsed.length > 6) {
      return null;
    }

    // Add method does not include an id and only has 5 strings
    if (parsed.length == 5) {
      // Create a string version of the id and place into an array to be concatenated
      String[] withId = new String[6];
      String[] idArray = new String[] { new Random().nextInt(1000) + "" };
      // place the id into the first position of the data array
      System.arraycopy(idArray, 0, withId, 0, 1);
      System.arraycopy(parsed, 0, withId, 1, 5);
      parsed = withId;
    }

    return new Reservation(Integer.parseInt(parsed[0].trim()), Integer.parseInt(parsed[1].trim()), parsed[2].trim(),
        parsed[3].trim(), parsed[4].trim(), Integer.parseInt(parsed[5].trim()));
  }

}


