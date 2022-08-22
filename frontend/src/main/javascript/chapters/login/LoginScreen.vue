<template>
  <div class="login-form-cover-block">
    <h1>Войдите в систему</h1>
    <el-form
        class="login_form"
        ref="login_form"
    >
      <el-form-item
          prop="login"
          class="login-input"
          label="Логин"
          label-width="120px"
      >
        <el-input
            size="small"
            placeholder="Имя пользователя"
            v-model="login"
        ></el-input>
      </el-form-item>
      <el-form-item
          prop="password"
          class="login-input"
          label="Пароль"
          label-width="120px"
      >
        <el-input
            size="small"
            placeholder="Пароль"
            v-model="password"
            type="password"
        ></el-input>
      </el-form-item>
        <el-button
            size="small"
            type="primary"
            @click="doLogin"
            style="margin: auto;display: block;"
        >Войти</el-button
        >
    </el-form>
  </div>
</template>

<script src="./LoginScreen.ts" lang="ts"/>

<style scoped>
.login-form-cover-block,
.login_form {
  font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif;
}
.login-form-cover-block {
  width: 500px;
  height: 300px;
  margin: auto;
  padding: 20px 50px 0px;
  box-shadow: 0 0 10px -3px #0f0f0f;
  background-color: #fff;
  z-index: 2;
}
.login-form-cover-block >>> h1 {
  text-align: center;
  font-size: 36px;
  font-family: inherit;
  line-height: normal;
  margin: 20px 0 10px 0;
  font-weight: 500;
}
.login_form >>> .el-form-item__error {
  margin-bottom: 20px;
}
.login_form >>> .el-form-item__label {
  font-weight: bold;
}
.login_form >>> .button_center {
  display: flex;
}
.button_center >>> .el-form-item__content {
  margin: 0 auto;
}
.auth_error label {
  font-style: italic;
  font-family: inherit;
  font-size: 14px;
  color: #f56c6c;
}
.login-input >>> .el-input__inner {
  width: 200px;
  height: 40px !important;
  font-family: inherit;
  font-size: 14px;
  padding: 4px;
  line-height: normal !important;
  border-color: #c0c4cc;
}
.login-input >>> .el-input__inner:focus {
  border-color: #409eff;
}
</style>