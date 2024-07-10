package bank.aop.advice;

import bank.integration.logging.LoggerDemo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

import java.util.UUID;


@Aspect
@Configuration
public class StopWatchAdvice {
    final LoggerDemo loggerDemo;

    public StopWatchAdvice(LoggerDemo loggerDemo) {
        this.loggerDemo = loggerDemo;
    }
    @Around("execution(* bank.service.*.*(..))")
    public Object profile (ProceedingJoinPoint call) throws Throwable{
        StopWatch clock = new StopWatch(UUID.randomUUID().toString());
        clock.start(call.toShortString());
        Object object= call.proceed();
        clock.stop();
        loggerDemo.log(clock.prettyPrint());
        return object;
    }
}
