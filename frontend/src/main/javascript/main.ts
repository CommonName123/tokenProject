import 'es6-shim';
import 'reflect-metadata';
import Vue from 'vue';
import App from './App.vue';
import router from './router';
import { store } from './stores/store';
import ElementUI from 'element-ui';
import lang from 'element-ui/lib/locale/lang/ru-RU';
import locale from 'element-ui/lib/locale';
import 'element-ui/lib/theme-chalk/index.css';

locale.use(lang);

Vue.use(ElementUI);


new Vue({
    router,
    store,
    render: h => h(App),
}).$mount('#app');
