<template>
    <el-container style="height: 100%;background: aliceblue;" direction="vertical">
      <login-screen v-if="isUserUnauthorised"></login-screen>
        <keep-alive>
            <new-menu v-if="!isUserUnauthorised"/>
        </keep-alive>
    </el-container>

</template>

<script lang="ts" src="./App.ts"></script>

<style lang="scss">
body {
    height: 100%;
    margin: 0;
    font-family: arial, sans-serif;
    overflow: hidden;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    background-color: white;
}
html {
    height: 100%;
}
</style>

<style scoped>
</style>
