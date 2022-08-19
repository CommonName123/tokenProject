package net.proselyte.jwtappdemo.rest.product;

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
    private boolean isASC;
}