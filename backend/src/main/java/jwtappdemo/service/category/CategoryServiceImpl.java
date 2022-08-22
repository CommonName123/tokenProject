package jwtappdemo.service.category;

import jwtappdemo.domain.category.Category;
import jwtappdemo.repository.category.CategoryRepository;

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

    @Override
    @Transactional
    public List<Category> getList() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional
    public Category createCategory() {
        return categoryRepository.save(new Category());
    }

    @Override
    @Transactional
    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void deleteCategoryById(UUID id) {
        // проверка на то, что статус у всех продуктов
        // тут немного не понял задание, удалить ссылки на категорию и проставить статус инактив у продуктов
        // или просто каскадом удалить и продукты
        categoryRepository.deleteById(id);
    }
}
