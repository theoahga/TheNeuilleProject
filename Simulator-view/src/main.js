import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import vuetify from "@/plugins/vuetify";
import { createPinia } from "pinia";
import "./assets/main.css";

// Création de l'instance Pinia
const pinia = createPinia();
Vue.use(pinia);

// Configuration de l'application Vue
new Vue({
  pinia, // Attache Pinia à l'instance Vue
  vuetify,
  router,
  render: (h) => h(App),
}).$mount("#app");