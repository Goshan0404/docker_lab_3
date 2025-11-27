package sinara_project.service.order;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
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

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class OrderModelServiceTest {

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private PizzaRepository pizzaRepository;

    @MockBean
    private IngredientRepository ingredientRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ModelMapper modelMapper;

    @InjectMocks
    private OrderModelService orderModelService;

    @Test
    void testMapAndSave() {
        long userId = 1L;
        String pizzaName = "Burger";
        String ingredientName = "plastic";

        IngredientDto ingredientDto = new IngredientDto();
        ingredientDto.setName(ingredientName);

        PizzaDto pizzaDto = new PizzaDto();
        pizzaDto.setName(pizzaName);
        pizzaDto.setIngredients(Set.of(ingredientDto));

        UserOrderDto orderDto = new UserOrderDto();
        orderDto.setUserId(userId);
        orderDto.setPizzas(Set.of(pizzaDto));

        UserApp user = new UserApp();
        user.setId(userId);

        Pizza pizza = new Pizza();
        pizza.setName(pizzaName);

        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientName);

        UserOrder savedOrder = new UserOrder();
        savedOrder.setUser(user);
        savedOrder.setPizzas(Set.of(pizza));

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(ingredientDto.getName()).thenReturn(ingredientName);
        when(modelMapper.map(pizzaDto, Pizza.class)).thenReturn(pizza);
        when(ingredientRepository.findAll()).thenReturn(Collections.emptyList());
        when(ingredientRepository.save(any())).thenReturn(ingredient);
        when(pizzaRepository.save(any())).thenReturn(pizza);
        when(orderRepository.save(any())).thenReturn(savedOrder);

        UserOrder result = orderModelService.mapAndSave(orderDto);

        assertThat(result).isNotNull();
        assertThat(result.getUser()).isEqualTo(user);
    }
}