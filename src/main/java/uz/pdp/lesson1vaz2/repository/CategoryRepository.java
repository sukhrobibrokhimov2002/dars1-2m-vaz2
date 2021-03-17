package uz.pdp.lesson1vaz2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson1vaz2.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByName(String name);
}
