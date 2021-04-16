<template>
  <div class="main">
    <h2>Product Reviews for {{ name }}</h2>

    <p class="description">{{ description }}</p>

    <div class="well-display">
      <div class="well">
        <!-- if AverageRating is clicked set the filter to value that is not a valid rating -->
        <!-- so all reviews will be displayed                                               -->
         <!-- v-on adds an event handler to an html element                                 --> 
         <!-- v-on:event="what-you-want-to-do"                                              -->                                        
        <span class="amount" v-on:click="filter=0">{{ averageRating }}</span>
        Average Rating
      </div>

      <div class="well">
        <!-- add an event to the span so it's clickable                         -->
        <!-- when it's clicked we will set a variable to the number of stars    -->
        <!-- that variable will be used to filter the ratings displayed         -->
        <!-- {{}} is used when you want to substitute the value of a property in html -->
        <span class="amount" v-on:click="filter=1" >{{ numberOfOneStarReviews }}</span>
        <!-- if the numbers of stars is not 1 add an 's' to the words           -->
        <!-- use the conditional operator - ?                                   -->
        <!-- conditional operator:   condition ? value-if-true : value-if-false -->
        <!-- if the number of stars is 1, don't add an 's', if not add an 's    -->
        1 Star Review{{ numberOfOneStarReviews === 1 ? '' : 's' }}
      </div>

      <div class="well">
        <span class="amount" v-on:click="filter=2">{{ numberOfTwoStarReviews }}</span>
        2 Star Review{{ numberOfTwoStarReviews === 1 ? '' : 's' }}
      </div>

      <div class="well">
        <span class="amount" v-on:click="filter=3">{{ numberOfThreeStarReviews }}</span>
        3 Star Review{{ numberOfThreeStarReviews=== 1 ? '' : 's' }}
      </div>

      <div class="well">
        <span class="amount" v-on:click="filter=4">{{ numberOfFourStarReviews }}</span>
        4 Star Review{{ numberOfFourStarReviews === 1 ? '' : 's' }}
      </div>

      <div class="well">
        <span class="amount" v-on:click="filter=5">{{ numberOfFiveStarReviews }}</span>
        5 Star Review{{ numberOfFiveStarReviews === 1 ? '' : 's' }}
      </div>
    </div>
    <!-- add a link that will change the showForm property to true which will cause the form to display -->
    <!-- and an <a> element with a placeholder href because we want control what happens when clicked   -->
    <!-- use v-on to add a 'click' event handler to the link toggle showForm between true and false     -->
    <!--       if showForm is true, make it false and if false, make it true                            -->
    <!--       to toggle a boolean: !boolean-name                                                       -->
    <!-- display "Show Form" if form is hidden or "Hide Form", is form is showing                       -->
    <a
      id="show-form-button"
      href="#"   
      v-on:click="showForm = !showForm"   
    >{{showForm ? "Hide Form" : "Show Form"}}
    </a>
    <!-- v-if - add this element to the DOM (displayed) if the specified condition is true  -->
    <!--        remove this element from the DOM (not displayed) if the condtion id false   -->
    <!-- v-show - hide (false) or display (true) this element that is already in the DOM    -->
    
    <!-- When the submit button is clicked we want to add the data from the form to reviews array -->
    <!-- add a v-on for a click event to run the addReview method when the button is clicked      -->
    <!-- we need tell the browser to NOT perform the default behavior of the submit button        -->
    <!--    instead of sending the form to a URL with an HTTP request, WE have a method to run    -->
    <!-- v-on:submit.prevent=""   will prevent the default behavior                               -->

    <!-- each input item in the form is two-way bound to an attribute in a newRewiew object       -->
    <!-- v-model is how we connect html elements with data in the JavaScript code                 -->
    <form v-if="showForm === true" v-on:submit.prevent="addReview">  <!-- show this form if showForm property is true -->
      <div class="form-element">
        <label for="reviewer">Name:</label>
        <input id="reviewer" type="text" v-model="newReview.reviewer" /> <!-- / close input tag -->
      </div>
      <div class="form-element">
        <label for="title">Title:</label>
        <input id="title" type="text" v-model="newReview.title" />
      </div>
      <div class="form-element">
        <label for="rating">Rating:</label>
        <select id="rating" v-model.number="newReview.rating">
          <option value="1">1 Star</option>
          <option value="2">2 Stars</option>
          <option value="3">3 Stars</option>
          <option value="4">4 Stars</option>
          <option value="5">5 Stars</option>
        </select>
      </div>
      <div class="form-element">
        <label for="review">Review</label>
        <textarea id="review" v-model="newReview.review"></textarea>
      </div>
      <!-- only want to allow a subn=mit when we have a valid form                           -->
      <!-- not allow submit to be active until we have determined we have a valid form       -->
      <!-- we will use a method to enable the submit button when it says the form is valid   -->
      <!-- add the disabled attribute to the button if the method says the form is not valid --> 
      <!-- use v-bind to add the disabled attribute depending on what isFormValid returns    --> 
      <button v-bind:disabled = "!isFormValid">Submit</button>
    
      <!-- NOT have the cancel button submit any form data when clicked      -->
      <!-- WHEN it is clicked do nothing and reset the form                  -->
      <button v-on:click.prevent="resetForm" type="cancel">Cancel</button>
    </form>
    <!-- Generate one of the following div for each element in               -->
    <!--     array returned by the filterReviews property (in computed)      -->
    <!-- Add a v-for to the div to loop through the filterReview array       -->
    <!-- v-for requires a v-bind to assign a variable in order to keep track -->
    <!--    of where each array element appears on the screen                -->
    <!-- add the class favorited to the div when review.favorited is true    -->
    <!--       using v-bind:class={ classname : variable }                   -->
    <!-- v-bind connects an html element/attribute to a program variable     -->
    <div
      class="review"
      v-bind:class="{ favorited: review.favorited }"
      v-for="review in filterReviews"  
      v-bind:key="review.id"
    >
      <h4>{{ review.reviewer }}</h4>
      <div class="rating">
        <img
          src="../assets/cigar.png"
          v-bind:title="review.rating + ' Star Review'"
          class="ratingStar"
          v-for="n in review.rating"
          v-bind:key="n"
        />
      </div>
      <h3>{{ review.title }}</h3>

      <p>{{ review.review }}</p>

      <p>
        Favorite?
        <!-- add  a checkbox for the review                                           -->
        <!-- if the checkbox is checked store that value in review.favorited variable --> 
        <!-- a checkbox return true if checked false if not                           -->
       <input type="checkbox" v-model="review.favorited" />
      </p>
    </div>
  </div>
</template>

<script>
export default {
  name: "product-review",
  data() {
    return {
      name: "Cigar Parties for Dummies",
      description:
        "Host and plan the perfect cigar party for all of your squirrelly friends.",
      showForm: false,  
      filter: 0,         // used to filter the elements from the array that are displayed
      newReview: {},
      reviews: [
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
    };
  },  // end of data()
  computed: { // computed properties are generated whenever data changes on screen
    isFormValid() {  // Determine if all the newReview fields have values - return true if they do, false if not

      return this.newReview.reviewer    // true if reviewer is not empty (truthy)
          && this.newReview.title       // true if title is not empty (truthy)
          && this.newReview.rating      // true if rating is not empty (truthy)
          && this.newReview.review      // true if review is not empty (truthy)
    },
    averageRating() {
      let sum = this.reviews.reduce((currentSum, review) => {
        return currentSum + review.rating;
      }, 0);
      return (sum / this.reviews.length).toFixed(1); // calculate the average and round to 1 decimal place
    },
    numberOfOneStarReviews() {
      // call the numberOfReviews method passing it the array to search 
      //          and the number of stars we want it to count
      return this.numberOfReviews(this.reviews, 1);  
    },
    numberOfTwoStarReviews() {
      // call the numberOfReviews method passing it the array to search 
      //          and the number of stars we want it to count
      return this.numberOfReviews(this.reviews, 2);
    },
    numberOfThreeStarReviews() {
      return this.numberOfReviews(this.reviews, 3);
    },
    numberOfFourStarReviews() {
      return this.numberOfReviews(this.reviews, 4);
    },
    numberOfFiveStarReviews() {
      return this.numberOfReviews(this.reviews, 5);
    },
    filterReviews() {  // Filter reviews based on the value in the variable
    // use an anon-func with filter to get elements - send each element one at a time
    // .filter return true if the element its given should be result, flase if not
    // This will return an array of reviews that match the filter variable
    return this.reviews.filter((aReview) => {  
      // return true if rating === filter or filter === 0, false if not
        return aReview.rating === this.filter || this.filter === 0; 
    // alternate:
    // if (aReview.rating === this.filter || this.filter === 0) {
    //   return true;
    // }
    // else {
    //      return false;
    //      }
        })    
    }  // end of filterReviews
 },  // end of computer section
 
  methods: {  // methods are run when they are called/referenced
    // method to receive and array and a rating number
    //   return the number of elements in the array with the rating number
    //   (reduce the array to a count of ratings with the value passed)
    // reduce takes one parameter which is a function that takes
    //  two parameters a value to hold the result and an element
    //  an initial value for the result is optional (but recommended)
    //  each time the function is called the result is updated and returned based
    //  on the element
 
    numberOfReviews(reviews, starType) {
      return reviews.reduce((currentCount, review) => {
        // if the rating in the current element equals the startype (rating)
        //     add 1 to current count, otherwise add 0 to current count
         return currentCount + (review.rating === starType ? 1 : 0);
        // alternate code:
        // truthy / falsy - true=1, false=0
        // if a condition is true it returns a 1, if it's false, it returns a 0
        // if rating === starType add 1, if not add 0
        //return currentCount + (review.rating === starType); 
      }, 0);
    },  // end of numberOfReviews
    // This method will add a review from the form to the beginning of the reviews array
    // The data from the form was two-way bound to an object called newReview
    //     the object newReview should have all the data from the form
    //     all we need to do is add the newReview object to the reviews array
    addReview() {
      this.reviews.unshift(this.newReview)   // add newReview to the start of reviews array
      this.resetForm()                       // call another method from this one
    },
    // This method will remove the form from the display and clear it's fields/attributes
    resetForm(){
      this.showForm = false;   // hide the form
      this.newReview = {}      // clear the new review object
    }
  }    // end of methods section
};  // end of export 
</script>

<style scoped>
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

