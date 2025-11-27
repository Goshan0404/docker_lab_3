package sinara_project.service.order;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import sinara_project.models.ingredient.Ingredient;
import sinara_project.models.ingredient.IngredientDto;
import sinara_project.models.order.UserOrder;
import sinara_project.models.order.UserOrderDto;
import sinara_project.models.pizza.Pizza;
import sinara_project.models.pizza.PizzaDto;
import sinara_project.models.user.UserApp;
import sinara_project.repositories.IngredientRepository;
import sinara_project.repositories.OrderRepository;
import sinara_project.repositories.PizzaRepository;
import sinara_project.repositories.UserRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderModelService {

    private final OrderRepository orderRepository;
    private final PizzaRepository pizzaRepository;
    private final IngredientRepository ingredientRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public OrderModelService(OrderRepository orderRepository, PizzaRepository pizzaRepository, IngredientRepository ingredientRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.pizzaRepository = pizzaRepository;
        this.ingredientRepository = ingredientRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @CacheEvict(value = "userOrder", key = "#dto.userId")
    public UserOrder mapAndSave(UserOrderDto dto) {
        UserApp user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> {
                    log.info("User is not found");
                    return new EntityNotFoundException("User is not found");});

        Set<Pizza> pizzas = dto.getPizzas().stream()
                .map(this::mapPizzaWithIngredients)
                .collect(Collectors.toSet());

        UserOrder order = new UserOrder();
        order.setUser(user);
        order.setPizzas(pizzas);

        return orderRepository.save(order);
    }

    private Pizza mapPizzaWithIngredients(PizzaDto pizzaDto) {
        Pizza pizza;

        if (pizzaDto.getName() == null || pizzaDto.getName().isBlank()) {
            pizza = pizzaRepository.findByName(pizzaDto.getName())
                    .orElse(null);
            if (pizza != null) {
                return pizza;
            }
        }

        pizza = modelMapper.map(pizzaDto, Pizza.class);

        Set<Ingredient> ingredients = pizzaDto.getIngredients().stream()
                .map(this::resolveIngredient)
                .collect(Collectors.toSet());

        pizza.setIngredients(ingredients);

        return pizzaRepository.save(pizza);
    }

    private Ingredient resolveIngredient(IngredientDto dto) {
        if (dto.getName() == null || dto.getName().isBlank()) {
            return ingredientRepository.findByName(dto.getName())
                    .orElseThrow(() -> {
                        log.info("Ingredient is nor found");
                        return new EntityNotFoundException("Ingredient is not found");});
        }

        return ingredientRepository.findAll().stream()
                .filter(i -> i.getName().equalsIgnoreCase(dto.getName()))
                .findFirst()
                .orElseGet(() -> {
                    Ingredient newIng = new Ingredient();
                    newIng.setName(dto.getName());
                    return ingredientRepository.save(newIng);
                });
    }
}
