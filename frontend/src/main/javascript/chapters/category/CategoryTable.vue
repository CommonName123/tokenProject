<template>
  <el-container direction="vertical">
    <el-table
        ref="productTable"
        :data="categories"
        style="overflow: auto"
        highlight-current-row
        border
    >
      <el-table-column
          label="Название"
          prop="name"
      />
      <el-table-column
          label="Описание"
          prop="description">
      </el-table-column>
    </el-table>
  </el-container>
</template>

<script src="./CategoryTable.ts" lang="ts"/>

<style scoped>
</style>