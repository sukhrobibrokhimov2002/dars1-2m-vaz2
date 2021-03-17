package uz.pdp.lesson1vaz2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson1vaz2.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Integer id);
}
