package jwtappdemo.service.product;

import jwtappdemo.domain.product.Product;
import jwtappdemo.domain.product.filter.ProductFilter;

import java.util.List;
import java.util.UUID;

/**
 * Сервис по работе с продуктами
 * Created by CommonName123 on 18.08.2022
 */
public interface ProductService {
    /**
     * Поулчить список продуктов с фильтрами
     * todo фильтры
     * @return
     */
    List<Product> getList(ProductFilter filters);

    /**
     * Создать пустой продукт
     * @return
     */
    Product createEmptyProduct(UUID categoryId);

    /**
     * Создать продукт
     * @return
     */
    Product createProduct(Product product);

    /**
     * Изменить продукт
     * @param product
     * @return
     */
    Product updateProduct(Product product);

    /**
     * Удалить продукт
     * @param id
     */
    void deleteProductById(UUID id);
}
