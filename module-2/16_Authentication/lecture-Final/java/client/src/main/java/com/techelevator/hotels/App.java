package com.techelevator.hotels;

import com.techelevator.hotels.models.Hotel;
import com.techelevator.hotels.models.Reservation;
import com.techelevator.hotels.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;

import java.util.Map;

public class App {

    private static final String API_BASE_URL = "http://localhost:8080/";

    public static void main(String[] args) {
        int menuSelection = 999;
        int hotelId = -1;

        ConsoleService consoleService = new ConsoleService();
        HotelService hotelService = new HotelService(API_BASE_URL);
        AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);

        while (menuSelection != 0) {
            menuSelection = consoleService.printMainMenu();
            try {
                if (menuSelection == 1) {
                    // List all hotels
                    consoleService.printHotels(hotelService.listHotels());
                } else if (menuSelection == 2) {
                    hotelId = consoleService.promptForHotel(hotelService.listHotels(), "List Reservations");
                    consoleService.printReservations(hotelService.listReservationsByHotel(hotelId), hotelId);
                } else if (menuSelection == 3) {
                    // Create new reservation for a given hotel
                    String newReservationString = consoleService.promptForReservationData();
                    Reservation reservation = hotelService.addReservation(newReservationString);
                } else if (menuSelection == 4) {
                    // Update an existing reservation
                    Reservation[] reservations = hotelService.listReservations();
                    int reservationId = consoleService.promptForReservation(reservations, "Update Reservation");
                    Reservation existingReservation = hotelService.getReservation(reservationId);
                    if (existingReservation != null) {
                        String csv = "" + reservationId + ",";
                        csv += consoleService.promptForReservationData();
                        hotelService.updateReservation(csv);
                    }
                } else if (menuSelection == 5) {
                    // Delete reservation
                    Reservation[] reservations = hotelService.listReservations();
                    int reservationId = consoleService.promptForReservation(reservations, "Delete Reservation");
                    hotelService.deleteReservation(reservationId);
                } else if (menuSelection == 6) {                           // Login option
                    String credentials = consoleService.promptForLogin();  //    Use the ConsoleService to get username and password
                    if( credentials.split(",").length == 2 ) {             //    Did the ConsoleService return two values separated by ,
                    	// Call the login() method of the authenticationSerice with the "username,password" that was entered
                    	// The authenticationService will send us back the HTTP response it got from the Authentication server
                    	// ResponseEntity is a class for objects representing an HTTP response
                    	//                typically a Map is used to hold the response data
                    	ResponseEntity<Map> response = authenticationService.login(credentials);
                        if (response.hasBody()) {                                   // If the response has a body (is there any data in the response?)
                            String token = (String) response.getBody().get("token");//    get the value for the "token" attribute and store it
                            hotelService.AUTH_TOKEN = token;                        //    Store the token in the HotelService variable to hold it
                            System.out.println("Login Successful");                 //    Tell the user that the login was successful
                        }
                    } else {
                        consoleService.printError("Please enter username and password separated by a comma.");
                    }
                }

                else if (menuSelection == 0) { // Exit
                    consoleService.exit();
                } else {
                    // anything else is not valid
                    System.out.println("Invalid Selection");
                }
                // Press any key to continue...
                if (hotelId != 0) {
                    consoleService.next();
                }
            } catch (AuthenticationServiceException ex) {
                consoleService.printError(ex.getMessage());
                consoleService.next();
            } catch (HotelServiceException ex) {
                consoleService.printError(ex.getMessage());
                consoleService.next();
            } catch (ResourceAccessException ex) {
                consoleService.printError(ex.getMessage());
                consoleService.next();
            }

            // Ensure loop continues
            menuSelection = 999;
        }
        // if the loop exits, so does the program
        consoleService.exit();
    }

}
