package sinara_project.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ApiCallLimitAspect {

    @Value("${api.max.calls}")
    private int maxApiCalls;

    @Autowired
    private RequestCounter requestCounter;

    @Pointcut("execution(* sinara_project.controllers.OrderController.*(..))")
    public void apiMethods() {
    }

    @Around("apiMethods()")
    public Object checkLimit(ProceedingJoinPoint joinPoint) throws Throwable {

        String methodName = joinPoint.getSignature().toShortString();
        int currentCount = requestCounter.getCount(methodName);
        requestCounter.increment(methodName);

        if (currentCount >= maxApiCalls) {
            log.info("Current limit: ", methodName);
            return null;
        }
        return joinPoint.proceed();
    }
}
