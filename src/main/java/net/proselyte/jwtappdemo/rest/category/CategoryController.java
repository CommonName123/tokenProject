package net.proselyte.jwtappdemo.rest.category;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.proselyte.jwtappdemo.domain.category.Category;
import net.proselyte.jwtappdemo.rest.common.ResponseEntityBuilder;
import net.proselyte.jwtappdemo.service.category.CategoryService;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для работы с категориями
 */
@Tag(name = "Category", description = "The Category API")
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ResponseEntityBuilder responseEntityBuilder;

    @Autowired
    private CategoryService categoryService;

    /**
     * Поулчить список категорий
     *
     * @return
     * @throws IOException
     */
    @Operation(summary = "Gets all categories", tags = "category",
            method = "GET")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Select all categories",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Category.class)))
                    })
    })
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public ResponseEntity<?> getList() throws IOException {
        return responseEntityBuilder.buildOk(categoryService.getList());
    }


    /**
     * Создать категорию
     *
     * @return
     * @throws IOException
     */
    @Operation(summary = "Create category", tags = "category",
            method = "POST")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Create category",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Category.class))
                    })
    })
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createCategory() throws IOException {
        return responseEntityBuilder.buildOk(categoryService.createCategory());
    }


    /**
     * Изменить категорию
     *
     * @return
     * @throws IOException
     */
    @Operation(summary = "Update category",
            method = "PUT",
            tags = "category",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content =
            @Content(encoding = @Encoding(name = "category", contentType = "application/json"),
                    schema = @Schema(implementation = Category.class),
                    examples = {
                            @ExampleObject(
                                    name = "Пример тела на обновление",
                                    value = "{" +
                                            "\"id\":\"3fa85f64-5717-4562-b3fc-2c963f66afa6\"," +
                                            "\"name\":\"Овощи\"," +
                                            "\"description\":\"Продукты питания\"" +
                                            "}",
                                    summary = "Образец №1"),
                            @ExampleObject(
                                    name = "Пример тела на обновление",
                                    value = "{" +
                                            "\"id\":\"3fa85f64-5717-4562-b3fc-2c963f66afa1\"," +
                                            "\"name\":\"Выпечка\"," +
                                            "\"description\":\"Булочки, хлеб и лепёшки\"" +
                                            "}",
                                    summary = "Образец №2"),
                    })))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Update category",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Category.class))
                    })
    })
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCategory(@RequestBody Category category) throws IOException {
        return responseEntityBuilder.buildOk(categoryService.updateCategory(category));
    }


    /**
     * Удалить категорию
     *
     * @return
     * @throws IOException
     */
    @Operation(summary = "Delete category",
            method = "DELETE",
            tags = "category",
            parameters = @Parameter(name = "id", description = "Идентификатор категории", required = true)
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Delete category")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCategory(@PathVariable UUID id) throws IOException {
        categoryService.deleteCategoryById(id);
        return responseEntityBuilder.buildOk();
    }


}
