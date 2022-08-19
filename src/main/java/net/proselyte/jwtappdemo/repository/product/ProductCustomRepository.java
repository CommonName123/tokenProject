package net.proselyte.jwtappdemo.repository.product;

import net.proselyte.jwtappdemo.domain.product.Product;
import net.proselyte.jwtappdemo.rest.product.ProductFilter;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * Кастомный репозиторий
 */
@Repository
public interface ProductCustomRepository {
    public List<Product> getListByFilters(ProductFilter filters);
}
