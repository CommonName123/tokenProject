package jwtappdemo.service.product;

import jwtappdemo.domain.category.Category;
import jwtappdemo.domain.product.Product;
import jwtappdemo.domain.product.filter.ProductFilter;
import jwtappdemo.repository.product.ProductRepository;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * Реализация сервиса по работе с продуктами
 * Created by CommonName123 on 18.08.2022
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public List<Product> getList(ProductFilter filters) {
        if (Objects.isNull(filters.getFilterList())){
            return productRepository.findAll();
        }
        // логично было бы выводить только те продукты, у которых есть категория и статус активен,
        // но в задании об этом ни слова
        return productRepository.getListByFilters(filters);
    }

    @Override
    @Transactional
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    @Override
    @Transactional
    public Product createEmptyProduct(UUID categoryId) {
        Product product = new Product();
        product.setCategory(new Category(categoryId));
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public void deleteProductById(UUID id) {
        productRepository.deleteById(id);
    }
    @Override
    @Transactional
    public void logicalDeleteByCategoryId(UUID id) {
        productRepository.deleteById(id);
    }
}
