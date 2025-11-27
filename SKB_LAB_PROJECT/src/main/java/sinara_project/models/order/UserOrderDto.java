package sinara_project.models.order;

import lombok.Data;
import sinara_project.models.pizza.PizzaDto;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserOrderDto {
    private long userId;
    private Set<PizzaDto> pizzas = new HashSet<>();

}
