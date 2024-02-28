<template>
  <div id="home">
    <div id="background-div" class="page-holder bg-cover">
      <div class="container py-5">
        <header class="text-left text-white py-5">
          <h3 class="mb-4 rounded" id="heading">
            <a href="#start-shopping" class="bg-white px-2 py-2 rounded">Start Shopping</a>
          </h3>
          <p class="lead mb-0 bg-dark p-1 rounded">Demo E-commerce</p>
        </header>
      </div>
    </div>

    <!-- display categories -->
    <div class="container">
      <div class="row">
          <div class="col-12 text-center">
            <h2 class="pt-3">Top categories</h2>
          </div>
      </div>
      <div class="row">
        <div v-for="index in this.categorySize" :key="index" 
        class="col-md-6 col-xl-4 co-12 pt-3 justify-content-around d-flex">
          <CategoryBox :category="categories[index-1]"></CategoryBox>
        </div>
      </div>
    </div>

    <hr/>
    <!-- display products -->
    <div class="container">
      <div class="row">
          <div class="col-12 text-center">
            <h2 class="pt-3">Top products</h2>
          </div>
      </div>
      <div class="row">
        <div v-for="index in this.productSize" :key="index" 
        class="col-md-6 col-xl-4 co-12 pt-3 justify-content-around d-flex">
          <ProductBox :product="products[index-1]"></ProductBox>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import CategoryBox from '@/components/Category/CategoryBox.vue';
import ProductBox from '@/components/Product/ProductBox.vue';

// @ is an alias to /src
// import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'HomeView',
  components: { CategoryBox, ProductBox },
  props: ["categories", "products"],
  data() {
    return {
      categorySize: 0,
      productSize: 0
    }
  },
  mounted() {
    this.categorySize = Math.min(6, this.categories.length);
    this.productSize = Math.min(6, this.products.length);
  }
}
</script>

<style>
  .page-holder {
    min-height: 100vh;
  }
  .bg-cover {
    background-size: cover !important;
  }
  #background-div {
    background: url("../assets/home.png");
  }
  #heading {
    font-weight: 400;
  }
</style>
