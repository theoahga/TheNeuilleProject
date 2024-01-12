import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

import vuetify from "@/plugins/vuetify.js";

createApp(App).use(vuetify,router).mount('#app');
