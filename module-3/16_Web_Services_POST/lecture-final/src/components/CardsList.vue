<template>
  <div>
    <div class="header">
      <h1>{{ title }}</h1>
      <!-- Add a button to link to add a new card to the board using the AddCard Vue Router path and current boardID as path variable  -->
      <router-link
        tag="button"
        class="btn addNewCard"
        :to="{ name: 'AddCard', params: {boardID: this.boardId} }"
        v-if="!isLoading"
      >Add New Card</router-link>
      <!-- this button with delete a board using the deleteBoard method when it is clicked -->
      <button
        class="btn btn-cancel deleteBoard"
        v-if="!isLoading"
        v-on:click="deleteBoard"
      >Delete Board</button>
    </div>
    <div class="loading" v-if="isLoading">
      <img id="loadingImg" src="../assets/DancingMeatball.gif" />
    </div>
    <div v-else>
      <div class="status-message error" v-show="errorMsg !== ''">{{errorMsg}}</div>
      <div class="boards">
        <board-column title="Planned" :cards="planned" :boardID="this.boardId" />
        <board-column title="In Progress" :cards="inProgress" :boardID="this.boardId" />
        <board-column title="Completed" :cards="completed" :boardID="this.boardId" />
      </div>
    </div>
    <div class="board-actions" v-if="!isLoading">
      <router-link to="/">Back to Boards</router-link>
    </div>
  </div>
</template>

<script>
import boardsService from "../services/BoardService";
import BoardColumn from "@/components/BoardColumn";

export default {
  name: "cards-list",
  components: {
    BoardColumn
  },
  data() {
    return {
      title: "",
      boardId: 0,   // contains the id of the current board
      isLoading: true,
      errorMsg: ""
    };
  },
  methods: {
    retrieveCards() {
      boardsService
        .getCards(this.boardId)
        .then(response => {
          this.title = response.data.title;
          this.$store.commit("SET_BOARD_CARDS", response.data.cards);
          this.isLoading = false;
        })
        .catch(error => {
          if (error.response && error.response.status === 404) {
            alert(
              "Board cards not available. This board may have been deleted or you have entered an invalid board ID."
            );
            this.$router.push("/");
          }
        });
    },
        deleteBoard() {
        // Confirm user action using JavaScript confirm box
        if (confirm("Are you sure you want to delete this board and all associated cards? This action cannot be undone.")) {
        
        // if action confirmed by user, call service method to delete the current board
        boardsService
          .deleteBoard(this.boardId)                // Call deleteBoard service with boardId of current board
          .then(response => {                       // Wait for API call to be done and then...
            if (response.status === 200) {          // If delete API call was successful
              alert("Board successfully deleted");  //          Display message with JavaScript alert box
              this.$store.commit("DELETE_BOARD", this.boardId); // Use data store mutation to delete board from data store
              this.$router.push("/");                           // go back to home page
            }
          })
          .catch(error => {                         // If delete API call was not successful
            if (error.response) {                   //    if it was a response error
              this.errorMsg =                       //               set the errorMsg property
                "Error deleting board. Response received was '" + // to these words
                error.response.statusText +                       // and the text of the HTTP error
                "'.";                                             // and punctuate correctly
            } else if (error.request) {             //     if it was a request error
              this.errorMsg =                       //                   set the errorMsg property
                "Error deleting board. Server could not be reached."; //    to these words
            } else {                                //     it not a response error nor a request error
              this.errorMsg =                        //                   set the errorMsg property
                "Error deleting board. Request could not be created."; //    to these words
            }
          });
      }
    }
  },
  created() {
    this.boardId = this.$route.params.id;
    this.retrieveCards();
  },
  computed: {
    planned() {
      return this.$store.state.boardCards.filter(
        card => card.status === "Planned"
      );
    },
    inProgress() {
      return this.$store.state.boardCards.filter(
        card => card.status === "In Progress"
      );
    },
    completed() {
      return this.$store.state.boardCards.filter(
        card => card.status === "Completed"
      );
    }
  }
};
</script>

<style>
.boards {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  grid-gap: 20px;
}
.board-actions {
  text-align: center;
  padding: 20px 0;
}
.board-actions a:link,
.board-actions a:visited {
  color: blue;
  text-decoration: none;
}
.board-actions a:hover {
  text-decoration: underline;
}
.btn.addNewCard {
  color: #fff;
  background-color: #508ca8;
  border-color: #508ca8;
}
.header {
  display: flex;
  align-items: center;
}
.header h1 {
  flex-grow: 1;
}
#loadingImg {
  height: 200px;
  width:  200px;
}
</style>
