import {Component, Prop} from "vue-property-decorator";
import Vue from "vue";
import {Action} from "vuex-class";
import {CHANGE_USER, LOG_OUT_USER} from "../stores/store";

/**
 * Меню навигации
 * Created by CommonName123 on 22.08.2022
 */
@Component
export default class MyNavigation extends Vue {
    public name: string = 'my-navigation';

    /**
     * Выбранный раздел
     * @private
     */
    @Prop({default: 'products'})
    private link!: string;


    /**
     * Сохранить пользователя в хранилище vuex.
     * @param {User} newUser - измененный объект данных о пользователе
     */
    @Action(LOG_OUT_USER)
    private logOutUser!: () => void;


    /**
     * Кнопки меню
     * @private
     */
    private tasks: any[] = [
        {
            index: "1",
            name: "Продукты",
            route: "products",
        }, 
        {
            index: "2",
            name: "Категории",
            route: "categories",
        }
    ];

    /**
     * Разлогиниться
     * @private
     */
    private doLogout(){
        this.logOutUser();
    }
}