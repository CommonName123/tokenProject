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

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public List<Product> getList(ProductFilter filters) {
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
}
