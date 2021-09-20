<template>
    <div class="details">
      <div class="search">
        <el-input style="width: 300px;transform: translateX(-380px)"
                  clearable v-model="search" placeholder="学院或学生姓名"
                  @keyup.enter.native="doSearch"></el-input>
      </div>

      <div class="table">
        <el-table :data="details" border style="width: 100%">
          <el-table-column type="index" label="序号" width="50px"></el-table-column>
          <el-table-column prop="name" label="姓名" width="100px"></el-table-column>
          <el-table-column prop="sex" label="性别" width="50px"></el-table-column>
          <el-table-column prop="department" label="院系"></el-table-column>
          <el-table-column prop="major" label="专业" width="150px"></el-table-column>
          <el-table-column prop="courseName" label="课程名称"></el-table-column>
          <el-table-column prop="grade" label="班级"></el-table-column>

          <el-table-column prop="score" width="80px">
            <!--插槽-->
            <template slot="header">
              <span>分数</span>
            </template>

            <template v-slot="scope">
              <el-input v-model="scope.row.score"></el-input>
            </template>
          </el-table-column>

          <el-table-column prop="gpa" width="80px">
            <!--插槽-->
            <template slot="header">
              <span>绩点</span>
            </template>

            <template v-slot="scope">
              <el-input v-model="scope.row.gpa"></el-input>
            </template>
          </el-table-column>

          <el-table-column width="90px">
            <!--插槽-->
            <template slot="header">
              <span>操作</span>
            </template>

            <template v-slot="scope">
              <el-button style="color: dodgerblue" @click="determine(scope)">确认分数</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
</template>

<script>
  import axios from 'axios';

  export default {
    name: "Details",
    data() {
      return {
        details: [],
        search: ""
      };
    },
    created() {

      this.getInfo('');
    },
    methods: {
      doSearch() {

        this.getInfo(search);
      },
      determine(scope) {

        axios.put("http://localhost:8081/tea_cou/updateScore", {
          id: scope.row.id,
          courseId: this.$route.params.courseId,
          score: scope.row.score,
          gpa: scope.row.gpa

        }).then(response => {

          if (response.data.code === 200) {

            this.$message.success("修改成功");
          } else {

            this.$message.error(response.data.message);
          }
        }).catch(error => {

          this.$message.error("网络错误");
        });
      },
      getInfo(val) {
        let courseId = this.$route.params.courseId;

        axios.get('http://localhost:8081/tea_cou/getDetails', {
          params: {
            courseId: courseId,
            content: val
          }
        }).then(response => {

          if (response.data.code === 200) {

            this.details = response.data.data;
          } else {

            this.$message.error(response.data.message);
          }
        }).catch(error => {

          this.$message.error("网络错误");
        });
      },
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {
          });
      }
    }
  };
</script>

<style scoped>
  .details {
    height: 96%;
    margin-left: 200px;
    transform: translateY(-130px);
    text-align: center;
    overflow: auto;
  }

  .table {
    margin-top: 10px;
  }

  .search {
    margin-left: -16px;
  }

</style>
