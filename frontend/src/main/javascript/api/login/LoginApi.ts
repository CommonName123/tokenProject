import {AxiosInstance} from 'axios';
import {axiosFactory} from '../AxiosFactory';
import User from "../../types/User";
import {dataToClass} from "../ClassUtil";

/**
 * api для продуктов
 * Created by CommonName123 22.08.2022
 */
class LoginApi {
    constructor(
        readonly axiosInstance: AxiosInstance = axiosFactory.axiosInstance
    ) {
    }

    /**
     * Получить данные о людях, которые были на трансляциях
     */
    public doLogin(userInfo: object): Promise<User> {
        return this.axiosInstance
            .post('login', userInfo,{
                headers:{
                    Authorization: null
                }
            })
            .then((response) => response.data)
            .then((data) => dataToClass(User, data) || {});
    }

}

const loginApi = new LoginApi();

export default loginApi;
