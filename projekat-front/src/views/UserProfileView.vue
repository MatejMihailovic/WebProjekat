<template>
<div class="container emp-profile">
            <form method="post">
                <div class="row">
                    <div class="col-md-4">
                        <div class="profile-img">
                            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSEidraPhmCDVkOsdFzkhtvO8TXj2_1ukhLZw&usqp=CAU" alt=""/>
                        </div>
                    </div>
                    </div>
                    <div class="col-md-6">
                        <div class="profile-head">
                                    <h5>
                                        {{user.korisnickoIme}}
                                    </h5>
                                    <h6>
                                        {{user.uloga}}
                                    </h6>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <a type="button" href="/profile-update"  class="profile-edit-btn">Update</a>
                    </div>
                    <div class="col-md-8">
                        <div class="tab-content profile-tab" id="myTabContent">
                            <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Name</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>{{user.ime}}</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Surname</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>{{user.prezime}}</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Gender</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>{{user.pol}}</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Role</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>{{user.uloga}}</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Date of birth</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>{{user.datumRodjenja}}</p>
                                            </div>
                                        </div>
                            </div>
                    </div>
                </div>
            </form>           
        </div>
</template>

<script>
import axios from "axios";
export default {
  name: "UserProfileView",
  data: function () {
    return {
      user: {}
    };
  },
   mounted: function () {
    axios
      .get("http://localhost:8080/api/korisnici/ulogovanKorisnik", {withCredentials:true})
      .then((res) => {
        this.user = res.data
      })
      .catch((err) =>{
        console.log(err)
      })
   },
   methods: {
   }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
body{
    background: -webkit-linear-gradient(left, #3931af, #00c6ff);
}
.emp-profile{
    padding: 3%;
    margin-top: 3%;
    margin-bottom: 3%;
    border-radius: 0.5rem;
    background: #fff;
}
.profile-img{
    text-align: center;
}
.profile-img img{
    width: 70%;
    height: 100%;
}
.profile-img{
    position: relative;
    overflow: hidden;
    margin-top: -20%;
    width: 70%;
    border: none;
    border-radius: 0;
    font-size: 15px;
    background: #212529b8;
}
.profile-img{
    position: absolute;
    opacity: 0;
    right: 0;
    top: 0;
}
.profile-head h5{
    color: #333;
}
.profile-head h6{
    color: #0062cc;
}
.profile-edit-btn{
    margin: 0;
    position: absolute;
    top: 25%;
    left: 45%;
    -ms-transform: translate(-50%, -50%);
    transform: translate(-50%, -50%);
    width: 10%;
    font-weight: 600;
    color: #6c757d;
    cursor: pointer;
}
.profile-head{
    margin-bottom:5%;
}
.profile-head{
    font-weight:600;
    border: none;
}
.profile-head{
    border: none;
    border-bottom:2px solid #0062cc;
}
.profile-tab label{
    font-weight: 600;
}
.profile-tab p{
    font-weight: 600;
    color: #0062cc;
}
</style>
