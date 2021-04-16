<template>
  <div></div>
</template>

<script>
import boardService from '../services/BoardService'
export default {
  name: "card-detail",
  data() {
    return {  
      cards: []         // Hold the data displayed on this data
    } // end of return
  }, // end of data()
  // We need the data for the page before Vue generates the html
  // so we will get data in a created() hook
  created() {
    // call the getCards() in boardService with the current boardID to get the data
    // the boardID is in the path - use this.$route.params.variableName - to get it
    // getCards() returns a promise from the API call to tell us when it's done
    //      so we .then() to wait for the API call to be done
    //            so we can access the data from the response
    boardService.getCards(this.$route.params.boardID).then(response => {
      // Because the response contains more than one attribute we need to tell it which one we want
      this.cards = response.data.cards // copy the data from the response into out cards attribute
      }) 
  }
};
</script>
