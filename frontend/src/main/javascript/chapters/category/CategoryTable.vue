<template>
  <el-container direction="vertical">
    <el-button
        @click="openCard"
    >
      <span>Добавить</span>
    </el-button>
    <el-table
        ref="categoryTable"
        :data="categories"
        style="overflow: auto"
        @row-click="selectRow"
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
      <el-table-column>
        <template slot-scope="scope">
          <el-button @click="confirmDialogVisible = true" size="small" icon="el-icon-delete">Удалить</el-button>
          <el-button @click="editCategory" size="small" icon="el-icon-edit">Изменить</el-button>
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
    <el-button type="primary" @click="deleteCategory">Подтвердить</el-button>
  </span>
    </el-dialog>
    <window-card
        title="Добавить новую категорию"
        v-if="dialogVisible"
        :dialogVisible="dialogVisible"
        saveButton="true"
        closeButton="true"
        @on-close="onCloseCard"
        @on-save="onSave"
    >
      <add-category-form :received="selectedCategory" ref="addForm"></add-category-form>
    </window-card>
  </el-container>
</template>

<script src="./CategoryTable.ts" lang="ts"/>

<style scoped>
</style>