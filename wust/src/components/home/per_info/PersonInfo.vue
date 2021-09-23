<template>
  <div class="person-info">
    <div class="avatar">
      <img src="../../../assets/avatar.png" v-real-img="src" alt="头像" class="img-icon">
      <el-upload
        action="http://localhost:8081/file/headPortrait"
        class="upload-box"
        accept=".jpg"
        :headers="headers"
        :with-credentials="true"
        :on-success="upSuccess"
        :show-file-list="false"
      >
        <span class="upload">点击上传头像</span>
      </el-upload>
    </div>

    <div class="info username">
      用户名:<span v-text="info.username"></span>
    </div>

    <div class="info name">
      姓 名:<span v-text="info.name"></span>
    </div>

    <div class="info number" v-if="$route.params.type === `student`">
      学 号:<span v-text="info.number"></span>
    </div>

    <div class="info number" v-if="$route.params.type === `teacher`">
      工 号:<span v-text="info.number"></span>
    </div>

    <div class="info sex">
      性 别:<span v-text="info.sex"></span>
    </div>

    <div class="info nation">
      民 族:<span v-text="info.nation"></span>
    </div>

    <div class="info type">
      身 份:<span v-text="info.type"></span>
    </div>

    <div class="info department">
      学 院:<span v-text="info.departmentName"></span>
    </div>

    <div class="info position" v-if="$route.params.type === `teacher`">
      职 位:<span v-text="info.position"></span>
    </div>

    <div class="info major" v-if="$route.params.type === `student`">
      专 业:<span v-text="info.majorName"></span>
    </div>

    <div class="info grade" v-if="$route.params.type === `student`">
      班 级:<span v-text="info.grade"></span>
    </div>

    <div class="info email">
      邮 件:<span v-text="info.email"></span>
    </div>

  </div>
</template>

<script>
  import axios from 'axios'

  export default {
    name: "PersonInfo",
    data() {
      return {
        info: {},
        src: "http://localhost:8081/static/" + localStorage.getItem("username")
          + ".jpg?"+new Date().getTime(),
        file: {},
        headers: {token: localStorage.token}
      };
    },
    methods: {
      getInfo() {

        axios.get('http://localhost:8081/user/getInfo')
          .then(response => {

            if (response.data.code === 200) {

              this.info = response.data.data;
            } else {

              this.$message.error(response.data.message);
            }
          })
          .catch(error => {

            this.$message.error("网络错误");
          });
      },
      upSuccess(response,file,fileList) {

        if (response.code === 200) {

          this.$message.success(response.message);
          //刷新浏览器
          setInterval(fun => {location.reload()}, 2000);
        } else {

          this.$message.error(response.message);
        }
      }
    },
    created() {
      this.getInfo();
    }
  };
</script>

<style scoped>
  @import "../../../../static/public.css";

  .person-info {
    height: 89%;
    margin-left: 200px;
    transform: translateY(-80px);
    text-align: center;
  }

  .avatar {
    cursor: default;
  }

  .info {
    height: 40px;
    width: 350px;
    margin: 3px auto;
    font-size: 20px;
    transform: translateX(70px);
    text-align: left;
    letter-spacing: 5px;
    cursor: default;
  }

  span{
    margin-left: 20px;
    letter-spacing: 2px;
  }

  .upload {
    font-size: xx-small;
    letter-spacing: 0;
    color: #409EFF;
    border-bottom: 1px solid #409EFF;
    cursor: pointer;
  }

  .upload-box {
    display: inline-block;
    position: absolute;
    transform: translate(-1em,2.5em);
  }
</style>
