<template>
    <div class="container">
        <div class="row pt-5">
            <div class="col-md-1"></div>
            <!-- display image -->
            <div class="col-md-4 col-12">
                <img :src="product.imageURL" class="img-fluid">
            </div>
            <!-- display product details -->
            <div class="col-md-6 col-12 pt-3 pt-md-0">
                <h4>{{ product.name }}</h4>
                <h6 class="category font-italic">{{ category.categoryName }}</h6>
                <h6 class="font-weight-bold">{{ product.price }} VND</h6>
                <p>
                    {{ product.description }}
                </p>
                <div class="features pt-3">
                    <h5><strong>Features</strong></h5>
                    <ul>
                        <li>hi 1</li>
                        <li>hi 2</li>
                        <li>hi 3</li>
                        <li>hi 4</li>
                        <li>hi 5</li>
                    </ul>
                </div>
                <button id="wishlist-button" class="btn mr-3 p-1 py-0" @click="addToWishlist">
                    {{ wishlistString }}
                </button>
            </div>
        </div>
    </div>
</template>

<script>
import swal from "sweetalert";
import axios from "axios";

export default ({
    data() {
        return {
            product: {},
            category: {},
            wishlistString: "Add to wishlist"
        }
    },
    props: ["baseURL", "products", "categories"],
    methods: {
        addToWishlist() {
            if (!this.token) {
                // user is not logged in
                swal({
                    text: "Please login to add item in wishlist",
                    icon: 'error'
                });
                return;
            }
            // add item to wishlist
            axios.post(`${this.baseURL}/wishlist/add?token=${this.token}`, {
                id: this.product.id
            }).then((res) => {
                if (res.status === 201) {
                    this.wishlistString = "Added to Wishlist";
                    swal({
                        text: "Added to Wishlist",
                        icon: 'success'
                    });
                }
            }).catch((err) => console.log(err));
        }
    },  
    mounted() {
        this.id = this.$route.params.id;
        this.product = this.products.find((product) => product.id == this.id);
        this.category = this.categories.find((category) => category.id == this.product.categoryId);
        this.token = localStorage.getItem("token");
    },
})
</script>

<style>
.category {
    font-weight: 400;
}

#wishlist-button {
    background-color: #b9b9b9;
}
</style>
