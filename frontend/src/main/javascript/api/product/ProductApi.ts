import { AxiosInstance } from 'axios';
import { axiosFactory } from '../AxiosFactory';
import Product from "../../types/Product";
import {dataToArrayClass, dataToClass} from "../ClassUtil";

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
    public getProducts(): Promise<Product[]> {
        return this.axiosInstance
            .post('product/getList',{})
            .then((response) => response.data)
            .then((data) => dataToArrayClass(Product,data) || {});
    }

    /**
     * Создать продукт
     */
    public createProduct(product:Product): Promise<Product> {
        return this.axiosInstance
            .post('product/create',product)
            .then((response) => response.data)
            .then((data) => dataToClass(Product,data) || {});
    }

    /**
     * Сохранить изменения в продукте
     */
    public updateProduct(product:Product): Promise<Product> {
        return this.axiosInstance
            .put('product/update',product)
            .then((response) => response.data)
            .then((data) => dataToClass(Product,data) || {});
    }


    /**
     * Удалить продукт
     */
    public deleteProduct(productId: String): Promise<void> {
        return this.axiosInstance
            .delete(`product/${productId}`)
            .then((response) => response.data);
    }

}

const productApi = new ProductApi();

export default productApi;
