<template>
  <div>
    <h1>My Kanban Boards</h1>
    <div class="boards">
      <!-- v-if indicates we want to display the element if the condition is true                         -->
      <!-- Only display the squirrel.gif image if isLoading is true                                       -->
      <!-- isLoading is attribute in this component set to true if page is in the process of being loaded -->
      <!--                                                 false if page is not being loaded              -->
      <!-- isLoading is initially set to true -->
      <div class="loading" v-if="isLoading">
        <img src="../assets/squirrel.gif" />
      </div>
      <!-- v-for - add a div for each element in the boards array     -->
      <div
        class="board"
        v-for="board in boards"
        v-bind:key="board.id"
        v-bind:style="{ 'background-color': board.backgroundColor }"
        v-else
      >
        <!-- router-link connects a link to a vue router path              -->
        <!-- v-bind:to="path or path name for the link"                    -->
        <!-- params: {variable : value} - adds a path variable to the path -->
        <!-- this router-link specifies the path: /board/:id               -->
        <router-link v-bind:to="{ name: 'Board', params: { id: board.id } }">
          {{ board.title }}
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
import boardsService from '../services/BoardService';  // Give us access to code in BoardService code 

export default {
  data() {
    return {
      boards: [],       // Data to be displayed in the page - loaded from an API
      isLoading: true   // Switch we use to indicate if page is in the process of being loaded
    };
  },
  created() {  // This code is executed before Vue creates the html to give to browser
  // We need to get data from an API to be included in the page
  // We need to get the data before the html tries to add it to the page

  // We are calling a function is boardsServices to get the data from an API call
  // boardsServices containing services used by this application
  
  // getBoards() in boardsServices makes an API call and returns the data
  // We don't know how long it will take the API to package up the data and send it back
  //    nor do we know how long it will take to construct the API call and send it to the server
  // So, we need to wait for getBoards() to return so we can copy the data from the response
  //     instead of just assuming getBoards() receiving the data was instantaneous and just continue
  // .then() say wait for the function to return before doing what's in the ()
  // .then() waits for the promise from the API call to be fulfilled

    boardsService.getBoards().then(response => { // call getBoards(), wait for it to finish, then...
      this.boards = response.data;  // copy the data from the API response to our boards array
      this.isLoading = false;       // Set the isLoading switch to false because we have the data we need 
    });
  }
};
</script>

<style scoped>
.boards {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.board {
  background-color: #f7fafc;
  border-radius: 10px;
  padding: 40px;
  flex: 1;
  margin: 0 20px;
  text-align: center;
}
.loading {
  flex: 3;
}
.board a:link,
.board a:visited {
  color: rgb(243, 243, 243);
  text-decoration: none;
}
</style>
