import {Component} from "vue-property-decorator";
import Vue from "vue";
import productApi from "../../api/product/ProductApi";
import Product from "../../types/Product";
import WindowCard from "../../component/card/WindowCard.vue";
import AddProductForm from "./addProductFrom/AddProductForm.vue";

/**
 * Таблица продуктов
 * Created by CommonName123 on 22.08.2022
 */
@Component({components: {WindowCard, AddProductForm}})
export default class ProductTable extends Vue {
    public name: string = 'product-table';

    /**
     * Список пользователей чата Twitch
     * @private
     */
    private products: Product[] = [];

    /**
     * Признак открытого окна
     * @private
     */
    private dialogVisible: boolean = false;

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
        productApi.createProduct(formData).then(data => {
            this.loadTable();
            this.dialogVisible = false;
        });
    }
}