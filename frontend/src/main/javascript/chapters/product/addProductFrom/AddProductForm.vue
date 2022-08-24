<template>
<el-form>
  <el-form-item label="Название">
    <el-input v-model="form.name"></el-input>
  </el-form-item>
  <el-form-item label="Описание">
    <el-input v-model="form.description"></el-input>
  </el-form-item>
  <el-form-item label="Цена">
    <el-input v-model="form.price"></el-input>
  </el-form-item>
  <el-form-item label="Изображение">
    <input type="file" accept="image/*" @change="loadPicture">
    <img
        :src="photo"
        style="margin-right: 10px; width: 30%; height: 50%;"
    />
  </el-form-item>
  <el-form-item label="Категория">
    <el-select v-model="selectedCategoryId"
    @change="onSelectCategory">
      <el-option
          v-for="item in categories"
          :key="item.id"
          :label="item.name"
          :value="item.id">
      </el-option>
    </el-select>
  </el-form-item>
</el-form>
</template>

<script src="./AddProductForm.ts" lang="ts" >
</script>

<style scoped>

</style>