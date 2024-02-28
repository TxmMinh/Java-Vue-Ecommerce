<template>
  <NavBar/> 
  <router-view v-if="categories && products" style="min-height: 60vh" 
  :products="products" :categories="categories" :baseURL="baseURL" @fetchData="fetchData" >
  </router-view>
  <Footer/>
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
      products: null
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
    }
  },
  mounted() {
    this.fetchData();
  }
})
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
