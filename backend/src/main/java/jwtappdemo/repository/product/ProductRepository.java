package jwtappdemo.repository.product;

import jwtappdemo.domain.product.Product;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>, ProductCustomRepository {

    /**
     * Пример сортировки продуктов по цене через репозиторий
     *
     * @return
     */
    List<Product> findAllByOrderByPriceAsc();


    /**
     * Пример сортировки продуктов по цене в обратном порядке через запрос
     *
     * @return
     */
    @Query(value = "select * from public.product order by price DESC ", nativeQuery = true)
    List<Product> findAllByOrderByPriceDesc();


    /**
     * Логическое удаление продуктов
     *
     * @return
     */
    @Query(value = "update public.product set category_id = null,status=false where category_id = ?1", nativeQuery = true)
    List<Product> logicalDeleteByCategoryId(UUID categoryId);


}
