import Vue from 'vue'
import VueRouter from 'vue-router'
import Home   from '../views/Home.vue'
import Board  from '../views/Board.vue'
import Card   from '../views/Card.vue'

Vue.use(VueRouter)

const routes = [  // router configuration array - associates paths to router views (in views folder)
  {
    path: '/',      // path
    name: 'Home',   // name for the path - may be used in <router-link> to reference this path
    component: Home // component in the views folder to use when the path is references - must be imported
  },
  {
    path: '/board/:id', // path with a path variable called id (dynamic path)
    name: 'Board',
    component: Board
  },
  {
    path: '/board/:boardID/card/:cardID',
    name: 'Card',
    component: Card
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
