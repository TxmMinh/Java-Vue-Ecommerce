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

    <div class="row">
      <div class="col-12 justify-content-center d-flex flex-row pt-5">
        <div id="signin-div" class="flex-item border">
          <h2 class="pt-4 pl-4">Sign-in</h2>
          <form @submit="signin" class="form-group pt-4 pl-4 pr-4">
            <div class="form-group">
              <label>Email</label>
              <input type="email" v-model="email" class="form-control" />
            </div>
            <div class="form-group">
              <label>Password</label>
              <input type="password" v-model="password" class="form-control" />
            </div>
            <button class="btn btn-primary mt-2 py-0">Continue</button>
          </form>
          <hr />
          <small class="form-text text-muted p-2 pl-4 text-center"
            >New to WebVue?</small
          >
          <p class="text-center">
            <router-link
              :to="{ name: 'SignUp' }"
              class="btn btn-dark text-center mx-auto px-5 py-1 mb-2"
              >Create Your WebVue Account</router-link
            >
          </p>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import swal from "sweetalert";

export default {
  props: ["baseURL"],
  data() {
    return {
      email: null,
      password: null,
    };
  },
  methods: {
    async signin(e) {
      e.preventDefault();
      // call signin api
      const user  = {
        email: this.email,
        password: this.password,
      };
      await axios
        .post(`${this.baseURL}/user/signin`, user )
        .then((res) => {
          this.redirectReload();
          localStorage.setItem("token", res.data.token);
          swal({
            text: "Login successfully",
            icon: "success",
          });
        })
        .catch((err) => {
          console.log(err);
          swal({
            text: "Invalid login",
            icon: "error",
          });          
        });
    },
    redirectReload() {
        this.$router
          .push({ path: '/' })
          .then(() => { this.$router.go(0) })
      }
  },
};
</script>

<style scoped>
.btn-primary {
  background-color: #f0c14b;
  color: black;
}

#logo {
  width: 100px;
  margin-left: 20px;
  margin-right: 20px;
}

@media screen {
  #signin-div {
    width: 40%;
  }
}
</style>
