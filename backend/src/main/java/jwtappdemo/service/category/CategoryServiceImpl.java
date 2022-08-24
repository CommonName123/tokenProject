package jwtappdemo.service.category;

import jwtappdemo.domain.category.Category;
import jwtappdemo.repository.category.CategoryRepository;
import jwtappdemo.repository.product.ProductRepository;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Реализация сервиса по работе с категориями
 * Created by CommonName123 on 18.08.2022
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Category> getList() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void deleteCategoryById(UUID categoryId) {
        productRepository.logicalDeleteByCategoryId(categoryId);
        categoryRepository.deleteById(categoryId);
    }
}
