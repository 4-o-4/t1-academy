package com.example.aspect;

import com.example.annotation.TrackAsyncTime;
import com.example.service.TimeService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Aspect
@Component
public class TrackAsyncTimeAspect {
    private final TimeService timeService;
    private StopWatch stopWatch;

    public TrackAsyncTimeAspect(@Autowired TimeService timeService) {
        this.timeService = timeService;
    }

    @Pointcut("@annotation(requestMethod)")
    public void pointcut(TrackAsyncTime requestMethod) {
    }

    @Around(value = "pointcut(requestMethod)", argNames = "joinPoint, requestMethod")
    public Object asyncRecord(ProceedingJoinPoint joinPoint, TrackAsyncTime requestMethod) {
        this.stopWatch = new StopWatch();
        this.stopWatch.start(joinPoint.getSignature().toShortString());
        Object object = null;
        try {
            object = joinPoint.proceed();
        } catch (Throwable e) {
            log.error("Ошибка TrackAsyncTimeAspect", e);
        }
        this.stopWatch.stop();
        CompletableFuture.runAsync(() -> {
            this.timeService.save(stopWatch, requestMethod);
            log.info(this.stopWatch.prettyPrint());
        });
        return object;
    }
}
