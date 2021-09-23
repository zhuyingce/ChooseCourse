<template>
  <div class="login-panel">
      <div class="username-bar">
        <label>用户名:&nbsp&nbsp</label>
        <input type="text" class="input" v-model="loginInfo.username" placeholder="请输入用户名">
      </div>

      <div class="password-bar">
        <label>密&nbsp&nbsp&nbsp码:&nbsp&nbsp</label>
        <input type="password" class="input" v-model="loginInfo.password"
               placeholder="请输入密码" @keyup.enter="login">
      </div>

      <el-button type="primary" class="login" @click.native="login">登&nbsp录</el-button>
      <el-button type="primary" class="register" @click.native="goReg">注&nbsp册</el-button>
  </div>
</template>

<script>
  import axios from 'axios'

  export default {

    name: "Login",
    props: [
      `type`
    ],
    data() {
      return {
        loginInfo: {
          username: "",
          password: "",
        }
      };
    },
    methods: {
      goReg() {
        this.$router.push('/log_reg/register');
      },

      login() {

        this.loginInfo.type = this.type;

        axios.post('http://localhost:8081/user/login',
          this.loginInfo
        ).then(response => {

          if (response.data.code === 200) {

            localStorage.setItem("token", response.data.data.token);
            localStorage.setItem("name", response.data.data.name);
            localStorage.setItem("number", response.data.data.number);
            localStorage.setItem("username", response.data.data.username);

            this.$message.success(response.data.message);

            this.$router.push(`/home/` + this.type + `/per_info`);
          } else {

            this.$message.error(response.data.message);
          }

        }).catch(error => {

          this.$message.error("网络错误");
        });
      }
    }
  };
</script>

<style scoped>
  @import "../../../static/public.css";

  .username-bar{
    margin-left: 10px;
  }

  .password-bar{
    margin-top: 40px;
    margin-left: 10px;
  }

  .login{
    margin-top: 40px;
    margin-right: 30px;
  }

  .register {
    margin-top: 40px;
    margin-left: 30px;
  }

  .login-panel{
    height: 200px;
    width: 300px;
    font-size: 20px;
    font-weight: normal;
    margin-top: 80px;
    margin-left: 20%;
    text-align: center;
  }
</style>
