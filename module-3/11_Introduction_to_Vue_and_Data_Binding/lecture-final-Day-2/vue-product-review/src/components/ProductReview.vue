<template>  <!-- Our html goes here -->
    <div class='main'>  <!-- Place our code in the main part of this page -->
       <h2>Product Reviews for {{ title }}</h2> <!-- Use a mustache {{variable}} to indicate you want data from the component placed here -->
       <p class='description'>{{words}}</p>     <!-- mustache expression - {{variable}}      -->

       <!-- The rating boxes will go here                                                    -->
       <div class="well-display">  
           <div class="well">   <!-- Average rating box    --> 
              <span class="amount">{{averageRating}}</span> <!-- span lets us style the middle of an element-->
              Average Rating
           </div>
           <div class="well">   <!-- One-star rating box    --> 
              <span class="amount">{{numberOfOneStarReviews}}</span> <!-- span lets us style the middle of an element-->
              1 Star Rating
           </div>
             <div class="well">   <!-- Two-star rating box    --> 
              <span class="amount">{{numberOfTwoStarReviews}}</span> <!-- span lets us style the middle of an element-->
              2 Star Rating
           </div>
             <div class="well">   <!-- Three-star rating box    --> 
              <span class="amount">{{numberOfThreeStarReviews}}</span> <!-- span lets us style the middle of an element-->
              3 Star Rating
           </div>
             <div class="well">   <!-- Four-star rating box    --> 
              <span class="amount">{{numberOfFourStarReviews}}</span> <!-- span lets us style the middle of an element-->
              4 Star Rating
           </div>
             <div class="well">   <!-- Five-star rating box    --> 
              <span class="amount">{{numberOfFiveStarReviews}}</span> <!-- span lets us style the middle of an element-->
              5 Star Rating
           </div>
       </div>
       <!-- We want go through the array of reviews in our data                              -->
       <!--     and display the attributes for the a review                                  -->
       <!-- Use a v-for loop to go through the array of reviews                              -->
       <!-- syntax for a v-for:  v-for="element-name in array-name  v-bind:key='key-field'"" -->
       <!--   the 'key-field' is used by v-for to uniquely identify an instance on page      -->
       <!--   the 'key-field' does NOT have to be an existing attribute in the array         -->
       <!-- v-for will generate one html element for each element in the array               -->
       <div class='review'
            v-for="aReview in reviews" v-bind:key="aReview.id" 
       >  <!-- Hold the display of the product review data -->
           <h4>{{aReview.reviewer}}</h4> <!-- reviewer attribute from the array element             -->
          <!-- generate one star image based on the value rating                                    -->
          <!-- use v-for to generate  the img tags                                                  -->
          <!-- v-for requires a name for an element in an array - we don't have an array of ratings -->
          <!--       so it doesn't matter what name we use - we are using 'n' this time             -->
          <div class="rating">
             <img 
                 src="../assets/star.png"
                 v-for="n in aReview.rating" v-bind:key="n"
             >
          </div>
           <h3>{{aReview.title}}</h3>    <!-- title attribute from the array element -->
           <p>{{aReview.review}}</p>     <!-- review attribute from the array element -->

          <!-- add a checkbox for Favorite - tied to the faviored attribute in array element     -->
          <!-- v-module ties a piece of data in the component to an element in the html          -->
          <!-- such that any change to data on the web page is relected in the data of component -->
          <!-- this is called two-way binding                                                    -->
          <p> 
            Favorite?
            <input type="checkbox" v-model="aReview.favorited"/>
          </p>
       </div>
    </div>
 
</template>

<script> // Our JavaScript code goes here
export default {  // Expose data from this code to external processes (like Vue)
    name: 'product-review',  // name for the default export is the component name in kabob-case
                             // kakob-case - all lowercase with - between the words

    // Any data used by this component needs to defined in a data() function in the export
    // Vue will call the data() for the component when it needs data for the component
    data() {      // data() function is provided for Vue to call when it needs data for the component
        return {  // we return an JavaScript object with the data we want to share with Vue
          title: '"Cigar Parties for Meatballs"',
          words: 'Host and plan the perfect cigar party for all your meatball friends',
          reviews: 
            [
              {
                reviewer: "Malcolm Gladwell",
                title: "What a book!",
                review:
                "It certainly is a book. I mean, I can see that. Pages kept together with glue and there's writing on it, in some language.",
                rating: 3,
                favorited: false
              },
              {
                reviewer: "Tim Ferriss",
                title: "Had a cigar party started in less than 4 hours.",
                review:
                "It should have been called the four hour cigar party. That's amazing. I have a new idea for muse because of this.",
                rating: 4,
                favorited: false
              },
              {
                reviewer: "Ramit Sethi",
                title: "What every new entrepreneurs needs. A door stop.",
                review:
                "When I sell my courses, I'm always telling people that if a book costs less than $20, they should just buy it. If they only learn one thing from it, it was worth it. Wish I learned something from this book.",
                rating: 1,
                favorited: false
              },
              {
                reviewer: "Gary Vaynerchuk",
                title: "And I thought I could write",
                review:
                "There are a lot of good, solid tips in this book. I don't want to ruin it, but prelighting all the cigars is worth the price of admission alone.",
                rating: 3,
                favorited: false
              }
            ]
        }   // end of return
    }, // end of data() - comma indicates there is more in the export than just the data()

  // Add a computed section to the export to perform calculations to achieve value we need 
  computed: {  // Stuff in here will be computed every time a value changes in the data
               // computed value are NOT functions - they are used as if they were variabes
  

      // Compute the average of all the ratings
      // Go through the array of reviews and sum the rating values
      // Divide the sum of the ratings by the number of ratings
      averageRating() {
        let sum = this.reviews.reduce((totalRatings, aReview) => {
          return totalRatings + aReview.rating;  // Add the current elememt's rating to the sum
        }, 0)   // ,0 - initialized totalRating and make it a number
        return sum / this.reviews.length  // Return the average
      },

      // Define a computed value to determine the number of one-star reviews in the reviews array
      numberOfOneStarReviews() {
      // need a place to hold the count the number of one-star reviews
      // Go through the array one element at a time checking for 1-star reviews
      //    (reduce the elements in the array to the number of 1-star reviews)  
      // We can use the .reduce() array function to do this!
      
      // .reduce() function takes one argument: a function that receives two arguments: reducer, anElement
      //           reducer is the variable the array is reduced to - the numberOfReviews in this example
      //           anElement is the current element in the array

      return this.reviews.reduce((currentCount, anElement) => {  // currentCount will have the number of 1-star reviews
                  if(anElement.rating === 1) {
                    currentCount++
                  }
                  return currentCount
                 }, 0)  // ,0 initialize the reducer (currentCount) and sets its type to a number
      },  // end of numberOfOneStarReviews

      // Define a computed value to determine the number of two-star reviews in the reviews array
      numberOfTwoStarReviews() {
          return this.reviews.reduce((currentCount, anElement) => {  // currentCount will have the number of 1-star reviews
                  if(anElement.rating === 2) {
                    currentCount++
                  }
                  return currentCount
                 }, 0)  // ,0 initialize the reducer (currentCount) and sets its type to a number
      },  // end of numberOfTwoStarReviews

      // Define a computed value to determine the number of three-star reviews in the reviews array
      numberOfThreeStarReviews() {
          return this.reviews.reduce((currentCount, anElement) => {  // currentCount will have the number of 1-star reviews
                  if(anElement.rating === 3) {
                    currentCount++
                  }
                  return currentCount
                 }, 0)  // ,0 initialize the reducer (currentCount) and sets its type to a number
      },  // end of numberOfThreeStarReviews

      // Define a computed value to determine the number of four-star reviews in the reviews array
      numberOfFourStarReviews() {
          return this.reviews.reduce((currentCount, anElement) => {  // currentCount will have the number of 1-star reviews
                  if(anElement.rating === 4) {
                    currentCount++
                  }
                  return currentCount
                 }, 0)  // ,0 initialize the reducer (currentCount) and sets its type to a number
      },  // end of numberOfFourStarReviews
            // Define a computed value to determine the number of Five-star reviews in the reviews array
      numberOfFiveStarReviews() {
          return this.reviews.reduce((currentCount, anElement) => {  // currentCount will have the number of 1-star reviews
                  if(anElement.rating === 5) {
                    currentCount++
                  }
                  return currentCount
                 }, 0)  // ,0 initialize the reducer (currentCount) and sets its type to a number
      }  // end of numberOfFiveStarReviews
    }   // end of computed section
}  // end of export
</script>

<style scoped>  /* CSS for the component scoped indicates the styling should be limited this component only */
                /* Because this component may be used with other components with similiar structure         */
                /* and/or class and id names, we don't want to impact styling on those pages                */
                /* nor do WE want to impact styling on components used with us                               */
div.main {
  margin: 1rem 0;
}
div.main div.well-display {
  display: flex;
  justify-content: space-around;
}
div.main div.well-display div.well {
  display: inline-block;
  width: 15%;
  border: 1px black solid;
  border-radius: 6px;
  text-align: center;
  margin: 0.25rem;
}
div.main div.well-display div.well span.amount {
  color: darkslategray;
  display: block;
  font-size: 2.5rem;
}
div.main div.review {
  border: 1px black solid;
  border-radius: 6px;
  padding: 1rem;
  margin: 10px;
}
div.main div.review.favorited {
  background-color: lightyellow;
}
div.main div.review div.rating {
  height: 2rem;
  display: inline-block;
  vertical-align: top;
  margin: 0 0.5rem;
}
div.main div.review div.rating img {
  height: 100%;
}
div.main div.review p {
  margin: 20px;
}
div.main div.review h3 {
  display: inline-block;
}
div.main div.review h4 {
  font-size: 1rem;
}
</style>