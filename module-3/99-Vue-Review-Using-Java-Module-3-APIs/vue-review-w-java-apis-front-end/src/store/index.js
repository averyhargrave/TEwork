import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    hotels: []
  },
  mutations: {
    SET_HOTELS(state, data) {
      state.hotels = data;
    },
  },
  actions: {},
  modules: {}
});
