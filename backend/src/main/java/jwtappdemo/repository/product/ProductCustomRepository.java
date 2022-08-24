package jwtappdemo.repository.product;

import jwtappdemo.domain.product.filter.ProductFilter;
import jwtappdemo.domain.product.Product;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * Кастомный репозиторий
 * Created by CommonName123 on 20.08.2022
 */
@Repository
public interface ProductCustomRepository {
    public List<Product> getListByFilters(ProductFilter filters);
}
