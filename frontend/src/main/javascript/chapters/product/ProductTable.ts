import {Component} from "vue-property-decorator";
import Vue from "vue";
import productApi from "../../api/product/ProductApi";
import Product from "../../types/Product";
import WindowCard from "../../component/card/WindowCard.vue";
import AddProductForm from "./addProductFrom/AddProductForm.vue";
import Category from "../../types/Category";
import categoryApi from "../../api/category/CategoryApi";

/**
 * Таблица продуктов
 * Created by CommonName123 on 22.08.2022
 */
@Component({components: {WindowCard, AddProductForm}})
export default class ProductTable extends Vue {
    public name: string = 'product-table';

    /**
     * Список продуктов
     * @private
     */
    private products: Product[] = [];

    private selectedProduct: Product | null = null;

    /**
     * Признак открытого окна
     * @private
     */
    private dialogVisible: boolean = false;

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
     * Загрузить данные в таблицу
     * @private
     */
    private loadTable() {
        productApi.getProducts().then((data: Product[]) => {
            this.products = data;
        });
    }

    /**
     * Открытие окна с добавлением продукта
     * @private
     */
    private openCard() {
        this.selectedProduct = new Product();
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
        let formData: Product = addForm.form;
        productApi.updateProduct(formData).then(data => {
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
    private selectRow(row: Product, column: any, event: any) {
        this.selectedProduct = row;
    }

    /**
     * Удалить выбранный продукт
     * @private
     */
    private deleteProduct() {
        if (this.selectedProduct && this.selectedProduct.id) {
            productApi.deleteProduct(this.selectedProduct.id).then(() => {
                const index = this.products.findIndex(x => x === this.selectedProduct);
                if (index >= 0) {
                    this.products.splice(index, 1);
                }
                this.confirmDialogVisible = false;
            });
        }

    }

    /**
     * Изменить выбранный продукт
     * @private
     */
    private editProduct() {
        this.dialogVisible = true;
    }
}