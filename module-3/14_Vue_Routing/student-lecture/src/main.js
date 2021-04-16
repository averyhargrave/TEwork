// This is created from options specified when you do the vue create command
import Vue from 'vue'          // Access the code to support Vue
import App from './App.vue'    // Access the code for our main page App.vue
import store from './store'    // Access the code to support the Vuex datastore
import router from './router'  // Access the code to support the Vue router

Vue.config.productionTip = false

new Vue({                      // Create a new instance of Vue
  store,                       // Tell Vue we are using the Vuex datastore
  router,                      // Tell Vue we are using the Vue router
  render: h => h(App)          // Tell Vue our main page is App.vue component 
}).$mount('#app')              // Tell Vue to add whatever it generates to the index.html element with id='app'
