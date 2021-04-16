package com.techelevator.services;

import com.techelevator.models.City;
import com.techelevator.models.Hotel;
import com.techelevator.models.Review;
import org.springframework.web.client.RestTemplate;

public class HotelService {

	// Hold the base part of the server URL the user wants us to use
    private final String API_BASE_URL; 
    
    // Instantiate an object of a class containing all the code to support API calls
    // REST is a protocol for doing SPI calls - we learn more about these later this week
    // RestTemplate is a framework class to support API calls
    // When we want to do an API call, we will use this RestTemplate object
    private RestTemplate callAPI = new RestTemplate();

    // Constructor - accepts the main/base part of the URL the user wants us to use
    public HotelService(String apiURL) { 
        API_BASE_URL = apiURL; // Assigns the main/base part of the URL the user sends to our instance variable above
    }

    // this method will go to the API server, get the hotel data, and return it in an Array of hotels
    public Hotel[] listHotels() {
    	// the URL path http://localhost:3000/hotels will return all the hotels from the API
    	// the http://localhost:3000/ is stored in our API_BASE_URL variable
    	// We need to add the "hotels" to the API_BASE_URL and send it to the API server using the RestTemplate object
    	// the API returns JSON formatted data which we need to convert to an Array of Hotel objects
    	
    	// The RestTemplate class has a method called getForObject() that will, given a URL, do an API GET request
    	//     to the URL and return data in any Java data format we pick. It will convert JSON data to Java data format automatically
    	
    	// The format of getForObject() method: getForObject(String-with-URL, datatype-you-want-returned)
    	//                            API-URL           , datatype-to-return
    	return callAPI.getForObject(API_BASE_URL + "hotels", Hotel[].class); // Hotel[].class is an Array of Hotel class objects
    }

    public Review[] listReviews() {
    	return callAPI.getForObject(API_BASE_URL + "reviews", Review[].class);
    }
    
    // return information on a specific hotel whose id is passed to the method
    public Hotel getHotelById(int id) {
    	return callAPI.getForObject(API_BASE_URL + "hotels/" + id, Hotel.class);
    }

    public Review[] getReviewsByHotelId(int hotelID) {
        return callAPI.getForObject(API_BASE_URL + "hotels/" + hotelID + "/reviews", Review[].class);
    }

    public Hotel[] getHotelsByStarRating(int stars) {
        return null;
    }

    public City getWithCustomQuery(){
        return null;
    }

}
