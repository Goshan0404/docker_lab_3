package sinara_project.aspect;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RequestCounter {
    private final Map<String, Integer> counter = new HashMap<>();

    public void increment(String methodName) {
        counter.put(methodName, counter.getOrDefault(methodName, 0) + 1);
    }

    public int getCount(String methodName) {
        return counter.getOrDefault(methodName, 0);
    }
}