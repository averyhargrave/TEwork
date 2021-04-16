<template>
  <div class="well">
    <span class="amount" v-on:click="updateFilter()">{{ numberOfReviews }}</span>
    {{ rating }} Star Review{{ numberOfReviews === 1 ? '' : 's' }}
  </div>
</template>

<script>
export default {
  name: "star-summary",
  props: ["rating"],  // props indicates a value coming from outside this component
  methods: {
    // Set the filter value in the data store when the box is clicked - use .commit()
    updateFilter() {
      this.$store.commit("UPDATE_FILTER", parseInt(this.rating))  // we need convert the rating to an int for the data store
    }
  },
  computed: {
    numberOfReviews() {
      // copy the reviews from the data store to this component so we can use them
      const reviews = this.$store.state.reviews
      return reviews.reduce((currentCount, review) => {
        // compare the rating on the current reviews element with the rating value passed as a props 
        return currentCount + (review.rating === parseInt(this.rating) ? 1 : 0);
      }, 0);
    }
  }
};
</script>

