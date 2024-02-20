<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <router-link :to="{ name: 'Category' }">
    <btn class="btn btn-primary btn-lg">Admin Categories</btn>
  </router-link>
  <br/>
  <router-link :to="{ name: 'Product' }">
    <button class="btn btn-primary btn-lg">Admin Product</button>
  </router-link>
</template>

<script>
export default {
  setup() {},
};
</script>

<style scoped>
  .btn {
    margin-bottom: 20px;
  }
</style>
