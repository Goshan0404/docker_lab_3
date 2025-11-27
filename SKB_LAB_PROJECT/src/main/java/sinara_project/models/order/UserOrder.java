package sinara_project.models.order;

import jakarta.persistence.*;
import lombok.Data;
import sinara_project.models.pizza.Pizza;
import sinara_project.models.user.UserApp;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class UserOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany
    @JoinTable(
            name = "order_pizza",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "pizza_id")
    )
    private Set<Pizza> pizzas = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserApp user;
}
