import {Component} from "vue-property-decorator";
import Vue from "vue";
import Product from "../../../types/Product";
import categoryApi from "../../../api/category/CategoryApi";
import Category from "../../../types/Category";

/**
 * Форма для добалвения новых продуктов
 * Created by CommonName123 on 23.08.2022
 */
@Component
export default class AddProductForm extends Vue {
    public name: string = 'add-product-form';

    public form: Product = new Product();

    private categories: Category[] = [];

    public selectedCategoryId: string = "";


    /**
     * Инициализация
     * @private
     */
    private mounted() {
        categoryApi.getCategories().then(data => {
            this.categories = data;
        });
    }

    /**
     * При выборе категории привязываем её к новому продукту
     * @param selectedCategoryId
     * @private
     */
    private onSelectCategory(selectedCategoryId: string) {
        this.form.category = selectedCategoryId;
    }

}