<template>
  <div id="app" class="main">
    <h1>Product Reviews for {{$store.state.name}}</h1> <!-- go to the datastore, get the name attribute from the state attribute -->
    <p class="description">{{$store.state.description}}</p>
    <div class="well-display">
      <average-summary />        <!-- use the AverageSummary.vue component                         -->
      <star-summary rating="1"/> <!-- use the StarSummary.vue component, passing it a rating value -->
      <star-summary rating="2"/> <!-- use the StarSummary.vue component, passing it a rating value -->
      <star-summary rating="3"/> <!-- use the StarSummary.vue component, passing it a rating value -->
      <star-summary rating="4"/> <!-- use the StarSummary.vue component, passing it a rating value -->
      <star-summary rating="5"/> <!-- use the StarSummary.vue component, passing it a rating value -->
    </div>
    <add-review />               <!-- use the AddReview.vue component                              -->
    <review-list />              <!-- use the ReviewList.vue component                             -->
  </div>
</template>

<script>
// import the file for each component we are using
import AverageSummary from "./components/AverageSummary.vue";
import StarSummary    from "./components/StarSummary.vue";
import AddReview      from "./components/AddReview.vue";
import ReviewList     from "./components/ReviewList.vue";

export default {
  name: "app",
  components: { // List the names of all the components we use;
    AverageSummary,
    StarSummary,
    AddReview,
    ReviewList
  }
};
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  max-width: 800px;
  margin: 60px auto 0 auto;
}
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
