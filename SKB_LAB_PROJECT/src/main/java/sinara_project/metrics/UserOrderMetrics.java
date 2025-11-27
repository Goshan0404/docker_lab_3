package sinara_project.metrics;

import io.micrometer.core.instrument.*;
import org.springframework.stereotype.Service;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Timer;
import sinara_project.models.order.UserOrderDto;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserOrderMetrics {

    private final Counter requestCounter;
    private final Timer requestTimer;
    private final Map<String, Counter> pizzaCounters = new ConcurrentHashMap<>();


    public UserOrderMetrics(MeterRegistry registry) {
        this.requestCounter = registry.counter("orders.request.count");
        this.requestTimer = registry.timer("orders.request.timer");
    }

    public void increment(UserOrderDto order) {
        requestCounter.increment();

        requestTimer.record(() -> {
            order.getPizzas().forEach(pizza -> {
                pizzaCounters
                        .computeIfAbsent(pizza.getName(), name ->
                                Counter.builder("orders.pizza.count")
                                        .tag("pizza", name)
                                        .description("Количество заказанных пицц по названию")
                                        .register(Metrics.globalRegistry))
                        .increment();
            });

        });
    }
}
