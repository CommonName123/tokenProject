import {Component} from "vue-property-decorator";
import Vue from "vue";
import Category from "../../types/Category";
import categoryApi from "../../api/category/CategoryApi";
import {State} from "vuex-class";
import User from "../../types/User";

/**
 * Таблица продуктов
 * Created by CommonName123 on 22.08.2022
 */
@Component
export default class CategoryTable extends Vue {
    public name: string = 'product-table';

    /**
     * Список пользователей чата Twitch
     * @private
     */
    private categories: Category[] = [];


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
        categoryApi.getCategories(this.user?.token).then((data:Category[]) => {
            this.categories = data;
        });
    }

}