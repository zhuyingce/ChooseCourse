<template>
  <div class="add-lesson">
    <div class="panel">
      <el-form :label-position="labelPosition" label-width="100px">
        <el-form-item label="课程名称">
          <el-input v-model="courseInfo.name" placeholder="请输入课程名称"></el-input>
        </el-form-item>

        <el-form-item label="上课地点">
          <el-input v-model="courseInfo.classroom" placeholder="请输入上课地点"></el-input>
        </el-form-item>

        <el-form-item label="班级名称">
          <el-input v-model="courseInfo.className" placeholder="请输入课程名称"></el-input>
        </el-form-item>

        <el-form-item label="上课时间">
          <el-input v-model="courseInfo.week" placeholder="请输入上课时间"></el-input>
        </el-form-item>
      </el-form>

      <el-button style="font-size: 20px" type="primary"
                 @click="dialogVisible = true">确 定</el-button>

      <el-dialog
        title="提示"
        :visible.sync="dialogVisible"
        width="30%"
        :before-close="handleClose">
        <span style="font-size: 19px">确认添加该课程到选课栏？</span>
        <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="submit">确 定</el-button>
  </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>
  import axios from 'axios';

  export default {
    name: "AddLesson",
    data() {
      return {
        labelPosition: 'right',
        dialogVisible: false,
        courseInfo: {
          name: "",
          classroom: "",
          className: "",
          week: ""
        }
      }
    },
    methods: {
      submit() {

        this.dialogVisible = false;

        axios.post("http://localhost:8081/tea_cou/add",
          this.courseInfo
        ).then(response => {

          if (response.data.code === 200) {

            this.$message.success("添加成功");
          } else {

            this.$message.error(response.data.message);
          }
        }).catch(response => {

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

  .add-lesson {
    height: 75%;
    margin-left: 200px;
    text-align: center;
    overflow: auto;
  }

  .panel {
    margin: auto;
    width: 50%;
  }
</style>
