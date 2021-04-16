<template> <!-- generate one div for each element in filteredReviews -->
  <div class="reviews">
    <!-- include the ReviewDisplay component and send it the current review as a props                -->
    <!-- v-bind:review connects the review attribute in the component to the "review" props attribute -->
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
      // This value will be used to filter the reviews
      const reviewsFilter = this.$store.state.filter;
      const reviews = this.$store.state.reviews;
      // .filter() will return an array of elements where the anon-func returns true
      return reviews.filter(review => {
        // determine if the current element rating in the reviews array matches the reviewsFilter value 
        // return true if the reviewsFilter value is 0 or the rating in the element == reviewsFilter
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
