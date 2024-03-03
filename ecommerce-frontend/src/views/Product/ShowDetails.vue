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
                <div class="d-flex flex-row justify-content-between">
                    <div class="input-group col-md-3 col-4 p-0">
                        <div class="input-group-prepend">
                            <span class="input-group-text">Quantity</span>
                        </div>
                        <input type="number" class="form-control" min="1" max="9" v-model="quantity"/>
                    </div>
                    <div class="input-group col-md-3 col-4 pt-0">
                        <button class="btn" id="add-to-cart-button" @click="addToCart">
                            Add to Cart
                        </button>
                    </div>
                </div>
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
            quantity: 1,    
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
        },

        // add to cart
        addToCart() {
            if (!this.token) {
                // user is not logged in
                swal({
                    text: "Please login to add item in wishlist",
                    icon: 'error'
                });
                return;
            }
            axios.post(`${this.baseURL}/cart/add?token=${this.token}`, {
                productId: this.id,
                quantity: this.quantity
            }).then((res) => {
                if (res.status == 201) {
                    swal({
                        text: "Product added in cart",
                        icon: "success"
                    });
                }
                this.$emit("fetchData");
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

#add-to-cart-button {
    background-color: #febd69;
}
 
input[type="number"] {
  -moz-appearance: textfield;
}
</style>
