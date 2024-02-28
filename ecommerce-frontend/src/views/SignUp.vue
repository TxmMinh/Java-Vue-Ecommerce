<template>
    <div class="container">
        <div class="row">
            <div class="col-12 text-center pt-3">
                <!-- display logo -->
                <router-link :to="{ name: 'home'}">
                    <img id="logo" src="../assets/logo.png"/>
                </router-link>
            </div>
        </div>
        <!-- header -->
        
        <div class="row">
            <div class="col-12 justify-content-center d-flex flex-row pt-5">
                <div id="signup-div" class="flex-item border">
                    <h2 class="pt-4 pl-4">Create Account</h2>
                    <form @submit="signup" class="p-4 pl-4 pr-4">
                        <div class="form-group">
                            <label for="Email">Email</label>
                            <input type="email" v-model="email" class="form-control" required/>
                        </div>
                        <div class="form-row">
                            <div class="col">
                                <div class="form-group">
                                    <label>First Name</label>
                                    <input type="name" v-model="firstName" class="form-control" required/>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <label>Last Name</label>
                                    <input type="name" v-model="lastName" class="form-control" required/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="Password">Password</label>
                            <input type="password" v-model="password" class="form-control" required/>
                        </div>
                        <div class="form-group">
                            <label for="Password">Confirm Password</label>
                            <input type="password" v-model="confirmPassword" class="form-control" required/>
                        </div>
                        <button type="submit" class="btn btn-primary mt-2 py-0">Create Account</button>
                    </form>
                </div>
            </div>
        </div>

        <!-- form -->
    </div>
</template>

<script>
import axios from 'axios'
import swal from 'sweetalert'

export default ({
    props: ["baseURL"],
    data() {
        return {
            email: null,
            firstName: null,
            lastName: null,
            password: null,
            confirmPassword: null
        };
    },
    methods: {
        async signup(e) {
            e.preventDefault();
            if (this.password === this.confirmPassword) {
                // call signup api
                const user = {
                    email: this.email,
                    firstName: this.firstName,
                    lastName: this.lastName,
                    password: this.password
                };
                await axios.post(`${this.baseURL}/user/signup`, user)
                .then(() => {
                    this.$router.replace("/");
                    swal({
                        text: "User singup successfully. Please login",
                        icon: "success"
                    });
                }).catch((err) => console.log(err));
            } else {
                // show error
                swal({
                        text: "Passwords are not matching",
                        icon: "error"
                    });
            }
        },
    }
})
</script>

<style scoped>
.btn-primary {
    background-color: #f0c14b;
    color: #000;
    border-radius: 0;
}
#logo {
  width: 100px;
  margin-left: 20px;
  margin-right: 20px;
}

@media screen and (min-width: 992px) {
  #signiup-div {
    width: 40%;
  }
}
</style>