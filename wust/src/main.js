// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
Vue.use(ElementUI);

import axios from 'axios';

//携带cookie
axios.defaults.withCredentials=true;
//阻止 vue 在启动时生成生产提示
Vue.config.productionTip = false;

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
      router.push({path: "/log_reg/login", replace: false}).then(r => {});
    }

    return response;
  }
);

//默认加载图片
//全局注册自定义指令，用于判断当前图片是否能够加载成功，可以加载成功则赋值为img的src属性，否则使用默认图片
Vue.directive('real-img',
  async function (el, binding) {//指令名称为：real-img
  let imgURL = binding.value;//获取图片地址
  if (imgURL) {
    let exist = await imageIsExist(imgURL);
    if (exist) {
      el.setAttribute('src', imgURL);
    }
  }
});

/**
 * 检测图片是否存在
 * @param url
 */
let imageIsExist = function (url) {
  return new Promise((resolve) => {
    let img = new Image();
    img.onload = function () {
      if (this.complete === true) {
        resolve(true);
        img = null;
      }
    };
    img.onerror = function () {
      resolve(false);
      img = null;
    };
    img.src = url;
  })
};

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
});
