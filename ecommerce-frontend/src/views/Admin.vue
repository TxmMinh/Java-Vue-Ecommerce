<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <div class="adminPanel container">
    <router-link :to="{ name: 'Category' }">
      <btn class="btn btn-primary btn-lg">Admin Categories</btn>
    </router-link>
    <router-link :to="{ name: 'Product' }">
      <button class="btn btn-primary btn-lg">Admin Product</button>
    </router-link>
  </div>
</template>

<script>
export default {
  setup() {},
};
</script>

<style scoped>
.btn {
  margin: 55px 0;
  font-size: 30px;
  width: fit-content;

}
.adminPanel {
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>
