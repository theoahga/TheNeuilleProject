<!--suppress ALL -->
<script>
import L from "leaflet";
import {onMounted} from "vue";
import {ca} from "vuetify/locale";
import caserneIcons from "./../assets/caserne.png";
import sensorIcons from "./../assets/sensor.png";
import flameIcons from "./../assets/flame.png";
import truckIcons from "./../assets/firetruck.png"

export default {
  data() {
    return {
      map:null,
      markerStationArray:[],
      markerSensorArray:[],
      markerTruckArray:[],
    }
  },
  props: {
    center: {default:[45.766914, 4.830913]},
    station: {default:[]},
    sensor: {default:[]},
    truck:{default:[]},
  },
  mounted() {
    this.map = L.map('map').setView(this.center, 15);
    L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 19,
      attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    }).addTo(this.map);
  },
  watch:{
    center(){
      this.map.setView(new L.LatLng(this.center[0],this.center[1]),15);
    },
    station(){
      if(this.station.length != 0){

        let caserneIcon = L.icon({
          iconUrl: caserneIcons,
          iconSize: [30,30],
        });

        console.log("Ajout des markers de station !");
        this.station.forEach((element) => {
          let m = new L.marker([element.lat,element.lon],{icon:caserneIcon}).bindPopup(element.address).addTo(this.map);
          this.markerStationArray.push(m);
        })
      }else if (this.station.length === 0){
        console.log("Suppression des markers de station !");
        for(let i=0;i<this.markerStationArray.length;i++){
          this.map.removeLayer(this.markerStationArray[i]);
        }
        this.markerStationArray = [];
      }
    },
    sensor(){
      if(this.sensor.length != 0){

        //Création des icons perso
        let sensorIcon = L.icon({
          iconUrl: sensorIcons,
          iconSize: [30,30],
        });

        let flameIcon = L.icon({
          iconUrl: flameIcons,
          iconSize: [30,30],
        });

        console.log("Ajout des markers sensor !");
        this.sensor.forEach((element) => {
          let m;
          if(element.intensity !== 0){
            console.log("FIRE");
            m = new L.marker([element.lat,element.lon],{icon:flameIcon}).bindPopup(element.adresse).addTo(this.map);
          }else{
            m = new L.marker([element.lat,element.lon],{icon:sensorIcon}).bindPopup(element.adresse).addTo(this.map);
          }
          this.markerSensorArray.push(m);
        })
      }else if(this.sensor.length === 0){
        console.log("Suppression des markers de sensor !");
        for(let i=0;i<this.markerSensorArray.length;i++){
          this.map.removeLayer(this.markerSensorArray[i]);
        }
        this.markerSensorArray = [];
      }
    },
    truck(){

      if(this.truck.length !== 0){
        //Création de l'icone de camion
        let truckIcon = L.icon({
          iconUrl: sensorIcons,
          iconSize: [30,30],
        });

        console.log("Ajout des markers de camions");

        this.truck.forEach((element) => {
          let m = new L.marker([element.lat,element.lon],{icon:truckIcon}).addTo(this.map);
          this.markerTruckArray.push(m);
        })
      }else if(this.sensor.length === 0){
        console.log("Suppression des markers");
        for(let i=0;i<this.markerTruckArray.length;i++){
          this.map.removeLayer(this.markerTruckArray[i]);
        }
        this.markerTruckArray = [];
      }
    }
  }
}
</script>

<template>
  <div id="map"/>
</template>

<style scoped>
#map {
  width: 65%;
  height: 85vh;
  margin-left: auto;
  border-radius: 5px;
}
</style>