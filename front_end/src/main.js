import Vue from 'vue'
import App from './App.vue'
import router from './router'
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'
import store from './store'
//入口
import '@/permission'
Vue.config.productionTip = true//阻止启动生产消息，一些提示信息
const options = {
  namespace: 'pro__',
  name: 'ls',
  storage: 'local'
}
Vue.use(Antd)

Vue.directive('title', {
  inserted: function (el, binding) {
    document.title = el.dataset.title
  }
})
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
//$mount    手动挂载生成的实例到index.html的<div id="app"></div>