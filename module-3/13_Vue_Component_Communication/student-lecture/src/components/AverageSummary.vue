<template>
  <div class="well">
    <span class="amount" v-on:click="updateFilter()">{{ averageRating }}</span>
    Average Rating
  </div>
</template>

<script>
export default {
  name: "average-summary",
  methods: {
    updateFilter() { // This will set the filter value to 0 when the average ratings box is clicked
      // We need to call the Vuex datastore mutation to set the filter value using .commit()
      //         .commit(mutation-name, value);
      this.$store.commit("UPDATE_FILTER", 0 );
    }
  },
  computed: {
    averageRating() {
      // copy the reviews from the Vuex datastore into this component so we can use them
      const reviews = this.$store.state.reviews;
      let sum = reviews.reduce((currentSum, review) => {
        return currentSum + review.rating;
      }, 0);
      return (sum / reviews.length).toFixed(2);
    }
  }
};
</script>
