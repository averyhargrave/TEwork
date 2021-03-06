// a skeleton of this file is automatically generated when you select Vuex as an option on vue create
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex) // I want to use Vuex to manage my shared data

export default new Vuex.Store({ // export will expose this data to processes outside this file
                                //    and create a new Vuex datastore
  state: { // attribute to hold any data stored in the data store
    name: 'Cigar Parties for Dummies',
    description: 'Host and plan the perfect cigar party for all of your squirrelly friends.',
    filter: 0, // Control which array elements are displayed
    reviews: [
      {
        reviewer: 'Malcolm Gladwell',
        title: 'What a book!',
        review:
          "It certainly is a book. I mean, I can see that. Pages kept together with glue and there's writing on it, in some language.",
        rating: 3,
        favorited: false
      },
      {
        reviewer: 'Tim Ferriss',
        title: 'Had a cigar party started in less than 4 hours.',
        review:
          "It should have been called the four hour cigar party. That's amazing. I have a new idea for muse because of this.",
        rating: 4,
        favorited: false
      },
      {
        reviewer: 'Ramit Sethi',
        title: 'What every new entrepreneurs needs. A door stop.',
        review:
          "When I sell my courses, I'm always telling people that if a book costs less than $20, they should just buy it. If they only learn one thing from it, it was worth it. Wish I learned something from this book.",
        rating: 1,
        favorited: false
      },
      {
        reviewer: 'Gary Vaynerchuk',
        title: 'And I thought I could write',
        review:
          "There are a lot of good, solid tips in this book. I don't want to ruin it, but prelighting all the cigars is worth the price of admission alone.",
        rating: 3,
        favorited: false
      }
    ],
  }, // end of the state attribute

  mutations: { // methods to change data in the datastore - called by components
               // mutation names: all UPPERCASE with _ separating words

    ADD_REVIEW(state, review) {      // Add a review to our data array - receives state and the new review
      state.reviews.unshift(review); // Take the new review passed and add it to the start of the array
    },
    UPDATE_FILTER(state, filter) { // Set the filter value used to filter the elements displayed
      state.filter = filter;       
    },
    FLIP_FAVORITED(state, reviewToChange) { // Toggle the favorited attribute (boolean)
      reviewToChange.favorited = !reviewToChange.favorited;
    }
  }, // end of mutations
  actions: {
  },
  modules: {
  },
  strict: true // strict attribute tells Vue whether to limit changes to any data in the datastore to mutations
               //     true: only changes through mutations are allowed, stop components from making changes
               //     false: allow components to directly change data (violating Single Responsibility Rule)
})
