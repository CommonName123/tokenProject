package jwtappdemo.rest.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jwtappdemo.domain.product.filter.ProductFilter;
import jwtappdemo.domain.product.Product;
import jwtappdemo.rest.common.ResponseEntityBuilder;
import jwtappdemo.service.product.ProductService;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * Контроллер для работы с продуктами
 */
@Tag(name = "Product", description = "The Product API")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ResponseEntityBuilder responseEntityBuilder;

    @Autowired
    private ProductService productService;

    /**
     * Поулчить список продуктов с фильтрами
     *
     * @return
     * @throws IOException
     */
    @Operation(summary = "Gets all products",
            tags = "product",
            method = "POST",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content =
            @Content(encoding = @Encoding(name = "filters", contentType = "application/json"),
                    schema = @Schema(implementation = ProductFilter.class),
                    examples = {
                            @ExampleObject(
                                    name = "Пример тела на получение списка продуктов",

                                    value = "{\"filterList\" :[{" +
                                            "\"field\":\"price\"," +
                                            "\"isASC\":true" +
                                            "}," +
                                            "{" +
                                            "\"field\":\"name\"," +
                                            "\"isASC\":false" +
                                            "}]}",
                                    summary = "Образец №1"),
                            @ExampleObject(
                                    name = "Пример пустого тела на получение списка продуктов",
                                    value = "{}",
                                    summary = "Образец №2"),
                    })))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Select all products with filters",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Product.class)))
                    })
    })
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    public ResponseEntity<?> getList(@RequestBody(required = false) ProductFilter filters) throws IOException {
        return responseEntityBuilder.buildOk(productService.getList(filters));
    }


    /**
     * Создать продукт
     *
     * @return
     * @throws IOException
     */
    @Operation(summary = "Create product",
            tags = "product",
            method = "POST",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content =
            @Content(encoding = @Encoding(name = "product", contentType = "application/json"),
                    schema = @Schema(implementation = Product.class),
                    examples = {
                            @ExampleObject(
                                    name = "Пример тела на обновление",
                                    value = "{" +
                                            "\"id\":\"3fa85f64-5717-4562-b3fc-2c963f66afa0\"," +
                                            "\"name\":\"Морковь\"," +
                                            "\"description\":\"Морковь обычная\"," +
                                            "\"price\":\"35.95\"," +
                                            "\"photo\": null," +
                                            "\"category_id\":\"3fa85f64-5717-4562-b3fc-2c963f66afa6\"," +
                                            "\"date\":\"21-08-2022 1:30\"," +
                                            "\"status\": true" +
                                            "}",
                                    summary = "Образец №1"),
                            @ExampleObject(
                                    name = "Пример тела на обновление",
                                    value = "{" +
                                            "\"id\":\"3fa85f63-5717-4562-b3fc-2c963f66afa1\"," +
                                            "\"name\":\"Хлеб б.\"," +
                                            "\"description\":\"Хлеб бородинский\"," +
                                            "\"price\":\"33.95\"," +
                                            "\"photo\": null," +
                                            "\"category_id\":\"3fa85f64-5717-4562-b3fc-2c963f66afa1\"," +
                                            "\"date\":\"21-08-2022 1:30\"," +
                                            "\"status\": true" +
                                            "}",
                                    summary = "Образец №2"),
                    })))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Create product",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Product.class))
                    })
    })
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createProduct(@RequestBody Product product) throws IOException {
        return responseEntityBuilder.buildOk(productService.createProduct(product));
    }

    @Operation(summary = "Save product image",
            tags = "product",
            method = "POST",
            description="Запрос из интерфейса посылается через XMLHttpRequest, " +
                    "в котором лежит FormData с массивом " +
                    "в котором записан файл(картинка)." +
                    "Массив лежит под названием \"image\""
           )
    @RequestMapping(value = "/saveImage/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> saveImage(MultipartHttpServletRequest request, HttpServletResponse response,
                                       @PathVariable UUID id) throws IOException {
        MultipartFile file = request.getFileMap().get("image");
        if (Objects.nonNull(file) && !file.isEmpty()) {
            try (InputStream inputStream = file.getInputStream()) {
                productService.saveImage(inputStream,id);
                return responseEntityBuilder.buildOk();
            } catch (Exception e) {
                return responseEntityBuilder.buildError(e.getMessage(), e.getMessage());
            }
        }
        return responseEntityBuilder.buildOk();
    }


    /**
     * Изменить продукт
     *
     * @return
     * @throws IOException
     */
    @Operation(summary = "Update product",
            method = "PUT",
            tags = "product",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content =
            @Content(encoding = @Encoding(name = "product", contentType = "application/json"),
                    schema = @Schema(implementation = Product.class),
                    examples = {
                            @ExampleObject(
                                    name = "Пример тела на обновление",
                                    value = "{" +
                                            "\"id\":\"3fa85f64-5717-4562-b3fc-2c963f66afa0\"," +
                                            "\"name\":\"Морковь\"," +
                                            "\"description\":\"Морковь обычная\"," +
                                            "\"price\":\"35.95\"," +
                                            "\"photo\": null," +
                                            "\"category_id\":\"3fa85f64-5717-4562-b3fc-2c963f66afa6\"," +
                                            "\"date\":\"21-08-2022 1:30\"," +
                                            "\"status\": true" +
                                            "}",
                                    summary = "Образец №1"),
                            @ExampleObject(
                                    name = "Пример тела на обновление",
                                    value = "{" +
                                            "\"id\":\"3fa85f63-5717-4562-b3fc-2c963f66afa1\"," +
                                            "\"name\":\"Хлеб б.\"," +
                                            "\"description\":\"Хлеб бородинский\"," +
                                            "\"price\":\"33.95\"," +
                                            "\"photo\": null," +
                                            "\"category_id\":\"3fa85f64-5717-4562-b3fc-2c963f66afa1\"," +
                                            "\"date\":\"21-08-2022 1:30\"," +
                                            "\"status\": true" +
                                            "}",
                                    summary = "Образец №2"),
                    })))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Update product",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Product.class))
                    })
    })
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProduct(@RequestBody Product product) throws IOException {
        return responseEntityBuilder.buildOk(productService.updateProduct(product));
    }


    /**
     * Удалить продукт
     *
     * @return
     * @throws IOException
     */
    @Operation(summary = "Delete product",
            method = "DELETE",
            tags = "product",
            parameters = @Parameter(name = "id", description = "Идентификатор продукта", required = true)
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Delete product")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct(@PathVariable UUID id) throws IOException {
        productService.deleteProductById(id);
        return responseEntityBuilder.buildOk();
    }
}
