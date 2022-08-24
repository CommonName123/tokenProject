package jwtappdemo.repository.category;

import jwtappdemo.domain.category.Category;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с категориями
 * Created by CommonName123 on 20.08.2022
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

}
