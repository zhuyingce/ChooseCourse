<template>
  <div class="conformity">
    <div class="avatar">
      <img src="../../assets/avatar.png" alt="头像" v-real-img="src" class="img-icon">

      <el-button size="mini" style="position: absolute;margin-left: -11.7em" @click="logout">退出登录</el-button>

      <div class="info">
        <div class="img-info">
          {{info.name}}
        </div>

        <div class="img-info">
          {{info.number}}
        </div>
      </div>
    </div>

    <div class="option">
      <div :class="{'bar':true,'back-color':this.getPath('per_info')}"
           @click="go('/per_info')">
        个人信息
      </div>

      <div :class="{'bar':true,'back-color':this.getPath('active')}"
           @click="go('/active')" v-show="$route.params.type === 'student'">
        备选课程
      </div>

      <div :class="{'bar':true,'back-color':this.getPath('selected')}"
           @click="go('/selected')" v-show="$route.params.type === 'student'">
        已选课程
      </div>

      <div :class="{'bar':true,'back-color':this.getPath('add')}"
           @click="go('/add')" v-show="$route.params.type === 'teacher'">
        添加课程
      </div>

      <div :class="{'bar':true,'back-color':this.getPath('tea_ch')}"
           @click="go('/tea_ch')" v-show="$route.params.type === 'teacher'">
        我教的课
      </div>
    </div>
    <RouterView/>
  </div>
</template>

<script>
  import axios from 'axios';

  export default {

    name: "Conformity",
    data() {
      return {
        info: {
          name:localStorage.name,
          number: localStorage.number,
          type:this.$route.params.type
        },
        src: "http://localhost:8081/static/" + localStorage.getItem("username")
          + ".jpg?"+new Date().getTime(),
      };
    },

    methods: {
      getPath(value) {

        return this.$route.path.indexOf(value) !== -1;
      },
      go(val) {
        if (this.$route.path !== ("/home/" + this.info.type + val)) {

          this.$router.push("/home/" + this.info.type + val);
        }
      },
      logout() {
        axios.put("http://localhost:8081/user/logout")
          .then(response => {

            if (response.data.code === 200) {

              this.$message.success("退出成功");

              this.$router.push("/log_reg/login");

              localStorage.removeItem("token");
            } else {

              this.$message.error(response.data.message);
            }
          }).catch(response => {

          this.$message.error("网络错误");
        });
      },
    }
  };
</script>

<style scoped>
  @import "../../../static/public.css";

  .conformity {
    height: 100%;
    width: 100%;
  }
  .option {
    border-right: 1px solid black;
    height: 75.5%;
    width: 200px;
    font-weight: normal;
    float: left;
  }

  .bar {
    border-bottom: 1px solid #409EFF;
    height: 60px;
    text-align: center;
    font-size: 25px;
    letter-spacing: 5px;
    line-height: 60px;
    cursor: default;
  }

  .avatar {
    border-right: 1px solid black;
    border-bottom: 1px solid black;
    height: 140px;
    width: 200px;
    text-align: center;
    cursor: default;
  }

  .back-color {
    background-color: #409EFF;
    color: white;
  }

  .img-info {
    font-size: 20px;
    letter-spacing: 2px;
    margin-top: 5px;
  }

</style>
