package jwtappdemo.repository;

import jwtappdemo.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface that extends {@link JpaRepository} for class {@link User}.
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);
}
