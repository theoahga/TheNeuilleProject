import Vue from 'vue'
import Vuetify from "vuetify";
import '@mdi/font/css/materialdesignicons.css' // Ensure you are using css-loader
import 'vuetify/dist/vuetify.min.css'

Vue.use(Vuetify)


export default new Vuetify({
    icons: {
        iconfont: 'mdi',
    },
})