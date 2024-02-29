import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import EditCategory from '@/views/Category/EditCategory.vue'
import Category from '@/views/Category/Category.vue'
import Product from '@/views/Product/Product.vue'
import AddProduct from '@/views/Product/AddProduct.vue'
import EditProduct from '@/views/Product/EditProduct.vue'
import Admin from '@/views/Admin.vue' 
import ShowDetails from '@/views/Product/ShowDetails.vue'
import ListProducts from '@/views/Category/ListProducts.vue'
import SignUp from '@/views/SignUp.vue'
import SignIn from '@/views/SignIn.vue'
import WishList from '@/views/Product/WishList.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  // add category 
  {
      path: '/admin/category/add',
      name: 'AddCategory',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import(/* webpackChunkName: "about" */ '../views/Category/AddCategory.vue')
  },
  {
    path: '/admin/category',
    name: 'Category',
    component: Category
  },
  // edit category
  {
    path: '/admin/category/:id',
    name: 'EditCategory',
    component: EditCategory
  },
  // category detail page
  {
    path: '/category/show/:id',
    name: 'ListProducts',
    component: ListProducts
  },
  // admin home page
  {
    path: '/admin',
    name: 'Admin',
    component: Admin
  },
  {
    path: '/admin/product',
    name: 'Product',
    component: Product
  },
  // add product
  {
    path: '/admin/product/new',
    name: 'AddProduct',
    component: AddProduct
  },
  // edit product
  {
    path: '/admin/product/:id',
    name: 'EditProduct',
    component: EditProduct
  },
  // show details of product
  {
    path: '/product/show/:id',
    name: 'ShowDetails',
    component: ShowDetails
  },
  // signup and signin
  {
    path: '/signup',
    name: 'SignUp',
    component: SignUp
  },
  {
    path: '/signin',
    name: 'SignIn',
    component: SignIn
  },
  {
    path: '/wishlist',
    name: 'WishList',
    component: WishList
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
