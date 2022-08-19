package net.proselyte.jwtappdemo.rest.category;

import net.proselyte.jwtappdemo.domain.category.Category;
import net.proselyte.jwtappdemo.rest.common.ResponseEntityBuilder;
import net.proselyte.jwtappdemo.service.category.CategoryService;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для работы с категориями
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ResponseEntityBuilder responseEntityBuilder;

    @Autowired
    private CategoryService categoryService;

    /**
     * Поулчить список категорий
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public ResponseEntity<?> getList() throws IOException {
        return responseEntityBuilder.buildOk(categoryService.getList());
    }


    /**
     * Создать категорию
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createCategory() throws IOException {
        return responseEntityBuilder.buildOk(categoryService.createCategory());
    }



    /**
     * Изменить категорию
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCategory(@RequestBody Category category) throws IOException {
        return responseEntityBuilder.buildOk(categoryService.updateCategory(category));
    }



    /**
     * Удалить категорию
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCategory(@PathVariable UUID id) throws IOException {
        categoryService.deleteCategoryById(id);
        return responseEntityBuilder.buildOk();
    }


}
