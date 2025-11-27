package org.example.handler;

import lombok.extern.slf4j.Slf4j;
import org.example.model.UserOrderDto;
import org.example.pushNotification.NotifyController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderHandler {

    @Autowired
    private NotifyController notifyController;

    @KafkaListener(topics = "${kafka.topic.order-created}", groupId = "order-service-group")
    void getOrder(UserOrderDto userOrderDto) {
        log.info("message received");
        log.info(userOrderDto.toString());
        notifyController.sendNotification(userOrderDto);
    }
}
