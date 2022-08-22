import { AxiosInstance } from 'axios';
import { axiosFactory } from '../AxiosFactory';

/**
 * api для продуктов
 * Created by CommonName123 22.08.2022
 */
class LoginApi {
    constructor(
        readonly axiosInstance: AxiosInstance = axiosFactory.axiosInstance
    ) {}

    /**
     * Получить данные о людях, которые были на трансляциях
     */
    public doLogin(userInfo:object): Promise<any> {
        return this.axiosInstance
            .post('login',userInfo)
            .then((response) => response.data)
            .then((data) => data || {});
    }

}

const loginApi = new LoginApi();

export default loginApi;
