<script>
import L from "leaflet";
import {onMounted} from "vue";

onMounted(() => {

});

export default {
  data() {
    return {
      map:null,
    }
  },
  props: {
    center: {default:[45.766914, 4.830913]},
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