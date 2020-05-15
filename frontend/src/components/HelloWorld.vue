<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <router-link to="/main">go to main</router-link>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Hello World'
    }
  }
}
</script>

<style scoped>
h1, h2 {
  font-weight: normal;
}
</style>
