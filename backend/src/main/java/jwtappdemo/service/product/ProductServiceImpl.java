package jwtappdemo.service.product;

import jwtappdemo.domain.category.Category;
import jwtappdemo.domain.product.Product;
import jwtappdemo.domain.product.filter.ProductFilter;
import jwtappdemo.repository.product.ProductRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
    @Transactional(readOnly = true)
    public List<Product> getList(ProductFilter filters) {
        if (Objects.isNull(filters.getFilterList())) {
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
    public void saveImage(InputStream inputStream, UUID id) throws IOException {
        byte[] image = new byte[inputStream.available()];
        inputStream.read(image);

        // тут можно заменить на сохранение сразу в бд, но мне проще было сделать за 2 запроса
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            product.get().setPhoto(image);
            productRepository.save(product.get());
        }
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
