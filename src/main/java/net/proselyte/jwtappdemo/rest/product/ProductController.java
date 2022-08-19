package net.proselyte.jwtappdemo.rest.product;

import net.proselyte.jwtappdemo.domain.product.Product;
import net.proselyte.jwtappdemo.rest.common.ResponseEntityBuilder;
import net.proselyte.jwtappdemo.service.product.ProductService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для работы с продуктами
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ResponseEntityBuilder responseEntityBuilder;

    @Autowired
    private ProductService productService;

    /**
     * Поулчить список продуктов с фильтрами
     * todo добавить фильтры
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    public ResponseEntity<?> getList(@RequestBody ProductFilter filters) throws IOException {
        return responseEntityBuilder.buildOk(productService.getList(filters));
    }


    /**
     * Создать продукт
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createProduct(@RequestParam(value = "categoryId",required = true) UUID categoryId) throws IOException {
        return responseEntityBuilder.buildOk(productService.createProduct(categoryId));
    }



    /**
     * Изменить продукт
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProduct(@RequestBody Product product) throws IOException {
        return responseEntityBuilder.buildOk(productService.updateProduct(product));
    }



    /**
     * Удалить продукт
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct(@PathVariable UUID id) throws IOException {
        productService.deleteProductById(id);
        return responseEntityBuilder.buildOk();
    }
}
