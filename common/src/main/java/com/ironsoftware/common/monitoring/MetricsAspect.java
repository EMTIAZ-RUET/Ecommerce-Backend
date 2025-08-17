package com.ironsoftware.common.monitoring;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class MetricsAspect {
    private final MeterRegistry registry;

    @Around("@annotation(com.ironsoftware.common.monitoring.MonitorMetric)")
    public Object monitorMetrics(ProceedingJoinPoint joinPoint) throws Throwable {
        Timer.Sample sample = Timer.start(registry);
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        
        try {
            Object result = joinPoint.proceed();
            sample.stop(Timer.builder("method.execution")
                    .tag("class", className)
                    .tag("method", methodName)
                    .tag("result", "success")
                    .register(registry));
            return result;
        } catch (Exception e) {
            sample.stop(Timer.builder("method.execution")
                    .tag("class", className)
                    .tag("method", methodName)
                    .tag("result", "error")
                    .tag("error", e.getClass().getSimpleName())
                    .register(registry));
            throw e;
        }
    }
}
