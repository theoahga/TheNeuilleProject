<script>
import Menu from "@/components/Menu.vue";
import Map from "@/components/Map.vue";
import axios from "axios";

//WebSocket part
import SockJS from "sockjs-client/dist/sockjs";
import Stomp from "webstomp-client";

let stompClient = null;

export default {
  data() {
    return {
      positionMap: [45.766914, 4.830913],
      stationArray: [],
      sensorArray: [],
    }
  },
  components: {
    Map,
    Menu,
  },
  methods:{
    updateStateCaserne : function (e){
      console.log("Display Caserne State : " + e);
      this.getStations(e);
    },
    updateStateTruck : function (e){
      console.log("Display Truck State : " + e);
    },
    updateStateSensor : function (e){
      console.log("Display Sensor State : " + e[1]);
      this.getSensor(e);
    },
    updateMapPositions : function (e){
      console.log("New Map Position : " + e)
      if(e === "Lyon"){
        this.positionMap = [45.766914, 4.830913];
      }else if (e === "Villeurbanne"){
        this.positionMap = [45.783906, 4.869766];
      }
    },
    getStations : async function (event){
      if(event === true) {
        await axios.get("http://localhost:10001/emergency/api/station/getAll")
            .then(response => {
              this.stationArray = response.data;
            })
      }else {
        this.stationArray = [];
      }
    },
    getSensor : async function (e) {
      if(e[1] === true) {
        await axios.get(`http://localhost:10001/emergency/api/sensor/getAll`)
          .then((response) => {
            if(this.sensorArray.length !== 0){
              let presence = false;
              response.data.forEach((element) => {
                for(let i=0 ; i< this.sensorArray.length ; i++){
                  if(element.cid === this.sensorArray[i].cid){
                    console.log("Element déjà présent dans la liste");
                    presence = true;
                    return;
                  }
                }
                if(presence === false){
                  this.sensorArray.push(element);
                }
              })
            }else {
              this.sensorArray = response.data;
            }
          });
      } else {
        this.sensorArray = []
      }
    },
    connectWSFireSensor : function () {
      let self = this;
      let socket = new SockJS('http://127.0.0.1:10001/emergency/api/ws');
      stompClient = Stomp.over(socket);
      stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/sensor', function (greeting) {
          self.sensorArray = [];
          self.getSensor(["Affiche",true]);
        });
      });
    }
  }
}
</script>

<template>
  <div class="wrapper-content">
    <Menu @change-state-caserne="updateStateCaserne($event)" @change-state-truck="updateStateTruck($event)" @change-map-position="updateMapPositions($event)" @change-sensor-truck="updateStateSensor($event)" @connect-websocket-fireSensor="connectWSFireSensor()"/>
    <Map :center="positionMap" :station="stationArray" :sensor="sensorArray"/>
  </div>
</template>

<style scoped>
  .wrapper-content {
    width: 95%;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    margin-left: auto;
    margin-right: auto;
  }
</style>