import {AxiosInstance} from 'axios';
import {axiosFactory} from '../AxiosFactory';
import {dataToArrayClass, dataToClass} from "../ClassUtil";
import Category from "../../types/Category";

/**
 * api для категорий
 * Created by CommonName123 22.08.2022
 */
class CategoryApi {
    constructor(
        readonly axiosInstance: AxiosInstance = axiosFactory.axiosInstance
    ) {
    }

    /**
     * Список категорий
     */
    public getCategories(): Promise<Category[]> {
        return this.axiosInstance
            .get('category/getList')
            .then((response) => response.data)
            .then((data) => dataToArrayClass(Category, data) || {});
    }

    /**
     * Создать категорию
     */
    public createCategory(category: Category): Promise<Category> {
        return this.axiosInstance
            .post('category/create', category)
            .then((response) => response.data)
            .then((data) => dataToClass(Category, data) || {});
    }

    /**
     * Изменить категорию
     */
    public updateCategory(category: Category): Promise<Category> {
        return this.axiosInstance
            .put('category/update', category)
            .then((response) => response.data)
            .then((data) => dataToClass(Category, data) || {});
    }

    /**
     * Удалить категорию
     */
    public deleteCategory(categoryId: String): Promise<void> {
        return this.axiosInstance
            .delete(`category/${categoryId}`)
            .then((response) => response.data);
    }
}

const categoryApi = new CategoryApi();

export default categoryApi;
