package com.example.aspect;

import com.example.annotation.TrackAsyncTime;
import com.example.service.RequestLogService;
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
    private final RequestLogService requestLogService;

    public TrackAsyncTimeAspect(@Autowired RequestLogService requestLogService) {
        this.requestLogService = requestLogService;
    }

    @Pointcut("@annotation(requestMethod)")
    public void pointcut(TrackAsyncTime requestMethod) {
    }

    @Around(value = "pointcut(requestMethod)", argNames = "joinPoint, requestMethod")
    public Object asyncRecord(ProceedingJoinPoint joinPoint, TrackAsyncTime requestMethod) {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start(joinPoint.getSignature().toShortString());
        Object object = null;
        try {
            object = joinPoint.proceed();
        } catch (Throwable e) {
            log.error("Ошибка TrackAsyncTimeAspect", e);
        }
        stopWatch.stop();
        CompletableFuture.runAsync(() -> {
            this.requestLogService.save(stopWatch, requestMethod);
            log.info(stopWatch.prettyPrint());
        });
        return object;
    }
}
