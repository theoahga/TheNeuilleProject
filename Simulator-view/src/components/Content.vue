<script>

import {LMap, LTileLayer, LMarker, LCircle} from 'vue2-leaflet';
import axios from "axios";
import { defineStore, mapStores } from "pinia";

//Import for Socket Part
import SockJS from "sockjs-client/dist/sockjs";
import Stomp from "webstomp-client"


import { onMounted } from "vue";


let stompClient = null;




const simStore = defineStore('simulationStore', {
  state: () => {
    return {
      displaySensor: false,
      selectedSDIS: 1,
      items: [],
    }
  },
});

let store;

export default {
  name: "websocket",
  components: {
    LMap,
    LTileLayer,
    LMarker,
    LCircle
  },
  data () {
    return {
      url: 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
      attribution:
          '&copy; <a target="_blank" href="http://osm.org/copyright">OpenStreetMap</a> contributors',
      zoom: 15,
      center: [45.766914, 4.830913],
      bounds: null,
      markers: [],
      e1: 'Lyon',
      items: ['Lyon','Villeurbanne'],
      received_messages: [],
      send_message: null,
      connected: false,
      fireArray: [],
      fireSensor: [],
    };
  },
  computed: {
    ...mapStores(simStore)
  },
  methods: {
    zoomUpdated (zoom) {
      this.zoom = zoom;
    },
    centerUpdated (center) {
      this.center = center;
    },
    boundsUpdated (bounds) {
      this.bounds = bounds;
    },
    displaySensorState : function (){
      let checkBox = document.getElementById("displaySensor").checked;

      store.displaySensor = !checkBox;
      let displaySensorValue = store.displaySensor;
      console.log(`Display Sensor : ${displaySensorValue}`);

    },
    getSensors : async function () {
      this.clearArray();
      if(store.displaySensor === true){
        console.log("AAAAAAA");
        await axios.get(`http://localhost:10000/simulator/api/sensor/getDescriptionByIdVille?id=${store.selectedSDIS}`)
            .then(response => {
              console.log(store.selectedSDIS);
              console.log(response);
              let tabSensor = response.data;
              tabSensor.forEach((element) => {
                store.items.push(element);
                this.markers.push(element)
              });
              this.updatePosition(store.selectedSDIS);
            })
            .catch(error => console.log(error))
        this.connectFire();
      }else {
        this.updatePosition(store.selectedSDIS);
      }
    },
    updatePosition : function (id) {
      if(id === 2){
        console.log('Update Map to Villeurbanne !');
        this.centerUpdated([45.783906, 4.869766]);
        this.zoomUpdated(15);
      } else if (id === 1){
        console.log('Update Map to Lyon !');
        this.centerUpdated([45.766914, 4.830913]);
        this.zoomUpdated(15);
      }
    },
    changeSDIS : function () {
      let selectSDIS = this.e1;

      if(selectSDIS === 'Lyon') {
        store.selectedSDIS = 1;
      }else if (selectSDIS === 'Villeurbanne') {
        store.selectedSDIS = 2;
      }
      console.log(`Selected SDIS ID : ${store.selectedSDIS}`);
    },
    clearArray: function () {
      this.markers = [];
      store.items = [];
    },
    updateFire: function (fire){
      this.fireArray = fire;
    },
    updateSensor: function (sensor){
      this.fireSensor = sensor;
    },
    connectFire: function () {
      let self = this;
      let socket = new SockJS('http://127.0.0.1:10000/simulator/api/ws');
      stompClient = Stomp.over(socket);
      stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/fire', function (greeting) {
          self.updateFire(JSON.parse(greeting.body))
          console.log(self.fireArray);
        });
      });
    },
    connectSensor: function () {
      let self = this;
      let socket = new SockJS('http://127.0.0.1:10000/simulator/api/ws');
      stompClient = Stomp.over(socket);
      stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/sensor', function (greeting) {
          self.updateSensor(JSON.parse(greeting.body));
          console.log(self.fireSensor);
        });
      });
    },
  },
  setup() {
    store = simStore();
    return store;
  },
}

</script>

<template>
  <div class="wrapper">
    <div class="menu-wrapper">
      <div class="wrapper-display">
        <v-checkbox id="displaySensor" dark v-on:click="displaySensorState"></v-checkbox>
        <p>Display sensor on the map !</p>
      </div>
      <v-app class="wrapper-SDIS">
        <h3>Select SDIS :</h3>
         <v-select
          v-model="e1"
          :items="items"
          menu-props="auto"
          label="Select"
          hide-details
          single-line
          dark
          class="SDIS-select"
          v-on:change="changeSDIS"
         ></v-select>
      </v-app>
      <v-btn depressed color="error" class="btn-apply" v-on:click="getSensors">Apply & Connect WS</v-btn>
    </div>
    <div class="map-wrapper">
        <l-map
            style="height: 90%; width: 90%; border-radius: 20px"
            :zoom="zoom"
            :center="center"
            @update:zoom="zoomUpdated"
            @update:center="centerUpdated"
            @update:bounds="boundsUpdated"
        >
          <l-tile-layer :url="url" :attribution="attribution"></l-tile-layer>
          <l-marker v-for="(marker, index) in markers" :key="index" :lat-lng="marker" id="marker"></l-marker>
          <l-circle v-for="(fire,index) in fireArray" :key="index" :lat-lng="[fire.lon,fire.lat]" :radius="fire.radius*10" :color="'red'"/>
        </l-map>
    </div>
  </div>
</template>

<style scoped>
  .wrapper {
    width: 100%;
    height: 100%;
    display: flex;
    .menu-wrapper {
      width: 30%;
      height: 95%;
      padding: 4%;
      .wrapper-display {
        display: flex;
        width: 100%;
        height: fit-content;
        align-items: center;
        p{
          margin-left: 1%;
          font-family: "Roboto", sans-serif;
          font-size: 1rem;
          color: var(--color-heading);
        }
      }
      #app {
        padding: 0;
        margin: 0;
        background: none;
        height: 12vh;
        h3 {
          font-family: "Roboto", sans-serif;
          color: var(--color-heading);
          margin-top: 1vh;
          margin-bottom: 0;
        }
        .SDIS-select {
          font-family: "Roboto", sans-serif;
          padding: 0;
          margin: 0;
        }
      }
      .btn-apply {
        background: #FF6060;
        font-family: "Roboto", sans-serif;
      }
    }
    .map-wrapper {
      width: 70%;
      height: 95%;
      display: flex;
      justify-content: center;
      align-items: center;
    }
  }
</style>