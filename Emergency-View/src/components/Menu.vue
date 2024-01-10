<script>
export default {
  data: () => ({
    locations: ['Lyon','Villeurbanne'],
    location: 'Lyon',
  }),
  methods: {
    changeCaserneState: function (e){
      this.$emit("change-state-caserne",e.target.checked);
    },
    changeTruckState: function (e){
      this.$emit("change-state-truck",e.target.checked);
    },
    updateMapPositions: function (e){
      this.$emit("change-map-position",e)
    }
  }
}

</script>

<template>
  <div class="menu">
    <div class="wrapperLocation"><v-select v-model="location" :items="locations" label="Choose location !" v-on:update:model-value="updateMapPositions($event)"></v-select></div>
    <div class="wrapperCaserne"><v-checkbox dark label="Display caserne" class="dispCaserne" v-on:click="changeCaserneState($event)"></v-checkbox></div>
    <div class="wrapperTruck"><v-checkbox dark label="Display truck" class="dispTruck" v-on:click="changeTruckState($event)"></v-checkbox></div>
    <div class="wrapperButton"><v-btn depressed color="error" class="btnConnect">Connect</v-btn></div>
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

  .wrapperButton {
    width: 100%;
    height: fit-content;
    display: flex;
    justify-content: center;
    margin-top: 1vh;
    .btn-apply {
      width: 6vw;
      font-size: 1rem;
    }
  }
</style>