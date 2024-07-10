package com.example.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Aspect
@Component
public class TrackAsyncTimeAspect {
    @Pointcut("@annotation(com.example.annotation.TrackAsyncTime)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object asyncRunner(ProceedingJoinPoint joinPoint) {
        return CompletableFuture.supplyAsync(() -> {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start(joinPoint.getSignature().toLongString());
            try {
                joinPoint.proceed();
            } catch (Throwable e) {
                log.error("Ошибка TrackAsyncTimeAspect", e);
            }
            return stopWatch;
        }).thenAccept(stopWatch -> {
            stopWatch.stop();
            log.info(stopWatch.prettyPrint());
        });
    }
}
