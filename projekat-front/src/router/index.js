import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import AdminView from '../views/AdminView.vue'
import CreateMenadzerView from '../views/CreateMenadzerView.vue'
import CreateDostavljacView from '../views/CreateDostavljacView.vue'
import RegisterView from '../views/RegisterView.vue'
import KupacView from '../views/KupacView.vue'
import MenadzerView from '../views/MenadzerView.vue'
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
    path: '/register',
    name: 'register',
    component: RegisterView
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
  },
  {
    path: '/kupac',
    name: 'kupac',
    component: KupacView
  },
  {
    path: '/menadzer',
    name: 'menadzer',
    component: MenadzerView
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
