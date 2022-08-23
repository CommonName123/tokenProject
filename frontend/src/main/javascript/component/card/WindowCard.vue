<template>
  <el-dialog
      ref="windowCard"
      :title="title"
      :visible="dialogVisible"
      :width="width"
  >
    <slot/>
    <span slot="footer" class="dialog-footer">
    <el-button v-if="saveButton" type="primary" @click="onSave">Сохранить</el-button>
    <el-button v-if="closeButton" @click="onClose">Закрыть</el-button>
  </span>
  </el-dialog>
</template>

<script src="./WindowCard.ts" lang="ts"/>

<style scoped/>