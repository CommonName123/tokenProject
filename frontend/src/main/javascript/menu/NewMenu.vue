<template>
  <el-container direction="horizontal" style="height: 100%">
    <my-navigation :link="section"/>
    <router-view v-slot="{ Component, route }" style="height: calc(100% - 50px);width: 100%">
      <component :is="section" view-prop="value"/>
    </router-view>
  </el-container>
</template>

<script src="./NewMenu.ts" lang="ts"/>

<style scoped>

</style>