<template>
  <el-menu
      router='router'
      class="el-menu-vertical-demo"
      style="width: 200px;"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b">
    <el-menu-item
        v-for="task in tasks"
        v-bind:index="task.index"
        v-bind:route="task.route"
        v-bind:key="task.index">
      <span>{{ task.name }}</span>
    </el-menu-item>
    <el-button
        class="exitButton"
        @click="doLogout"
    >
      <span>Выход</span>
    </el-button>
  </el-menu>
</template>

<script src="./MyNavigation.ts" lang="ts">
</script>

<style scoped>
.exitButton{
  background: transparent;
  color: white;
}
.exitButton:hover {
  color: white;
  background-color: transparent;
}
</style>