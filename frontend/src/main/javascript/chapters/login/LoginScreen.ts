import {Component} from "vue-property-decorator";
import Vue from "vue";
import LoginApi from "../../api/login/LoginApi";
import {Action} from "vuex-class";
import User from "../../types/User";
import {CHANGE_USER} from "../../stores/store";

/**
 * Логин форма
 * Created by CommonName123 on 22.08.2022
 * todo прикрутить логин на enter
 */
@Component
export default class LoginScreen extends Vue {
    public name: string = 'login-screen';

    /**
     * Логин
     * @private
     */
    private login: string = "";
    /**
     * Пароль
     * @private
     */
    private password: string = "";

    /**
     * Сохранить пользователя в хранилище vuex.
     * @param {User} newUser - измененный объект данных о пользователе
     */
    @Action(CHANGE_USER)
    private updateUserInStore!: (newUser: User) => void;

    /**
     * Инициализация
     * @private
     */
    private doLogin() {
        if (this.login !== "" && this.password !== "") {
            // try to login
            const me = this;
            LoginApi.doLogin({username:this.login,password:this.password}).then(data=>{
                me.updateUserInStore(data);
            },
                error => {
                    if (error.response) {
                        alert(error.response.data.detail);
                    } else {
                        alert('Отсутствует соединение с сервером');
                    }
                });
        }

    }
}