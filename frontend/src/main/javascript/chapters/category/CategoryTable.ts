import {Component} from "vue-property-decorator";
import Vue from "vue";
import Category from "../../types/Category";
import categoryApi from "../../api/category/CategoryApi";
import AddCategoryForm from "../category/addCategoryForm/AddCategoryForm.vue";
import WindowCard from "../../component/card/WindowCard.vue";

/**
 * Таблица категорий
 * Created by CommonName123 on 22.08.2022
 */
@Component({components: {WindowCard, AddCategoryForm}})
export default class CategoryTable extends Vue {
    public name: string = 'product-table';

    /**
     * Список категорий
     * @private
     */
    private categories: Category[] = [];
    /**
     * Признак открытого окна
     * @private
     */
    private dialogVisible: boolean = false;

    /**
     * Выбранная категория
     * @private
     */
    private selectedCategory: Category | null = null;

    /**
     * Подтверждение удаления
     * @private
     */
    private confirmDialogVisible: boolean = false;


    /**
     * Инициализация
     * @private
     */
    private mounted() {
        this.loadTable();
    }


    /**
     * Загрузить список категорий
     * @private
     */
    private loadTable() {
        categoryApi.getCategories().then((data: Category[]) => {
            this.categories = data;
        });
    }

    /**
     * Открытие окна с добавлением продукта
     * @private
     */
    private openCard() {
        this.selectedCategory = new Category();
        this.dialogVisible = true;
    }

    /**
     * Событие при закрытии карточки
     * @private
     */
    private onCloseCard() {
        this.dialogVisible = false;
    }

    /**
     * Создание продукта
     * @private
     */
    private onSave() {
        const addForm: any = this.$refs.addForm;
        let formData: Category = addForm.form;
        categoryApi.createCategory(formData).then(data => {
            this.loadTable();
            this.dialogVisible = false;
        });
    }

    /**
     * Событие выбора строки
     * @param row
     * @param column
     * @param event
     * @private
     */
    private selectRow(row: Category, column: any, event: any) {
        this.selectedCategory = row;
    }

    /**
     * Удалить выбранную категорию
     * @private
     */
    private deleteCategory() {
        if (this.selectedCategory && this.selectedCategory.id) {
            categoryApi.deleteCategory(this.selectedCategory.id).then(() => {
                const index = this.categories.findIndex(x => x === this.selectedCategory);
                if (index >= 0) {
                    this.categories.splice(index, 1);
                }
                this.confirmDialogVisible = false;
            });
        }

    }

    /**
     * Изменить выбранную категорию
     * @private
     */
    private editCategory() {
        this.dialogVisible = true;
    }
}