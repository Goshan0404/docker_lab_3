package sinara_project.models.pizza;

import jakarta.persistence.*;
import lombok.Data;
import sinara_project.models.ingredient.Ingredient;
import sinara_project.models.order.UserOrder;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int size;

    @ManyToMany(mappedBy = "pizzas")
    private Set<UserOrder> userOrders = new HashSet<>();


    @ManyToMany
    @JoinTable(
            name = "pizza_ingredient",
            joinColumns = @JoinColumn(name = "pizza_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredients_id")
    )
    private Set<Ingredient> ingredients = new HashSet<>();
}
