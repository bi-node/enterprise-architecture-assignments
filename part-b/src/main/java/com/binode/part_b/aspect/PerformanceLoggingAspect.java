package com.binode.part_b.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class PerformanceLoggingAspect {

    @Around("execution(* com.binode.part_b..*DAO.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start(proceedingJoinPoint.getSignature().getName());

        Object retVal;
        try {
            retVal = proceedingJoinPoint.proceed();
        } finally {
            stopWatch.stop();
            long totalTime = stopWatch.getLastTaskInfo().getTimeMillis();
            System.out.println("Execution time of " + proceedingJoinPoint.getSignature() + ": " + totalTime + " ms");
        }

        return retVal;
    }
}
