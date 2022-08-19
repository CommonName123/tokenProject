package net.proselyte.jwtappdemo.service.product;

import net.proselyte.jwtappdemo.domain.category.Category;
import net.proselyte.jwtappdemo.domain.product.Product;
import net.proselyte.jwtappdemo.repository.product.ProductRepository;
import net.proselyte.jwtappdemo.rest.product.ProductFilter;

import java.util.List;
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
        // логично было бы выводить только те продукты, у которых есть категория и статус активен,
        // но в задании об этом ни слова
        return productRepository.getListByFilters(filters);
    }

    @Override
    @Transactional
    public Product createProduct(UUID categoryId) {
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
