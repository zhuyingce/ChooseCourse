<template>
  <div class="teach-lesson">
    <div class="table">
      <el-table :data="courseInfo" border style="width: 100%">
        <el-table-column type="index" label="序号" width="50px"></el-table-column>
        <el-table-column prop="name" label="课程名称" width="120px"></el-table-column>
        <el-table-column prop="credit" label="学分" width="100px"></el-table-column>
        <el-table-column prop="classHour" label="学时" width="100px"></el-table-column>
        <el-table-column prop="classroom" label="教室" width="120px"></el-table-column>
        <el-table-column prop="className" label="班级" width="120px"></el-table-column>
        <el-table-column prop="week" label="上课时间"></el-table-column>
        <el-table-column prop="stuNum" label="选课人数" width="80px"></el-table-column>

        <el-table-column width="100px">
          <!--插槽-->
          <template slot="header" slot-scope="scope">
            <span>学生信息</span>
          </template>

          <template slot-scope="scope">
            <el-button style="color: dodgerblue" @click.native="details(scope)">查 看</el-button>
          </template>
        </el-table-column>

        <el-table-column width="80px">
          <!--插槽-->
          <template slot="header" slot-scope="scope">
            <span>删 除</span>
          </template>

          <template slot-scope="scope">
            <el-button style="color: dodgerblue"
                       @click="dialogVisible = true">删 除</el-button>

            <el-dialog
              title="提示"
              :visible.sync="dialogVisible"
              width="30%"
              :before-close="handleClose">
              <span style="font-size: 19px">确认删除该课程？</span>
              <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="doDelete(scope)">确 定</el-button>
              </span>
            </el-dialog>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
  import axios from 'axios';

  export default {
    name: "TeachLesson",

    data() {
      return {
        dialogVisible: false,
        courseInfo:[]
      };
    },
    created() {

      this.getInfo();
    },
    methods: {
      doDelete(scope) {

        this.dialogVisible = false;

        let courseId = scope.row.id;

        axios.delete('http://localhost:8081/tea_cou/delete/' + courseId,
        ).then(response => {

          if (response.data.code === 200) {

            this.getInfo();
            this.$message.success(response.data.message);
          } else {

            this.$message.error(response.data.message);
          }

        }).catch(error => {

          this.$message.error("网络错误");
        });
      },
      details(scope) {

        let courseId = scope.row.id;

        this.$router.push('/home/teacher/details/' + courseId);
      },
      getInfo() {
        axios.get('http://localhost:8081/tea_cou/teach')
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
  .teach-lesson {
    height: 96%;
    margin-left: 200px;
    transform: translateY(-130px);
    text-align: center;
    overflow: auto;
  }
</style>
