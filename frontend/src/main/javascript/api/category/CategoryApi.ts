import { AxiosInstance } from 'axios';
import { axiosFactory } from '../AxiosFactory';
import {dataToArrayClass} from "../ClassUtil";
import Category from "../../types/Category";

/**
 * api для категорий
 * Created by CommonName123 22.08.2022
 */
class CategoryApi {
    constructor(
        readonly axiosInstance: AxiosInstance = axiosFactory.axiosInstance
    ) {}



    /**
     * Список категорий
     */
    public getCategories(token:any): Promise<Category[]> {
        return this.axiosInstance
            .get('category/getList',{
                headers:{
                    Authorization: `Bearer ${token}`
                }
            })
            .then((response) => response.data)
            .then((data) => dataToArrayClass(Category,data) || {});
    }

}

const categoryApi = new CategoryApi();

export default categoryApi;
