<template>
  <div class="selected">
    <div class="search">
      <el-input style="width: 300px;transform: translateX(-390px)"
                clearable v-model="search" placeholder="课程名或任课教师"
                @keyup.enter.native="doSearch"></el-input>
    </div>

    <div class="table">
      <el-table :data="courseInfo" border style="width: 100%">
        <el-table-column type="index" label="序号" width="50px"></el-table-column>
        <el-table-column prop="name" label="课程名称" width="120px"></el-table-column>
        <el-table-column prop="courseNature" label="学科性质" width="150px"></el-table-column>
        <el-table-column prop="examNature" label="考试性质" width="90px"></el-table-column>
        <el-table-column prop="credit" label="学分" width="50px"></el-table-column>
        <el-table-column prop="classHour" label="学时" width="50px"></el-table-column>
        <el-table-column prop="teacher" label="任课教师" width="80px"></el-table-column>
        <el-table-column prop="classroom" label="教室" width="110px"></el-table-column>
        <el-table-column prop="week" label="开课周次"></el-table-column>
        <el-table-column prop="score" label="分数" width="50px"></el-table-column>
        <el-table-column prop="gpa" label="绩点" width="50px"></el-table-column>

        <el-table-column width="70px">
          <!--插槽-->
          <template slot="header" slot-scope="scope">
            <span>退选</span>
          </template>

          <template slot-scope="scope">
            <el-button style="color: dodgerblue" @click="doDelete(scope)">退选</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
  import axios from 'axios';

  export default {

    name: "Selected",
    data() {
      return {
        courseInfo: [],
        search: ''
      };
    },
    created() {
      this.getInfo('');
    },
    methods: {
      doSearch() {

        this.getInfo(this.search);
      },
      doDelete(scope) {
        let courseId = scope.row.id;

        axios.delete('http://localhost:8081/stu_cou/delete/' + courseId,
        ).then(response => {

          if (response.data.code === 200) {

            this.getInfo('');
            this.$message.success(response.data.message);
          } else {

            this.$message.error(response.data.message);
          }

        }).catch(error => {

          this.$message.error("网络错误");
        });
      },
      getInfo(val) {

        axios.get('http://localhost:8081/stu_cou/getLike?content=' + val)
          .then(response => {

            if (response.data.code === 200) {

              this.courseInfo = response.data.data;
            } else {

              this.$message.error(response.data.message);
            }
          })
          .catch(error => {

            this.$message.error("网络错误");
          });
      }
    }
  };
</script>

<style scoped>
  .selected {
    height: 96%;
    margin-left: 200px;
    transform: translateY(-130px);
    text-align: center;
    overflow: auto;
  }

  .table {
    margin-top: 10px;
  }
</style>
