// This tell Vue Router what paths it will handle and which views to display for those paths

import Vue            from 'vue'                       // Give me access to the Vue code
import VueRouter      from 'vue-router'                // Give me access to the Vue Router code
import Products       from '@/views/Products.vue'      // include the Products.vue view code
import ProductDetail  from '@/views/ProductDetail.vue' // include the ProductDetail.vue view code
import AddReview      from '@/views/AddReview.vue'    // include the AddReview.vue view code

Vue.use(VueRouter)                   // We want to the the VueRouter for this app

// routes that Vue will manage are defined in an array called routes
// a route is like a @RequestMapping in an MVC controller to assign a URL path to a process
const routes = [
  { // an element in routes is an object with the attributes of the route - associate a path to a view
    path: '/',           // The URL path we want to associate with a router view
    name: 'products',    // name for the path router view association
    component: Products  // view router component associated with the path - stored in views folder
  },
  // we need to define a dynamic path that takes a path variable
  // a dynamic path  has a path variable defined with a :variable-name
  {
    path: '/product/:id',      // this path has a path variable called id
    name: 'product-detail',    // name for the path-view association use in v-bind:to router-link
    component: ProductDetail   // name if router view in the views folder 
  },
  {
    path: '/product/:id/add-review', // this path has a path variable called id
    name: 'add-review',              // name for the path-view association use in v-bind:to router-link
    component: AddReview             // name if router view in the views folder 
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
