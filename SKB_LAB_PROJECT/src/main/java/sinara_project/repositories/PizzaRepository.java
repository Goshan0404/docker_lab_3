package sinara_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sinara_project.models.pizza.Pizza;

import java.util.Optional;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    Optional<Pizza> findByName(String name);
}
