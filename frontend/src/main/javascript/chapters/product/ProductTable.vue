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
        @row-click="selectRow"
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
        <template slot-scope="scope">
          <img
              :src="scope.row.photo"
              style="margin-right: 10px; width: 30%; height: 50%;"
          />
        </template>
      </el-table-column>
      <el-table-column
          label="Категория"
          prop="categoryName">
      </el-table-column>
      <el-table-column>
        <template slot-scope="scope">
          <el-button @click="confirmDialogVisible = true" size="small" icon="el-icon-delete">Удалить</el-button>
          <el-button @click="editProduct" size="small" icon="el-icon-edit">Изменить</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
        title="Удаление"
        :visible.sync="confirmDialogVisible"
        width="30%"
        center>
      <span>Вы действительно хотите удалить данную запись?</span>
      <span slot="footer" class="dialog-footer">
    <el-button @click="confirmDialogVisible = false">Отменить</el-button>
    <el-button type="primary" @click="deleteProduct">Подтвердить</el-button>
  </span>
    </el-dialog>
    <window-card
        title="Добавить новый продукт"
        v-if="dialogVisible"
        :dialogVisible="dialogVisible"
        saveButton="true"
        closeButton="true"
        @on-close="onCloseCard"
        @on-save="onSave"
    >
      <add-product-form ref="addForm" :received="selectedProduct"></add-product-form>
    </window-card>
  </el-container>
</template>

<script src="./ProductTable.ts" lang="ts"/>

<style scoped>
</style>