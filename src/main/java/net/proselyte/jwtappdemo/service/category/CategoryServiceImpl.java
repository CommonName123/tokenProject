package net.proselyte.jwtappdemo.service.category;

import net.proselyte.jwtappdemo.domain.category.Category;
import net.proselyte.jwtappdemo.repository.category.CategoryRepository;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        categoryRepository.deleteById(id);
    }
}
