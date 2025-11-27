package sinara_project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import sinara_project.aspect.RequestLimit;
import sinara_project.metrics.UserOrderMetrics;
import sinara_project.models.order.UserOrder;
import sinara_project.models.order.UserOrderDto;
import sinara_project.service.order.OrderService;
import sinara_project.service.order.OrderServiceProducer;


@RestController()
public class OrderController {

    @Autowired
    private OrderServiceProducer orderServiceProducer;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserOrderMetrics metrics;

    @RequestLimit
    @PostMapping("/order/create")
    public UserOrderDto createOrder(@RequestBody UserOrderDto order) {
        var result =  orderServiceProducer.createOrder(order);
        metrics.increment(order);
        return result;
    }

    @GetMapping("order")
    @Cacheable(value = "userOrder", key = "#userId")
    public Page<UserOrderDto> getUserOrders(@RequestBody Long userId, @RequestParam("offset") Integer offset,
                              @RequestParam("limit") Integer limit) {
        return orderService.getUserOrders(userId, PageRequest.of(offset, limit));
    }
}