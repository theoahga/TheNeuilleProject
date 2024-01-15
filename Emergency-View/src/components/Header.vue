<script setup>
  defineProps({
    msg: {
      type: String,
      required: true
    }
  })
</script>

<template>
  <div class="header">
    <h1> {{msg}}</h1>
  </div>
</template>

<style scoped>
.header {
  width: 100%;
  height: 8vh;
  display: flex;
  align-items: center;
  justify-content: center;
  color: var(--color-heading);
}
</style>