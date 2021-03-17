package uz.pdp.lesson1vaz2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson1vaz2.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

}
