import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import AdminView from '../views/AdminView.vue'
import CreateMenadzerView from '../views/CreateMenadzerView.vue'
import CreateDostavljacView from '../views/CreateDostavljacView.vue'
import RegisterView from '../views/RegisterView.vue'
import CreateRestoranView from '../views/CreateRestoranView.vue'
import UserProfileView from '../views/UserProfileView.vue'
import UpdateUserProfileView from '../views/UpdateUserProfileView.vue'
import MenadzerView from '../views/MenadzerView.vue'
import KupacView from '../views/KupacView.vue'
import RestoranView from '../views/RestoranView.vue'
import KorpaView from '../views/KorpaView.vue'
import DostavljacView from '../views/DostavljacView.vue'

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
    path: '/create-restoran',
    name: 'create-restoran',
    component: CreateRestoranView
  },
  {
    path: '/profile',
    name: 'profile-view',
    component: UserProfileView
  },
  {
    path: '/profile-update',
    name: 'profile-update',
    component: UpdateUserProfileView
  },
  {
    path: '/menadzer',
    name: 'menadzer',
    component: MenadzerView
  },
  {
    path: '/restorani/:id',
    name: 'restoran',
    component: RestoranView
  },
  {
    path: '/kupac',
    name: 'kupac',
    component: KupacView
  },
  {
    path: '/korpa',
    name: 'korpa',
    component: KorpaView
  },
  {
    path:'/dostavljac',
    name: 'dostavljac',
    component: DostavljacView
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
