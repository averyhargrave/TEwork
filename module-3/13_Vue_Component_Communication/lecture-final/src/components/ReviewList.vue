<template>
<!-- generate one review-list for each element in the fileteredReviews array -->
  <div class="reviews">
    <!-- include the ReviewDisplay component and send it the current current as a prop (v-bind:review) -->
    <!-- v-bind:review connect the review attribute in the component to the "review" props attribute     -->
    <review-display
      v-for="review in filteredReviews"
      v-bind:key="review.title"
      v-bind:review="review"
    />
  </div>
</template>

<script>
import ReviewDisplay from "./ReviewDisplay";

export default {
  name: "review-list",
  components: {
    ReviewDisplay
  },
  computed: {
    filteredReviews() {
      // this value will be to filter the reviews - we need to get it from the data store filter attribute
      const reviewsFilter = this.$store.state.filter
      
      // copy the reviews array from the data store into this component so we can use
      const reviews = this.$store.state.reviews
      // .filter() - return an array of elements where the anon-func returned true for the element it was sent
      return reviews.filter(review => {
        // determine if the current element rating in the reviews array matches the reviewsFilter value
        // return true if the reviewsFilter value is 0 or rating in element == reviewsFilter value
        return reviewsFilter === 0 ? true : reviewsFilter === review.rating;
      });
    }
  }
};
</script>

<style>
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

div.main div.well-display div.well {
  cursor: pointer;
}
</style>
