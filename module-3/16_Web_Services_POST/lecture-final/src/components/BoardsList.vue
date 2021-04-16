<template>
  <div id="sideNav">
    <h1>My Kanban Boards</h1>
    <div class="boards">
      <!-- This div will display an error message if one exists                         -->
      <!--      v-show="errorMsg != "" - display only if errorMsg is not empty          -->
      <div class="status-message error" v-show="errorMsg !== ''">{{errorMsg}}</div>

      <div class="loading" v-if="isLoading">
        <img id="loadingImg" src="../assets/ping_pong_loader.gif" />
      </div>
      <!-- v-else - this will displayed only if the v-if above is false                         -->
      <!-- display this when we are done loading the page (isLoading == "false")                -->
      <!-- router-link will generate a <div> tag rather than <a> (tag="div")                    -->
      <!--        to avoid default behavior of <a> tag                                          -->
      <!-- Generate one router-link for each element in the boards array in the Vuex data store -->
      <!-- v-bind:style="" allows you to add a CSS style to this specific element               -->
      <router-link :to="{ name: 'Board', params: { id: board.id } }"
        class="board"
        v-for="board in this.$store.state.boards"
        v-bind:key="board.id"
        v-bind:style="{ 'background-color': board.backgroundColor }"
        v-else
        tag="div"
      >
        {{ board.title }}
      </router-link>
      <!-- This button will be displayed only if the page already loaded (!isLoading)      -->
      <!--      and the AddBoard is not displayed (!showAddBoard)                          -->
      <!-- WHen the button is clicked we will toggle the showAddBoard attribute            -->
      <button class="btn addBoard" v-if="!isLoading && !showAddBoard" v-on:click="showAddBoard = !showAddBoard">Add Board</button>
   
      <!-- Display this form if the showAddBoard attribute is true (v-if="showAddBoard"    -->
      <form v-if="showAddBoard">  <!-- gather data for a new board from the user           -->
                                  <!-- the data entered by ther will be stored in newBoard -->
                                  <!-- all the input fields are two-way bound to newBoard  -->
        Board Title:
        <input type="text" class="form-control" v-model="newBoard.title" />
        Background Color:
        <input type="text" class="form-control" v-model="newBoard.backgroundColor" />

        <!-- The submit button will run the method saveNewBoard                       -->
        <button class="btn btn-submit" v-on:click="saveNewBoard">Save</button>
        <!-- The cancel button will toggle the showAddBoard attribute                 -->

        <button class="btn btn-cancel" v-on:click="showAddBoard = !showAddBoard">Cancel</button>
      </form>
    </div>
  </div>
</template>

<script>
import boardsService from '../services/BoardService';

export default {
  data() {
    return {
      isLoading: true,
      showAddBoard: false,
      newBoard: {
        title: '',
        backgroundColor: this.randomBackgroundColor()
      },
      errorMsg: ''  // Hold any error message generated - displayed if it has a value
    };
  },
  created() {  // Do this before the html is rendered and sent to the browser
    this.retrieveBoards();   // run the retreiveBoards method to get the data from an API
  },
  methods: {     // Stuff in here is only executed when it's called
    retrieveBoards() {   // called from created() hook to load the data from API into the Vuex data store
      boardsService.getBoards().then(response => {          // call the getBoards() function in boardsService
                                                            //      and wait for the promise from API call
                                                            //      to be fulfilled (.then)
                                                            //      when the API call is done...
                                                            //          send the response to a function
        this.$store.commit("SET_BOARDS", response.data);    // Use the data store mutation to store data
                                                            //     from the response (response.data)
        this.isLoading = false;                             // indicate we are done loading data

        // if the destination route is named "Home" AND the API call was successful AND we have data
        if (this.$route.name == "Home" && response.status === 200 && response.data.length > 0) {
          // redirect ($router.push() to the /board path with the id of the first board
          this.$router.push(`/board/${response.data[0].id}`);
        }
      });
    },
    saveNewBoard() {  // This will save the new board in the data store using a service in boardsService
    // data from the user will be store in the newBoard object

    // call the AddBoard service to add the newBoard object to the data store
    // wait for the API call to be done (.then) and check to be sure there was no errors
    boardsService.addBoard(this.newBoard).then(response => {  // send the API response to a function 
      if(response.status === 201) { // if post was successful (HTTP status 201)
          this.retrieveBoards()     // refresh the boards in the data store from API data source
          this.showAddBoard = false // indicate we are done add the adding board
          this.newBoard = {         // reset the newBoard object since we are done with it
                          title:"",                                     // title to empty
                          backgroundColor : this.randomBackgroundColor  // background to random color
          }
      }
    })  // end of call to AddBoard() in boardsService
    .catch(error => {  // handle any error that comes from the API call in AddBoard()
      // two types of errors may be returned from an API call:
      //     response error - you have HTTP error status code (4xx or 5xx)
      //     request error - problem getting to the server - your request was not received
      if (error.response) {  // if there was an http error status
        this.errorMsg = "there was an HTTP repsonse error"  // set the error message 
      }
      else {   // it must be a request error
         this.errorMsg = "there was an HTTP request error"  // set the error message 
      }
    })
    
    },
    randomBackgroundColor() {
      return "#" + this.generateHexCode();
    },
    generateHexCode() {
      var bg = Math.floor(Math.random()*16777215).toString(16);
      if (bg.length !== 6) bg = this.generateHexCode();
      return bg;
    }
  }
};
</script>

<style scoped>
div#sideNav {
  height: 100%;
  width: 20%;
  position: fixed;
  z-index: 1;
  left: 0;
  top: 0;
  padding-top: 20px;
  padding-bottom: 20px;
  overflow-x: hidden;
  border-right: solid lightgrey 1px;
}

h1 {
  text-align: center;
}

.boards {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
}
.board {
  color: #f7fafc;
  border-radius: 10px;
  padding: 40px;
  flex: 1;
  margin: 10px;
  text-align: center;
  cursor: pointer;
  width: 60%;
}
.addBoard {
  color: #f7fafc;
  border-radius: 10px;
  background-color: #28a745;
  font-size: 16px;
  width: 60%;
  margin: 10px;
  padding: 20px;
  cursor: pointer;
}
.form-control {
  margin-bottom: 10px;
}
.btn {margin-bottom: 35px;}
.loading {
  flex: 3;
}
.board:hover:not(.router-link-active), .addBoard:hover {
  font-weight: bold;
}
.router-link-active {
  font-weight: bold;
  border: solid blue 5px;
}
</style>
