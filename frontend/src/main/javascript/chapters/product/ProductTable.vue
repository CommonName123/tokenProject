<template>
  <el-container direction="vertical">
    <el-table
        ref="productTable"
        :data="products"
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
      <el-table-column
          label="Цена"
          prop="price">
      </el-table-column>
      <el-table-column
          label="Изображение"
          prop="photo">
      </el-table-column>
      <el-table-column
          label="Категория"
          prop="category">
      </el-table-column>
    </el-table>
  </el-container>
</template>

<script src="./ProductTable.ts" lang="ts"/>

<style scoped>
</style>