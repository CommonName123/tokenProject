package jwtappdemo.repository.product;

import jwtappdemo.domain.product.filter.ProductFilter;
import jwtappdemo.domain.product.Product;
import jwtappdemo.repository.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Реализация кастомного репозитория
 * Created by CommonName123 on 20.08.2022
 */
public class ProductCustomRepositoryImpl implements ProductCustomRepository {


    @Autowired
    private EntityManager entityManager;


    @Override
    @Transactional(readOnly = true)
    public List<Product> getListByFilters(ProductFilter filters){
        String sqlFilters = filters.getFilterList()
                .stream()
                .map(x -> x.getField() + " " + (x.getAsc() ? "ASC" : "DESC"))
                .collect(Collectors.joining(",", "", ""));
        Query query = entityManager.createNativeQuery(
                "select * from product order by " +
                        sqlFilters);
        List<Product> result = HibernateUtil.aliasToBean(query, Product.class).getResultList();
        return result;
    }
}
