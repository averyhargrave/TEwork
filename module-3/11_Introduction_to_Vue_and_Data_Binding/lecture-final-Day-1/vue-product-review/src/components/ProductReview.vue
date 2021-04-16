<template>  <!-- Our html goes here -->
    <div class='main'>  <!-- Place our code in the main part of this page -->
       <h2>Product Reviews for {{ title }}</h2> <!-- Use a mustache {{variable}} to indicate you want data from the component placed here -->
       <p class='description'>{{words}}</p> <!-- mustache expression - {{variable}} -->
    </div>
</template>

<script> // Our JavaScript code goes here
export default {  // Expose data from this code to external processes (like Vue)
    name: 'product-review',  // name for the default export is the component name in kabob-case
                             // kakob-case - all lowercase with - between the words

    // Any data used by this component needs to defined in a data() function in the export
    // Vue will call the data() for the componentwhen it needs data for the component
    data() {      // data() function is provided for Vue to call when it needs data for the component
        return {  // we return an JavaScript object with the data we want to share with Vue
            title: 'Cigar Parties for Dummies',
            words: 'Host and plan the perfect cigar party for all your squirrelly friends',
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
    }
    }
}
</script>

<style scoped> /* Our CSS code goes here */
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