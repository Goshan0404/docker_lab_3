package sinara_project.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import sinara_project.metrics.UserOrderMetrics;
import sinara_project.models.order.UserOrderDto;
import sinara_project.service.order.OrderService;
import sinara_project.service.order.OrderServiceProducer;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderServiceProducer orderServiceProducer;

    @MockBean
    private OrderService orderService;

    @MockBean
    private UserOrderMetrics metrics;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createOrder() throws Exception {
        UserOrderDto ordder = new UserOrderDto();
        ordder.setUserId(1);

        Mockito.when(orderServiceProducer.createOrder(any())).thenReturn(ordder);
        mockMvc.perform(MockMvcRequestBuilders.get("/order/create")).andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(ordder)));
    }

    @Test
    void getUserOrders() throws Exception {
        Page<UserOrderDto> order = new PageImpl<>(List.of(new UserOrderDto()));

        Mockito.when(orderService.getUserOrders(any(), any())).thenReturn(order);
        mockMvc.perform(MockMvcRequestBuilders.get("/order")).andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(order)));
    }
}