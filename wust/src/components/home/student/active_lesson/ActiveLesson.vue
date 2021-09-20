<template>
  <div class="active-lesson">
    <div class="search">
      <el-input style="width: 300px;transform: translateX(-380px)"
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
        <el-table-column prop="className" label="教学班"></el-table-column>
        <el-table-column prop="week" label="开课周次" width="150 px"></el-table-column>

        <el-table-column width="70px">
          <!--插槽-->
          <template slot="header" slot-scope="scope">
            <span>选课</span>
          </template>

          <template slot-scope="scope">
            <el-button style="color: dodgerblue" @click="doSelect(scope)">选择</el-button>
          </template>
        </el-table-column>


      </el-table>
    </div>
  </div>
</template>

<script>
  import axios from 'axios';
  import QS from 'qs';

  export default {
    name: "ActiveLesson",
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
      doSelect(scope) {

        let courseId = scope.row.id;
        let teacherId = scope.row.teacherId;
        let data = {"courseId": courseId, "teacherId": teacherId};

        axios.post('http://localhost:8081/stu_cou/add',
          QS.stringify(data),
          {
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded'
            }
          }
        ).then(response => {

          if (response.data.code === 200) {

            this.$message.success(response.data.message);
          } else {

            this.$message.error(response.data.message);
          }

        }).catch(error => {

          this.$message.error("网络错误");
        });
      },
      getInfo(val) {

        axios.get('http://localhost:8081/tea_cou/getLike?content=' + val)
          .then(response => {

            if (response.data.code === 200) {

              this.courseInfo = response.data.data;
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
  .active-lesson {
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
