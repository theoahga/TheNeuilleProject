<script>
import Menu from "@/components/Menu.vue";
import Map from "@/components/Map.vue";

export default {
  data() {
    return {
      positionMap: [45.766914, 4.830913],
    }
  },
  components: {
    Map,
    Menu,
  },
  methods:{
    updateStateCaserne : function (e){
      console.log("Display Caserne State : " + e);
    },
    updateStateTruck : function (e){
      console.log("Display Truck State : " + e);
    },
    updateMapPositions : function (e){
      console.log("New Map Position : " + e)
      if(e === "Lyon"){
        this.positionMap = [45.766914, 4.830913];
      }else if (e === "Villeurbanne"){
        this.positionMap = [45.783906, 4.869766];
      }
    }
  }
}
</script>

<template>
  <div class="wrapper-content">
    <Menu @change-state-caserne="updateStateCaserne($event)" @change-state-truck="updateStateTruck($event)" @change-map-position="updateMapPositions($event)"/>
    <Map :center="positionMap"/>
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