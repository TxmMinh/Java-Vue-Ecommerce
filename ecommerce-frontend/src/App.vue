<template>
  <NavBar :cartCount="cartCount" @resetCartCount="resetCartCount"
    v-if="!['SignUp', 'SignIn'].includes($route.name)"/> 
  <router-view v-if="categories && products" style="min-height: 60vh" 
  :products="products" :categories="categories" :baseURL="baseURL" @fetchData="fetchData" >
  </router-view>
  <Footer v-if="!['SignUp', 'SignIn'].includes($route.name)"/>
</template>

<script>
import NavBar from "./components/NavBar.vue";
import Footer from "@/components/Footer.vue";

const axios = require("axios");
export default ({
  components: {NavBar, Footer},
  data() {
    return {
      baseURL: "http://localhost:8081",
      categories: null,
      products: null,
      cartCount: 0
    }
  },
  methods: {
    async fetchData() {
      // api call to get all the categories
      await axios.get(`${this.baseURL}/category/list`)
      .then(res => this.categories = res.data)
      .catch(err => console.log("err ", err));

      // api call to get the products
      await axios.get(this.baseURL + "/product/list")
      .then(res => this.products = res.data)
      .catch(err => console.log("err ", err));

      // fetch cart item if token is present i.e logged in
      if(this.token) {
        axios.get(`${this.baseURL}/cart/list/?token=${this.token}`)
            .then(res => {
                const result = res.data;
                this.cartCount = result.cartItems.length;
            })
            .catch(err => console.log("err ", err));
      }
    },
    resetCartCount() {
      this.cartCount = 0;
    }
  },
  mounted() {
    this.token = localStorage.getItem("token");
    this.fetchData();
  }
})
</script>

<style>
html {
  overflow-y: scroll;
}
</style>
