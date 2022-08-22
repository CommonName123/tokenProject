import { Component } from 'vue-property-decorator';
import NewMenu from './menu/NewMenu.vue';
import Vue from "vue";
import User from "./types/User";
import {Action, State} from "vuex-class";
import LoginScreen from "./chapters/login/LoginScreen.vue";
import {CHANGE_USER} from "./stores/store";

/**
 * Приложение
 * Created by CommonName123 on 22.08.2022
 */
@Component({ components: { NewMenu,LoginScreen } })
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
     * Сессия завершена, либо пользователь не авторизован.
     * @returns {boolean} - true - аутентификация прошла успешна и пользователь присутствует, false - нет.
     */
    private get isUserUnauthorised() {
        return this.user === null;
    }
}