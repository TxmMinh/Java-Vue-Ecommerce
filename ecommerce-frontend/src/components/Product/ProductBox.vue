<template>
    <div class="card w-100 h-100" style="margin-top: 10px">
        <div class="embed-responsive embed-responsive-16by9">
            <img :src="product.imageURL" class="card-img-top embed-responsive-item" alt="Card image cap">
        </div>
        <div class="card-body">
            <router-link :to="{name: 'ShowDetails', params: {id: product.id}}">
                <h5 class="card-title">{{ product.name }}</h5>
            </router-link>
            <p class="card-text">
                {{ product.description }}
            </p>
            <router-link :to="{name: 'EditProduct', params: {id: product.id}}"
                v-show="$route.name == 'AdminProduct'">
                <button class="btn btn-primary">Edit</button>
            </router-link>
        </div>
    </div>
</template>

<script>
export default {
    name: "ProductBox",
    props: ["product"],
}
</script>
 
<style scoped>
.card-img-top {
    object-fit: cover;
}
a {
    text-decoration: none;
}
.card-title {
    color: #484848;
}
</style>