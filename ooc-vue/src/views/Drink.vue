<template>
  <div class="drink content">
    <!-- <Header/> -->
    <div class="container py-2">
      <div class="row border-bottom py-2">
        <h1 class="text-dark ">Coffee</h1>
      </div>
      <div class="row py-3">
        <div class="col-lg-3 col-md-4 col-sm-6 p-3 " v-bind:key="data.name" v-for="(data) in coffee">
          <div class="card bg-dark text-secondary">
            <img v-if="data.imageName !== null" class="card-img-top" :src="'http://localhost:8088/api/image/'+data.imageName" alt="Card image cap">
            <img v-else class="card-img-top" src="../assets/logo.png" alt="Card image cap">
            
            <div class="card-body text-light">
              <h5 class="card-title border-bottom">{{data.name}}</h5>
              <div class="row">
                <div class="col-5 text-left">

                  <span>Topping :</span>
                  
                </div>
                <div class="col-7">
                  <select v-model="data.topping" 
                      class="btn btn-sm w-100">
                      <option v-bind:key="topping.id" 
                              v-for="topping in toppings" 
                              v-bind:value="topping">
                        {{ topping.toppingType }}
                      </option>
                  </select>
                </div>
              </div>
              <div class="row pt-2">
                <div class="col-5 text-left">
                  Sweet :
                </div>
                <div class="col-7 text-left">
                  <input type="radio" id="one" value="SWEET" v-model="data.sweetness">
                  <label for="one">Less Sweet</label>
                  <br>
                  <input type="radio" id="two" value="SWEETER" v-model="data.sweetness">
                  <label for="two">Normal</label>
                  <br>
                  <input type="radio" id="three" value="SWEETEST" v-model="data.sweetness">
                  <label for="two">Extra Sweet</label>
                  <br>
                </div>
                
              </div>
            </div>
            <div class="card-footer">
              <div class="d-flex w-100">
                <div class="py-2 font-weight-bold text-light"> 
                  {{data.price}} ฿ <span v-if="data.topping && data.topping.id !==1">+ {{data.topping.price}} ฿</span> 
                  </div>
                <button type="submit"
                        class="btn btn-info p-auto ml-auto "
                        v-confirm="{
                          ok: dialog => add(data),
                          cancel: doNothing,
                          message: 'Add to List?'
                        }"
                >
                  SELECT
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="row border-bottom py-2">
        <h1 class="text-dark ">Non Coffee</h1>
      </div>
      <div class="row py-3">
        <div class="col-lg-3 col-md-4 col-sm-6 p-3 " v-bind:key="data.name" v-for="(data) in non_coffee">
          <div class="card bg-dark text-secondary">
            <img v-if="data.imageName !== null" class="card-img-top" :src="'http://localhost:8088/api/image/'+data.imageName" alt="Card image cap">
            <img v-else class="card-img-top" src="../assets/logo.png" alt="Card image cap">
            
            <div class="card-body text-light">
              <h5 class="card-title border-bottom">{{data.name}}</h5>
              <div class="row">
                <div class="col-5 text-left">

                  <span>Topping :</span>
                  
                </div>
                <div class="col-7">
                  <select v-model="data.topping" 
                      class="btn btn-sm w-100">
                      <option v-bind:key="topping.id" 
                              v-for="topping in toppings" 
                              v-bind:value="topping">
                        {{ topping.toppingType }}
                      </option>
                  </select>
                </div>
              </div>
              <div class="row pt-2">
                <div class="col-5 text-left">
                  Sweet :
                </div>
                <div class="col-7 text-left">
                  <input type="radio" id="one" value="SWEET" v-model="data.sweetness">
                  <label for="one">Less Sweet</label>
                  <br>
                  <input type="radio" id="two" value="SWEETER" v-model="data.sweetness">
                  <label for="two">Normal</label>
                  <br>
                  <input type="radio" id="three" value="SWEETEST" v-model="data.sweetness">
                  <label for="two">Extra Sweet</label>
                  <br>
                </div>
                
              </div>
            </div>
            <div class="card-footer">
              <div class="d-flex w-100">
                <div class="py-2 font-weight-bold text-light"> 
                  {{data.price}} ฿ <span v-if="data.topping && data.topping.id !==1">+ {{data.topping.price}} ฿</span> 
                  </div>
                <button type="submit"
                        class="btn btn-info p-auto ml-auto "
                        v-confirm="{
                          ok: dialog => add(data),
                          cancel: doNothing,
                          message: 'Add to List?'
                        }"
                >
                  SELECT
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
  import {mapMutations} from 'vuex'

  export default {
    name: 'Drink',
    data() {
      return {
        coffee: [],
        non_coffee: [],
        toppings : [],
      }
    },
    mounted() {
      this.$http.get('api/topping')
        .then(response => {
          this.toppings = response.data
        });
      this.$http.get('api/non_coffee')
        .then(response => {
          this.non_coffee = response.data
        });
      this.$http.get('api/coffee')
        .then(response => {
          this.coffee = response.data
        });
      

    
    },
    methods : {
      ...mapMutations([
        'addItem',
      ]),
      ...mapMutations({
        add: 'addItem', // map `this.add()` to `this.$store.commit('increment')`

      }),
      doNothing(){}
    }
  }
</script>

<style scoped>

</style>
