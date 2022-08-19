package net.proselyte.jwtappdemo.rest.product;

import lombok.Data;

import java.util.List;

/**
 * Контейнер для фильтров
 */
@Data
public class ProductFilter {

    /**
     * Список критериев
     */
    private List<Filter> filterList;
}
