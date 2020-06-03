<template>
  <div id="mainPage" class="test">
    <h1>main page</h1>
    <router-link to="/">go to home</router-link>
    <div>
      <div>callget ajax data test</div>
      <button v-on:click="callGetAjax($event)">get ajax test</button>
      <div v-show="doGetShow">
        <p>name is here : {{ name }}</p>
        <p>age is here : {{ age }}</p>
      </div>
    </div>
    <hr/>
    <div>
      <div>call post ajax data test</div>
      <div>
        name : <input type="text" v-model="postname"/><br/>
        age : <input  type="text" v-model="postage"/><br/>
        <button v-on:click="callPostAjax($event)">post get ajax test</button>
      </div>
      <div v-show="doPostShow">
        <p>name is here : {{ post_name }}</p>
        <p>age is here : {{ post_age }}</p>
        <p>address is here : {{ post_address }}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MainPage',
  data () {
    return {
      // request value
      postname: '',
      postage: '',
      // response value
      name: '',
      age: '',
      post_name: '',
      post_age: '',
      post_address: '',
      // element show hide status
      doGetShow: false,
      doPostShow: false
    }
  },
  methods: {
    callGetAjax: function (event) {
      // fetch get
      fetch('http://localhost:8080/hello/test/123')
        .then((response) => {
          if (response.ok) {
            return response.json()
          }
          throw new Error('fetch error')
        })
        .then((json) => {
          this.name = json.name
          this.age = json.age
          this.doGetShow = true
        })
        .catch((error) => {
          console.log(error)
        })
    },
    // post 방식 사용 with axios
    callPostAjax: function (event) {
      this.$http.post('http://localhost:8080/post/user', {
        name: this.postname,
        age: this.postage,
        timeout: 1000
      }).then((result) => {
        this.post_name = result.data.name
        this.post_age = result.data.age
        this.post_address = result.data.address
        this.doPostShow = true
      })
    }
  }
}
</script>

<style scoped>
.test{
  background-color: #2c3e50;
  color: white;
}
a{
  color: white;
}
</style>
