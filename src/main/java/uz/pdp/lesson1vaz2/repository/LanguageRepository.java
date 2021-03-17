package uz.pdp.lesson1vaz2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson1vaz2.entity.Language;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Integer id);
}
