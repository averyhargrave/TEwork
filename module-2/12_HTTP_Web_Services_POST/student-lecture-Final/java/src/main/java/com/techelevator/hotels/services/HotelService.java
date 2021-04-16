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

  private final String BASE_URL;                                // Hold the base URL for our server - from the user
  private final RestTemplate restTemplate = new RestTemplate(); // Define a RestTemplate objct so we can use HHTP request with API URLs
  private final ConsoleService console = new ConsoleService();  // Instantiate a ConsoleService to handle user terminal interactions

  public HotelService(String url) {  //constructor for the HotelService that accepts the base URL for our server
    BASE_URL = url;                  // assign the url passed to our base url variable
  }

  /**
   * Create a new reservation in the hotel reservation system host on our API server
   *
   * @param newReservation - a String with the reservation data
   * @return Reservation   - reservation object that was added to API resource
   */
  public Reservation addReservation(String newReservation) {
	  // To add a reservation to the API server we need a  POST HTTP request
	  Reservation aReservation = makeReservation(newReservation);   // Use the helper method to create a Reservation object
    
	  // Now that we have a Reservation object we need to use API to add it to the API resource
	  // We will use an HTTP Post request to do so
	  // An HTTP POST requires headers and the data to be added in the body of the request
	  
	  // Create the headers for the HTTP POST
	  
	  HttpHeaders theHeaders = new HttpHeaders();             // Define a header object to hold the header information for the request
	  theHeaders.setContentType(MediaType.APPLICATION_JSON);  // Set the content attribute of the headers to be APPLICTAIOB_JSON
	                                                          // MediaType is a group of valid constants for request data types
	 
	  // An HttpEntity object contains a properly formatted request for use in RestTemplate methods
	  // We use HttpEntity objects to format our request so we don't have to know how to do it ourselves
	  // An HttpEntity constructor takes the object containing the data to send an an object with the HttpHeaders object
	  
	  HttpEntity anEntity = new HttpEntity(aReservation, theHeaders);  // Instantiate an HttpEntity object with the
	                                                                   // Reservation object to be added to the API resource
	                                                                   // and the headers
	  
	  // Call the API with a POST request and the HttpEntity we created
	  
	  //                          do a POST           url                  request   this-type-of-data-returned
	  aReservation = restTemplate.postForObject(BASE_URL + "reservations", anEntity, Reservation.class);
	  
	  return aReservation;    
  }

  /**
   * Updates an existing reservation by replacing the old one with a new
   * reservation
   *
   * @param CSV  a comma delimited string with the new data
   * @return Reservation  -   return the Reservation used to update
   */
  public Reservation updateReservation(String CSV) {
    // To update we use a PUT HTTP request
	  
	Reservation aReservation = makeReservation(CSV); // Use helper method to create a new Reservation to send to API server
	  
	// Create the headers
	HttpHeaders theHeaders = new HttpHeaders();
	
	// Set content type in the headers
	theHeaders.setContentType(MediaType.APPLICATION_JSON);
	
	// Create an HttpEntity to format the request properly for the data and headers
	HttpEntity anEntity = new HttpEntity(aReservation, theHeaders);
	
	// Call the API with an HTTP PUT to update the Reservation on the API resource
	restTemplate.put(BASE_URL + "reservations/" + aReservation.getId(), anEntity);   // Http PUT does does not return anything
	
    return aReservation;
  }

  /**
   * Delete an existing reservation
   *
   * @param id
   */
  public void deleteReservation(int id) {
	  // Since no data is sent with an HTTP DELETE request there is no need for HttpHeaders nor HttpEntity objects
	  // All we have to do is call the API to delete with the id passed in as a parameter
	  
	  restTemplate.delete(BASE_URL + "reservations/" + id);  //  Do HTTO DELETE with the required URL and id
  }

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
  //
  //  Helper method to instantiate a Reservation object from a comma delimited String
  // 
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
