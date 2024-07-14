package com.example.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Component
public class TrackTimeAspect {
    private StopWatch stopWatch;

    @Pointcut("@annotation(com.example.annotation.TrackTime)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void beforeRecord(JoinPoint joinPoint) {
        this.stopWatch = new StopWatch();
        this.stopWatch.start(joinPoint.getSignature().toLongString());
    }

    @After("pointcut()")
    public void afterRecord() {
        this.stopWatch.stop();
        log.info(this.stopWatch.prettyPrint());
    }
}
