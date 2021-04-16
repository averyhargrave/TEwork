// This tells Vue Router what paths it will handle and which views to display for those paths

import Vue from 'vue'                                 // Give me access to the Vue code
import VueRouter from 'vue-router'                    // Give me access to the Vue Router code
import Products from '@/views/Products.vue'           // Include the ProductsList.vue code
import ProductDetail from '@/views/ProductDetail.vue' // Include the ProductsList.vue code

Vue.use(VueRouter)                               // We want to use the VueRouter for this app

// routes that Vue Router will manage are defined in an array called routes
// a route is like a @RequestMapping in an MVC controller to assign a URL path to a process
const routes = [
  { // an element in the routes array is an object with the attributes of the route - associate a path to a view
    path: '/',          // the URL path we want to associate with a router view
    name: 'products',   // name for the path router view association
    component: Products // view router component associated with the path - stored in the views folder
  },
  // We need to define a dynamic path that takes a path variable 
  // a dynamic path has a path variable defined with a :variable-name
  {
    path: '/product/:id',    // this path has a path variable called id
    name: 'product-detail',  // name for the path-view association to be used in v-bind:to router link
    component: ProductDetail // name of router view in the views folder
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
