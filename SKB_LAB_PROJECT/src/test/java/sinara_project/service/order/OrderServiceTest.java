package sinara_project.service.order;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import sinara_project.models.order.UserOrder;
import sinara_project.models.order.UserOrderDto;
import sinara_project.repositories.OrderRepository;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class OrderServiceTest {

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private OrderService orderService;

    @Test
    public void testGetUserOrders() {
        PageRequest pageRequest = PageRequest.of(0, 1);
        Long userId = 1L;

        UserOrderDto userOrderDto = new UserOrderDto();
        UserOrder userOrder = new UserOrder();
        userOrder.setId(1);
        userOrderDto.setUserId(1);
        Page<UserOrder> userOrderPage = new PageImpl<>(List.of(userOrder));

        Mockito.when(orderRepository.findAllById(userId, pageRequest)).thenReturn(userOrderPage);
        Mockito.when(modelMapper.map(userOrder, UserOrderDto.class)).thenReturn(userOrderDto);

        Page<UserOrderDto> result = orderService.getUserOrders(userId, pageRequest);

        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0)).isEqualTo(userOrderDto);
    }
}