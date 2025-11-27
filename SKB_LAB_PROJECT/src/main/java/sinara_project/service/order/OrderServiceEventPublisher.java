//package sinara_project.service;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.stereotype.Service;
//import sinara_project.OrderCreatedEvent;
//import sinara_project.models.order.UserOrder;
//
//@Slf4j
//@Service
//public class OrderService {
//    private final ApplicationEventPublisher eventPublisher;
//
//    public OrderService(ApplicationEventPublisher eventPublisher) {
//        this.eventPublisher = eventPublisher;
//    }
//
//    public void createOrder(UserOrder order) {
//        log.info("Создание заказа");
//        log.info("Создание ивента для создания заказа");
//        eventPublisher.publishEvent(new OrderCreatedEvent(this, order));
//    }
//}
