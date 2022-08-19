package net.proselyte.jwtappdemo.repository.product;

import net.proselyte.jwtappdemo.domain.product.Product;
import net.proselyte.jwtappdemo.repository.HibernateUtil;
import net.proselyte.jwtappdemo.rest.product.ProductFilter;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Реализация кастомного репозитория
 */
public class ProductCustomRepositoryImpl implements ProductCustomRepository {


    @Autowired
    private EntityManager entityManager;


    @Override
    @Transactional(readOnly = true)
    public List<Product> getListByFilters(ProductFilter filters){
        String sqlFilters = filters.getFilterList()
                .stream()
                .map(x -> x.getField() + " " + (x.isASC() ? "ASC" : "DESC"))
                .collect(Collectors.joining(",", "", ""));
        Query query = entityManager.createNativeQuery(
                "select * from product order by " +
                        sqlFilters );
        List<Product> result = HibernateUtil.aliasToBean(query, Product.class).getResultList();
        return result;
    }
}
