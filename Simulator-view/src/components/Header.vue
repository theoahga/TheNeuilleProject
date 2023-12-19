<script>
</script>

<template>
  <div class="wrapper-header">
    <h1> Simulation</h1>
  </div>
</template>

<style scoped>

.wrapper-header {
  width: 100%;
  height: 20%;
  background: var(--color-background);
  display: flex;
  align-items: center;
  color: var(--color-heading);
  padding-left: 0.5vw;
  padding-right: 0.5vw;
  h1 {
    margin-left: auto;
    margin-right: auto;
    font-size: 2rem;
    font-family: "Roboto", sans-serif;
  }
}

@media (prefers-color-scheme: dark) {
  .wrapper-header {
    width: 100%;
    height: fit-content;
    background: var(--color-background);
    display: flex;
    align-items: center;
    color: var(--color-heading);
    padding-left: 0.5vw;
    padding-right: 0.5vw;
    h1 {
      margin-left: auto;
      margin-right: auto;
      font-size: 2rem;
      font-family: "Roboto", sans-serif;
    }
  }
}

</style>