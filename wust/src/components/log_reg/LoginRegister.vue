<template>
    <div class="login-register">
      <div :class="teaClass" @click="selectTea">
        教 师
      </div>

      <div :class="stuClass" @click="selectStu">
        学 生
      </div>

      <RouterView :type="type"/>
    </div>
</template>

<script>
  import Login from "./Login";

  export default {
    name: "LoginRegister",
    components: {Login},
    data() {
      return {
        teaClass:'type type-after teacher',
        stuClass: 'type',
        type: 'teacher'
      }
    },
    methods: {
      selectStu() {
        this.teaClass = 'type teacher';
        this.stuClass = 'type type-after';
        this.type = 'student';
      },
      selectTea() {
        this.stuClass = 'type';
        this.teaClass = 'type type-after teacher';
        this.type = 'teacher';
      }
    }
  };
</script>

<style scoped>
  .login-register{
    border: 1px solid black;
    height: 300px;
    width: 500px;
    position: relative;
    left:50%;
    top:50%;
    transform: translate(-50%, -80%);
    font-weight: lighter;
  }

  .type {
    height: 40px;
    width: 249.5px;
    text-align: center;
    line-height: 40px;
    float: left;
    font-size: 20px;
    border: none;
    border-bottom: 1px solid black;
    color: white;
  }

  .type-after{
    background-color: #409EFF;
  }

  .teacher {
    border-right: 1px solid black;
  }
</style>
