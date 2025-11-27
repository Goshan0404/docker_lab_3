package sinara_project.models.ingredient;

import jakarta.persistence.*;
import lombok.Data;
import sinara_project.models.pizza.Pizza;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(

        indexes = {
                @Index(name = "idx_name", columnList = "name")
        }
)
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "ingredients")
    private Set<Pizza> pizzas = new HashSet<>();
}
