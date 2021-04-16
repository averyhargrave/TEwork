// axios is a framework for making REST API calls from JavaScript
// being a framework we have to understand how it is intended to be used

import axios from 'axios';  // Give me access to axios code

const http = axios.create({        // instantiate an axios object
  baseURL: "http://localhost:3000" // to reference this server
});

export default {  // expose functions/data to outside processes

  // Call the server defined for axios object with an GET request 
  //      and the path specified
  // axios makes the request to the server asynchronously
  // doesn't wait for the server to respond before continuing
  // it issues a promise to let you know when the server is done
  getBoards() {
    return http.get('/boards');  // starts the api request and returns a promise
                                 //               to let use know when the server is done
  },
  // get the cards for the boardID passed - return a promise from the API call
  getCards(boardID) {
    return http.get(`/boards/${boardID}`) // note the tick-marks around the variable notation
                                          //  tick-marks indicate interpolation
                                          //  use interpolation for a string with variables
                                          //  we have used this in console.log statements
  },
  // return an array of cards for the id given
  // the .then() is coded here NOT in the app that calls this function
  getCard(boardID, cardID) {
    return http.get(`/boards/${boardID}`).then((response) => {
      const cards = response.data.cards;  // copy the data from the response to our variable
      return cards.find(card => card.id == cardID);  // Search the response data for cards we want
    })
  }

}
