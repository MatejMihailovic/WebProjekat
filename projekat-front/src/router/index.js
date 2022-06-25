import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import AdminView from '../views/AdminView.vue'
import CreateMenadzerView from '../views/CreateMenadzerView.vue'
import CreateDostavljacView from '../views/CreateDostavljacView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/admin',
    name: 'admin',
    component: AdminView
  },
  {
    path: '/create-menadzer',
    name: 'create-menadzer',
    component: CreateMenadzerView
  },
  {
    path: '/create-dostavljac',
    name: 'create-dostavljac',
    component: CreateDostavljacView
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
