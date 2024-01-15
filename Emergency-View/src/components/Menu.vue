<script>
export default {
  data: () => ({
    locations: ['Lyon','Villeurbanne'],
    location: 'Lyon',
    actualPosition: 'Lyon',
  }),
  methods: {
    changeCaserneState: function (e){
      this.$emit("change-state-caserne",e.target.checked);
    },
    changeTruckState: function (e){
      this.$emit("change-state-truck",e.target.checked);
    },
    changeSensorState: function (e){
      this.$emit("change-sensor-truck",{1:e.target.checked,2:this.actualPosition});
    },
    updateMapPositions: function (e){
      this.actualPosition = e;
      this.$emit("change-map-position",e)
    },
    connectFire: function (){
      this.$emit("connect-websocket-fireSensor");
    }
  }
}

</script>

<template>
  <div class="menu">
    <div class="wrapperLocation"><v-select v-model="location" :items="locations" label="Choose location !" v-on:update:model-value="updateMapPositions($event)"></v-select></div>
    <div class="wrapperCaserne"><v-checkbox dark label="Display caserne" class="dispCaserne" v-on:click="changeCaserneState($event)"></v-checkbox></div>
    <div class="wrapperButton"><v-btn depressed color="error" class="btnConnectFire" v-on:click="connectFire()">Connect Fire</v-btn><v-btn depressed color="#1E88E5" class="btnConnectTruck">Connect Truck</v-btn></div>
  </div>
</template>

<style scoped>
  .menu {
    width: 32.5%;
    height: 92vh;
  }

  .wrapperLocation {
    width: 90%;
    height: fit-content;
    margin-top: 2vh;
    margin-left: 5%;
    color: var(--vt-c-text-dark-1);
  }

  .wrapperCaserne {
    width: 90%;
    height: fit-content;
    margin-left: 5%;
    margin-top: 1vh;
    color: var(--vt-c-text-dark-1);
    .dispCaserne {
      display: flex;
    }
  }

  .wrapperTruck {
    width: 90%;
    height: fit-content;
    margin-left: 5%;
    color: var(--vt-c-text-dark-1);
    .dispTruck {
      display: flex;
      font-size: 1rem;
    }
  }

  .wrapperSensor {
    width: 90%;
    height: fit-content;
    margin-left: 5%;
    color: var(--vt-c-text-dark-1);
    .dispSensor {
      display: flex;
      font-size: 1rem;
    }
  }

  .wrapperButton {
    width: 80%;
    height: fit-content;
    display: flex;
    justify-content: space-around;
    margin-top: 1vh;
    margin-left: auto;
    margin-right: auto;
    .btn-apply {
      width: 6vw;
      font-size: 1rem;
    }
  }
</style>