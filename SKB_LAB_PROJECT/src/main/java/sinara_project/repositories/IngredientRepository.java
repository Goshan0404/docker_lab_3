package sinara_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sinara_project.models.ingredient.Ingredient;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Optional<Ingredient> findByName(String name);
}
