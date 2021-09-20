<template>
  <div class="reg-panel">
    <div class="register">
      <el-form :label-position="labelPosition" label-width="100px">
        <el-form-item label="用户名" class="bar">
          <el-input v-model="userInfo.name" placeholder="请输入手机号"></el-input>
        </el-form-item>

        <el-form-item label="密 码" class="bar">
          <el-input v-model="userInfo.password" placeholder="请输入密码" show-password></el-input>
        </el-form-item>

        <el-form-item label="邮 箱" class="bar">
          <el-input v-model="userInfo.email" placeholder="请输入邮箱"></el-input>
        </el-form-item>

        <el-form-item label="姓 名" class="bar">
          <el-input v-model="userInfo.email" placeholder="请输入姓名"></el-input>
        </el-form-item>

        <el-form-item label="性 别" class="bar" style="margin-top: -20px">
          <el-radio v-model="userInfo.sex" label="男">男</el-radio>
          <el-radio v-model="userInfo.sex" label="女">女</el-radio>
        </el-form-item>

        <el-form-item label="身份" class="bar" style="margin-top: -20px">
          <el-radio v-model="userInfo.type" label="teacher">教师</el-radio>
          <el-radio v-model="userInfo.type" label="student">学生</el-radio>
        </el-form-item>

        <el-form-item label="工 号"  v-show="userInfo.type === `teacher`" class="bar" style="margin-top: -20px">
          <el-input v-model="userInfo.number" placeholder="请输入工号"></el-input>
        </el-form-item>

        <el-form-item label="学 号"  v-show="userInfo.type === `student`" class="bar" style="margin-top: -20px">
          <el-input v-model="userInfo.number" placeholder="请输入学号"></el-input>
        </el-form-item>

        <el-form-item label="民 族" class="bar">
          <el-select v-model="userInfo.nation" placeholder="请选择">
            <el-option v-for="item in nations" :key="item.value" :label="item.label"
                       :value="item.label">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="院 系" class="bar">
          <el-select v-model="userInfo.departmentId" placeholder="请选择" @focusin.native="getDept">
            <el-option v-for="item in departments" :key="item.id" :label="item.name"
                       :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="职 位" v-show="userInfo.type === `teacher`" class="bar">
          <el-input v-model="userInfo.position" placeholder="请输入教师职位"></el-input>
        </el-form-item>

        <el-form-item label="专 业" v-show="userInfo.type === `student`" class="bar">
          <el-select v-model="userInfo.majorId" placeholder="请选择" @focusin.native="getMajor">
            <el-option v-for="item in majors" :key="item.id" :label="item.name"
                       :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="职 位" v-show="userInfo.type === `student`" class="bar">
          <el-input v-model="userInfo.grade" placeholder="请输入教师职位"></el-input>
        </el-form-item>

      </el-form>

      <el-button style="font-size: 20px" type="primary" @click="submit">提 交</el-button>

    </div>

  </div>
</template>

<script>
  const DB = require(`../../data/nation`);
  import axios from 'axios';

  export default {
    name: "Register",
    data() {
      return {
        labelPosition: 'right',
        userInfo: {
          username: "",
          password: "",
          email: "",
          name: "",
          sex: "男",
          number: "",
          nation: "",
          departmentId: "",
          position: "",
          majorId: "",
          grade: "",
          type:"teacher"
        },
        nations: DB.data,
        departments: [],
        majors: []
      }
    },
    methods: {
      getDept() {

        axios.get('http://localhost:8081/dept/getAll')
          .then(response => {

            if (response.data.code === 200) {

              this.departments = response.data.data;
            } else {

              this.$message.error(response.data.message);
            }
          })
          .catch(error => {

            this.$message.error("网络错误");
          });
      },

      getMajor() {

        axios.get('http://localhost:8081/major/getByDeptId',{
          params:{
            departmentId: this.userInfo.departmentId
          }
        })
          .then(response => {

            if (response.data.code === 200) {

              this.majors = response.data.data;
            } else {

              this.$message.error(response.data.message);
            }
          })
          .catch(error =>  {

            this.$message.error("网络错误");
          });
      },

      submit() {

        axios.post('http://localhost:8081/user/register',
          this.userInfo
        ).then(response => {

          if (response.data.code === 200) {

            this.$message.success(response.data.message);

            setTimeout(() => this.$router.push(`/log_reg/login`), 2000);
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

  .reg-panel {
    height: 500px;
    width:500px;
    transform: translateY(-140px);
    background-color: whitesmoke;
    overflow:auto;
    font-size: 20px;
    text-align: center;
    padding-top: 20px;
  }

  .register {
    width: 320px;
    margin: auto;
    text-align: center;
  }

  .bar {
    margin-top: -5px;
    text-align: left;
  }

  .submit {
    margin-top: 20px;
  }


</style>
