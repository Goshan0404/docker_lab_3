package org.example.pushNotification;

import lombok.extern.slf4j.Slf4j;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Subscription;
import org.example.model.UserOrderDto;
import org.jose4j.lang.JoseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@Slf4j
public class NotifyController {
    private final PushService pushService;

    // Так как это пример, я не стал поднимать БД
    private final List<Subscription> subscriptions = new ArrayList<>();

    public NotifyController(PushService pushService) {
        this.pushService = pushService;
    }

    @PostMapping("/subscribe")
    public void subscribe(@RequestBody Subscription subscription) {
        subscriptions.add(subscription);
    }

    public void sendNotification(UserOrderDto orderDto) {

        log.info("sending push notifications");

        for (Subscription sub : subscriptions) {
            try {
                Notification notification = new Notification(sub, orderDto.toString());
                pushService.send(notification);
            } catch (GeneralSecurityException | IOException | ExecutionException | InterruptedException |
                     JoseException e) {
                log.info("Exception during sending notification: " + e);
                throw new RuntimeException(e);
            }
        }
    }
}
