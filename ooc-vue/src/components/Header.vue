<template>

  <div class="header">
    <nav class="navbar navbar-expand-md bg-bab-header navbar-dark">

      <div class="container">
        <!-- Brand -->

        <a class="navbar-brand py-1" href="/">
          Brew
          <img src="../assets/logo.png" alt="Logo" class="rounded-circle" style="width:40px;">
          Bev
        </a>
        <div>
          
        </div>
        <!-- Toggler/collapsibe Button -->
        <button class="navbar-toggler float-right" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
          <span class="navbar-toggler-icon"></span>
        </button>

        <!-- Navbar links -->
        <div class="collapse navbar-collapse flex-row-reverse" id="collapsibleNavbar">

          <ul class="navbar-nav font-weight-bold ml-auto">
              <Login v-if="user.email == ''" class="pt-1"/>
              <Register v-if="user.email == ''" class="pt-1"/>
            <!-- User -->
            <li class="nav-item dropdown pt-1" v-if="user.userType === 'USER'">
              <a class="nav-link dropdown-toggle text-light " href="#" id="navbardrop" data-toggle="dropdown">
                <!-- Change to {{User}} after done  login -->
                {{user.email}}
              </a>
              <div class="dropdown-menu">
                <a class="dropdown-item" href="/profile">Profile</a>
                <a class="dropdown-item" href="/" @click="logout">Logout</a>
              </div>
            </li>
            <li class="nav-item dropdown pt-1" v-if="user.userType === 'ADMIN'">
              <a class="nav-link dropdown-toggle text-light " href="#" id="navbardrop" data-toggle="dropdown">
                <!-- Change to {{User}} after done  login -->
                Admin
              </a>
              <div class="dropdown-menu">
                <a class="dropdown-item" href="#">Store</a>
                <a class="dropdown-item" href="/" @click="logout">Logout</a>
              </div>
            </li>
            <Cart/>

          </ul>

          <ul class="navbar-nav">
            <!-- <li class="nav-item px-1">
              <a class="nav-link text-light" href="/">Home</a>
            </li> -->
            <li class="nav-item px-1">
              <router-link class="nav-link text-light" to="/appetizer">Appetizer</router-link>
            </li>
            <li class="nav-item px-1">
              <router-link class="nav-link text-light" to="/dessert">Dessert</router-link>
            </li>

            <li class="nav-item px-1">
              <router-link class="nav-link text-light" to="/drink">Drink</router-link>
            </li>

            <li class="nav-item px-1">
              <router-link class="nav-link text-light" to="/main_dish">MainDish</router-link>
            </li>
          </ul>

        </div>

      </div>
    </nav>
  </div>
</template>

<script>

  import Login from "./Login";
  import Register from "./Register";
  import Cart from "./Cart";
  import { mapMutations } from 'vuex'

  export default {
    name: 'Header',
    components: {Register, Login, Cart},
    computed: {
      user() {
        return this.$store.state.user;
      }
    },
    methods: {
      ...mapMutations([
        'logout'
      ])
    }

  }
</script>

<style scoped>

  .header {
    top: 0;
    width: 100%;

  }

  .bg-bab-header {
    background-color: #13254f;
  }

</style>
