import { AxiosInstance } from 'axios';
import { axiosFactory } from '../AxiosFactory';
import Product from "../../types/Product";
import {dataToArrayClass} from "../ClassUtil";

/**
 * api для продуктов
 * Created by CommonName123 22.08.2022
 */
class ProductApi {
    constructor(
        readonly axiosInstance: AxiosInstance = axiosFactory.axiosInstance
    ) {}

    /**
     * Получить список продуктов
     */
    public getProducts(token:any): Promise<Product[]> {
        return this.axiosInstance
            .post('product/getList',{},{
                headers:{
                    Authorization: `Bearer ${token}`
                }
            })
            .then((response) => response.data)
            .then((data) => dataToArrayClass(Product,data) || {});
    }

}

const productApi = new ProductApi();

export default productApi;
