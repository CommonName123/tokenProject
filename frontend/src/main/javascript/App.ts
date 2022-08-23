import {Component, Watch} from 'vue-property-decorator';
import NewMenu from './menu/NewMenu.vue';
import Vue from "vue";
import User from "./types/User";
import {Action, State} from "vuex-class";
import LoginScreen from "./chapters/login/LoginScreen.vue";
import {CHANGE_USER} from "./stores/store";
import {axiosFactory} from "./api/AxiosFactory";

/**
 * Приложение
 * Created by CommonName123 on 22.08.2022
 */
@Component({components: {NewMenu, LoginScreen}})
export default class App extends Vue {
    public name: string = 'Тестовый проект';

    /**
     * Пользователь
     * @private
     */
    @State(state => state.user)
    private user!: User | null;

    /**
     * Сохранить пользователя в хранилище vuex.
     * @param {User} newUser - измененный объект данных о пользователе
     */
    @Action(CHANGE_USER)
    private updateUserInStore!: (newUser: User) => void;

    /**
     * Перехватчик
     * @private
     */
    private interceptor: any = null;

    /**
     * Сессия завершена, либо пользователь не авторизован.
     * @returns {boolean} - true - аутентификация прошла успешна и пользователь присутствует, false - нет.
     */
    private get isUserUnauthorised() {
        return this.user === null;
    }


    @Watch('user')
    private changeUser(user: User | null) {
        if (this.user) {
            this.addRequestInterceptors(this.user);
        } else {
            this.clearInterceptors();
        }
    }


    /**
     * Добавить перехватчик запросов для добавления токена
     * @param user
     * @private
     */
    private addRequestInterceptors(user: User) {
        this.interceptor = axiosFactory.axiosInstance.interceptors.request.use(request => {
            request.headers = {
                Authorization: `Bearer ${user.token}`
            };
            return request;
        });
    }

    /**
     * Удалить перехватчик
     * @private
     */
    private clearInterceptors() {
        if (this.interceptor !== null) {
            axiosFactory.axiosInstance.interceptors.request.eject(this.interceptor);
            this.interceptor = null;
        }

    }

}