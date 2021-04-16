import axios from 'axios';  // Access the axios API framework for HTTP REST API calls

const http = axios.create({         // Instantiate an axios object 
  baseURL: "http://localhost:3000"  //      with the URL for the server we want to access
});

export default {  // expose the stuff in here to outside processes
  
  // Use interpolation (` `) to create a string containing values from a variable
  // Use single quotes (' ') to create a string containing no values from variables

  // axios .get(path-to-the-API-as-a-string)

  getBoards() {
    return http.get('/boards');  // Do a REST API GET request to the path specified
  },

   // REST API protocol says put the key of the resource to retrieve a path variable in GET
  getCards(boardID) {
    return http.get(`/boards/${boardID}`)  // Do a REST API GET request to the path specified
  },                                       //   include the value passed as a path variable

  // REST API protocol says put the key of the resource to retrieve a path variable in GET
  getCard(cardID) {
    return http.get(`/cards/${cardID}`)   // Do a REST API GET request to the path specified
  },                                      //   include the value passed as a path variable

  // REST API protocol says put the data to be added in the request body of the POST request
  //     axios knows to take the data you give it in a post and put it in request body
  // axios .post(path-as-a-string, data-to-posted) 
  addCard(card) {
    return http.post('/cards', card);     // Do a REST API POST to the path specified with the data to be posted
  },                                      //   include the value passed as the data to be posted

  // REST API protocol says put the key of the resource to update as a path variable for a PUT
  //          and put the data for the update in the request body (axios know to do this)
  // axios .put(path-as-a-string, data-to-posted) 
  updateCard(card) {
    return http.put(`/cards/${card.id}`, card); // Do a REST API PUT to the path specified with the id as a path variable
  },                                            //   include the value passed as the data to be posted

  // REST API protocol says put the key of the resource to delete as a path variable
  // axios .delete(path-as-a-string) 
  deleteCard(cardID) {
    return http.delete(`/cards/${cardID}`);  // Do a REST API DELETE to the path specified with the data passed as a path variable
  },
  // Add a new board to the API data store using an axios POST
  addBoard(newBoard) {
    return http.post('/boards',newBoard)  // DO a REST API POST to the path specified with data passed
  },
  // delete a board from the API data store
  // REST API protocol says put the key of the resource to delete as a path variable
  // axios .delete(path-as-a-string) 
  deleteBoard(boardId) {
    return http.delete(`/boards/${boardId}`)  // Do a REST API DELETE to the path specified with the data passed as a path variable
  }

}
