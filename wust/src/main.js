// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
Vue.use(ElementUI);

import axios from 'axios';

Vue.prototype.axios = axios;

//请求拦截器，在请求头中加入token
axios.interceptors.request.use(
  (config) => {

    if (localStorage.getItem("token")) {
      config.headers.token = localStorage.getItem("token");
    }

    return config;
  }
);

//响应拦截器
axios.interceptors.response.use(
  response => {

    if (response.data.code === 1008) {

      //删除token
      localStorage.removeItem("token");
      //跳转到登录界面
      router.push({path: "/log_reg/login", replace: false});
    }

    return response;
  }
);

//携带cookie
axios.defaults.withCredentials=true;

Vue.config.productionTip = false;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
});
