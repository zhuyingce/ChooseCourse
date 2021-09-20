import Vue from 'vue'
import Router from 'vue-router'
import Log_Reg from "../views/Log_Reg";
import Login from "../components/log_reg/Login";
import Register from "../components/log_reg/Register";
import PersonInfo from "../components/home/per_info/PersonInfo";
import ActiveLesson from "../components/home/student/active_lesson/ActiveLesson";
import Selected from "../components/home/student/selected/Selected";
import AddLesson from "../components/home/teacher/add_lesson/AddLesson";
import Home from "../views/Home";
import TeachLesson from "../components/home/teacher/teach_lesson/TeachLesson";
import Details from "../components/home/teacher/details/Details";

Vue.use(Router);

export default new Router({
  mode:'history',

  routes: [
    //登录--------------------------
    {
      path: '/',
      redirect: '/log_reg/login'
    },

    {
      path: '/log_reg',
      redirect: '/log_reg/login'
    },

    {
      path: '/log_reg',
      component: Log_Reg,
      children: [
        {path: 'login', component: Login},
        {path: 'register', component: Register}
      ]
    },

    //主页面------------------------------------------
    {
      path: '/home/:type',
      component: Home,
      children: [
        {path: 'per_info', component: PersonInfo},
        {path: 'active', component: ActiveLesson},
        {path: 'selected', component: Selected},
        {path: 'add', component: AddLesson},
        {path: 'tea_ch', component: TeachLesson},
        {path: 'details/:courseId', component: Details}
      ]
    }
  ]
})
