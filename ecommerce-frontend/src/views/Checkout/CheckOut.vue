<template>
  <div class="div_class">
    <h3>You will be redirected to payment page</h3>
    <div class="alert alert-primary">
      While making payment use card number 4242 4242 4242 4242 and enter random
      date and cvv (3 digit)
    </div>
    <button class="btn btn-primary" @click="goToCheckout">Make Payment</button>
  </div>
</template>

<script>
import axios from 'axios';

export default ({
  data() {
    return {
      stripeAPIToken: 'pk_test_51OkLgvAHMHJKiZQ43oCrxFEDStlgPcuVRsLb83eVZNdWlWOMWrYkt60IMm7NHsOunx98QvHx02mK8jR70SCIEF84002pDb1GRW',
      stripe: '',
      token: null,
      checkoutBodyArray: []
    }
  },
  name: 'CheckOut',
  props: ["baseURL"],
  methods: {
    getAllItems() {
      axios.get(`${this.baseURL}/cart/list?token=${this.token}`)
      .then((res) => {
        if (res.status == 200) {
          let products = res.data;
          for (let i = 0; i < products.cartItems.length; i++) {
            this.checkoutBodyArray.push({
              price: products.cartItems[i].product.price,
              quantity: products.cartItems[i].quantity,
              productName: products.cartItems[i].product.name,
              productId: products.cartItems[i].product.id
            });
          }
        }
      })
      .catch(err => console.log(err));
    },

    goToCheckout() {
      console.log('checkoutBodyArray', this.checkoutBodyArray);
      axios.post(`${this.baseURL}/order/create-checkout-session`, this.checkoutBodyArray)
      .then(res => {
        localStorage.setItem('sessionId', res.data.sessionId);
        console.log('session', res.data);
        this.stripe.redirectToCheckout({ sessionId: res.data.sessionId });
      }).catch(err => console.log(err));
    }
  },
  mounted() {
    this.token = localStorage.getItem("token");
    this.stripe = window.Stripe(this.stripeAPIToken);
    this.getAllItems();
  },
})
</script>

<style scoped>
.alert {
  width: 50%
}
.div_class {
  margin-top: 5%;
  margin-left: 35%
}

</style>
