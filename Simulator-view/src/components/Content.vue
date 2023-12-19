<script>

import {LMap, LTileLayer} from 'vue2-leaflet';

export default {
  components: {
    LMap,
    LTileLayer,
  },
  data () {
    return {
      url: 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
      attribution:
          '&copy; <a target="_blank" href="http://osm.org/copyright">OpenStreetMap</a> contributors',
      zoom: 16,
      center: [45.783906, 4.869766],
      bounds: null,
      e1: 'Lyon',
      items: [
        'Lyon','Villeurbanne',
      ],
    };
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
    }
  }
}

</script>

<template>
  <div class="wrapper">
    <div class="menu-wrapper">
      <div class="wrapper-display">
        <input type="checkbox" id="displaySensor">
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
         ></v-select>
      </v-app>
      <v-btn depressed color="error" class="btn-apply">Apply</v-btn>
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
        input {
          width: 1.3vw;
          height: 1.3vw;
          margin-right: 1%;
          border: none;
        }
        p{
          font-family: "Roboto", sans-serif;
          font-size: 1rem;
          color: var(--color-heading);
        }
      }
      #app {
        padding: 0;
        margin: 0;
        background: none;
        height: 10vh;
        h3 {
          font-family: "Roboto", sans-serif;
          color: var(--color-heading);
          margin-top: 2vh;
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
        margin-top: 1vh;
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