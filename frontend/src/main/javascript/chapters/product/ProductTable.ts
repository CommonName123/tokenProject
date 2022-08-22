import {Component} from "vue-property-decorator";
import Vue from "vue";
import productApi from "../../api/product/ProductApi";
import Product from "../../types/Product";
import {State} from "vuex-class";
import User from "../../types/User";

/**
 * Таблица продуктов
 * Created by CommonName123 on 22.08.2022
 */
@Component
export default class ProductTable extends Vue {
    public name: string = 'product-table';

    /**
     * Список пользователей чата Twitch
     * @private
     */
    private products: Product[] = [];


    /**
     * Пользователь
     * @private
     */
    @State(state => state.user)
    private user!: User | null;

    /**
     * Инициализация
     * @private
     */
    private mounted() {
        productApi.getProducts(this.user?.token).then((data:Product[]) => {
            this.products = data;
        });
    }

}