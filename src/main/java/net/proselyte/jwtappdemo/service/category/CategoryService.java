package net.proselyte.jwtappdemo.service.category;

import net.proselyte.jwtappdemo.domain.category.Category;

import java.util.List;
import java.util.UUID;

/**
 * Сервис по работе с категориями
 * Created by CommonName123 on 18.08.2022
 */
public interface CategoryService {
    /**
     * Поулчить список категорий
     * @return
     */
    List<Category> getList();

    /**
     * Создать категорию
     * @return
     */
    Category createCategory();

    /**
     * Изменить категорию
     * @param category
     * @return
     */
    Category updateCategory(Category category);

    /**
     * Удалить категорию
     * @param id
     */
    void deleteCategoryById(UUID id);

}
