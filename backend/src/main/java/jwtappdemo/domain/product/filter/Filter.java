package jwtappdemo.domain.product.filter;

import lombok.Data;

/**
 * Фильтр
 */
@Data
public class Filter{

    /**
     * Критерий сортировки
     */
    private String field;
    /**
     * Прямой/обратный пордяок
     */
    private Boolean asc;
}