<template xmlns:v-validate="http://www.w3.org/1999/xhtml">
<div class="register">

  <a class="nav-link text-light" data-toggle="modal" data-target="#register-modal">Register</a>

  <div class="modal fade" id="register-modal">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">

          <!-- Modal Header -->
          <div class="modal-header">
            <h4 class="modal-title">Register</h4>
            <button type="button" class="close" data-dismiss="modal">&times;</button>
          </div>

          <!-- Modal body -->
          <div class="modal-body">
            <div class="row pt-2">
              <div class="col-md mt-2 mx-3">
                  <form @submit.prevent="addUser()">
                    <div class="form-row">
                      <div class="form-group col-md-6">
                        <input type="text"
                               class="form-control"
                               placeholder="First Name"
                               name="first name"
                               v-validate="'required|alpha'"
                               v-model="firstName">
                        <span v-if="errors.has('first name')">
                          <p class="alert alert-warning py-0 text-left my-1">{{errors.first('first name')}}</p>
                        </span>
                      </div>
                      <div class="form-group col-md-6">
                        <input type="text"
                               class="form-control"
                               placeholder="Last Name"
                               name="last name"
                               v-validate="'required|alpha'"
                               v-model="lastName">
                        <span v-if="errors.has('last name')">
                          <p class="alert alert-warning py-0 text-left my-1">{{errors.first('last name')}}</p>
                        </span>
                      </div>
                    </div>

                    <div class="form-row">
                      <div class="form-group col-md">
                        <input type="email"
                               class="form-control"
                               placeholder="Email"
                               name="email"
                               v-model="email"
                               v-validate:email="'required|email'">
                        <span v-if="errors.has('email')">
                          <p class="alert alert-warning py-0 text-left my-1">{{errors.first('email')}}</p>
                        </span>
                      </div>
                    </div>

                    <div class="form-row">
                      <div class="form-group col-md">
                        <input type="text"
                               class="form-control"
                               placeholder="Mobile Number"
                               name="mobile number"
                               v-validate="'required|digits:10'"
                               v-model="mobilePhone">
                        <span v-if="errors.has('mobile number')">
                          <p class="alert alert-warning py-0 text-left my-1">{{errors.first('mobile number')}}</p>
                        </span>
                      </div>
                    </div>

                    <div class="form-row">
                      <div class="form-group col-md">
                        <input type="password"
                               class="form-control"
                               placeholder="Password"
                               name="password"
                               v-validate="'required'"
                               v-model="password">
                        <span v-if="errors.has('password')">
                          <p class="alert alert-warning py-0 text-left my-1">{{errors.first('password')}}</p>
                        </span>
                      </div>
                    </div>
                    <div class="form-row">
                      <div class="form-group col-md">
                        <input type="password"
                               class="form-control"
                               placeholder="Confirmed Password"
                               name="confirmed password"
                               v-validate="'required|confirmed:password'"
                               v-model="checkPassword">
                        <span v-if="errors.has('confirmed password')">
                          <p class="alert alert-warning py-0 text-left my-1">{{errors.first('confirmed password')}}</p>
                        </span>
                      </div>

                    </div>
                    <div class="form-footer ">
                      <button class="btn btn-primary">Register</button>
                    </div>
                  </form>
                <!-- </div> -->
              </div>
            </div>
          </div>

          <!-- Modal footer -->
          <div class="modal-footer">

          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  
  export default {

    name: "Register",


    data() {
      return {
        firstName : "",
        lastName : "",
        email : "",
        mobilePhone : "",
        password : "",
        checkPassword : "",
        message : ""
      }
    },

    methods :{

      resetForm(){
        this.firstName ="",
        this.lastName ="",
        this.email ="",
        this.mobilePhone = "",
        this.password = "",
        this.checkPassword = ""
        this.message = ""
      },

      addUser(){
        this.$validator.validateAll().then((result) => {
          if (result){
            alert('Your form is submitted')
            this.$http.post(`/user/add`,{
              firstName : this.firstName,
              lastName : this.lastName,
              email : this.email,
              mobilePhone : this.mobilePhone,
              password : this.password
            })
            .then(response => {
              
              this.message  = response.data
              // const status = JSON.parse(response.data.response.status);
              console.log(this.message);
              if (this.message == "fail") {
                this.email = '';
                alert("Email alreay existed");
              }
              else {
                window.location.href="/";
                alert("Register successful");
              }
            })
            .catch(e =>{
              console.log(e);
            })
            return;
          }
          alert('Please fill all required fields correctly');
        });


      }
      }
    }

</script>

<style scoped>
  .alert{
    font-size: 10px;

  }
</style>

