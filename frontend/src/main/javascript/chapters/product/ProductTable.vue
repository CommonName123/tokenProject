<template>
  <el-container direction="vertical">
    <el-button
        @click="openCard"
    >
      <span>Добавить</span>
    </el-button>
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
          prop="categoryName">
      </el-table-column>
    </el-table>
    <window-card
        title="Добавить новый продукт"
        v-if="dialogVisible"
        :dialogVisible="dialogVisible"
        saveButton="true"
        closeButton="true"
        @on-close="onCloseCard"
        @on-save="onSave"
    >
      <add-product-form ref="addForm"></add-product-form>
    </window-card>
  </el-container>
</template>

<script src="./ProductTable.ts" lang="ts"/>

<style scoped>
</style>