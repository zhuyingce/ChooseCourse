<template>
    <div class="log-reg">
      <div class="title">
        我的课程,我选择
      </div>
      <LoginRegister/>
    </div>
</template>

<script>
    import LoginRegister from "../components/log_reg/LoginRegister";

    export default {
      name: "Log_Reg",
      components:{
          LoginRegister
      }
    }
</script>

<style scoped>
  .log-reg {
    background-image: url(../assets/background.jpg);
    /*height: 585px;*/
    height: 100%;
    background-size: 100% 100%;
    background-repeat: no-repeat;
  }

  .title {
    text-align: center;
    padding-top: 35px;
    padding-left: 30px;
    font-size: 60px;
    font-family: '华文彩云',sans-serif;
    font-style: italic;
    letter-spacing: 40px;
    color: darkblue;
  }
</style>
