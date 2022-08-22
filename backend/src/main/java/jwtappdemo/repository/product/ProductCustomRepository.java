package jwtappdemo.repository.product;

import jwtappdemo.domain.product.filter.ProductFilter;
import jwtappdemo.domain.product.Product;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * Кастомный репозиторий
 */
@Repository
public interface ProductCustomRepository {
    public List<Product> getListByFilters(ProductFilter filters);
}
