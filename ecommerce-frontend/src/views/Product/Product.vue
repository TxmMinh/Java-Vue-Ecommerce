<!-- eslint-disable vue/multi-word-component-names -->
<template>
    <div class="container">
        <div class="row">
            <div class="col-12 text-center">
                <h3 class="pt-3">Our Products</h3>
                <router-link :to="{ name: 'AddProduct' }" style="float: right">
                    <button class="btn">Add Product</button>
                </router-link>
            </div>
        </div>
        <div class="row">
            <div v-for="product in products" :key="product.id" class="col-xl-4 col-md-6 col-12 pt-3 d-flex">
                <ProductBox :product="product"></ProductBox>
            </div>
        </div>
    </div>
</template>

<script>
import ProductBox from '../../components/Product/ProductBox.vue'
export default ({
    // eslint-disable-next-line vue/multi-word-component-names
    name: "Product",
    props: ["products"],
    components: { ProductBox },
})
</script>
