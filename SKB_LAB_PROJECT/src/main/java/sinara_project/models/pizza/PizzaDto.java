package sinara_project.models.pizza;

import lombok.Data;
import sinara_project.models.ingredient.IngredientDto;

import java.util.HashSet;
import java.util.Set;

@Data
public class PizzaDto {
    private String name;
    private int size;
    private Set<IngredientDto> ingredients = new HashSet<>();
}
