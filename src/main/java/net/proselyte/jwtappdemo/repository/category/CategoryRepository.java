package net.proselyte.jwtappdemo.repository.category;

import net.proselyte.jwtappdemo.domain.category.Category;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

}
